package de.tuhrig.rsd.checking.system.infrastructure.jms;

import de.tuhrig.rsd.checking.system.application.EventPublisher;
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
    public void publish(Object event) {
        jmsTemplate.convertAndSend(destination, event);
        log.trace("Sent event. [destination={}, event={}]", destination, event);
    }
}
