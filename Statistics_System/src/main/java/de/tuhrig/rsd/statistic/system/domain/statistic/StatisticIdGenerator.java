package de.tuhrig.rsd.statistic.system.domain.statistic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class StatisticIdGenerator {

    private static Integer sequenceNumber = 1;

    public static StatisticId createNew() {

        StringJoiner joiner = new StringJoiner("-", "", "");

        joiner.add(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        joiner.add("R");
        joiner.add(("00000" + sequenceNumber).substring(String.valueOf(sequenceNumber).length()));

        sequenceNumber++;

        return new StatisticId(joiner.toString());
    }
}
