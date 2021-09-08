package Pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBConnection {

	public static void main(String[] args) {
		try {
			Connection conn = null; // DB 커넥션 연결 객체 생성
			Statement stmt = null; // statement 쿼리를 담는 객체 생성
			ResultSet rs = null; // Select 결과를 담는 객체 생성

			// DBMS접속할 db명 , Timezone error 해결
			String URL = "jdbc:mysql://localhost:3306/db01?serverTimezone=UTC";
			String USERNAME = "root";// DBMS접속 시 아이디
			String PASSWORD = "1234";// DBMS접속 시 비밀번호

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("드라이버 로딩 성공");
			
			String sql = ""; // Query문
			stmt = conn.createStatement();

			System.out.println("1. insert  2. select  3. update  4. delete");

			Scanner scanner = new Scanner(System.in);
			int value = scanner.nextInt();

			switch (value) {
			case 1:
				try {
					//Insert
					sql = "insert into board values('호랑이 추가')";
					stmt.executeUpdate(sql);
					System.out.println("insert");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					//Select
					sql = "select id from board";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						System.out.println(rs.getString(1));
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case 3:
				try {
					//Update
					sql = "UPDATE board SET id = '티라노'";
					stmt.executeUpdate(sql);
					System.out.println("update");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					//Delete
					sql = "delete from board where id = '티라노'";
					stmt.executeUpdate(sql);
					System.out.println("delete");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				System.out.println("1, 2, 3, 4, 중에 입력하세요.");
				break;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}