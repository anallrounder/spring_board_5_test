<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
//https://api.jquery.com/serialize/
//https://api.jquery.com/submit/s
//$("#modify").click(function(){

$("form").on("submit", function(event) {
	  event.preventDefault();
	  console.log($(this).serialize());
	  
	  $ajax({ 
		  type:'PUT',
	      url: url,
	      data: data,
	      cache : false, 
	      dataType: 'json',
	      success:
	  
	  }); //end of ajax
	  
	  
	});
</script>

</head>
<body>
	<table id="list-table" width="500" cellpadding="0" cellspacing="0"
		border="1">
		<form action="${pageContext.request.contextPath}/restful/board/${content_view.bId}" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<td>번호</td>
				<td>${content_view.bId}</td>
			</tr>
			<tr>
				<td>히트</td>
				<td>${content_view.bHit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input id="f1" type="text" name="bName" value="${content_view.bName}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input id="f2" type="text" name="bTitle" value="${content_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="f3" rows="10" name="bContent">${content_view.bContent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input id='modify' type="submit" value="수정">
					&nbsp;&nbsp; 
					<a href="${pageContext.request.contextPath}/restful/board">목록보기</a>
					&nbsp;&nbsp; 
					<a id="a-del" href="${pageContext.request.contextPath}/restful/board/${vo.bId}">삭제</a>
					&nbsp;&nbsp; 
					<a href="reply_view?bId=${content_view.bId}">답변</a></td>
			</tr>
		</form>
	</table>

</body>
</html>