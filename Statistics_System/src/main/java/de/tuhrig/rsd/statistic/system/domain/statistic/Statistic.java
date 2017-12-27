package de.tuhrig.rsd.statistic.system.domain.statistic;

import de.tuhrig.rsd.common.domain.DomainEntity;
import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@Slf4j
@EqualsAndHashCode(of = "statisticId")
@AllArgsConstructor(access = AccessLevel.PRIVATE) // For the builder!
public class Statistic implements DomainEntity<StatisticId> {

    private StatisticId statisticId;
    private ReviewStatus reviewStatus;

    public Statistic(ReviewStatus reviewStatus) {
        this.statisticId = StatisticId.createNew();
        this.reviewStatus = reviewStatus;
        log.info("Initialized new statistic. [statisticId={}, reviewStatus={}]", statisticId, reviewStatus);
    }

    @Override
    public StatisticId getId() {
        return statisticId;
    }
}
