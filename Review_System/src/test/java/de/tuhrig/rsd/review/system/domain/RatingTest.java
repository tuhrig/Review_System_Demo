package de.tuhrig.rsd.review.system.domain;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class RatingTest {

    @Test
    public void should_CreateNewRating() throws Exception {
        Rating rating = new Rating(3);
        assertThat(rating.getRating()).isEqualTo(3);
    }

    @Test
    public void should_ThrowException_IfRatingIsTooLow() throws Exception {
        Throwable throwable = catchThrowable(() -> new Rating(-1));
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating must be between 0 and 5, but was: -1");
    }

    @Test
    public void should_ThrowException_IfRatingIsTooHigh() throws Exception {
        Throwable throwable = catchThrowable(() -> new Rating(6));
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rating must be between 0 and 5, but was: 6");
    }

    @Test
    public void should_ByEqual_IfIntegerValueIsEqual() throws Exception {
        Rating rating1 = new Rating(3);
        Rating rating2 = new Rating(3);
        assertThat(rating1).isEqualTo(rating2);
    }
}