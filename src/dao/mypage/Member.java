package dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;

public class Member {
		
	public ResultSet viewPurchasedBook(String mem_id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = Connect.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ROWNUM NUM, A.* FROM (SELECT C.cart_id, B.BOOK_NAME"
	                + ", B.BOOK_AUTHOR"
	                + ", B.BOOK_SALE"
	                + ", C.CART_MEM"
	                + ", C.CART_DATE"
	                + " FROM CART C, book B"
	                + " where C.CART_ID = B.BOOK_ID"
	                + " AND CART_MEM = ?"
	                +" AND CART_STATE = '1'"
	                +" order by CART_DATE DESC)A)");
			
			pstm = conn.prepareStatement(sql.toString());
			
			pstm.setString(1, mem_id);
			
			rs = pstm.executeQuery(sql.toString());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	
	
	void refund() {
		
	}
	
	
	
}
