(function(){

    angular.module('rsui').factory('ReviewService', ReviewServiceFactory);

    ReviewServiceFactory.$inject = ['$http'];

    function ReviewServiceFactory($http) {

        function submitReview(review) {
            return $http.post("reviews", review);
        }

        return {
            submitReview: submitReview
        };
    }
}());