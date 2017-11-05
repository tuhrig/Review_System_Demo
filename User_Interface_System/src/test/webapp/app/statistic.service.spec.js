describe('StatisticService', function() {

    var StatisticService;
    var $httpBackend;

    beforeEach(function() {

        module('rsui');

        inject(function (_StatisticService_, _$httpBackend_) {
            StatisticService = _StatisticService_;
            $httpBackend = _$httpBackend_;
        });
    });

    it('should be defined', function() {
        expect(StatisticService).toBeDefined();
    });

    it('should return number of approved reviews', function() {
        $httpBackend.expectGET('statistics/approved').respond(200, 42);
        StatisticService.getNumberOfApprovedReviews().then(function (reviews) {
            expect(reviews).toBe(42);
        });
        $httpBackend.flush();
    });

    it('should return number of rejected reviews', function() {
        $httpBackend.expectGET('statistics/rejected').respond(200, 42);
        StatisticService.getNumberOfRejectedReviews().then(function (reviews) {
            expect(reviews).toBe(42);
        });
        $httpBackend.flush();
    });

    it('should return number of submitted reviews', function() {
        $httpBackend.expectGET('statistics/submitted').respond(200, 42);
        StatisticService.getNumberOfSubmittedReviews().then(function (reviews) {
            expect(reviews).toBe(42);
        });
        $httpBackend.flush();
    });
});