package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.booklist.BookList;
import util.BufferUtil;
import vo.BookVO;

public class MainMenu {
   
   public void screen2(int cartNumber) {
      
      boolean run = true;
      
      try {
         
         while(run) {
         System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");
           System.out.println("|\t\t\t\t\t\t\t\t\t|");
           System.out.println("|\t 1.도서목록 \t 2.장바구니 \t 3.마이페이지 \t 4.로그아웃     \t|");
           System.out.println("|\t\t\t\t\t\t\t\t\t|");
           System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");

           System.out.print("[입력] ");

           int input = BufferUtil.nextInt();
           
           switch (input) {
           
            case 1: {
            	System.out.println("[전체 도서목록]");
            	BookListService bookservice = new BookListService();
            	bookservice.viewBookList();
            	break;
            }
            case 2: {
               CartService cart = new CartService();
               cart.cartView(cartNumber);
               break;
            }
            case 3: {
               System.out.println("마이페이지 클래스 호출");
               // 마이페이지 클래스 호출
               break;
            }
            case 4: {
               run = false;
               break;
            }
           }
         }
           
      }catch(IOException e){
         e.printStackTrace();
         System.out.println("[INPUT FAILURE]");
      }
   }
}