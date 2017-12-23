package de.tuhrig.rsd.statistic.system.domain.review;

import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewSubmittedEvent implements DomainEvent {
    private String reviewId;
}