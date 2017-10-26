package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.review.system.application.EventPublisher;
import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewFixtures;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewSubmittedEventSenderTest {

    @Mock
    private EventPublisher eventPublisherMock;

    @InjectMocks
    private ReviewSubmittedEventSender reviewSubmittedEventSender;

    @Test
    public void should_SendReviewSubmittedEvent() throws Exception {
        Review review = ReviewFixtures.anOpenFiveStarSmartphoneReview();

        reviewSubmittedEventSender.reviewSubmitted(review);

        ArgumentCaptor<ReviewSubmittedEvent> captor = ArgumentCaptor.forClass(ReviewSubmittedEvent.class);
        verify(eventPublisherMock).publish(captor.capture());
        ReviewSubmittedEvent event = captor.getValue();

        assertThat(event.getReviewId()).isEqualTo(review.getReviewId());
        assertThat(event.getSubject()).isEqualTo(review.getSubject());
        assertThat(event.getContent()).isEqualTo(review.getContent());
        assertThat(event.getRating()).isEqualTo(review.getRating());
    }
}