describe('ReviewService', function() {

    var ReviewService;
    var $httpBackend;

    beforeEach(function() {

        module('rsui');

        inject(function (_ReviewService_, _$httpBackend_) {
            ReviewService = _ReviewService_;
            $httpBackend = _$httpBackend_;
        });
    });

    it('should be defined', function() {
        expect(ReviewService).toBeDefined();
    });

    it('should post review', function() {
        var review = {
            subject: "My Review",
            rating: 5,
            content: "This is a review about a great product."
        };
        $httpBackend.expectPOST('reviews', review).respond(200);
        ReviewService.submitReview(review);
        $httpBackend.flush();
    });
});