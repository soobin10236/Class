package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet") �� ����
@WebServlet("/update")
//class �̸� üũ
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //������ �̸� üũ   
    public UpdateServlet() {
        super();
        System.out.println("Constructor Call");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Update doGet Call");
		
		// request �� form�� ���� ������ name ���� ���� �� �ִ�.
				request.setCharacterEncoding("UTF-8");
				String id = request.getParameter("id");

				// �����(Ŭ���̾�Ʈ)�� ����� �����ϱ�.
				// �����(Ŭ���̾�Ʈ)�� ����� �����ϱ�.

				response.setContentType("text/html;charset=UTF-8");

				PrintWriter pw = response.getWriter();

				pw.println("<html>");
				pw.println("<body>");
				pw.println(id + "���� ���������� �����Ǿ����ϴ�.<br/>");
				pw.println("</body>");
				pw.println("</html>");

				pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Update doPost Call");
	}

}