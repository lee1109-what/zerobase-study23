<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="gooAPI.locationDTO" %>
<%@ page import="gooAPI.MyPosition" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="com.mysql.jdbc.Statement" %>

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
	<style>
		.even-row{
			background-color: #F5F5F5;
		}
		.odd-row{
			background-color: #FFFFFF;
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
			<button><strong>근처 WIPI 정보 보기</strong></button>
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
				
				<% 
				
				
				String url = "jdbc:mysql://localhost:3306/seoulwifi_db";
				String username = "testuser5";
				String password = "zerobase";
				
				Connection connection = null;
				Statement statement = null;
				ResultSet rs = null;
				
				
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(url, username, password);
					
					String selectQuery = "SELECT * FROM MyData_sortedTable";
					statement = (Statement) connection.createStatement();
					rs = statement.executeQuery(selectQuery);
					
					int rowCount = 0;
					while(rs.next()) { 
						double X_SWIFI_DISTANCE = rs.getDouble("X_SWIFI_DISTANCE");
						String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
						String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
						String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
						String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
						String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
						String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
						String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
						String X_SWIFI_INSTL_MB = rs.getString("X_SWIFI_INSTL_MB");
						String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
						String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
						String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
						String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
						String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
						String LAT = rs.getString("LAT");
						String LNT = rs.getString("LNT");
						String WORK_DTTM = rs.getString("WORK_DTTM");
						
								
						if(rowCount%2 == 0)
						{ %> <tr class = "even-row" >
						<% } else { %>
						<tr class = "odd-row" >
						<% } %>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= String.format("%.4f", X_SWIFI_DISTANCE) %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_MGR_NO %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_WRDOFC %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_MAIN_NM %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_ADRES1 %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_ADRES2 %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_INSTL_FLOOR %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_INSTL_TY %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_INSTL_MB %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_SVC_SE %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_CMCWR %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_CNSTC_YEAR %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_INOUT_DOOR %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= X_SWIFI_REMARS3 %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= LAT %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= LNT %></td>
						<td style="border: 1px solid LightGrey; text-align: center; color:black"><%= WORK_DTTM %></td>
						
					</tr>
								
				 
				 <%	rowCount++; %> 
				 <%	} 
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(rs != null) {
							try {
								rs.close();
							} catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if(statement != null) {
							try {
								statement.close();
							} catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if(connection != null) {
							try {
								connection.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				
				%>
					
					
				</thead> 
			</table>
			

		</div>
		
	</div>
	
	
</body>
</html>