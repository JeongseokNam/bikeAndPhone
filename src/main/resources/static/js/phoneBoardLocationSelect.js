function selectLocation(location){
    $("#location").val(location);
    $("#searchForm").submit();
}
$(document).ready(function (){
    var location = getParameters('location')
    if (location==''){
        $("#li_1").attr('class','border rounded-top bg-black col py-1');
        $("#lo_1").attr('class','text-white');
    }
    if (location=='서울'){
        $("#li_2").attr('class','border rounded-top bg-black col py-1');
        $("#lo_2").attr('class','text-white');
    }
    if (location=='경기'){
        $("#li_3").attr('class','border rounded-top bg-black col py-1');
        $("#lo_3").attr('class','text-white');
    }
    if (location=='충청도'){
        $("#li_4").attr('class','border rounded-top bg-black col py-1');
        $("#lo_4").attr('class','text-white');
    }
    if (location=='강원도'){
        $("#li_5").attr('class','border rounded-top bg-black col py-1');
        $("#lo_5").attr('class','text-white');
    }
    if (location=='경상도'){
        $("#li_6").attr('class','border rounded-top bg-black col py-1');
        $("#lo_6").attr('class','text-white');
    }
    if (location=='전라도'){
        $("#li_7").attr('class','border rounded-top bg-black col py-1');
        $("#lo_7").attr('class','text-white');
    }
    if (location=='제주도'){
        $("#li_8").attr('class','border rounded-top bg-black col py-1');
        $("#lo_8").attr('class','text-white');
    }


})
var getParameters = function (paramName) {
    // 리턴값을 위한 변수 선언
    var returnValue;

    // 현재 URL 가져오기
    var url = location.href;

    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
    for (var i = 0; i < parameters.length; i++) {
        var varName = parameters[i].split('=')[0];
        if (varName.toUpperCase() == paramName.toUpperCase()) {
            returnValue = parameters[i].split('=')[1];
            return decodeURIComponent(returnValue);
        }
    }
};