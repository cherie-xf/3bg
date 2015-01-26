package ca.esystem.bridges.dao;

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

import static org.junit.Assert.*;
import ca.esystem.bridges.domain.Ticket;

/**
 * test cases for ticketDao
 * 
 * @author cherie
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TicketDaoTest extends AbstractDbunit {
    @Resource
    private TicketDao ticketDao;
    private IDataSet  ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","ticket_status","ticket_source_type", "ticket"};
        backupCustomedTable(tables);
        ds = createDataSet("ticket");
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Ticket tcondition = new Ticket();
        tcondition.setId(1);
        Ticket actual = (Ticket) ticketDao.queryObjectByCondition(tcondition);

        EntitiesHelper.assertTicket(actual);
    }

    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Ticket tcondition = new Ticket();
        List<Ticket> TicketList = ticketDao.queryListByCondition(tcondition);

        assertEquals(TicketList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket tcondition = new Ticket();
        int count = ticketDao.queryCountRowsByCondition(tcondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket insertTicket = new Ticket();
        EntitiesHelper.setBaseTicket(insertTicket);
        ticketDao.insert(insertTicket);
        Ticket expect = (Ticket) ticketDao.queryObjectByCondition(insertTicket);
        assertNotNull(expect);
       // EntitiesHelper.assertTicket((Ticket) ticketDao.queryObjectByCondition(insertTicket));

    }

    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket tcondition = new Ticket();
        tcondition.setId(1);
        tcondition.setTitle("change");
        tcondition.setDescription("change description");
        tcondition.setStatus_code("20");
        tcondition.setModified_by(3);
        tcondition.setType_code("PHONE");
        ticketDao.update(tcondition);

        Ticket query = (Ticket) ticketDao.queryObjectByCondition(tcondition);
        assertEquals(query.getTitle(), tcondition.getTitle());
        assertEquals(query.getDescription(), tcondition.getDescription());
        assertEquals(query.getStatus_code(), tcondition.getStatus_code());
        assertEquals(query.getModified_by(), tcondition.getModified_by());
        assertEquals(query.getType_code(), tcondition.getType_code());
    }

    @Test
    public void testUpdateStatus() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Ticket tcondition = new Ticket();
        tcondition.setId(1);
        tcondition.setStatus_code("20");
        tcondition.setModified_by(3);
        ticketDao.updateStatus(tcondition);

        Ticket query = (Ticket) ticketDao.queryObjectByCondition(tcondition);
        assertEquals(query.getStatus_code(), tcondition.getStatus_code());
        assertEquals(query.getModified_by(), tcondition.getModified_by());
    }

    @Test(expected = Exception.class)
    public void testExceptionOut() throws Exception {
        throw new Exception();
    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }
    
    

}
