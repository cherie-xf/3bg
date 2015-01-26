package ca.esystem.framework.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ca.esystem.bridges.security.LoggedInUser;

public class AbstractController {  
    /**
     * The path that store the csv files uploaded from client side. The default OS is Windows
     */
    private String     DomainName;
    private String     RootDir;
    private String     TempDir;
    private String     BigpicPath;
    private String     ThumbPath;
    private String     AttachPath;

    public AbstractController(){
        
    }
    
    public void loadProperties(Properties globalproperties) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("linux") >= 0 || os.indexOf("unix") >= 0 || os.indexOf("mac") >= 0) {
            this.RootDir = globalproperties.getProperty("DocumentRoot.Linux");
        } else {
            this.RootDir = globalproperties.getProperty("DocumentRoot.Windows");
        }
        
        this.DomainName = globalproperties.getProperty("DomainName");
        this.TempDir = globalproperties.getProperty("Folder.Temp");
        this.BigpicPath = globalproperties.getProperty("Folder.Media.Bigpic");
        this.ThumbPath = globalproperties.getProperty("Folder.Media.Thumb");
        this.AttachPath = globalproperties.getProperty("Folder.Attach");
        
        System.out.println("******************");
        System.out.println("Temp Dir=" + this.TempDir);
        System.out.println("DocumentRoot Dir=" + this.RootDir);
    }

    public String getTempDir() {
        return TempDir;
    }

    public String getBigpicPath() {
        return BigpicPath;
    }

    public String getThumbPath() {
        return ThumbPath;
    }

    public String getRootDir() {
        return RootDir;
    }

    public String getAttachPath() {
        return AttachPath;
    }

    public String getDomainName() {
        return DomainName;
    }
    
    public LoggedInUser getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoggedInUser) {
            return (LoggedInUser) principal;
        } else {
            return null;
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("syserror");
        modelAndView.addObject("errormsg", e.getMessage());
        return modelAndView;
    }

    /**
     * 控制分页
     * 
     * @param currentpage
     * @param pagecount
     * @return
     */
    public List getPageNumList(int currentpage, int pagecount) {
        List pageNumList = new ArrayList();
        if (currentpage <= 20) {
            for (int i = 1; i <= 20; i++) {
                if (i > pagecount)
                    break;
                pageNumList.add("" + i);
            }
        } else {
            for (int i = currentpage - 10; i <= currentpage + 10; i++) {
                if (i > pagecount)
                    break;
                pageNumList.add("" + i);
            }
        }
        return pageNumList;
    }	
    
    public Object getAppScopeObj(HttpServletRequest request, String objname) {
        return request.getSession().getServletContext().getAttribute(objname);
    }

    public void setAppScopeObj(HttpServletRequest request, String objname, Object obj) {
        request.getSession().getServletContext().setAttribute(objname, obj);
    }    
    
}
