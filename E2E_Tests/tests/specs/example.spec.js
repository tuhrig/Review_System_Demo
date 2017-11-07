describe('As an user', function () {

    var componentFinder = require('../utils/component-finder.util');
    var reviewFixtures = require('../fixtures/review.fixture');
    var navigationBarController = require('../components/navigation-bar.controller.js');
    var reviewListController = require('../components/review-list.controller.js');
    var reviewCreatorController = require('../components/review-creator.controller.js');
    var statisticsViewController = require('../components/statistics-view.controller');

    // This E2E test will:
    //
    //      1 - Open the application in the browser
    //      2 - Go to the statistics view to see how many reviews are in the system
    //      3 - Go to the submit view to create 2 new reviews (a valid and an invalid one)
    //      4 - Go back to the statistics view to check if it has changed
    //      5 - Go to the review list to view the new valid review on top of the list
    //
    it('I can submit and see reviews in the system', function () {

        openReviewSystemInBrowser();

        goToStatisticsView();

        var statisticsBeforeTest = statisticsViewController.getStatistics();

        goToSubmitReview();

        var validReview = reviewFixtures.aValidFiveStarReview();
        reviewCreatorController.setReview(validReview);
        reviewCreatorController.submit();

        var invalidReview = reviewFixtures.anInvalidFiveStarReview();
        reviewCreatorController.setReview(invalidReview);
        reviewCreatorController.submit();

        goToStatisticsView();

        statisticsBeforeTest.then(function (statisticsBefore) {
            var statisticsAfter = statisticsViewController.getStatistics();
            expect(statisticsAfter).toEqual({
                "approved": statisticsBefore.approved + 1,
                "rejected": statisticsBefore.rejected + 1,
                "submitted": statisticsBefore.submitted + 2
            });
        });

        goToReviewList();

        var review = reviewListController.getReview(0);
        expect(review).toEqual(validReview);
    });

    function openReviewSystemInBrowser() {
        browser.get("http://localhost:9002");
        expectUrl("http://localhost:9002/#!/reviews");
        var navigationBar = componentFinder.findNavigationBar();
        navigationBarController.setComponent(navigationBar);
    }

    function goToReviewList() {
        navigationBarController.clickOnReviewsViews();
        expectUrl("http://localhost:9002/#!/reviews");
        var reviewList = componentFinder.findReviewList();
        reviewListController.setComponent(reviewList);
    }

    function goToSubmitReview() {
        navigationBarController.clickOnSubmitReview();
        expectUrl("http://localhost:9002/#!/submit");
        var reviewCreator = componentFinder.findReviewCreator();
        reviewCreatorController.setComponent(reviewCreator);
    }

    function goToStatisticsView() {
        navigationBarController.clickOnStatistics();
        expectUrl("http://localhost:9002/#!/statistics");
        var statisticsView = componentFinder.findStatisticsView();
        statisticsViewController.setComponent(statisticsView);
    }

    function expectUrl(expectedUrl) {
        var actualUrl = browser.getCurrentUrl();
        expect(actualUrl).toBe(expectedUrl);
    }
});