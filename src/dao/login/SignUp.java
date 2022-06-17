package dao.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.BufferUtil;
import util.Connect;

public class SignUp {

public void insertMember(){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = Connect.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MEMBER (MEM_ID, MEM_PW, MEM_NAME, MEM_BIR, MEM_PH, MEM_EMAIL, MEM_ADD1, MEM_ADD2, MEM_CASH)");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			
			
			System.out.println("\n\n\n\n\n\n\n");
			
            System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
           System.out.printf("%54s %43s\n"," 회원 가입을 진행합니다.","");
           System.out.printf("%55s %39s\n","아래에 정보를 기입해 주세요.","");
           System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

	        System.out.print("\t\t\t       [아이디] ☞  ");
			String mem_id = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [비밀번호] ☞  ");
			String mem_pw = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [이름] ☞  ");
			String mem_name = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [생년월일] ☞  ");
			String mem_bir = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [전화번호] ☞  ");
			String mem_ph = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [이메일] ☞  ");
			String mem_email = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [주소] ☞  ");
			String mem_add1 = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [상세주소] ☞  ");
			String mem_add2 = BufferUtil.readLine();
			System.out.println();
			System.out.print("\t\t\t       [충전금액] ☞  ");
			int mem_cash = BufferUtil.nextInt();
			
			
			
			
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pw);
			pstmt.setString(3, mem_name);
			pstmt.setString(4, mem_bir);
			pstmt.setString(5, mem_ph);
			pstmt.setString(6, mem_email);
			pstmt.setString(7, mem_add1);
			pstmt.setString(8, mem_add2);
			pstmt.setInt(9, mem_cash);
			
			int rs;
			rs = pstmt.executeUpdate();
			
			if(rs != 0 ) {
	            System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	            System.out.printf("│%-97s│\n","");
				System.out.printf("│%45s %40s │","회원가입 성공","");
		        System.out.printf("│%-97s│\n","");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

			}
			else {
	            System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	            System.out.printf("│%-97s│\n","");
	            System.out.println("[회원가입 실패]");
		        System.out.printf("│%-97s│\n","");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("[SQL FAILURE]");
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("[INPUT FAILURE]");
		}finally {
			try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("[MEMORY RECOVERY FAILURE");
			}
		}
			
	}
	
}
