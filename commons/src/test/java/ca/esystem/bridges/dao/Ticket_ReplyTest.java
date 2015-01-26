package ca.esystem.bridges.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Ticket_Reply;

/**
 * test cases for ticket_ReplyDao
 * 
 * @author cherie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Ticket_ReplyTest extends AbstractDbunit {

    @Resource
    private Ticket_ReplyDao ticketReplyDao;
    private IDataSet        ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","ticket_status","ticket_source_type", "ticket", "ticket_reply" };
        backupCustomedTable(tables);
        ds = createDataSet("ticket_reply");
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Ticket_Reply tcondition = new Ticket_Reply();
        tcondition.setId(1);
        Ticket_Reply actual = (Ticket_Reply) ticketReplyDao.queryObjectByCondition(tcondition);
        Ticket_Reply expect = new Ticket_Reply();
        expect.setReply_msg("test reply message");
        expect.setReply_by(2);
        expect.setReplier_name("Lily");

        EntitiesHelper.assertTicketReplyEquals(actual, expect);
    }

    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket_Reply tcondition = new Ticket_Reply();
        List<Ticket> TicketReplyList = ticketReplyDao.queryListByCondition(tcondition);

        assertEquals(TicketReplyList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket_Reply tcondition = new Ticket_Reply();
        int count = ticketReplyDao.queryCountRowsByCondition(tcondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket_Reply insertTicket_Reply = new Ticket_Reply();
        insertTicket_Reply.setReply_msg("message");
        insertTicket_Reply.setReply_by(1);
        insertTicket_Reply.setReplier_name("hello");
        insertTicket_Reply.setTicket_id(1);
        ticketReplyDao.insert(insertTicket_Reply);
        Ticket_Reply expect = (Ticket_Reply) ticketReplyDao.queryObjectByCondition(insertTicket_Reply);
        assertNotNull(expect);

    }
    
    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket_Reply tcondition = new Ticket_Reply();
        tcondition.setReply_msg("modified");
        tcondition.setReply_by(1);
        tcondition.setReplier_name("cat");
        tcondition.setId(1);
        tcondition.setTicket_id(1);
        ticketReplyDao.update(tcondition);

        Ticket_Reply query = (Ticket_Reply) ticketReplyDao.queryObjectByCondition(tcondition);
        EntitiesHelper.assertTicketReplyEquals(query,tcondition);
    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }
}
