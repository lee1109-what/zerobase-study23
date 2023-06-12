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
	</div>
	<p style="line-height:1;"></p>
	<div class="container">
		<div class="row">
		<% String message = (String) request.getAttribute("message");
			if(message != null && !message.isEmpty()){ %>
		<p><%= message %></p>
		<% } %>
			<table class="table" style="text-align: center; margin: 0 auto; border-style: hidden; border-collapse:collapse; width:100%; height:50px">
				<thead><%-- 속성을 알려줌 --%>
					<tr> <%-- 하나의 행 --%>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">ID</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">X좌표</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">Y좌표</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">조회일자</th>
						<th style="background-color: #168D63; border: 1px solid LightGrey; text-align: center; color:white">비고</th>
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
					
					String selectQuery = "SELECT * FROM Location_History";
					statement = (Statement) connection.createStatement();
					rs = statement.executeQuery(selectQuery);
					
					int rowCount = 0;
					while(rs.next()) { 
						int id = rs.getInt("id");
						String LAT = rs.getString("LAT");
						String LNT = rs.getString("LNT");
						String WORK_DTTM = rs.getString("WORK_DTTM");
						
								
						if(rowCount%2 == 0)
						{ %> <tr class = "even-row" >
						<% } else { %>
						<tr class = "odd-row" >
						<% } %>
						<td style="border: 1px solid LightGrey; text-align: left; color:black"><%= id %></td>
						<td style="border: 1px solid LightGrey; text-align: left; color:black"><%= LAT %></td>
						<td style="border: 1px solid LightGrey; text-align: left; color:black"><%= LNT %></td>
						<td style="border: 1px solid LightGrey; text-align: left; color:black"><%= WORK_DTTM %></td>
						<td>
							<form action="delete" method="post">
								<input type="hidden" name="id" value="<%= id %>">
								<input type="submit" value="삭제">	
							</form>
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