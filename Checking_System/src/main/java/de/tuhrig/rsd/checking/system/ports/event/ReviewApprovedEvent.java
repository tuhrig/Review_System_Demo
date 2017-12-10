package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.domain.ReviewId;
import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewApprovedEvent implements DomainEvent {
    private ReviewId reviewId;
}