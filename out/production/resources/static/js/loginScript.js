$("#loginBtn").on("click",function(e){
    if (!$("#username").val()) {
        alert("아이디를 입력하세요.");
        e.preventDefault();
    } else if (!$("#password").val()) {
        alert("비밀번호를 입력하세요.");
        e.preventDefault();
    }
});

//테스트
function LoginTestData() { // 로그인 테스트 데이터
    var form = document.test;

    form.username.value = 'gustj5071';
    form.password.value = 'gus3815gus3815*';
}