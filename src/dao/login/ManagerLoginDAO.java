package dao.login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import util.Connect;
import vo.EmployeeVO;

public class ManagerLoginDAO {

	
	public EmployeeVO empLogin(String emp_id,String emp_pw) throws Exception {
		EmployeeVO vo = null;
		
		Connection conn = Connect.getConnection();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String genre = "";
		
		try {	
			
			String sql = " select emp_pw, emp_id from employee where emp_id = '"+ emp_id +"' "
					+ "and emp_pw = '" + emp_pw+"' ";


			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				String empId = rs.getString("emp_id");
				String empPw = rs.getString("emp_pw");
				vo = new EmployeeVO(empId,empPw);
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
		return vo;
	}
	
	
}
