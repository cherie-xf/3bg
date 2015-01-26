package ca.esystem.bridges.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.User;
 
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest extends AbstractDbunit {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	private UserDao userDao;
	
	@Test
	public void testSelectList(){
		User userquery = new User();
		List<User> Userlist = userDao.queryListByCondition(userquery);
		assertNotNull(Userlist);
		logger.debug("Userlist size is:"+Userlist.size());
	}

   @Test
    public void testUpdateUser(){
        User user = new User();
        user.setEmail("jsnliang@gmail.com");
        user.setPreferredName("Super man");
        user.setFirstName("Tony");
        user.setLastName("Zhang");
        user.setPhone("6049998888");
        user.setEnabled(false);
        user.setAccountExpired(new Date());
        user.setId(1);
        user.setUser_type(1);
        userDao.insert(user);
        
        int i = userDao.update(user);
        
        assertTrue(i>0);
    }
}
