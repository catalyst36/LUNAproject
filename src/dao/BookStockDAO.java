package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BookStockDAO {

	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "proj";
		String pass = "java";
		ResultSet rs = null;
		PreparedStatement pstmt = null; // sql 동적 쿼리 명령 객체
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {	
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			StringBuilder sb = new StringBuilder();
			
			sb.append(" { call book_stock(?,?,?,?,?,?,?) } ");

			cstmt = conn.prepareCall(sb.toString());
			
			System.out.print("책 이름 입력 : ");
			String bname = sc.nextLine();
			System.out.print("책 가격 입력 : ");
			int  bsale = sc.nextInt();
			System.out.print("책 저자 입력 : ");
			String  bauthor = sc.next();
			System.out.print("책 출판사 입력 : ");
			String  bpub = sc.next();
			System.out.print("책 수량 입력 : ");
			int  bqty = sc.nextInt();
			System.out.print("책 장르 입력 : ");
			String  bgenre = sc.next();
			System.out.print("책 줄거리 입력 : ");
			String  bsummary = sc.next();

			
			cstmt.setString(1, bname);
			cstmt.setInt(2, bsale);
			cstmt.setString(3, bauthor);
			cstmt.setString(4, bpub);
			cstmt.setInt(5, bqty);
			cstmt.setString(6, bgenre);
			cstmt.setString(7, bsummary);
			

			
			int res =cstmt.executeUpdate();
			
			if (res ==0){
				System.out.println("입고 서적 내용 기입을 실패하였습니다.");
			} else{
				System.out.println("입고 서적이 추가되었습니다.");
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
		
		Manager manager = new Manager();
		manager.exe();
	}
	
}
