package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.booklist.BookList;
import dao.cart.CartDAO; 
import util.BufferUtil;
import util.SEQ;
import vo.BookVO;

public class BookListService {
	
	public void viewBookList(int cartNumber, String mem_id, ArrayList<BookVO> list) throws IOException {
		
		
		BookList bl = new BookList();
		
		boolean run = true;
		
		while(run){
		
		System.out.println("[1. 전체 목록 2. 검색 3. 장바구니 담기 4. 나가기]");
		System.out.print("<입력> : ");
		int select1 = BufferUtil.nextInt();
		
		switch(select1) {
		case 1:
			
			bl.getWholeBookList(list);
			break;
			
		case 2:
			
			System.out.println("[1. 제목 검색 2. 장르 검색 3. 저자 검색]");
			System.out.print("<입력> : ");
			int select2 = BufferUtil.nextInt();
			
			switch(select2) {
			
			case 1:
				bl.searchBookName(list);
				break;
			
			case 2:
				bl.searchBookGenre(list);
				break;
			
			case 3:
				bl.searchBookAuthor(list);
				break;
				
			}
			
			break;
			
		case 3:
			
			System.out.println("[장바구니에 담을 도서의 번호를 입력해주세요]");
			System.out.print("<입력> : ");
			int book_num = BufferUtil.nextInt();
			System.out.println("[수량을 입력해주세요]");
			System.out.print("<입력> : ");
			int cart_qty = BufferUtil.nextInt();
			for(int i = 0; i<list.size(); i++) {
				if(book_num == list.get(i).getBook_num()) {
					if(list.get(i).getBook_qty() >= cart_qty) {
						String bookId = Integer.toString(SEQ.returnSequenceKeyList(list.get(i)));
						CartDAO cd = new CartDAO();
						cd.insertCart(bookId, mem_id, cart_qty, cartNumber);
					}else {
						System.out.println("도서 재고가 부족합니다.");
						break;
					}
				}
			}
			break;
			
		case 4:
			run = false;
			
		}
		}
	}
}