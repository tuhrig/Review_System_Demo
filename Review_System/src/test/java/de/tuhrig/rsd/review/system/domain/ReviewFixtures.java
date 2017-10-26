package de.tuhrig.rsd.review.system.domain;

public class ReviewFixtures {

    public static Review anInitialFiveStarSmartphoneReview() {
        String subject = "Best Smartphone Ever!";
        String content = "I've tested a lot of smartphones over the last couple of years. However," +
                "this one is definitely the best. High display resolution, fast CPU and long battery" +
                "life.";
        return new Review(subject, content, new Rating(5));
    }

    public static Review anOpenFiveStarSmartphoneReview() {
        Review review = anInitialFiveStarSmartphoneReview();
        review.open();
        return review;
    }

    public static Review anApprovedFiveStarSmartphoneReview() {
        Review review = anInitialFiveStarSmartphoneReview();
        review.open();
        review.approve();
        return review;
    }
}
