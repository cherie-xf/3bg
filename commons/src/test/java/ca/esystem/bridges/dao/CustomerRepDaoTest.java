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
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.CustomerRepInfo;

/**
 * DaoTest for CustomerRepDao
 * 
 * @author cherie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRepDaoTest extends AbstractDbunit {

    @Resource
    private CustomerRepDao customerRepDao;
    private IDataSet       ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {
        

      //  backuponeTable("user_profile");
        String[] tables = new String[] { "user","languages","user_profile","member_type","recommend_level","member"};
        backupCustomedTable(tables);
        ds = createDataSet("customerRep");
    }
    
    
    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        CustomerRepInfo condition = new CustomerRepInfo();
        condition.setMember_id("S0000001");
        CustomerRepInfo actual = (CustomerRepInfo) customerRepDao.queryObjectByCondition(condition);
        assertNotNull(actual);
        System.out.println(actual.getName());
        CustomerRepInfo expect = new CustomerRepInfo();
        expect.setEmail("1111@gmail.com");
        expect.setPhone("77881111");
        expect.setFirstName("test1");
        expect.setLastName("Apple");
        expect.setUser_id(1);
        expect.setType_code("S1");
        expect.setPreferred_language("10MAN");
        expect.setStatus(1);
        expect.setMember_id("S0000001");
        
        

        EntitiesHelper.assertCustomerRepInfoEquals(actual, expect);
    }
    
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        CustomerRepInfo condition = new CustomerRepInfo();
        List<CustomerRepInfo> list = customerRepDao.queryListByCondition(condition);

        assertEquals(list.size(), 3);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        CustomerRepInfo condition = new CustomerRepInfo();
        int count = customerRepDao.queryCountRowsByCondition(condition);
        assertEquals(count, 3);

    }
   
    
    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }
}
