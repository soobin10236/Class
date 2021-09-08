package Pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public List<String> memberList = new ArrayList<String>();
	
//	private String[] memberList = new String[]{"ȫ�浿","�谩��","�谩��"};
   
    public MemberListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Call");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String memID = request.getParameter("memberID");
		memberList.add(memID);
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table border='1' width='600'>");
		out.println("<tr>");
		out.println("<a href='index.html'> index </a>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> ȸ�� �̸� </td>");
		out.println("<td> �����ϱ� </td>");
		out.println("<td> �����ϱ� </td>");
		out.println("</tr>");
		for(int i = 0; i < memberList.size(); i++) {
			out.println("<tr>");
			out.println("<td>");
			out.println(memberList.get(i));
			out.println("</td>");
			out.println("<td> <a href='/Homework/delList?id="+i+"'>����</a> </td>");
			out.println("<td> <a href='/Homework/modList?id="+i+"'>����</a> </td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
	
		out.close();
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
