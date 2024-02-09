<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/top.jspf"%>
<div class=""><a href="#" id="" name="" class="" ><h4>유닛로고</h4></a></div>
<ul class="">
	<c:if test="${isTokenValue == null}">
		<li class=""><a class="" id="" name="" href="http://localhost:8080/login.do">로그인</a></li>
		<li class=""><a class="" id="" name="" href="http://localhost:8080/user/userCreate.do">회원가입</a></li>
	</c:if>
	<c:if test="${isTokenValue != null}">
		<li class=""><a class="" id="" name="" href="http://localhost:8080/login.do">마이페이지(login.do)</a></li>
		<li class=""><a class="" id="" name="" href="http://localhost:8080/user2.do">관리자만 가능(user2.do)</a></li>
		<li class=""><a class="" id="" name="" href="http://localhost:8080/logout.do">로그아웃</a></li>
	</c:if>
	<!-- <li class=""><a class="" id="" name="" href="http://localhost:8080/login.do">로그인</a></li>
	<li class=""><a class="" id="" name="" href="http://localhost:8080/user/userCreate.do">회원가입</a></li>
	<li class=""><a class="" id="" name="" href="http://localhost:8080/logout.do">로그아웃</a></li> -->
</ul>