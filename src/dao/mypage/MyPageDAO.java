package dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.BufferUtil;
import util.Connect;
public class MyPageDAO {
	

	// ------------------------------ 회원정보확인

	public static void infoView() {

		Connection conn = null; // 연결객체
		ResultSet rs = null; // 결과를 보관할 객체
		Statement stmt = null; // sql명령 객체
		PreparedStatement pstmt = null; // 동적 sql 명령객체

		try {

			conn = Connect.getConnection();
			
			
	           System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

	           System.out.printf("%55s \n","회원님의 기본정보를 조회합니다.");
	           System.out.printf("%58s \n","인증을 위해 회원님의 ID를 입력해주세요.");

	           System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			

	             System.out.printf("%50s","ID입력 ☞ ");
			
			
			String getid = BufferUtil.readLine();
			String sql = "select mem_id,mem_add1,mem_add2, mem_email, mem_bir, mem_mileage, mem_cash, mem_grade"
					   + " from member"
					   + " where mem_id = ? ";

			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, getid);

			rs = pstmt.executeQuery();

			String id=null;
			while (rs.next()) {

				id = rs.getString("mem_id");
				String add1 = rs.getString("mem_add1");
				
				String add2 = rs.getString("mem_add2");
				String em = rs.getString("mem_email");
				String bir = rs.getString("mem_bir");
				int mil = rs.getInt("mem_mileage");
				int cash = rs.getInt("mem_cash");
				String grade = rs.getString("mem_grade");
				
				
				
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		         System.out.printf("%20s %25s\n","ㆍID : ",id);
		         System.out.printf("%20s %25s\n","ㆍ등급 : ",grade);
		         System.out.printf("%20s %25s\n","ㆍ생일  : ",bir);
		         System.out.printf("%20s %25s\n","ㆍ이메일  : ",em);
		         System.out.printf("%20s %25s\n","ㆍ주소  : ",add1+add2);
		         System.out.printf("%20s %25s %20s %25s %20s\n","ㆍ마일리지  : ",mil, "||", "ㆍ보유캐시 : ",cash);
		         System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				}
			if(id==null){                       //id입력 잘못했을때!!
				System.out.println();
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%53s \n","※입력하신 ID는 없는ID입니다.※");
			     System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				
				String a=BufferUtil.readLine();
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
			}
		}
	}

	// -------------------------------------- 회원탈퇴
	public void delMem() {

		Connection conn = null; // 연결객체
		ResultSet rs = null; // 결과를 보관할 객체
		Statement stmt = null; // sql명령 객체
		PreparedStatement pstmt = null; // 동적 sql 명령객체

		String id = null;
		String name = null;
		String bir = null;
		String checkPw = null;

		try {
			conn = Connect.getConnection();

			System.out.printf("%50s","회원님의 id를 입력해주세요 ☞ ");
			String getId = BufferUtil.readLine();

			String sql = "select mem_id,mem_name,mem_bir" + " from member"
					+ " where mem_id = ?";
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, getId);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				id = rs.getString("mem_id");
				name = rs.getString("mem_name");
				bir = rs.getString("mem_bir");

			}
			if(id.equals(getId)){
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.printf("%50s","회원님의 정보가 아래와 같습니까?");
			System.out.printf("%30s %30s\n","ID : ",id); 
			System.out.printf("%20s %30s \n ","이름 : ",name);
			System.out.printf("%20s %30s \n","생일 : ",bir);
			System.out.println();
			System.out.printf("%45s %45s","1.예","2.아니오");
		     System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		     System.out.printf("%55s","입력 ☞ ");

			int input = BufferUtil.nextInt();

			if (input == 1) {
				System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%54s","회원 탈퇴를 진행하시겠습니까?");
				System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				
				  System.out.printf("%55s","입력 ☞ ");
				  int put = BufferUtil.nextInt();

				if (put == 1) {
					String sql1 = "select mem_pw" + " from member"
							+ " where mem_id = '"+getId+"'";
					
					stmt=conn.createStatement();
					
					rs=stmt.executeQuery(sql1);
					
					if(rs.next()) {
						checkPw=rs.getString("mem_pw");
					}
					
					
					  System.out.printf("%55s","비밀번호를 입력해주세요 ☞ ");
					String getPw = BufferUtil.readLine();

					if(checkPw.equals(getPw)){
						String delsql = "delete" + " from member"
								+ " where mem_id=?" + " and mem_pw=?";
						pstmt = conn.prepareStatement(delsql);

						pstmt.setString(1, getId);
						pstmt.setString(2, getPw);
						pstmt.executeUpdate();
						int rs1 = pstmt.executeUpdate();
						 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.printf("%49s","회원탈퇴성공" + rs1);
						System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				

						String a=BufferUtil.readLine();
					}else{
						 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.printf("%45\n","비밀번호를 잘못 입력하셨습니다.");
						System.out.printf("%50s","다시 시도해주세요");
						System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				

						String a=BufferUtil.readLine();
					}
					
				}	

					if (put == 2) {
						return;
					}
				
			}
			if (input == 2) {
				return;
			}
			}else {
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%50s","입력하신 계정이 없습니다.");       //id또는 이름 잘못입력했을때
				System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				

				String a=BufferUtil.readLine();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

			}
		}
	}

	// ----------------------------비밀번호 변경/ 잘못입력하였을때 오류문도 완료
	public void pwChange() {
		Connection conn = null; // 연결객체
		ResultSet rs1 = null; // 결과를 보관할 객체
		Statement stmt = null; // sql명령 객체
		PreparedStatement pstmt = null; // 동적 sql 명령객체

		String getid;
		String getname;

		try {
			conn = Connect.getConnection();

			  System.out.printf("%55s","ID 입력 ☞ ");
			getid = BufferUtil.readLine(); // id를 입력받아서 인증
			  System.out.printf("%55s","이름 입력 ☞ ");
			getname = BufferUtil.readLine(); // 이름도 인증

			String sql = "select mem_id, mem_name" + " from member"
					+ " where mem_id =?" + " and mem_name =?";

			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, getid);
			pstmt.setString(2, getname);

			rs1 = pstmt.executeQuery();
			
			String id=null;
			String name=null;

// ----------------
			if (rs1.next()) {
				id=rs1.getString("mem_id");
				name=rs1.getString("mem_name");
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%50s","비밀번호 변경합니다.\n");
				System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");	
				  System.out.printf("%55s","비밀번호 입력 ☞ ");

				String pwCh = BufferUtil.readLine();

				String pwSql = "update member" + " set mem_pw=?"
						+ " where mem_id=?" + " and mem_name=?";

				pstmt = conn.prepareStatement(pwSql);

				pstmt.setString(1, pwCh);
				pstmt.setString(2, getid);
				pstmt.setString(3, getname);
				

				int rs2 = pstmt.executeUpdate();

				if (rs2 != 0) {
					 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("%50s","비밀번호가 변경되었습니다.");
					System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				

					String a=BufferUtil.readLine();
				}
			}
			if(id==null||name==null){
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%50s","정보를 잘못 입력하셨습니다.");
				System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				

				String a=BufferUtil.readLine();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

			}
		}

	}

	// 포인트 충전
	public void cashCharge() {
		Connection conn = null; // 연결객체
		ResultSet rs = null; // 결과를 보관할 객체
		Statement stmt = null; // sql명령 객체
		PreparedStatement pstmt = null; // 동적 sql 명령객체
        int tcash=0;
        String pw=null;
        
		try {
			conn = Connect.getConnection();

			 System.out.printf("%52s","ID입력 ☞ ");
			String getid = BufferUtil.readLine();

			String sql = "select mem_name,mem_cash" + " from member"
			
					+ " where mem_id =?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, getid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int cash = rs.getInt("mem_cash");
				System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%50s %6d\n","현재 보유 캐쉬 :",cash);
				System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				
			}
			 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.printf("%50s","캐쉬를 충전하시겠습니까?");
			System.out.printf("%6s %6s\n","1.예","2.아니오");
			System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				
			  System.out.printf("%55s","입력 ☞ ");
			int put = BufferUtil.nextInt();

			if (put == 1) {
				
				System.out.printf("%50s","충전할 금액을 입력하세요 ☞ ");
				int charge = BufferUtil.nextInt();

				System.out.println();
				  System.out.printf("%55s","비밀번호 입력 ☞ ");
				  String getpw = BufferUtil.readLine();
				
				
				String sql3="select mem_cash, mem_pw from member where mem_id = '"+getid+"'";
				
				stmt=conn.createStatement();
				
				rs=stmt.executeQuery(sql3);
				
				if(rs.next()) {
					tcash=rs.getInt("mem_cash");
					pw=rs.getString("mem_pw");
				}
				if(pw.equals(getpw)){
				
				tcash=tcash+charge;
	
				String cashSql = "update member" + " set mem_cash= ?"
							+ " where mem_id=?" + " and mem_pw=?";
					
					
				pstmt = conn.prepareStatement(cashSql);
		
				pstmt.setInt(1, tcash);
				pstmt.setString(2, getid);
				pstmt.setString(3, getpw);
					
	
				pstmt.executeUpdate();
				int rs1 = pstmt.executeUpdate();
	
				String sql2 = "select mem_name,mem_cash" + " from member"
							+ " where mem_id =?";
				stmt = conn.createStatement();
	
				pstmt = conn.prepareStatement(sql2);
	
				pstmt.setString(1, getid);
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					int cash = rs.getInt("mem_cash");
					System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("%50s \n","충전되었습니다.");
					System.out.printf("%50s %6d\n","현재 보유 캐쉬 :",cash);
					System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				
				}
			}else{
				 System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%50s","비밀번호가 틀렸습니다.");
				System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");				

				String a=BufferUtil.readLine();
				}
			}
			if (put == 2) {
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

			}
		}
	}

}