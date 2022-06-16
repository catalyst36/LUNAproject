package dao.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import service.Manager;
import util.Connect;

public class ManagerListDAO1 {

	
	public void selectMember(){
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		Statement stmt = null;

		String genre = "";

		Connection conn = Connect.getConnection();
		
		try {	

			String sql = "select m.mem_id, m.mem_pw, m.mem_name , m.mem_add1, m.mem_add2 ,"
					+ "m.mem_email, m.mem_bir, m.mem_mileage ,m.mem_ph ,m.mem_cash ,"
					+ "m.mem_grade, nvl(sum(c.cart_qty),0)  from member m, cart c where mem_id = cart_mem  (+)"
					+ "group by m.mem_id, m.mem_pw, m.mem_name , m.mem_add1, m.mem_add2 ,"
					+ "m.mem_email, m.mem_bir, m.mem_mileage ,m.mem_ph ,m.mem_cash ,m.mem_grade "
					+ "order by m.mem_name";
			String sql1 ="select m.mem_id, m.mem_pw, m.mem_name , m.mem_add1, m.mem_add2 ,"
					+ "m.mem_email, m.mem_bir, m.mem_mileage ,m.mem_ph ,m.mem_cash ,"
					+ "m.mem_grade, nvl(sum(c.cart_qty),0)  from member m, cart c where mem_id = cart_mem (+) "
					+ "group by m.mem_id, m.mem_pw, m.mem_name , m.mem_add1, m.mem_add2 ,"
					+ "m.mem_email, m.mem_bir, m.mem_mileage ,m.mem_ph ,m.mem_cash ,m.mem_grade "
					+ "order by m.mem_bir";
			String sql2 = "select m.mem_id, m.mem_pw, m.mem_name , m.mem_add1, m.mem_add2 ,"
					+ "m.mem_email, m.mem_bir, m.mem_mileage ,m.mem_ph ,m.mem_cash ,"
					+ "m.mem_grade, nvl(sum(c.cart_qty),0)  from member m, cart c where mem_id = cart_mem (+) "
					+ "group by m.mem_id, m.mem_pw, m.mem_name , m.mem_add1, m.mem_add2 ,"
					+ "m.mem_email, m.mem_bir, m.mem_mileage ,m.mem_ph ,m.mem_cash ,m.mem_grade "
					+ "order by sum(c.cart_qty)";
			
			stmt = conn.createStatement();
			
		//
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println(" 1. 이름 순 |  2. 생일 순 |  3. 도서 구매 순 ");
			System.out.println("---------------------------------------------------------------------------------");
			int input = sc.nextInt();
			
			switch(input){
			case 1: 
				rs = stmt.executeQuery(sql);
				break;
			case 2:
				rs = stmt.executeQuery(sql1);
				break;
			case 3:
				rs = stmt.executeQuery(sql2);
				break;
			}
			

			System.out.println("ID\t\t패스워드\t\t이름\t\t주소1\t\t주소2\t\t이메일\t\t생일\t\t마일리지 현황\t\t전화번호\t\t보유금액\t\t회원등급");
			System.out.println("====================================================================");
			while(rs.next()){
				String mem_id = rs.getString("mem_id");
				String mem_pw = rs.getString("mem_pw");
				String mem_name = rs.getString("mem_name");
				String mem_add1 = rs.getString("mem_add1");
				String mem_add2 = rs.getString("mem_add2");
				String mem_email = rs.getString("mem_email");
				String mem_bir = rs.getString("mem_bir");
				String mem_mileage = rs.getString("mem_mileage");
				String mem_ph = rs.getString("mem_ph");
				String mem_cash = rs.getString("mem_cash");
				String mem_grade = rs.getString("mem_grade");

				System.out.println(mem_id + "\t\t" + mem_pw + "\t\t" + mem_name + "\t\t" + mem_add1 + "\t\t"
					+ mem_add2 + "\t\t" + mem_email + "\t\t" + mem_bir + "\t\t" + mem_mileage + "\t\t" + mem_ph
					+ "\t\t" + mem_cash + "\t\t" + mem_grade);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null){   // 자원회수하는 것이 close()
					rs.close();
				}if(stmt !=null) stmt.close();
					if(conn != null) ((Connection) conn).close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		Manager manager = new Manager();
		manager.exe();
	}
	
}
