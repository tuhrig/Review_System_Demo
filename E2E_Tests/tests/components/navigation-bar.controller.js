(function () {

    var component = null;

    function setComponent(componentToUse) {
        component = componentToUse;
    }

    function clickOnSubmitReview() {
        var link = component.all(by.tagName('li')).get(0);
        link.click();
    }

    function clickOnReviewsViews() {
        var link = component.all(by.tagName('li')).get(1);
        link.click();
    }

    function clickOnStatistics() {
        var link = component.all(by.tagName('li')).get(2);
        link.click();
    }

    module.exports = {
        setComponent: setComponent,
        clickOnSubmitReview: clickOnSubmitReview,
        clickOnReviewsViews: clickOnReviewsViews,
        clickOnStatistics: clickOnStatistics
    };

})();