function soldOut(phoneId){ //판매완료처리
    if (confirm("판매완료 처리 하시겠습니까?")){
        $.ajax({
            url:"/phone/soldOut",
            data : {"phoneId" : phoneId},
            type : "POST",
            success : function (){
                alert("판매완료 처리 되었습니다.");
                reloadStatusBtnArea();
            },
            error : function (){
                alert("통신오류 입니다. 관리자에게 문의하세요.")
            }
        })
    }
}
function soldOutCancel(phoneId){ //판매완료취소처리
    if (confirm("판매완료취소 처리 하시겠습니까?")){
        $.ajax({
            url:"/phone/soldOutCancel",
            data : {"phoneId" : phoneId},
            type : "POST",
            success : function (){
                alert("판매완료취소 처리 되었습니다.");
                reloadStatusBtnArea();
            },
            error : function (){
                alert("통신오류 입니다. 관리자에게 문의하세요.")
            }
        })
    }
}

function reloadStatusBtnArea() { //판매완료, 찜 버튼 영역
    $('#statusBtnArea').load(location.href+' #statusBtnArea');
}

function PhoneDelete(phoneId){
    if (confirm("정말 게시물을 삭제 하시겠습니까?")){
        location.href = "/phone/delete/?phoneId="+phoneId;
    }
}