package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet") 을 변형
//맵핑방법은 어노테이션 X -> web.xml 만들어야함 (유지보수에는 이게 좋은 방식일때 있다.)
//class 이름 체크
public class WorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //생성자 이름 체크   
    public WorldServlet() {
        super();
        System.out.println("Constructor Call");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Call");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
			out.println("<head>");
				out.println("<title>");
					out.println("Title Example");
				out.println("</title>");
			out.println("</head>");
			out.println("<body>");
				out.println("<h1> Tiger 200</h>");
			out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}