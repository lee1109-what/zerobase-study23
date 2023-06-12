<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="gooAPI.locationDTO" %>
<%@ page import="gooAPI.MyPosition" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/custom.css">
<title>와이파이 정보 구하기</title>
<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration: none;
		font-weight:bold;
		}
</style>
</head>
<body>
	<div>
		<strong>
			<font size=6>와이파이 정보 구하기</font>
		</strong>
	</div>
	<div>
	
		<br>
			<a href="main.jsp">홈</a>
			<span>|</span>
			<a href="location-history.jsp">위치 히스토리 목록</a>
			<span>|</span>
			<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
		</br>
		
	</div>
	<style>
	  .border-box {
	    box-sizing: border-box;
	    width: 158px;
	    height: 0px;
	    padding-bottom: 18px;
	    border: 1px solid black;
	    display: inline-block;
	  }
	</style>
	<%
	locationDTO data = MyPosition.getMyLocation();
	%>
	<div>
		<br>
			<span><strong>LAT:</strong></span>
			<style>
			    .box {
			        display: inline-block;
			        width: 150px;
			        border: 1px solid black;
			        padding: 1px;
			        text-align: left;
			        font-weight: bold;
			      
			    }
			</style>
			<div class="box">
			    <%= data.getlat() %>
			</div>
			<span>,</span>
			<span><strong>LNT:</strong></span>
			<div class="box">
			    <%= data.getlng() %>
			</div>
			<button><strong><a href="get-mypo.jsp">내 위치 가져오기</a></strong></button>
			<button><strong><a href="near-wifi.jsp">근처 WIPI 정보 보기</a></strong></button>
		</br>
	</div>
	<p style="line-height:1;"></p>
	<div class="container">
		<div class="row">
			<table class="table" style="text-align: center; margin: 0 auto; border-style: hidden; border-collapse:collapse; width:100%; height:50px">
				<thead><%-- 속성을 알려줌 --%>
					<tr> <%-- 하나의 행 --%>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">거리(km)</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">관리번호</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">자치구</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">와이파이명</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">도로명주소</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">상세주소</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">설치위치(층)</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">설치유형</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">설치기관</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">서비스구분</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">망종류</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">설치년도</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">실내외구분</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">WIFI접속환경</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">X좌표</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">Y좌표</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">작업일자</th>
					</tr>
				</thead> 
			</table>
			<style>
			  .border-box2 {
			    box-sizing: border-box;
			    width: 100%;
			    height: 70px;
			    padding: 25px;
			    border: 1px solid LightGrey;
			    display: inline-block;
			    text-align: center;
			  }
			</style>
			<div class="border-box2"><strong>위치 정보를 입력한 후에 조회해 주세요.</strong></div>

		</div>
		
	</div>
	
	
</body>
</html>