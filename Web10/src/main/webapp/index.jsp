
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>
	<%--
	 
	<!-- ex) -->
	
	<% Date date = new Date(); %> 
	<%= date %><br/>
	
	--%>

	<!-- ex9) 구구단 -->

	<%
	Date date = new Date();
	%>
	<%=date%><p>
	
	<h1>구구단을 외우자!</h1>
	<table border="1">
		<%
			for (int i = 1; i < 10; i++) {
		%>
		<tr>
			<%
				for (int j = 2; j < 10; j++) {
			%>
			<td>
				<%=j%> x <%=i%> = <%=j * i%>
			</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>

	<%-- <!-- ex8) html tag-->
	<% Date date = new Date(); %> 
	<%= date %><br/>
	<button>호랑이</button>
	<hr/>
	<button>호랑이</button><p>
	<input type="text" value="초기값"/><br/>
	<input type="submit" value="submit"/><br/>
	<p>
	<select name ="color">
		<option value="red" selected>Red</option>
		<option value="blue">Blue</option>
		<option value="green">Green</option>
	</select> --%>
	
	<%-- <!-- ex7) JSP for문-->
	<body bgcolor = <%="green" %>>
	<% Date date = new Date(); %>
	<%= date %>
	
	<%
		String[] name = {"호랑이","독수리","원숭이","코끼리"};
	%>
	
	<table border="1" width="200">
	<%for(int i = 0; i < name.length; i++){ %>
		<tr>
			<%
				int temp = i * 10;
			%>
			<td><%=temp %></td>
			<td><%=name[i] %></td>
		</tr>
	<%} %>
	</table> --%>
	
	<%-- <!-- ex6) 테이블 출력 -->
	<% Date date = new Date(); %> 
	<%= date %>
	<table border="1" width="200">
		<tr>
			<td><%=1 %></td>
			<td><%="호랑이" %></td>
		</tr>
		<tr>
			<td><%=2 %></td>
			<td><%="코끼리" %></td>
		</tr>
		<tr>
			<td><%=3 %></td>
			<td><%="독수리" %></td>
		</tr>
	</table> --%>
	
	
	<%-- <!-- ex5) import -->
	<% //java.util.Date date = new java.util.Date(); %> 
	
	<%//@ page import = "java.util.*" %>
	<% Date date = new Date(); %> 
	<%= date %> --%>
	
	
	<%-- <!-- ex4) JSP 선언문(함수작성) -->
	<h1>호랑이</h1>
	<%!
		void f1(javax.servlet.jsp.JspWriter out){
			try{
			out.print("method call");
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	
		int f2(){
			return 999;
		}
	%>
	<%
		f1(out); 
	
	// 표현식에는 ; 들어가면 안됨!
	%>
	<br/>
	<%=f2() %> --%>


	<%-- <!-- ex3) JSP 표현식 -->
	<h1>호랑이</h1>
	<%
		int num = 0;
		String s = "호랑이";
	%>
	출력 1 : <%=num %><br/>
	출력 2 : <%=s %><br/>	 --%>

	<%-- <!-- ex2) JSP 출력-->
	<h1>호랑이</h1>
	<%
		int num = 0;
		String s = "호랑이";
		out.println(num + "<br/>");
		out.println(s);
	%> --%>




	<%-- <!-- ex1) JSP 기초 4가지 -->
	1번 스크립트 릿 -> 자바코드 작성
	<% %>
	
	2번 표현식 -> 웹페이지에 출력 용도
	<%= %>
	
	3번 선언문(함수 작성) -> 잘 쓰이지 않는다.
	<%! %>
	
	4번 주석 잡기 <%-- -->
	자바코드 안에서는 자바에서 처럼 주석 잡으면 됨 --%>

</body>

</html>