(function () {

    angular.module('rsui').controller('StatisticsViewController', StatisticsViewController);

    StatisticsViewController.$inject = [ 'StatisticService' ];

    function StatisticsViewController(StatisticService) {

        var vm = this;

        vm.approvedReviews = 0;
        vm.rejectedReviews = 0;
        vm.submittedReviews = 0;

        StatisticService.getNumberOfApprovedReviews().then(function (numberOf) {
            vm.approvedReviews = numberOf;
        });

        StatisticService.getNumberOfRejectedReviews().then(function (numberOf) {
            vm.rejectedReviews = numberOf;
        });

        StatisticService.getNumberOfSubmittedReviews().then(function (numberOf) {
            vm.submittedReviews = numberOf;
        });
    }
})();