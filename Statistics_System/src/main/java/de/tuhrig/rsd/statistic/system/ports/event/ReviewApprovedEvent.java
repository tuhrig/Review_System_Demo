package de.tuhrig.rsd.statistic.system.ports.event;

import lombok.Data;

@Data
public class ReviewApprovedEvent {
    private String reviewId;
}