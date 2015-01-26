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

import ca.esystem.bridges.domain.ServiceSchedule;
import ca.esystem.bridges.domain.Service_Order;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceOrderDaoTest extends AbstractDbunit {

    @Resource
    private ServiceOrderDao dao;
    private IDataSet          ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","category","member","service_product", "service_area", "service_order", "service_schedule"};
        backupCustomedTable(tables);
        ds = createDataSet("service_order");
    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }

    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Service_Order object = new Service_Order();
        object.setOrder_id(1); // must set the primary key service_id to call this API.
        Service_Order actual = (Service_Order) dao.queryObjectByCondition(object);

        assertEquals(object.getOrder_id(), actual.getOrder_id());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Service_Order object = new Service_Order();
        object.setCustomer_member_id("C0001001");
        List<Service_Order> list = dao.queryListByCondition(object);

        //assertEquals(1, list.size());TODO
    }
    
    @Test
    public void testQueryServiceScheduleList() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        ServiceSchedule object = new ServiceSchedule();
        object.setOrder_id(1);
        List<?> list = dao.queryServiceScheduleList(object);

        assertEquals(1, list.size());
    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Service_Order object = new Service_Order();
        object.setService_id(1);
        object.setCreated_at(new Date());
        object.setUnit_id("HOUR");
        object.setStatus_id("10");
        object.setCustomer_member_id("C0001001");
        
        dao.insert(object);
        Service_Order actual = (Service_Order) dao.queryObjectByCondition(object);
        assertEquals(object.getService_id(), actual.getService_id());
        assertEquals(object.getCustomer_member_id(), actual.getCustomer_member_id());
        assertEquals(object.getUnit_id(), actual.getUnit_id());
        assertEquals(object.getStatus_id(), actual.getStatus_id());
    }
    
    @Test
    public void testInsertServiceSchedule() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        ServiceSchedule object = new ServiceSchedule();
        object.setOrder_id(1);
        object.setServicetime(new Date());
        object.setSelected(true);
        
        dao.insertServiceSchedule(object);
        @SuppressWarnings("unchecked")
        List<ServiceSchedule> actual2 = (List<ServiceSchedule>) dao.queryServiceScheduleList(object);
        assertEquals(2, actual2.size());
    }

    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Service_Order object = new Service_Order();
        object.setOrder_id(1);
        object.setRequirement("abcdefg");
        object.setStatus_id("30");
        object.setModified_by(3);
        dao.update(object);

        Service_Order actual = (Service_Order) dao.queryObjectByCondition(object);
        Assert.assertEquals(object.getRequirement(), actual.getRequirement());
        Assert.assertEquals(object.getStatus_id(), actual.getStatus_id());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testDeleteServiceSchedule() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);        

        ServiceSchedule object = new ServiceSchedule();
        object.setOrder_id(1);
        
        List<ServiceSchedule> actual = (List<ServiceSchedule>) dao.queryServiceScheduleList(object);
        assertEquals(1, actual.size());
        assertEquals(object.getOrder_id(), actual.get(0).getOrder_id());
        assert(actual.get(0).getId() > 0);
        
        int result = dao.deleteServiceSchedule(actual.get(0));
        assertEquals(1, result);
        actual = (List<ServiceSchedule>) dao.queryServiceScheduleList(object);
        assertEquals(0, actual.size());
    }
    
    @Test
    public void testDelete() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);        

        Service_Order object = new Service_Order();
        object.setOrder_id(1);
        List<?> actual = dao.queryListByCondition(object);
        assertEquals(1, actual.size());
        
        int result = dao.delete(object);
        assertEquals(1, result);
        actual = dao.queryListByCondition(object);
        assertEquals(0, actual.size());
    }
}
