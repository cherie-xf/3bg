package ca.esystem.bridges.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.esystem.framework.service.BasicService;

/**
 * 
 * @author Larry
 *
 */
public interface TicketService extends BasicService {
	public int updateStatus(Object obj);

	public List replyQueryList(Object condition);

	public int replyQueryCount(Object condition);

	public Object replyQueryOne(Object condition);

	public Object replyAdd(Object obj);

	public int replyUpdate(Object obj);
	
	public List queryNotAssignedList(Object condition);
	
    public List queryAssignedList(Object condition);	
}
