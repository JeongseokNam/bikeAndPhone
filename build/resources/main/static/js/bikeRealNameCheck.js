function realName(memberId, index){ // 실명확인버튼
    let inputName = $("#checkName"+index).val();
        $.ajax({
            url:"/board/realNameCheck",
            data : {"memberId" : memberId, "inputName" : inputName},
            type : "POST",
            success : function (flag){
                if (flag){
                    $("#notEqualName").hide();
                    $("#equalName").show();
                }else {
                    $("#notEqualName").show();
                    $("#equalName").hide();
                }
            },
            error : function (){
                alert("통신오류 입니다. 관리자에게 문의하세요.")
            }
        })
}