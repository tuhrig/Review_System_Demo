package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.domain.ReviewId;
import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewRejectedEvent implements DomainEvent {
    private ReviewId reviewId;
    private String reason;
}