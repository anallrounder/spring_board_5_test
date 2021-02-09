<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REST Delete</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	/* if (typeof jQuery == 'undefined') { 
		var script = document.createElement('script'); 
		script.type = "text/javascript"; 
		script.src = "http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"; 
		document.getElementsByTagName('head')[0].appendChild(script); 
	}  */
	
	//test : jQuery문제는 아님.
/* 	$(document).ready(function(){
	    $('#test_id').click(function(){
	        alert("얍");
	    });
	});
 */
 
	 $(document).ready(function() {
		$(".a-del").click(function(event) { // id가 아니고 클래스로 받아오는것이 첫번째 포인트! 
			event.preventDefault();
			//alert("a-del click");
			console.log("a-del click");

			var tr = $(this).parent().parent();

			$.ajax({
				type : 'DELETE', //method
				url : $(this).attr("href"), //주소를 받아오는 것이 두 번째 포인트.
				cache : false,
				success : function(result) {
				//	alert("result: " + result)
					console.log("result: " + result);
					if (result == "SUCCESS") {
						$(tr).remove();
					//	alert("삭제되었습니다.");
					}
				},
				errer : function(e) {
					console.log(e);
				}
			}); //end of ajax
		}); //end of $().click

	});
</script>

</head>
<body>
	<a id="test_id" href="https://google.com">test</a></p> 
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.bId}</td>
				<td>${vo.bName}</td>
				<td><c:forEach begin="1" end="${vo.bIndent}">-</c:forEach> <a
					id="a-content"
					href="${pageContext.request.contextPath}/restful/board/${vo.bId}">${vo.bTitle}</a></td>
				<td>${vo.bDate}</td>
				<td>${vo.bHit}</td>
				<td><a class="a-del" href="${pageContext.request.contextPath}/restful/board/${vo.bId}">삭제</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="write_view">글작성</a></td>
		</tr>
	</table>
</body>
</html>

