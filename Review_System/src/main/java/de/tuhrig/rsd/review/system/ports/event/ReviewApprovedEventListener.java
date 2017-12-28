package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.common.application.MessageHandler;
import de.tuhrig.rsd.review.system.application.ReviewCheckingResultService;
import de.tuhrig.rsd.review.system.domain.ReviewApprovedEvent;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewApprovedEventListener extends MessageHandler<ReviewApprovedEvent> {

    private ReviewCheckingResultService reviewCheckingResultService;

    @Override
    public void onMessage(ReviewApprovedEvent reviewApprovedEvent) {
        ReviewId reviewId = new ReviewId(reviewApprovedEvent.getReviewId());
        reviewCheckingResultService.approve(reviewId);
    }
}