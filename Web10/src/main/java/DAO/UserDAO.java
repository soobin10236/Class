package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	Connection conn = null; // DB Ŀ�ؼ� ���� ��ü ����
	Statement stmt = null; // statement ������ ��� ��ü ����
	ResultSet rs = null; // Select ����� ��� ��ü ����
	
	public UserDAO(){
		// DBMS������ db�� , Timezone error �ذ�
		String URL = "jdbc:mysql://localhost:3306/db01?serverTimezone=UTC";
		String USERNAME = "root";// DBMS���� �� ���̵�
		String PASSWORD = "1234";// DBMS���� �� ��й�ȣ

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("����̹� �ε� ����");
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
