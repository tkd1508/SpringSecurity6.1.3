<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="/WEB-INF/view/common/top.jspf"%>

<script type="text/javascript" src="<c:url value='js/user/login2.js'/>"></script>
</head>
<body>
<form id="aform" name="aform" method="post" >

	<div class="form-group">
		<img src="" style="" alt="에헴"/>
	</div>

	<div class="form-group">
		<label for="userId">아이디</label> 
		<input type="text" id="username" name="username" class="form-control" placeholder="아이디" >
	</div>
	<div class="form-group">
		<label for="userPw">PASSWORD</label> 
		<input type="text" name="password" id="password" class="form-control" placeholder="비밀번호">
	</div>

	<div class="from-group">
		<button type="button" id="userInfoBtn" class="btn btn-info form-control">로그인</button>
	</div>

</form>
</body>
</html>