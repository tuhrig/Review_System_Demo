(function () {

    angular.module('rsui').controller('ReviewListController', ReviewListController);

    ReviewListController.$inject = [ 'ReviewService' ];

    function ReviewListController(ReviewService) {

        var vm = this;
        vm.reviews = [];


        ReviewService.getApprovedReviews().then(function (reviews) {
            vm.reviews = reviews;
        })
    }
})();