package de.tuhrig.rsd.checking.system.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Getter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Jackson mapper default!
public class Review {

    // A list of "bad words" which are considered an inappropriate language. Any
    // review containing one of these words will be rejected.
    //
    // Note that this is just an example and probably not a complete list ;)
    private static final String[] INAPPROPRIATE_CONTENT = new String[] {
            "bad", "wrong", "shit", "fuck"
    };

    private ReviewId reviewId;
    private String subject;
    private String content;
    private String rejectionReason;
    private boolean approved;

    public Review(ReviewId reviewId, String subject, String content) {
        this.reviewId = reviewId;
        this.subject = subject;
        this.content = content;
    }

    /**
     * This method will check the review to either approve or reject it.
     * To do so, it will check the content for inappropriate words which
     * might be offending for other users. If the review contains such
     * words, it will be rejected with a dedicated message/reason.
     */
    public void check() {
        boolean containsInappropriateContent = Arrays.stream(INAPPROPRIATE_CONTENT)
                                                     .anyMatch(content.toLowerCase()::contains);
        if(containsInappropriateContent) {
            reject("Your review contains inappropriate content");
        } else {
            approve();
        }
    }

    private void reject(String reason) {
        this.approved = false;
        this.rejectionReason = reason;
        log.info("Review rejected. [reviewId={}, reason={}]", reviewId, reason);
    }

    private void approve() {
        this.approved = true;
        log.info("Review approved. [reviewId={}]", reviewId);
    }
}
