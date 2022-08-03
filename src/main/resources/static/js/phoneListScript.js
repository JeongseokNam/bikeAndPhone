
$(document).ready(function () {
    var xOffset = 10;
    var yOffset = 30;

    //마우스 오버시 preview 생성
    $(document).on("mouseover", ".gallery", function (e) {
        var image_data = $(this).data("image");
        var add_caption = (image_data != undefined) ? "<br/>" + image_data : "";
        $("body").append("<p id='preview'><img src='" + $(this).attr("src") + "' width='300px' />" + add_caption + "</p>"); //보여줄 이미지를 선언
        $("#preview")
            .css("top", (e.pageY - xOffset) + "px")
            .css("left", (e.pageX + yOffset) + "px")
            .fadeIn("fast"); //미리보기 화면 설정 셋팅
    });

    //마우스 이동시 preview 이동
    $(document).on("mousemove", ".gallery", function (e) {
        $("#preview")
            .css("top", (e.pageY - xOffset) + "px")
            .css("left", (e.pageX + yOffset) + "px");
    });

    //마우스 아웃시 preview 제거
    $(document).on("mouseout", ".gallery", function () {
        $("#preview").remove();
    });

});
