function reloadStatusBtnArea() { // 공지사항 삭제버튼
    $('#BtnArea').load(location.href+' #BtnArea');
}

function deleteNotice(noticeId){
    if (confirm("공지사항을 삭제 하시겠습니까?")){
        location.href = "/manager/delete/?noticeId="+noticeId;
    }
}