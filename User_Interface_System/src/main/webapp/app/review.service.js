(function(){

    angular.module('rsui').factory('ReviewService', ReviewServiceFactory);

    ReviewServiceFactory.$inject = ['$http'];

    function ReviewServiceFactory($http) {

        function submitReview(review) {
            return $http.post("reviews", review);
        }

        function getApprovedReviews() {
            return $http.get("reviews/approved").then(function (result) {
                return result.data;
            });
        }

        return {
            submitReview: submitReview,
            getApprovedReviews: getApprovedReviews
        };
    }
}());