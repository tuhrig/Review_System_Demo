package de.tuhrig.rsd.checking.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class ReviewIdTest {

    @Test
    public void should_CreateNewReviewId_ByPassingString() throws Exception {
        ReviewId reviewId = new ReviewId("20170101-R-00001");
        assertThat(reviewId.getReviewId()).isEqualTo("20170101-R-00001");
    }

    @Test
    public void should_ThrowException_ForInvalidReviewId() throws Exception {
        Throwable throwable = catchThrowable(() -> new ReviewId("20170101-00001"));// Missing "-R-"!
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Review id must have format YYYYMMDD-R-XXXXX, but was: 20170101-00001");
    }

    @Test
    public void should_ByEqual_IfStringValueIsEqual() throws Exception {
        ReviewId reviewId1 = new ReviewId("20170101-R-00001");
        ReviewId reviewId2 = new ReviewId("20170101-R-00001");
        assertThat(reviewId1).isEqualTo(reviewId2);
    }

    @Test
    public void toString_Should_ReturnStringValue() throws Exception {
        ReviewId reviewId = new ReviewId("20170101-R-00001");
        assertThat(reviewId.toString()).isEqualTo("20170101-R-00001");
    }
}