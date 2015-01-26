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

import ca.esystem.bridges.domain.Category;
import ca.esystem.bridges.domain.CategoryTag;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest extends AbstractDbunit {

    @Resource
    private CategoryDao dao;
    private IDataSet    ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "category", "category_tag"};
        backupCustomedTable(tables);
        ds = createDataSet("category");
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

        Category object = new Category();
        object.setCategory_id("01"); // must set the primary key service_id to call this API.
        Category actual = (Category) dao.queryObjectByCondition(object);

        Assert.assertEquals(object.getCategory_id(), actual.getCategory_id());
    }
    
    @Test
    public void testQueryTagListByCondition() throws DatabaseUnitException, SQLException {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        CategoryTag object = new CategoryTag();
        object.setCategory_id("01"); // must set the primary key service_id to call this API.
        List<?> actual = dao.queryTagListByCondition(object);

        Assert.assertEquals(1, actual.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Category object = new Category();
        object.setCategory_name("category 1");
        List<Category> list = dao.queryListByCondition(object);

        assertEquals(1,list.size());
    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Category object = new Category();
        object.setCategory_id("010102");
        object.setCreated_at(new Date());

        dao.insert(object); //test insert
        Category actual = (Category) dao.queryObjectByCondition(object);
        assertEquals(object.getCategory_id(),actual.getCategory_id());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testInsertTag() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Category object = new Category();
        object.setCategory_id("010102");
        object.setCreated_at(new Date());

        dao.insert(object); //test insert
        Category actual = (Category) dao.queryObjectByCondition(object);
        assertEquals(object.getCategory_id(),actual.getCategory_id());
        
        CategoryTag tag = new CategoryTag();
        tag.setCategory_id("010102");
        tag.setCreated_at(new Date());
        tag.setTag("new Tag");

        dao.insertTag(tag); //test insertTag
        List<CategoryTag> actualTag = (List<CategoryTag>) dao.queryTagListByCondition(tag);
        assertEquals(1,actualTag.size());
        assertEquals(tag.getTag(),actualTag.get(0).getTag());
    }
    
    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Category object = new Category();
        object.setCategory_name("new name");
        object.setCategory_id("01");
        object.setModified_by(3);
        dao.update(object);

        Category query = (Category) dao.queryObjectByCondition(object);
        assertEquals(object.getCategory_id(),query.getCategory_id());
        assertEquals(object.getCategory_name(),query.getCategory_name());
    }
    
    @Test
    public void testDelete() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);        

        Category object = new Category();
        object.setCategory_id("01");
        Category query = (Category) dao.queryObjectByCondition(object);
        assertNotNull(query);
        
        int result = dao.delete(object);
        assertEquals(1, result);
        query = (Category) dao.queryObjectByCondition(object);
        assertEquals(null, query);
    }
    
    @Test
    public void testDeleteTag() throws DatabaseUnitException, SQLException {
        assertNotNull("the dbunitCon  is not exist", dbunitCon);
        assertNotNull("the IDataSet is not exist", ds);
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);        

        CategoryTag object = new CategoryTag();
        object.setTag_id(1L);
        object.setCategory_id("01");
        List<?> actual = dao.queryTagListByCondition(object);
        assertEquals(1, actual.size());
        
        int result = dao.deleteTag(object);
        assertEquals(1, result);
        actual = dao.queryTagListByCondition(object);
        assertEquals(0, actual.size());
    }
}
