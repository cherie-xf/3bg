package ca.esystem.bridges.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Ticket_Reply;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.web.controller.AbstractController;

import com.google.gson.Gson;

/**
 * The controller class for ticket and suspend ticke management.
 * 
 * @author Cherie
 *
 */

@Controller
public class TicketMngController extends AbstractController {
    private final Logger  logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "TicketService")
    private TicketService ticketService;
    @Resource(name = "UserAccountService")
    private UserAccountService userAccountService;
    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping(value = "/ticket/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryTicketMngList(@ModelAttribute("ticketQuery") Ticket ticketQuery, Model model, HttpServletRequest request) throws Exception {

        logger.debug("Received request at /ticket/manage/");

        int rownum = ticketService.queryCount(ticketQuery);
        ticketQuery.getPagination().setRowCount(rownum);
        ticketQuery.getPagination().setPageSize(20);
        int pagecount = ticketQuery.getPagination().getPageCount();
        int currentpage = ticketQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        ticketQuery.setOrderByClause("created_at desc");
        List ticketmnglist = ticketService.queryList(ticketQuery);
        model.addAttribute("ticketmnglist", ticketmnglist);
        String role = "super";
        model.addAttribute("role", role);

        return "/ticket/manage";
    }

    @RequestMapping(value = "/ticket/create", method = RequestMethod.GET)
    public String openCreateTicketForm(@ModelAttribute("ticketQuery") Ticket ticketQuery, Model model, HttpServletRequest request) throws Exception {
        // ticketQuery contains customers' personal information when creating ticket from user managerment

        logger.debug("Received request at /ticket/create");
        Ticket ticket = new Ticket();
        ticket.setOperate("add");
        model.addAttribute("ticket", ticket);
        return "/ticket/form";
    }

    @RequestMapping(value = "/ticket/create", method = RequestMethod.POST)
    public String submitCreateTicketForm(@ModelAttribute("ticket") Ticket ticket, Model model, HttpServletRequest request) throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        ticket.setCreated_by(user.getUserid());
        ticket.setCreated_at(new Date());
        ticketService.add(ticket);

        String message = "创建Ticket成功";
        //model.addAttribute("message", message);
        ticket.setFeedbackMessage(message);
        ticket.setOperate("update");
        model.addAttribute("ticket", ticket);
        return "/ticket/form";
    }

    @RequestMapping(value = "/ticket/operate", method = RequestMethod.GET)
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
        return "/ticket/operate";
    }

    @RequestMapping(value = "/ticket/createReply", method = RequestMethod.POST)
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
    
    @RequestMapping(value = "/ticket/update", method = RequestMethod.POST)
    public String submitUpdateTicketForm(@ModelAttribute("ticket") Ticket ticket, @RequestParam("id") int ticketId,
             Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        Ticket updateTicket = new Ticket();
        updateTicket.setStatus_code(ticket.getStatus_code());
        updateTicket.setTitle(ticket.getTitle());
        updateTicket.setDescription(ticket.getDescription());
        updateTicket.setType_code(ticket.getType_code());
        updateTicket.setUser_id(ticket.getUser_id());
        updateTicket.setId(ticketId);
        updateTicket.setModified_by(user.getUserid());
        updateTicket.setModified_at(new Date());
        
        
        ticketService.update(updateTicket);
        String message = "处理Ticket成功";
        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/ticket/updateTicket", method = RequestMethod.POST)
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

    @RequestMapping(value = "/ticket/suspendmng", method = { RequestMethod.GET, RequestMethod.POST })
    public String querySuspendTicketMngList(@ModelAttribute("ticketQuery") Ticket ticketQuery, Model model, HttpServletRequest request) throws Exception {

        ticketQuery.setOrderByClause("created_at desc");
        ticketQuery.setStatus_code("30");
        List ticketmnglist = ticketService.queryList(ticketQuery);
        model.addAttribute("ticketmnglist", ticketmnglist);

        return "/ticket/suspendmng";
    }

    @RequestMapping(value = "/ticket/normalmng", method = { RequestMethod.GET, RequestMethod.POST })
    public String querynormalTicketMngList(@ModelAttribute("ticketQuery") Ticket ticketQuery, Model model, HttpServletRequest request) throws Exception {

        ticketQuery.setOrderByClause("created_at desc");

        List<String> statusCodeList = new ArrayList<String>();

        statusCodeList.add("10");
        statusCodeList.add("20");
        statusCodeList.add("30");
        //statusCodeList.add("40");

        ticketQuery.setStatusCodeList(statusCodeList);

        List ticketmnglist = ticketService.queryList(ticketQuery);
        model.addAttribute("ticketmnglist", ticketmnglist);

        return "/ticket/normalmng";
    }

    @RequestMapping(value = "/ticket/customermng", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryCustomerTicketMngList(@ModelAttribute("ticketQuery") Ticket ticketQuery, Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();
        ticketQuery.setOrderByClause("created_at desc");
        ticketQuery.setProcessor_id(user.getUserid());

        List<String> statusCodeList = new ArrayList<String>();

        statusCodeList.add("10");
        statusCodeList.add("20");

        ticketQuery.setStatusCodeList(statusCodeList);

        List ticketmnglist = ticketService.queryList(ticketQuery);
        model.addAttribute("ticketmnglist", ticketmnglist);

        return "/ticket/customermng";
    }
    
    @RequestMapping(value = "/ticket/reOpenTicket", method = RequestMethod.POST)
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
    
    @SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/ticket/getUerInfo", method = RequestMethod.GET)
    public @ResponseBody String refreshDictionary(Model model, HttpServletRequest request) throws Exception {
        String user_id = request.getParameter("user_id");
        User user = new User();
        user.setId(Integer.parseInt(user_id));
        
        user = (User) userService.queryOne(user);
        System.out.println(user.getFirstName());
        String name = user.getFirstName()+" "+user.getLastName();
        
       /* Business_Category businessCategory = new Business_Category();
        businessCategory .setMember_id(member_id );
        
        List<Business_Category> list = (List<Business_Category>) membershipDao.queryBusinessCategoryList(businessCategory);*/
        
        Gson gson = new Gson();
        String json =  gson.toJson(name);
        return json;      
    }
    

}
