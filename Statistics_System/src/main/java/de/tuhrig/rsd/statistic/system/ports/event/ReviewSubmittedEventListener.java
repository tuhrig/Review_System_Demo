package de.tuhrig.rsd.statistic.system.ports.event;

import de.tuhrig.rsd.statistic.system.application.ReviewStatisticsService;
import de.tuhrig.rsd.statistic.system.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ReviewSubmittedEventListener {

    private ReviewStatisticsService reviewStatisticsService;

    @JmsListener(
            destination = "Consumer.statistics_system.VirtualTopic.Events",
            selector = "_type = 'REVIEW_SUBMITTED_EVENT'"
    )
    public void onEvent(ReviewSubmittedEvent event) {
        reviewStatisticsService.reviewWasProcessed(ReviewStatus.OPEN);
        log.info("Review has been submitted. [reviewId={}]", event.getReviewId());
    }
}