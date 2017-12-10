package de.tuhrig.rsd.review.system.infrastructure.jms;

import de.tuhrig.rsd.common.application.EventPublisher;
import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

@Slf4j
@AllArgsConstructor
public class JmsEventPublisher implements EventPublisher {

    private final JmsTemplate jmsTemplate;
    private final Destination destination;

    @Override
    public void publish(DomainEvent event) {
        jmsTemplate.convertAndSend(destination, event);
        log.trace("Sent event. [destination={}, event={}]", destination, event);
    }
}
