package dao.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import util.Connect;
import util.SEQ;

public class ManagerListDAO2 {

	public void bookSelect() {

		Connection conn = Connect.getConnection();
		
		ResultSet rs = null;
		Statement stmt = null;
		String genre = "";
		
		try {	
			
			String sql = " select book_id, book_name, book_sale ,book_author ,book_pub , "
					+ " book_qty, book_genre,book_qtysale from book";

			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.printf("\n %-5s %-53s %-5s %-15s %-15s %-3s %-9s %-6s\n"
					,"책 코드", "제목", "가격", "저자", "출판사", "수량", "장르", "누적판매수");
			System.out.printf("\n%100s\n","─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			while(rs.next()){
				String book_id = SEQ.createSequenceKey(rs.getString("book_id"));
				String book_name = rs.getString("book_name");
				String book_sale = rs.getString("book_sale");
				String book_author = rs.getString("book_author");
				String book_pub = rs.getString("book_pub");
				String book_qty = rs.getString("book_qty");
				String book_genre = rs.getString("book_genre");
				String book_qtysale = rs.getString("book_qtysale");

				  System.out.printf("\n %-5s %-53s %-5s %-15s %-15s %-3s %-9s %-6s", book_id, book_name, book_sale, book_author, book_pub, book_qty,
						  book_genre, book_qtysale);

			}				  System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null){   // 자원회수하는 것이 close()
					rs.close();
				}if(stmt !=null) stmt.close();
					if(conn != null) conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	}
	
}

