package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet") 을 변형
@WebServlet("/") //index가 됨
//class 이름 체크
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //생성자 이름 체크   
    public IndexServlet() {
        super();
        System.out.println("Constructor Call");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터 베이스 접속이 필요하다. -> java코드가 필요함. 
		// index.html로는 DB에 접속할 수 없음
		// 정보를 로딩한다.(정보를 넣고 받을 수 있음) -> 이것들 덕분에 동적인 프로그래밍이라고 한다.
		System.out.println("doGet Call");
		response.setContentType("text/html;charset=UTF-8");
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Test.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}