package service;

import java.io.IOException;

import dao.login.LoginDAO;
import dao.login.SignUp;
import util.BufferUtil;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("\t\t\t\t[루나문고]");
		while (true) {
			System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");
			System.out.println("|\t\t\t\t\t\t\t\t\t|");
			System.out.println("|\t 1.회원가입 \t 2.로그인 \t 3.관리자 \t 4.종료     \t|");
			System.out.println("|\t\t\t\t\t\t\t\t\t|");
			System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ");

			System.out.print("[입력] ");

			int input = 0;

			while (true) {
				input = BufferUtil.nextInt();
				switch (input) {
				case 1: {
					SignUp signup = new SignUp();
					signup.insertMember();
				}
				case 2: {
					LoginDAO login = new LoginDAO();
					login.memberLogin();
				}
				case 3: {
					Manager manager = new Manager();
					manager.login();
				}
				default: {
					System.exit(0);
				}
				}
			}
		}
	}

}
