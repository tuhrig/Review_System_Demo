package de.tuhrig.rsd.checking.system.domain;

import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewRejectedEvent implements DomainEvent {
    private String reviewId;
    private String reason;
}