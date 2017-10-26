package de.tuhrig.rsd.checking.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ReviewTest {

    @Test
    public void should_ApproveValidReview() throws Exception {
        Review validReview = ReviewFixtures.validReview();
        validReview.check();
        assertThat(validReview.isApproved()).isTrue();
    }

    @Test
    public void should_RejectInvalidReview() throws Exception {
        Review validReview = ReviewFixtures.invalidReview();
        validReview.check();
        assertThat(validReview.isApproved()).isFalse();
    }

    @Test
    public void should_SetRejectingReason_ForInvalidReview() throws Exception {
        Review validReview = ReviewFixtures.invalidReview();
        validReview.check();
        assertThat(validReview.getRejectionReason()).isEqualTo("Content is too short");
    }
}