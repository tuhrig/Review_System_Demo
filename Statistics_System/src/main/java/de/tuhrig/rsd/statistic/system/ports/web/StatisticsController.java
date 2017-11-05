package de.tuhrig.rsd.statistic.system.ports.web;

import de.tuhrig.rsd.statistic.system.domain.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.StatisticRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private StatisticRepository reviewRepository;

    @RequestMapping(path = "/approved", method = RequestMethod.GET)
    public int getNumberOfApprovedReviews() {
        return reviewRepository.countByReviewStatus(ReviewStatus.APPROVED);
    }

    @RequestMapping(path = "/rejected", method = RequestMethod.GET)
    public int getNumberOfRejectedReviews() {
        return reviewRepository.countByReviewStatus(ReviewStatus.REJECTED);
    }

    @RequestMapping(path = "/submitted", method = RequestMethod.GET)
    public int getNumberOfSubmittedReviews() {
        return reviewRepository.countByReviewStatus(ReviewStatus.OPEN);
    }
}