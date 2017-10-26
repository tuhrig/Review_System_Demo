package de.tuhrig.rsd.review.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Jackson mapper default!
@EqualsAndHashCode(of = "reviewId")
public class Review {
    private ReviewId reviewId;
    @JsonIgnore
    private ReviewStatus reviewStatus = ReviewStatus.INITIALIZED;
    private String subject;
    private String content;
    private Rating rating;

    public Review(String subject, String content, Rating rating) {
        this.subject = subject;
        this.content = content;
        this.rating = rating;
    }

    public void open() {
        if(reviewStatus != ReviewStatus.INITIALIZED) {
            throw new IllegalStateException("Review is already open: " + reviewId);
        }
        reviewId = ReviewId.createNew();
        reviewStatus = ReviewStatus.OPEN;
        log.info("Review opened. [reviewId={}]", reviewId);
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
}
