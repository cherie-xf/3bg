package ca.esystem.bridges.web.controller;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ca.esystem.bridges.domain.ServiceProduct;
import ca.esystem.bridges.domain.Content;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.ServiceProductMngService;
import ca.esystem.bridges.service.ContentService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * Index.html 首页
 * 
 * @author cherie
 *
 */
@Controller
public class PageIndexController extends AbstractController {

    private final Logger           logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Resource(name = "ServiceProductMngService")
    private ServiceProductMngService productservice;
    
    @Resource(name = "ContentService")
    private ContentService contentService;
    
    private BCryptPasswordEncoder encoder;
    
    public PageIndexController(){
        encoder = new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public String openIndexPage(Model model, HttpServletRequest request) throws Exception {
    	ServiceProduct serviceProductQuery = new ServiceProduct();
    	serviceProductQuery.setStatus("20");
    	serviceProductQuery.getPagination().setSkip(0);
    	serviceProductQuery.getPagination().setPageSize(6);
    	List recommendlist = productservice.queryServiceProductForIndex(serviceProductQuery);
    	model.addAttribute("recommendlist", recommendlist);

    	//news list 
    	Content contentquery = new Content();
    	contentquery.setOrderByClause("created_at desc");
    	contentquery.getPagination().setSkip(0);
    	contentquery.getPagination().setPageSize(3);
    	contentquery.setStatus("20");
    	List newslist = contentService.queryList(contentquery);
    	model.addAttribute("newslist", newslist);
    	
        return "/index";
    }
}
