package ca.esystem.bridges.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

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

import ca.esystem.bridges.dao.DictionaryDao;
import ca.esystem.bridges.dao.InitBusinessInputDao;
import ca.esystem.bridges.domain.*;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for initial business input requests.
 * 
 * @author Lei Han
 *
 */
@Controller()
public class InitBusinessMngController extends AbstractController {
    private final Logger      logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "GlobalProperties")
    private Properties        globalproperties;

    @Resource(name = "MembershipService")
    private MembershipService membershipService;

    @Resource(name = "CategoryService")
    private CategoryService   categoryService;

    @Resource
    private DictionaryDao    dictionaryDao;
    
    @Resource
    private InitBusinessInputDao    initBusinessInputDao;

    @RequestMapping(value = "/initbusinessinput/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchList(@ModelAttribute("businessProfileQuery") Business_Profile businessProfileQuery, Model model, HttpServletRequest request) throws Exception {

        queryBusinessprofileList(businessProfileQuery, model);

        return "/businessinput/manage";
    }

    @RequestMapping(value = "/initbusinessinput/create", method = RequestMethod.GET)
    public String openCreateForm(@ModelAttribute("businessProfile") Business_Profile businessProfile, Model model, HttpServletRequest request) throws Exception {
        String tcpip = request.getRemoteAddr();

        businessProfile.setCreated_by(0);
        businessProfile.setTcpip(tcpip);
        businessProfile.setOperate("add");
        model.addAttribute("businessProfile", businessProfile);        
        model.addAttribute("recommendLevelMap", getRecommandLevelMap());
        return "/businessinput/form";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/initbusinessinput/update", method = RequestMethod.GET)
    public String openUpdateForm(@ModelAttribute("businessProfile") Business_Profile businessProfile, Model model, HttpServletRequest request) throws Exception {
        businessProfile = (Business_Profile) initBusinessInputDao.queryObjectByCondition(businessProfile);
        
        Business_Category businessCategory = new Business_Category();
        businessCategory.setMember_id(businessProfile.getMember_id());
        List<Business_Category> businessCategoryList = (List<Business_Category>) initBusinessInputDao.queryBusinessCategoryList(businessCategory);

        if (businessCategoryList != null && businessCategoryList.size() > 0) {
            businessProfile.setCategory_list(businessCategoryList);
        }
        businessProfile.setOperate("update");
        model.addAttribute("businessProfile", businessProfile);
        model.addAttribute("recommendLevelMap", getRecommandLevelMap());
        return "/businessinput/form";
    }

    @RequestMapping(value = "/initbusinessinput/create", method = RequestMethod.POST)
    public String submitCreateForm(@ModelAttribute("businessProfile") Business_Profile businessProfile, Model model, HttpServletRequest request) throws Exception {

        businessProfile.setMember_id(genereateMemberId());        

        businessProfile.setCreated_at(new Date());
        initBusinessInputDao.insert(businessProfile);
        
        List<Business_Category> newBusinessCategoryList = businessProfile.getCategory_list();
        if (newBusinessCategoryList != null) {
            for (Business_Category businessCategoryNew : newBusinessCategoryList) {
                businessCategoryNew.setMember_id(businessProfile.getMember_id());
                initBusinessInputDao.insertBusinessCategory(businessCategoryNew);
            }
        }

        String message = "创建Business profile成功.  <a class=\"btn btn-sm btn-success\" href=\"create.html\">继续创建</a>";
        model.addAttribute("message", message);
        return "success";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/initbusinessinput/update", method = RequestMethod.POST)
    public String submitUpdateForm(@ModelAttribute("businessProfile") Business_Profile businessProfile, Model model, HttpServletRequest request) throws Exception {
        //LoggedInUser user = getLoggedInUser();
        //System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        //int user_id = user.getUserid();

        businessProfile.setModified_at(new Date());
        businessProfile.setModified_by(0);

        initBusinessInputDao.update(businessProfile);
        
        Business_Category businessCategory = new Business_Category();
        businessCategory.setMember_id(businessProfile.getMember_id());
        
        List<Business_Category> oldBusinessCategoryList = (List<Business_Category>) initBusinessInputDao.queryBusinessCategoryList(businessCategory);
        for (Business_Category businessCategoryOld : oldBusinessCategoryList) {
            initBusinessInputDao.deleteBusinessCategory(businessCategoryOld);
        }

        List<Business_Category> newBusinessCategoryList = businessProfile.getCategory_list();
        if (newBusinessCategoryList != null) {
            for (Business_Category businessCategoryNew : newBusinessCategoryList) {
                initBusinessInputDao.insertBusinessCategory(businessCategoryNew);
            }
        }
        String message = "修改Business profile成功.  <a class=\"btn btn-sm btn-success\" href=\"update.html?member_id=" + businessProfile.getMember_id() + "\">继续修改</a>";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/initbusinessinput/delete/{member_id}", method = RequestMethod.GET)
    public String delete(@PathVariable String member_id, Model model, HttpServletRequest request) throws Exception {
        //LoggedInUser user = getLoggedInUser();
        //System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        //int user_id = user.getUserid();

        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setMember_id(member_id);
        businessProfile.setModified_at(new Date());
        businessProfile.setModified_by(0);
        businessProfile.setIs_deleted(true);

        initBusinessInputDao.delete(businessProfile);
        String message = "删除Business Profile成功";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/initbusinessinput/choosecategory", method = RequestMethod.GET)
    public String addKeywordtoTagForm(@ModelAttribute("businessCategory") Business_Category businessCategory, Model model, HttpServletRequest request)
            throws Exception {
        model.addAttribute("member_id", businessCategory.getMember_id());

        // get the category tree
        CategoryTree topCategory = categoryService.getStaticCategoryTree();
        List<CategoryTree> displayList = topCategory.getChildrenList();
        model.addAttribute("displayList", displayList);
        return "/businessinput/categorydialog";
    }

    private void queryBusinessprofileList(Business_Profile businessprofileQuery, Model model) {
        int rownum = initBusinessInputDao.queryCountRowsByCondition(businessprofileQuery);
        businessprofileQuery.getPagination().setRowCount(rownum);
        businessprofileQuery.getPagination().setPageSize(20);
        int pagecount = businessprofileQuery.getPagination().getPageCount();
        int currentpage = businessprofileQuery.getPagination().getCurrentPage();
        List<?> pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        businessprofileQuery.setOrderByClause("a.member_id desc");
        List<?> businessProfileList = initBusinessInputDao.queryListByCondition(businessprofileQuery);
        model.addAttribute("businessProfileList", businessProfileList);
    }
    
    private String genereateMemberId() {        
        String newMeberId = null;        
        String maxMemberId = initBusinessInputDao.getMaxMemberId();
        
        if(maxMemberId == null || maxMemberId.length() == 0){
            newMeberId = "N0000001"; // N plus 7 digit number.
        }else{
            Integer number = Integer.parseInt(maxMemberId.substring(1)) + 1;
            newMeberId = "N" + String.format("%07d", number);
        } 

        return newMeberId;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private TreeMap getRecommandLevelMap(){
        List<Dictionary> recommendlevelList = (List<Dictionary>) dictionaryDao.queryRecommendlevelList();
        TreeMap hashmap = new TreeMap();
        for (Dictionary dictionary : recommendlevelList) {
            hashmap.put(dictionary.getCode(), dictionary.getName());
        }
        return hashmap;
    }
}
