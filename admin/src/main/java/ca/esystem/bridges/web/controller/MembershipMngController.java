package ca.esystem.bridges.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
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
public class MembershipMngController extends AbstractController {
    private final Logger      logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "GlobalProperties")
    private Properties        globalproperties;

    @Resource(name = "MembershipService")
    private MembershipService membershipService;

    @Resource(name = "CategoryService")
    private CategoryService   categoryService;

    @Resource(name = "UserService")
    private UserService       userService;

    @Resource
    private SysSequenceDao    sysSequenceDao;

    @RequestMapping(value = "/membership/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchMembershipList(@ModelAttribute("membershipQuery") Membership membershipQuery, Model model, HttpServletRequest request) throws Exception {

        queryMembershipList(membershipQuery, model);

        return "/membership/manage";
    }

    @RequestMapping(value = "/membership/create", method = RequestMethod.GET)
    public String openCreateMemberForm(@ModelAttribute("membership") Membership membershipQuery, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser loginUser = getLoggedInUser();
        int loginUserId = loginUser.getUserid();

        Membership membership = new Membership();

        int user_id = membershipQuery.getUser_id();
        membership.setUser_id(user_id);

        User user = new User();
        user.setId(user_id);

        user = (User) userService.queryOne(user);

        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setOwner_name(user.getFirstName() + ' ' + user.getLastName());
        businessProfile.setEmail(user.getEmail());
        businessProfile.setPhone(user.getPhone());
        
        membership.setBusinessProfile(businessProfile);

        membership.setCreated_by(loginUserId);
        membership.setOperate("add");
        model.addAttribute("membership", membership);
        return "/membership/form";
    }

    @RequestMapping(value = "/membership/update", method = RequestMethod.GET)
    public String openUpdateMembershipForm(@ModelAttribute("membership") Membership membership, Model model, HttpServletRequest request) throws Exception {
        membership = (Membership) membershipService.getMemberAndBusinessProfile(membership);
        membership.setOperate("update");
        model.addAttribute("membership", membership);
        return "/membership/form";
    }

    @RequestMapping(value = "/membership/create", method = RequestMethod.POST)
    public String submitCreateCategoryForm(@ModelAttribute("membership") Membership membership, Model model, HttpServletRequest request) throws Exception {

        Business_Profile businessProfile = membership.getBusinessProfile();
        int user_id = membership.getUser_id();

        if (!membership.isMemberIDManualInput()) {
            String type_code = membership.getType_code();
            String defaultId = generateDefaultMembershipId(type_code);
            membership.setMember_id(defaultId);
        }

        uploadFile(businessProfile, user_id);

        membership.setCreated_at(new Date());
        membershipService.insertMemberAndBusinessProfile(membership);

        String message = "创建Membership成功";
        model.addAttribute("message", message);
        return "success";
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

        membershipService.updateMemberAndBusinessProfile(membership);
        String message = "修改Membership成功";
        model.addAttribute("message", message);
        return "success";
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
