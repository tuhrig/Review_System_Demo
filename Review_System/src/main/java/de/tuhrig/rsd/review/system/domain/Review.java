package de.tuhrig.rsd.review.system.domain;

import de.tuhrig.rsd.common.domain.DomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@Slf4j
@EqualsAndHashCode(of = "reviewId")
@AllArgsConstructor(access = AccessLevel.PRIVATE) // For the builder!
public class Review implements DomainEntity<ReviewId> {

    private ReviewId reviewId;
    private ReviewStatus reviewStatus;
    private String subject;
    private String content;
    private Rating rating;

    public static Review createReview(CreateReviewCommand createReviewCommand) {
        return new Review(
                createReviewCommand.getSubject(),
                createReviewCommand.getContent(),
                new Rating(createReviewCommand.getRating())
        );
    }

    public Review(String subject, String content, Rating rating) {

        this.reviewId = ReviewId.createNew();
        this.reviewStatus = ReviewStatus.OPEN;
        this.subject = subject;
        this.content = content;
        this.rating = rating;

        log.info("Review created. [reviewId={}]", reviewId);

        ReviewSubmittedEvent event = new ReviewSubmittedEvent();
        event.setReviewId(reviewId.asStringValue());
        event.setSubject(subject);
        event.setContent(content);
        event.setRating(rating.getRating());
        raise(event);
    }

    public void approve() {
        if(reviewStatus != ReviewStatus.OPEN) {
            throw new IllegalStateException("Cannot approve review in state '" + reviewStatus + "', must be: " + ReviewStatus.OPEN);
        }
        reviewStatus = ReviewStatus.APPROVED;
        log.info("Review approved. [reviewId={}]", reviewId);
    }

    public void reject() {
        if(reviewStatus != ReviewStatus.OPEN) {
            throw new IllegalStateException("Cannot reject review in state '" + reviewStatus + "', must be: " + ReviewStatus.OPEN);
        }
        reviewStatus = ReviewStatus.REJECTED;
        log.info("Review rejected. [reviewId={}]", reviewId);
    }

    @Override
    public ReviewId getId() {
        return reviewId;
    }
}
