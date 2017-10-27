(function(){

    angular.module('rsui').factory('TestUtil', TestUtilFactory);

    TestUtilFactory.$inject = ['$rootScope', '$compile'];

    function TestUtilFactory($rootScope, $compile) {

        var element;

        function compile(directive) {
            var scope = $rootScope.$new();
            element = angular.element(directive);
            element = $compile(element)(scope);
            scope.$apply();

            // We should not access private properties from AngularJS
            // which are prefixed by "$$". However, I don't see any other
            // technical solution for this problem at the moment.
            return scope.$$childTail;
        }

        function getElement() {
            return element;
        }

        return {
            compile: compile,
            getElement: getElement
        };
    }
}());