package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ManagerListDAO2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Connection conn = Connect.getConnection();
		
		ResultSet rs = null;
		Statement stmt = null;
		String genre = "";
		
		try {	
			
			String sql = " select book_id, book_name, book_sale ,book_author ,book_pub , "
					+ " book_qty, book_genre,book_qtysale from book";

			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			System.out.println("책코드\t\t책 이름\t\t판매가\t\t책 저자\t\t출판사\t\t책 수량\t\t장르\t\t판매수량");
			System.out.println("====================================================================");
			while(rs.next()){
				String book_id = rs.getString("book_id");
				String book_name = rs.getString("book_name");
				String book_sale = rs.getString("book_sale");
				String book_author = rs.getString("book_author");
				String book_pub = rs.getString("book_pub");
				String book_qty = rs.getString("book_qty");
				String book_genre = rs.getString("book_genre");
				String book_qtysale = rs.getString("book_qtysale");

				System.out.println(book_id + "\t\t" + book_name + "\t\t" + book_sale + "\t\t"
					+ book_author + "\t\t" + book_pub + "\t\t" + book_qty + "\t\t" + book_genre  + "\t\t" +	book_qtysale);
			}
			
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

