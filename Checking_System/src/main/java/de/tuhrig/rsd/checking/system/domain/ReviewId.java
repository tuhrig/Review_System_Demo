package de.tuhrig.rsd.checking.system.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import de.tuhrig.rsd.common.domain.DomainEntityId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ReviewId implements DomainEntityId {

    private String reviewId;

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

    @Override
    public String asStringValue() {
        return reviewId;
    }
}
