package de.tuhrig.rsd.review.system.ports.event;

import de.tuhrig.rsd.review.system.domain.Rating;
import de.tuhrig.rsd.review.system.domain.ReviewId;
import lombok.Data;

@Data
public class ReviewSubmittedEvent {
    private ReviewId reviewId;
    private String subject;
    private String content;
    private Rating rating;
}