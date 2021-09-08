<%@page import="java.sql.ResultSet"%>
<%@page import="DAO.UserDAO"%>
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
	UserDAO userDAO = new UserDAO();
	String strSql2 = "insert into board values('Test')";
	userDAO.executeUpdate(strSql2);
	String strSql = "select * from board";
	ResultSet rs = userDAO.executeQuery(strSql);
	%>
	<table border="1">
		<tr>
			<%
			while (rs.next()) {
			%>
			<td><%=rs.getString(1) %></td>
			<%
			} 
			%>
		</tr>
	</table>
</body>
</html>