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
	
	  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
      var csrfToken = $("meta[name='_csrf']").attr("content");
      
      console.log(csrfHeader);
	  
	  $.ajax({
		 url: '/api/messages.do',
		 type: 'get',
		 async: false,
		 //dataType: 'json',
		 //contentType: "application/json",
		 data: {},
		 beforeSend : function(xhr) {
			 xhr.setRequestHeader(csrfHeader, csrfToken);
		 },
		 success: function(res){
         // ajax 통신 성공시 호출
			 console.log(res);
		 },
		 error: function(xhr, status, error) {
        // 요청이 실패했을 때 실행될 콜백 함수
        console.log('Error:', xhr.responseText);
        if(xhr.status == '401'){
			window.location.href = '/login.do?error=true&exception=' + xhr.responseJSON.message;
		}else if(xhr.status == '403'){
			window.location.href = '/api/denied.do?exception=' + xhr.responseJSON.message;
		}
        
        
        
    }
	  });
  }
  
  function admin() {
	  
	  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
      var csrfToken = $("meta[name='_csrf']").attr("content");
	  
	  $.ajax({
		 url: '/api/login',
		 type: 'post',
		 async: false,
		 dataType: 'json',
		 //contentType: "application/json",
		 data: JSON.stringify({
			 user_name : "admin",
  			 password : "1234rf"
		 }),
		 beforeSend : function(xhr) {
			 xhr.setRequestHeader(csrfHeader, csrfToken);
		 },
		 success: function(response){ // ajax 통신 성공시 호출
			if(response.status === "success") {
	            // 서버로부터 받은 리다이렉트 URL로 페이지 이동
	            debugger;
	            console.log("Session ID from server: " + response.sessionId);
	            getMessege();
	        }
		 },
		 error: function(xhr) {
			 debugger;
			 window.location.href = '/login.do?error=true&exception=' + xhr.responseText
            console.log('Error:', xhr.responseText);
        }
	  });
  }