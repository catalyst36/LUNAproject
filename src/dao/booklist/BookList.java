package dao.booklist;


import java.io.IOException;
import java.util.ArrayList;

import util.BufferUtil;
import vo.BookVO;

public class BookList {

	public void getWholeBookList(ArrayList<BookVO> list) {
		
		for(int i = 0; i<145; i++) {
		System.out.print("-");
		}
		
		System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |\n"
								, "번호", "코드", "제목", "가격", "저자", "출판사", "수량", "장르", "누적판매수");

		for(int i = 0; i<145; i++) {
		System.out.print("-");
		}
			
		for(int i = 0; i<list.size(); i++) {
		System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |"
				, String.valueOf(list.get(i).getBook_num()),  list.get(i).getBook_id(), list.get(i).getBook_name()
				,  String.valueOf(list.get(i).getBook_sale()),  list.get(i).getBook_author(),  list.get(i).getBook_pub()
				,  String.valueOf(list.get(i).getBook_qty()),  list.get(i).getBook_genre(),  String.valueOf(list.get(i).getBook_qtysale()));
		
		}
		System.out.println();
		for(int i = 0; i<145; i++) {
		System.out.print("-");
		}
		System.out.println();
	}
	
	public void searchBookName(ArrayList<BookVO> list) throws IOException{
		System.out.println("[도서 제목을 입력하세요]");
		System.out.print("<입력> : ");
		String book_name = BufferUtil.readLine();
		int missNameCount = 0;
		for(int i = 0; i<145; i++) {
			System.out.print("-");
			}
			
			System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |\n"
									, "번호", "코드", "제목", "가격", "저자", "출판사", "수량", "장르", "누적판매수");
			
			for(int i = 0; i<145; i++) {
			System.out.print("-");
			}
			
			for(int j = 0; j<list.size(); j++) {
				if(book_name.equals(list.get(j).getBook_name())) {
					
					  System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |"
					, String.valueOf(list.get(j).getBook_num()),  list.get(j).getBook_id(),  list.get(j).getBook_name()
					, String.valueOf(list.get(j).getBook_sale()),  list.get(j).getBook_author(),  list.get(j).getBook_pub()
					, String.valueOf(list.get(j).getBook_qty()),  list.get(j).getBook_genre(),  String.valueOf(list.get(j).getBook_qtysale()));
					
				}else {
					missNameCount++;
				}
			}
			System.out.println();
			for(int i = 0; i<145; i++) {
			System.out.print("-");
			}
			System.out.println();
			if(missNameCount==list.size()) System.out.println("[찾으시는 도서가 없습니다]");
	}
	
	public void searchBookGenre(ArrayList<BookVO> list) throws IOException{
		System.out.println("[장르를 입력하세요]");
		System.out.print("<입력> : ");
		String book_genre = BufferUtil.readLine();
		int missGenreCount = 0;
		
		for(int i = 0; i<145; i++) {
			System.out.print("-");
			}
			
			System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |\n"
									, "번호", "코드", "제목", "가격", "저자", "출판사", "수량", "장르", "누적판매수");
			
			for(int i = 0; i<145; i++) {
			System.out.print("-");
			}
			
			for(int j = 0; j<list.size(); j++) {
				if(book_genre.equals(list.get(j).getBook_genre())) {
					
					  System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |"
					, String.valueOf(list.get(j).getBook_num()),  list.get(j).getBook_id(),  list.get(j).getBook_name()
					, String.valueOf(list.get(j).getBook_sale()),  list.get(j).getBook_author(),  list.get(j).getBook_pub()
					, String.valueOf(list.get(j).getBook_qty()),  list.get(j).getBook_genre(),  String.valueOf(list.get(j).getBook_qtysale()));
					
				}else {
					missGenreCount++;
				}
			}
			System.out.println();
			for(int i = 0; i<145; i++) {
			System.out.print("-");
			}
			System.out.println();
			if(missGenreCount==list.size()) System.out.println("[찾으시는 도서가 없습니다]");
	}
	
	public void searchBookAuthor(ArrayList<BookVO> list) throws IOException{
		System.out.println("[저자를 입력하세요]");
		System.out.print("<입력> : ");
		String book_author = BufferUtil.readLine();
		int missAuthorCount = 0;
		
		for(int i = 0; i<145; i++) {
		System.out.print("-");
		}
		
		System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |\n"
								, "번호", "코드", "제목", "가격", "저자", "출판사", "수량", "장르", "누적판매수");
		
		for(int i = 0; i<145; i++) {
		System.out.print("-");
		}
		
		for(int j = 0; j<list.size(); j++) {
			if(book_author.equals(list.get(j).getBook_author())) {
				
				  System.out.printf("\n| %-2s | %-5s | %-50s | %-5s | %-15s | %-15s | %-3s | %-9s | %-6s |"
				, String.valueOf(list.get(j).getBook_num()),  list.get(j).getBook_id(),  list.get(j).getBook_name()
				, String.valueOf(list.get(j).getBook_sale()),  list.get(j).getBook_author(),  list.get(j).getBook_pub()
				, String.valueOf(list.get(j).getBook_qty()),  list.get(j).getBook_genre(),  String.valueOf(list.get(j).getBook_qtysale()));
				
			}else {
				missAuthorCount++;
			}
		}
		System.out.println();
		for(int i = 0; i<145; i++) {
		System.out.print("-");
		}
		System.out.println();
		if(missAuthorCount==list.size()) System.out.println("[찾으시는 도서가 없습니다]");
	}
	
}