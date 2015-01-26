package ca.esystem.bridges.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.esystem.bridges.domain.Blog;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.BlogService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for blog management.
 * 
 * @author cherie
 *
 */
@Controller
public class BlogMngController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource(name = "BlogService")
    private BlogService  blogService;

    @RequestMapping(value = "/blog/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryBlogMngList(@ModelAttribute("blogQuery") Blog blogQuery, Model model, HttpServletRequest request) throws Exception {

        logger.debug("Received request at /blog/manage/");

        int rownum = blogService.queryCount(blogQuery);
        blogQuery.getPagination().setRowCount(rownum);
        blogQuery.getPagination().setPageSize(20);
        int pagecount = blogQuery.getPagination().getPageCount();
        int currentpage = blogQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        blogQuery.setOrderByClause("created_at desc");
        /*List<String> statusCodeList = new ArrayList<String>();
        statusCodeList.add("10");
        statusCodeList.add("20");*/
        
        //blogQuery.setStatusCodeList(statusCodeList);
        List blogtmnglist = blogService.queryList(blogQuery);
        model.addAttribute("blogmnglist", blogtmnglist);

        return "/blog/manage";
    }

    @RequestMapping(value = "/blog/create", method = RequestMethod.GET)
    public String openCreateBlogForm(Model model, HttpServletRequest request) throws Exception {
        // blogQuery contains customers' personal information when creating blog from user managerment

        Blog blog = new Blog();
        blog.setOperate("add");
        model.addAttribute("blog", blog);
        return "/blog/form";
    }

    @RequestMapping(value = "/blog/create", method = RequestMethod.POST)
    public String submitCreateblogForm(@ModelAttribute("blog") Blog blog, Model model, HttpServletRequest request) throws Exception {
        logger.debug("Received request at /blog/create");
        // get user info
        LoggedInUser user = getLoggedInUser();
        blog.setCreated_by(user.getUserid());
        blog.setCreated_at(new Date());
        blogService.add(blog);

        String message = "创建blog成功";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/blog/update", method = RequestMethod.GET)
    public String openUpdateBlogForm(@RequestParam("id") int blogId, Model model, HttpServletRequest request) throws Exception {
        // blogQuery contains customers' personal information when creating blog from user managerment

        Blog qureyblog = new Blog();
        qureyblog.setBlog_id(blogId);
        Blog updateblog = (Blog) blogService.queryOne(qureyblog);

        updateblog.setOperate("update");

        model.addAttribute("blog", updateblog);
        return "/blog/form";
    }

    @RequestMapping(value = "/blog/update", method = RequestMethod.POST)
    public String submitUpateblogForm(@ModelAttribute("blog") Blog blog, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        blog.setModified_by(user.getUserid());
        blog.setModified_at(new Date());
        blogService.update(blog);

        String message = "更新blog成功";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/blog/delete", method = RequestMethod.GET)
    public String deleteBlog(@RequestParam("id") int blogId, Model model, HttpServletRequest request) throws Exception {
        Blog blog = new Blog();
        blog.setBlog_id(blogId);
        blogService.delete(blog);

        String message = "删除blog成功";
        model.addAttribute("message", message);
       // return "redirect:manage.html";
        return "success";
    }

    
}
