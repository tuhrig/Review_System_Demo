package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.statistic.Statistic;
import de.tuhrig.rsd.statistic.system.domain.statistic.StatisticId;
import org.springframework.stereotype.Service;

@Service
public class StatisticEntityMapper {

    public Statistic toDomain(StatisticEntity statisticEntity) {

        if (statisticEntity == null) {
            return null;
        }

        return Statistic
                .builder()
                .statisticId(new StatisticId(statisticEntity.getStatisticId()))
                .reviewStatus(statisticEntity.getReviewStatus())
                .build();
    }

    public StatisticEntity fromDomain(Statistic statistic) {

        if (statistic == null) {
            return null;
        }

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId(statistic.getStatisticId().getStatisticId());
        statisticEntity.setReviewStatus(statistic.getReviewStatus());

        return statisticEntity;
    }
}
