package de.tuhrig.rsd.review.system.application;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewCheckingResultService {

    private ReviewRepository reviewRepository;

    public void approve(ReviewId reviewId) {
        Review review = reviewRepository.find(reviewId);
        review.approve();
        reviewRepository.save(review);
    }

    public void reject(ReviewId reviewId) {
        Review review = reviewRepository.find(reviewId);
        review.reject();
        reviewRepository.save(review);
    }
}
