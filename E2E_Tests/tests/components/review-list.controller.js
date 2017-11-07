(function () {

    var component = null;

    function setComponent(componentToUse) {
        component = componentToUse;
    }

    function getNumberOfReviews() {
        return component.all(by.name('review')).then(function(elements){
            return elements.length;
        });
    }

    function getSubject(index) {
        var reviewComponent = component.all(by.name('review')).get(index);
        return reviewComponent.element(by.name('subject')).getText();
    }

    function getRating(index) {
        var reviewComponent = component.all(by.name('review')).get(index);
        return reviewComponent.element(by.name('rating')).getText().then(function (value) {
            return parseInt(value);
        });
    }

    function getContent(index) {
        var reviewComponent = component.all(by.name('review')).get(index);
        return reviewComponent.element(by.name('content')).getText();
    }

    function getReview(index) {
        return getSubject(index).then(function (subject) {
            return getRating(index).then(function (rating) {
                return getContent(index).then(function (content) {
                    return {
                        "subject": subject,
                        "rating": rating,
                        "content": content
                    };
                });
            });
        });
    }
    
    module.exports = {
        setComponent: setComponent,
        getNumberOfReviews: getNumberOfReviews,
        getSubject: getSubject,
        getRating: getRating,
        getContent: getContent,
        getReview: getReview
    };

})();