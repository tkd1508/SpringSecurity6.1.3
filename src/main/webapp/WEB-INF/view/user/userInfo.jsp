<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="/WEB-INF/view/common/top.jspf"%>

<script type="text/javascript" src="<c:url value='/user/userInfo.js'/>"></script>
</head>
<body>
<form id="aform" name="aform" method="post" >
<input type="hidden" id="ip" name="ip" value="${input.ip}">

	<div class="form-group">
		<label for="userId">아이디</label> 
		<input type="text" id="userId" name="userId" class="form-control" placeholder="아이디" >
	</div>
	<div class="form-group">
		<label for="userPw">PASSWORD</label> 
		<input type="text" name="password" id="password" class="form-control" placeholder="비밀번호">
	</div>
	<div class="form-group">
		<label for="user_name">user_name</label> 
		<input type="text" name="user_name" id="user_name" class="form-control" placeholder="user_name">
	</div>
	<div class="form-group">
		<label for="email">email</label> 
		<input type="email" name="email" id="email" class="form-control" placeholder="email">
	</div>
	<div class="form-group">
		<label for="age">age</label> 
		<input type="text" name="age" id="age" class="form-control" placeholder="age">
	</div>
	<div class="form-group">
		<label for="role">role</label> 
		<input type="text" name="role" id="role" class="form-control" placeholder="role">
	</div>

	<div class="from-group">
		<button id="userInfoBtn" class="btn btn-info form-control">회원가입</button>
	</div>
	<!-- <div class="form-group">
		<input type="checkbox" id="remember" name="remember" value="on" >
		<label for="remember">아이디기억하기</label>
		<div>
			<a href="search_login.jsp" title="" id="">아이디/비밀번호 찾기</a>
		</div>
	</div> -->
	
	<%-- <div class="from-group">
		<a href="${pageContext.request.contextPath}/join.join" class="btn btn-info join" title="#">회원가입</a>
	</div> --%>

</form>
</body>
</html>