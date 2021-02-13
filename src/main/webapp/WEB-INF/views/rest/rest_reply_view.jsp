<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(document).ready(function(){
		$("#replyForm").submit(function(event) {
			event.preventDefault();
			
			var bId=$("#bId").val();
			var bGroup=$("#bGroup").val();	
			var bStep=$("#bStep").val();
			var bIndent=$("#bIndent").val();
			
			var bName=$("#bName").val();
			var bTitle=$("#bTitle").val();
			var bContent=$("#bContent").val();
			
			console.log(bContent);
	        console.log($(this).attr("action"));
			
	        var form = {
				bId:bId,
				bGroup:bGroup,
				bStep:bStep,
				bIndent:bIndent,
				bName:bName,
				bTitle:bTitle,
				bContent:bContent
			};
			
			$.ajax({
				type : "POST",
				url : $(this).attr("action"),
				cache : false,
				contentType : 'application/json; charset=UTF-8',
				//dataType : "json", -> 데이터 타입을 적으면 성공했음에도 불구하고 에러로 처리되어 리스트로 넘어가지 않는다.
				//parsererror _  https://vvh-avv.tistory.com/159 
				data: JSON.stringify(form), 
				success: function (result) {       
		               if(result == "SUCCESS"){
		                  //list로 
		                  $(location).attr('href', '${pageContext.request.contextPath}/restful/board')                            
		               }                       
		             },
		             error: function (e) {
		                 console.log(e);
		             }
				
			}); // end of ajax
		}); // end of submit event
	})

</script>

</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form id="replyForm" action="${pageContext.request.contextPath}/restful/board/reply/${reply_view.bId}" method="post">
			<input type="hidden" id="bId" value="${reply_view.bId}" />
			<input type="hidden" id="bGroup" value="${reply_view.bGroup}" /> 
			<input type="hidden" id="bStep"  value="${reply_view.bStep}" /> 
			<input type="hidden" id="bIndent"  value="${reply_view.bIndent}" />
			<tr>
				<td>번호</td>
				<td>${reply_view.bId}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${reply_view.bHit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input id="bName" type="text" name="bName" value="${reply_view.bName}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input id="bTitle" type="text" name="bTitle" value="${reply_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="bContent" rows="10" name="bContent" value="${reply_view.bContent}">${reply_view.bContent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="답변"> &nbsp;
					&nbsp; <a href="${pageContext.request.contextPath}/restful/board">목록</a></td>
			</tr>

		</form>
	</table>
</body>
</html>