package ca.esystem.bridges.service;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = "file:src/test/service-test-config.xml")
public class ServiceBaseTest {

    public ServiceBaseTest() {

    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }
}
