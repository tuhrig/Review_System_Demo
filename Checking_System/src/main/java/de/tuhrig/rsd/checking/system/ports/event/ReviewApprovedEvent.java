package de.tuhrig.rsd.checking.system.ports.event;

import de.tuhrig.rsd.checking.system.domain.ReviewId;
import lombok.Data;

@Data
public class ReviewApprovedEvent {
    private ReviewId reviewId;
}