$(function(){
    //setTimeout(function() {$(".home-post img.article-thumbnail").trigger("sporty")}, 5000);
    var grid = $(".main-content");
    grid.masonry();
    grid.imagesLoaded(function(){ //图片加载完后，再计算
        grid.masonry({
            itemSelector : '.home-post, .default-post',
            isAnimated : true
        });
    });
    // Infinite Scroll
    grid.infinitescroll({
            navSelector: '#nav-below', //页面分页元素--本页的导航，意思就是一个可以触发ajax函数的模块
            nextSelector: '#nav-below a',  //下一页的导航
            itemSelector: '.home-post, .default-post', //（每次载入的数据放的地方）
            animate: true, //加载时候时候需要动画，默认是false
            extraScrollPx: 50,
            pixelsFromNavToBottom: 300, //滑动de真正触发
            //maxPage: 10,
            loading: {
                msgText: "加载中...",
                finishedMsg: '<span>快的没有BIBI了</span>',
                img: '/images/ajax-loader.gif'
            }
        },

        function( newElements ) {
            // hide new items while they are loading
            var $newElems = $(newElements).css({opacity: 0});
            // ensure that images load before adding to masonry layout
            $newElems.imagesLoaded(function(){
                // show elems now they're ready
                $newElems.animate({ opacity: 1 });
                grid.masonry( 'appended', $newElems, true );
            });

        }
    );

    //动态生成导致事件没有绑定
    $(".container").on("click",".image-popup", function(e){
        e.preventDefault();

        var data_img = $.trim($(this).find('img').attr("data-img"));
        data_img = data_img.substring(0,data_img.length-1);
        var imgs = data_img.split(',');
        var title = $(this).next().find('h2').text();
        var box = [];
        for(var i = 0; i < imgs.length; i++) {
            var photo = {};
            photo["href"] = imgs[i];
            photo["title"] = title;
            box[i] = photo;
        }

        $.swipebox(box);
        views(this);
    });


    $('.zilla-likes').click(function(){
        var zillaLikes = $(this);
        var id = zillaLikes.attr("data-id");
        $.ajax({
            url:"/likes/" + id,
            context: this,
            type:"PUT",
            success:(function(e){
                $(this).children()[0].innerText = e;
            })
        })
    });

    toTop();

});

function views(obj){
    var id = $(obj).attr("data-id");
    $.ajax({
        url:"/views/" + id,
        context: $(obj).parent().find(".zilla-views-count"),
        type:"PUT",
        success:(function(e){
            $(this).html(e);
        })
    })
}

function toTop(){
    $(window).scroll(function(){
        if($(window).scrollTop() >= 100){
            $(".toTop").fadeIn(400);
        } else {
            $(".toTop").fadeOut(200);
        }
    });
    $('.toTop').click(function(){
        $('html,body').animate({scrollTop:0},800);
    })
}
