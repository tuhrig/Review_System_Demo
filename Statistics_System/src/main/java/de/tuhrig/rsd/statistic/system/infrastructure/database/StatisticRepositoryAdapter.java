package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.Statistic;
import de.tuhrig.rsd.statistic.system.domain.StatisticRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adapter that protects the domain layer from Spring Data specific implementations.
 */
@Component
@AllArgsConstructor
public class StatisticRepositoryAdapter implements StatisticRepository {

    private final StatisticSpringDataRepository springDataRepository;

    @Override
    public void save(Statistic review) {
        springDataRepository.save(review);
    }

    @Override
    public int countByReviewStatus(ReviewStatus reviewStatus) {
        return springDataRepository.countByReviewStatus(reviewStatus);
    }
}
