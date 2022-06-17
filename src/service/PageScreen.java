package service;

import java.util.ArrayList;

import dao.mypage.Member;
import dao.mypage.MyPageDAO;
import util.BufferUtil;

public class PageScreen {
	MyPageDAO mp = new MyPageDAO(); 
 public void pagescreen(String mem_id){
	 
	 boolean b=true;
    try {
    	while(b){
    	   System.out.print("\n\n\n");
           System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
           System.out.printf("│%-97s│\n","");
           System.out.printf("│%15s %15s %15s %15s %15s %9s \n","1.기본정보조회","2.비밀번호변경","3.포인트충전","4.회원탈퇴","5.도서환불","│");
           System.out.printf("│%-97s│\n","");
           System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────────────┘");
           System.out.printf("%17s %15s %15s %15s %15s\n","1.기본정보조회","2.비밀번호변경","3.포인트충전","4.회원탈퇴","5.마이페이지");
           System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
 
    	    System.out.print("\t\t\t   원하시는 메뉴번호를 입력하세요 ☞ ");
    	    
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
    	    	ArrayList refundList = m.viewPurchasedBook(mem_id);
    	    	for(int i=0;i<refundList.size();i++) {
    	    		System.out.println(refundList.get(i).toString());
    	    	}
    	    	break;
    	    	}
    	
    		}
    	}catch(Exception e){
            e.printStackTrace();
            System.out.println("번호를 잘못 누르셨습니다.");
    	}
    	
    
    
 }
}
