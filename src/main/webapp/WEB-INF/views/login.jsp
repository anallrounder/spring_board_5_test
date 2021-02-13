<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>로그인</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
	integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
	crossorigin="anonymous"></script>
<style>
#userId {
	box-sizing: border-box;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
	margin-bottom: -9px;
}

#userPass {
	box-sizing: border-box;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

#wrapper {
	position: absolute;
	top: 20%;
	bottom: 20%;
	right: 20%;
	left: 20%;
}
/* 왜 부트스트랩으로 class="position:absolute top-20 left:20" 이런식으로 주면 안먹는지 의문...!!! */
</style>
</head>

<body class="bg-light text-center">
	<%
	String path = request.getContextPath();
	%>
	<%-- <%=path%>  --%>
	<div id="wrapper" class="mx-auto p-0"
		style="text-align: center; width: 100%; max-width: 300px;">
		

		<c:if test="${user == null}">
			<!-- 유저가 없으면 (컨트롤러에서 모델로 넘어옴) 이걸 태운다.아니면 아래거를 태움 -->

			<form class="form-signin" role="form-signin" method="post"
				autocomplete="off" action="<%=path%>/login">
				<img src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg"
					class="img-fluid rounded mx-auto d-block" style="width: 70px;" /> <br>
			<h3 class="text-center font-weight-normal mb-3">Please sign in</h3>
			<!-- el로 처리하는게 더 좋다. ${pageContex.request.contextPath} - 이건 그냥 확인용-->
				<div class="mb-3">
					<div class="bd-highlight position-relative">
						<label for="userId" class="form-label"></label> 
						<input type="text" id="userId" class="form-control mx-auto p-4" name="id" placeholder="Email address" />
					</div>
					<div class="bd-highlight position-relative">
						<label for="userPass" class="form-label"></label> 
						<input type="password" id="userPass" class="form-control mx-auto p-4" name="pw" placeholder="Password" />
					</div>
				</div>
				<div class="form-check mx-auto mb-4">
					<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> 
					<label class="form-check-label" for="flexCheckDefault"> Remember me </label>
				</div>

				<button class="btn btn-primary btn-lg btn-block mx-auto" type="submit">Sign in</button>
			</form>
			<p class="text-center text-secondary mt-5">© 2017-2018</p>
		</c:if>
	
	
	<c:if test="${msg == false}">
		<p style="color: #f00;">로그인에 실패했습니다. 아이디 또는 패스워드를 다시 입력해주십시오.</p>
	</c:if>
	
	<c:if test="${user != null}">
		<p>${user.username}님환영합니다.</p>

		<!-- <a href="member/modify">회원정보 수정</a>, <a href="member/withdrawal">회원탈퇴</a><br/> -->
		<a href="<%=path%>/board/list">게시판 리스트</a>
		<br>
		<a href="<%=path%>/logout">로그아웃</a>
	</c:if>
	</div>
</body>
</html>








