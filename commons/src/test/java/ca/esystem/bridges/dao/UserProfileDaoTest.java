package ca.esystem.bridges.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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

import ca.esystem.bridges.domain.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileDaoTest extends AbstractDbunit {
    
    @Resource
    private UserProfileDao userProfileDao;

    private IDataSet  ds;

    @Before
    public void setUp() throws DataSetException, IOException, Exception {

        String[] tables = new String[] { "user","user_profile"};
        backupCustomedTable(tables);
        ds = createDataSet("user_profile");
    }
    
    @Test
    public void testInsetUserProfile() throws DatabaseUnitException, SQLException {
        
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(2);
        userProfile.setIconUrl("/icon/2/profile.jpg");
        userProfile.setDescription("A hero from China");
        userProfile.setGender("M");
        userProfile.setBirthday(new Date());
        userProfile.setBloodType("O");
        userProfile.setPreferredLanguage("20CAN");
        userProfile.setPassportNumber("G123456789");
        userProfile.setChineseIdNumber("123456789123");
        userProfile.setDriverLicense("63259874");
        userProfile.setProfession("adjfladjfladfjaldjflasjfasdf  adsfasfadf adf");
        userProfile.setHealth("very good condition");
        userProfile.setFamilyInfo("Three kids and a wife");
        userProfile.setCarInfo("two brand new car");
        userProfile.setPetInfo("a cute dog");
        userProfile.setPropertyInfo("two houses");
        userProfile.setNewsLetter(false);
        userProfile.setAdditional("hello");
        userProfile.setCreatedBy(1);
        userProfile.setCreatedAt(new Date());
        userProfileDao.insert(userProfile);
    }
    
    @Test
    public void testUpdateUserProfile() throws DatabaseUnitException, SQLException {
        
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(1);
        userProfile.setIconUrl("/icon/2/haha.jpg");
        userProfile.setDescription("A hero from Canada");
        userProfile.setGender("F");
        userProfile.setBirthday(new Date());
        userProfile.setBloodType("B");
        userProfile.setPreferredLanguage("20CAN");
        userProfile.setPassportNumber("G99999");
        userProfile.setChineseIdNumber("987654321");
        userProfile.setDriverLicense("63259874");
        userProfile.setProfession("bbbbbbbbbbb");
        userProfile.setHealth("very good condition,lala");
        userProfile.setFamilyInfo("four kids and a wife");
        userProfile.setCarInfo("three brand new car");
        userProfile.setPetInfo("2 cute dog");
        userProfile.setPropertyInfo("two houses");
        userProfile.setNewsLetter(false);
        userProfile.setAdditional("aaaaaaaaaaaaaaaa");
        userProfile.setModifiedBy(2);
        userProfile.setModifiedAt(new Date());
        userProfileDao.update(userProfile);
    }
    
    @After
    public void tearDown() throws IOException, DatabaseUnitException, Exception {
        resumeTable();
    }    
}
