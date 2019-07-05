<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="common.jsp" %>

<%@include file="checkLogin.jsp"%>
<%@include file="menubar.jsp"%>
<script>
$(document).ready(function(){
	var evenTrColor = "${evenTrColor}";
	var oddTrColor ="${oddTrColor}";
	var headerColor="${headerColor}";
	var mouseOverColor="${mouseOverColor}";
	
	setTableTrBgColor("contactList",headerColor,evenTrColor,oddTrColor,mouseOverColor);

	inputData("selectPageNo","${sessionScope.contactSearchDTO.selectPageNo}");
	
	// 페이징 처리 관련 html 소스를 class= pagingNumber 가진 태그 안에 삽입하기
	$(".pagingNumber").html(
		getPagingNumber(
		"${requestScope.contactListAllCnt}", // 검색 결과 총 행 개수
		"${sessionScope.contactSearchDTO.selectPageNo}", // 선택된 현재 페이지 번호
		"5", // 페이지 당 출력행의 개수
		"10", // 페이지 당 보여줄 페이징번호 개수
		"goSearch();" // 페이지 번호 클릭 실행할 자스 코드
		)
	);
	

	// 검색 조건 흔적 남기기 2
	inputData("keyword","${sessionScope.contactSearchDTO.keyword}");
	inputData("religion","${sessionScope.contactSearchDTO.religion}");
	inputData("graduate_year1","${sessionScope.contactSearchDTO.graduate_year1}");
	inputData("graduate_year2","${sessionScope.contactSearchDTO.graduate_year2}");
	<c:forEach items="${sessionScope.contactSearchDTO.gender}" var="gender">
	inputData("gender","${gender}");
	</c:forEach>
	
	<c:forEach items="${sessionScope.contactSearchDTO.school}" var="school">
	inputData("school","${school}");
	</c:forEach>
	
	<c:forEach items="${sessionScope.contactSearchDTO.skill}" var="skill">
	inputData("skill","${skill}");
	</c:forEach>
	
	inputData("graduate_month1","${sessionScope.contactSearchDTO.graduate_month1}");
	inputData("graduate_month2","${sessionScope.contactSearchDTO.graduate_month2}");
	 
	inputData("selectOption","${sessionScope.contactSearchDTO.selectOption}");
	inputData("ascDesc","${sessionScope.contactSearchDTO.ascDesc}");
	
	var src = $('[name="selectOption"]').val();

	var str = $('[name='+src+']').text();
	if($('[name="ascDesc"]').val()=='asc'){

		if(str.indexOf("▼") >= 0){
			str = str.replace("▼","▲");
		}
		else{
			str = str.concat("▲");
		}
	}
	else{
		if(str.indexOf("▲") >= 0){
			str = str.replace("▲","▼");
		}
		else{
			str = str.concat("▼");
		}
	}
	

	$('[name='+src+']').text(str);
	
	
	
	
});
function goSearch(){
	if(is_special_char("keyword")){
		alert("키워드에는 영문,숫자,한글,_ 만 가능합니다.");
		$(".keyword").val("");
		return;
	}
	
	document.contactSearchForm.submit();
	
}
// 모두검색 키워드 없애기
function goSearchAll(){
	// 공용함수 setEmpty2 활용
	setEmpty2(" [name=keyword], [name=religion], [name=graduate_year1], [name=graduate_year1], [name=graduate_year2]");
	setEmpty2(" [name=graduate_month1], [name=graduate_month2], [name=skill], [name=school], [name=gender] ");
	inputData("selectPageNo","1");
	document.contactSearchForm.submit();
}

function checkMonth1(){
	var month = $('[name="graduate_month1"]').val();
	var month2 = $('[name="graduate_month2"]').val();

	var year = $('[name="graduate_year1"]').val();
	var year2 = $('[name="graduate_year2"]').val();
	if(month==""){
		$('[name="graduate_month1"]').val("01");
	}
	if(year2!=""){
		if(year>year2){
			$('[name="graduate_year1"]').val(1980);
			alert("기간이 맞지 않습니다.");
		}
		else if(year==year2){
			if(Number(month)>Number(month2)){
				 $('[name="graduate_month1"]').val("01");
			}
		}
	}
	
}

function checkYear1(){
	var month = $('[name="graduate_month1"]').val();
	var month2 = $('[name="graduate_month2"]').val();
	if(month==""){
		$('[name="graduate_month1"]').val("01");
	}
	var year = $('[name="graduate_year1"]').val();
	var year2 = $('[name="graduate_year2"]').val();
	if(year==""){
		$('[name="graduate_month1"]').val("");
		alert("연도를 먼저 선택해주십시오.");
	}
	if(year2!=""){
	if(year>year2){
		$('[name="graduate_year1"]').val(1980);
		 $('[name="graduate_month1"]').val("01");
		alert("기간이 맞지 않습니다.");
	}
	else if(year==year2){
		if(Number(month)>Number(month2)){
			 $('[name="graduate_month1"]').val("01");
		}
	}
	}
}

function checkMonth2(){

	var month = $('[name="graduate_month1"]').val();
	var month2 = $('[name="graduate_month2"]').val();

	if(month2==""){
		$('[name="graduate_month2"]').val(12);
	}
	

	var year = $('[name="graduate_year1"]').val();
	var year2 = $('[name="graduate_year2"]').val();
	
	if(year>year2){
		$('[name="graduate_year2"]').val(2010);
		alert("기간이 맞지 않습니다.");
	}
	else if(year==year2){
		if(Number(month)>Number(month2)){
			 $('[name="graduate_month2"]').val(12);
		}
	}
}

function checkYear2(){
	

	var month = $('[name="graduate_month1"]').val();
	var month2 = $('[name="graduate_month2"]').val();

	if(month2==""){
		$('[name="graduate_month2"]').val(12);
	}
	var year = $('[name="graduate_year1"]').val();
	var year2 = $('[name="graduate_year2"]').val();
	if(year2==""){
		$('[name="graduate_month2"]').val("");
		alert("연도를 먼저 선택해주십시오.");
	}
	if(year>year2){
		$('[name="graduate_year2"]').val(2010);
		 $('[name="graduate_month2"]').val(12);
		alert("기간이 맞지 않습니다.");
	}
	else if(year==year2){
		if(Number(month)>Number(month2)){
			 $('[name="graduate_month2"]').val(12);
		}
	}
	
}

function goContactRegForm(){
	document.contactRegForm.submit();
}
function goContactUpDelForm(dev_no){
	$('[name="developer_no"]').val(dev_no);
	document.contactUpDelForm.submit();
			
}
function goContactSearchForm(value){
	alert(1);
	if($("[name='selectOption']").val()==value){
		if($("[name='ascDesc']").val()=='asc'){
			$("[name='ascDesc']").val('desc')
		}	
		else{
			$("[name='ascDesc']").val('asc')
		}
	}
	else{
		alert(2);
		$("[name='selectOption']").val(value);
		$("[name='ascDesc']").val('asc')
	}
	document.contactSearchForm.submit();
	
	
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<form class="contactSearchForm" name="contactSearchForm" method="post" action="/erp/contactSearchForm.do">
	<table class="tbcss1" width = "800" border="1" bordercolor="#DDDDDD" cellpadding="5" align="center">
		<tr align="center">
			<th bgcolor="${headerColor}" colspan="6">사원 정보 검색</th>
		<tr align="center">
			<th bgcolor="${headerColor}" width=60>키워드
			<td width=250><input type="text" name="keyword"> 
			<th bgcolor="${headerColor}" width=60>성별
			<td width=150> <input type="checkbox"
				name="gender" id="gender1" value="male">남자 <input type="checkbox"
				name="gender" id="gender2" value="female">여자 
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
			<input type="checkbox" name="school" id="school1" value="1">고졸
			<input type="checkbox" name="school" id="school2" value="2">전문대졸
			<input type="checkbox" name="school" id="school3" value="3">일반대졸
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
										~
										<select name="graduate_year2"  onchange="checkMonth2();" >
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
										<select name="graduate_month2" onchange="checkYear2();">
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
	</table>
		<input type="hidden" name="selectPageNo">
		<input type="hidden" name="ascDesc">
		<input type="hidden" name="selectOption">
				<table>
					<tr height=4>
						<td>
				</table>
		<input type="button" value="검색" onClick="goSearch();"> 
		<input type="button" value="전부검색" onClick="goSearchAll();"> 
		<input type="reset" value="초기화">
		<input type="button" value="등록" onClick="goContactRegForm();">
		
	</form>
	
	<table border = 0>
			<tr>
				<td align=right>
						검색건수 : ${requestScope.contactListAllCnt} &nbsp;&nbsp;&nbsp;

			<tr>
			<td><table class="tbcss1" width = "800" border="1" bordercolor="#DDDDDD" cellpadding="5" align="center">
			<tr>
				<th bgcolor="${headerColor}" >번호
				<th bgcolor="${headerColor}"  style="cursor: pointer" onClick="goContactSearchForm('developer_name');" name="developer_name" >이름
				<th bgcolor="${headerColor}"  style="cursor: pointer" onClick="goContactSearchForm('gender');" name="gender" >성별
				<th bgcolor="${headerColor}"  style="cursor: pointer" onClick="goContactSearchForm('religion_code');"  name="religion_code" >종교
				<th bgcolor="${headerColor}"  style="cursor: pointer" onClick="goContactSearchForm('skillOption');" name="skillOption">기술 
				<th bgcolor="${headerColor}"  style="cursor: pointer" onClick="goContactSearchForm('graduate_date');" name="graduate_date">졸업일 
				<th bgcolor="${headerColor}" >
				
				<c:forEach items="${contactList}" var="contactList"
						varStatus="loopTagStatus">
						<tr>
							<td>${(sessionScope.contactSearchDTO.selectPageNo-1)*5 + loopTagStatus.index +1}
							<td>${contactList.developer_name}
							<td>${contactList.gender}
							<td>${contactList.religion_name}
							<td>${contactList.skill}
							<td>${contactList.graduate_date}
							<td><input type="button" value="수정/삭제" onClick="goContactUpDelForm(${contactList.developer_no});">
						
					</c:forEach>
					
				
		</table>
		
			<tr>
				<th><span class = "pagingNumber"></span>	
		</table>
		
		${requestScope.contactListAllCnt==0?'검색된 연락처가 없습니다.' : ''} 
		<form name="contactRegForm" method="post" action="/erp/contactRegForm.do">
				
		</form> 
		
		
		<form name="contactUpDelForm" method="post" action="/erp/contactUpDelForm.do">
		<!-- 게시판 상세보기 화면을 구성하는 글의 고유번호를 hidden 태그에 저장한다 -->
		<!-- 수정/삭제를 하려면 현재 글의 고유번호를 알아야하기 때문이다. -->
		<input type="hidden" name="developer_no">	
	</form>
	</center>
</body>
</html>