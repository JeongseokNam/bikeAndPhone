function addLike(memberId, phoneId, relatDiv){ // 상세페이지 찜 버튼
    $.ajax({
        type: "POST",
        url: "/phone/clickLike",
        data: {"memberId":memberId, "phoneId":phoneId, "relatDiv":relatDiv},
        success : function(){
            location.reload();
        },
        error : function(){
            alert("관리자에게 문의바랍니다.");
        }
    })
}
function delLike(likeId){ // 상세페이지 찜 취소 취소버튼
    $.ajax({
        type: "POST",
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