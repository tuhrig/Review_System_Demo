package de.tuhrig.rsd.review.system.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Hibernate default!
public class Rating {

    @Column(nullable = false)
    private int rating;

    public Rating(int rating) {
        if(rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5, but was: " + rating);
        }
        this.rating = rating;
    }

    @JsonValue
    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.valueOf(rating);
    }
}
