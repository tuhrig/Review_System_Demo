package de.tuhrig.rsd.checking.system.application;

import de.tuhrig.rsd.checking.system.domain.Review;
import de.tuhrig.rsd.checking.system.ports.event.ReviewApprovedEventSender;
import de.tuhrig.rsd.checking.system.ports.event.ReviewRejectedEventSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckingService {

    private ReviewApprovedEventSender reviewApprovedEventSender;
    private ReviewRejectedEventSender reviewRejectedEventSender;

    public void checkReview(Review review) {
        review.check();
        if(review.isApproved()) {
            reviewApprovedEventSender.reviewApproved(review.getReviewId());
        } else {
            reviewRejectedEventSender.reviewRejected(review.getReviewId(), review.getRejectionReason());
        }
    }
}