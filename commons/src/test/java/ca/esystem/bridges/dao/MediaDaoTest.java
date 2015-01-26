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

import ca.esystem.bridges.domain.Media;
 
/**
 * test cases for mediaDao
 * 
 * @author cherie
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MediaDaoTest extends AbstractDbunit {
	
	@Resource
	private MediaDao mediaDao;
    private IDataSet        ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","media" };
        backupCustomedTable(tables);
        ds = createDataSet("media");
    }
	
    @Test
    public void testQueryObjectByCondition() throws DatabaseUnitException, SQLException {

        // dbUnit will find the table through the table name of the .xml file in ds
        Assert.assertNotNull("the dbunitCon  is not exist", dbunitCon);
        Assert.assertNotNull("the IDataSet is not exist", ds);

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

        Media mcondition = new Media();
        mcondition.setMedia_id(1);
        Media actual = (Media) mediaDao.queryObjectByCondition(mcondition);
        Media expect = new Media();
        expect.setTitle("this is title");
        expect.setThumb("this is thumb");
        expect.setDescription("this is description");
        expect.setExt_name("1");
        expect.setBigpic("this is bigpic");

        EntitiesHelper.assertMediaEquals(actual, expect);;
    }
    
    @Test
    public void testQueryListByCondition() throws DatabaseUnitException, SQLException {

        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Media mcondition = new Media();
        List<Media> SearchMissedList = mediaDao.queryListByCondition(mcondition);

        assertEquals(SearchMissedList.size(), 2);

    }

    @Test
    public void testQueryCountRowsByCondition() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Media mcondition = new Media();
        int count = mediaDao.queryCountRowsByCondition(mcondition);
        assertEquals(count, 2);

    }

    @Test
    public void testInsert() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Media insertMedia = new Media();
        
        insertMedia.setTitle("insert title");
        insertMedia.setThumb("insert thumb");
        insertMedia.setDescription("insert description");
        insertMedia.setExt_name("3");
        insertMedia.setBigpic("insert bigpic");
        mediaDao.insert(insertMedia);
        Media expect = (Media) mediaDao.queryObjectByCondition(insertMedia);
        assertNotNull(expect);

    }
    @Test
    public void testDelete() throws DatabaseUnitException, SQLException
    {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Media mcondition = new Media();
        mcondition.setMedia_id(1);
        mediaDao.delete(mcondition);
       /* Media query = (Media) mediaDao.queryObjectByCondition(mcondition);
        assertNull(query);*/
        int count = mediaDao.queryCountRowsByCondition(new Media());
        assertEquals(count,1);
    }
    
    @Test
    public void testUpdate() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        Media mcondition = new Media();
        mcondition.setMedia_id(1);
        mcondition.setTitle("update title");
        mcondition.setDescription("update description");
        mcondition.setModified_by(2);
        
        mediaDao.update(mcondition);

        Media query = (Media) mediaDao.queryObjectByCondition(mcondition);
        assertNotNull(query);
        assertEquals(query.getTitle(), mcondition.getTitle());
        assertEquals(query.getDescription(), mcondition.getDescription());
        
        
    }
    
	/*@Test
	public void testSelectList(){
		Media mediaquery = new Media();
		mediaquery.getPagination().setCurrentPage(1);
		//`Mediaquery.setTitle("阿猫");
		List<Media> Medialist = mediaDao.queryListByCondition(mediaquery);
		assertNotNull(Medialist);
	}
	
	@Test
	public void testInsertMedia(){
		Media media = new Media();
		media.setThumb("/thumb/howareyou.jpg");
		media.setBigpic("/bigpic/howareyou.jpg");
		media.setTitle("John 3:10");
		media.setExt_name("jpg");
		media.setDescription("Scriadfadfafafafafasfasfasfpt");
		media.setCreated_by(1);
		mediaDao.insert(media);
		int pk = media.getMedia_id();
		assertTrue(pk>0);
	}
	
	   @Test
	    public void testUpdateMedia(){
	        Media media = new Media();
	        media.setTitle("pic update test");
	        media.setDescription("qwerrr");
	        media.setModified_at(new Date());
	        media.setModified_by(1);
	        media.setMedia_id(1);
	        int i = mediaDao.update(media);
	        
	        assertTrue(i>0);
	    }*/
}
