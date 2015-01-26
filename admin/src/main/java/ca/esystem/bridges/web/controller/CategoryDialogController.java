package ca.esystem.bridges.web.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ca.esystem.bridges.domain.CategoryTree;
import ca.esystem.bridges.domain.Search_Missed;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * 
 * @author Larry
 *
 */

@Controller
public class CategoryDialogController extends AbstractController {

    @Resource(name = "CategoryService")
    private CategoryService categoryService;

    @RequestMapping(value = "/dialog/leafcategory", method = { RequestMethod.GET })
    public String getCategoryDialog( Model model, HttpServletRequest request) throws Exception {

        // reflush the categorytree
        categoryService.reflush();
        CategoryTree topCategory = categoryService.getStaticCategoryTree();
        List<CategoryTree> displayList = topCategory.getChildrenList();
        model.addAttribute("displayList", displayList);
        return "dialog/leafcategory";
    }
}
