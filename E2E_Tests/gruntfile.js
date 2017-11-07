module.exports = function (grunt) {
    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        // configure e2e testing
        protractor: {
            options: {
                configFile: "protractor.conf.js",
                keepAlive: true,
                noColor: false,
                webdriverManagerUpdate: true,
                args: {
                    // Arguments passed to the command
                }
            },
            all: {}
        }
    });

    grunt.loadNpmTasks('grunt-protractor-runner');
};
