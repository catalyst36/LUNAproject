package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.BufferUtil;
import vo.BookVO;
import vo.CartVO;
import vo.MemberVO;

public class CartDAO {

	public ArrayList<CartVO> selectCart(int cartNumber) throws IOException {

		ConnectDB db = new ConnectDB();
		Connection conn = db.connectDB();
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<CartVO> list = new ArrayList<>();
		int count = 1;
		try {
			String sql = "select cart_no, book_name, cart_id, cart_qty, cart_date, cart_mem "
					   + "from cart,book "
					   + "where cart_id = book_id "
					   + "and cart_no = " + cartNumber;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CartVO cartVo = new CartVO();
				String cart_no = rs.getString("cart_no");
				String book_name = rs.getString("book_name");
				String cart_id = rs.getString("cart_id");
				String cart_date = rs.getString("cart_date");
				String cart_mem = rs.getString("cart_mem");
				int cart_qty = Integer.parseInt(rs.getString("cart_qty"));
				
				cartVo.setCount(count);
				cartVo.setCart_no(cart_no);
				cartVo.setCart_id(cart_id);
				cartVo.setCart_date(cart_date);
				cartVo.setCart_mem(cart_mem);
				cartVo.setCart_qty(cart_qty);
				
				list.add(cartVo);

				System.out.println("-----------------------------------------------------------");
				System.out.printf("| %3d | %3s | %26s | %6s | %3d |", count,cart_no,book_name,cart_id,cart_qty);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void insertCart(String bookId, String memberId, int cart_qty, int cartNumber) {
		ConnectDB db = new ConnectDB();
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = db.connectDB();
			StringBuffer sql = new StringBuffer();

			sql.append("insert into cart(cart_num, cart_no, cart_id, cart_mem, cart_qty, cart_date)");
			sql.append("values(cart_seq.nextval" + cartNumber + ",?,?,?,sysdate)");

			pstm = conn.prepareStatement(sql.toString());

			pstm.setString(1, bookId);
			pstm.setString(2, memberId);
			pstm.setInt(3, cart_qty);

			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeCart(int removeNum) throws IOException {
		ConnectDB db = new ConnectDB();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = db.connectDB();
			StringBuffer sql = new StringBuffer();

			sql.append("delete from cart where cart_no = " + removeNum);

			pstm = conn.prepareStatement(sql.toString());
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getNewCartNumber() {
		ConnectDB db = new ConnectDB();
		Connection conn = db.connectDB();
		ResultSet rs = null;
		Statement stmt = null;
		int newCartNumber = 0;
		try {
			String sql = "select cart_no from("
					+ "select cart_no "
					+ "from cart "
					+ "order by cart_no desc)"
					+ "where rownum = 1";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				newCartNumber = rs.getInt("cart_no");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newCartNumber + 1;
	}

}
