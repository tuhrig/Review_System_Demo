package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter that protects the domain layer from Spring Data specific implementations.
 */
@Component
@AllArgsConstructor
public class ReviewRepositoryAdapter implements ReviewRepository {

    private final ReviewSpringDataRepository springDataRepository;
    private final ReviewEntityMapper reviewEntityMapper;

    @Override
    public void save(Review review) {
        ReviewEntity reviewEntity = reviewEntityMapper.fromDomain(review);
        springDataRepository.save(reviewEntity);
    }

    @Override
    public List<Review> findAllByStatus(ReviewStatus reviewStatus) {
        List<ReviewEntity> reviewEntities = springDataRepository.findAllByReviewStatus(reviewStatus);
        return reviewEntities
                .stream()
                .map(reviewEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Review find(ReviewId reviewId) {
        ReviewEntity reviewEntity =  springDataRepository.findOne(reviewId.getReviewId());
        return reviewEntityMapper.toDomain(reviewEntity);
    }
}
