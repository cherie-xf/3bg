package ca.esystem.bridges.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.UserAccount;
import ca.esystem.bridges.domain.UserAccountSearch;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserAccountDaoTest extends AbstractDbunit {

    @Resource
    private UserAccountDao userAccountDao;
    private IDataSet       ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {
        

      //  backuponeTable("user_profile");
        String[] tables = new String[] { "user","languages","user_profile"};
        backupCustomedTable(tables);
        ds = createDataSet("user_account");
    }
    
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        UserAccountSearch ucondition = new UserAccountSearch();
        List<UserAccount> list = userAccountDao.queryListByCondition(ucondition);

        assertEquals(list.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        UserAccountSearch ucondition = new UserAccountSearch();
        int count = userAccountDao.queryCountRowsByCondition(ucondition);
        assertEquals(count, 2);

    }

    /*@Test
    public void testQueryCountRowsByCondition() {

        Map condition = new HashMap<String, Object>();
        condition.put("gender", GenderType.female);

        int count = userAccountDao.queryCountRowsByCondition(condition);
        assertTrue(count > 0);
    }

    @Test
    public void testQueryListByCondition() {

        Map condition = new HashMap<String, Object>();
        condition.put("gender", GenderType.female);
        condition.put("newsLetter", true);
        condition.put("orderBy", "gender");
        List list = userAccountDao.queryListByCondition(condition);
        assertTrue(list != null && list.size() > 0);
    }*/

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }
}
