package de.tuhrig.rsd.statistic.system.infrastructure.memory;

import de.tuhrig.rsd.statistic.system.domain.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.Statistic;
import de.tuhrig.rsd.statistic.system.domain.StatisticRepository;

import java.util.HashSet;
import java.util.Set;

public class StatisticStubRepository implements StatisticRepository {

    private Set<Statistic> reviews = new HashSet<>();

    @Override
    public void save(Statistic review) {
        reviews.add(review);
    }

    @Override
    public int countByReviewStatus(ReviewStatus reviewStatus) {
        return (int) reviews
                        .stream()
                        .filter(review -> review.getReviewStatus() == reviewStatus)
                        .count();
    }
}