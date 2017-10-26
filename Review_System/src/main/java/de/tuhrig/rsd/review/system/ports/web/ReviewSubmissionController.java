package de.tuhrig.rsd.review.system.ports.web;

import de.tuhrig.rsd.review.system.application.ReviewSubmissionService;
import de.tuhrig.rsd.review.system.domain.Review;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
@Slf4j
public class ReviewSubmissionController {

    private ReviewSubmissionService reviewSubmissionService;

    @RequestMapping(method = RequestMethod.POST)
    public void submit(@RequestBody Review review) {
        reviewSubmissionService.submit(review);
        log.trace("POST to /reviews. [review={}]", review);
    }
}