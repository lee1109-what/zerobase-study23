<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>"
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.jasper.JasperException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 	<%
		List<String> myList = (List<String>) request.getAttribute("myList");
		
	 	try{
			for (String item : myList) {
			    out.println(item);
			}
	 	} catch (Exception e){
	 		out.println("오류발생");
	 	}
		    // JSP 페이지 실행 또는 컴파일
		%>
</body>
</html>