package de.tuhrig.rsd.checking.system.domain;

public class ReviewFixtures {
    public static Review validReview() {
        return new Review(
                new ReviewId("20170101-R-00001"),
                "My Review",
                "This is a valid review send by an user. It has an ID, a subject and some content. This review can be approved!"
        );
    }

    public static Review invalidReview() {
        return new Review(
                new ReviewId("20170101-R-00001"),
                "My Review",
                "This is an invalid review. Its content is too short."
        );
    }
}
