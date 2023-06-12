<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="openapi.OpenApi" %>
<%@ page import="openapi.OpenApiDTO.MyData" %>
<%-- 없어도 됨 --%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%-- 여기까지 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/custom.css">
<title>와이파이 정보 구하기</title>
</style>
</head>
<body> 
	<div class="container">
		<div class="row">
			<table class="table" style="text-align: center; margin: auto; border-style: hidden; border-collapse:separate; border-spacing: 0 30px">
				<thead><%-- 속성을 알려줌 --%>
					<tr> <%-- 하나의 행 --%>
						<th style=" border: 1px solid white; text-align: center; color:black; font-size:25px">
						<strong><%= OpenApi.getListTotalCount() %>개의 WIFI 정보를 정상적으로 저장하였습니다.</strong></th> 
					</tr>
					<tr>
						<th style=" border: 1px solid white; text-align: center"><a href="main.jsp">홈으로 가기</a></th>
					</tr>
				</thead>
			</table>
			
		</div>
	</div>
	
</body>
</html>
