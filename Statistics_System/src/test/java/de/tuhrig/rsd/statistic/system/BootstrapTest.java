package de.tuhrig.rsd.statistic.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This test will run the complete Spring Boot application. It
 * will make sure that all beans and configurations are found
 * and that the application will start.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BootstrapTest {

    @Test
    public void should_StartSpringBootApplication() throws Exception {
        // empty as only the start up is tested
    }
}
