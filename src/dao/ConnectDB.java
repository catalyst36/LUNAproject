package dao;

import java.sql.*;

public class ConnectDB {

	static Connection conn = null;
	
	public static Connection connectDB() {
		try{
			String driver = "oracle.jdbc.driver.OracleDriver";	
			String url = "jdbc:oracle:thin:@192.168.143.28:1521:xe";  
			String user = "luna";
			String password = "java";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
		}catch(ClassNotFoundException e) {
			System.out.println("DB드라이버 로딩 실패 : " + e.toString());
		}catch(SQLException e) {
			System.out.println("DB 접속 실패 : " + e.toString());
		}catch(Exception e) {
			System.out.println("unknown error...");
			e.printStackTrace();
		}
		
		return conn;
	}
}
