let nickFlag = true;

function nickCheck() { // 회원가입 및 마이페이지 닉네임변경 중복체크
    $.ajax({
        type:"POST",
        data:{"inputNick" : $("#nickName").val()}, //입력받은 닉네임값
        url:"/member/nickCheck",
        success : function (flag) {
            if (flag) {
                alert("사용중인 닉네임입니다.")
                nickFlag = true;
            }else {
                alert("사용 가능한 닉네임입니다.")
                nickFlag = false;
            }
        },
        error : function () {
            alert("통신 오류입니다. 관리자에게 문의해주세요.")
        }
    })
}

function submitNickChange(){ // 마이페이지 닉네임 변경모달 완료버튼
    if (nickFlag){
        alert("닉네임 중복체크를 해주세요");
    }else{$.ajax({
        type:"POST",
        data:{"nickName" : $("#nickName").val()}, //입력받은 이메일값
        url:"/member/NickChange",
        success : function () {
        location.reload();
        },
        error : function () {
        alert("통신 오류입니다. 관리자에게 문의해주세요.")
        }
        })
        alert("닉네임이 변경되었습니다.");
    }
}

function submitEmailChange(){ // 마이페이지 이메일 변경모달 완료버튼
    $.ajax({
            type:"POST",
            data:{"inputEmail" : $("#email").val()}, //입력받은 이메일값
            url:"/member/emailChange",
            success : function () {
                location.reload();
            },
            error : function () {
                alert("통신 오류입니다. 관리자에게 문의해주세요.")
            }
        })
    alert("이메일이 변경되었습니다.");
}



function submitPwdChange(form) { // 마이페이지 비밀번호변경 모달 변경버튼

    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    var pw = $("#password").val();
        var num = pw.search(/[0-9]/g);
        var eng = pw.search(/[a-z]/ig);
        var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    // ----------유효성검사------------
        form.password.value = form.password.value.trim();
        if (pw.length < 8 || pw.length > 20) {
            alert('비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.');
            form.password.focus();
            return false;
        }else if(num < 0 || eng < 0 || spe < 0 ){
            alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
            form.password.focus();
            return false;
        }
        // ----------유효성검사 끝------------

    form.password.value = form.password.value.trim();
    if (form.password.value.length == 0) {
        alert('비밀번호를 입력해주세요.');
        form.password.focus();
        return false;
    }
    form.chkPW.value = form.chkPW.value.trim();
    if (form.password.value != form.chkPW.value) {
        alert('비밀번호 확인이 일치하지 않습니다.');
        form.chkPW.focus();
        return false;
    }

    form.submit();
    alert("비밀번호 변경이 완료되었습니다.");
}


var compare_result = false;

function fn_compare_pwd() { // 비밀번호 일치여부
    var pwd1 = $('#password').val();
    var pwd2 = $('#chkPW').val();

    if (pwd1 == pwd2) {
        compare_result = true;
        $('#s_result').text('일치합니다.');
    } else {
        compare_result = false;
        $('#s_result').text('일치하지 않습니다.');
    }
}
