(function () {

    function findReviewCreator() {
        return element(by.tagName('review-creator'));
    }

    function findReviewList() {
        return element(by.tagName('review-list'));
    }

    function findStatisticsView() {
        return element(by.tagName('statistics-view'));
    }

    function findNavigationBar() {
        return element(by.tagName('navigation-bar'));
    }

    module.exports = {
        findReviewCreator: findReviewCreator,
        findReviewList: findReviewList,
        findStatisticsView: findStatisticsView,
        findNavigationBar: findNavigationBar
    };

})();