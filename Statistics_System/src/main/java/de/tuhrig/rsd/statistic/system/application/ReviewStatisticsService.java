package de.tuhrig.rsd.statistic.system.application;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.statistic.Statistic;
import de.tuhrig.rsd.statistic.system.domain.statistic.StatisticRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewStatisticsService {

    private StatisticRepository statisticRepository;

    public void reviewWasProcessed(ReviewStatus reviewStatus) {
        Statistic statistic = new Statistic(reviewStatus);
        statisticRepository.save(statistic);
    }
}
