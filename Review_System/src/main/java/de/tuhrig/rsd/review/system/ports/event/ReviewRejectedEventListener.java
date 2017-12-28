package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.common.application.MessageHandler;
import de.tuhrig.rsd.review.system.application.ReviewCheckingResultService;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import de.tuhrig.rsd.review.system.domain.ReviewRejectedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewRejectedEventListener extends MessageHandler<ReviewRejectedEvent> {

    private ReviewCheckingResultService reviewCheckingResultService;

    @Override
    public void onMessage(ReviewRejectedEvent reviewRejectedEvent) {
        ReviewId reviewId = new ReviewId(reviewRejectedEvent.getReviewId());
        reviewCheckingResultService.reject(reviewId);
    }
}