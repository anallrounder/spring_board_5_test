<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kakao callback</title>
</head>
<body>
	<% String path = request.getContextPath();	%>
	
	<%-- <c:if test="${userId eq null}">
        <a href="<%=path%>">
           <p>로그인 하러 가기</p>
        </a>
    </c:if> --%>
	
	<%-- <c:if test="${user ne null}"> --%>
		<div class="mb-3">
			<h4>${nickname}님 환영합니다.</h4>
		</div>
		
		<p>닉네임 : ${nickname}</p>
		<p>이메일 : ${email}</p>
		
		<!-- <a href="member/modify">회원정보 수정</a>, <a href="member/withdrawal">회원탈퇴</a><br/> -->
		<a href="<%=path%>/board/list">게시판 리스트</a>
		<br>
		<a href="<%=path%>/logout">로그아웃</a>
	<%-- </c:if> --%>

	</div>
	<div id="dropDownSelect1"></div>
</body>
</html>