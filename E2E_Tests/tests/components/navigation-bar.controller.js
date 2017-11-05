(function () {

    function clickOnSubmitReview(navigationBar) {
        var link = navigationBar.all(by.tagName('li')).get(0);
        link.click();
    }

    function clickOnReviewsViews(navigationBar) {
        var link = navigationBar.all(by.tagName('li')).get(1);
        link.click();
    }

    function clickOnStatistics(navigationBar) {
        var link = navigationBar.all(by.tagName('li')).get(2);
        link.click();
    }

    module.exports = {
        clickOnSubmitReview: clickOnSubmitReview,
        clickOnReviewsViews: clickOnReviewsViews,
        clickOnStatistics: clickOnStatistics
    };

})();