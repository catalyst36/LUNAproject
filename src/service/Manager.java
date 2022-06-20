package service;

import java.io.IOException;

import dao.book.BookStockDAO;
import dao.login.ManagerLoginDAO;
import dao.manager.ManagerListDAO1;
import dao.manager.ManagerListDAO2;
import dao.manager.ManagerListDAO3;
import util.BufferUtil;
import util.PrintUtil;
import vo.EmployeeVO;

public class Manager {
	
	public void login() throws Exception{ //  로그인 메소드
		Manager manager = new Manager();
		ManagerLoginDAO managerLogin = new ManagerLoginDAO();
		boolean run = false;
		int index = 0;
		
		while(true){
			System.out.printf("\n\n\n\n\n\n");
		    System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.printf("%20s", "ID : ");
			String id = BufferUtil.readLine();
			System.out.printf("%20s","PW : ");	          
			String pw = BufferUtil.readLine();

			System.out.println();
			
			EmployeeVO vo = managerLogin.empLogin(id, pw);
			if (vo != null){
			    System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("%52s\n", "로그인에 성공하였습니다.");
			          System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

					System.out.println();
					PrintUtil.print();
					manager.exe();
					break;
			} else{
			    System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.printf("%52s\n","아이디 혹은 비밀번호가 틀렸습니다.");
		          System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				index++;

				if (index == 5){
				    System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("%52s\n","※로그인을 5회 실패하였습니다. 메인으로 돌아갑니다.※");
			          System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
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
	
	public void exe() throws Exception{ // 실행 메소드
		Manager manager = new Manager();
		boolean run = false; // while문 안쓸거면 지우기
		
		System.out.printf("\n\n\n\n\n\n");
        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%20s %18s %18s %18s\n","1. 책 입고","2. 회원 목록 조회","3. 도서 목록 조회","4. 로그아웃");
                System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

		System.out.printf("%55s","사용할 기능을 입력하세요 ☞  ");
		int num = BufferUtil.nextInt();
		
		switch(num){
		case 1:
			PrintUtil.print();
			manager.bookStock();
			break;
		case 2:
			PrintUtil.print();
			manager.memberList();
			break;
		case 3:
			PrintUtil.print();
			manager.bookList();
			break;
			case 4:
			PrintUtil.print();
			manager.logout();
			break;
		}
	}
	
	public void bookStock() throws Exception{  // 책 입고 메소드
		BookStockDAO stock = new BookStockDAO();
		PrintUtil.print();
		stock.insertBook();
	}
	
	public void memberList() throws Exception{ // 회원 목록 조회 메소드
		ManagerListDAO1 memList = new ManagerListDAO1();
		PrintUtil.print();
		memList.selectMember();
	}
	
	public void bookList() throws Exception{ // 도서 목록 조회 메소드
		Manager manager = new Manager();
		ManagerListDAO2 bookList = new ManagerListDAO2();
		PrintUtil.print();
		bookList.bookSelect();
		System.out.printf("\n\n\n\n\n\n");
        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("%20s %18s \n", "1. 품절 품목 추가 입고","2. 뒤로 가기");
        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

		System.out.printf("%55s","사용할 기능을 입력하세요 ☞  ");
		int num = BufferUtil.nextInt();
		
		if (num == 1){
			ManagerListDAO3 bookList2 = new ManagerListDAO3();
			PrintUtil.print();
			bookList2.checkStock();
		}else{
			PrintUtil.print();
			manager.exe(); // 로그인 한 이후의 관리페이지로 이동
		}
		
	}
	
	public void logout() throws Exception{
		System.out.printf("\n\n\n\n\n\n");
        System.out.printf("┌%97s┐\n","─────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.printf("%52s\n","로그아웃 되었습니다");
        System.out.printf("└%97s┘\n","─────────────────────────────────────────────────────────────────────────────────────────────────");

		try {
			Main.main(null); // 로그아웃 시 메인 화면으로 이동
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
