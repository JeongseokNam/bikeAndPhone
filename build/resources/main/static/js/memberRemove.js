let PwFlag = true;

        function PwCheck() {
    	    $.ajax({
    	        type: "POST",
    	        url: "/member/PwCheck",
    	        data: {"inputPw" : $('#password').val()},
    	        success : function (data){
    	            if(data){
    	                alert("비밀번호가 일치합니다.\n회원탈퇴 버튼을 클릭해주세요.")
                        PwFlag = false;
    	            }else {
    	                alert("비밀번호가 틀립니다.");
    	                PwFlag = true;
    	            }
    	        }
    	    })
    	}

$(document).ready(function(){
    var frm = document.querySelector('#frm');
            frm.addEventListener('submit', function (event) {
                event.preventDefault(); // 기본동작 막기

                frm.password.value = frm.password.value.trim();
                if (frm.password.value.length == 0) {
                    alert('비밀번호를 입력해주세요.');
                    frm.password.focus();
                    return false;
                }
                if (PwFlag){
                    alert("비밀번호확인 버튼을 클릭 해주세요");
                    return false;
                }

                var isRemove = confirm('정말 회원탈퇴 하시겠습니까?');

                if (!isRemove) {
                    return;
                }
                frm.submit();
            });
    })
