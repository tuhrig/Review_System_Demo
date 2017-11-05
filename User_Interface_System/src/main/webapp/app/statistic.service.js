(function(){

    angular.module('rsui').factory('StatisticService', StatisticServiceFactory);

    StatisticServiceFactory.$inject = ['$http'];

    function StatisticServiceFactory($http) {

        function getNumberOfApprovedReviews() {
            return $http.get("statistics/approved").then(function (result) {
                return result.data;
            });
        }

        function getNumberOfRejectedReviews() {
            return $http.get("statistics/rejected").then(function (result) {
                return result.data;
            });
        }

        function getNumberOfSubmittedReviews() {
            return $http.get("statistics/submitted").then(function (result) {
                return result.data;
            });
        }

        return {
            getNumberOfApprovedReviews: getNumberOfApprovedReviews,
            getNumberOfRejectedReviews: getNumberOfRejectedReviews,
            getNumberOfSubmittedReviews: getNumberOfSubmittedReviews
        };
    }
}());