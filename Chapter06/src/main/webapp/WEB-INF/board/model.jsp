<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String data = (String) request.getAttribute("data");
%>
<head>
<meta charset="UTF-8">
<title>Model</title>
</head>
<body>
	<h1>Model</h1>
	<h2>el tag : ${data}</h2>
	<h2>request : <%=data %></h2>
</body>
</html>