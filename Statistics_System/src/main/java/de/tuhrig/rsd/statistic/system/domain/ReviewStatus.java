package de.tuhrig.rsd.statistic.system.domain;

public enum ReviewStatus {

    /**
     * After a review was submitted by the customer, it will be OPEN.
     * This means that the review was received, but not yet approved
     * or rejected.
     */
    OPEN,

    /**
     * The review send by the customer has been approved. This means
     * that it can be displayed to other customers. It doesn't include
     * any inappropriate language.
     */
    APPROVED,

    /**
     * The review was rejected. This means it will not be displayed to
     * other customers. It might contain inappropriate language or is
     * invalid for some other reason.
     */
    REJECTED
}
