package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.BookVO;
import vo.MemberVO;

public class InputCartDAO {

	public int insertCart(BookVO book, MemberVO member, int cart_qty) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;

		conn = ConnectDB.connectDB();
		StringBuffer sql = new StringBuffer();

		sql.append("insert into cart(cart_no, cart_id, cart_mem, cart_qty, cart_date)");
		sql.append("values(cart_seq.nextval,?,?,?,sysdate)");

		pstm = conn.prepareStatement(sql.toString());

		pstm.setString(2, book.getBook_id());
		pstm.setString(3, member.getMem_id());
		pstm.setString(4, Integer.toString(cart_qty));

		int executeUpdate = pstm.executeUpdate();

		pstm.close();
		conn.close();
		
		return executeUpdate;
	}
}
