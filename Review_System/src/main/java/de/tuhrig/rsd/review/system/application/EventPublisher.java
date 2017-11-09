package de.tuhrig.rsd.review.system.application;

/**
 * Service to publish domain events. Any other application
 * can listen for those events in order to perform some action.
 * Note that an event can be consumed by multiple listeners.
 */
public interface EventPublisher {
    void publish(Object event);
}
