package de.tuhrig.rsd.statistic.system.domain.statistic;

import de.tuhrig.rsd.common.domain.DomainEntityId;
import lombok.Value;

@Value
public class StatisticId implements DomainEntityId {

    private String statisticId;

    public static StatisticId createNew() {
        return StatisticIdGenerator.createNew();
    }

    public StatisticId(String statisticId) {
        if (!statisticId.matches("2([0-9]{7})-R-\\d{5}")) {
            throw new IllegalArgumentException("Statistic id must have format YYYYMMDD-R-XXXXX, but was: " + statisticId);
        }
        this.statisticId = statisticId;
    }

    @Override
    public String toString() {
        return statisticId;
    }

    @Override
    public String asStringValue() {
        return statisticId;
    }
}
