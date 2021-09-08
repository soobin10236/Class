package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet") 을 변형
@WebServlet("/update")
//class 이름 체크
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //생성자 이름 체크   
    public UpdateServlet() {
        super();
        System.out.println("Constructor Call");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Update doGet Call");
		
		// request 로 form이 보낸 데이터 name 으로 얻어올 수 있다.
				request.setCharacterEncoding("UTF-8");
				String id = request.getParameter("id");

				// 사용자(클라이언트)에 결과를 응답하기.
				// 사용자(클라이언트)에 결과를 응답하기.

				response.setContentType("text/html;charset=UTF-8");

				PrintWriter pw = response.getWriter();

				pw.println("<html>");
				pw.println("<body>");
				pw.println(id + "님이 성공적으로 삭제되었습니다.<br/>");
				pw.println("</body>");
				pw.println("</html>");

				pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Update doPost Call");
	}

}