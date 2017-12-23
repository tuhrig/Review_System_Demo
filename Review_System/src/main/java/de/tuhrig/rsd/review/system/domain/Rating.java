package de.tuhrig.rsd.review.system.domain;

import de.tuhrig.rsd.common.domain.ValueObject;
import lombok.Value;

@Value
public class Rating implements ValueObject {

    private int rating;

    public Rating(int rating) {
        if(rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5, but was: " + rating);
        }
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.valueOf(rating);
    }
}
