package de.tuhrig.rsd.common.application;

import de.tuhrig.rsd.common.domain.DomainEvent;

import java.util.List;

/**
 * Service to publish domain events. Any other application
 * can listen for those events in order to perform some action.
 * Note that an event can be consumed by multiple listeners.
 */
public interface EventPublisher {

    void publish(DomainEvent event);

    default void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }
}
