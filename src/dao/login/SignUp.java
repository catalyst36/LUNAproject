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
			
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			System.out.println("┌──────┤ 회원가입 ├──────┐");
			System.out.println("│      └───────┘ ");
			System.out.println("│       [가입정보입력]      │");
			System.out.println("└───────────────────────┘");
			System.out.println("\n\n\n\n\n");
			System.out.print("[아이디] >> ");
			String mem_id = BufferUtil.readLine();
			System.out.println();
			System.out.print("[비밀번호] >> ");
			String mem_pw = BufferUtil.readLine();
			System.out.println();
			System.out.print("[이름] >> ");
			String mem_name = BufferUtil.readLine();
			System.out.println();
			System.out.print("[생년월일] >> ");
			String mem_bir = BufferUtil.readLine();
			System.out.println();
			System.out.print("[전화번호] >> ");
			String mem_ph = BufferUtil.readLine();
			System.out.println();
			System.out.print("[이메일] >> ");
			String mem_email = BufferUtil.readLine();
			System.out.println();
			System.out.print("[주소] >> ");
			String mem_add1 = BufferUtil.readLine();
			System.out.println();
			System.out.print("[상세주소] >> ");
			String mem_add2 = BufferUtil.readLine();
			System.out.println();
			System.out.print("[충전금액] >> ");
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
			
			if(rs != 0 ) System.out.println("[회원가입 성공]");
			else System.out.println("[회원가입 실패]");
		
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
