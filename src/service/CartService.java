package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.cart.CartDAO;
import util.BufferUtil;
import util.PrintUtil;
import vo.CartVO;

public class CartService {

	CartDAO cartDao = new CartDAO();
	
	void cartView(int cartNumber, String mem_id) throws IOException {
		
		int input; 
		while(true) {
			
			ArrayList<CartVO> list = cartDao.selectCart(mem_id);
			
			System.out.println("---------------------------------------------------------");
			System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%25s %23s %23s\n","1.구매","2.상품삭제","3.뒤로가기");
	        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
	        System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");
			input = BufferUtil.nextInt();
			
			System.out.println();
			if(input == 1) {
				if(list.isEmpty()) {
				System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.printf("%55s\n","장바구니에 상품이 없습니다");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				}else
					cartDao.paymentCartList(list);
			}
			else if(input ==2) {
				System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.printf("%55s\n","장바구니 삭제를 진행합니다");
		        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		        System.out.printf("%55s","삭제할 번호를 입력하세요 ☞ ");
				int select = BufferUtil.nextInt();
				PrintUtil.print();
				System.out.println();
				cartDao.removeCart(select, mem_id);
			}
			else
				PrintUtil.print();
				return;
		}
	}
	
}
