package de.tuhrig.rsd.review.system.domain;

import de.tuhrig.rsd.common.domain.DomainEvent;
import lombok.Data;

@Data
public class ReviewSubmittedEvent implements DomainEvent {
    private String reviewId;
    private String subject;
    private String content;
    private int rating;
}