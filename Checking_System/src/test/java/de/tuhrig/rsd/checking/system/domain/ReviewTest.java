package de.tuhrig.rsd.checking.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ReviewTest {

    @Test
    public void should_ApproveValidReview() throws Exception {
        Check validReview = ReviewFixtures.validReview();
        validReview.check();
        assertThat(validReview.isApproved()).isTrue();
    }

    @Test
    public void should_RejectReview_WithInappropriateContent() throws Exception {
        Check validReview = ReviewFixtures.inappropriateReview();
        validReview.check();
        assertThat(validReview.isApproved()).isFalse();
    }

    @Test
    public void should_SetRejectingReason_ForInvalidReview() throws Exception {
        Check validReview = ReviewFixtures.inappropriateReview();
        validReview.check();
        assertThat(validReview.getRejectionReason()).isEqualTo("Your review contains inappropriate content");
    }
}