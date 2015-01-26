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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.Address;

@RunWith(SpringJUnit4ClassRunner.class)
public class AddressDaoTest extends AbstractDbunit {

    @Resource
    private AddressDao addressDao;
    private IDataSet   ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","country", "city", "address" };
        backupCustomedTable(tables);
        ds = createDataSet("address");
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Address acondition = new Address();
        acondition.setAddress_id(1);
        Address actual = (Address) addressDao.queryObjectByCondition(acondition);
        Address expect = new Address();
        expect.setAddress("this is address1");
        expect.setDistrict("this is district1");
        expect.setCity_code("CT01");
        expect.setPostal_code("V5J0A4");
        expect.setUser_id(1);
        expect.setModified_by(1);
        expect.setCreated_by(1);

        EntitiesHelper.assertAddressEquals(actual, expect);;
    }

    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Address acondition = new Address();
        List<Address> SearchMissedList = addressDao.queryListByCondition(acondition);

        assertEquals(SearchMissedList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Address acondition = new Address();
        int count = addressDao.queryCountRowsByCondition(acondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Address insertAddress = new Address();

        insertAddress.setAddress("insert address");
        insertAddress.setDistrict("insert district");
        insertAddress.setCity_code("CT02");
        insertAddress.setPostal_code("V7N 08C");
        insertAddress.setUser_id(1);
        insertAddress.setModified_by(1);
        addressDao.insert(insertAddress);
        Address expect = (Address) addressDao.queryObjectByCondition(insertAddress);
        assertNotNull(expect);

    }

    @Test
    public void testDelete() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Address acondition = new Address();
        acondition.setAddress_id(1);
        addressDao.delete(acondition);
        int count = addressDao.queryCountRowsByCondition(new Address());
        assertEquals(count, 1);
    }

    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Address acondition = new Address();
        acondition.setAddress_id(1);
        acondition.setAddress("update address");
        acondition.setDistrict("update district");
        acondition.setCity_code("CT02");
        acondition.setPostal_code("V7N 09C");
        acondition.setModified_by(2);
        addressDao.update(acondition);

        Address query = (Address) addressDao.queryObjectByCondition(acondition);
        assertNotNull(query);
        assertEquals(query.getAddress(), acondition.getAddress());
        assertEquals(query.getDistrict(), acondition.getDistrict());
        assertEquals(query.getCity_code(), acondition.getCity_code());
        assertEquals(query.getPostal_code(), acondition.getPostal_code());
        assertEquals(query.getModified_by(), acondition.getModified_by());

    }

    /*
     * @Test public void testSelectList() { Address addressquery = new Address(); addressquery.setUser_id(1);
     * 
     * List<Address> addresslist = addressDao.queryListByCondition(addressquery); assertNotNull(addresslist); }
     * 
     * @Test public void testInsertAddress() { System.out.println(new Object() { }.getClass().getEnclosingMethod().getName());
     * 
     * Address address = new Address(); address.setUser_id(1); address.setAddress("1012 10Ave"); address.setDistrict("Metrotown"); address.setCity_code("CT01");
     * address.setPostal_code("V3K908"); address.setCreated_by(1); address.setCreated_at(new Date()); addressDao.insert(address); int pk =
     * address.getAddress_id(); System.out.println("New address id=" + pk); assertTrue(pk > 0); }
     * 
     * @Test public void testUpdateAddress() { Address address = new Address(); address.setUser_id(1); address.setAddress("7412 11Ave");
     * address.setDistrict("Heigh Gate"); address.setCity_code("CT03"); address.setPostal_code("V3N123"); address.setAddress_id(2); address.setModified_by(3);
     * address.setModified_at(new Date()); int i = addressDao.update(address); assertTrue(i > 0); }
     */
}
