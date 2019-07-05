<!--  현재 이 jsp 페이지 실행 후 생성되는 문서는 html 이고 이문서는 utf-8 방식으로 인코딩한다. -->

<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- jsp 기술의 한 종류인 include Directive를 이용하여 common.jsp 파일 내의 소스를 삽입하기 -->
<%@include file="common.jsp" %>
<script>
	$(document).ready(function() {
		
		//$(".admin_id").val("abc");
		//$(".pwd").val("abc123");
		inputData("admin_id",'${cookie.admin_id.value}');
		inputData("pwd",'${cookie.pwd.value}');
		
		<c:if test="${!empty cookie.admin_id.value}">
		inputData("is_login","y")
		</c:if>
		
		$("[name=loginForm] .login").click(function() {
			checkLoginForm();
		});
		//$('[name="loginForm"]').find('. login').click(function(){
	});
	function checkLoginForm() {
		// 입력된 아이디를 가져와 변수에 저장
		var admin_id = $(".admin_id").val();
		// 아이디를 입력 안했으면 경고하고 함수 중단
		if (admin_id.split(" ").join("") == "") {
			$(".admin_id").val("");
			alert("아이디를 입력하지 않았습니다.");
			return;
		}

		// 입력된 비밀번호를 가져와 변수에 저장
		var pwd = $(".pwd").val();
		// 비밀번호를 입력 안했으면 경고하고 함수 중단
		if (pwd.split(" ").join("") == "") {
			$(".pwd").val("");
			alert("비밀번호를 입력하지 않았습니다.");
			return;
		}

		if (is_empty("login_option")) {
			alert("로그인할 페이지를 선택해 주십시요");
			$("[name='login_option']").focus();
			return;
		}
		alert( $('[name="login_option"]:checked').val());

		$.ajax({
			url:"/erp/loginProc.do",
			type:"post",
			// 일일이 써야함
			//data:{'admin_id':admin_id, 'pwd':pwd}
			// 여러개도 가능	
			data: $("[name=loginForm]").serialize(),
			datatype:"html",
			/* success:function(html){
				var idCnt = $(html).text();
				alert(idCnt);
				idCnt = idCnt.split(" ").join("");
				if(idCnt==1){
					alert("로그인 성공!");
					//location.replace("/erp/boardListForm.do");
				}
				else{
					alert("로그인 실패!");
				}
			}, */
			success:function(data){
				if(data==1){
					alert("로그인 성공!");
/* 
					 만약 post 방식으로 "erp/boardListForm.do 에 접근하고 싶다면?
							
					 POST 방식으로 보내려고 할 때 여기에 폼 하나 만들고 폼을 SUBMIT 
					
						<form name="boardListForm" method="post" action="/erp/boardListForm.do">
						</form>  */
							// body태그 하단에 폼태그를 선언하고
							// location.replace 대신 아래 코드를 사용한다.
					// document.boardListForm.submit();

					if($('[name="login_option"]:checked').val() == 1){

						location.replace("/erp/boardListForm.do");
					}
					else{

						location.replace("/erp/contactSearchForm.do");	
					}
				}
				else{
					alert("로그인 실패!");
				}
			},
			error : function(){
				alert("서버 접속 실패!");
			}
			
		});

	}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<form name="loginForm" method="post" action="/erp/loginProc.do">
			<table class="tbcss1" border=1 cellpadding=20 cellspacing=20>
				<tr>
					<th><b>[로그인]</b>
						<div style="height: 6"></div>
						<table class="tbcss1" border=1 cellpadding=5 cellspacing=0 bordercolor="gray">
							<tr>
								<th bgcolor="${headerColor}" align=center>아이디
								<td><input type="text" name="admin_id" class="admin_id"
									size="20">
							<tr>
								<th bgcolor="${headerColor}" align=center>암호
								<td><input type="password" class="pwd" name="pwd" size="20">
							<tr>
								<th bgcolor="${headerColor}" align=center>로그인할 페이지
								<td>
						<input type="radio" name="login_option" id="login_option1" value="1">게시판
						<input type="radio" name="login_option" id="login_option2" value="2">연락처
							
						</table>
						<div style="height: 6"></div> <input type="button" value="로그인" class="login">
							
						<input type="checkbox" name="is_login" value="y"> 아이디,암호 기억
			</table>
		</form>
	</center>
</body>
</html>