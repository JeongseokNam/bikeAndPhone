/*셀렉트값 부가정보*/
$(document).ready(function (){
    if ($("#bikeBrand").val()!=''){
        let bikeBrand = $("#bikeBrand").val();
        $.ajax({
            url:"/board/findModel",
            type:"POST",
            data:{"bikeBrand":bikeBrand},
            dataType : 'json',
            success : function (data){
                let selectBox = $("#bikeModel");
                selectBox.empty();
                selectBox.append('<option value="">모델</option>');
                $.each(data,function (idx, val){
                    var bikeModel = val.bikeModel
                    if (getParameters('bikeModel')==bikeModel){
                        selectBox.append(addOptionSelected(bikeModel));
                    }else{
                        selectBox.append(addOption(bikeModel));
                    }
                })
            }
        })
    }
    $("#bikeBrand").change(function (){
        let bikeBrand = $("#bikeBrand").val();
        $.ajax({
            url:"/board/findModel",
            type:"POST",
            data:{"bikeBrand":bikeBrand},
            dataType : 'json',
            success : function (data){
                let selectBox = $("#bikeModel");
                selectBox.empty();
                selectBox.append('<option value="">모델</option>');
                $.each(data,function (idx, val){
                    var bikeModel = val.bikeModel
                    selectBox.append(addOption(bikeModel));
                })
            }
        })
    })
})
//selectBox option추가
function addOption(bikeModel){
    return'<option value="'+bikeModel+'">'+bikeModel+'</option>'
}
function addOptionSelected(bikeModel){
    return'<option selected value="'+bikeModel+'">'+bikeModel+'</option>'
}
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

            return decodeURIComponent(returnValue).replace("+"," ");
        }
    }
};