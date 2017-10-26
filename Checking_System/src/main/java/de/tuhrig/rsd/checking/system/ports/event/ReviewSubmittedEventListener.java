package de.tuhrig.rsd.checking.system.ports.event;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewSubmittedEventListener {

    @JmsListener(
            destination = "Consumer.checking_system.VirtualTopic.Events",
            selector = "_type = 'REVIEW_REJECTED_EVENT'"
    )
    public void onEvent(ReviewSubmittedEvent reviewSubmittedEvent) {
        // TODO
    }
}
