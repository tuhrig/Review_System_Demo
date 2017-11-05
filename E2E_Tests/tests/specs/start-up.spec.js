describe('The application', function () {
    it('should jump to the reviews view when opened', function () {
        browser.get("http://localhost:9002");
        var url = browser.getCurrentUrl();
        expect(url).toBe("http://localhost:9002/#!/reviews");
    });
});