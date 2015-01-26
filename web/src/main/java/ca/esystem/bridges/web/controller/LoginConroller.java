package ca.esystem.bridges.web.controller;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.UserService;
import ca.esystem.bridges.service.RecoveryTokenService;
import ca.esystem.bridges.sysio.EmailSender;
import ca.esystem.bridges.domain.Signup;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.RecoveryToken;
import ca.esystem.framework.util.CommonUtil;
import ca.esystem.framework.web.controller.AbstractController;

@Controller
public class LoginConroller extends AbstractController {
    @Resource(name = "UserService")
    private UserService          userService;

    @Resource(name = "EmailSender")
    private EmailSender          emailSender;

    @Resource(name = "RecoveryTokenService")
    private RecoveryTokenService recoveryTokenService;

    @Resource(name = "GlobalProperties")
    private Properties           globalproperties;
    
	private BCryptPasswordEncoder encoder;
	
	public LoginConroller(){
		encoder = new BCryptPasswordEncoder();
	}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
        return "/signin/login";
    }

    @RequestMapping(value = "/signin/forgetpassword", method = RequestMethod.GET)
    public String forgetPassword(@ModelAttribute("userquery") User userquery, Model model, HttpServletRequest request) {
        return "/signin/forgetpassword";
    }

    @RequestMapping(value = "/signin/sendresetlink", method = RequestMethod.POST)
    public String sendResetPasswordLink(@ModelAttribute("userquery") User userquery, Model model, HttpServletRequest request) {
        String message = "";
        User user = userService.queryUserByEmail(userquery.getEmail());
        if (user == null) {
            message = "您输入的邮件地址不存在";
        } else {
            if (user.getStatus() == 0) {
                message = "对不起，您的帐号未激活";
            } else if (user.getStatus() == 2) {
                message = "对不起，您的帐号已被锁";
            } else if (user.getStatus() == 1) {
                String resetpasswordlink = globalproperties.getProperty("DomainName") + "/web/signin/resetpassword.html?" + "id=" + user.getId() + "&token="
                        + user.getPassword();
                emailSender.sendResetPassLetter(user.getEmail(), user.getFirstName(), "重置密码链接", resetpasswordlink);

                message = "重置密码链接已经发送到" + user.getEmail() + "中";
            }
        }
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/signin/resetpassword", method = RequestMethod.GET)
    public String resetpassword(@RequestParam("id") int id, @RequestParam("token") String token, Model model, HttpServletRequest request)throws Exception {
        String returnurl = "";
        User userquery = new User();
        userquery.setId(id);
        User user = (User) userService.queryOne(userquery);
        if (user == null) {
            throw new Exception("无效链接");
        } else {
            if (token.equals(user.getPassword())) {
                request.getSession().setAttribute("userid",new Integer(id));
                User resetForm = new User();
                model.addAttribute("resetForm", resetForm);
                returnurl = "/signin/resetpassword";
            } else {
            	throw new Exception("无效链接");
            }
        }
        return returnurl;
    }
    
    @RequestMapping(value = "/signin/resetpassword", method = RequestMethod.POST)
    public String resetpassword(@ModelAttribute("resetForm") User resetForm, Model model, HttpServletRequest request)throws Exception {
        String returnurl = "success";
        User userquery = new User();
        int userid = (Integer)request.getSession().getAttribute("userid");
        userquery.setId(userid);
        String password = resetForm.getPassword();
        String password_confirmation = resetForm.getPassword_confirmation();
        if(password.equals(password_confirmation)){
        	password = encoder.encode(password);
        	userquery.setId(userid);
        	userquery.setPassword(password);
            userService.changePassword(userquery);
            String message = "重置密码成功";
            model.addAttribute("message", message);
        }else{
        	throw new Exception("新密码与重复密码不一致");
        }
        return returnurl;
    }
}
