package de.tuhrig.rsd.review.system.domain;

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
public class ReviewId implements DomainEntityId, Serializable {

    private static Integer sequenceNumber = 1;
    private String reviewId;

    public static ReviewId createNew() {

        StringJoiner joiner = new StringJoiner("-", "", "");

        joiner.add(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        joiner.add("R");
        joiner.add(("00000" + sequenceNumber).substring(String.valueOf(sequenceNumber).length()));

        sequenceNumber++;

        return new ReviewId(joiner.toString());
    }

    public ReviewId(String reviewId) {
        if (!reviewId.matches("2([0-9]{7})-R-\\d{5}")) {
            throw new IllegalArgumentException("Review id must have format YYYYMMDD-R-XXXXX, but was: " + reviewId);
        }
        this.reviewId = reviewId;
    }

    @Override
    @JsonValue
    public String toString() {
        return reviewId;
    }
}
