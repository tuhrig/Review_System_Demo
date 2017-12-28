package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.review.system.application.ReviewCheckingResultService;
import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewFixtures;
import de.tuhrig.rsd.review.system.domain.ReviewRejectedEvent;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import de.tuhrig.rsd.review.system.infrastructure.memory.MessageStubRepository;
import de.tuhrig.rsd.review.system.infrastructure.memory.ReviewStubRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ReviewRejectedEventListener.class,
        ReviewCheckingResultService.class,
        ReviewStubRepository.class,
        MessageStubRepository.class,
        JacksonAutoConfiguration.class
})
public class ReviewRejectedEventListenerTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewRejectedEventListener reviewRejectedEventListener;

    @Test
    public void should_RejectReview() {

        Review openFiveStarSmartphoneReview = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        reviewRepository.save(openFiveStarSmartphoneReview);

        ReviewRejectedEvent event = new ReviewRejectedEvent();
        event.setReviewId(openFiveStarSmartphoneReview.getReviewId().getReviewId());

        reviewRejectedEventListener.onMessage(event);

        Review review = reviewRepository.find(openFiveStarSmartphoneReview.getReviewId());
        assertThat(review.getReviewStatus()).isEqualTo(ReviewStatus.REJECTED);
    }
}