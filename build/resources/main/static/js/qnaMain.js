function qnaDelete(qnaId){
    if (confirm("해당 문의 내용을 삭제 하시겠습니까?")){
        $.ajax({
            url:"/qna/delete",
            data:{"qnaId":qnaId},
            type:"POST",
            success:function (){
                location.reload();
            },
            error:function (){
                alert("통신오류 입니다. 관리자에게 문의하세요")
            }
        })
    }
}