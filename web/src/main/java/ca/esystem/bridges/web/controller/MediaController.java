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
public class MediaController extends AbstractController {

    @Resource(name="GlobalProperties")
    private Properties globalproperties;    
    
    @Resource(name = "MediaService")
    private MediaService mediaService;

    public MediaController(){
        
    }
        

    @RequestMapping(value = "/dialog/medialib", method = { RequestMethod.GET, RequestMethod.POST })
    public String openMediaDialog(@ModelAttribute("mediaQuery") Media mediaQuery, Model model, HttpServletRequest request) throws Exception {
    	LoggedInUser loggedinuser = getLoggedInUser();
    	mediaQuery.setCreated_by(loggedinuser.getUserid());
    	System.out.println("***userid="+loggedinuser.getUserid());
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
    
    
    @RequestMapping(value = "/media/create", method = RequestMethod.GET)
    public String openCreateMediaForm(@ModelAttribute("media") Media mediaQuery, Model model, HttpServletRequest request) throws Exception {
        //LoggedInUser user = getLoggedInUser();
        //System.out.println("id="+user.getId()+" email="+user.getEmail());
        Media media = new Media();
        media.setCreated_by(1);
        media.setOperate("add");
        model.addAttribute("media", media);
        return "/media/uploadform";
    }

    @RequestMapping(value = "/media/create", method = RequestMethod.POST)
    public String submitCreateMediaForm(@ModelAttribute("media") Media media, Model model, HttpServletRequest request) throws Exception {       
        loadProperties(globalproperties);
        
        LoggedInUser user = getLoggedInUser();
        System.out.println("id="+user.getUserid()+" email="+user.getEmail());        
        int user_id=user.getUserid();
        media.setCreated_by(user.getUserid());
        
        String now = CommonUtil.convertDatetoString(new Date(), "yyMMddhhmmss");
        CommonsMultipartFile picfile = media.getPicfile();
        System.out.println("file size="+picfile.getSize());
        
        //the size of picture is bigger than 1024 can be uploaded
        if(picfile.getSize()>1024){
            String filename=now+"_"+picfile.getOriginalFilename();
            File tempfile1 = new File(getRootDir()+getTempDir()+"/"+"1_"+filename);
            File tempfile2 = new File(getRootDir()+getTempDir()+"/"+filename);
            if(!tempfile1.exists()){
                tempfile1.mkdirs();
            }
            System.out.println("tempfile="+tempfile1);
            picfile.transferTo(tempfile1);
            
            //resize the picture if the picture's size is too big
            int picwidth = PicUtil.getImageWidth(tempfile1.toString());
            if(picwidth<=PicUtil.getMaxPicWidth()){
                tempfile1.renameTo(tempfile2);
            }else{
                PicUtil.resizePicture(tempfile1.toString(),tempfile2.toString(), PicUtil.getMaxPicWidth(), PicUtil.getMaxPicWidth());
            }
            
            //Print the water mark on the image
            File bigfile = new File(getRootDir()+getBigpicPath()+"/"+user_id+"/"+filename);
            PicUtil.printWatermark(tempfile2.toString(), bigfile.toString(), "3qnow.com");
            String bigpicurl = getBigpicPath()+"/"+user_id+"/"+filename;
            media.setBigpic(bigpicurl);
            
            //Generate a thumb for each big picture
            File thumbfile = new File(getRootDir()+getThumbPath()+"/"+user_id+"/"+filename);
            PicUtil.resizePicture(tempfile2.toString(),thumbfile.toString(), 300, 300);
            String thumburl = getThumbPath()+"/"+user_id+"/"+filename;
            media.setThumb(thumburl);
            
            //insert data into db
            String name = picfile.getOriginalFilename().substring(0, picfile.getOriginalFilename().lastIndexOf("."));
            String extname = picfile.getOriginalFilename().substring(picfile.getOriginalFilename().lastIndexOf(".")+1);
            if(media.getTitle()==null||media.getTitle().length()<1){
                media.setTitle(name);
            }
            media.setExt_name(extname);
            
            media.setCreated_at(new Date());
            mediaService.add(media);
            int pk = media.getMedia_id();
        
        }       
        String message = "图片已上传成功。 <a class=\"btn btn-sm btn-success\" href=\"create.html\">继续上传</a>";
        model.addAttribute("message", message);

        return "/dialog/success";
    }

    @RequestMapping(value = "/media/update", method = RequestMethod.GET)
    public String openUpdateMediaForm(@ModelAttribute("media") Media mediaQuery, Model model, HttpServletRequest request) throws Exception {
        Media media = (Media)mediaService.queryOne(mediaQuery);
        model.addAttribute("media", media);
        return "/media/updateform";
    }

    @RequestMapping(value = "/media/update", method = RequestMethod.POST)
    public String submitUpdateMediaForm(@ModelAttribute("media") Media media, Model model, HttpServletRequest request) throws Exception {
        int user_id=1;
        media.setModified_by(user_id);
        media.setModified_at(new Date());
        mediaService.update(media);
        String message = "图片关键字修改成功。";
        model.addAttribute("message", message);
        return "/media/success";
    }    
    
    @RequestMapping(value = "/media/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteMedia(@ModelAttribute("media") Media mediaQuery, Model model, HttpServletRequest request) throws Exception {
        loadProperties(globalproperties);
        System.out.println("delete image "+mediaQuery.getMedia_id());
        Media media = (Media)mediaService.queryOne(mediaQuery);
        File thumbfile = new File(getRootDir()+media.getThumb());
        File bigpic = new File(getRootDir()+media.getBigpic());
        thumbfile.renameTo(new File(thumbfile.toString()+".del"));
        bigpic.renameTo(new File(bigpic.toString()+".del"));
        
        mediaService.delete(mediaQuery);
        return "Delete Successfully";
    }
  
}
