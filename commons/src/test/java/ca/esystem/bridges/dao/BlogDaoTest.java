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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.dao.BlogDao;
import ca.esystem.bridges.domain.Blog;
import ca.esystem.bridges.domain.Search_Missed;
import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Ticket_Reply;


/**
 * test cases for BlogDao
 * 
 * @author cherie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogDaoTest extends AbstractDbunit {

    @Resource
    private BlogDao blogDao;
    private IDataSet        ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "blog_class", "blog_status","blog" };
        backupCustomedTable(tables);
        ds = createDataSet("blog");
    }
    
    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Blog bcondition = new Blog();
        bcondition.setBlog_id(1);
        Blog actual = (Blog) blogDao.queryObjectByCondition(bcondition);
        Blog expect = new Blog();
        expect.setTitle("测试用例题目1");
        expect.setContent("测试描述具体111111111111");
        expect.setMeta_keyword("work");
        expect.setMeta_dec("description1");
        expect.setBlog_status("10");
        expect.setClass_id("10");
        

        EntitiesHelper.assertBlogEquals(actual, expect);
    }
    
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Blog scondition = new Blog();
        List<Blog> SearchMissedList = blogDao.queryListByCondition(scondition);

        assertEquals(SearchMissedList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Blog scondition = new Blog();
        int count = blogDao.queryCountRowsByCondition(scondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Blog insertBlog = new Blog();
        insertBlog.setTitle("title");
        insertBlog.setContent("this is content");
        insertBlog.setMeta_keyword("this is keyword");
        insertBlog.setMeta_dec("this is description");
        insertBlog.setBlog_status("10");
        insertBlog.setClass_id("20");
        insertBlog.setCreated_by(1);
        
        blogDao.insert(insertBlog);
        Blog expect = (Blog) blogDao.queryObjectByCondition(insertBlog);
        System.out.println(expect.getBlog_id());
        assertNotNull(expect);

    }
    
    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Blog bcondition = new Blog();
        bcondition.setTitle("title");
        bcondition.setContent("this is content");
        bcondition.setMeta_keyword("this is keyword");
        bcondition.setMeta_dec("this is description");
        bcondition.setBlog_status("10");
        bcondition.setClass_id("20");
        bcondition.setBlog_id(1);
        blogDao.update(bcondition);

        Blog query = (Blog) blogDao.queryObjectByCondition(bcondition);
        EntitiesHelper.assertBlogEquals(query,bcondition);
    }
    
    @Test
    public void testUpdateStatus() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Blog bcondition = new Blog();
        bcondition.setBlog_id(1);
        bcondition.setBlog_status("20");
        bcondition.setModified_by(3);
        blogDao.updateStatus(bcondition);

        Blog query = (Blog) blogDao.queryObjectByCondition(bcondition);
        assertEquals(query.getBlog_status(), bcondition.getBlog_status());
        assertEquals(query.getModified_by(), bcondition.getModified_by());
    }

    
    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }
}
