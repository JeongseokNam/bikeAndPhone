function addLike(memberId,boardId,relatDiv){ // 상세페이지 좋아요 버튼
    $.ajax({
        type: "post",
        url: "/like/addLike",
        data: {"memberId":memberId, "boardId":boardId,"relatDiv":relatDiv},
        success : function(){
            location.reload();
        },
        error : function(){
            alert("관리자에게 문의바랍니다.");
        }
    })
}
function delLike(likeId){ // 상세페이지 좋아요 취소버튼
    $.ajax({
        type: "post",
        url: "/like/delLike",
        data: {"likeId":likeId},
        success : function(){
            location.reload();
        },
        error : function(){
            alert("관리자에게 문의바랍니다.");
        }
    })
}