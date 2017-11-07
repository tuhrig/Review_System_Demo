(function () {

    var component = null;

    function setComponent(componentToUse) {
        component = componentToUse;
    }

    function getNumberOfApprovedReviews() {
        return component.element(by.name('approvedReviews')).getText().then(function (value) {
            return parseInt(value);
        });
    }

    function getNumberOfRejectedReviews() {
        return component.element(by.name('rejectedReviews')).getText().then(function (value) {
            return parseInt(value);
        });
    }

    function getNumberOfSubmittedReviews() {
        return component.element(by.name('submittedReviews')).getText().then(function (value) {
            return parseInt(value);
        });
    }

    function getStatistics() {
        return getNumberOfApprovedReviews().then(function (approved) {
            return getNumberOfRejectedReviews().then(function (rejected) {
                return getNumberOfSubmittedReviews().then(function (submitted) {
                    return {
                        "approved": approved,
                        "rejected": rejected,
                        "submitted": submitted
                    };
                });
            });
        });
    }

    module.exports = {
        setComponent: setComponent,
        getNumberOfApprovedReviews: getNumberOfApprovedReviews,
        getNumberOfRejectedReviews: getNumberOfRejectedReviews,
        getNumberOfSubmittedReviews: getNumberOfSubmittedReviews,
        getStatistics: getStatistics
    };

})();