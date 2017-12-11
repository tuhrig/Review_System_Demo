package de.tuhrig.rsd.checking.system.application;

import de.tuhrig.rsd.checking.system.domain.Review;
import de.tuhrig.rsd.common.application.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckingService {

    private EventPublisher eventPublisher;

    public void checkReview(Review review) {
        review.check();
        eventPublisher.publish(review.getOccurredEvents());
    }
}