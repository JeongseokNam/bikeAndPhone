function soldOut(boardId){ //판매완료처리
    if (confirm("판매완료 처리 하시겠습니까?")){
        $.ajax({
            url:"/board/soldOut",
            data : {"boardId" : boardId},
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
function soldOutCancel(boardId){ //판매완료취소처리
    if (confirm("판매완료취소 처리 하시겠습니까?")){
        $.ajax({
            url:"/board/soldOutCancel",
            data : {"boardId" : boardId},
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

function reloadStatusBtnArea() { //판완,수정,삭제 버튼 영역 reload
    $('#statusBtnArea').load(location.href+' #statusBtnArea');
}

function deleteBoard(boardId){
    if (confirm("정말 게시물을 삭제 하시겠습니까?")){
        location.href = "/board/delete/?boardId="+boardId;
    }
}