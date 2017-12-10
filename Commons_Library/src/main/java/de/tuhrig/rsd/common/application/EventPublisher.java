package de.tuhrig.rsd.common.application;

import de.tuhrig.rsd.common.domain.DomainEvent;

/**
 * Service to publish domain events. Any other application
 * can listen for those events in order to perform some action.
 * Note that an event can be consumed by multiple listeners.
 */
public interface EventPublisher {
    void publish(DomainEvent event);
}
