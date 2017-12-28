package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.application.CheckingService;
import de.tuhrig.rsd.checking.system.domain.Check;
import de.tuhrig.rsd.checking.system.domain.ReviewId;
import de.tuhrig.rsd.checking.system.domain.ReviewSubmittedEvent;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewSubmittedEventListener {

    private CheckingService checkingService;

    @JmsListener(
            destination = "Consumer.checking_system.VirtualTopic.Events",
            selector = "_type = 'ReviewSubmittedEvent'"
    )
    public void onEvent(ReviewSubmittedEvent reviewSubmittedEvent) {
        Check check = new Check(
                new ReviewId(reviewSubmittedEvent.getReviewId()),
                reviewSubmittedEvent.getSubject(),
                reviewSubmittedEvent.getContent()
        );
        checkingService.checkReview(check);
    }
}
