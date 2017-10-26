package de.tuhrig.rsd.review.system.ports.web;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewListController {

    private ReviewRepository reviewRepository;

    @RequestMapping(path = "/approved", method = RequestMethod.GET)
    public List<Review> getApprovedReviews() {
        return reviewRepository.findAllByStatus(ReviewStatus.APPROVED);
    }
}