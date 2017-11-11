package de.tuhrig.rsd.review.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class ReviewTest {

    @Test
    public void should_OpenReview() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        review.open();
        assertThat(review.getReviewId()).isNotNull();
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.OPEN);
    }

    @Test
    public void should_ThrowException_IfReviewIsAlreadyOpened() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        review.open();

        Throwable throwable = catchThrowable(review::open); // open it twice!
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot open review which already has a state (was: 'OPEN'): " + review.getReviewId());
    }

    @Test
    public void should_ApproveReview() throws Exception {
        Review review = ReviewFixtures.anOpenFiveStarSmartphoneReview();
        review.approve();
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
    }

    @Test
    public void should_ThrowException_IfReviewCannotBeApproved() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();

        Throwable throwable = catchThrowable(review::approve);
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot approve review in state 'null', must be: OPEN");
    }

    @Test
    public void should_RejectReview() throws Exception {
        Review review = ReviewFixtures.anOpenFiveStarSmartphoneReview();
        review.reject();
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.REJECTED);
    }

    @Test
    public void should_ThrowException_IfReviewCannotBeRejected() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();

        Throwable throwable = catchThrowable(review::reject);
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot reject review in state 'null', must be: OPEN");
    }

}