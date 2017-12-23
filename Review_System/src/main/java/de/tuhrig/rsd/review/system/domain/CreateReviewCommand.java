package de.tuhrig.rsd.review.system.domain;

import de.tuhrig.rsd.common.domain.Command;
import lombok.Data;

@Data
public class CreateReviewCommand implements Command {
    private String subject;
    private String content;
    private int rating;
}
