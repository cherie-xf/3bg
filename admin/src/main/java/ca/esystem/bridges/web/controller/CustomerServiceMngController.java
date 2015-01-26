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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.esystem.bridges.dao.SysSequenceDao;
import ca.esystem.bridges.domain.Address;
import ca.esystem.bridges.domain.BloodType;
import ca.esystem.bridges.domain.Business_Profile;
import ca.esystem.bridges.domain.Contact;
import ca.esystem.bridges.domain.ContactClass;
import ca.esystem.bridges.domain.CustomerRepInfo;
import ca.esystem.bridges.domain.GenderType;
import ca.esystem.bridges.domain.Membership;
import ca.esystem.bridges.domain.NewUserAccount;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.UserAccountBasic;
import ca.esystem.bridges.domain.UserAccountOther;
import ca.esystem.bridges.domain.UserAccountStatusType;
import ca.esystem.bridges.domain.User_History;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.AddressService;
import ca.esystem.bridges.service.ContactService;
import ca.esystem.bridges.service.CustomerRepService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.util.CommonUtil;
import ca.esystem.framework.web.controller.AbstractController;

@Controller
public class CustomerServiceMngController extends AbstractController {

    private final Logger       logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private CustomerRepService customerRepService;

    @Resource(name = "UserAccountService")
    private UserAccountService userAccountService;

    @Resource(name = "MembershipService")
    private MembershipService  membershipService;
    
    @Resource(name = "ContactService")
    private ContactService     contactService;

    @Resource(name = "AddressService")
    private AddressService     addressService;
    
    @Resource(name = "UserService")
    private UserService        userService;

    @Resource
    private SysSequenceDao     sysSequenceDao;

    @ModelAttribute("accountStatusMap")
    public Map<Integer, String> accountStatusOptions() {

        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        statusMap.put(UserAccountStatusType.active.getCode(), UserAccountStatusType.active.getName());
        statusMap.put(UserAccountStatusType.inactive.getCode(), UserAccountStatusType.inactive.getName());
        statusMap.put(UserAccountStatusType.locked.getCode(), UserAccountStatusType.locked.getName());

        return statusMap;
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
        for (int year = cy - 100; year < cy; year++) {
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

    
    @RequestMapping(value = "/customerservice/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryTicketMngList(@ModelAttribute("customerQuery") CustomerRepInfo customerQuery, Model model, HttpServletRequest request) throws Exception {

        logger.debug("Received request at /customerseive/manage/");

        int rownum = customerRepService.queryCount(customerQuery);
        customerQuery.getPagination().setRowCount(rownum);
        customerQuery.getPagination().setPageSize(20);
        int pagecount = customerQuery.getPagination().getPageCount();
        int currentpage = customerQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        customerQuery.setOrderByClause("a.created_at desc");
        List customermnglist = customerRepService.queryList(customerQuery);
        model.addAttribute("customermnglist", customermnglist);

        return "/customerservice/manage";
    }

    @RequestMapping(value = "/customerservice/create", method = RequestMethod.GET)
    public String openCreateMemberForm(Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int loginUserId = user.getUserid();

        CustomerRepInfo customerRepInfo = new CustomerRepInfo();
        model.addAttribute("customerRepInfo", customerRepInfo);
        return "/customerservice/form";
    }

    @RequestMapping(value = "/customerservice/create", method = RequestMethod.POST)
    public String submitCreateCategoryForm(@ModelAttribute("customerRepInfo") CustomerRepInfo customerRepInfo, Model model, HttpServletRequest request)
            throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            customerRepInfo.setFeedbackMessage("No permission.");
            return null;
        }

        Membership membership = new Membership();
        NewUserAccount account = new NewUserAccount();
        account.setPhone(customerRepInfo.getPhone());
        account.setEmail(customerRepInfo.getEmail());
        account.setPassword(customerRepInfo.getPassword());
        account.setFirstName(customerRepInfo.getFirstName());
        account.setLastName(customerRepInfo.getLastName());
        account.setPassword(customerRepInfo.getPassword());
        //set as mis user
        account.setUser_type(1);
        

        membership.setType_code(customerRepInfo.getType_code());
        membership.setCredit(customerRepInfo.getCredit());
        membership.setValid_from(customerRepInfo.getValid_from());
        membership.setValid_to(customerRepInfo.getValid_to());
        membership.setMemberIDManualInput(customerRepInfo.isMemberIDManualInput());
        membership.setMember_id(customerRepInfo.getMember_id());
        try {
            int user_id = userAccountService.addUserAccount(user.getId(), account);
            //set the default value for customerservice to "1"
            User updateUser = new User();
            updateUser.setId(user_id);
            updateUser = (User) userService.queryOne(updateUser);
            updateUser.setStatus(1);
            userService.update(updateUser);
            
            
            membership.setUser_id(user_id);
            if (!membership.isMemberIDManualInput()) {
                String type_code = membership.getType_code();
                String defaultId = generateDefaultMembershipId(type_code);
                membership.setMember_id(defaultId);
            }

            membership.setCreated_at(new Date());
            membership.setCreated_by(user.getUserid());
            membershipService.insertMemberAndBusinessProfile(membership);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            account.setFeedbackMessage("System error.");
            return null;
        }

        String message = "创建客服成功!";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/customerservice/delete/{member_id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable String member_id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        Membership membership = new Membership();
        membership.setMember_id(member_id);
        membership.setModified_at(new Date());
        membership.setModified_by(user_id);
        membership.setIs_deleted(true);

        membershipService.deleteMemberAndBusinessProfile(membership);
        String message = "删除Membership成功";
        model.addAttribute("message", message);
        return "success";
        // return "redirect:/customerservice/manage.html";
    }

    @RequestMapping(value = "/customerservice/detail", method = { RequestMethod.GET })
    public String showUserAccountBasic(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {

        model.addAttribute("id", id);
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        UserAccountBasic account = userAccountService.getUserAccountBasic(user.getId(), id);
        // diff the operate from "detail" and "membership"
       // account.setOperate("detail");
        model.addAttribute("account", account);

        return "/customerservice/basic";
    }

    
    @RequestMapping(value = "/customerservice/basicUpdate", method = { RequestMethod.POST })
    public String updateUserAccountBasic(@ModelAttribute("account") UserAccountBasic account, @RequestParam("id") int id, Model model,
            HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        userAccountService.updateUserAccountBasic(user.getId(), account);

        String message = "更新基本信息成功!";
        account.setFeedbackMessage(message);
        model.addAttribute("account", account);

        return "/customerservice/basic";
    }

    private String generateDefaultMembershipId(String type_code) {

        String seqName = type_code.substring(0, 1);
        int seqValue = sysSequenceDao.queryNextVal(seqName);
        String memberNumber = CommonUtil.addZeroBeforeNumber(7, "" + seqValue);
        String memberid = seqName + memberNumber;

        return memberid;
    }
    
    @RequestMapping(value = "/customerservice/contact", method = { RequestMethod.GET })
    public String showContact(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {

        model.addAttribute("id", id);

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

        return "/customerservice/contact";
    }
    
    
    @RequestMapping(value = "/customerservice/phone", method = { RequestMethod.GET })
    public String showCreatePhoneNumber(@RequestParam("userid") Integer userId, @RequestParam(value = "id", required = false) Integer id, 
            Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        
        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
            contact = (Contact)contactService.queryOne(contact);
            if (contact == null) {
                return "systemerror";
            }
            contact.setOperate("update");
        }
        else {
            contact.setOperate("add");
            contact.setUser_id(userId);
        }
        
        model.addAttribute("contact", contact);
        
        return "/customerservice/phone";
    }

    @RequestMapping(value = "/customerservice/phone", method = { RequestMethod.POST })
    public String createPhoneNumber(@ModelAttribute("contact") Contact contact, Model model,
            HttpServletRequest request) throws Exception {
        
        // get user info
        LoggedInUser user = getLoggedInUser();
        System.out.println(contact.getOperate());
        System.out.println(contact.getId());
        if ("update".equalsIgnoreCase(contact.getOperate()) && contact.getId() > 0) {
            contact.setModified_by(user.getId());
            contact.setModified_at(new Date());
            contactService.update(contact);
        }
        else {
            contact.setCreated_by(user.getId());
            contact.setCreated_at(new Date());
            contactService.add(contact);
        }
        
        return "success";
    }
    
    
    @RequestMapping(value = "/customerservice/email", method = { RequestMethod.GET })
    public String showCeateEmailAddress(@RequestParam("userid") Integer userId, @RequestParam(value = "id", required = false) Integer id, 
            Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        
        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
            contact = (Contact)contactService.queryOne(contact);
            if (contact == null) {
                return "systemerror";
            }
            contact.setOperate("update");
        }
        else {
            contact.setOperate("add");
            contact.setUser_id(userId);
        }
        
        //
        model.addAttribute("contact", contact);
        return "/customerservice/email";
    }

    @RequestMapping(value = "/customerservice/email", method = { RequestMethod.POST })
    public String createEmailAddress(@ModelAttribute("contact") Contact contact, Model model,
            HttpServletRequest request) throws Exception {
        
        // get user info
        LoggedInUser user = getLoggedInUser();
        
        if ("update".equalsIgnoreCase(contact.getOperate()) && contact.getId() > 0) {
            contact.setModified_by(user.getId());
            contact.setModified_at(new Date());
            contactService.update(contact);
        }
        else {
            contact.setCreated_by(user.getId());
            contact.setCreated_at(new Date());
            contactService.add(contact);
        }
        
        return "success";
    }
    
    
    @RequestMapping(value = "/customerservice/social", method = { RequestMethod.GET })
    public String showCreateSocialAccount(@RequestParam("userid") Integer userId, @RequestParam(value = "id", required = false) Integer id, 
            Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        
        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
            contact = (Contact)contactService.queryOne(contact);
            if (contact == null) {
                return "systemerror";
            }
            contact.setOperate("update");
        }
        else {
            contact.setOperate("add");
            contact.setUser_id(userId);
        }
        
        model.addAttribute("contact", contact);
        return "/customerservice/social";
    }

    @RequestMapping(value = "/customerservice/social", method = { RequestMethod.POST })
    public String createSocialAccount(@ModelAttribute("contact") Contact contact, Model model,
            HttpServletRequest request) throws Exception {
        
        // get user info
        LoggedInUser user = getLoggedInUser();
        
        if ("update".equalsIgnoreCase(contact.getOperate()) && contact.getId() > 0) {
            contact.setModified_by(user.getId());
            contact.setModified_at(new Date());
            contactService.update(contact);
        }
        else {
            contact.setCreated_by(user.getId());
            contact.setCreated_at(new Date());
            contactService.add(contact);
        }
        
        return "success";
    }
    
    @RequestMapping(value = "/customerservice/address", method = { RequestMethod.GET })
    public String showCreateMailingAddress(@RequestParam("userid") Integer userId, @RequestParam(value = "address_id", required = false) Integer id, 
            Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        
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
            address.setUser_id(userId);
        }
        
        //
        model.addAttribute("address", address);
        return "/customerservice/address";
    }

    @RequestMapping(value = "/customerservice/address", method = { RequestMethod.POST })
    public String createMailingAddress(@ModelAttribute("address") Address address, Model model,
            HttpServletRequest request) throws Exception {
        
        // get user info
        LoggedInUser user = getLoggedInUser();
        
        if ("update".equalsIgnoreCase(address.getOperate()) && address.getAddress_id() > 0) {
            address.setModified_by(user.getId());
            address.setModified_at(new Date());
            addressService.update(address);
        }
        else {
            address.setCreated_by(user.getId());
            address.setCreated_at(new Date());
            addressService.add(address);
        }
        
        return "success";
    }


    @RequestMapping(value = "/customerservice/deletecontact/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable Integer id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int userId;

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
           userId = querycontact.getUser_id();
           
        }
        catch (Exception ex) {
            return "systemerror";            
        }
        
        //return "success";
        return "redirect:/customerservice/contact.html?id=" +userId;
        
    }
    
    
    @RequestMapping(value = "/customerservice/deleteaddress/{id}", method = RequestMethod.GET)
    public String deleteAddress(@PathVariable Integer id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int userId;

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
            userId = queryaddress.getUser_id();
        }
        catch (Exception ex) {
            return "systemerror";            
        }
        
       // return "success";
        return "redirect:/customerservice/contact.html?id=" +userId;
    }
    
    @RequestMapping(value = "/customerservice/userhistory", method = { RequestMethod.GET })
    public String showServiceLog(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
     // get user info
        LoggedInUser user = getLoggedInUser();
        String username = user.getFirstName() + user.getLastName();
        

        // fetch the ticket_reply list information
        User_History userhistoryQuery = new User_History();
        userhistoryQuery.setUser_id(id);
        List<User_History> userHistorylist = userAccountService.userHistoryList(userhistoryQuery);

        // add the attributes
        model.addAttribute("username", username);
        model.addAttribute("userHistorylist", userHistorylist);
        model.addAttribute("userhistoryQuery",userhistoryQuery);
      
        // return to the view(TicketOperate.jsp)
        return "/customerservice/userhistory";
    }
    @RequestMapping(value = "/customerservice/createHistory", method = RequestMethod.POST)
    public String createUserHistoryForm(@ModelAttribute("userhistoryQuery") User_History userhistoryQuery, @RequestParam("id") int user_id,
            Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        userhistoryQuery.setCreated_by(user.getUserid());
        userhistoryQuery.setCreater_name(user.getFirstName() + user.getLastName());
        userhistoryQuery.setCreated_at(new Date());;
        userhistoryQuery.setUser_id(user_id);

        userAccountService.userHistoryAdd(userhistoryQuery);

        String message = "新增日志成功!";
        userhistoryQuery.setFeedbackMessage(message);
        model.addAttribute("userhistoryQuery", userhistoryQuery);
        model.addAttribute("id", user_id);
        return "redirect:userhistory.html?id="+user_id;
    }
    

    @RequestMapping(value = "/customerservice/other", method = { RequestMethod.GET })
    public String showUserAccountOther(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {

        model.addAttribute("id", id);
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        UserAccountOther account = userAccountService.getUserAccountOther(user.getId(), id);
        account.setOperate("update");
        model.addAttribute("account", account);

        return "/customerservice/other";
    }

    @RequestMapping(value = "/customerservice/other", method = { RequestMethod.POST })
    public String updateUserAccountOther(@ModelAttribute("account") UserAccountOther account, @RequestParam("id") int id, Model model,
            HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        userAccountService.updateUserAccountOther(user.getId(), account);
        String message = "更新用户信息成功!";
        account.setFeedbackMessage(message);
        model.addAttribute("account", account);
       // return "redirect:/account/other.html?id=" + id+"&message="+message;
        return "/customerservice/other";
        
    }
    
}
