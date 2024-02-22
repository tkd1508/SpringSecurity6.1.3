<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/top.jspf"%>

<script type="text/javascript">
  
  // ajax 통신 함수
  function ajaxTest() {
	  $.ajax({
		 url: '/sample/ajaxTest',
		 type: 'post',
		 dataType: 'json',
		 data: {},
		 success: function(res){
         // ajax 통신 성공시 호출
			 console.log(res);
		 }
	  });
  }
  </script>
<div class=""><a href="#" id="" name="" class="" ><h4>유닛로고</h4></a></div>
<ul class="">
	<c:if test="${isTokenValue == null}">
		<li class=""><a class="" id="" name="" href="http://localhost:8080/login.do">로그인</a></li>
		<li class=""><a class="" id="" name="" href="http://localhost:8080/user/userCreate.do">회원가입</a></li>
	</c:if>
	<c:if test="${isTokenValue != null}">
		<li class=""><a class="" id="" name="" href="http://localhost:8080/login.do">마이페이지(login.do)</a></li>
		<li class=""><a class="" id="" name="" href="http://localhost:8080/user2.do">관리자만 가능(user2.do)</a></li>
		<div class="pull-right">
	        <button type="button" class="btn btn-sm btn-primary" onclick="ajaxTest();">ajax테스트</button>
	    </div>
		<li class=""><a class="" id="" name="" href="http://localhost:8080/logout.do">로그아웃</a></li>
	</c:if>
	<!-- <li class=""><a class="" id="" name="" href="http://localhost:8080/login.do">로그인</a></li>
	<li class=""><a class="" id="" name="" href="http://localhost:8080/user/userCreate.do">회원가입</a></li>
	<li class=""><a class="" id="" name="" href="http://localhost:8080/logout.do">로그아웃</a></li> -->
</ul>