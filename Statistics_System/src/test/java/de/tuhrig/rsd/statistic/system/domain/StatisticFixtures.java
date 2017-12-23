package de.tuhrig.rsd.statistic.system.domain;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.statistic.Statistic;

public class StatisticFixtures {

    public static Statistic anApprovedStatistic() {
        return new Statistic(ReviewStatus.APPROVED);
    }

    public static Statistic aRejectedStatistic() {
        return new Statistic(ReviewStatus.REJECTED);
    }

    public static Statistic aSubmittedStatistic() {
        return new Statistic(ReviewStatus.OPEN);
    }
}
