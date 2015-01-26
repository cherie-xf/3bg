package ca.esystem.bridges.dao;

import java.util.List;
import java.util.HashMap;
import org.springframework.stereotype.Repository;
import ca.esystem.framework.dao.BasicAccessDao;

/**
 * DAO for the tables of Ticket
 * 
 * @author Larry
 *
 */

@Repository
public interface TicketDao extends BasicAccessDao {
    public int updateStatus(Object obj);
    public List queryNotAssignedListByCondition(Object obj);
    public List queryAssignedListByCondition(Object obj);    
}
