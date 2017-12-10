package de.tuhrig.rsd.statistic.system.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import de.tuhrig.rsd.common.domain.DomainEntityId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Getter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Hibernate default!
public class StatisticId implements DomainEntityId, Serializable {

    private static Integer sequenceNumber = 1;
    private String statisticId;

    public static StatisticId createNew() {

        StringJoiner joiner = new StringJoiner("-", "", "");

        joiner.add(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        joiner.add("R");
        joiner.add(("00000" + sequenceNumber).substring(String.valueOf(sequenceNumber).length()));

        sequenceNumber++;

        return new StatisticId(joiner.toString());
    }

    public StatisticId(String statisticId) {
        if (!statisticId.matches("2([0-9]{7})-R-\\d{5}")) {
            throw new IllegalArgumentException("Statistic id must have format YYYYMMDD-R-XXXXX, but was: " + statisticId);
        }
        this.statisticId = statisticId;
    }

    @Override
    @JsonValue
    public String toString() {
        return statisticId;
    }
}
