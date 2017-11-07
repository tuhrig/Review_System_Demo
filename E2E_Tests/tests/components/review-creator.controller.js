(function () {

    var component = null;

    function setComponent(componentToUse) {
        component = componentToUse;
    }

    function setSubject(subject) {
        var inputField = component.element(by.name('subject'));
        inputField.sendKeys(subject);
    }

    function setRating(index) {
        component.all(by.tagName('option')).then(function(options){
            options[index + 1].click();
        });
    }

    function setContent(content) {
        var inputField = component.element(by.name('content'));
        inputField.sendKeys(content);
    }

    function setReview(review) {
        setSubject(review.subject);
        setRating(review.rating);
        setContent(review.content);
    }

    function submit() {
        var button = component.element(by.name('submit'));
        button.click();
    }

    module.exports = {
        setComponent: setComponent,
        setSubject: setSubject,
        setRating: setRating,
        setContent: setContent,
        setReview: setReview,
        submit: submit
    };

})();