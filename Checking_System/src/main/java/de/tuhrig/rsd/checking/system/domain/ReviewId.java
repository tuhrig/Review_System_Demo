package de.tuhrig.rsd.checking.system.domain;

import de.tuhrig.rsd.common.domain.DomainEntityId;
import lombok.Value;

@Value
public class ReviewId implements DomainEntityId {

    private final String reviewId;

    public ReviewId(String reviewId) {
        if (!reviewId.matches("2([0-9]{7})-R-\\d{5}")) {
            throw new IllegalArgumentException("Review id must have format YYYYMMDD-R-XXXXX, but was: " + reviewId);
        }
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        return reviewId;
    }

    @Override
    public String asStringValue() {
        return reviewId;
    }
}
