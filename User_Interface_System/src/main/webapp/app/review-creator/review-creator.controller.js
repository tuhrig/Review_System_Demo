(function () {

    angular.module('rsui').controller('ReviewCreatorController', ReviewCreatorController);

    ReviewCreatorController.$inject = [ 'ReviewService' ];

    function ReviewCreatorController(ReviewService) {

        var vm = this;
        vm.review = { /* initially empty */ };

        vm.submitReview = function () {
            ReviewService.submitReview(vm.review);
            vm.review = {};
        }
    }
})();