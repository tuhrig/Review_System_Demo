package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository.
 *
 * @see "http://docs.spring.io/spring-data/jpa/docs/current/reference/html"
 */
public interface StatisticSpringDataRepository extends CrudRepository<StatisticEntity, String> {

    int countByReviewStatus(ReviewStatus reviewStatus);
}