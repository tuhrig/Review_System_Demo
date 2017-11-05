describe('The application', function () {

    var navigationBarController = require('../components/navigation-bar.controller.js');

    it('should jump to the "reviews view" when opened', function () {
        browser.get("http://localhost:9002");

        var url = browser.getCurrentUrl();
        expect(url).toBe("http://localhost:9002/#!/reviews");
    });

    it('should jump to the "submit view" when link is clicked', function () {
        browser.get("http://localhost:9002");
        var navigationBar = element(by.tagName('navigation-bar'));
        navigationBarController.clickOnSubmitReview(navigationBar);

        var url = browser.getCurrentUrl();
        expect(url).toBe("http://localhost:9002/#!/submit");
    });

    it('should jump to the "reviews view" when link is clicked', function () {
        browser.get("http://localhost:9002");
        var navigationBar = element(by.tagName('navigation-bar'));
        navigationBarController.clickOnReviewsViews(navigationBar);

        var url = browser.getCurrentUrl();
        expect(url).toBe("http://localhost:9002/#!/reviews");
    });

    it('should jump to the "statistics view" when link is clicked', function () {
        browser.get("http://localhost:9002");
        var navigationBar = element(by.tagName('navigation-bar'));
        navigationBarController.clickOnStatistics(navigationBar);

        var url = browser.getCurrentUrl();
        expect(url).toBe("http://localhost:9002/#!/statistics");
    });
});