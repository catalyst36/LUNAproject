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
		
		System.out.println();
		while(true) {
			System.out.println("1.도서선택   2.검색   3.돌아가기");
			System.out.println("[입력] ");
			int input = BufferUtil.nextInt();
			
			switch(input) {
			case 1:
				//도서선택 메소드
				break;
			case 2:
				//검색 메소드
			case 3:
				return;
			default:
				break;
			}
		}
	}
}
