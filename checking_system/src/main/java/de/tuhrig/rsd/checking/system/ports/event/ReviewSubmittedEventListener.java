package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.application.CheckingService;
import de.tuhrig.rsd.checking.system.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewSubmittedEventListener {

    private CheckingService checkingService;

    @JmsListener(
            destination = "Consumer.checking_system.VirtualTopic.Events",
            selector = "_type = 'REVIEW_SUBMITTED_EVENT'"
    )
    public void onEvent(ReviewSubmittedEvent reviewSubmittedEvent) {
        Review review = new Review(
                reviewSubmittedEvent.getReviewId(),
                reviewSubmittedEvent.getSubject(),
                reviewSubmittedEvent.getContent()
        );
        checkingService.checkReview(review);
    }
}
