<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/top.jspf"%>

<script type="text/javascript" src="<c:url value='/js/login/login.js'/>"></script>
</head>
<%
String id = "";
String remember = "";
boolean ison = false;
String cookie = request.getHeader("Cookie"); // 쿠키 불러와
if (cookie != null) { // 쿠키가 있다면
	Cookie[] cookies = request.getCookies(); // 배열화하고
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals("userId")) {
	id = cookies[i].getValue();
		}
		if (cookies[i].getName().equals("remember")) {
	remember = cookies[i].getValue();
	ison = true;
		}

	}
}
%>
<body>

<c:if test="${error != null}">
	<span>${exception}</span>
</c:if>


<form id="aform" name="aform" method="post"  >
<input type="hidden" id="" name="secret_key" value="kkk">
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>

	<div class="form-group">
		<label for="userId">아이디</label> 
		<input type="text" id="username" name="username" class="form-control" placeholder="아이디" <%if (ison) {%> value="<%=id%>" <%}%> required autofocus>
	</div>
	<div class="form-group">
		<label for="userPw">PASSWORD</label> 
		<input type="text" name="password" id="password" class="form-control" placeholder="비밀번호" required>
	</div>

	<div class="from-group">
		<button type="button" id="loginBtn" class="btn btn-info form-control" title="로그인을 시도합니다.">로그인</button>
	</div>

	<div class="form-group">
		<input type="checkbox" id="remember" name="remember" value="on" <%if (ison) {%> checked="<%=remember%>" <%}%>>
		<label for="remember">아이디기억하기</label>
		<div>
			<a href="search_login.jsp" title="" id="">아이디/비밀번호 찾기</a>
			
			
			
			안녕하세요
		</div>
	</div>
	
	<!-- 간편로그인 이미지 -->
	<!-- <div class="from-group" style="text-align: center">
		<a href="https://www.naver.com" class="btn btn-success naver" title="#">네이버</a>
		<div>
			<a
				href="https://kauth.kakao.com/oauth/authorize?client_id=0654a6d47b4e64a0e4184e4bb96f960e&redirect_uri=http://sanguk1508.cafe24.com/joinboard_popol/KakaoLoginApi&response_type=code">
				<img src="../inc/imgs/kakao_login_medium_narrow.png">
			</a>
		</div>
		<a href="https://www.google.com" class="btn btn-danger google" title="#">구글</a>
	</div> -->

	<div class="from-group">
		<button type="button" id="userCreateBtn" class="btn btn-info form-control" title="회원가입을 시도합니다.">회원가입</button>
	</div>

	<div class="pull-right">
        <button type="button" class="btn btn-sm btn-primary" onclick="getMessege();">getMessege()</button>
        <button type="button" class="btn btn-sm btn-primary" onclick="admin();">admin()</button>
    </div>















</form>
</body>
</html>