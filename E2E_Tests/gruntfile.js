module.exports = function (grunt) {
    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        // configure e2e testing
        protractor: {
            options: {
                configFile: "protractor_conf.js", // Default config file
                keepAlive: true, // If false, the grunt process stops when the test fails.
                noColor: false, // If true, protractor will not use colors in its output.
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
