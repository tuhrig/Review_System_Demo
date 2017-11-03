describe('ReviewListComponent', function () {

    var $rootScope;
    var TestUtil;
    var ReviewService;
    var $q;

    beforeEach(function () {

        module('rsui');
        module('test-templates');

        inject(function(_TestUtil_, _$rootScope_, _ReviewService_, _$q_){
            TestUtil = _TestUtil_;
            $rootScope = _$rootScope_;
            ReviewService = _ReviewService_;
            $q = _$q_;
        });
    });

    it("should load approved review on initialization", function() {

        var review = {
            subject: "My review",
            rating: 5,
            content: "What a great product!"
        };

        var deferred = $q.defer();
        deferred.resolve([ review ]);
        spyOn(ReviewService, "getApprovedReviews").and.returnValue(deferred.promise);
        var scope = TestUtil.compile('<review-list></review-list>');

        expect(ReviewService.getApprovedReviews).toHaveBeenCalled();
        expect(scope.$ctrl.reviews).toEqual([ review ])
    });
});