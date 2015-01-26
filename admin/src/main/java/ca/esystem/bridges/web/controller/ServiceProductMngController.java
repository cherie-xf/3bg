package ca.esystem.bridges.web.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ca.esystem.bridges.dao.MembershipDao;
import ca.esystem.bridges.domain.*;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.bridges.service.MediaService;
import ca.esystem.bridges.service.ServiceProductMngService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for service product and related domain object requests.
 * 
 * @author Lei Han
 *
 */
@Controller()
public class ServiceProductMngController extends AbstractController {
    private final Logger             logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "GlobalProperties")
    private Properties               globalproperties;

    @Resource(name = "ServiceProductMngService")
    private ServiceProductMngService service;

    @Resource(name = "CategoryService")
    private CategoryService          categoryService;

    @Resource(name = "MediaService")
    private MediaService             mediaService;

    @Resource
    private MembershipDao            membershipDao;

    @RequestMapping(value = "/serviceproduct/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchServiceProductList(@ModelAttribute("serviceProductQuery") ServiceProduct serviceProductQuery, Model model, HttpServletRequest request)
            throws Exception {

        queryServiceProductList(serviceProductQuery, model);

        return "/serviceproduct/manage";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/serviceproduct/create", method = RequestMethod.GET)
    public String openCreateServiceProductForm(@ModelAttribute("serviceProduct") ServiceProduct serviceProductQuery, Model model, HttpServletRequest request)
            throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        ServiceProduct serviceProduct = new ServiceProduct();
        serviceProduct.setCreated_by(user_id);
        serviceProduct.setOperate("add");
        serviceProduct.setStatus_id("10");
        serviceProduct.setRecommend_level_id(0);
        model.addAttribute("serviceProduct", serviceProduct);

        Membership membershipQuery = new Membership();
        membershipQuery.setMember_id("B");
        membershipQuery.setSearchRecommended(false);
        List<Membership> list = membershipDao.queryListByCondition(membershipQuery);
        Map<String, String> memberIdNameMap = convertMembershipListtoMap(list);
        model.addAttribute("memberIdNameMap", memberIdNameMap);
        return "/serviceproduct/form";
    }

    @SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/serviceproduct/update", method = RequestMethod.GET)
    public String openUpdateServiceProductForm(@ModelAttribute("serviceProduct") ServiceProduct serviceProduct, Model model, HttpServletRequest request)
            throws Exception {
        serviceProduct = (ServiceProduct) service.queryOne(serviceProduct);
        serviceProduct.setOperate("update");
        model.addAttribute("serviceProduct", serviceProduct);

        Business_Category businessCategory = new Business_Category();
        String member_id = serviceProduct.getMember_id();
        businessCategory.setMember_id(member_id);

        List<Business_Category> list = (List<Business_Category>) membershipDao.queryBusinessCategoryList(businessCategory);

        HashMap<String, String> memberCategoryMap = convertListtoMap(list);
        model.addAttribute("memberCategoryMap", memberCategoryMap);

        return "/serviceproduct/form";
    }

    @RequestMapping(value = "/serviceproduct/create", method = RequestMethod.POST)
    public String submitCreateCategoryForm(@ModelAttribute("serviceProduct") ServiceProduct serviceProduct, Model model, HttpServletRequest request)
            throws Exception {
        serviceProduct.setCreated_at(new Date());
        service.add(serviceProduct);

        String message = "创建Service Product成功。  <a class=\"btn btn-sm btn-success\" href=\"create.html\">继续创建</a>";

        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/serviceproduct/update", method = RequestMethod.POST)
    public String submitUpdateCategoryForm(@ModelAttribute("serviceProduct") ServiceProduct serviceProduct, Model model, HttpServletRequest request)
            throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        serviceProduct.setModified_at(new Date());
        serviceProduct.setModified_by(user_id);

        service.update(serviceProduct);
        Integer service_id = serviceProduct.getService_id();
        String message = "修改Service Product成功。  <a class=\"btn btn-sm btn-success\" href=\"update.html?service_id=" + service_id + "\">继续修改</a>";

        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/serviceproduct/delete/{service_id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable int service_id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        ServiceProduct serviceProduct = new ServiceProduct();
        serviceProduct.setService_id(service_id);

        serviceProduct = (ServiceProduct) service.queryOne(serviceProduct);

        serviceProduct.setModified_at(new Date());
        serviceProduct.setModified_by(user_id);
        serviceProduct.setStatus_id("40"); //business requirement: delete means change status_id to 40.

        service.update(serviceProduct);
        String message = "修改ServiceProduct状态为删除。";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/serviceproduct/choosecategory", method = RequestMethod.GET)
    public String addKeywordtoTagForm(@ModelAttribute("businessCategory") Business_Category businessCategory, Model model, HttpServletRequest request)
            throws Exception {
        model.addAttribute("member_id", businessCategory.getMember_id());
        // get the category tree
        CategoryTree topCategory = categoryService.getStaticCategoryTree();
        List<CategoryTree> displayList = topCategory.getChildrenList();
        model.addAttribute("displayList", displayList);
        return "/serviceproduct/categorydialog";
    }

    @SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/serviceproduct/getMemberCategory", method = RequestMethod.GET)
    public @ResponseBody String refreshDictionary(Model model, HttpServletRequest request) throws Exception {
        String member_id = request.getParameter("member_id");
        Business_Category businessCategory = new Business_Category();
        businessCategory.setMember_id(member_id);

        List<Business_Category> list = (List<Business_Category>) membershipDao.queryBusinessCategoryList(businessCategory);

        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping(value = "/serviceproduct/medialib", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryMediaMngList(@ModelAttribute("mediaQuery") Media mediaQuery, Model model, HttpServletRequest request) throws Exception {

        int rownum = mediaService.queryCount(mediaQuery);
        mediaQuery.getPagination().setRowCount(rownum);
        mediaQuery.getPagination().setPageSize(30);
        int pagecount = mediaQuery.getPagination().getPageCount();
        int currentpage = mediaQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);
        mediaQuery.setOrderByClause("created_at desc");
        List mediamnglist = mediaService.queryList(mediaQuery);
        model.addAttribute("mediamnglist", mediamnglist);
        return "/serviceproduct/medialib";
    }

    private void queryServiceProductList(ServiceProduct serviceProductQuery, Model model) {
        int rownum = service.queryCount(serviceProductQuery);
        serviceProductQuery.getPagination().setRowCount(rownum);
        serviceProductQuery.getPagination().setPageSize(20);
        int pagecount = serviceProductQuery.getPagination().getPageCount();
        int currentpage = serviceProductQuery.getPagination().getCurrentPage();
        List<?> pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        serviceProductQuery.setOrderByClause("a.service_id desc"); // Latest created will be displayed first.
        List<?> serviceProductList = service.queryList(serviceProductQuery);
        model.addAttribute("serviceProductList", serviceProductList);
    }

    private HashMap<String, String> convertListtoMap(List<Business_Category> list) {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        for (Business_Category dictionary : list) {
            hashmap.put(dictionary.getCategory_id(), dictionary.getCategory_name());
        }
        return hashmap;
    }

    private HashMap<String, String> convertMembershipListtoMap(List<Membership> list) {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        for (Membership dictionary : list) {
            hashmap.put(dictionary.getMember_id(), dictionary.getMember_id());
        }
        return hashmap;
    }
}
