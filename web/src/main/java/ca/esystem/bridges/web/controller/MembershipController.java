package ca.esystem.bridges.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ca.esystem.bridges.dao.SysSequenceDao;
import ca.esystem.bridges.domain.*;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.util.CommonUtil;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for membership and business_profile requests.
 * 
 * @author Lei Han
 *
 */
@Controller()
public class MembershipController extends AbstractController {
    private final Logger      logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "GlobalProperties")
    private Properties        globalproperties;

    @Resource(name = "MembershipService")
    private MembershipService membershipService;
    
    @Resource(name = "TicketService")
    private TicketService ticketService;

    @Resource(name = "CategoryService")
    private CategoryService   categoryService;

    @Resource(name = "UserService")
    private UserService       userService;

    @Resource
    private SysSequenceDao    sysSequenceDao;

    @RequestMapping(value = "/membership/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchMembershipList(@ModelAttribute("membershipQuery") Membership membershipQuery, Model model, HttpServletRequest request) throws Exception {

        LoggedInUser loginUser = getLoggedInUser();
        int loginUserId = loginUser.getUserid();        
        membershipQuery.setUser_id(loginUserId);
        membershipQuery.setMemberTypeNotIs("S");
        
        queryMembershipList(membershipQuery, model);

        return "/membership/manage";
    }

    
    @RequestMapping(value = "/membership/updatetype", method = RequestMethod.GET)
    public String openUpdateTypeform(@ModelAttribute("memberTypeChangeForm") MemberTypeChangeForm memberTypeChangeForm, Model model, HttpServletRequest request) throws Exception {
        
        Membership membership = new Membership();
        membership.setMember_id(memberTypeChangeForm.getMember_id());
        membership = (Membership) membershipService.queryList(membership).get(0);
        
        memberTypeChangeForm.setCurrentTypeCode(membership.getType_code());
        
        @SuppressWarnings("unchecked")
		Map<String,String> memberTypeMap = (Map<String, String>) request.getSession().getServletContext().getAttribute("memberTypeMap");
        
        memberTypeChangeForm.setCurrentTypeName(memberTypeMap.get(membership.getType_code()));        
        
        model.addAttribute("memberTypeChangeForm", memberTypeChangeForm);
        return "/membership/updatetypeform";
    }    
    
    @RequestMapping(value = "/membership/updatetype", method = RequestMethod.POST)
    public String submitUpdateTypeForm(@ModelAttribute("memberTypeChangeForm") MemberTypeChangeForm memberTypeChangeForm, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser loginUser = getLoggedInUser();
        int user_id = loginUser.getUserid();
        
        User userQuery = new User();
        userQuery.setId(user_id);
        
        User user = (User) userService.queryOne(userQuery);        
        
        Ticket memberApplication = new Ticket();
        String ticketTitle = "用户" + user.getLastName()
                + " " + user.getFirstName() + "申请由会员类别 '"
                + memberTypeChangeForm.getCurrentTypeName() + "' 变更为 '"
                + memberTypeChangeForm.getNewTypeName() + "'";
        memberApplication.setTitle(ticketTitle);
        String description = "会员ID: "
                + memberTypeChangeForm.getMember_id() + "<br>"
                + "姓名: " + user.getLastName() + ", "
                + user.getFirstName() + "<br>" + "电话: "
                + user.getPhone() + "<br>" + "Email:"
                + user.getEmail() + "<br>" 
                + "变更原因: " + memberTypeChangeForm.getChangeReason();
        memberApplication.setDescription(description);
        memberApplication.setStatus_code("10");
        memberApplication.setType_code("RGST");
        memberApplication.setCreated_at(new Date());
        memberApplication.setCreated_by(user_id);
        memberApplication.setUser_id(user_id);
        memberApplication.setEmail(user.getEmail());
        memberApplication.setPhone(user.getPhone());        
        
        ticketService.add(memberApplication);

        String message = "升降级申请提交成功";
        model.addAttribute("message", message);
        return "dialog/success";
    }
    
    @RequestMapping(value = "/membership/create", method = RequestMethod.GET)
    public String openCreateMemberForm(@ModelAttribute("membership") Membership membershipQuery, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser loginUser = getLoggedInUser();
        int loginUserId = loginUser.getUserid();

        Membership membership = new Membership();
        
        membership.setUser_id(loginUserId);

        User user = new User();
        user.setId(loginUserId);

        user = (User) userService.queryOne(user);

        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setOwner_name(user.getFirstName() + ' ' + user.getLastName());
        businessProfile.setEmail(user.getEmail());
        businessProfile.setPhone(user.getPhone());
        
        membership.setBusinessProfile(businessProfile);
        
        membership.setCreated_by(loginUserId);
        membership.setOperate("add");
        model.addAttribute("membership", membership);
        
        membershipQuery.setUser_id(loginUserId);
        membershipQuery.setType_code("C");
        List<?> list  = membershipService.queryList(membershipQuery);
        if(list.size() > 0){
            membership.setMemberTypeNotIs("C");
        }else{
            membership.setMemberTypeNotIs("S");
        }
        
        return "/membership/form";
    }

    @RequestMapping(value = "/membership/create", method = RequestMethod.POST)
    public String submitCreateCategoryForm(@ModelAttribute("membership") Membership membership, Model model, HttpServletRequest request) throws Exception {

        Business_Profile businessProfile = membership.getBusinessProfile();
        int user_id = membership.getUser_id();

        String type_code = membership.getType_code();
        String defaultId = generateDefaultMembershipId(type_code);
        membership.setMember_id(defaultId);        

        uploadFile(businessProfile, user_id);

        membership.setCreated_at(new Date());
        
        String categoryids = businessProfile.getCategoryids();
        
        if (CommonUtil.isNotEmptyStr(categoryids)) {
            List<Business_Category> category_list = new ArrayList<Business_Category>();            
            String[] categoryArray = categoryids.split(",");
            for (int i = 0; i < categoryArray.length; i++) {
                if (CommonUtil.isNotEmptyStr(categoryArray[i])) {
                    Business_Category category = new Business_Category();
                    category.setCategory_id(categoryArray[i]);                    
                    category_list.add(category);
                }
            }
            businessProfile.setCategory_list(category_list);
        }       
        
        membershipService.insertMemberAndBusinessProfile(membership);        
        
        membershipService.getMemberAndBusinessProfile(membership);
        
        User user = new User();
        user.setId(user_id);
        user = (User) userService.queryOne(user);
        
        Ticket ticket = new Ticket();
        
        String ticketTitle = "用户" + user.getLastName()
                + " " + user.getFirstName() + "申请成为 '"
                + membership.getType_name() + "' 会员";
                
        ticket.setTitle(ticketTitle);
        String description = "会员ID: "
                + membership.getMember_id() + "<br>"
                + "姓名: " + user.getLastName() + ", "
                + user.getFirstName() + "<br>" + "电话: "
                + user.getPhone() + "<br>" + "Email:"
                + user.getEmail();
        ticket.setDescription(description);        
        ticket.setStatus_code("10");
        ticket.setType_code("RGST");
        ticket.setCreated_at(new Date());
        ticket.setCreated_by(user_id);
        ticket.setUser_id(user_id);
        ticket.setEmail(user.getEmail());
        ticket.setPhone(user.getPhone());        
        
        ticketService.add(ticket);

        String message = "新建会员申请提交成功";
        model.addAttribute("message", message);
        return "dialog/success";
    }
    
    @RequestMapping(value = "/membership/update", method = RequestMethod.GET)
    public String openUpdateMembershipForm(@ModelAttribute("membership") Membership membership, Model model, HttpServletRequest request) throws Exception {
        membership = (Membership) membershipService.getMemberAndBusinessProfile(membership);
        
        Business_Profile businessProfile = membership.getBusinessProfile();
        List<Business_Category> category_list = businessProfile.getCategory_list();
        if(category_list != null){
            String categoryIds = "";
            String categoryNames = "";      
            for(Business_Category category : category_list){
                categoryIds += category.getCategory_id()+",";
                categoryNames += category.getCategory_name() + " ";
            }        
            businessProfile.setCategoryids(categoryIds);
            businessProfile.setCategorynames(categoryNames);
        }       
        
        membership.setOperate("update");
        model.addAttribute("membership", membership);
        return "/membership/form";
    }

    @RequestMapping(value = "/membership/update", method = RequestMethod.POST)
    public String submitUpdateCategoryForm(@ModelAttribute("membership") Membership membership, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        Business_Profile businessProfile = membership.getBusinessProfile();

        uploadFile(businessProfile, user_id);

        membership.setModified_at(new Date());
        membership.setModified_by(user_id);
        
        String categoryids = businessProfile.getCategoryids();
        
        String member_id = membership.getMember_id();
        
        if (CommonUtil.isNotEmptyStr(categoryids)) {
            List<Business_Category> category_list = new ArrayList<Business_Category>();            
            String[] categoryArray = categoryids.split(",");
            for (int i = 0; i < categoryArray.length; i++) {
                if (CommonUtil.isNotEmptyStr(categoryArray[i])) {
                    Business_Category category = new Business_Category();
                    category.setCategory_id(categoryArray[i]);      
                    category.setMember_id(member_id);
                    category_list.add(category);
                }
            }
            businessProfile.setCategory_list(category_list);
        } 

        membershipService.updateMemberAndBusinessProfile(membership);
        String message = "更新服务商信息成功";
        model.addAttribute("message", message);
        return "dialog/success";
    }

    @RequestMapping(value = "/membership/delete/{member_id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable String member_id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        Membership membership = new Membership();
        membership.setMember_id(member_id);
        membership.setModified_at(new Date());
        membership.setModified_by(user_id);
        membership.setIs_deleted(true);

        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setMember_id(member_id);
        businessProfile.setModified_at(new Date());
        businessProfile.setModified_by(1);
        businessProfile.setIs_deleted(true);

        membership.setBusinessProfile(businessProfile);

        membershipService.deleteMemberAndBusinessProfile(membership);
        String message = "删除Membership成功";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/membership/choosecategory", method = RequestMethod.GET)
    public String addKeywordtoTagForm(@ModelAttribute("businessCategory") Business_Category businessCategory, Model model, HttpServletRequest request)
            throws Exception {
        model.addAttribute("member_id", businessCategory.getMember_id());

        // get the category tree
        CategoryTree topCategory = categoryService.getStaticCategoryTree();
        List<CategoryTree> displayList = topCategory.getChildrenList();
        model.addAttribute("displayList", displayList);
        return "/membership/categorydialog";
    }

    private void queryMembershipList(Membership membershipQuery, Model model) {
        int rownum = membershipService.queryCount(membershipQuery);
        membershipQuery.getPagination().setRowCount(rownum);
        membershipQuery.getPagination().setPageSize(20);
        int pagecount = membershipQuery.getPagination().getPageCount();
        int currentpage = membershipQuery.getPagination().getCurrentPage();
        List<?> pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        membershipQuery.setOrderByClause("a.member_id asc");
        List<?> membershiplist = membershipService.queryList(membershipQuery);
        model.addAttribute("membershiplist", membershiplist);
    }

    private String generateDefaultMembershipId(String type_code) {

        String seqName = type_code.substring(0, 1);
        int seqValue = sysSequenceDao.queryNextVal(seqName);
        String memberNumber = CommonUtil.addZeroBeforeNumber(7, "" + seqValue);
        String memberid = seqName + memberNumber;

        return memberid;
    }

    private void uploadFile(Business_Profile businessProfile, int user_id) {
        CommonsMultipartFile supportFile = businessProfile.getSupport_doc_file();
        if (supportFile != null && !supportFile.isEmpty()) {
            loadProperties(globalproperties);
            String fileName = supportFile.getOriginalFilename();
            String uploadFileName = getRootDir() + getAttachPath() + "/" + user_id + "/" + fileName;
            File uploadFile = new File(uploadFileName);

            try {
                byte[] bytes = supportFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                logger.debug(e.getMessage());
            }
            String support_doc = getAttachPath() + "/" + user_id + "/" + fileName;
            businessProfile.setSupport_doc(support_doc);
        }
    }
}
