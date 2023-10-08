/**
 * 
 */
$(document).ready(function(){
	//vaildation();
	
	$("#userInfoBtn").click(function(){
		userInfoBtn();
	});
});

function userInfoBtn(){
	debugger;
	document.aform.action = "/userInfo.do";
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