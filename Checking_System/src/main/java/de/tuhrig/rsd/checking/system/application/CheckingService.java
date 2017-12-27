package de.tuhrig.rsd.checking.system.application;

import de.tuhrig.rsd.checking.system.domain.Check;
import de.tuhrig.rsd.common.application.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckingService {

    private EventPublisher eventPublisher;

    public void checkReview(Check check) {
        check.check();
        eventPublisher.publish(check.getOccurredEvents());
    }
}