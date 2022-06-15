package dao.cart;

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

		Connection conn = Connect.getConnection();
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

			System.out.println("  번호   주문번호    	 도서 이름 		도서코드   수량");
			
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
				
				System.out.println("-------------------------------------------------------------");
				System.out.printf("| %3d | %5s | %26s | %6s | %3d |\n", count,cart_no,book_name,cart_id,cart_qty);
				
				count++;
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
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = Connect.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("insert into cart(cart_num, cart_no, cart_id, cart_mem, cart_qty, cart_date)");
			sql.append("values(cart_seq.nextval," + cartNumber + ",?,?,?,sysdate)");

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
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = Connect.getConnection();
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
		
		Connection conn = Connect.getConnection();
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
	
	public void paymentCartList(ArrayList<CartVO> list){
		
		Connection conn = Connect.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		int totalPrice = 0;
		int bookQty = 0;
		int bookSale = 0;
		int memberCash = 0;
		
		try {
			stmt = conn.createStatement();
			for(int i=0;i<list.size();i++) {
				String sql = "select book_qty, book_sale"
						+ " from book "
						+ " where book_id = '" + list.get(i).getCart_id() + "'";
	
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					bookQty = rs.getInt("book_qty");
					bookSale = rs.getInt("book_sale") * list.get(i).getCart_qty();
				}
				
				bookQty -= list.get(i).getCart_qty();
				totalPrice += bookSale;
				
				rs.close();
				
			}
			
			String sql = "select mem_cash from member where mem_id = '" + list.get(0).getCart_mem() + "'";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				memberCash = rs.getInt("mem_cash");
			}
			if(memberCash >= totalPrice) {
				
				for(int i=0;i<list.size();i++) {
					sql = "update book"
							+ " set book_qty = book_qty - " + list.get(i).getCart_qty()
							+ " where book_id = '" + list.get(i).getCart_id() + "'";
				}
				
				sql = "update member"
					+ " set mem_cash = mem_cash - " + totalPrice
					+ " where mem_id = '" + list.get(1).getCart_mem() + "'";
				stmt.executeUpdate(sql);
			}else {
				return;
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
		
	}

}
