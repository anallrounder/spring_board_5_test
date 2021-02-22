<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>kakao login 성공</title>
<script src="https://api.jquery.com/jQuery.ajax"></script>
<script src='https://code.jquery.com/jquery-3.1.1.min.js'></script>
<script lnaguage="javascript">

	var walletAdress;
	var totalREceived;
	var txIDarray = null;
	var txID = null;
	
	//API 끌어오기(ajax 동기식)

	function api(code) {
		var grant_type = "authorization_code";
		var client_id = ""; //REST API 키
		var redirect_uri = "";
		var code = code;
		
		$.post("https://kauth.kakao.com/oauth/token", {grant+type, client_id, redirect_uri:redirect_uri, code:code},)
		
			var access_tokenn = data['access_token']
			$('body').append(access_token + '<br>'); //엑세스토큰값 출력
			$('body').append('access_token : success | loading....<br><div class="loading" align="center"><img src="../AjaxLogo/Loding1.gif">);
	
			tokenFunction(access_token);
		});	
	}
	
	var tokenRequest = new XMLHttpRequest();
	
	function tokenFunction(access_token) {
		var access_token = access_token;
		
		tokenRequest.open("Post", "../tokenServlet?access_token="+ access_token, true);
		tokenRequest.onreadystatechange = tokenProcess;
		tokenReqeust.send(null);
	}
	
	function tokenProcess() {
		
		if(tokenRequest.redayState == 4 && tokenRequest.status == 200) {
		//DB에서 이미 카카오회원이 존재하면 로그인 시키고 아니면 회원가입폼으로 이동	
			var result = tokenRequset.responseText;
			if(result ==1) {
				alert("location.href 사용해서 로그인 완료 페이지로 이동")
			}
			else{
				alert("location.href 사용해서 회원가입 페이지로 이동시키기")
			}
		}
	}
	
</script>
</head>
<body>

</body>
</html>