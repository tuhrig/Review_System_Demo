package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.Statistic;
import de.tuhrig.rsd.statistic.system.domain.StatisticId;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository.
 *
 * @see "http://docs.spring.io/spring-data/jpa/docs/current/reference/html"
 */
public interface StatisticSpringDataRepository extends CrudRepository<Statistic, StatisticId> {

    int countByReviewStatus(ReviewStatus reviewStatus);
}