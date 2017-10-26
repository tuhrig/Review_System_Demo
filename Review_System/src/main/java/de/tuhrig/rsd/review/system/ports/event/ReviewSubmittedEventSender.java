package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.review.system.application.EventPublisher;
import de.tuhrig.rsd.review.system.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewSubmittedEventSender {

    private EventPublisher eventPublisher;

    public void reviewSubmitted(Review review) {

        ReviewSubmittedEvent reviewSubmittedEvent = new ReviewSubmittedEvent();
        reviewSubmittedEvent.setSubject(review.getSubject());
        reviewSubmittedEvent.setContent(review.getContent());
        reviewSubmittedEvent.setRating(review.getRating());
        reviewSubmittedEvent.setReviewId(review.getReviewId());

        eventPublisher.publish(reviewSubmittedEvent);
    }
}
