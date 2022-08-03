//댓글의 입력창 스크립트
$(function () {
    $(".commentaginbox_again").hide();
    $(".viewBtn_again").click(function () {
        $(".commentaginbox_again").slideToggle();
    });
});

//재댓글의 댓글입력창 스크립트
$(function () {
    $(".commentaginbox_again2").hide();
    $(".viewBtn_again2").click(function () {
        $(".commentaginbox_again2").slideToggle();
    });
});