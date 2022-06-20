package service;

import java.util.ArrayList;

import dao.mypage.Member;
import dao.mypage.MyPageDAO;
import dao.mypage.RefundData;
import util.BufferUtil;

public class PageScreen {
	MyPageDAO mp = new MyPageDAO(); 
 public void pagescreen(String mem_id){
	 
	 boolean b=true;
    try {
    	while(b){
			System.out.printf("\n\n\n\n\n\n");

           System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
           System.out.printf("%10s %10s %10s %10s %10s %9s %9s\n","1.기본정보조회","2.비밀번호변경","3.포인트충전","4.회원탈퇴","5.도서환불","6.뒤로가기");
           System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────"); 
           System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");
    	    
    	    int input = BufferUtil.nextInt();
    	   
    	    
    	    switch(input) {
    	    case 1:
    	    	mp.infoView();
    	    	break;
    	    case 2:
    	    	
    	    	mp.pwChange();
    	    	break;
    	    case 3:

    	    	mp.cashCharge();
    	    	break;
    	    case 4:
    	    	mp.delMem();
    	    	break;
    	    case 5:
    	    	Member m = new Member();
    	    	ArrayList<RefundData> refundList = m.viewPurchasedBook(mem_id);
    	    	
    	    	System.out.printf("%3s %6s %45s %15s %7s %3s %10s %10s\n","번호","도서코드","도서제목","저자","가격","수량","아이디","날짜");
    	    	for(int i=0;i<122;i++) {
    	    		System.out.printf("-");
    	    	}
    	    	System.out.println();
    	    	for(int i=0;i<refundList.size();i++) {
    	    		System.out.printf("|%3d| %6s| %45s| %15s| %7d| %3d| %10s| %10s|\n", refundList.get(i).getNum(), refundList.get(i).getRefund_bookId()
    	    				, refundList.get(i).getRefund_bookName(), refundList.get(i).getRefund_author(), refundList.get(i).getRefund_sale()
    	    				, refundList.get(i).getRefund_qty(),refundList.get(i).getRefund_mem(), refundList.get(i).getRefund_date());
    	    	}
    	    	System.out.print("환불할 번호를 입력하세요 : ");
    	    	int listNum = BufferUtil.nextInt() - 1;
    	    	int result = m.refund(refundList.get(listNum));
    	    	if(result == 0) {
    	    		System.out.println("환불 실패!");
    	    	}else {
    	    		System.out.println("환불 성공!");
    	    	}
    	    case 6 :
    	    	return;
    	    	}
    	
    		}
    	}catch(Exception e){
            e.printStackTrace();
            System.out.println("번호를 잘못 누르셨습니다.");
    	}
    	
    
    
 }
}
