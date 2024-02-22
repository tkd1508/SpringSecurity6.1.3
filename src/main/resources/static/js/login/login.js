/**
 * 
 */
/**
 * 
 */
$(document).ready(function(){
	//vaildation();
	
	$("#loginBtn").click(function(){
		userInfoBtn();
	});
	
	$("#userCreateBtn").click(function(){
		userCreateBtn();
	});
	
});

function userInfoBtn(){
	document.aform.action = "/login-process.do";
	document.aform.submit();
}

function userCreateBtn(){
	document.aform.action = "/user/userCreate.do";
	document.aform.submit();
}

function vaildation(){
	if ($("#userId").val() == "") {
		alert("아이디 입력해주세요.");
		$("#userId").focus();
		return false;
	}
	if ($("#userPw").val() == "") {
		alert("비밀번호 입력해주세요.");
		$("#userPw").focus();
		return false;
	}
}

function ajaxTest() {
	  $.ajax({
		 url: '/sample/ajaxTest',
		 type: 'post',
		 dataType: 'json',
		 contentType: "application/json",
		 data: {},
		 success: function(res){
         // ajax 통신 성공시 호출
			 console.log(res);
		 },
		 error: function(xhr, status, error) {
            console.error('Error:', xhr.responseText);
        }
	  });
  }
  
function getMessege() {
	  $.ajax({
		 url: '/api/messages.do',
		 type: 'get',
		 async: false,
		 dataType: 'json',
		 //contentType: "application/json",
		 data: {},
		 success: function(res){
         // ajax 통신 성공시 호출
			 console.log(res);
		 },
		 error: function(xhr, status, error) {
        // 요청이 실패했을 때 실행될 콜백 함수
        console.log('Error:', xhr.responseText);
    }
	  });
  }
  
  function admin() {
	  $.ajax({
		 url: '/api/login',
		 type: 'post',
		 async: false,
		 dataType: 'json',
		 //contentType: "application/json",
		 data: JSON.stringify({
			 user_name : "manager",
  			 password : "1234rf"
		 }),
		 success: function(response){ // ajax 통신 성공시 호출
			if(response.status === "success") {
	            // 서버로부터 받은 리다이렉트 URL로 페이지 이동
	            debugger;
	            console.log("Session ID from server: " + response.sessionId);
	            window.location.href = response.redirect;
	        }
		 },
		 error: function(xhr) {
			 debugger;
            console.log('Error:', xhr.responseText);
        }
	  });
  }