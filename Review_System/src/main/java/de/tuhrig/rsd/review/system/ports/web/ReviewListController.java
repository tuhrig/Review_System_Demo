package de.tuhrig.rsd.review.system.ports.web;

import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewListController {

    private ReviewRepository reviewRepository;

    @RequestMapping(path = "/approved", method = RequestMethod.GET)
    public List<ReviewResource> getApprovedReviews() {
        return reviewRepository
                .findAllByStatus(ReviewStatus.APPROVED)
                .stream()
                .map(review -> {
                    ReviewResource reviewResource = new ReviewResource();
                    reviewResource.setReviewId(review.getReviewId().getReviewId());
                    reviewResource.setSubject(review.getSubject());
                    reviewResource.setContent(review.getContent());
                    reviewResource.setRating(review.getRating().getRating());
                    return reviewResource;
                })
                .collect(toList());
    }
}