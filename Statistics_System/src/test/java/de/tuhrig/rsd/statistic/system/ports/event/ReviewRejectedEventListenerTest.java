package de.tuhrig.rsd.statistic.system.ports.event;

import de.tuhrig.rsd.statistic.system.JmsUtil;
import de.tuhrig.rsd.statistic.system.application.ReviewStatisticsService;
import de.tuhrig.rsd.statistic.system.domain.review.ReviewRejectedEvent;
import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.statistic.StatisticRepository;
import de.tuhrig.rsd.statistic.system.infrastructure.jms.JmsConfig;
import de.tuhrig.rsd.statistic.system.infrastructure.memory.StatisticStubRepository;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        JmsConfig.class,
        ReviewRejectedEventListener.class,
        ReviewStatisticsService.class,
        StatisticStubRepository.class
})
@TestPropertySource({
        "classpath:application.properties"
})
public class ReviewRejectedEventListenerTest {

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void should_SaveNewStatistic() {

        jmsTemplate.convertAndSend(new ActiveMQQueue("Consumer.statistics_system.VirtualTopic.Events"), new ReviewRejectedEvent());

        JmsUtil jmsUtil = new JmsUtil(jmsTemplate);
        jmsUtil.waitForAll("Consumer.statistics_system.VirtualTopic.Events");

        int statistic = statisticRepository.countByReviewStatus(ReviewStatus.REJECTED);
        assertThat(statistic).isEqualTo(1);
    }
}