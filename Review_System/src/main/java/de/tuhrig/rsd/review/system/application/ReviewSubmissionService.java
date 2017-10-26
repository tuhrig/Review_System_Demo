package de.tuhrig.rsd.review.system.application;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.ports.event.ReviewSubmittedEventSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewSubmissionService {

    private ReviewRepository reviewRepository;
    private ReviewSubmittedEventSender reviewSubmittedEventSender;

    public void submit(Review review) {
        review.open();
        reviewRepository.save(review);
        reviewSubmittedEventSender.reviewSubmitted(review);
    }
}