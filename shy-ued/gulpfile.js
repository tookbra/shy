/*
 * gulp配置文件
 * 1、gulp.src(globs[, options])
 *    gulp.src返回当前文件流至可用插件
 *    @param globs 需要处理的源文件匹配符路径。类型(必填)：String or StringArray；
 * 2、gulp.dest(path[, options])
 *    dest方法是指定处理完后文件输出的路径；
 */

//引入功能组件
var gulp       = require('gulp');
var sass       = require('gulp-sass');
var minifycss  = require('gulp-clean-css');
var uglify     = require('gulp-uglify');
var sourcemaps = require('gulp-sourcemaps');
var imagemin   = require('gulp-imagemin');
var connect    = require('gulp-connect');
var del        = require('del');
var pngquant   = require('imagemin-pngquant');
var jshint     = require('gulp-jshint');
var plumber    = require("gulp-plumber");
var stylish    = require("jshint-stylish");
var concat     = require('gulp-concat');
var rename     = require('gulp-rename');
var chalk      = require('chalk'); //美化日志
var vfs        = require('vinyl-fs');

// 开发目录
var paths = {
    assets: 'assets',
    sass: 'assets/css/sass/**/*',
    css: 'assets/css',
    js: 'assets/js/*', //js文件相关目录
    jslib: 'assets/js/**/*', //js文件相关目录
    img: 'assets/images/**/*', //图片相关
};

// 打包目录
var dist={
    css_dir:"dist/css",
    js_dir:"dist/js",
    img_dir:"dist/images"
};

// 部署开发使用服务器，
gulp.task('server', function(){
    connect.server({
        root:'dist',
        livereload:true
    });
    console.log('服务器已启动，请访问http://localhost:8080');
});

//清除
gulp.task('clean', function(cb) {
    del(['dist'], cb);
});

// 把所有html放在dist文件夹
gulp.task('copy-html',function(){
    return gulp.src('assets/*.html').pipe(gulp.dest('dist')).pipe(connect.reload());
});


// sass编译
gulp.task('sass',function(){
    gulp.src([paths.sass,paths.css,'!assets/css/sass/*.map'])
        .pipe(plumber())
        .pipe(sourcemaps.init())
        .pipe(sass())
        .pipe(concat('style.css'))
        .pipe(gulp.dest(paths.css))
        //.pipe(minifycss())
        .pipe(sourcemaps.write({
            sourceRoot: '/css/sass'
        }))
        .pipe(rename('dev.min.css'))
        .pipe(gulp.dest('dist/style/css'))
        .pipe(connect.reload())
});

// JS检查
gulp.task('lint', function() {
    return gulp.src([paths.js,'!assets/js/*.min.js'])
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task('scripts', function() {
    gulp.src(paths.js)
        .pipe(plumber())
        .pipe(sourcemaps.init())
        .pipe(jshint())
        .pipe(jshint.reporter(stylish))
        .pipe(uglify({
            compress: {
                drop_console: true
            }
        }))
        .pipe(gulp.dest('assets/js'));
});

gulp.task('scripts-lib', function() {
    gulp.src(paths.jslib)
        .pipe(gulp.dest('dist/js/'));
});


// 处理图像
gulp.task('images', function() {
    return gulp.src(paths.img)
        .pipe(imagemin({
            progressive: true,
            svgoPlugins: [{
                removeViewBox: false
            }],
            use: [pngquant()]
        }))
        .pipe(gulp.dest('dist/images'));
});

// 监视文件变化
gulp.task('watch', function(){
    console.log(chalk.green('[监听] 启动gulp watch自动编译'))
    gulp.watch('assets/*.html',['copy-html']);
    gulp.watch(paths.jslib,['script-lib']);
    gulp.watch(paths.js,['scripts']);
    gulp.watch(paths.sass,['sass']);
    gulp.watch(paths.img,['images']);
});

/**
 * 生成最终交付文件夹
 * $ gulp build
 *
 */
gulp.task('build', function() {
    del(['build'], function() {
        console.log(chalk.red('[清理] 删除旧有build文件夹'))
    });
    gulp.src('dev/*.html').pipe(gulp.dest('build'))
    gulp.src('assets/**/!(*dev*)*').pipe(gulp.dest('build/assets'))
});

// 启动gulp
gulp.task('default',['server','watch'])