package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.Rating;
import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import org.springframework.stereotype.Service;

@Service
public class ReviewEntityMapper {

    public Review toDomain(ReviewEntity reviewEntity) {

        if (reviewEntity == null) {
            return null;
        }

        return Review
                .builder()
                .reviewId(new ReviewId(reviewEntity.getReviewId()))
                .subject(reviewEntity.getSubject())
                .content(reviewEntity.getContent())
                .rating(new Rating(reviewEntity.getRating()))
                .reviewStatus(reviewEntity.getReviewStatus())
                .build();
    }

    public ReviewEntity fromDomain(Review review) {

        if (review == null) {
            return null;
        }

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewId(review.getReviewId().getReviewId());
        reviewEntity.setSubject(review.getSubject());
        reviewEntity.setContent(review.getContent());
        reviewEntity.setRating(review.getRating().getRating());
        reviewEntity.setReviewStatus(review.getReviewStatus());

        return reviewEntity;
    }
}
