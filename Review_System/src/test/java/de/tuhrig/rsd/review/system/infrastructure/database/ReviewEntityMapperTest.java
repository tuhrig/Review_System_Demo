package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.Rating;
import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ReviewEntityMapperTest {

    private ReviewEntityMapper reviewEntityMapper = new ReviewEntityMapper();

    @Test
    public void should_ReturnNull_IfNullIsPassed_WhenMappingToDomain() {
        Review result = reviewEntityMapper.toDomain(null);
        assertThat(result).isNull();
    }

    @Test
    public void should_ReturnNull_IfNullIsPassed_WhenMappingFromDomain() {
        ReviewEntity result = reviewEntityMapper.fromDomain(null);
        assertThat(result).isNull();
    }

    @Test
    public void should_MapToDomain() {

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewId("20170101-R-00001");
        reviewEntity.setSubject("My review");
        reviewEntity.setContent("Some content");
        reviewEntity.setRating(2);
        reviewEntity.setReviewStatus(ReviewStatus.APPROVED);

        Review review = reviewEntityMapper.toDomain(reviewEntity);

        assertThat(review.getReviewId().getReviewId()).isEqualTo("20170101-R-00001");
        assertThat(review.getSubject()).isEqualTo("My review");
        assertThat(review.getContent()).isEqualTo("Some content");
        assertThat(review.getRating().getRating()).isEqualTo(2);
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
    }

    @Test
    public void should_MapFromDomain() {

        Review review = Review.builder()
                              .reviewId(new ReviewId("20170101-R-00001"))
                              .subject("My review")
                              .content("Some content")
                              .rating(new Rating(2))
                              .reviewStatus(ReviewStatus.APPROVED)
                              .build();

        ReviewEntity reviewEntity = reviewEntityMapper.fromDomain(review);

        assertThat(reviewEntity.getReviewId()).isEqualTo("20170101-R-00001");
        assertThat(reviewEntity.getSubject()).isEqualTo("My review");
        assertThat(reviewEntity.getContent()).isEqualTo("Some content");
        assertThat(reviewEntity.getRating()).isEqualTo(2);
        assertThat(reviewEntity.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
    }
}