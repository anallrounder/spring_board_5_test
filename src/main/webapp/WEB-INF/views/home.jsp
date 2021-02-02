<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="https://images.unsplash.com/photo-1432107294469-414527cb5c65?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80">이미지</a>

<img alt="img" src="${pageContext.request.contextPath}/resources/test/a.jpg" style="width:500px"> 


<!-- 절대경로로 만들어주는게 중요하다. 가장많이쓰는방법은 다이렉트로 el로 쓰는방법이있다. 
${pageContext.request.contextPath} = http://localhost:8282/ 이걸 자동으로 깔아준다.
띄어쓰기 주의!, 그리고 $앞에는 / 달아주면 절대 안된다. -->
</body> 
</html>
