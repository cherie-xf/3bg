package ca.esystem.bridges.service;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.esystem.bridges.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends ServiceBaseTest {

    @Resource
    private UserService userService;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void before() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAdd() {
        String email = "testEmail", password = "password", phone = "123456";

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        Integer userId = (Integer) userService.add(user);
        //userService.delete(userId);

        assertNotNull(userId);
    }
}
