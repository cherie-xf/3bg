package ca.esystem.bridges.dao;

import static org.junit.Assert.*;

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

import ca.esystem.bridges.domain.Business_Category;
import ca.esystem.bridges.domain.Business_Profile;
import ca.esystem.bridges.domain.Membership;

@RunWith(SpringJUnit4ClassRunner.class)
public class MembershipDaoTest extends AbstractDbunit {

    @Resource
    private MembershipDao dao;
    private IDataSet      ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

       // String[] tables = new String[] { "user", "member_type", "recommend_level", "member", "category", "business_profile", "business_category" };
        String[] tables = new String[] { "user",   "member", "category", "business_profile", "business_category" };
 
        backupCustomedTable(tables);
        ds = createDataSet("member");
    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {
        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Membership expect = new Membership();
        expect.setMember_id("B0001001"); // must set the primary key service_id to call this API.
        expect.setUser_id(1);
        expect.setCreated_by(1);
        expect.setModified_by(1);
        expect.setType_code("B1");
        expect.setCredit(new Long(50));
        Membership actual = (Membership) dao.queryObjectByCondition(expect);

        EntitiesHelper.assertMembershipEquals(actual, expect);
    }

    @Test
    public void testQueryBusinessProfileByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Business_Profile expect = new Business_Profile();
        expect.setMember_id("B0001001"); // must set the primary key service_id to call this API.
        expect.setRecommend_level_id(1);
        expect.setBusiness_name("business name 1");
        expect.setBusiness_number("business number 1");

        Business_Profile actual = (Business_Profile) dao.queryBusinessProfileByCondition(expect);

        EntitiesHelper.assertBusinessProfileEquals(actual, expect);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryBusinessCategoryList() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Business_Category object = new Business_Category();
        object.setMember_id("C0001001");
        List<Business_Category> list = (List<Business_Category>) dao.queryBusinessCategoryList(object);

        assertEquals(list.size(), 1);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Membership object = new Membership();
        object.setType_code("B1");
        object.setSearchRecommended(false);
        List<Business_Category> list = dao.queryListByCondition(object);

        assertEquals(1, list.size());
    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Membership insertMembership = new Membership();
        insertMembership.setMember_id("B0001003"); // must set the primary key service_id to call this API.
        insertMembership.setUser_id(2);
        insertMembership.setCreated_by(2);
        insertMembership.setModified_by(2);
        insertMembership.setType_code("S1");
        insertMembership.setCredit(new Long(50));
        insertMembership.setCreated_at(new Date());

        dao.insert(insertMembership);
        Membership expect = (Membership) dao.queryObjectByCondition(insertMembership);
        assertNotNull(expect);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testInsertBusinessCategory() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Business_Category insertBusiness_Category = new Business_Category();
        insertBusiness_Category.setMember_id("B0001001"); // must set the primary key service_id to call this API.
        insertBusiness_Category.setCategory_id("02"); 

        dao.insertBusinessCategory(insertBusiness_Category);
        List list =  dao.queryBusinessCategoryList(insertBusiness_Category);
        assertEquals(2,list.size());
    }

    @Test
    public void testInsertBusinessProfile() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Business_Profile insertBusiness_Profile = new Business_Profile();
        insertBusiness_Profile.setMember_id("B0001002"); // must set the primary key service_id to call this API.
        insertBusiness_Profile.setRecommend_level_id(0);
        insertBusiness_Profile.setBusiness_number("I01");
        insertBusiness_Profile.setBusiness_name("Insert business name");

        dao.insertBusinessProfile(insertBusiness_Profile);
        Business_Profile expect = (Business_Profile) dao.queryBusinessProfileByCondition(insertBusiness_Profile);
        assertNotNull(expect);
    }

    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Membership object = new Membership();
        object.setType_code("S1");;
        object.setMember_id("B0001001");
        object.setModified_by(2);
        dao.update(object);

        Membership query = (Membership) dao.queryObjectByCondition(object);
        assertEquals(object.getMember_id(), query.getMember_id());
        assertEquals(object.getType_code(), query.getType_code());
    }

    @Test
    public void testUpdateBusinessProfile() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Business_Profile object = new Business_Profile();
        object.setRecommend_level_id(0);
        object.setMember_id("B0001001");
        object.setModified_by(2);
        dao.updateBusinessProfile(object);

        Business_Profile query = (Business_Profile) dao.queryBusinessProfileByCondition(object);
        assertEquals(object.getMember_id(), query.getMember_id());
        assertEquals(object.getRecommend_level_id(), query.getRecommend_level_id());
    }

    @Test
    public void testDeleteBusinessCategory() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Membership object = new Membership();
        object.setMember_id("B0001001");
        Membership query = (Membership) dao.queryObjectByCondition(object);
        assertNotNull(query);

        int result = dao.delete(object);
        assertEquals(1, result);
        query = (Membership) dao.queryObjectByCondition(object);
        assertNull(query);
    }

    @Test
    public void testDeleteBusinessProfile() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Business_Profile object = new Business_Profile();
        object.setMember_id("B0001001");
        Object actual = dao.queryBusinessProfileByCondition(object);
        assertNotNull(actual);

        int result = dao.deleteBusinessProfile(object);
        assertEquals(1, result);
        actual = dao.queryBusinessProfileByCondition(object);
        assertNull(actual);
    }

    @Test
    public void testDelete() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Membership object = new Membership();
        object.setMember_id("B0001001");
        object.setSearchRecommended(false);
        List<?> actual = dao.queryListByCondition(object);
        assertEquals(1, actual.size());

        int result = dao.delete(object);
        assertEquals(1, result);
        actual = dao.queryListByCondition(object);
        assertEquals(0, actual.size());
    }
}
