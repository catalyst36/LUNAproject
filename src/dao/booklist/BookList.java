package dao.booklist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.Connect;
import util.SEQ;
import vo.BookVO;

public class BookList {

	public ArrayList<BookVO> getWholeList() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<>();
		
		try {
			con = Connect.getConnection();
			stmt = con.createStatement();
			
			String wholeList = "SELECT * FROM BOOK";
			rs = stmt.executeQuery(wholeList);
			int count = 0;
			
			while(rs.next()) {
				
				BookVO book = new BookVO();
				
				String book_id = SEQ.createSequenceKey(rs.getString("BOOK_ID"));
				String book_name = rs.getString("BOOK_NAME");
				int book_sale = rs.getInt("BOOK_SALE");
				String book_author = rs.getString("BOOK_AUTHOR");
				String book_pub = rs.getString("BOOK_PUB");
				int book_qty = rs.getInt("BOOK_QTY");
				String book_genre = rs.getString("BOOK_GENRE");
				int book_qtysale = rs.getInt("BOOK_QTYSALE");
				
				System.out.println("[번호]" + count +"[코드] "+book_id+" [제목] "+book_name+" [가격] "+book_sale+" [저자] "+book_author+
						" [출판사] "+book_pub+" [수량] "+book_qty+" [장르] "+book_genre+" [누적 판매수] "+book_qtysale);
				
				book.setBook_id(book_id);
				book.setBook_name(book_name);
				book.setBook_sale(book_sale);
				book.setBook_author(book_author);
				book.setBook_pub(book_pub);
				book.setBook_qty(book_qty);
				book.setBook_genre(book_genre);
				book.setBook_qtysale(book_qtysale);
				
				list.add(book);
				count++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("[SQL FAILURE]");
		}finally {
			try {
			if(con != null) con.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("[MEMORY RECOVERY FAILURE");
			}
		}
		return list;
	}
}