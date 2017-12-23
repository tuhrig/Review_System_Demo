package de.tuhrig.rsd.review.system.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class ReviewIdGenerator {

    private static Integer sequenceNumber = 1;

    public static ReviewId createNew() {

        StringJoiner joiner = new StringJoiner("-", "", "");

        joiner.add(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        joiner.add("R");
        joiner.add(("00000" + sequenceNumber).substring(String.valueOf(sequenceNumber).length()));

        sequenceNumber++;

        return new ReviewId(joiner.toString());
    }
}
