package ca.esystem.bridges.web.controller;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.service.UserService;
import ca.esystem.bridges.service.RecoveryTokenService;
import ca.esystem.bridges.sysio.EmailSender;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.RecoveryToken;

@Controller
public class SignupConroller {
    @Resource(name = "UserService")
    private UserService userService;
    
    @Resource(name = "EmailSender")
    private EmailSender emailSender;
    
    @Resource(name = "RecoveryTokenService")
    private RecoveryTokenService recoveryTokenService;
    
    @RequestMapping(value="/signup/validateemail",method=RequestMethod.POST)
    public @ResponseBody String validateemail(HttpServletRequest request){
        String email = request.getParameter("user.email");
        String str="";
        if(email==null){
            str ="{\"valid\":false, \"message\":\"Your email is required\"}";
        }else{ 
            email = email.trim();
            if(!email.contains("@")){
                str ="{\"valid\":false, \"message\":\"Your email format is not correct\"}";
            }else{
                User user = userService.queryUserByEmail(email);
                if(user==null ){
                  str ="{\"valid\":true}";
                }else if(user.getStatus()<1){
                  str ="{\"valid\":true}";
                }
                else{
                  str ="{\"valid\":false, \"message\":\"Your email has been registered\"}";
                }
            }
        }
        return str;
    }
    
    @RequestMapping(value="/signup/activeaccount",method=RequestMethod.GET)
    public String activeAccount(@ModelAttribute("recoverytoken") RecoveryToken recoverytokenQuery,Model model, HttpServletRequest request){
    	String message="";
    	RecoveryToken recoveryToken = recoveryTokenService.queryRecoveryToken(recoverytokenQuery);
    	if(recoveryToken!=null){
    		if((new Date()).getTime()-recoveryToken.getCreated_at().getTime()<1000*60*60*24&&recoveryToken.getToken().equals(recoverytokenQuery.getToken())){
    			//update account status 
    			User user = new User();
    			user.setId(recoveryToken.getUser_id());
    			user.setStatus(1);
    			userService.updateUserStatus(user);
    			message = "账号激活成功。";
    		}else{
    			message="对不起，链接不正确或者链接有效期已超过24小时。";
    		}
    	}else{
    		message="对不起，链接不正确。";
    	}
    	model.addAttribute("message",message);
    	return "success";
    }
    
    
    
    @RequestMapping(value="/signup/sendemail",method=RequestMethod.GET)
    public @ResponseBody String sendEmail(HttpServletRequest request){
        emailSender.sendResetPassLetter("larrylin17@gmail.com", "Mr. Lin", "Conguratulations", "You are such a good guy.");
        return "email sent";
    }
    
    
}
