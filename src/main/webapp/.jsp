<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>"
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Example</title>
</head>
<body>
    <h1>List Example</h1>
    
    <ul>
        <%-- 리스트를 가져와서 순회하며 아이템 출력 --%>
        <% List<String> myList = (List<String>) request.getAttribute("myList"); 
        
        if(myList != null){
        	out.println(myList.toString());
        } else {
        	out.println("null");
        }
        
        %>
        
    </ul>
</body>
</html>