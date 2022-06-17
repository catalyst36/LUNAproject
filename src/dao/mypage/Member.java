package dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;
import util.SEQ;

public class Member {
		
	public ArrayList<RefundData> viewPurchasedBook(String mem_id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<RefundData> refundList = new ArrayList<>();
		
		try {
			conn = Connect.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ROWNUM NUM, A.* FROM (SELECT C.cart_id, B.BOOK_NAME"
	                + ", B.BOOK_AUTHOR"
	                + ", B.BOOK_SALE"
	                + ", C.CART_MEM"
	                + ", C.CART_DATE, C.CART_QTY"
	                + " FROM CART C, book B"
	                + " where C.CART_ID = B.BOOK_ID"
	                + " AND CART_MEM = ?"
	                +" AND CART_STATE = '1'"
	                +" order by CART_DATE DESC)A");
			
			pstm = conn.prepareStatement(sql.toString());
			
			pstm.setString(1, mem_id);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				RefundData rd = new RefundData();
				
				rd.setNum(rs.getInt("NUM"));
				rd.setRefund_bookId(SEQ.createSequenceKey(rs.getString("cart_id")));
				rd.setRefund_bookName(rs.getString("book_name"));
				rd.setRefund_author(rs.getString("book_author"));
				rd.setRefund_sale(rs.getInt("book_sale"));
				rd.setRefund_date(rs.getString("cart_date"));
				rd.setRefund_qty(rs.getInt("cart_qty"));
				
				
				refundList.add(rd);
			}
			
			
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
		return refundList;
	}
	
	
	
	void refund() {
		
	}
	
	
	
}
