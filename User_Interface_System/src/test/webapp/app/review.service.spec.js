describe('ReviewService', function() {

    var ReviewService;
    var $httpBackend;

    var review = {
        subject: "My Review",
        rating: 5,
        content: "This is a review about a great product."
    };

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
        $httpBackend.expectPOST('reviews', review).respond(200);
        ReviewService.submitReview(review);
        $httpBackend.flush();
    });

    it('should retrieve list of approved reviews', function() {
        $httpBackend.expectGET('reviews/approved').respond(200, [ review ]);
        ReviewService.getApprovedReviews().then(function (reviews) {
            expect(reviews.length).toBe(1);
            expect(reviews[0]).toEqual(review)
        });
        $httpBackend.flush();
    });
});