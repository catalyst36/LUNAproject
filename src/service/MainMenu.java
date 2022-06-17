package service;

import java.io.IOException;
import java.util.ArrayList;
import dao.booklist.CreateBook;
import vo.BookVO;
import util.BufferUtil;

public class MainMenu {
   
   public void screen2(int cartNumber, String mem_id) {
      
      boolean run = true;
      
      try {
         
         while(run) {
        	 
             System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
             System.out.printf("│%-97s│\n","");
             System.out.printf("│%20s %18s %18s %18s %14s\n","1.도서목록","2.장바구니","3.마이페이지","4.로그아웃","│");
             System.out.printf("│%-97s│\n","");
             System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

             System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");

           int input = BufferUtil.nextInt();
           
           switch (input) {
           
            case 1: {
            	CreateBook booklist = new CreateBook();
            	ArrayList<BookVO> list = booklist.createInstance();
            	BookListService bookservice = new BookListService();
            	bookservice.viewBookList(cartNumber, mem_id, list);
            	break;
            }
            case 2: {
               CartService cart = new CartService();
               cart.cartView(cartNumber, mem_id);
               break;
            }
            case 3: {
               PageScreen page = new PageScreen();
               page.pagescreen(mem_id);
               break;
            }
            case 4: {
            	return;
            }
           }
         }
           
      }catch(IOException e){
         e.printStackTrace();
         System.out.println("[INPUT FAILURE]");
      }
   }
}