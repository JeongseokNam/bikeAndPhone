$(window).scroll(function () {
    var scroll = $(window).scrollTop();
    if (scroll >= 88) {
        $(".floating-menu").addClass("on");
    } else {
        $(".floating-menu").removeClass("on");
    }
});