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

import ca.esystem.bridges.domain.Content;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.ContentService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * 
 * @author Larry
 *
 */
@Controller
public class ContentMngController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource(name = "ContentService")
    private ContentService  contentService;

    @RequestMapping(value = "/content/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryContentMngList(@ModelAttribute("contentQuery") Content contentQuery, Model model, HttpServletRequest request) throws Exception {

        logger.debug("Received request at /content/manage/");
        int rownum = contentService.queryCount(contentQuery);       
        contentQuery.getPagination().setRowCount(rownum);
        contentQuery.getPagination().setPageSize(20);
        int pagecount = contentQuery.getPagination().getPageCount();
        int currentpage = contentQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);
        contentQuery.setOrderByClause("created_at desc");
        List contenttmnglist = contentService.queryList(contentQuery);
        model.addAttribute("contentmnglist", contenttmnglist);
        
        return "/content/manage";
    }

    @RequestMapping(value = "/content/create", method = RequestMethod.GET)
    public String openCreateContentForm(Model model, HttpServletRequest request) throws Exception {
        // contentQuery contains customers' personal information when creating content from user managerment

        Content content = new Content();
        content.setOperate("add");
        content.setStatus("10");
        model.addAttribute("content", content);
        return "/content/form";
    }

    @RequestMapping(value = "/content/create", method = RequestMethod.POST)
    public String submitCreatecontentForm(@ModelAttribute("content") Content content, Model model, HttpServletRequest request) throws Exception {
        logger.debug("Received request at /content/create");
        // get user info
        LoggedInUser user = getLoggedInUser();
        content.setCreated_by(user.getUserid());
        content.setCreated_at(new Date());
        contentService.add(content);

        String message = "创建content成功, <a class=\"btn btn-success btn-sm\" href=\"create.html\">继续创建</a>";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/content/update", method = RequestMethod.GET)
    public String openUpdateContentForm(@RequestParam("sid") int sid, Model model, HttpServletRequest request) throws Exception {
        // contentQuery contains customers' personal information when creating content from user managerment
        Content qureycontent = new Content();
        qureycontent.setSid(sid);
        Content updatecontent = (Content) contentService.queryOne(qureycontent);
        updatecontent.setOperate("update");
        model.addAttribute("content", updatecontent);
        return "/content/form";
    }

    @RequestMapping(value = "/content/update", method = RequestMethod.POST)
    public String submitUpatecontentForm(@ModelAttribute("content") Content content, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        content.setModified_by(user.getUserid());
        content.setModified_at(new Date());
        contentService.update(content);
        String message = "更新content成功";
        content.setFeedbackMessage(message);
        return "/content/form";
    }

    @RequestMapping(value = "/content/delete", method = RequestMethod.GET)
    public String deleteContent(@RequestParam("sid") int sid, Model model, HttpServletRequest request) throws Exception {
        Content content = new Content();
        content.setSid(sid);
        contentService.delete(content);
        String message = "删除content成功";
        model.addAttribute("message", message);
        return "success";
    }

    
}
