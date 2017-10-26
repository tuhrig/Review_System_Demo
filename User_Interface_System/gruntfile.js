module.exports = function (grunt) {
    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        bower: {
            options: {
                copy: false // Copy Bower packages to target directory. We control the directory via '.bowerrc'
            },
            install: {
                //just run 'grunt bower:install' and you'll see files from your Bower packages in lib directory
            }
        },

        // We use Karma to run our tests. The tests are written with Jasmine.js
        // and will test our services, controller and directives. All Karma tests
        // are unit tests and should run very fast.
        //
        // See: https://karma-runner.github.io
        // See: https://jasmine.github.io
        karma: {
            options: {
                configFile: 'karma.conf.js'
            },
            single: {
                singleRun: true
            },

            // profile that can be used during development. It will automatically run tests when they are developed.
            continuous: {
                singleRun: false,
                autoWatch: true
            }
        },

        // We concat all of our own JavaScript files to a single file. We do this so that we don't
        // need to maintain the list of files manually in the "index.html". The concatenated JavaScript
        // file will be placed in the "libs" folder which will not be committed.
        //
        // Note that we need to include the module definitions first before we include the
        // other files because services etc. will depend on the modules. We use the very
        // same mechanism when we load the files for our Karma tests.
        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: [
                    "src/main/webapp/app/**/*.module.js",
                    "src/main/webapp/app/**/*.js"
                ],
                dest: 'src/main/webapp/libs/app.js'
            }
        },

        // Because we concat our JavaScript files to a single file (see above), we cannot see
        // changes immediately when we refresh the web page in the browser. To support this
        // very convenient feature for development, we use the file watcher task below. This
        // task will observe all "*.js" and "*.html" files and will run the "concat" task as
        // soon as one of those files change. So be keeping this task running, we can develop
        // just as usual.
        watch: {
            scripts: {
                files: [
                    'src/main/webapp/app/**/*.js',
                    'src/main/webapp/app/**/*.html'
                ],
                tasks: ['concat'],
                options: {
                    spawn: false
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-watch');

    // Custom grunt tasks
    grunt.registerTask('setup', ['bower:install', 'concat']);
    grunt.registerTask('check', ['setup', 'test']);
    grunt.registerTask('test', ['setup', 'karma:single']);
};
