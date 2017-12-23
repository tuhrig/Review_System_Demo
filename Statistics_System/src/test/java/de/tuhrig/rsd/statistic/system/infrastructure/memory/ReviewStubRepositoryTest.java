package de.tuhrig.rsd.statistic.system.infrastructure.memory;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.statistic.Statistic;
import de.tuhrig.rsd.statistic.system.domain.StatisticFixtures;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ReviewStubRepositoryTest {

    @Test
    public void should_SaveReview() throws Exception {
        StatisticStubRepository memoryReviewRepository = new StatisticStubRepository();
        Statistic statistic = StatisticFixtures.anApprovedStatistic();
        memoryReviewRepository.save(statistic);

        assertThat(memoryReviewRepository.countByReviewStatus(ReviewStatus.APPROVED)).isEqualTo(1);
    }
}