package ca.esystem.bridges.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.xml.sax.InputSource;

/**
 * base class for prepare environment for dbunit test
 * 
 * @author cherie
 * 
 */
@ContextConfiguration(locations = "file:src/test/db-test-config.xml")
public class AbstractDbunit {

    private static final String       path = "./src/test/java/ca/esystem/bridges/dao/dataset/";

    public static IDatabaseConnection dbunitCon;

    private File                      tempFile;

    @Autowired
    protected BasicDataSource         dataSource;

    /**
     * get the the dbConnection
     * 
     * @throws Exception
     */
    @Before
    public void before() throws Exception {
        if (dbunitCon == null) {
            Connection conn = DataSourceUtils.getConnection(dataSource);
            dbunitCon = new DatabaseConnection(conn);
        }
    }

    /**
     * get the the dbConnection
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void init() throws Exception {

    }

    /**
     * destroy the dbConnection
     * 
     * @throws Exception
     */
    @AfterClass
    public static void destory() throws Exception {
    }

    /**
     * create the dataset
     * 
     * @param tname
     * @return
     * @throws DataSetException
     * @throws IOException
     */
    protected IDataSet createDataSet(String tname) throws DataSetException, IOException {
        // InputStream is = AbstractDbunitTestCase.class.getClassLoader().getResourceAsStream(tname + "Dataset.xml");
        // System.out.println(path + tname + "Dataset.xml");
        InputStream is = new FileInputStream(path + tname + "Dataset.xml");

        Assert.assertNotNull("the data file is not exist", is);
        //FlatXmlDataSet.write(ds, new FileWriter(tempFile) );
        return new FlatXmlDataSet(new FlatXmlProducer(new InputSource(is)));

    }

    /**
     * method for back up all of the tables in db
     * 
     * @throws DatabaseUnitException
     * @throws IOException
     * @throws Exception
     */
    protected void backupAllTable() throws DatabaseUnitException, IOException, Exception {
        IDataSet ds = dbunitCon.createDataSet();
        writeBackupFile(ds);
    }

    /**
     * method for write data xml file for backing up table; Data file: prefix :backup subfix :.xml
     * 
     * @param ds
     * @throws DataSetException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void writeBackupFile(IDataSet ds) throws DataSetException, FileNotFoundException, IOException {
        tempFile = File.createTempFile("backup", ".xml");
        FlatXmlDataSet.write(ds, new FileOutputStream(tempFile));
    }

    /**
     * method for backup customed tables
     */
    protected void backupCustomedTable(String[] tname) throws DataSetException, IOException, Exception {
        QueryDataSet ds = new QueryDataSet(dbunitCon);
        for (String str : tname) {
            ds.addTable(str);
        }
        writeBackupFile(ds);

    }

    /**
     * method for backup on table
     * 
     * @param tname
     * @throws DataSetException
     * @throws IOException
     * @throws Exception
     */
    protected void backuponeTable(String tname) throws DataSetException, IOException, Exception {
        backupCustomedTable(new String[] { tname });
    }

    /**
     * method for resume table data
     * 
     * @throws FileNotFoundException
     * @throws DatabaseUnitException
     * @throws Exception
     */
    protected void resumeTable() throws FileNotFoundException, DatabaseUnitException, Exception {
        IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(new FileInputStream(tempFile))));
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);

    }

}
