package de.tuhrig.rsd.review.system.ports.web;

import lombok.Data;

@Data
public class ReviewResource {
    private String reviewId;
    private String subject;
    private String content;
    private int rating;
}
