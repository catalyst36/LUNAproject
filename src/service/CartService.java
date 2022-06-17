package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.cart.CartDAO;
import util.BufferUtil;
import vo.CartVO;

public class CartService {

	CartDAO cartDao = new CartDAO();
	
	void cartView(int cartNumber, String mem_id) throws IOException {
		
		int input; 
		while(true) {
			
			ArrayList<CartVO> list = cartDao.selectCart(mem_id);
			
			System.out.println("---------------------------------------------------------");
			System.out.println("[1. 구매      2. 상품삭제     other. 뒤로가기]");
			System.out.print("[입력]");
			input = BufferUtil.nextInt();
			if(input == 1) {
				if(list.isEmpty())
					System.out.println("장바구니가 비었습니다!");
				else
					cartDao.paymentCartList(list);
			}
			else if(input ==2) {
				System.out.println("삭제할 번호");
				System.out.print("[입력]");
				int select = BufferUtil.nextInt();
				cartDao.removeCart(select, mem_id);
			}
			else
				return;
		}
	}
	
}
