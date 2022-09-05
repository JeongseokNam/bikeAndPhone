
<!--슬라이드 스크립트-->
var galleryThumbs = new Swiper('.gallery-thumbs', {
    loop: true,
    loopedSlides: 10,
    centeredSlides: true,
    spaceBetween: 10,
    slideToClickedSlide: true,
    slidesPerView: 5,
    watchSlidesVisibility: true,
    watchSlidesProgress: true,
});

var galleryTop = new Swiper('.gallery-top', {
    loop: true,
    loopedSlides: 10,
    spaceBetween: 10,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    // USING THE THUMBS COMPONENT
    // thumbs: {
    //   swiper: galleryThumbs
    // }
});


// ALTERNATIVE SOLUTION to get the active thumb centered, it doesn't work on Safari if sliding backwards
galleryTop.controller.control = galleryThumbs;
galleryThumbs.controller.control = galleryTop;