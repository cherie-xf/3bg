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
import ca.esystem.framework.web.controller.AbstractController;

@Controller
public class NavigationController extends AbstractController {
	@Resource(name = "CategoryService")
	private CategoryService categoryService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@RequestMapping(value = "/navigation/loadnavigation", method = { RequestMethod.GET })
	public @ResponseBody String loadNavigation(Model model, HttpServletRequest request)throws Exception {
		if (getAppScopeObj(request, "globalnav") == null) {
			CategoryTree topCategory = categoryService.getStaticCategoryTree();
			List<CategoryTree> globalnav = topCategory.getChildrenList();
			setAppScopeObj(request, "globalnav", globalnav);
		}
		return "loaded navigation";
	}
	
	@RequestMapping(value = "/navigation/reloadnavigation", method = { RequestMethod.GET })
	public @ResponseBody String reloadNavigation(Model model, HttpServletRequest request)throws Exception {
		    categoryService.reflush();
			CategoryTree topCategory = categoryService.getStaticCategoryTree();
			List<CategoryTree> globalnav = topCategory.getChildrenList();
			setAppScopeObj(request, "globalnav", globalnav);
		return "reloaded navigation";
	}	
}
