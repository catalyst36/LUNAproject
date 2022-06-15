package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAO {

	public void selectBook() {
		
		ConnectDB db = new ConnectDB();
		Connection conn = db.connectDB();
		ResultSet rs = null;
		Statement stmt = null;
		try {
		String sql = "select * from book";
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			String cart_no = rs.getString("cart_no");
			String book_name = rs.getString("book_name");
			String cart_id = rs.getString("cart_id");
			int cart_qty = Integer.parseInt(rs.getString("cart_qty"));
			
			System.out.println("---------------------------------------------------------");
			System.out.printf("%12s%12s%12s%12s",cart_no, book_name, cart_id, cart_qty);
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

