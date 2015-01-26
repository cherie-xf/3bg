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

import ca.esystem.bridges.domain.ServiceArea;
import ca.esystem.bridges.domain.ServiceProduct;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceProductDaoTest extends AbstractDbunit {

    @Resource
    private ServiceProductDao dao;
    private IDataSet          ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","category","member","service_product", "service_area"};
        backupCustomedTable(tables);
        ds = createDataSet("service_product");
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

        ServiceProduct object = new ServiceProduct();
        object.setService_id(1); // must set the primary key service_id to call this API.
        ServiceProduct actual = (ServiceProduct) dao.queryObjectByCondition(object);

        assertEquals(object.getService_id(), actual.getService_id());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        ServiceProduct object = new ServiceProduct();
        object.setMember_id("B0001001");
        List<ServiceProduct> list = dao.queryListByCondition(object);

        assertEquals(1, list.size());
    }
    
    @Test
    public void testQueryServiceAreaList() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        ServiceArea object = new ServiceArea();
        object.setService_id(1);
        List<?> list = dao.queryServiceAreaList(object);

        assertEquals(1, list.size());
    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        ServiceProduct object = new ServiceProduct();
        object.setCategory_id("010102");
        object.setMember_id("B0001002");
        object.setCreated_at(new Date());
        object.setUnit_id("HOUR");
        object.setStatus_id("20");
        object.setRecommend_level_id(2);

        dao.insert(object);
        ServiceProduct actual = (ServiceProduct) dao.queryObjectByCondition(object);
        assertEquals(object.getCategory_id(), actual.getCategory_id());
        assertEquals(object.getMember_id(), actual.getMember_id());
        assertEquals(object.getUnit_id(), actual.getUnit_id());
        assertEquals(object.getStatus_id(), actual.getStatus_id());
        assertEquals(object.getRecommend_level_id(), actual.getRecommend_level_id());
    }
    
    @Test
    public void testInsertServiceArea() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        ServiceProduct object = new ServiceProduct();
        object.setCategory_id("010102");
        object.setMember_id("B0001002");
        object.setCreated_at(new Date());
        object.setUnit_id("HOUR");
        object.setStatus_id("20");
        object.setRecommend_level_id(3);

        dao.insert(object);
        ServiceProduct actual = (ServiceProduct) dao.queryObjectByCondition(object);
        assertEquals(object.getCategory_id(), actual.getCategory_id());
        assertEquals(object.getMember_id(), actual.getMember_id());
        assertEquals(object.getUnit_id(), actual.getUnit_id());
        assertEquals(object.getStatus_id(), actual.getStatus_id());
        assertEquals(object.getRecommend_level_id(), actual.getRecommend_level_id());
        
        ServiceArea serviceArea = new ServiceArea();
        serviceArea.setService_id(object.getService_id());
        serviceArea.setCity_code("CT02");
        
        dao.insertServiceArea(serviceArea);
        @SuppressWarnings("unchecked")
        List<ServiceArea> actual2 = (List<ServiceArea>) dao.queryServiceAreaList(serviceArea);
        assertEquals(1, actual2.size());
        assertEquals(serviceArea.getService_id(), actual2.get(0).getService_id());
        assertEquals(serviceArea.getCity_code(), actual2.get(0).getCity_code());
    }

    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        ServiceProduct object = new ServiceProduct();
        object.setService_id(1);
        object.setCategory_id("101010");
        object.setModified_by(3);
        dao.update(object);

        ServiceProduct actual = (ServiceProduct) dao.queryObjectByCondition(object);
        Assert.assertEquals(object.getCategory_id(), actual.getCategory_id());

    }
    
    @Test
    public void testDeleteServiceArea() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);        

        ServiceArea object = new ServiceArea();
        object.setService_id(1);
        object.setCity_code("CT01");
        List<?> actual = dao.queryServiceAreaList(object);
        assertNotNull(actual);
        
        int result = dao.deleteServiceArea(object);
        assertEquals(1, result);
        actual = dao.queryServiceAreaList(object);
        assertEquals(0, actual.size());
    }
    
    @Test
    public void testDelete() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);        

        ServiceProduct object = new ServiceProduct();
        object.setService_id(1);
        List<?> actual = dao.queryListByCondition(object);
        assertEquals(1, actual.size());
        
        int result = dao.delete(object);
        assertEquals(1, result);
        actual = dao.queryListByCondition(object);
        assertEquals(0, actual.size());
    }
}
