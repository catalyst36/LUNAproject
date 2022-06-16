package util;

import java.util.ArrayList;

import vo.BookVO;

public class SEQ {

		public static String createSequenceKey(String bookID){
				String r = "";
				r= bookID; // BOOK_ID (VARCHAR2)를 문자열 변수 r에 저장
				int header = Integer.valueOf(r.substring(0, 2)); // 문자열 앞부분 2개를 따와 숫자형 변수 header에 저장
				char result = (char) header; // header 값을 ASCII로 변환
				
				r = result + r.substring(2); // 
				
				return r;
		}
		
		public static int returnSequenceKey(String bookID){
			int r = 0;
			char id = bookID.charAt(0); // BOOK_ID의 문자 부분(A,B,C)를 따와 id 변수에 저장
			String intStr = bookID.replaceAll("[^0-9]", ""); // BOOK_ID의 숫자 부분을 intStr 문자열에 저장
			int num = Integer.parseInt(intStr); // intStr 문자열을 숫자형으로 타입 변환
			r = (int) id * 10000 + num; 
					return r;
		}
		
		public static int returnSequenceKeyList(BookVO bookID){
			int r = 0;
			char id = bookID.getBook_id().charAt(0); // BOOK_ID의 문자 부분(A,B,C)를 따와 id 변수에 저장
			String intStr = bookID.getBook_id().replaceAll("[^0-9]", ""); // BOOK_ID의 숫자 부분을 intStr 문자열에 저장
			int num = Integer.parseInt(intStr); // intStr 문자열을 숫자형으로 타입 변환
			r = (int) id * 10000 + num; 
					return r;
		}

}
