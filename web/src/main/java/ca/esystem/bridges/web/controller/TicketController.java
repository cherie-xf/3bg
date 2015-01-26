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
import org.springframework.web.bind.annotation.RequestParam;

import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Ticket_Reply;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.web.controller.AbstractController;
/**
 * The controller class for user ticket  management.
 * @author cherie
 *
 */
@Controller
public class TicketController extends AbstractController {
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

     // get user info
        LoggedInUser user = getLoggedInUser();

        if (user == null) {
            return null;
        }

        ticketQuery.setOrderByClause("created_at desc");
        ticketQuery.setUser_id(user.getUserid());
        List<Ticket> ticketmnglist = ticketService.queryList(ticketQuery);
        model.addAttribute("ticketmnglist", ticketmnglist);

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
        ticket.setUser_id(user.getUserid());
        ticket.setCreated_by(user.getUserid());
        ticket.setCreated_at(new Date());
        ticket.setType_code("RGST");
        ticket.setStatus_code("10");
        ticketService.add(ticket);

        String message = "创建Ticket成功";
        //model.addAttribute("message", message);
        ticket.setFeedbackMessage(message);
        ticket.setOperate("update");
        model.addAttribute("ticket", ticket);
        return "/ticket/form";
    }
    
    @RequestMapping(value = "/ticket/update", method = RequestMethod.POST)
    public String submitUpdateTicketForm(@ModelAttribute("ticket") Ticket ticket, @RequestParam("id") int ticketId,
             Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        Ticket updateTicket = new Ticket();
        updateTicket.setTitle(ticket.getTitle());
        updateTicket.setDescription(ticket.getDescription());
        updateTicket.setId(ticketId);
        updateTicket.setModified_by(user.getUserid());
        updateTicket.setModified_at(new Date());
        
        
        ticketService.update(updateTicket);
        String message = "处理Ticket成功";
        model.addAttribute("message", message);
        return "/dialog/success";
    }
    
    @RequestMapping(value = "/ticket/operate", method = RequestMethod.GET)
    public String openOperateTicketForm(@RequestParam("id") int ticketId, @RequestParam(value = "userLevel", required = false) String userLevel, Model model, HttpServletRequest request)
            throws Exception {
        // get user info
        LoggedInUser user = getLoggedInUser();
        String username = user.getFirstName() + user.getLastName();
        // fetch the ticket information
        Ticket ticketSingleQuery = new Ticket();
        ticketSingleQuery.setId(ticketId);
        Ticket ticket = (Ticket) ticketService.queryOne(ticketSingleQuery);
        // diff the normal user operate and super user operate
        if(userLevel != null)
        {
            ticket.setOperate(userLevel);
        }
        

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
    
    @RequestMapping(value = "/ticket/cancle", method = { RequestMethod.GET, RequestMethod.POST })
    public String cancelTicket(@ModelAttribute("ticket") Ticket ticket, @RequestParam("id") int ticketId,
             Model model, HttpServletRequest request) throws Exception {

        // get user info
        LoggedInUser user = getLoggedInUser();

        Ticket updateTicket = new Ticket();
        
        updateTicket.setId(ticketId);
        updateTicket.setStatus_code("50");
        updateTicket.setModified_by(user.getUserid());
        updateTicket.setModified_at(new Date());
        
        
        ticketService.update(updateTicket);
        String message = "取消Ticket成功!";
        model.addAttribute("message", message);
        return "/dialog/success";
    }
}
