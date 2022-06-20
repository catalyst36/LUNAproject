package service;

import java.io.IOException;

import dao.login.LoginDAO;
import dao.login.SignUp;
import util.BufferUtil;
import util.PrintUtil;

public class Main {

	public static void main(String[] args) throws Exception {

		while (true) {
<<<<<<< HEAD
		   PrintUtil.print();
		   System.out.printf("%s\n","[루나문고]");
=======

>>>>>>> branch 'develop' of https://github.com/catalyst36/LUNAproject.git
           System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
           System.out.printf("%20s %18s %18s %18s\n","1.회원가입","2.로그인","3.관리자","4.종료");
           System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

		

           System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");

			int input = 0;

			input = BufferUtil.nextInt();
			switch (input) {
			case 1: {
				SignUp signup = new SignUp();
				PrintUtil.print();
				signup.insertMember();
				break;
			}
			case 2: {
				LoginDAO login = new LoginDAO();
				PrintUtil.print();
				login.memberLogin();
				break;
			}
			case 3: {
				Manager manager = new Manager();
				PrintUtil.print();
				manager.login();
				break;
			}
			default: {
				System.out.println("종료되었습니다.");
				System.exit(0);
			}
			}
		}
	}

}
