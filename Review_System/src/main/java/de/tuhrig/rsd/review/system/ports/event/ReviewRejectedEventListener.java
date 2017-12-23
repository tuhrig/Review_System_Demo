package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.review.system.application.ReviewCheckingResultService;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewRejectedEvent;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewRejectedEventListener {

    private ReviewCheckingResultService reviewCheckingResultService;

    @JmsListener(
            destination = "Consumer.review_system.VirtualTopic.Events",
            selector = "_type = 'REVIEW_REJECTED_EVENT'"
    )
    public void onEvent(ReviewRejectedEvent reviewRejectedEvent) {
        ReviewId reviewId = new ReviewId(reviewRejectedEvent.getReviewId());
        reviewCheckingResultService.reject(reviewId);
    }
}