package service;

import java.io.IOException;

import dao.login.LoginDAO;
import dao.login.SignUp;
import util.BufferUtil;

public class Main {

	public static void main(String[] args) throws Exception {

		while (true) {
			System.out.printf("\n\n\n\n\n\n");

           System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
           System.out.printf("%20s %18s %18s %18s\n","1.회원가입","2.로그인","3.관리자","4.종료");
           System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

		

           System.out.printf("%55s","원하시는 메뉴번호를 입력하세요 ☞ ");

			int input = 0;

			input = BufferUtil.nextInt();
			switch (input) {
			case 1: {
				SignUp signup = new SignUp();
				signup.insertMember();
				break;
			}
			case 2: {
				LoginDAO login = new LoginDAO();
				login.memberLogin();
				break;
			}
			case 3: {
				Manager manager = new Manager();
				manager.login();
				break;
			}
			default: {
				System.exit(0);
			}
			}
		}
	}

}
