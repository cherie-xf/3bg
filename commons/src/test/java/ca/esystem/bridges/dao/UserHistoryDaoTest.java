package ca.esystem.bridges.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
import ca.esystem.bridges.domain.User_History;

/**
 * test cases for ticket_ReplyDao
 * 
 * @author cherie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserHistoryDaoTest extends AbstractDbunit{
    @Resource
    private UserHistoryDao userHistoryDao;
    private IDataSet        ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","user_history" };
        backupCustomedTable(tables);
        ds = createDataSet("user_history");
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        User_History tcondition = new User_History();
        tcondition.setHistory_id(1);
        User_History actual = (User_History) userHistoryDao.queryObjectByCondition(tcondition);
        User_History expect = new User_History();
        expect.setContent("test content message");
        expect.setCreater_name("Lily");
        expect.setCreated_by(1);
        expect.setUser_id(1);
        expect.setHistory_id(1);

        EntitiesHelper.assertUserHistoryEquals(actual, expect);
    }

    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        User_History tcondition = new User_History();
        List<Ticket> TicketReplyList = userHistoryDao.queryListByCondition(tcondition);

        assertEquals(TicketReplyList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        User_History tcondition = new User_History();
        int count = userHistoryDao.queryCountRowsByCondition(tcondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        User_History insertUser_History = new User_History();
        insertUser_History.setUser_id(2);
        insertUser_History.setContent("insert content test");
        insertUser_History.setCreater_name("wang");
        insertUser_History.setCreated_by(2);
        insertUser_History.setCreated_at(new Date());
        userHistoryDao.insert(insertUser_History);
        User_History expect = (User_History) userHistoryDao.queryObjectByCondition(insertUser_History);
        System.out.println("@"+expect.getUser_id());
        assertNotNull(expect);

    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }
}
