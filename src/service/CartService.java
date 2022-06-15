package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.cart.CartDAO;
import util.BufferUtil;
import vo.CartVO;

public class CartService {

	CartDAO cartDao = new CartDAO();
	
	void cartView(int cartNumber) throws IOException {
		
		ArrayList<CartVO> list = cartDao.selectCart(cartNumber);
		
		System.out.println("---------------------------------------------------------");
		System.out.println("[1. 구매      2. 상품삭제     other. 뒤로가기]");
		System.out.print("[입력]");
		int input = BufferUtil.nextInt();
		while(true) {
			if(input == 1) {
				cartDao.paymentCartList(list);
			}
			else if(input ==2) {
				System.out.println("삭제할 번호");
				System.out.print("[입력]");
				int select = BufferUtil.nextInt();
				cartDao.removeCart(select);
			}
			else
				return;
		}
	}
	
}
