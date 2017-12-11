package de.tuhrig.rsd.common.messaging.events;

import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewApprovedEvent implements DomainEvent {
    private String reviewId;
}