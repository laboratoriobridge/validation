var gulp = require('gulp');
var babel = require("gulp-babel");
var del = require('del');
var browserify = require('browserify');
var source = require('vinyl-source-stream');

var JS_FILES = 'src/main/script/**/*.js';
var TEST_FILES = 'src/test/script/**/*.js';


gulp.task("babel", function () {
  return gulp.src(JS_FILES)
    .pipe(babel())
    .pipe(gulp.dest("lib"));
});

gulp.task('server-bundle', ['babel'], function () {
  var b = browserify({
    debug: false,
    entries: './lib/index.js',
    standalone: 'validation'
  });

  return b.bundle()
    .pipe(source('server-bundle.js'))
    .pipe(gulp.dest('./lib/'));
});

gulp.task('clean', function (cb) {
  del.sync(['lib/'], cb);
});

gulp.task('default', ['clean', 'babel', 'server-bundle']);
