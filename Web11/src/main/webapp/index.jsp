<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<a href="a.jsp?FullName=tiger">click A</a>
	<%
		String str = "lion";
		String str2 = "monkey";
		int age = 24;
	%>
	<a href="b.jsp?FullName=<%=str %>">click B</a>
	<a href="c.jsp?FullName=<%=str2 %>&age=<%=age%>">click C</a>
	
	<%
		String str3 = "dog";
		int num = 999;
	%>
	<a href="d.jsp?FullName=<%=str3 %>&age=<%=num%>">click D</a>
	<a href="https://www.naver.com">naver</a>
	
	<form method="post" action="e.jsp">
		<input type="text" name ="name" value="apple"/>
		<input type="hidden" name ="age" value="23"/>
		<input type="submit" value="E 전송"/><p>
	</form>
	
	<%
		String str4 = "rabbit";
		int age2 = 30;
	%>
	<form method="post" action="f.jsp">
		<input type="text" name ="name" value="<%=str4%>"/>
		<input type="hidden" name ="age" value="<%=age2%>"/>
		<input type="submit" value="F 전송"/><p>
	</form>
	<a href="index.jsp">return</a>
	
</body>
</html>