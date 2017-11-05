package de.tuhrig.rsd.statistic.system.domain;

public interface StatisticRepository {
    void save(Statistic review);
    int countByReviewStatus(ReviewStatus open);
}
