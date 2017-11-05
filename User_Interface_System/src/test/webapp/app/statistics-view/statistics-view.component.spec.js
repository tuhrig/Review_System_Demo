describe('StatisticsViewComponent', function () {

    var $rootScope;
    var TestUtil;
    var StatisticService;
    var $q;

    beforeEach(function () {

        module('rsui');
        module('test-templates');

        inject(function(_TestUtil_, _$rootScope_, _StatisticService_, _$q_){
            TestUtil = _TestUtil_;
            $rootScope = _$rootScope_;
            StatisticService = _StatisticService_;
            $q = _$q_;
        });
    });

    it("should load statistics on initialization", function() {

        var deferred = $q.defer();
        deferred.resolve(42);

        spyOn(StatisticService, "getNumberOfApprovedReviews").and.returnValue(deferred.promise);
        spyOn(StatisticService, "getNumberOfRejectedReviews").and.returnValue(deferred.promise);
        spyOn(StatisticService, "getNumberOfSubmittedReviews").and.returnValue(deferred.promise);

        var scope = TestUtil.compile('<statistics-view></statistics-view>');

        expect(scope.$ctrl.approvedReviews).toEqual(42);
        expect(scope.$ctrl.rejectedReviews).toEqual(42);
        expect(scope.$ctrl.submittedReviews).toEqual(42);
    });
});