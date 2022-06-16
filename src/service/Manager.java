package service;

import java.io.IOException;
import java.util.Scanner;

import dao.book.BookStockDAO;
import dao.login.ManagerLoginDAO;
import dao.manager.ManagerListDAO1;
import dao.manager.ManagerListDAO2;
import dao.manager.ManagerListDAO3;

public class Manager {
	
	public static void main(String[] args) {
		Manager manager = new Manager();

		manager.login();  // 관리자 페이지 접속하자마자 로그인 메소드 호출
		
	}
	
	public void login(){ //  로그인 메소드
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		ManagerLoginDAO managerLogin = new ManagerLoginDAO();
		boolean run = false;
		int index = 0;
		
		while(true){
			System.out.println("|--------------관리자 로그인---------------|");
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("PW : ");
			String pw = sc.next();
			
			managerLogin.ManagerLogin();
			if (id.equals(managerLogin.getEmpID()) && pw.equals(managerLogin.getEmpPW())){
					System.out.println("로그인에 성공하였습니다.");
					System.out.println("|----------------------------------------------|");
					System.out.println();
					manager.exe();
					break;
			} else{
				System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
				System.out.println("|----------------------------------------------|");
				System.out.println();
				index++;

				if (index == 5){
					System.out.println("로그인을 5회 실패하였습니다.");
					System.out.println();
					System.out.println();
					try {
						Main.main(null); // 로그인 5회 실패하면 메인 페이지로 넘어감
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				}
			}
		}
	}
	
	public void exe(){ // 실행 메소드
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		boolean run = false; // while문 안쓸거면 지우기
		
		System.out.println("================================================");
		System.out.println("| 1. 책 입고  |  2. 회원 목록 조회 |  3. 도서 목록 조회  |  4. 로그아웃  |");
		System.out.println("================================================");
		System.out.print("사용할 기능 입력 : ");
		int num = sc.nextInt();
		
		switch(num){
		case 1:
			manager.bookStock();
		case 2:
			manager.memberList();
		case 3:
			manager.bookList();
			case 4:
			manager.logout();
		}
	}
	
	public void bookStock(){  // 책 입고 메소드
		BookStockDAO stock = new BookStockDAO();
		stock.insertBook();
	}
	
	public void memberList(){ // 회원 목록 조회 메소드
		ManagerListDAO1 memList = new ManagerListDAO1();
		memList.selectMember();
	}
	
	public void bookList(){ // 도서 목록 조회 메소드
		Manager manager = new Manager();
		Scanner sc = new Scanner(System.in);
		ManagerListDAO2 bookList = new ManagerListDAO2();
		
		bookList.bookSelect();
		System.out.println("================d======================");
		System.out.println("| 1. 품절 품목 추가 입고    |   2. 뒤로 가기                |");
		System.out.println("======================================");
		int num = sc.nextInt();
		
		if (num == 1){
			ManagerListDAO3 bookList2 = new ManagerListDAO3();
			bookList2.checkStock();
		}else{
			manager.exe(); // 로그인 한 이후의 관리페이지로 이동
		}
		
	}
	
	public void logout(){
		System.out.println("로그아웃 되었습니다");
		try {
			Main.main(null); // 로그아웃 시 메인 화면으로 이동
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
