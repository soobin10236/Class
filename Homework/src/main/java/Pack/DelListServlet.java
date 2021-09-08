package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delList")
public class DelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DelListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("DelList doGet Call");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		System.out.println(request.getParameterValues("memList"));
		System.out.println(id);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
