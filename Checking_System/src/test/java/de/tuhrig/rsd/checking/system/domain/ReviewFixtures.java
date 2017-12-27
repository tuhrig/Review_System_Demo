package de.tuhrig.rsd.checking.system.domain;

public class ReviewFixtures {
    public static Check validReview() {
        return new Check(
                new ReviewId("20170101-R-00001"),
                "My Review",
                "This is a valid review. This review can be approved!"
        );
    }

    public static Check inappropriateReview() {
        return new Check(
                new ReviewId("20170101-R-00001"),
                "My Review",
                "This is an invalid review. It contains to many bad words."
        );
    }
}
