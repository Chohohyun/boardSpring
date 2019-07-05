<!--  현재 이 jsp 페이지 실행 후 생성되는 문서는 html 이고 이문서는 utf-8 방식으로 인코딩한다. -->

<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="checkLogin.jsp" %>
<%@include file="common.jsp" %>
<%@include file="menubar.jsp"%>

<script>
	function checkContactRegForm() {
		alert("1");
		if (is_empty("dev_name")) {
			alert("이름을 입력해 주십시요");
			$("[name=dev_name]").focus();
			return;
		}
		if (is_empty("jumin_num1")) {
			alert("주민등록번호를 입력해 주십시요");
			$("[name=jumin_num1]").focus();
			return;
		}
		if (is_empty("jumin_num2")) {
			alert("주민등록번호를 입력해 주십시요");
			$("[name=jumin_num2]").focus();
			return;
		}
		if ($("[name=religion]").val() == 0) {
			alert("종교를 선택해 주십시요");
			$("[name=religion]").focus();
			return;
		}
		if (is_empty("school")) {
			alert("학력을 선택해 주십시요");
			$("[name=school]").focus();
			return;
		}

		if (is_empty("skill")) {
			alert("스킬을 선택해 주십시요");
			$("[name=skill]").focus();
			return;
		}
		if (is_empty("graduate_year1")) {
			alert("졸업년도 선택해 주십시요");
			$("[name=graduate_year1]").focus();
			return;
		}
		if (is_empty("graduate_month1")) {
			alert("졸업월 선택해 주십시요");
			$("[name=graduate_month1]").focus();
			return;
		}
		if (is_empty("graduate_day1")) {
			alert("졸업일 선택해 주십시요");
			$("[name=graduate_day1]").focus();
			return;
		}
		if(!is_pattern("dev_name", /^[a-zA-Z]{3,10}$/) && !is_pattern("dev_name", /^[가-힣]{3,10}$/)){
			alert("영어 또는 한글만 가능");
			$("[name=dev_name]").val("");
			return;
		}
		
		if (confirm("정말 저장하시겠습니까?") == false) {
			return;
		}
		alert($("[name=contactRegForm]").serialize());
		// 현재화면에서 페이지 이동 없이 서버쪽 "/erp/boardRegProc.do"을 호출하여
		// 게시판 입력 행 적용 개수가 있는html 소스를 문자열로 받기
		$.ajax({
			url : "/erp/contactRegProc.do",
			type : "post",
			// 일일이 써야함
			//data:{'admin_id':admin_id, 'pwd':pwd}
			// 여러개도 가능	
			data : $("[name=contactRegForm]").serialize(),
			datatype : "html",

			success : function(data){
				if(data == 1){
					alert("게시판 새글 등록 성공!");
					location.replace("/erp/contactSearchForm.do");
				} else {
					alert("게시판 새글 등록 실패! 관리자에게 문의 바람!");
				}
			},
			error : function(html) {
				alert("서버와 비동기 방식 통신 실패!");
			}

		});
	}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>게시판 새글쓰기</title>
</head>
<body>
	<center>
		<center>
			<form method="post" name="contactRegForm"
				action="/erp/contactRegProc.do">
		<table class="tbcss1" width = "800" border="1" bordercolor="#DDDDDD" cellpadding="5" align="center">
			<tr align="center">
			<th bgcolor="${headerColor}" colspan="6">사원 정보 등록</th>
		<tr align="center">
			<th bgcolor="${headerColor}" width=60>이름
			<td width=250><input type="text" name="dev_name">
			<th bgcolor="${headerColor}" width=60>성별
			<td width=400> <input type="text" name="jumin_num1"> <input type="password" name="jumin_num2"> 
			<th bgcolor="${headerColor}" width=60>종교
			<td width="150"><select name="religion">
					<option value="0">
					<option value="1">기독교
					<option value="2">천주교
					<option value="3">불교
					<option value="4">이슬람교
					<option value="5">무교
				</select>
		<tr align="center">
			<th bgcolor="${headerColor}">학력
			<td>
			<input type="radio" name="school" id="school1" value="1">고졸
			<input type="radio" name="school" id="school2" value="2">전문대졸
			<input type="radio" name="school" id="school3" value="3">일반대졸
			<th bgcolor="${headerColor}">기술
			<td colspan="3">
			<input type="checkbox" name="skill" id="skill1" value="1">Java
			<input type="checkbox" name="skill" id="skill2" value="2">JSP
			<input type="checkbox" name="skill" id="skill3" value="3">ASP
			<input type="checkbox" name="skill" id="skill4" value="4">PHP
			<input type="checkbox" name="skill" id="skill5" value="5">Delphi
		<tr align="center">
			<th bgcolor="${headerColor}">졸업일
			<td colspan="5"><select name="graduate_year1" onchange="checkMonth1();">
											<option value=""></option>
											<option value="1980">1980</option>
											<option value="1981">1981</option>
											<option value="1982">1982</option>
											<option value="1983">1983</option>
											<option value="1984">1984</option>
											<option value="1985">1985</option>
											<option value="1986">1986</option>
											<option value="1987">1987</option>
											<option value="1988">1988</option>
											<option value="1989">1989</option>
											<option value="1990">1990</option>
											<option value="1991">1991</option>
											<option value="1992">1992</option>
											<option value="1993">1993</option>
											<option value="1994">1994</option>
											<option value="1995">1995</option>
											<option value="1996">1996</option>
											<option value="1997">1997</option>
											<option value="1998">1998</option>
											<option value="1999">1999</option>
											<option value="2000">2000</option>
											<option value="2001">2001</option>
											<option value="2002">2002</option>
											<option value="2003">2003</option>
											<option value="2004">2004</option>
											<option value="2005">2005</option>
											<option value="2006">2006</option>
											<option value="2007">2007</option>
											<option value="2008">2008</option>
											<option value="2009">2009</option>
											<option value="2010">2010</option>
										</select>
										년 
										<select name="graduate_month1" onchange="checkYear1();">
											<option value=""></option>
											<option value="01">01</option>
											<option value="02">02</option>
											<option value="03">03</option>
											<option value="04">04</option>
											<option value="05">05</option>
											<option value="06">06</option>
											<option value="07">07</option>
											<option value="08">08</option>
											<option value="09">09</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
										</select>
										월
										<select name="graduate_day1" onchange="checkDay1();">
											<option value=""></option>
											<option value="01">01</option>
											<option value="02">02</option>
											<option value="03">03</option>
											<option value="04">04</option>
											<option value="05">05</option>
											<option value="06">06</option>
											<option value="07">07</option>
											<option value="08">08</option>
											<option value="09">09</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
											<option value="13">13</option>
											<option value="14">14</option>
											<option value="15">15</option>
											<option value="16">16</option>
											<option value="17">17</option>
											<option value="18">18</option>
											<option value="19">19</option>
											<option value="20">20</option>
											<option value="21">21</option>
											<option value="22">22</option>
											<option value="23">23</option>
											<option value="24">24</option>
											<option value="25">25</option>
											<option value="26">26</option>
											<option value="27">27</option>
											<option value="28">28</option>
											<option value="29">29</option>
											<option value="30">30</option>
										</select>
									
			</table>
				<table>
					<tr height=4>
						<td>
				</table>
				

				<input type="button" value="등록" onClick="checkContactRegForm();"> 
				<input type="reset" value="초기화"> 
				<input type="button" value="등록취소" onClick="document.contactSearchForm.submit();">
			</form>

			<form name="contactSearchForm" method="post" action="/erp/contactSearchForm.do">
			</form>
			<br>
			<input type="button" value="html정보보기"
					onClick="print_html_info();">
		</center>
	</center>
</body>
</html>