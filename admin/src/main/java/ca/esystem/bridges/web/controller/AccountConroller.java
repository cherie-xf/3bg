package ca.esystem.bridges.web.controller;

import java.util.ArrayList;
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

import ca.esystem.bridges.domain.Address;
import ca.esystem.bridges.domain.BloodType;
import ca.esystem.bridges.domain.Contact;
import ca.esystem.bridges.domain.ContactClass;
import ca.esystem.bridges.domain.GenderType;
import ca.esystem.bridges.domain.Membership;
import ca.esystem.bridges.domain.NewUserAccount;
import ca.esystem.bridges.domain.ServiceOrderFinishedPeriodEnum;
import ca.esystem.bridges.domain.Service_Order;
import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Ticket_Reply;
import ca.esystem.bridges.domain.UserAccountBasic;
import ca.esystem.bridges.domain.UserAccountOther;
import ca.esystem.bridges.domain.UserAccountSearch;
import ca.esystem.bridges.domain.UserAccountStatusType;
import ca.esystem.bridges.domain.UserOrderQueryType;
import ca.esystem.bridges.domain.User_History;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.AddressService;
import ca.esystem.bridges.service.ContactService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.ServiceOrderMngService;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.framework.web.controller.AbstractController;

@Controller
@RequestMapping(value = "/account")
public class AccountConroller extends AbstractController {
    private final Logger       logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "UserAccountService")
    private UserAccountService userAccountService;

    @Resource(name = "ContactService")
    private ContactService     contactService;

    @Resource(name = "AddressService")
    private AddressService     addressService;
    
    @Resource(name = "MembershipService")
    private MembershipService     membershipService;
    
    @Resource(name = "TicketService")
    private TicketService ticketService;
    
    @Resource(name = "ServiceOrderMngService")
    private ServiceOrderMngService     serviceOrderMngService;

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

    /**
     * request the searching  user list page
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = { RequestMethod.GET })
    public String showSearchUserAccount(Model model, HttpServletRequest request) throws Exception {

        logger.debug("Search user account");

        // get user info
        LoggedInUser user = getLoggedInUser();

        UserAccountSearch accountSearch = new UserAccountSearch();
        accountSearch.setOperate("search");
        model.addAttribute("accountQuery", accountSearch);

        int rownum = userAccountService.queryCount(accountSearch);
        accountSearch.getPagination().setRowCount(rownum);
        accountSearch.getPagination().setPageSize(20);
        int pagecount = accountSearch.getPagination().getPageCount();
        int currentpage = accountSearch.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        accountSearch.setOrderByClause("createdAt desc");
        //only display web user
        accountSearch.setUser_type(0);
        List accountList = userAccountService.queryList(accountSearch);
        model.addAttribute("accountList", accountList);

        return "/account/search";
    }

    /**
     * search the specific user list
     * @param accountSearch
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = { RequestMethod.POST })
    public String searchUserAccount(@ModelAttribute("accountQuery") UserAccountSearch accountSearch, Model model, HttpServletRequest request) throws Exception {

        logger.debug("Search user account");

        // get user info
        LoggedInUser user = getLoggedInUser();

        //
        int rownum = userAccountService.queryCount(accountSearch);
        accountSearch.getPagination().setRowCount(rownum);
        accountSearch.getPagination().setPageSize(20);
        int pagecount = accountSearch.getPagination().getPageCount();
        int currentpage = accountSearch.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        accountSearch.setOrderByClause("createdAt desc");
        List accountList = userAccountService.queryList(accountSearch);
        model.addAttribute("accountList", accountList);

        return "/account/search";
    }

    /**
     * request the create userporfile page
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = { RequestMethod.GET })
    public String showCreateUserAccount(Model model, HttpServletRequest request) throws Exception {

        logger.debug("Create user account");

        // get user info
        LoggedInUser user = getLoggedInUser();

        NewUserAccount account = new NewUserAccount();
        account.setOperate("add");
        model.addAttribute("account", account);

        return "/account/create";
    }

    /**
     * post the create new userprofile info
     * @param account
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public String createUserAccount(@ModelAttribute("account") NewUserAccount account, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            account.setFeedbackMessage("No permission.");
            return null;
        }

        try {
            userAccountService.addUserAccount(user.getId(), account);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            account.setFeedbackMessage("System error.");
            return null;
        }

        return "success";
    }

    /**
     * delete the userprofile info
     * @param id
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUserAccount(@PathVariable Integer id, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
        LoggedInUser user = getLoggedInUser();

        // Permission check
        if (user == null || user.getId() == id) {
            model.addAttribute("errormsg", "No permission, you can not delete yourself! .");
            return "syserror";
        }

        try {
            userAccountService.deleteUserAccount(user.getId(), id);
        }
        catch (Exception ex) {
            model.addAttribute("errormsg", "delete fail, contact the system manager .");

            return "syserror";            
        }
        
        return "success";
       // return "redirect:/account/search.html";
    }
    
    /**
     * get the detail tab page named "basic.html"
     * @param id
     * @param message
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    public String showUserAccountBasic(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {

        model.addAttribute("id", id);
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        UserAccountBasic account = userAccountService.getUserAccountBasic(user.getId(), id);
       // account.setOperate("update");
        //diff the operate from "detail" and "membership" 
        account.setOperate("detail");
        model.addAttribute("account", account);
      //  model.addAttribute("message",message);

        return "/account/basic";
    }
    
    

    /**
     * post the update info from basic page
     * @param account
     * @param id
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/basicUpdate", method = { RequestMethod.POST })
    public String updateUserAccountBasic(@ModelAttribute("account") UserAccountBasic account, @RequestParam("id") int id, Model model,
            HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
        // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        userAccountService.updateUserAccountBasic(user.getId(), account);
        

       // return "redirect:/account/detail.html?id=" + id;
        String message = "更新基本信息成功!";
       // model.addAttribute("message", message);
        
        // return "redirect:/account/detail.html?id=" + id+"&message="+message;
        account.setFeedbackMessage(message);
        model.addAttribute("account", account);
        
        return "/account/basic";
    }

    /**
     * get the membership list page
     * @param id
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/membership", method = { RequestMethod.GET })
    public String showMembership(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
        
        Membership cmembershipQuery = new Membership();
        cmembershipQuery.setUser_id(id);
        cmembershipQuery.setType_code("C");
        cmembershipQuery.setSearchRecommended(false);
        List<?> cmembershiplist = membershipService.queryList(cmembershipQuery);
        model.addAttribute("cmembershiplist", cmembershiplist);
        
        Membership bmembershipQuery = new Membership();
        bmembershipQuery.setUser_id(id);
        bmembershipQuery.setType_code("B");
        bmembershipQuery.setSearchRecommended(false);
        List<?> bmembershiplist = membershipService.queryList(bmembershipQuery);
        model.addAttribute("bmembershiplist", bmembershiplist);
        
        Membership smembershipQuery = new Membership();
        smembershipQuery.setUser_id(id);
        smembershipQuery.setType_code("S");
        smembershipQuery.setSearchRecommended(false);
        List<?> smembershiplist = membershipService.queryList(smembershipQuery);
        model.addAttribute("smembershiplist", smembershiplist);
        
        return "/account/membership";
    }

    /**
     * get the tab page for contact
     * @param id
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/contact", method = { RequestMethod.GET })
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

        return "/account/contact";
    }

    @RequestMapping(value = "/ticket", method = { RequestMethod.GET })
    public String showTicket(@RequestParam("id") int id, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("id", id);
        
         Ticket ticketQuery = new Ticket();
        ticketQuery.setOrderByClause("created_at desc");
        ticketQuery.setUser_id(id);

        List<String> statusCodeList = new ArrayList<String>();

        statusCodeList.add("10");
        statusCodeList.add("20");
        statusCodeList.add("30");
        statusCodeList.add("40");

        ticketQuery.setStatusCodeList(statusCodeList);

        List ticketmnglist = ticketService.queryList(ticketQuery);
        model.addAttribute("ticketmnglist", ticketmnglist);
        return "/account/ticket";
    }
    
    @RequestMapping(value = "/operate", method = RequestMethod.GET)
    public String openOperateTicketForm(@RequestParam("id") int ticketId, @RequestParam("userLevel") String userLevel, Model model, HttpServletRequest request)
            throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        String username = user.getFirstName() + user.getLastName();
        // fetch the ticket information
        Ticket ticketSingleQuery = new Ticket();
        ticketSingleQuery.setId(ticketId);
        Ticket ticket = (Ticket) ticketService.queryOne(ticketSingleQuery);
        // diff the normal user operate and super user operate
        System.out.println("***************************" + userLevel);
        ticket.setOperate(userLevel);

        // fetch the ticket_reply list information
        Ticket_Reply ticketReplyQuery = new Ticket_Reply();
        ticketReplyQuery.setTicket_id(ticketId);
        List<Ticket_Reply> ticketReplylist = ticketService.replyQueryList(ticketReplyQuery);

        // add the attributes
        model.addAttribute("username", username);
        model.addAttribute("ticket", ticket);
        model.addAttribute("ticketReplylist", ticketReplylist);
        model.addAttribute("ticketReplyQuery", ticketReplyQuery);
        // return to the view(TicketOperate.jsp)
        return "/account/operate";
    }
    
    @RequestMapping(value = "/createReply", method = RequestMethod.POST)
    public String createTicketReplyForm(@ModelAttribute("ticketReplyQuery") Ticket_Reply ticketReplyQuery, @RequestParam("id") int ticketId,
            @RequestParam("userLevel") String userLevel, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        ticketReplyQuery.setReply_by(user.getUserid());
        ticketReplyQuery.setReplier_name(user.getFirstName() + user.getLastName());
        ticketReplyQuery.setReply_time(new Date());;
        ticketReplyQuery.setTicket_id(ticketId);

        ticketService.replyAdd(ticketReplyQuery);

        String message = "处理Ticket成功";
        model.addAttribute("message", message);
        return "redirect:operate.html?id=" + ticketId + "&userLevel=" + userLevel;
    }

    @RequestMapping(value = "/updateTicket", method = RequestMethod.POST)
    public String submitOperateTicketForm(@ModelAttribute("ticket") Ticket ticket, @RequestParam("id") int ticketId,
            @RequestParam("userLevel") String userLevel, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        Ticket updateTicket = new Ticket();
        updateTicket.setStatus_code(ticket.getStatus_code());
        // update status to 'COMP'
        if ("40".equals(ticket.getStatus_code())) {
            updateTicket.setClose_at(new Date());
            updateTicket.setClose_by(user.getUserid());
        }
        updateTicket.setId(ticketId);
        updateTicket.setModified_by(user.getUserid());
        updateTicket.setModified_at(new Date());
        //update processor
        updateTicket.setProcessor_id(ticket.getProcessor_id());
        
        ticketService.updateStatus(updateTicket);
        String message = "处理Ticket成功";
        model.addAttribute("message", message);
//        return "redirect:operate.html?id=" + ticketId + "&userLevel=" + userLevel;
        return "success";
    }
    @RequestMapping(value = "/reOpenTicket", method = RequestMethod.POST)
    public String reOpenTicketForm(@ModelAttribute("ticket") Ticket ticket, @RequestParam("id") int ticketId,
            @RequestParam("userLevel") String userLevel, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        Ticket updateTicket = new Ticket();
        updateTicket.setStatus_code("20");
      
        updateTicket.setId(ticketId);
        updateTicket.setModified_by(user.getUserid());
        updateTicket.setModified_at(new Date());
        ticketService.updateStatus(updateTicket);
        String message = "重启Ticket成功";
        model.addAttribute("message", message);
        return "redirect:operate.html?id=" + ticketId + "&userLevel=" + userLevel;
    }    

    @RequestMapping(value = "/order", method = { RequestMethod.GET, RequestMethod.POST})
    public String showOrder(UserOrderQueryType userOrderQueryType, Model model, HttpServletRequest request) throws Exception {
        Integer user_id = userOrderQueryType.getId();        
        
        Service_Order unfinishedOrder = userOrderQueryType.getUnFinishedOrder();
        unfinishedOrder.setUser_id(userOrderQueryType.getId());
        unfinishedOrder.setIsFinished(false);
        unfinishedOrder.setOrder_id(userOrderQueryType.getOrder_id());
        unfinishedOrder.setService_title(userOrderQueryType.getService_title());
        
        int rownum1 = serviceOrderMngService.queryCount(unfinishedOrder);
        unfinishedOrder.getPagination().setRowCount(rownum1);
        unfinishedOrder.getPagination().setPageSize(5);
        int pagecount1 = unfinishedOrder.getPagination().getPageCount();
        int currentpage1 = unfinishedOrder.getPagination().getCurrentPage();
        List<?> pageNumList1 = getPageNumList(currentpage1, pagecount1);
        model.addAttribute("pageNumList1", pageNumList1);
        
        List<?> unfinishedOrderList = serviceOrderMngService.queryList(unfinishedOrder);
        model.addAttribute("unfinishedOrderList", unfinishedOrderList);
        
        Service_Order finishedOrder = userOrderQueryType.getFinishedOrder();
        finishedOrder.setUser_id(userOrderQueryType.getId());
        finishedOrder.setIsFinished(true);
        
        Integer pastMonths = userOrderQueryType.getOrderFinishedMonths();
        if(pastMonths == null){
            pastMonths = ServiceOrderFinishedPeriodEnum.threeMonth.getCode();
        }
        
        Calendar calTo = Calendar.getInstance();
        finishedOrder.setServiceDateTo(calTo.getTime());
        
        Calendar calFrom = Calendar.getInstance();            
        calFrom.add(Calendar.MONTH, 0 - pastMonths);
        finishedOrder.setServiceDateFrom(calFrom.getTime());
        
        int rownum2 = serviceOrderMngService.queryCount(finishedOrder);
        finishedOrder.getPagination().setRowCount(rownum2);
        finishedOrder.getPagination().setPageSize(5);
        int pagecount2 = finishedOrder.getPagination().getPageCount();
        int currentpage2 = finishedOrder.getPagination().getCurrentPage();
        List<?> pageNumList2 = getPageNumList(currentpage2, pagecount2);
        model.addAttribute("pageNumList2", pageNumList2);

        finishedOrder.setOrderByClause("a.order_id desc"); // Latest created will be displayed first.
        
        List<?> finishedOrderList = serviceOrderMngService.queryList(finishedOrder);
        model.addAttribute("finishedOrderList", finishedOrderList);
        model.addAttribute("id", user_id);
        
        return "/account/order";
    }

    
    @RequestMapping(value = "/userhistory", method = { RequestMethod.GET })
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
        return "/account/userhistory";
    }
    @RequestMapping(value = "/createHistory", method = RequestMethod.POST)
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
    

    @RequestMapping(value = "/other", method = { RequestMethod.GET })
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

        return "/account/other";
    }

    @RequestMapping(value = "/other", method = { RequestMethod.POST })
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
        return "/account/other";
        
    }

    /*@RequestMapping(value = "/createmembership", method = { RequestMethod.GET })
    public String createMembership(Authentication auth, HttpServletRequest request) throws Exception {

        return "/account/createmembership";
    }*/

    @RequestMapping(value = "/createphonenumber", method = { RequestMethod.GET })
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
        
        //
        model.addAttribute("contact", contact);
        
        return "/account/createphonenumber";
    }

    @RequestMapping(value = "/createphonenumber", method = { RequestMethod.POST })
    public String createPhoneNumber(@ModelAttribute("contact") Contact contact, Model model,
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
    
    @RequestMapping(value = "/createemailaddress", method = { RequestMethod.GET })
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
        return "/account/createemailaddress";
    }

    @RequestMapping(value = "/createemailaddress", method = { RequestMethod.POST })
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
    
    @RequestMapping(value = "/createsocialaccount", method = { RequestMethod.GET })
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
        
        //
        model.addAttribute("contact", contact);
        return "/account/createsocialaccount";
    }

    @RequestMapping(value = "/createsocialaccount", method = { RequestMethod.POST })
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

    @RequestMapping(value = "/deletecontact/{id}", method = RequestMethod.GET)
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
        return "redirect:/account/contact.html?id=" +userId;
        
    }
    
    @RequestMapping(value = "/createmailingaddress", method = { RequestMethod.GET })
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
        return "/account/createmailingaddress";
    }

    @RequestMapping(value = "/createmailingaddress", method = { RequestMethod.POST })
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

    @RequestMapping(value = "/deleteaddress/{id}", method = RequestMethod.GET)
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
        return "redirect:/account/contact.html?id=" +userId;
    }
   
    
}
