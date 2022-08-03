   function submitRoleChange(index){ // 관리자페이지 사용자목록 관리 권한수정 버튼
    $.ajax({
        type:"POST",
        data:{"memberRole" : $("#memberRole"+index).val(),"memberId":$("#memberId"+index).val()},
        url:"/manager/RoleChange",
        success : function () {
            location.reload();
        },
        error : function () {
            alert("통신 오류입니다. 관리자에게 문의해주세요.")
        }
    })
    alert("권한변경하였습니다.");
}
    function deleteBoard(boardId){ //관리자페이지 바이크게시글 삭제버튼
    if(confirm("정말 삭제 하시겠습니까?")){
     $.ajax({
            type:"POST",
            data:{"boardId" : boardId},
            url:"/manager/delete",
            success : function () {
                location.reload();
                alert("삭제되었습니다.");
            },
            error : function () {
                alert("통신 오류입니다. 관리자에게 문의해주세요.")
            }
        })
    }
  }

   function deletePhone(phoneId){ //관리자페이지 휴대폰게시글 삭제버튼
       if(confirm("정말 삭제 하시겠습니까?")){
           $.ajax({
               type:"POST",
               data:{"phoneId" : phoneId},
               url:"/manager/deletePhone",
               success : function () {
                   location.reload();
                   alert("삭제되었습니다.");
               },
               error : function () {
                   alert("통신 오류입니다. 관리자에게 문의해주세요.")
               }
           })
       }
   }
