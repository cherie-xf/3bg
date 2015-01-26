package ca.esystem.bridges.web.controller;

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

import ca.esystem.bridges.dao.SysUserDao;
import ca.esystem.bridges.domain.SysUser;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * 
 * @author Larry
 *
 */

@Controller
public class SysUserMngController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private SysUserDao   sysUserDao;

    public SysUserDao getSysUserDao() {
        return sysUserDao;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
    public String adminlogin(@ModelAttribute("userquery") SysUser sysUser, Model model, HttpServletRequest request) {
        sysUser.setOperate("add");
        return "adminlogin";
    }    
    
    @RequestMapping(value = "/usermng", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryUserMngList(@ModelAttribute("userQuery") SysUser userQuery, Model model, HttpServletRequest request) {
        userQuery.setOrderByClause("inputtime desc");
        List usermnglist = null;

        int rownum = sysUserDao.queryCountRowsByCondition(userQuery);
        userQuery.getPagination().setRowCount(rownum);
        userQuery.getPagination().setPageSize(10);
        int pagecount = userQuery.getPagination().getPageCount();
        int currentpage = userQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        usermnglist = sysUserDao.queryListByCondition(userQuery);
        model.addAttribute("usermnglist", usermnglist);
        return "usermng";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUserGet(@ModelAttribute("sysUser") SysUser sysUser, Model model, HttpServletRequest request) {
        sysUser.setOperate("add");
        return "userform";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("sysUser") SysUser sysUser, Model model, HttpServletRequest request) {
        sysUser.setTcpip(request.getRemoteAddr());
        sysUser.setInputtime(new Date());
        try {
            int userid = (Integer) sysUserDao.insert(sysUser);
            System.out.println("userid=" + sysUser.getUserid());
            sysUser.setFeedbackMessage("用户 <font color=red>" + sysUser.getEmail() + "</font> 添加成功，填写下表继续添加新用户");
        } catch (Exception e) {
            sysUser.setFeedbackMessage("用户<font color=red>" + sysUser.getEmail() + "</font> 已存在或者系统出故障" + e.getMessage());
        }
        return "userform";
    }

}
