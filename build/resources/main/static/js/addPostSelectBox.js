
/*셀렉트값 부가정보*/
$(document).ready(function (){
    /*바이크 제조사*/
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
    /*시도군구*/
    $("#location").change(function (){
        let location = $("#location").val();
        $.ajax({
            url:"/board/findLocationDetail",
            type:"POST",
            data:{"location":location},
            dataType : 'json',
            success : function (data){
                let selectBox = $("#locationDetail");
                selectBox.empty();
                selectBox.append('<option value="">시,구(군) 선택</option>');
                $.each(data,function (idx, val){
                    var locationDetail = val.locationDetailName
                    selectBox.append(addOption(locationDetail));
                })
            }
        })
    })
})
//selectBox option추가 - 바이크모델
function addOption(name){
    return'<option value="'+name+'">'+name+'</option>'
}
