package dao.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import service.Manager;
import util.BufferUtil;
import util.Connect;

public class ManagerListDAO1 {

	
	public void selectMember() throws Exception{
		ResultSet rs = null;
		Statement stmt = null;

		String genre = "";

		Connection conn = Connect.getConnection();
		
		try {	

			String sql = "select m.mem_id, m.mem_pw, m.mem_name, m.mem_add1 , m.mem_add2 , m.mem_email, "
					+ " m.mem_bir, m.mem_mileage, m.mem_ph, m.mem_cash, m.mem_grade, nvl(c.sum_qty,0) "
					+ " from member m, ("
					+ "    select cart_mem, nvl(sum(cart_qty),0) sum_qty "
					+ "    from cart "
					+ "    where cart_state = 1 "
					+ "    group by cart_mem "
					+ "    ) c "
					+ " where m.mem_id = c.cart_mem (+) "
					+ " order by m.mem_name ";
			String sql1 = "select m.mem_id, m.mem_pw, m.mem_name, m.mem_add1 , m.mem_add2 , m.mem_email, "
					+ " m.mem_bir, m.mem_mileage, m.mem_ph, m.mem_cash, m.mem_grade, nvl(c.sum_qty,0) "
					+ " from member m, ("
					+ "    select cart_mem, nvl(sum(cart_qty),0) sum_qty "
					+ "    from cart "
					+ "    where cart_state = 1 "
					+ "    group by cart_mem "
					+ "    ) c "
					+ " where m.mem_id = c.cart_mem (+) "
					+ " order by m.mem_bir ";
			String sql2 = "select m.mem_id, m.mem_pw, m.mem_name, m.mem_add1 , m.mem_add2 , m.mem_email, "
					+ " m.mem_bir, m.mem_mileage, m.mem_ph, m.mem_cash, m.mem_grade, nvl(c.sum_qty,0) "
					+ " from member m, ("
					+ "    select cart_mem, nvl(sum(cart_qty),0) sum_qty "
					+ "    from cart "
					+ "    where cart_state = 1 "
					+ "    group by cart_mem "
					+ "    ) c "
					+ " where m.mem_id = c.cart_mem (+) "
					+ " order by m.mem_grade, c.sum_qty desc ";
			
			stmt = conn.createStatement();
			
		//
			
   		 System.out.print("\n\n\n");
         System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%30s %20s %20s\n","1.이름 순","2.생일 순", "3.회원 등급 순");
        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			
			
			int input = BufferUtil.nextInt();
			
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
			

			System.out.printf("%10s      %10s   %10s                   %10s                          %10s                      %10s           %10s             %10s     %10s        %10s    ","ID","Password","이름","주소","이메일","생일","마일리지 현황","전화번호","보유금액","회원등급");
			System.out.printf("\n%100s\n","─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
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
				String mem_qty = rs.getString("nvl(c.sum_qty,0)");
				
				
				System.out.printf(" %-10s    %-10s     %-10s    %-50s %-20s %-10s           %-15s           %-20s          %-10s        %-10s   \n",mem_id,mem_pw,mem_name,mem_add1+mem_add2,mem_email, mem_bir, mem_mileage, mem_ph,mem_cash,mem_grade,mem_qty);

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
