package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	Connection conn = null; // DB 커넥션 연결 객체 생성
	Statement stmt = null; // statement 쿼리를 담는 객체 생성
	ResultSet rs = null; // Select 결과를 담는 객체 생성
	
	public UserDAO(){
		// DBMS접속할 db명 , Timezone error 해결
		String URL = "jdbc:mysql://localhost:3306/db01?serverTimezone=UTC";
		String USERNAME = "root";// DBMS접속 시 아이디
		String PASSWORD = "1234";// DBMS접속 시 비밀번호

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("드라이버 로딩 성공");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeUpdate(String strSql) {
		try {
			stmt.executeUpdate(strSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String strSql) {
		try {
			rs = stmt.executeQuery(strSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
