package dao.booklist;


import java.io.IOException;
import java.util.ArrayList;

import util.BufferUtil;
import vo.BookVO;

public class BookList {

	public void getWholeBookList(ArrayList<BookVO> list) {
		
		for(int i = 0; i<list.size(); i++) {
			StringBuilder res = new StringBuilder();
			
			res.append("[번호] " + list.get(i).getBook_num());
			res.append(" [코드] " + list.get(i).getBook_id());
			res.append(" [제목] " + list.get(i).getBook_name());
			res.append(" [가격] " + list.get(i).getBook_sale());
			res.append(" [저자] " + list.get(i).getBook_author());
			res.append(" [출판사] " + list.get(i).getBook_pub());
			res.append(" [수량] " + list.get(i).getBook_qty());
			res.append(" [장르] " + list.get(i).getBook_genre());
			res.append(" [누적 판매수] " + list.get(i).getBook_qtysale());
			System.out.println(res.toString());
		}
	}
	
	public void searchBookName(ArrayList<BookVO> list) throws IOException{
		System.out.println("[도서 제목을 입력하세요]");
		System.out.print("<입력> : ");
		String book_name = BufferUtil.readLine();
		int missNameCount = 0;
		for(int i = 0; i<list.size(); i++) {
			if(book_name.equals(list.get(i).getBook_name())) {
				StringBuilder res = new StringBuilder();
				res.append("[번호] " + list.get(i).getBook_num());
				res.append(" [코드] " + list.get(i).getBook_id());
				res.append(" [제목] " + list.get(i).getBook_name());
				res.append(" [가격] " + list.get(i).getBook_sale());
				res.append(" [저자] " + list.get(i).getBook_author());
				res.append(" [출판사] " + list.get(i).getBook_pub());
				res.append(" [수량] " + list.get(i).getBook_qty());
				res.append(" [장르] " + list.get(i).getBook_genre());
				res.append(" [누적 판매수] " + list.get(i).getBook_qtysale());
				System.out.println(res.toString());
			}else {
				missNameCount++;
			}
		}
		if(missNameCount==list.size()) System.out.println("[찾으시는 도서가 없습니다]");
	}
	
	public void searchBookGenre(ArrayList<BookVO> list) throws IOException{
		System.out.println("[장르를 입력하세요]");
		System.out.print("<입력> : ");
		String book_genre = BufferUtil.readLine();
		int missGenreCount = 0;
		for(int i = 0; i<list.size(); i++) {
			if(book_genre.equals(list.get(i).getBook_genre())) {
				StringBuilder res = new StringBuilder();
				res.append("[번호] " + list.get(i).getBook_num());
				res.append(" [코드] " + list.get(i).getBook_id());
				res.append(" [제목] " + list.get(i).getBook_name());
				res.append(" [가격] " + list.get(i).getBook_sale());
				res.append(" [저자] " + list.get(i).getBook_author());
				res.append(" [출판사] " + list.get(i).getBook_pub());
				res.append(" [수량] " + list.get(i).getBook_qty());
				res.append(" [장르] " + list.get(i).getBook_genre());
				res.append(" [누적 판매수] " + list.get(i).getBook_qtysale());
				System.out.println(res.toString());
			}else {
				missGenreCount++;
			}
		}
		if(missGenreCount==list.size()) System.out.println("[찾으시는 도서가 없습니다]");
	}
	
	public void searchBookAuthor(ArrayList<BookVO> list) throws IOException{
		System.out.println("[저자를 입력하세요]");
		System.out.print("<입력> : ");
		String book_author = BufferUtil.readLine();
		int missAuthorCount = 0;
		for(int i = 0; i<list.size(); i++) {
			if(book_author.equals(list.get(i).getBook_author())) {
				StringBuilder res = new StringBuilder();
				res.append("[번호] " + list.get(i).getBook_num());
				res.append(" [코드] " + list.get(i).getBook_id());
				res.append(" [제목] " + list.get(i).getBook_name());
				res.append(" [가격] " + list.get(i).getBook_sale());
				res.append(" [저자] " + list.get(i).getBook_author());
				res.append(" [출판사] " + list.get(i).getBook_pub());
				res.append(" [수량] " + list.get(i).getBook_qty());
				res.append(" [장르] " + list.get(i).getBook_genre());
				res.append(" [누적 판매수] " + list.get(i).getBook_qtysale());
				System.out.println(res.toString());
			}else {
				missAuthorCount++;
			}
		}
		if(missAuthorCount==list.size()) System.out.println("[찾으시는 도서가 없습니다]");
	}
	
}