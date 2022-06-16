package dao.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import service.Manager;
import util.BufferUtil;
import util.Connect;
import util.SEQ;

public class ManagerListDAO3 {

	public void checkStock() throws Exception {
		Manager manager = new Manager();
		SEQ seq = new SEQ();

		Connection conn = Connect.getConnection();
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String genre = "";
		
		try {	
			String sql = " select book_id, book_name, book_sale ,book_author ,book_pub , "
					+ " book_qty, book_genre,book_qtysale from book "
					+ " where book_qty = 0 ";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.isBeforeFirst() == false ){
				System.out.println("품절된 도서가 없습니다.");
				manager.exe();
			}else{
			
				System.out.println("책코드\t\t책 이름\t\t판매가\t\t책 저자\t\t출판사\t\t책 수량\t\t장르\t\t판매수량");
				System.out.println("====================================================================");
				while(rs.next()){
					String book_id = rs.getString("book_id");
					String bookId_final = seq.createSequenceKey(book_id); // DB의 책코드를 문자열로 변환 (65000 -> A000)
					String book_name = rs.getString("book_name");
					String book_sale = rs.getString("book_sale");
					String book_author = rs.getString("book_author");
					String book_pub = rs.getString("book_pub");
					String book_qty = rs.getString("book_qty");
					String book_genre = rs.getString("book_genre");
					String book_qtysale = rs.getString("book_qtysale");

					System.out.println(bookId_final + "\t\t" + book_name + "\t\t" + book_sale + "\t\t"
					+ book_author + "\t\t" + book_pub + "\t\t" + book_qty + "\t\t" + book_genre  + "\t\t" +	book_qtysale);
					

			} 
				System.out.println("====================================================================");

				StringBuilder sql1 = new StringBuilder();
			
				sql1.append("update book set  book_qty = ?  where book_id = ?");

				System.out.print("책 코드 입력 [대소문자 구분] : ");
				String bid = BufferUtil.readLine();
				System.out.print("입고할 수량 입력 : ");
				int  bqty = BufferUtil.nextInt();
			
				int bid_final = seq.returnSequenceKey(bid); // 입력받은 문자열을 숫자로 변환하여 bid_final에 저장
				String bid_fin = Integer.toString(bid_final); // BOOK_ID 컬럼이 VARCHAR2이므로 bid_final을 문자열로 변환 
				System.out.println(bid_final); // 완료되면 지우기
			
				pstmt = conn.prepareStatement(sql1.toString());
				pstmt.setInt(1, bqty);
				pstmt.setString(2, bid_fin); 
			
				int res =pstmt.executeUpdate();
				
				conn.commit();
				
				System.out.println(res);
			
				if (res ==0){
					System.out.println("자료 입력에 실패하였습니다.");
				} else{
					System.out.println("책코드가 [" + bid + "] 인 도서가 " + bqty + "권 추가 입고 되었습니다.");
				}
			
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
		manager.exe();
	}
}