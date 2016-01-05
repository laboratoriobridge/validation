var gulp = require('gulp');
var ts = require('gulp-typescript');
var merge = require('merge2');
var del = require('del');
var mocha = require('gulp-mocha');
var browserify = require('browserify');
var source = require('vinyl-source-stream');

var TS_FILES = 'src/**/*.ts';
var TEST_FILES = '/src/**/__tests__/**/*.{ts,tsx,js}';

gulp.task('typescript', function() {
  var tsProject = ts.createProject('tsconfig.json');

  var tsResult = gulp.src(TS_FILES)
                     .pipe(ts(tsProject));

    return merge([
        tsResult.dts.pipe(gulp.dest('dist/')),
        tsResult.js.pipe(gulp.dest('dist/'))
    ]);
});

gulp.task('test', ['typescript'], function() {
  require('babel/register');
  return gulp.src(TEST_FILES, {read: false})
          .pipe(mocha({ reporter: 'dot' }));
});

gulp.task('clean', function(cb) {
  del.sync(['dist/'], cb);
});

gulp.task('watch', ['test'], function() {
  gulp.watch([TEST_FILES, TS_FILES], ['test']);
});

gulp.task('default', ['clean', 'typescript', 'test']);
