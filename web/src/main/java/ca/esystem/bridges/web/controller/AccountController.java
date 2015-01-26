package ca.esystem.bridges.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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

import ca.esystem.bridges.domain.Address;
import ca.esystem.bridges.domain.BloodType;
import ca.esystem.bridges.domain.Contact;
import ca.esystem.bridges.domain.ContactClass;
import ca.esystem.bridges.domain.GenderType;
import ca.esystem.bridges.domain.NewUserAccount;
import ca.esystem.bridges.domain.ServiceOrderFinishedPeriodEnum;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.UserAccountBasic;
import ca.esystem.bridges.domain.UserAccountOther;
import ca.esystem.bridges.domain.UserAccountStatusType;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.AddressService;
import ca.esystem.bridges.service.ContactService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.ServiceOrderMngService;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * Web Account Controller
 * 
 * @author cherie
 *
 */
@Controller
public class AccountController extends AbstractController {

    private final Logger           logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "UserAccountService")
    private UserAccountService     userAccountService;

    @Resource(name = "ContactService")
    private ContactService         contactService;

    @Resource(name = "AddressService")
    private AddressService         addressService;

    @Resource(name = "MembershipService")
    private MembershipService      membershipService;

    @Resource(name = "TicketService")
    private TicketService          ticketService;

    @Resource(name = "ServiceOrderMngService")
    private ServiceOrderMngService serviceOrderMngService;
    
    @Resource(name = "UserService")
    private UserService        userService;
    
    private BCryptPasswordEncoder encoder;
    
    public AccountController(){
        encoder = new BCryptPasswordEncoder();
    }

    @ModelAttribute("accountStatusMap")
    public Map<Integer, String> accountStatusOptions() {

        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        statusMap.put(UserAccountStatusType.active.getCode(), UserAccountStatusType.active.getName());
        statusMap.put(UserAccountStatusType.inactive.getCode(), UserAccountStatusType.inactive.getName());
        statusMap.put(UserAccountStatusType.locked.getCode(), UserAccountStatusType.locked.getName());

        return statusMap;
    }

    @ModelAttribute("serviceOrderFinishedPeriodMap")
    public SortedMap<Integer, String> serviceOrderFinishedPeriodMap() {

        SortedMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(ServiceOrderFinishedPeriodEnum.threeYear.getCode(), ServiceOrderFinishedPeriodEnum.threeYear.getName());
        map.put(ServiceOrderFinishedPeriodEnum.oneYear.getCode(), ServiceOrderFinishedPeriodEnum.oneYear.getName());
        map.put(ServiceOrderFinishedPeriodEnum.threeMonth.getCode(), ServiceOrderFinishedPeriodEnum.threeMonth.getName());

        return map;
    }

    @ModelAttribute("genderMap")
    public Map<String, String> genderOptions() {

        Map<String, String> genderMap = new HashMap<String, String>();
        genderMap.put(GenderType.male.getCode(), GenderType.male.getName());
        genderMap.put(GenderType.female.getCode(), GenderType.female.getName());

        return genderMap;
    }

    @ModelAttribute("bloodTypeMap")
    public Map<String, String> bloodTypeOptions() {

        Map<String, String> bloodTypeMap = new HashMap<String, String>();
        bloodTypeMap.put(BloodType.A.getCode(), BloodType.A.getName());
        bloodTypeMap.put(BloodType.B.getCode(), BloodType.B.getName());
        bloodTypeMap.put(BloodType.AB.getCode(), BloodType.AB.getName());
        bloodTypeMap.put(BloodType.O.getCode(), BloodType.O.getName());

        return bloodTypeMap;
    }

    @ModelAttribute("yearMap")
    public SortedMap<Integer, String> yearOptions() {

        SortedMap<Integer, String> yearMap = new TreeMap<Integer, String>();
        int cy = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = cy; year > cy - 100; year--) {
            yearMap.put(year, String.format("%d年", year));
        }

        return yearMap;
    }

    @ModelAttribute("monthMap")
    public SortedMap<Integer, String> monthOptions() {

        SortedMap<Integer, String> monthMap = new TreeMap<Integer, String>();
        for (int month = 1; month <= 12; month++) {
            monthMap.put(month, String.format("%d月", month));
        }

        return monthMap;
    }

    @ModelAttribute("dayOfMonthMap")
    public SortedMap<Integer, String> dayOfMonthOptions() {

        SortedMap<Integer, String> dayOfMonthMap = new TreeMap<Integer, String>();
        for (int day = 1; day <= 31; day++) {
            dayOfMonthMap.put(day, String.format("%d日 ", day));
        }

        return dayOfMonthMap;
    }

    @RequestMapping(value = "/account/basic", method = { RequestMethod.GET })
    public String showUserAccountBasic(Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        UserAccountBasic account = userAccountService.getUserAccountBasic(user.getId(), user.getId());

        // account.setOperate("update");
        model.addAttribute("account", account);

        return "/account/basic";
    }

    @RequestMapping(value = "/account/basicUpdate", method = { RequestMethod.POST })
    public String updateUserAccountBasic(@ModelAttribute("account") UserAccountBasic account, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        account.setId(user.getId());
        userAccountService.updateUserAccountBasic(user.getId(), account);

        String message = "更新基本信息成功!";

        account.setFeedbackMessage(message);
        model.addAttribute("account", account);

        return "/account/basic";
    }

    @RequestMapping(value = "/account/contact", method = { RequestMethod.GET })
    public String showContact(Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }
        int id = user.getUserid();
        // phone number
        List phoneContactList = contactService.queryContactListByUserAndClass(id, ContactClass.PN);
        model.addAttribute("phoneContactList", phoneContactList);
        // Email
        List emailContactList = contactService.queryContactListByUserAndClass(id, ContactClass.EM);
        model.addAttribute("emailContactList", emailContactList);
        // Social accout list
        List socialContactList = contactService.queryContactListByUserAndClass(id, ContactClass.SC);
        model.addAttribute("socialContactList", socialContactList);
        // Address
        List addressList = addressService.queryAddressList(id);
        model.addAttribute("addressList", addressList);

        return "/account/contact";
    }

    @RequestMapping(value = "/account/phone", method = { RequestMethod.GET })
    public String showCreatePhoneNumber(@RequestParam(value = "id", required = false) Integer id, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        if (user == null) {
            return null;
        }

        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
            contact = (Contact) contactService.queryOne(contact);
            if (contact == null) {
                return "systemerror";
            }
            contact.setOperate("update");
        } else {
            contact.setOperate("add");
            contact.setUser_id(user.getUserid());
        }

        model.addAttribute("contact", contact);

        return "/account/phone";
    }

    @RequestMapping(value = "/account/phone", method = { RequestMethod.POST })
    public String createPhoneNumber(@ModelAttribute("contact") Contact contact, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();
        String message ="";
        /*
         * System.out.println(contact.getOperate()); System.out.println(contact.getId());
         */
        if ("update".equalsIgnoreCase(contact.getOperate()) && contact.getId() > 0) {
            contact.setModified_by(user.getId());
            contact.setModified_at(new Date());
            contactService.update(contact);
            message = "更新成功!";
        } else {
            contact.setCreated_by(user.getId());
            contact.setCreated_at(new Date());
            contactService.add(contact);
            message = "增加成功!";
        }

        model.addAttribute("message", message);
        return "/dialog/success";
    }

    @RequestMapping(value = "/account/email", method = { RequestMethod.GET })
    public String showCeateEmailAddress(@RequestParam(value = "id", required = false) Integer id, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        if (user == null) {
            return null;
        }

        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
            contact = (Contact) contactService.queryOne(contact);
            if (contact == null) {
                return "systemerror";
            }
            contact.setOperate("update");
        } else {
            contact.setOperate("add");
            contact.setUser_id(user.getUserid());
        }

        //
        model.addAttribute("contact", contact);
        return "/account/email";
    }

    @RequestMapping(value = "/account/email", method = { RequestMethod.POST })
    public String createEmailAddress(@ModelAttribute("contact") Contact contact, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();
        String message ="";

        if ("update".equalsIgnoreCase(contact.getOperate()) && contact.getId() > 0) {
            contact.setModified_by(user.getId());
            contact.setModified_at(new Date());
            contactService.update(contact);
            message = "更新成功!";
        } else {
            contact.setCreated_by(user.getId());
            contact.setCreated_at(new Date());
            contactService.add(contact);
            message = "增加成功!";
        }

        model.addAttribute("message", message);
        return "/dialog/success";
    }

    @RequestMapping(value = "/account/social", method = { RequestMethod.GET })
    public String showCreateSocialAccount(@RequestParam(value = "id", required = false) Integer id, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        if (user == null) {
            return null;
        }

        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
            contact = (Contact) contactService.queryOne(contact);
            if (contact == null) {
                return "systemerror";
            }
            contact.setOperate("update");

        } else {
            contact.setOperate("add");
            contact.setUser_id(user.getUserid());

        }

        model.addAttribute("contact", contact);
        return "/account/social";
    }

    @RequestMapping(value = "/account/social", method = { RequestMethod.POST })
    public String createSocialAccount(@ModelAttribute("contact") Contact contact, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();
        String message ="";

        if ("update".equalsIgnoreCase(contact.getOperate()) && contact.getId() > 0) {
            contact.setModified_by(user.getId());
            contact.setModified_at(new Date());
            contactService.update(contact);
            message = "更新成功!";
        } else {
            contact.setCreated_by(user.getId());
            contact.setCreated_at(new Date());
            contactService.add(contact);
            message = "增加成功!";
        }

        model.addAttribute("message", message);
        return "/dialog/success";
    }
    
    @RequestMapping(value = "/account/address", method = { RequestMethod.GET })
    public String showCreateMailingAddress( @RequestParam(value = "address_id", required = false) Integer id, 
            Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        if (user == null) {
            return null;
        }
        
        Address address = new Address();
        if (id != null) {
            address.setAddress_id(id);
            address = (Address)addressService.queryOne(address);
            if (address == null) {
                return "systemerror";
            }
            address.setOperate("update");
        }
        else {
            address.setOperate("add");
            address.setUser_id(user.getUserid());
        }
        
        model.addAttribute("address", address);
        return "/account/address";
    }

    @RequestMapping(value = "/account/address", method = { RequestMethod.POST })
    public String createMailingAddress(@ModelAttribute("address") Address address, Model model,
            HttpServletRequest request) throws Exception {
        
        // get user info
        LoggedInUser user = getLoggedInUser();
        String message ="";
        
        if ("update".equalsIgnoreCase(address.getOperate()) && address.getAddress_id() > 0) {
            address.setModified_by(user.getId());
            address.setModified_at(new Date());
            addressService.update(address);
            message = "更新成功!";
        }
        else {
            address.setCreated_by(user.getId());
            address.setCreated_at(new Date());
            addressService.add(address);
            message = "增加成功!";
           
        }
        model.addAttribute("message", message);
        return "/dialog/success";
    }
    
    @RequestMapping(value = "/account/deletecontact/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable Integer id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
       // int userId;

        // Permission check
        if (user == null) {
            model.addAttribute("message", "No permission.");
            return "systemerror";
        }

        try {
            Contact contact = new Contact();
            contact.setId(id);
            contactService.delete(contact);
           Contact querycontact = (Contact)contactService.queryOne(contact);
         //  userId = querycontact.getUser_id();
           
        }
        catch (Exception ex) {
            return "systemerror";            
        }
        
        model.addAttribute("message", "删除成功！");
        return "/dialog/success";
       // return "redirect:/account/contact.html?id=" +userId;
        
    }
    
    
    @RequestMapping(value = "/account/deleteaddress/{id}", method = RequestMethod.GET)
    public String deleteAddress(@PathVariable Integer id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        //int userId;

        // Permission check
        if (user == null) {
            model.addAttribute("message", "No permission.");
            return "systemerror";
        }

        try {
            Address address = new Address();
            address.setAddress_id(id);
            addressService.delete(address);
            Address queryaddress = (Address)addressService.queryOne(address);
          //  userId = queryaddress.getUser_id();
        }
        catch (Exception ex) {
            return "systemerror";            
        }
        model.addAttribute("message", "删除成功！");
        return "/dialog/success";
       // return "redirect:/account/contact.html?id=" +userId;
    }
    
    @RequestMapping(value = "/account/other", method = { RequestMethod.GET })
    public String showUserAccountOther( Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        UserAccountOther account = userAccountService.getUserAccountOther(user.getId(), user.getId());
        account.setOperate("update");
        model.addAttribute("account", account);

        return "/account/other";
    }

    @RequestMapping(value = "/account/other", method = { RequestMethod.POST })
    public String updateUserAccountOther(@ModelAttribute("account") UserAccountOther account,  Model model,
            HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        userAccountService.updateUserAccountOther(user.getUserid(), account);
        String message = "更新用户信息成功!";
        account.setFeedbackMessage(message);
        model.addAttribute("account", account);
        return "/account/other";
        
    }
    
    @RequestMapping(value = "/account/password", method = { RequestMethod.GET })
    public String showUserAccountPassword(Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        /*User queryuser = new User();
        queryuser.setId(user.getUserid());
        User updateUser = (User) userService.queryOne(queryuser);
        
        encoder.encode(updateUser.getPassword());*/
        User updateUser = new User();

        model.addAttribute("updateUser", updateUser);

        return "/account/password";
    }

    @RequestMapping(value = "/account/password", method = { RequestMethod.POST })
    public String updateUserAccountPassword(@ModelAttribute("updateUser") User updateUser, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        User queryuser = new User();
        queryuser.setId(user.getUserid());
        User old = (User) userService.queryOne(queryuser);
                
                
        updateUser.setId(user.getUserid());
        String password = updateUser.getPassword();
        String password_confirmation = updateUser.getPassword_confirmation();
        String old_password = updateUser.getOperate();
        String encodePassword = encoder.encode(old_password);
        System.out.println("back="+old.getPassword()+" front="+encodePassword);
        
        String message ="";
        String messageCode="";
        System.out.println("***************"+encoder.matches(old_password, old.getPassword()));
        if(! encoder.matches(old_password, old.getPassword()))
        {
             message = "原密码输入不正确!";
             messageCode = "error";
        }
        else if( !password.equals(password_confirmation)){
             message = "两次输入密码不一致!";
             messageCode = "error";
        }else{
            String encodepassword = encoder.encode(password);
            updateUser.setPassword(encodepassword);
            userService.changePassword(updateUser);
             message = "重置密码成功!";
           
        }
        
        updateUser.setFeedbackMessage(message);
        updateUser.setPassword(password);
        model.addAttribute("updateUser", updateUser);
        model.addAttribute("messageCode", messageCode);

        return "/account/password";
    }

}
