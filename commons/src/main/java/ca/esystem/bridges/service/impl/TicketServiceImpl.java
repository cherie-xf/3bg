package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.TicketDao;
import ca.esystem.bridges.dao.Ticket_ReplyDao;
import ca.esystem.bridges.service.TicketService;
import ca.esystem.framework.service.AbstractService;

@Service("TicketService")
public class TicketServiceImpl extends AbstractService implements TicketService {
	
	@Resource
    private TicketDao ticketDao;
	@Resource
	private Ticket_ReplyDao ticketReplyDao;
	
	public List queryList(Object condition) {
		// TODO Auto-generated method stub
		return ticketDao.queryListByCondition(condition);
	}

	public int queryCount(Object condition) {
		// TODO Auto-generated method stub
		return ticketDao.queryCountRowsByCondition(condition);
	}

	public Object queryOne(Object condition) {
		// TODO Auto-generated method stub
		return ticketDao.queryObjectByCondition(condition);
	}

	public Object add(Object obj) {
		// TODO Auto-generated method stub
		return ticketDao.insert(obj);
	}

	public int update(Object obj) {
		// TODO Auto-generated method stub
		return ticketDao.update(obj);
	}

	public int archive(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	public int updateStatus(Object obj){
	  return ticketDao.updateStatus(obj);
	}
	
	

	public List replyQueryList(Object condition) {
		return ticketReplyDao.queryListByCondition(condition);
	}

	public int replyQueryCount(Object condition) {
		return ticketReplyDao.queryCountRowsByCondition(condition);
	}

	public Object replyQueryOne(Object condition) {
		return ticketReplyDao.queryObjectByCondition(condition);
	}

	public Object replyAdd(Object obj) {
		return ticketReplyDao.insert(obj);
	}

	public int replyUpdate(Object obj) {
		return ticketReplyDao.update(obj);
	}
	
	public List queryNotAssignedList(Object condition) {
	    return ticketDao.queryNotAssignedListByCondition(condition);
	}
	
    public List queryAssignedList(Object condition) {
        return ticketDao.queryAssignedListByCondition(condition);
    }	
}
