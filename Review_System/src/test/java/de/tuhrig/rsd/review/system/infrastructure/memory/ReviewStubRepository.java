package de.tuhrig.rsd.review.system.infrastructure.memory;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReviewStubRepository implements ReviewRepository {

    private Set<Review> reviews = new HashSet<>();

    @Override
    public void save(Review review) {
        reviews.add(review);
    }

    @Override
    public List<Review> findAllByStatus(ReviewStatus reviewStatus) {
        return reviews
                .stream()
                .filter(review -> review.getReviewStatus() == reviewStatus)
                .collect(Collectors.toList());
    }

    @Override
    public Review find(ReviewId reviewId) {
        return reviews
                .stream()
                .filter(review -> review.getReviewId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }
}