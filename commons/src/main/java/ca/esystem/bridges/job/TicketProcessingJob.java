package ca.esystem.bridges.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.esystem.bridges.domain.MemberType;
import ca.esystem.bridges.domain.Membership;
import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.TicketStatus;
import ca.esystem.bridges.http.SMSRequest;
import ca.esystem.bridges.integration.SMSClient;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.framework.domain.Pagination;

@Service
public class TicketProcessingJob {
    private final Logger       logger = LoggerFactory.getLogger(this.getClass().getName());
    
    private MembershipService     membershipService;
    
    private TicketService ticketService;
    
    public void setMembershipService(MembershipService membershipService) {
        this.membershipService = membershipService;
        
    }
    
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
        
    }
    
    @SuppressWarnings("rawtypes")
    public boolean assignWaitingTicket() {
        
        try {
            Membership memberSearch = new Membership();
            memberSearch.setType_code(MemberType.MEMBER_S1); // Customer service
            memberSearch.setOrderByClause("member_id asc");
            memberSearch.setSearchRecommended(false);
            Pagination memberPage = new Pagination();
            memberPage.setSkip(0);
            memberPage.setPageSize(0);
            memberSearch.setPagination(memberPage);
            List memberList = membershipService.queryList(memberSearch);
            if (memberList == null || memberList.size() == 0) return true;
            
            // Search for unassigned tickets
            Ticket ticketSearch = new Ticket();
            ticketSearch.setOrderByClause("created_at asc");
            Pagination ticketPage = new Pagination();
            ticketPage.setSkip(0);
            ticketPage.setPageSize(100);
            ticketSearch.setPagination(ticketPage);
            @SuppressWarnings("unused")
            List ticketList = ticketService.queryNotAssignedList(ticketSearch);
            if (ticketList != null) {
                // Search last assigned ticket to decide next customer service member
                // to assign ticket to
                ticketSearch = new Ticket();
                ticketSearch.setOrderByClause("created_at desc");
                ticketPage = new Pagination();
                ticketPage.setSkip(0);
                ticketPage.setPageSize(1);
                ticketSearch.setPagination(ticketPage);
                @SuppressWarnings("unused")
                int index = 0;
                List assignedTicketList = ticketService.queryAssignedList(ticketSearch);
                if (assignedTicketList != null && assignedTicketList.size() == 1) {
                   Ticket assignedTicket = (Ticket)assignedTicketList.get(0);
                   int processorId = assignedTicket.getProcessor_id();
                   for(Object object: memberList) {
                       Membership member = (Membership)object;
                       index ++;
                       if (processorId == member.getUser_id()) {
                           break;
                       }
                   }
                }
                index = index >= memberList.size() ? 0 : index;
                // Assign tickets to customer service members in turn
                for (Object object : ticketList) {
                    Ticket ticket = (Ticket)object;
                    Membership member = (Membership)memberList.get(index);
                    Ticket status = new Ticket();
                    status.setId(ticket.getId());
                    status.setProcessor_id(member.getUser_id());
                    ticketService.updateStatus(status);
                    index ++;
                    if (index >= memberList.size()) index = 0;
                }
            }
        }
        catch(Exception ex) {
            logger.equals(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
