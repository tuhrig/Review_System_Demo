module.exports = function(config) {
    config.set({

        frameworks: ['jasmine', 'jasmine-matchers'],

        // list of files / patterns to load in the browser
        files: [

            'src/main/webapp/libs/jquery/dist/jquery.js',
            'src/main/webapp/libs/lodash/lodash.js',
            'src/main/webapp/libs/bootstrap/dist/js/bootstrap.js',
            'src/main/webapp/libs/angular/angular.js',
            'src/main/webapp/libs/angular-ui-router/release/angular-ui-router.js',

            'node_modules/angular-mocks/angular-mocks.js',
            'node_modules/jasmine-promise-matchers/dist/jasmine-promise-matchers.js',

            'src/main/webapp/app/*.module.js',
            'src/main/webapp/app/**/*.module.js',
            'src/main/webapp/app/**/*.js',
            'src/main/webapp/app/**/*.html',

            'src/test/webapp/app/**/*.module.js',
            'src/test/webapp/app/**/*.js'
        ],

        colors: true,

        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        preprocessors: {
            'src/main/webapp/app/**/*.html': "ng-html2js"
        },

        ngHtml2JsPreprocessor: {
            moduleName: "test-templates",
            stripPrefix: "src/main/webapp/"
        },

        captureTimeout: 60000,

        plugins : [
            'karma-jasmine',
            'karma-phantomjs-launcher',
            'karma-jasmine-matchers',
            'karma-ng-html2js-preprocessor'
        ],

        reporters: ['progress'],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: true
    });
};