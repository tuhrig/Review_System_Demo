package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Adapter that protects the domain layer from Spring Data specific implementations.
 */
@Component
@AllArgsConstructor
public class ReviewRepositoryAdapter implements ReviewRepository {

    private final ReviewSpringDataRepository springDataRepository;

    @Override
    public void save(Review review) {
        springDataRepository.save(review);
    }

    @Override
    public List<Review> findAllByStatus(ReviewStatus reviewStatus) {
        return springDataRepository.findAllByReviewStatus(reviewStatus);
    }

    @Override
    public Review find(ReviewId reviewId) {
        return springDataRepository.findOne(reviewId);
    }
}
