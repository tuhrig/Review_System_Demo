package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.domain.ReviewId;
import de.tuhrig.rsd.common.application.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewRejectedEventSender {

    private EventPublisher eventPublisher;

    public void reviewRejected(ReviewId reviewId, String checkingResult) {
        ReviewRejectedEvent reviewRejectedEvent = new ReviewRejectedEvent();
        reviewRejectedEvent.setReviewId(reviewId);
        reviewRejectedEvent.setReason(checkingResult);
        eventPublisher.publish(reviewRejectedEvent);
    }
}
