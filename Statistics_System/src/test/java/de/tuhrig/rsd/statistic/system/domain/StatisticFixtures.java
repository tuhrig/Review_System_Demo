package de.tuhrig.rsd.statistic.system.domain;

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
