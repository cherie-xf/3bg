package ca.esystem.bridges.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.domain.CategoryTree;
import ca.esystem.bridges.service.CategoryService;

@Controller
public class CategoryDialogController {
    @Resource(name = "CategoryService")
    private CategoryService categoryService;
    private final Logger        logger       = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping(value = "/dialog/categorydialog", method = { RequestMethod.GET })
    public String getCategoryDialog( Model model, HttpServletRequest request) throws Exception {

        // reflush the categorytree
        CategoryTree topCategory = categoryService.getStaticCategoryTree();
        List<CategoryTree> displayList = topCategory.getChildrenList();
        model.addAttribute("displayList", displayList);
        return "dialog/categorydialog";
    }   
    
}
