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
		
		System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%20s %18s %18s %18s\n","1.전체목록","2.검색","3.장바구니 담기","4.나가기");
        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");
		int select1 = BufferUtil.nextInt();
		
		switch(select1) {
		case 1:
			
			bl.getWholeBookList(list);
			break;
			
		case 2:
			
			System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%25s %23s %23s\n","1.제목검색","2.장르검색","3.저자검색");
	        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");
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
			
			System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%55s\n","장바구니 담기를 진행합니다");
	        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%55s","책 번호를 입력하세요 ☞ ");
			int book_num = BufferUtil.nextInt();
			System.out.printf("%55s","수량을 입력하세요 ☞ ");
			int cart_qty = BufferUtil.nextInt();
			System.out.println();
			for(int i = 0; i<list.size(); i++) {
				if(book_num == list.get(i).getBook_num()) {
					if(list.get(i).getBook_qty() >= cart_qty) {
						String bookId = Integer.toString(SEQ.returnSequenceKeyList(list.get(i)));
						CartDAO cd = new CartDAO();
						cd.insertCart(bookId, mem_id, cart_qty, cartNumber);
						System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				        System.out.printf("%55s\n","장바구니 담기가 완료되었습니다");
				        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					}else {
						System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				        System.out.printf("%55s\n","선택하신 도서의 재고가 부족합니다");
				        System.out.printf("%55s\n","관리자에게 연락 부탁드립니다");
				        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
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