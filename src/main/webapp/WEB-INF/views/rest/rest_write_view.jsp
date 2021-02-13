<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#insertForm").submit(function(event){
			event.preventDefault();
			
			var bName = $("bName").val();
			var bTitle = $("bTitle").val();
			var bContent = $("bContent").val();
			
			var form = {
					bName:bName,
					bTitle:bTitle,
					bContent:bContent
			}
			
			$.ajax({
				type:"POST",
				url:$(this).attr("action"),
				cache:false,
				//dataType : "json",
				contentType:'application/json; charset=utf-8',
				data: JSON.stringify(form), 
				success: function(result){
					if(result == "SUCCESS"){
						$(location).attr('href','${pageContext.request.contextPath}/restful/board')
					}
				},
				error: function(e){
					console.log(e);
				}
				
			})//end of ajax
		}) //end of submit event
	
		
	}) 
	
</script>
</head>
<body>
		<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form id="insertForm" action="${pageContext.request.contextPath}/restful/board/rest_write" method="post">  <!-- 수정 -->
			<tr>
				<td> 이름 </td>
				<td> <input id="bName" type="text" name="bName" size = "50"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input id="bTitle" type="text" name="bTitle" size = "50"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea id="bContent" name="bContent" rows="10" ></textarea> </td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/restful/board">목록 보기</a>_</td>
			</tr>
		</form>
	</table>
</body>
</html>