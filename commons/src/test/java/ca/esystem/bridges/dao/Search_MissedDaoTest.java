package ca.esystem.bridges.dao;

import static org.junit.Assert.*;

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

import ca.esystem.bridges.domain.Search_Missed;

/**
 * test cases for search_MissedDao
 * 
 * @author cherie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Search_MissedDaoTest extends AbstractDbunit {
    @Resource
    private Search_MissedDao searchMissedDao;
    private IDataSet        ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user", "search_missed"};
        backupCustomedTable(tables);
        ds = createDataSet("search_missed");
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Search_Missed scondition = new Search_Missed();
        scondition.setId(1);
        Search_Missed actual = (Search_Missed) searchMissedDao.queryObjectByCondition(scondition);
        Search_Missed expect = new Search_Missed();
        expect.setKeyword("job");
        expect.setUser_id(1);
        expect.setUser_ip_address("127.0.0.1");

        EntitiesHelper.assertSearchMissedEquals(actual, expect);
    }
    
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Search_Missed scondition = new Search_Missed();
        List<Search_Missed> SearchMissedList = searchMissedDao.queryListByCondition(scondition);

        assertEquals(SearchMissedList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Search_Missed scondition = new Search_Missed();
        int count = searchMissedDao.queryCountRowsByCondition(scondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Search_Missed insertSearch_Missed = new Search_Missed();
        insertSearch_Missed.setKeyword("sun");
        insertSearch_Missed.setUser_id(2);
        insertSearch_Missed.setUser_ip_address("197.0.0.1");
        searchMissedDao.insert(insertSearch_Missed);
        Search_Missed expect = (Search_Missed) searchMissedDao.queryObjectByCondition(insertSearch_Missed);
        assertNotNull(expect);

    }
    @Test
    public void testDelete() throws DatabaseUnitException, SQLException
    {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Search_Missed scondition = new Search_Missed();
        scondition.setId(1);
        searchMissedDao.delete(scondition);
        Search_Missed query = (Search_Missed) searchMissedDao.queryObjectByCondition(scondition);
        assertNull(query);
        
    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }

}
