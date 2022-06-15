package luna;
import java.io.IOException;

public class main {

   public static void main(String[] args) throws IOException {

      System.out.println("\t\t\t\t[루나문고]");
      while (true) {
         System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");
         System.out.println("|\t\t\t\t\t\t\t\t\t|");
         System.out.println("|\t 1.회원가입 \t 2.로그인 \t 3.관리자 \t 4.종료     \t|");
         System.out.println("|\t\t\t\t\t\t\t\t\t|");
         System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");

         System.out.print("[입력] ");

         int input = BufferUtil.nextInt();
         
         switch (input) {
            case 1: {
               // 회원가입 클래스 호출
            }
            case 2: {
               // 로그인 클래스 호출
               // 메인 메뉴 추가 ( 메인메뉴(메소드) , 1. 도서 구매, 2. 도서 환불, 3. 마이페이지 ) 
            }
            case 3: {
               // 관리자 클래스 호출
               // 도서 관리 ( 도서입고, 도서출고 )
            }
            default: {
               System.exit(0);
            }
         }
      }
   }

}