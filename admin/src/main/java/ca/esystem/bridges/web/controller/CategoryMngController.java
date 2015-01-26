package ca.esystem.bridges.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

import ca.esystem.bridges.domain.*;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for category and category tag requests.
 * 
 * @author Lei
 *
 */
@Controller()
public class CategoryMngController extends AbstractController {
    private final Logger    logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "CategoryService")
    private CategoryService categoryService;

    @RequestMapping(value = "/category/browse", method = { RequestMethod.GET, RequestMethod.POST })
    public String browseCategoryList(@ModelAttribute("categoryQuery") Category categoryQuery, Model model, HttpServletRequest request) throws Exception {

        queryCategoryList(categoryQuery, model);

        return "/category/browse";
    }

    @RequestMapping(value = "/category/search", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchCategoryList(@ModelAttribute("categoryQuery") Category categoryQuery, Model model, HttpServletRequest request) throws Exception {

        queryCategoryList(categoryQuery, model);

        return "/category/search";
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.GET)
    public String openCreateCategoryForm(@ModelAttribute("category") Category categoryQuery, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        String parent_id = categoryQuery.getParent_id();
        String defaultId = generateDefaultCategoryId(parent_id);

        Category category = new Category();
        category.setCategory_id(defaultId);
        category.setCreated_by(user_id);
        category.setOperate("add");
        category.setParent_id(parent_id);
        model.addAttribute("category", category);
        return "/category/form";
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.GET)
    public String openUpdateCategoryForm(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) throws Exception {
        category = (Category) categoryService.queryOne(category);
        category.setOperate("update");
        model.addAttribute("category", category);
        return "/category/form";
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public String submitCreateCategoryForm(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) throws Exception {
        category.setCreated_at(new Date());
        categoryService.add(category);

        String parent_id = category.getParent_id();
        String message = "创建Category成功。";
        if (parent_id != null && parent_id.length() > 0) {
            message += "  <a class=\"btn btn-sm btn-success\" href=\"create.html?parent_id=" + parent_id + "\">继续创建</a>";
        } else {
            message += "  <a class=\"btn btn-sm btn-success\" href=\"create.html\">继续创建</a>";
        }

        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    public String submitUpdateCategoryForm(@ModelAttribute("category") Category category, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        category.setModified_by(user_id);
        category.setModified_at(new Date());

        categoryService.update(category);
        String message = "修改Category成功.  <a class=\"btn btn-sm btn-success\" href=\"update.html?category_id=" + category.getCategory_id() + "\">继续修改</a>";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/category/delete/{category_id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable String category_id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        Category category = new Category();
        category.setCategory_id(category_id);
        category.setModified_at(new Date());
        category.setModified_by(user_id);
        category.setIs_deleted(true);
        categoryService.delete(category);
        String message = "删除Category成功";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/category/tagmanage", method = RequestMethod.GET)
    public String openCategoryTagForm(@ModelAttribute("categorytag") CategoryTag tag, Model model, HttpServletRequest request) throws Exception {
        Category category = new Category();
        category.setCategory_id(tag.getCategory_id());
        category = (Category) categoryService.queryOne(category);
        model.addAttribute("category", category);

        tag.setOrderByClause("created_at desc");
        List<?> tagList = categoryService.queryTagListByCategoryId(tag);
        model.addAttribute("taglist", tagList);

        return "/category/tagform";
    }

    @RequestMapping(value = "/category/createtag", method = RequestMethod.POST)
    public String createCategoryTag(@ModelAttribute("categorytag") CategoryTag tag, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();
        tag.setCreated_at(new Date());
        tag.setCreated_by(user_id);
        categoryService.addTag(tag);

        tag.setTag(null);

        return updateCategoryTagModel(tag, model);
    }

    @RequestMapping(value = "/category/deletetag", method = RequestMethod.GET)
    public String deleteCategoryTag(@ModelAttribute("categorytag") CategoryTag tag, Model model, HttpServletRequest request) throws Exception {
        tag.setIs_deleted(true);
        categoryService.deleteTag(tag);

        return updateCategoryTagModel(tag, model);
    }

    private void queryCategoryList(Category categoryQuery, Model model) {
        int rownum = categoryService.queryCount(categoryQuery);
        categoryQuery.getPagination().setRowCount(rownum);
        categoryQuery.getPagination().setPageSize(20);
        int pagecount = categoryQuery.getPagination().getPageCount();
        int currentpage = categoryQuery.getPagination().getCurrentPage();
        List<?> pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        categoryQuery.setOrderByClause("a.priority asc, a.category_id asc");
        List<?> categorymnglist = categoryService.queryList(categoryQuery);
        model.addAttribute("categorymnglist", categorymnglist);

        String parent_id = categoryQuery.getParent_id();
        if (parent_id != null && parent_id != "") {
            Category category;
            List<Category> categorytreelist = new ArrayList<Category>();
            do {
                category = new Category();
                category.setCategory_id(parent_id);
                category = (Category) categoryService.queryOne(category);
                if (category != null) {
                    categorytreelist.add(category);
                    parent_id = category.getParent_id();
                }
            } while (category != null && parent_id != null);

            Collections.reverse(categorytreelist);
            model.addAttribute("categorytreelist", categorytreelist);
        }
    }

    private String updateCategoryTagModel(CategoryTag tag, Model model) {
        String category_id = tag.getCategory_id();

        Category category = new Category();
        category.setCategory_id(category_id);
        category = (Category) categoryService.queryOne(category);
        model.addAttribute("category", category);

        tag = new CategoryTag();
        tag.setCategory_id(category_id);
        tag.setOrderByClause("created_at desc");
        List<?> tagList = categoryService.queryTagListByCategoryId(tag);
        model.addAttribute("taglist", tagList);

        return "/category/tagform";
    }

    private String generateDefaultCategoryId(String parent_id) {
        Category categoryQuery = new Category();
        categoryQuery.setParent_id(parent_id);
        categoryQuery.setIs_deleted(null);
        categoryQuery.setOrderByClause("a.category_id DESC");

        @SuppressWarnings("unchecked")
        List<Category> list = categoryService.queryList(categoryQuery);

        String defaultId = "01";

        if (list != null && !list.isEmpty()) {
            String currentId = list.get(0).getCategory_id();
            String subId = currentId;

            Long id = (Long.parseLong(subId) + 1L);
            if (id < 10)
                subId = "0" + Long.toString(id);
            else
                subId = Long.toString(id);
            defaultId = subId;

            if (parent_id != null && parent_id.length() > 0) {
                int length = parent_id.length();
                subId = currentId.substring(length);
                id = (Long.parseLong(subId) + 1L);
                if (id < 10)
                    subId = "0" + Long.toString(id);
                else
                    subId = Long.toString(id);
                
                defaultId = parent_id + subId;
            }
        } else {
            if (parent_id != null) {
                defaultId = parent_id + defaultId;
            }
        }

        return defaultId;
    }
}
