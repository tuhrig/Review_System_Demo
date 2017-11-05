package de.tuhrig.rsd.statistic.system.application;

import de.tuhrig.rsd.statistic.system.domain.ReviewStatus;
import de.tuhrig.rsd.statistic.system.domain.Statistic;
import de.tuhrig.rsd.statistic.system.domain.StatisticRepository;
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
