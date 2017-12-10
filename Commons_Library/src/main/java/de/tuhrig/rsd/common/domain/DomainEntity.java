package de.tuhrig.rsd.common.domain;

/**
 * This class marks a domain entity. A domain entity is a main object
 * in our domain which is identified by its unique ID. This means that
 * is has an identity. It changes its state based on business logic
 * and publishes domain events.
 */

public interface DomainEntity<T extends DomainEntityId> {

    /**
     * Returns the unique ID of the domain entity. Two domain entities
     * with the same ID are considered to be equal.
     *
     * @return The ID of the domain entity
     */
    T getId();
}
