package dao.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.cart.CartDAO;
import service.MainMenu;
import util.BufferUtil;
import util.Connect;

public class LoginDAO {

	
	public void memberLogin () {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = Connect.getConnection();
			String sql = "SELECT MEM_ID, MEM_PW FROM MEMBER";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%55s\n","로그인을 진행합니다");
	        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

	        System.out.printf("%55s","아이디를 입력하세요 ☞ ");
			String id = BufferUtil.readLine();
			System.out.printf("%55s","비밀번호를 입력하세요 ☞ ");
			String pw = BufferUtil.readLine();			
			System.out.println();
			int missIdCount = 0;
			int rowCount= 0;
			
			while(rs.next()) {
				
				rowCount++;
				
				String mem_id = rs.getString("MEM_ID");
				String mem_pw = rs.getString("MEM_PW");
				
				if(id.equals(mem_id)) {
					if(pw.equals(mem_pw)) {
						System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				        System.out.printf("%55s\n","※로그인에 성공했습니다※");
				        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println();
						MainMenu sc2 = new MainMenu();
						CartDAO cart = new CartDAO();
						int cartNumber = cart.getNewCartNumber();
						sc2.screen2(cartNumber, mem_id);
						return;
					}else {
						
						boolean run = true;
						while(run) {
						System.out.println(" ---------------------- ");
						System.out.println("|    비밀번호가 틀렸습니다    |");
						System.out.println("| 새 비밀번호를 만드시겠습니까? |");
						System.out.println("|    1. 예   2. 아니오    |");
						System.out.println(" ---------------------- ");
						System.out.print("[선택] >> ");
						int select = BufferUtil.nextInt();
						
						switch(select) {
						case 1:
							memberPasswordReset();
							run = false;
							break;
						case 2:
							System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					        System.out.printf("%55s\n","메인 메뉴로 돌아갑니다");
					        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					        System.out.println();
							run = false;
							break;
						default:
							System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					        System.out.printf("%55s\n","\"예\" 또는 \"아니오\"를 선택하세요");
					        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					        System.out.println();
							break;
						}
						}
					}
				}else {
					missIdCount++;
				}
				
			}
			
			if(missIdCount==rowCount) {
				System.out.println(" -------------------- ");
				System.out.println("| 아이디가 존재하지 않습니다 |");
				System.out.println("|  회원가입을 진행해주십시오 |");
				System.out.println(" -------------------- ");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("[SQL FAILURE]");
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("[INPUT FAILURE]");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[METHOD FAILURE]");
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
	} 
	
	public static void memberPasswordReset() throws Exception{
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		con = Connect.getConnection();
		stmt = con.createStatement();
		
		System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%55s\n","아래의 회원 정보를 입력하세요");
        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println();
        System.out.printf("%55s","아이디를 입력하세요 ☞ ");
		String mem_id = BufferUtil.readLine();
		System.out.printf("%55s","이름을 입력하세요 ☞ ");
		String mem_name = BufferUtil.readLine();
		System.out.printf("%55s","생년월일을 입력하세요 ☞ ");
		String mem_bir = BufferUtil.readLine();
		System.out.printf("%55s","전화번호를 입력하세요 ☞ ");
		String mem_ph = BufferUtil.readLine();
		
		String sql = "SELECT MEM_ID, MEM_NAME, MEM_BIR, MEM_PH"+
					 " FROM MEMBER"+
					 " WHERE MEM_ID = "+"'"+mem_id+"'"+
					 " AND MEM_NAME = "+"'"+mem_name+"'"+
					 " AND MEM_BIR = "+"'"+mem_bir+"'"+
					 " AND MEM_PH = "+"'"+mem_ph+"'";
		
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			
			boolean run = true;
			while(run) {
			System.out.println(" -------------------------- ");
			System.out.println("|      회원정보가 일치합니다      |");
			System.out.println("| 임시로 비밀번호를 생성하시겠습니까? |");
			System.out.println("|     1. 예   2. 아니오       |");
			System.out.println(" -------------------------- ");
			
			System.out.println("[선택] >> ");
			int select = BufferUtil.nextInt();
			
			switch(select) {
			case 1:
				createRandomPw(mem_id);
				run = false;
			case 2:
				System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.printf("%55s\n","메인 메뉴로 돌아갑니다");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.println();
				run = false;
				break;
			default:
				System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.printf("%55s\n","\"예\" 또는 \"아니오\"를 선택하세요");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.println();
				break;
			}
			}
			
		}else {
			System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%55s\n","회원정보가 틀렸습니다");
	        System.out.printf("%55s\n","메인 메뉴로 돌아갑니다");
	        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.println();
		}
		}
	
	public static void createRandomPw(String mem_id) throws Exception{
		
		String[] strSet = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
							"1", "2", "3", "4", "5", "6", "7", "8", "9", "~", "!", "@", "#", "$", "%", "^", "&", "*"};
		String randomPw = "";
		for(int i = 0; i<5; i++) {
			int idx = (int)(Math.random()*44)+1;
			randomPw += strSet[idx];
		}
		
		Connection con = null;
		Statement stmt = null;
		int rs;
		
		con = Connect.getConnection();
		stmt = con.createStatement();
		
		String sql = "UPDATE MEMBER SET MEM_PW = "+"'"+randomPw+"'"+
					 " WHERE MEM_ID = "+"'"+mem_id+"'";
		
		rs = stmt.executeUpdate(sql);
		
		if(rs !=0 ) {
		System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	    System.out.printf("%55s\n","임시 비밀번호가 생성되었습니다");
	    System.out.printf("%55s\n","임시 비밀번호 : "+randomPw);
	    System.out.printf("%55s\n","마이페이지에서 꼭 변경하세요");
	    System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	    System.out.println();		
		}
	}
	
}
