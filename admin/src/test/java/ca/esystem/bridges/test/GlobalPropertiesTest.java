package ca.esystem.bridges.test;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/application-config.xml")
public class GlobalPropertiesTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "GlobalProperties")
    private Properties   globalproperties;

    @Test
    public void testProperties() {
        String documentrootLinux = globalproperties.getProperty("DocumentRoot.Linux");
        String documentrootWindows = globalproperties.getProperty("DocumentRoot.Linux");
        System.out.println("***documentrootLinux=" + documentrootLinux);
        System.out.println("***documentrootWindows=" + documentrootWindows);

        assertNotNull(documentrootWindows);
    }
}
