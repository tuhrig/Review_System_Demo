package de.tuhrig.rsd.review.system.application;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.ports.event.ReviewSubmittedEventSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReviewSubmissionService {

    private ReviewRepository reviewRepository;
    private ReviewSubmittedEventSender reviewSubmittedEventSender;

    // This method is transactional as we do two things at the same time:
    // saving the review and sending a new event. If the event sending fails,
    // we also want to rollback the saved review.
    @Transactional
    public void submit(Review review) {
        review.open();
        reviewRepository.save(review);
        reviewSubmittedEventSender.reviewSubmitted(review);
    }
}