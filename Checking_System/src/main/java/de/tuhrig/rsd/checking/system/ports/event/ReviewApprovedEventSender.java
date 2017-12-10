package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.domain.ReviewId;
import de.tuhrig.rsd.common.application.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewApprovedEventSender {

    private EventPublisher eventPublisher;

    public void reviewApproved(ReviewId reviewId) {
        ReviewApprovedEvent reviewApprovedEvent = new ReviewApprovedEvent();
        reviewApprovedEvent.setReviewId(reviewId);
        eventPublisher.publish(reviewApprovedEvent);
    }
}
