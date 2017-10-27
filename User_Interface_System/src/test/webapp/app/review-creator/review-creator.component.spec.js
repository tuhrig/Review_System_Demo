describe('ReviewCreatorComponent', function () {

    var $rootScope;
    var TestUtil;
    var ReviewService;

    beforeEach(function () {

        module('rsui');
        module('test-templates');

        inject(function(_TestUtil_, _$rootScope_, _ReviewService_){
            TestUtil = _TestUtil_;
            $rootScope = _$rootScope_;
            ReviewService = _ReviewService_;
        });
    });

    describe("submit", function () {

        var scope;
        var review = {
            subject: "My review",
            rating: 5,
            content: "What a great product!"
        };

        beforeEach(function () {
            spyOn(ReviewService, "submitReview");
            scope = TestUtil.compile('<review-creator></review-creator>');
            scope.$ctrl.review = review;
        });

        it("should call review service to submit", function() {
            scope.$ctrl.submitReview();
            expect(ReviewService.submitReview).toHaveBeenCalledWith(review);
        });

        it("should remove current review", function() {
            scope.$ctrl.submitReview();
            expect(scope.$ctrl.review).toEqual({})
        });
    });

    describe("the review form", function () {

        it("should be invalid by default as it is empty", function() {
            var scope = TestUtil.compile('<review-creator></review-creator>');
            expect(scope.submitReviewForm.$valid).toBe(false);
        });

        it("should be valid if a review is filled in", function() {
            var scope = TestUtil.compile('<review-creator></review-creator>');
            scope.$ctrl.review = {
                subject: "My review",
                rating: 5,
                content: "What a great product!"
            };
            $rootScope.$apply();
            expect(scope.submitReviewForm.$valid).toBe(true);
        });

        it("should be invalid if subject is missing", function() {
            var scope = TestUtil.compile('<review-creator></review-creator>');
            scope.$ctrl.review = {
                rating: 5,
                content: "What a great product!"
            };
            $rootScope.$apply();
            expect(scope.submitReviewForm.$valid).toBe(false);
        });

        it("should be invalid if rating is missing", function() {
            var scope = TestUtil.compile('<review-creator></review-creator>');
            scope.$ctrl.review = {
                subject: "My review",
                content: "What a great product!"
            };
            $rootScope.$apply();
            expect(scope.submitReviewForm.$valid).toBe(false);
        });

        it("should be invalid if content is missing", function() {
            var scope = TestUtil.compile('<review-creator></review-creator>');
            scope.$ctrl.review = {
                subject: "My review",
                rating: 5
            };
            $rootScope.$apply();
            expect(scope.submitReviewForm.$valid).toBe(false);
        });
    });
});