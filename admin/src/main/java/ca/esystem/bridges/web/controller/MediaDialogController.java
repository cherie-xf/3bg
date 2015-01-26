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

import ca.esystem.bridges.domain.Media;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.MediaService;
import ca.esystem.framework.util.CommonUtil;
import ca.esystem.framework.util.PicUtil;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * 
 * @author Larry
 *
 */

@Controller
public class MediaDialogController extends AbstractController {

    @Resource(name="GlobalProperties")
    private Properties globalproperties;    
    
    @Resource(name = "MediaService")
    private MediaService mediaService;

        
    @RequestMapping(value = "/dialog/medialib", method = { RequestMethod.GET, RequestMethod.POST })
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
        return "/dialog/medialib";
    }
}
