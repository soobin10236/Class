<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- ex11) colspan -->
	<h1>TestPage</h1>
	<%
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		out.println(name);
		out.println(pass);
	%>
	<h1><%=name %></h1>
	<h1><%=pass %></h1>
	<p>
	<a href="a.jsp">a.jsp로 이동</a>
	
</body>
</html>