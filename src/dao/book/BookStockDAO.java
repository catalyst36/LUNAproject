package dao.book;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import service.Manager;
import util.BufferUtil;
import util.Connect;
import util.SEQ;

public class BookStockDAO {

	
	public void insertBook() throws Exception{
		Manager manager = new Manager();
		Connection conn = Connect.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null; // sql 동적 쿼리 명령 객체
		CallableStatement cstmt = null;
		Statement stmt = null;
		
		try {	


	        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.printf("%15s","책 이름 입력 : ");
			String bname = BufferUtil.readLine();
			System.out.printf("%15s","책 가격 입력 : ");
			int  bsale = BufferUtil.nextInt();
			System.out.printf("%15s","책 저자 입력 : ");
			String  bauthor = BufferUtil.readLine();
			System.out.printf("%15s","책 출판사 입력 : ");
			String  bpub = BufferUtil.readLine();
			System.out.printf("%15s","책 수량 입력 : ");
			int  bqty = BufferUtil.nextInt();
			System.out.printf("%15s","책 장르 입력 [소설,입문,에세이,경제경영,자기계발,예술 및 대중문화, 수험서, 여행] : ");
			String  bgenre = BufferUtil.readLine();
			System.out.printf("%15s","책 줄거리 입력 : ");
			String  bsummary = BufferUtil.readLine();
	        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

			stmt = conn.createStatement();
			String sql = "select book_name, book_id from book where book_name = '"+bname+"'";
			rs = stmt.executeQuery(sql);
			
			if (!rs.next()){
				StringBuilder sb = new StringBuilder();
				
				sb.append(" { call book_stock(?,?,?,?,?,?,?) } ");
				cstmt = conn.prepareCall(sb.toString());
				
				cstmt.setString(1, bname);
				cstmt.setInt(2, bsale);
				cstmt.setString(3, bauthor);
				cstmt.setString(4, bpub);
				cstmt.setInt(5, bqty);
				cstmt.setString(6, bgenre);
				cstmt.setString(7, bsummary);
				
				
			int res =cstmt.executeUpdate();
			if (res ==0){
		        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%52s\n","입고 서적 내용 기입을 실패하였습니다.");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			} else{
		        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%52s\n","입고 서적이 추가되었습니다.");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			}

				}
			else {
				rs.close();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String name = rs.getString("book_name");
					String id = SEQ.createSequenceKey(rs.getString("book_id"));

			        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.println("     " + name + " 도서는 이미 등록되어 있습니다.  책 코드 : " + id);
			        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					
				}

				manager.exe();
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null){   // 자원회수하는 것이 close()
					rs.close();
				}if(pstmt !=null) cstmt.close();
					if(conn != null) conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
		
		manager.exe();
	}
	
}
