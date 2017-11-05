package de.tuhrig.rsd.statistic.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class StatisticIdTest {

    @Test
    public void should_CreateNewReviewId() throws Exception {
        StatisticId reviewId = StatisticId.createNew();
        assertThat(reviewId.getStatisticId()).isNotNull();
    }

    @Test
    public void should_CreateNewReviewId_ByPassingString() throws Exception {
        StatisticId reviewId = new StatisticId("20170101-R-00001");
        assertThat(reviewId.getStatisticId()).isEqualTo("20170101-R-00001");
    }

    @Test
    public void should_ThrowException_ForInvalidReviewId() throws Exception {
        Throwable throwable = catchThrowable(() -> new StatisticId("20170101-00001"));// Missing "-R-"!
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Statistic id must have format YYYYMMDD-R-XXXXX, but was: 20170101-00001");
    }

    @Test
    public void should_ByEqual_IfStringValueIsEqual() throws Exception {
        StatisticId reviewId1 = new StatisticId("20170101-R-00001");
        StatisticId reviewId2 = new StatisticId("20170101-R-00001");
        assertThat(reviewId1).isEqualTo(reviewId2);
    }

    @Test
    public void toString_Should_ReturnStringValue() throws Exception {
        StatisticId reviewId = new StatisticId("20170101-R-00001");
        assertThat(reviewId.toString()).isEqualTo("20170101-R-00001");
    }
}