package service;

import dao.mypage.MyPageDAO;
import util.BufferUtil;

public class PageScreen {
	MyPageDAO mp = new MyPageDAO(); 
 public void pagescreen(){
	 
	 boolean b=true;
    try {
    	while(b){
    		     System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-----------");
    	         System.out.println("|\t\t\t\t\t\t\t\t\t|");
    	         System.out.println("|\t 1.기본정보조회       2.비밀번호 변경     3.포인트 충전       4.회원탈퇴        5.마이페이지 나가기  \t|");
    	         System.out.println("|\t\t\t\t\t\t\t\t\t|");
    	         System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-----------");
 
    	    System.out.println("[입력] ");
    	    
    	    int input = BufferUtil.nextInt();
    	    
    	    switch(input) {
    	    case 1:
    	    	System.out.println("회원님의 기본정보 입니다.");
    	    	mp.infoView();
    	    	break;
    	    case 2:
    	    	System.out.println("비번변경");
    	    	mp.pwChange();
    	    	break;
    	    case 3:
    	    	System.out.println("포인트 충전");
    	    	//mp.pCharge();
    	    	break;
    	    case 4:
    	    	System.out.println("회원탈퇴");
    	    	mp.delMem();
    	    	break;
    	    case 5:
    	    	b=false;
    	    	break;
    	    	}
    		}
    	}catch(Exception e){
            e.printStackTrace();
            System.out.println("번호를 잘못 누르셨습니다.");
    	}
 }
}
