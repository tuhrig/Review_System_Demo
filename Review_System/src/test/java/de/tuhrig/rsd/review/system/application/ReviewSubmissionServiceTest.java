package de.tuhrig.rsd.review.system.application;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewFixtures;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.ports.event.ReviewSubmittedEventSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewSubmissionServiceTest {

    @Mock
    private ReviewRepository reviewRepositoryMock;

    @Mock
    private ReviewSubmittedEventSender reviewSubmittedEventSenderMock;

    @InjectMocks
    private ReviewSubmissionService reviewSubmissionService;

    @Test
    public void should_OpenReview() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        reviewSubmissionService.submit(review);
        assertThat(review.getReviewId()).isNotNull();
    }

    @Test
    public void should_SaveReview() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        reviewSubmissionService.submit(review);
        verify(reviewRepositoryMock).save(review);
    }

    @Test
    public void should_SendSubmissionEvent() throws Exception {
        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        reviewSubmissionService.submit(review);
        verify(reviewSubmittedEventSenderMock).reviewSubmitted(review);
    }
}