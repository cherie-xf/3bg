package ca.esystem.bridges.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.esystem.bridges.domain.Signup;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.web.controller.AbstractController;

@Controller
public class SignupLoginContoller extends AbstractController {

    @Resource(name = "UserService")
    private UserService userService;

    @Resource(name = "UserAccountService")
    private UserAccountService userAccountService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(@ModelAttribute("signupquery") Signup signup, Model model, HttpServletRequest request) {
        return "index";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignup(@ModelAttribute("signupquery") Signup signup, Model model, HttpServletRequest request) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("signupquery") Signup signup, Model model, HttpServletRequest request) {
        userAccountService.addUser(signup);
        return "signup";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("loginquery") Signup signup, Model model, HttpServletRequest request) {
        
        // Should be null
        LoggedInUser user = getLoggedInUser();
        
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(@ModelAttribute("logoutquery") Signup signup, Model model, HttpServletRequest request) {
        
        // Should not be nulls
        LoggedInUser user = getLoggedInUser();
        
        return "logout";
    }    
}
