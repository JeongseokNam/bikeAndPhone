$("#change_pwd_btn").click(function () {
    let password = $("#password").val();
    let rePassword = $("#re_password").val();
    var num = password.search(/[0-9]/g);
    var eng = password.search(/[a-z]/ig);
    var spe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    if (password.length < 8 || password.length > 20) {
        alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.");
    } else if (password.search(/\s/) != -1) {
        alert("비밀번호는 공백 없이 입력해주세요.");
    } else if (num < 0 || eng < 0 || spe < 0) {
        alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
    } else if (password != rePassword) {
        alert("신규 비밀번호가 일치하지 않습니다.")
    } else {
        $.ajax({
            type: "POST",
            data: {"inputPassword": password, "memberId": $("#memberId").val()}, //입력받은 아이디값
            url: "/member/pwdChange",
            success: function (flag) {
                if (flag) {
                    alert("비밀번호가 변경되었습니다.")
                    location.href = '/member/login'
                } else {
                    alert("통신 오류입니다. 관리자에게 문의해주세요.")
                }
            },
            error: function () {
                alert("통신 오류입니다. 관리자에게 문의해주세요.")
            }
        })
    }
})