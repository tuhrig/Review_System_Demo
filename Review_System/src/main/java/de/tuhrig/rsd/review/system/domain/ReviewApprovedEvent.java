package de.tuhrig.rsd.review.system.domain;

import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewApprovedEvent implements DomainEvent {
    private String reviewId;
}