package service;

import java.io.IOException;
import java.util.ArrayList;
import dao.booklist.BookList;
import dao.cart.CartDAO;
import util.BufferUtil;
import vo.BookVO;

public class BookListService {
	
	public void viewBookList(int cartNumber, String mem_id) throws IOException {
		
		CartDAO cart = new CartDAO();
		BookList booklist = new BookList();
		
		
		
		ArrayList<BookVO> list = booklist.getWholeList();
		
		while(true) {
			System.out.print("장바구니에 추가할 도서 번호 : ");
			int input = BufferUtil.nextInt();
			System.out.println("구매 수량 : ");
			int qty = BufferUtil.nextInt();
			cart.insertCart(list.get(input).getBook_id(), mem_id, qty, cartNumber);
		}
	}
}
