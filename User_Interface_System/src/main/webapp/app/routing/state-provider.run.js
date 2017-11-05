(function () {

    angular.module('rsui').config(['$stateProvider', '$urlRouterProvider', addStates]);

    function addStates($stateProvider, $urlRouterProvider) {

        var submitReviewState = {
            url: "/submit",
            views: {
                'stepContent@': {
                    template: "<review-creator></review-creator>"
                }
            }
        };

        var reviewListState = {
            url: "/reviews",
            views: {
                'stepContent@': {
                    template: "<review-list></review-list>"
                }
            }
        };

        var statisticsState = {
            url: "/statistics",
            views: {
                'stepContent@': {
                    template: "<statistics-view></statistics-view>"
                }
            }
        };

        $stateProvider.state("submit", submitReviewState);
        $stateProvider.state("reviews", reviewListState);
        $stateProvider.state("statistics", statisticsState);

        $urlRouterProvider.when('', '/reviews');
    }
    
})();