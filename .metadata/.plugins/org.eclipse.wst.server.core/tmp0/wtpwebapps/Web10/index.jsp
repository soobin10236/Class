
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
	
	<!-- ex13) Driectory Path-->
	<% Date date = new Date(); %> 
	<%= date %><br/>
	<h1>Index�Դϴ�</h1>
	<P>
	<a href="a.jsp">webapp - a.jsp</a>
	<a href="./dog/b.jsp">webapp - dog - b.jsp</a>
	<a href="./dog/sub/c.jsp">webapp - dog - sub - c.jsp</a>
	
	<%-- <!-- ex12) <a>-->
	<% Date date = new Date(); %> 
	<%= date %><br/>
	
	<%
		for(int i = 0; i < 10; i ++){
				out.println("<a href=a.jsp?num="+i+">["+i+"]</a>");
		}
	%> --%>
	
	<%-- <!-- ex11) colspan -->
	<%
	Date date = new Date();
	%>
	<%=date%><br />

	<table border="1" align="center" width="80%">
		<tr>
			<td align="center" bgcolor="yellow">
				<form method="post" action="testPage.jsp">
					<table border="1" cellpadding="0" cellspacing="0" width="80%">
						<tr>
							<td colspan="3">ȸ������</td>
						</tr>
						<tr>
							<td>���̵�</td>
							<td><input type="text" name="name" /></td>
							<td>���̵� �����ּ���</td>
						</tr>
						<tr>
							<td>��й�ȣ</td>
							<td><input type="password" name="pass" /></td>
							<td>��й�ȣ�� �Է��ϼ���</td>
						</tr>
						<tr>
							<td colspan="3"><input type="submit" value="�����ϱ�"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table> --%>

	<%-- <!-- ex10) form-->

	<% Date date = new Date(); %>
	<%= date %><br />

	<fieldset>
		<legend>����</legend>
		<form method="post" action="testPage.jsp">
			<input type="text" name="name" value="apple" />
			<input type="submit" value="����" />
			<p>
		</form>
	</fieldset> --%>


	<%-- <!-- ex9) ������ -->

	<%
	Date date = new Date();
	%>
	<%=date%><p>
	
	<h1>�������� �ܿ���!</h1>
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
	</table> --%>

	<%-- <!-- ex8) html tag-->
	<% Date date = new Date(); %> 
	<%= date %><br/>
	<button>ȣ����</button>
	<hr/>
	<button>ȣ����</button><p>
	<input type="text" value="�ʱⰪ"/><br/>
	<input type="submit" value="submit"/><br/>
	<p>
	<select name ="color">
		<option value="red" selected>Red</option>
		<option value="blue">Blue</option>
		<option value="green">Green</option>
	</select> --%>

	<%-- <!-- ex7) JSP for��-->
	<body bgcolor = <%="green" %>>
	<% Date date = new Date(); %>
	<%= date %>
	
	<%
		String[] name = {"ȣ����","������","������","�ڳ���"};
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

	<%-- <!-- ex6) ���̺� ��� -->
	<% Date date = new Date(); %> 
	<%= date %>
	<table border="1" width="200">
		<tr>
			<td><%=1 %></td>
			<td><%="ȣ����" %></td>
		</tr>
		<tr>
			<td><%=2 %></td>
			<td><%="�ڳ���" %></td>
		</tr>
		<tr>
			<td><%=3 %></td>
			<td><%="������" %></td>
		</tr>
	</table> --%>


	<%-- <!-- ex5) import -->
	<% //java.util.Date date = new java.util.Date(); %> 
	
	<%//@ page import = "java.util.*" %>
	<% Date date = new Date(); %> 
	<%= date %> --%>


	<%-- <!-- ex4) JSP ����(�Լ��ۼ�) -->
	<h1>ȣ����</h1>
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
	
	// ǥ���Ŀ��� ; ���� �ȵ�!
	%>
	<br/>
	<%=f2() %> --%>


	<%-- <!-- ex3) JSP ǥ���� -->
	<h1>ȣ����</h1>
	<%
		int num = 0;
		String s = "ȣ����";
	%>
	��� 1 : <%=num %><br/>
	��� 2 : <%=s %><br/>	 --%>

	<%-- <!-- ex2) JSP ���-->
	<h1>ȣ����</h1>
	<%
		int num = 0;
		String s = "ȣ����";
		out.println(num + "<br/>");
		out.println(s);
	%> --%>




	<%-- <!-- ex1) JSP ���� 4���� -->
	1�� ��ũ��Ʈ �� -> �ڹ��ڵ� �ۼ�
	<% %>
	
	2�� ǥ���� -> ���������� ��� �뵵
	<%= %>
	
	3�� ����(�Լ� �ۼ�) -> �� ������ �ʴ´�.
	<%! %>
	
	4�� �ּ� ��� <%-- -->
	�ڹ��ڵ� �ȿ����� �ڹٿ��� ó�� �ּ� ������ �� --%>

</body>

</html>