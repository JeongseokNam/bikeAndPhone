
function phoneChk(type){
    let phoneChkFlag = false;
    if (type=='soleProprietorJoin'||type=='corporateJoin'){
        alert("담당자 본인인증이 필요합니다.")
    }
    var IMP = window.IMP; // 생략 가능

    IMP.init("imp07007357"); // 예: imp00000000
    IMP.certification({ // param
        popup : true // PC환경에서는 popup 파라메터가 무시되고 항상 true 로 적용됨
    }, function (rsp) { // callback
        if (rsp.success) {
            $.ajax({
                url: "/certifications",
                type: "POST",
                data: {"imp_uid" : rsp.imp_uid,"type":type },
                success: function(url){
                    $.ajax({
                        url: "/member/phoneChk",
                        type: "POST",
                        data: {"phone" : url.split('phone=')[1]},
                        success: function (flag) {
                            if (flag) {
                                phoneChkFlag = true;
                            } else {
                                phoneChkFlag = false;
                            }
                            if (phoneChkFlag) {
                                location.href=url;
                            } else {
                                alert("이미 가입된 휴대폰 번호입니다. 로그인 화면으로 이동합니다.")
                                location.href="/member/login";
                            }
                        },
                        error: function () {
                            alert("통신 오류입니다 관리자에게 문의하세요.");
                        }
                    })
                }
            });
        } else {
            alert("인증에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
        }
    });
}

function changePhone(){
    let phoneChkFlag = false;
    var IMP = window.IMP; // 생략 가능

    IMP.init("imp07007357"); // 예: imp00000000
    IMP.certification({ // param
        popup : true // PC환경에서는 popup 파라메터가 무시되고 항상 true 로 적용됨
    }, function (rsp) { // callback
        if (rsp.success) {
            $.ajax({
                url: "/certifications/changePhone",
                type: "POST",
                data: {"imp_uid" : rsp.imp_uid},
                success: function(data){
                    $.ajax({
                        url: "/member/phoneChk",
                        type: "POST",
                        data: {"phone" : data.phone},
                        success: function (phone) {
                            if (phone) {
                                phoneChkFlag = true;
                            } else {
                                phoneChkFlag = false;
                            }
                            if (phoneChkFlag) {
                                $.ajax({
                                    url:"/member/changePhone",
                                    type:"POST",
                                    data:{"phone":phone,"name":data.name},
                                    success:function (str){
                                            alert(str);
                                    },
                                    error : function (){
                                            alert("통신 오류입니다. 관리자에게 문의하세요.")
                                    },
                                })
                                    .done(function (){
                                        location.reload();
                                    })
                            } else {
                                alert("이미 가입된 휴대폰 번호입니다.")
                            }
                        },
                        error: function () {
                            alert("통신 오류입니다 관리자에게 문의하세요.");
                        }
                    })
                }
            });
        } else {
            alert("인증에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
        }
    });
}

function findMyInfo(){
    let phoneChkFlag = false;
    var IMP = window.IMP; // 생략 가능

    IMP.init("imp07007357"); // 예: imp00000000
    IMP.certification({ // param
        popup : true // PC환경에서는 popup 파라메터가 무시되고 항상 true 로 적용됨
    }, function (rsp) { // callback
        if (rsp.success) {
            $.ajax({
                url: "/certifications/changePhone",
                type: "POST",
                data: {"imp_uid" : rsp.imp_uid},
                success: function(phone){
                    $.ajax({
                        url: "/member/phoneChkForFindInfo",
                        type: "POST",
                        data: {"phone" : phone},
                        success: function (phone) {
                            if (phone) {
                                phoneChkFlag = true;
                            } else {
                                alert("등록되지 않은 휴대폰 번호입니다.")
                                phoneChkFlag = false;
                            }
                            if (phoneChkFlag) {
                                location.href = "/member/pwdChange?phone="+phone;
                            }
                        },
                        error: function () {
                            alert("통신 오류입니다 관리자에게 문의하세요.");
                        }
                    })
                }
            });
        } else {
            alert("인증에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
        }
    });
}