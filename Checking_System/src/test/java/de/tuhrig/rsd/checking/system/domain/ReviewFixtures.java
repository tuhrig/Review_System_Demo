package de.tuhrig.rsd.checking.system.domain;

public class ReviewFixtures {
    public static Review validReview() {
        return new Review(
                new ReviewId("20170101-R-00001"),
                "My Review",
                "This is a valid review. This review can be approved!"
        );
    }

    public static Review inappropriateReview() {
        return new Review(
                new ReviewId("20170101-R-00001"),
                "My Review",
                "This is an invalid review. It contains to many bad words."
        );
    }
}
