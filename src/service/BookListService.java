package service;

import java.util.ArrayList;

import dao.booklist.BookList;
import vo.BookVO;

public class BookListService {
	
	public void viewBookList() {
		
		BookList booklist = new BookList();
		ArrayList<BookVO> list = booklist.getWholeList();
		
	}
	
}
