package ca.esystem.bridges.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContactDaoTest extends AbstractDbunit {

    @Resource
    private ContactDao contactDao;
    private IDataSet   ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        backupCustomedTable(new String[] { "contact", "user","contact_type" });
        ds = createDataSet("contact");

        // insert base dataset to database
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
    }

    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }

    @Test
    public void testInsertContact() throws DatabaseUnitException, SQLException {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        // the contact to be updated
        Contact contact = new Contact();
        contact.setId(2);
        contact.setUser_id(1);
        contact.setType_code("11EM");
        contact.setContact_value("tony@hotmail.com");
        contact.setModified_by(2);
        contact.setModified_at(new Date());

        // update contact
        int ret = contactDao.insert(contact);

        // read created contact from database
        Contact createdContact = (Contact) contactDao.queryObjectByCondition(contact);

        // assertion to make sure it has return
        assertTrue(ret > 0);
        // assertion to make sure it has been updated correctly for each field
        EntitiesHelper.assertContactEquals(contact, createdContact);
    }

    @Test
    public void testSelectList() {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        Contact contactquery = new Contact();
        contactquery.setUser_id(1);
        contactquery.setType_code("10EM");

        @SuppressWarnings("unchecked")
        List<Contact> contacts = (List<Contact>) contactDao.queryListByCondition(contactquery);
        assertNotNull(contacts);
        assertEquals(1, contacts.size());
    }

    @Test
    public void testUpdateContact() throws DatabaseUnitException, SQLException {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        // the contact to be updated
        Contact contact = new Contact();
        contact.setId(1);
        contact.setUser_id(1);
        contact.setType_code("11EM");
        contact.setContact_value("tony@hotmail.com");
        contact.setModified_by(2);
        contact.setModified_at(new Date());

        // update contact
        int ret = contactDao.update(contact);

        // read updated contact from database
        Contact updatedContact = (Contact) contactDao.queryObjectByCondition(contact);

        // assertion to make sure it has return
        assertTrue(ret > 0);
        // assertion to make sure it has been updated correctly for each field
        EntitiesHelper.assertContactEquals(contact, updatedContact);

    }

    @Test
    public void testDeleteContact() throws DatabaseUnitException, SQLException {

        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        // the contact to be deleted
        Contact contact = new Contact();
        contact.setId(2);

        // delete contact
        int ret = contactDao.delete(contact);

        //try {
            // read deleted contact from database
            Contact deletedContact = (Contact) contactDao.queryObjectByCondition(contact);
  
            assertNotNull(deletedContact);
            assertEquals(true, deletedContact.getIs_deleted());

            //TestCase.fail("returned removed contact");
       /* } catch (Exception e) {
            // ignore
        }*/

        // assertion to make sure it has return value
        assertTrue(ret > 0);
    }
}
