package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet") 을 변형
@WebServlet("/list")
//class 이름 체크
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//생성자 이름 체크   
	public ListServlet() {
		super();
		System.out.println("Constructor Call");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("List doGet Call");
		
		//DB에서 데이터를 가져옴.
		String[] id = {"호랑이","코끼리","독수리"};
		int size = id.length;
		//사용자(클라이언트)에 결과를 응답하기.

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1' width='300'>");
		out.println("<tr bgcolor='#ff0'>");
		out.println("<td> 아이디 </td>");
		out.println("<td> 삭제 </td>");
		out.println("<td> 수정 </td>");
		out.println("</tr>");
		//반복문으로 DB에서 가져온 데이터 출력 -> 동적프로그래밍이다.
		for(int i = 0; i < size; i++) {
			out.println("<tr>");
			out.println("<td>");
			out.println(id[i]);
			out.println("</td>");
			out.println("<td> <a href='delete?id="+id[i]+"'>삭제</a> </td>");
			out.println("<td> <a href='update?id="+id[i]+"'>수정</a> </td>");
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