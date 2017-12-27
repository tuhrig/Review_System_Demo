package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.StatisticFixtures;
import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.statistic.Statistic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        PersistenceConfig.class,
        StatisticRepositoryAdapter.class,
        StatisticEntityMapper.class
})
@TestPropertySource({
        "classpath:application.properties"
})
@Transactional
public class StatisticRepositoryAdapterTest {

    @Autowired
    private StatisticRepositoryAdapter statisticRepositoryAdapter;

    @Test
    public void should_SaveStatistic() {
        Statistic statistic = StatisticFixtures.anApprovedStatistic();
        statisticRepositoryAdapter.save(statistic);
        int numberOf = statisticRepositoryAdapter.countByReviewStatus(ReviewStatus.APPROVED);
        assertThat(numberOf).isEqualTo(1);
    }
}