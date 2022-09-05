$(document).ready(function () {
    $("#header_menu").mouseenter(function () {
        $(".header_subdisplay").slideDown();
        $("#header_menu1").addClass("port_back");
        $("#header_menu2,#header_menu3").removeClass("port_back");
    });
    $("#header_menu").mouseleave(function () {
        $(".header_subdisplay").hide();
    });
});