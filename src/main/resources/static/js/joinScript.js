let idFlag = true;
let nickFlag = true;

function idCheck() { // 회원가입 시 아이디 중복체크
    $.ajax({
        type: "POST",
        data: {"inputId": $("#username").val()}, //입력받은 아이디값
        url: "/member/idCheck",
        success: function (flag) {
            if (flag) {
                alert("아이디가 중복되었습니다.")
                idFlag = true;
            } else {
                alert("사용 가능한 아이디 입니다.")
                idFlag = false;
            }
        },
        error: function () {
            alert("통신 오류입니다. 관리자에게 문의해주세요.")
        }
    })
}

function nickCheck() { // 회원가입 및 마이페이지 닉네임변경 중복체크
    $.ajax({
        type: "POST",
        data: {"inputNick": $("#nickName").val()}, //입력받은 닉네임값
        url: "/member/nickCheck",
        success: function (flag) {
            if (flag) {
                alert("사용중인 닉네임입니다.")
                nickFlag = true;
            } else {
                alert("사용 가능한 닉네임입니다.")
                nickFlag = false;
            }
        },
        error: function () {
            alert("통신 오류입니다. 관리자에게 문의해주세요.")
        }
    })
}

function submitNickChange(form) { // 마이페이지 닉네임 변경모달 완료버튼
    if (nickFlag) {
        alert("닉네임 중복체크를 해주세요");
        return false;
    }

    form.submit();
    alert("닉네임이 변경되었습니다.\n로그인페이지로 이동합니다.");
}

function submitPwdChange(form) { // 마이페이지 비밀번호변경 모달 변경버튼

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

$(document).ready(function () { // 회원가입 시 약관동의
    $("#chk_checkall").click(function () {
        if ($("#chk_checkall").is(":checked")) $("input[class=chk]").prop("checked", true);
        else $("input[class=chk]").prop("checked", false);
    });

    $("input[name=chk]").click(function () {
        var total = $("input[class=chk]").length;
        var checked = $("input[class=chk]:checked").length;

        if (total != checked) $("#chk_checkall").prop("checked", false);
        else $("#chk_checkall").prop("checked", true);
    });
});


function submitJoinForm(form) { // 회원가입 버튼

    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    var pw = $("#password").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    form.username.value = form.username.value.trim();
    if (form.username.value.length == 0) {
        alert('로그인 아이디를 입력해주세요.');
        form.username.focus();
        return false;
    }
    if (nickFlag) {
        alert("닉네임 중복체크를 해주세요");
        form.nickName.focus();
        return false;
    }

    if (idFlag) {
        alert("아이디 중복체크를 해주세요");
        form.username.focus();
        return false;
    }

    // ----------유효성검사------------
    form.password.value = form.password.value.trim();
    if (pw.length < 8 || pw.length > 20) {
        alert('비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.');
        form.password.focus();
        return false;
    } else if (num < 0 || eng < 0 || spe < 0) {
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
    //--이용정보 동의
    let agree1 = $("#chk_join-terms-fourteen").is(':checked');
    let agree2 = $("#chk_join-terms-service").is(':checked');
    let agree3 = $("#chk_join-terms-privacy-collect-use").is(':checked');
    if (!agree1) {
        alert("필수 동의사항을 확인해 주세요.");
        $("#chk_join-terms-fourteen").focus();
        return false;
    }
    if (!agree2) {
        alert("이용약관 동의를 확인해 주세요.");
        $("#chk_join-terms-service").focus();
        return false;
    }
    if (!agree3) {
        alert("개인정보 수집 및 이용 동의를 확인해 주세요.");
        $("#chk_join-terms-privacy-collect-use").focus();
        return false;
    }
    form.submit();
    alert("회원가입을 축하드립니다.");
}

function submitSoleProprietorJoinForm(form) { //개인사업자회원가입 버튼

    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    var pw = $("#password").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    form.username.value = form.username.value.trim();
    if (form.businessName.value.length == 0) {
        alert('상호명을 입력해주세요.');
        form.businessName.focus();
        return false;
    }
    if (form.ceoName.value.length == 0) {
        alert('대표자 성명을 입력해주세요.');
        form.ceoName.focus();
        return false;
    }
    if (form.businessNum.value.length == 0) {
        alert('사업자 등록번호를 입력해주세요.');
        form.businessNum.focus();
        return false;
    }
    if (form.businessLocation.value.length == 0) {
        alert('사업장소재지 를 입력해주세요.');
        form.businessLocation.focus();
        return false;
    }
    if (form.businessCondition.value.length == 0) {
        alert('사업종류 : 업태를 입력해주세요.');
        form.businessCondition.focus();
        return false;
    }
    if (form.businessItem.value.length == 0) {
        alert('사업종류 : 종목을 입력해주세요.');
        form.businessItem.focus();
        return false;
    }
    if (form.username.value.length == 0) {
        alert('로그인 아이디를 입력해주세요.');
        form.username.focus();
        return false;
    }
    if (nickFlag) {
        alert("닉네임 중복체크를 해주세요");
        form.nickName.focus();
        return false;
    }

    if (idFlag) {
        alert("아이디 중복체크를 해주세요");
        form.username.focus();
        return false;
    }

    // ----------유효성검사------------
    form.password.value = form.password.value.trim();
    if (pw.length < 8 || pw.length > 20) {
        alert('비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.');
        form.password.focus();
        return false;
    } else if (num < 0 || eng < 0 || spe < 0) {
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
    //--이용정보 동의
    let agree1 = $("#chk_join-terms-fourteen").is(':checked');
    let agree2 = $("#chk_join-terms-service").is(':checked');
    let agree3 = $("#chk_join-terms-privacy-collect-use").is(':checked');
    if (!agree1) {
        alert("필수 동의사항을 확인해 주세요.");
        $("#chk_join-terms-fourteen").focus();
        return false;
    }
    if (!agree2) {
        alert("이용약관 동의를 확인해 주세요.");
        $("#chk_join-terms-service").focus();
        return false;
    }
    if (!agree3) {
        alert("개인정보 수집 및 이용 동의를 확인해 주세요.");
        $("#chk_join-terms-privacy-collect-use").focus();
        return false;
    }
    form.submit();
    alert("회원가입을 축하드립니다.");
}
function corporateJoinForm(form) { //법인사업자회원가입 버튼

    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    var pw = $("#password").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    form.username.value = form.username.value.trim();
    if (form.businessName.value.length == 0) {
        alert('상호명을 입력해주세요.');
        form.businessName.focus();
        return false;
    }
    if (form.ceoName.value.length == 0) {
        alert('대표자 성명을 입력해주세요.');
        form.ceoName.focus();
        return false;
    }
    if (form.businessNum.value.length == 0) {
        alert('사업자 등록번호를 입력해주세요.');
        form.businessNum.focus();
        return false;
    }
    if (form.corporateNum.value.length == 0) {
        alert('법인사업자 등록번호를 입력해주세요.');
        form.corporateNum.focus();
        return false;
    }
    if (form.businessLocation.value.length == 0) {
        alert('사업장소재지 를 입력해주세요.');
        form.businessLocation.focus();
        return false;
    }
    if (form.businessCondition.value.length == 0) {
        alert('사업종류 : 업태를 입력해주세요.');
        form.businessCondition.focus();
        return false;
    }
    if (form.businessItem.value.length == 0) {
        alert('사업종류 : 종목을 입력해주세요.');
        form.businessItem.focus();
        return false;
    }
    if (form.username.value.length == 0) {
        alert('로그인 아이디를 입력해주세요.');
        form.username.focus();
        return false;
    }
    if (nickFlag) {
        alert("닉네임 중복체크를 해주세요");
        form.nickName.focus();
        return false;
    }

    if (idFlag) {
        alert("아이디 중복체크를 해주세요");
        form.username.focus();
        return false;
    }

    // ----------유효성검사------------
    form.password.value = form.password.value.trim();
    if (pw.length < 8 || pw.length > 20) {
        alert('비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.');
        form.password.focus();
        return false;
    } else if (num < 0 || eng < 0 || spe < 0) {
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
    //--이용정보 동의
    let agree1 = $("#chk_join-terms-fourteen").is(':checked');
    let agree2 = $("#chk_join-terms-service").is(':checked');
    let agree3 = $("#chk_join-terms-privacy-collect-use").is(':checked');
    if (!agree1) {
        alert("필수 동의사항을 확인해 주세요.");
        $("#chk_join-terms-fourteen").focus();
        return false;
    }
    if (!agree2) {
        alert("이용약관 동의를 확인해 주세요.");
        $("#chk_join-terms-service").focus();
        return false;
    }
    if (!agree3) {
        alert("개인정보 수집 및 이용 동의를 확인해 주세요.");
        $("#chk_join-terms-privacy-collect-use").focus();
        return false;
    }
    form.submit();
    alert("회원가입을 축하드립니다.");
}