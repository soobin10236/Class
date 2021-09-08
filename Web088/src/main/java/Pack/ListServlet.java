package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet") �� ����
@WebServlet("/list")
//class �̸� üũ
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//������ �̸� üũ   
	public ListServlet() {
		super();
		System.out.println("Constructor Call");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("List doGet Call");
		
		//DB���� �����͸� ������.
		String[] id = {"ȣ����","�ڳ���","������"};
		int size = id.length;
		//�����(Ŭ���̾�Ʈ)�� ����� �����ϱ�.

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1' width='300'>");
		out.println("<tr bgcolor='#ff0'>");
		out.println("<td> ���̵� </td>");
		out.println("<td> ���� </td>");
		out.println("<td> ���� </td>");
		out.println("</tr>");
		//�ݺ������� DB���� ������ ������ ��� -> �������α׷����̴�.
		for(int i = 0; i < size; i++) {
			out.println("<tr>");
			out.println("<td>");
			out.println(id[i]);
			out.println("</td>");
			out.println("<td> <a href='delete?id="+id[i]+"'>����</a> </td>");
			out.println("<td> <a href='update?id="+id[i]+"'>����</a> </td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}