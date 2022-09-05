$(document).ready(function () {
    $(".commentaginbox_again").hide();
    $(".commentaginbox_again2").hide();
    $("#add_comment_btn").click(function () {
        let relatId = $("#relatId").val();
        let relatDiv = $("#relatDiv").val()
        let memberId = $("#memberId").val();
        let content = $("#comment_content").val();
        let privateYn = "N";
        if ($("#privateYn").is(':checked')){
            privateYn="Y"
        }
        if (content.trim() == '') {
            alert("댓글 내용을 입력해 주세요.")
        } else {
            $.ajax({
                type: "POST",
                url: "/comment/addComment",
                data: {"relatId": relatId,"relatDiv":relatDiv, "memberId": memberId, "content": content, "privateYn": privateYn},
                success: function () {
                    location.reload();
                },
                error: function () {
                    alert("통신오류 입니다. 괸리자에게 문의하세요.")
                }
            })

        }
    })
})
function addReComment(relatId,index){
    let content = $("#reCommentContent"+index).val();
    let privateYn = "N";
    if ($("#reCommentPrivateYn"+index).is(':checked')){
        privateYn="Y";
    }
    let targetNickName = $("#reCommentTargetNickName"+index).val();
    if (content.trim() == '') {
        alert("댓글 내용을 입력해 주세요.")
    } else {
        $.ajax({
            type: "POST",
            url: "/comment/addReComment",
            data: {"relatId": relatId, "content": content, "privateYn": privateYn, "targetNickName": targetNickName},
            success: function () {
                location.reload();
            },
            error: function () {
                alert("통신오류 입니다. 괸리자에게 문의하세요.")
            }
        })

    }
}
function delComment(commentId) {
    if (confirm("정말 삭제 하시겠습니까?")) {
        $.ajax({
            type: "POST",
            url: "/comment/delComment",
            data: {"commentId": commentId},
            success: function () {
                location.reload();
            },
            error: function () {
                alert("통신오류 입니다. 괸리자에게 문의하세요.")
            }
        })
    }
}
function delReComment(commentId) {
    if (confirm("정말 삭제 하시겠습니까?")) {
        $.ajax({
            type: "POST",
            url: "/comment/delReComment",
            data: {"commentId": commentId},
            success: function () {
                location.reload();
            },
            error: function () {
                alert("통신오류 입니다. 괸리자에게 문의하세요.")
            }
        })
    }
}
function commentToggle(index){
    let str = "";
    str = index;
    $("#commentaginbox_again"+index).slideToggle();
}