package de.tuhrig.rsd.statistic.system.domain.statistic;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;

public interface StatisticRepository {
    void save(Statistic review);
    int countByReviewStatus(ReviewStatus open);
}
