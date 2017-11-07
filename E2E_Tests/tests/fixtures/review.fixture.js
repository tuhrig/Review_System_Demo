(function () {

    function aValidFiveStarReview() {
        return {
            subject: "This is a valid review",
            rating: 5,
            content: "This review is valid and contains no evil words."
        }
    }

    function anInvalidFiveStarReview() {
        return {
            subject: "This is an invalid review",
            rating: 5,
            content: "This review contains bad words and is inappropriate."
        }
    }

    module.exports = {
        aValidFiveStarReview: aValidFiveStarReview,
        anInvalidFiveStarReview: anInvalidFiveStarReview
    };

})();