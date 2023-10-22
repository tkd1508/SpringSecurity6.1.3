<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/top.jspf"%>

<%-- <script type="text/javascript" src="<c:url value='/js/login/denied.js'/>"></script> --%>
</head>
<body>

	<span>${username}님은 접근 권한이 없습니다.</span>
	<span>${exception}</span>

</body>
</html>