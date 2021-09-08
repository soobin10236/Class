<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%
	String FullName = request.getParameter("FullName"); 
	int Age = Integer.parseInt(request.getParameter("age"));
	%>
	<h1><%=FullName %></h1>
	<h1><%=Age %></h1>

</body>
</html>