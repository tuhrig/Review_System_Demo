package de.tuhrig.rsd.review.system.infrastructure.messaging;

import de.tuhrig.rsd.review.system.infrastructure.database.PersistenceConfig;
import de.tuhrig.rsd.review.system.infrastructure.database.MessageSpringDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        PersistenceConfig.class
})
@TestPropertySource({
        "classpath:application.properties"
})

public class MessageSpringDataRepositoryTest {

    @Autowired
    private MessageSpringDataRepository messageSpringDataRepository;

    @Test
    public void name() {


    }
}