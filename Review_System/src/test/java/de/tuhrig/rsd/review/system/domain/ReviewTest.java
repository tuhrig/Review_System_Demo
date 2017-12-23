package de.tuhrig.rsd.review.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class ReviewTest {

    @Test
    public void should_ApproveReview() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        review.approve();
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
    }

    @Test
    public void should_ThrowException_IfReviewCannotBeApproved() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        review.reject(); // Already rejected!

        Throwable throwable = catchThrowable(review::approve);
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot approve review in state 'REJECTED', must be: OPEN");
    }

    @Test
    public void should_RejectReview() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        review.reject();
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.REJECTED);
    }

    @Test
    public void should_ThrowException_IfReviewCannotBeRejected() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        review.approve(); // Already approved!

        Throwable throwable = catchThrowable(review::reject);
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot reject review in state 'APPROVED', must be: OPEN");
    }
}