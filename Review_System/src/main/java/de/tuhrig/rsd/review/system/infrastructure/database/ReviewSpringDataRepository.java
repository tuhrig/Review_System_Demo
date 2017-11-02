package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Spring data repository.
 *
 * @see "http://docs.spring.io/spring-data/jpa/docs/current/reference/html"
 */
public interface ReviewSpringDataRepository extends CrudRepository<Review, ReviewId> {

    List<Review> findAllByReviewStatus(ReviewStatus reviewStatus);
}