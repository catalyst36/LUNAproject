package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.CartDAO;
import util.BufferUtil;
import vo.CartVO;

public class CartService {

	CartDAO cartDao = new CartDAO();
	
	void cartView() throws IOException {
		
		System.out.println("---------------------------------------------------------");
		System.out.println("[1. 구매      2. 상품삭제     3. 뒤로가기]");
		System.out.print("[입력]");
		int input = BufferUtil.nextInt();
		while(true) {
			if(input == 1) {
				return; //카트 구입 메소드 호출
			}
			else if(input ==2) {
				System.out.println("삭제할 번호");
				System.out.print("[입력]");
				int select = BufferUtil.nextInt();
				cartDao.removeCart(select);
			}
			else
				return;
				System.out.println("입력 오류!! 다시 입력해주세요.");
		}
	}
	
}
