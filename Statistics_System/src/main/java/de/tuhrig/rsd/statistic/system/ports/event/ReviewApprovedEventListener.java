package de.tuhrig.rsd.statistic.system.ports.event;

import de.tuhrig.rsd.statistic.system.application.ReviewStatisticsService;
import de.tuhrig.rsd.statistic.system.domain.review.ReviewApprovedEvent;
import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ReviewApprovedEventListener {

    private ReviewStatisticsService reviewStatisticsService;

    @JmsListener(
            destination = "Consumer.statistics_system.VirtualTopic.Events",
            selector = "_type = 'REVIEW_APPROVED_EVENT'"
    )
    public void onEvent(ReviewApprovedEvent event) {
        reviewStatisticsService.reviewWasProcessed(ReviewStatus.APPROVED);
        log.info("Review has been approved. [reviewId={}]", event.getReviewId());
    }
}