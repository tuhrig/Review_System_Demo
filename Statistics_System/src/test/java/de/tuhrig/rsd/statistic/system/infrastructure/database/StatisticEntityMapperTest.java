package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.statistic.Statistic;
import de.tuhrig.rsd.statistic.system.domain.statistic.StatisticId;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StatisticEntityMapperTest {

    private StatisticEntityMapper statisticEntityMapper = new StatisticEntityMapper();

    @Test
    public void should_ReturnNull_IfNullIsPassed_WhenMappingToDomain() {
        Statistic result = statisticEntityMapper.toDomain(null);
        assertThat(result).isNull();
    }

    @Test
    public void should_ReturnNull_IfNullIsPassed_WhenMappingFromDomain() {
        StatisticEntity result = statisticEntityMapper.fromDomain(null);
        assertThat(result).isNull();
    }

    @Test
    public void should_MapToDomain() {

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId("20170101-R-00001");
        statisticEntity.setReviewStatus(ReviewStatus.APPROVED);

        Statistic statistic = statisticEntityMapper.toDomain(statisticEntity);

        assertThat(statistic.getStatisticId().getStatisticId()).isEqualTo("20170101-R-00001");
        assertThat(statistic.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
    }

    @Test
    public void should_MapFromDomain() {

        Statistic statistic = Statistic.builder()
                .statisticId(new StatisticId("20170101-R-00001"))
                .reviewStatus(ReviewStatus.APPROVED)
                .build();

        StatisticEntity statisticEntity = statisticEntityMapper.fromDomain(statistic);

        assertThat(statisticEntity.getStatisticId()).isEqualTo("20170101-R-00001");
        assertThat(statisticEntity.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
    }
}