function fnImgPop(url) {
    var img = new Image();
    img.src = url;
    var img_width = img.width;
    var win_width = img.width;
    var img_height = img.height;
    var win = img.height;
    var OpenWindow = window.open('', '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto');
    OpenWindow.document.write("<style>body{margin:0px;}</style><img src='" + url + "' width='" + win_width + "'>");
}
function joinApproval(memberId,approvalYn){
    $.ajax({
        url:"/member/businessMemberApproval",
        type:"post",
        data:{"memberId":memberId,"approvalYn":approvalYn},
        success:function (message){
            alert(message);
            reloadListTableArea();
        },
        error:function (){
            alert("통신 오류입니다. 개발자에게 문의하세요.");
        }
    })
}
function reloadListTableArea() { //판완,수정,삭제 버튼 영역 reload
    $('#listTable').load(location.href+' #listTable');
}