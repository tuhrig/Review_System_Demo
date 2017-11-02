package de.tuhrig.rsd.review.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Jackson mapper default!
@EqualsAndHashCode(of = "reviewId")
@Entity
public class Review {

    @EmbeddedId
    private ReviewId reviewId;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus reviewStatus = ReviewStatus.INITIALIZED;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Embedded
    @Column(nullable = false)
    private Rating rating;

    @CreatedDate
    @Column(updatable = false)
    // This field is private as it should only be used for auditing. Don't use it for
    // any application or business logic.
    private Date createdDate;

    @LastModifiedDate
    // This field is private as it should only be used for auditing. Don't use it for
    // any application or business logic.
    private Date lastModifiedDate;

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
