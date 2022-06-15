package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.BufferUtil;
public class myPageDAO {
	
//------------------------------ 회원정보확인
	
	public static void infoView() {
	  
	      Connection conn = null; // 연결객체
	      ResultSet rs = null; // 결과를 보관할 객체
	      Statement stmt = null; // sql명령 객체
	      PreparedStatement pstmt = null; //동적 sql 명령객체
		
	      
	      try {
	         
	         conn = Connect.getConnection();
	         
	         System.out.println("회원님의 id를 입력하세요");
	         String getid = BufferUtil.readLine();
	         String sql = "select mem_id,mem_add1,mem_add2, mem_email, mem_bir, mem_mileage, mem_cash"
	        		 		+" from member"
	        		 		+" where mem_id ="+getid;
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql);
	         
	         while(rs.next()){
	        	 String id = rs.getString("mem_id");
	        	 String add1 = rs.getString("mem_add1");
	        	 String add2 = rs.getString("mem_add2");
	        	 String em = rs.getString("mem_email");
	        	 String bir = rs.getString("mem_bir");
	        	 int mil = rs.getInt("mem_mileage");
	        	 int cash= rs.getInt("mem_cash");
	        	 
	        	 System.out.println("===========================");
	        	 System.out.println("ID : "+id+"\n 생일 : "+bir+"\n 이메일 : "+em+"\n 주소1"+add1+"\n 주소2 : "+add2+"\n 마일리지 : "+mil+"\n 보유 캐시 : "+cash);
	        	 System.out.println("===========================");
	         }

	      	}catch(SQLException e){
	      		e.printStackTrace();
	      	}catch(Exception e){
	      		e.printStackTrace();
	      	}
	      	finally{
	      		 try{
	      	   		if(rs!=null){
	      	  			rs.close();
	      	  		}
	      	  		if(stmt!=null){
	      	  			stmt.close();
	      	  		}
	      	  		if(conn!=null){
	      	  			conn.close();
	      	  		}
	      	   	}catch(Exception e){}
	      	}
	}
	
//-------------------------------------- 회원탈퇴
	public void delMem(){
     
     Connection conn = null; // 연결객체
     ResultSet rs = null; // 결과를 보관할 객체
     Statement stmt = null; // sql명령 객체
     PreparedStatement pstmt = null; //동적 sql 명령객체
     
     try{
    	 conn = Connect.getConnection();
    	 
    	 System.out.println("회원님의 id를 입력해주세요");
    	 String getId = BufferUtil.readLine();
    	 
    	 
    	 String sql = "select mem_id, mem_bir"
    			 	+ " from member"
    			 	+ " where mem_id = "+getId;
    	 stmt = conn.createStatement();
    	 rs=stmt.executeQuery(sql);
    	 
    		while(rs.next()){
       		 String id=rs.getString("mem_id");
       		 String bir = rs.getString("mem_bir");
       		 
       		 
       		 System.out.println("회원님의 정보가 아래와 같습니까?");
     	 	 System.out.println(id+", "+bir);
       	 			}
    		System.out.println("1.예        2.아니오");
    		int input = BufferUtil.nextInt();
    		if(input == 1){
    			System.out.println("회원 탈퇴하시겠습니까?");
    			int delinput = BufferUtil.nextInt();
    				if(delinput == 1){
    					String delsql = "delete"
    									+"from member"
    									+"where mem_id="+getId;
    					stmt = conn.createStatement();
    					rs=stmt.executeQuery(sql);
    					}
    				if(delinput == 2){return;}
    	} if(input == 2){return;}
    	
     }catch(SQLException e){
         e.printStackTrace();
     }catch(Exception e){
    	 e.printStackTrace();
     }
   	 finally{
   		 try{
   		if(rs!=null){
  			rs.close();
  		}
  		if(stmt!=null){
  			stmt.close();
  		}
  		if(conn!=null){
  			conn.close();
  		}
   	}catch(Exception e){
   		
   	}
    }
}


//----------------------------비밀번호 변경
	public void pwChange(){
		 Connection conn = null; // 연결객체
	     ResultSet rs = null; // 결과를 보관할 객체
	     Statement stmt = null; // sql명령 객체
	     PreparedStatement pstmt = null; //동적 sql 명령객체
	     
	     try{
	    	 conn = Connect.getConnection();
	    	 
	    	 System.out.println("id를 입력하세요.");
	    	 String getid = BufferUtil.readLine();   //id를 입력받아서 인증
	    	 System.out.println("이름을 입력하세요.");
	    	 String getname = BufferUtil.readLine();   //이름도 인증
	    	 
	    	 String sql = "select mem_id, mem_name"
	    			 	+ " from member"
	    			 	+ " where mem_id = " +getid
	    			 	+ " and mem_name = "+getname;
	    	 
	    	 stmt = conn.createStatement();
	    	 rs=stmt.executeQuery(sql);
	    	 
	    	 while(rs.next()){
	    		 String id = rs.getString("mem_id");
	    		 String name = rs.getString("mem_name");
	    		 
	    		 System.out.println("비밀번호 변경합니다.\n변경 비밀번호 입력 :");
	    		 String pwCh = BufferUtil.readLine();
	    		 
	    		 String pwSqp = "update member"
	    				 	  + " set mem_pw ="+pwCh
	    				 	  + " where mem_id =" + getid
	    				 	  + " and mem_name =" + getname;
	    		 
	    			stmt = conn.createStatement();
					rs=stmt.executeQuery(sql);
	    	 }
	    	 System.out.println("비밀번호 변경 성공");
	    	 
	    	 
	     }catch(SQLException e){
	         e.printStackTrace();
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	   	 finally{
	   		 try{
	   		if(rs!=null){
	  			rs.close();
	  		}
	  		if(stmt!=null){
	  			stmt.close();
	  		}
	  		if(conn!=null){
	  			conn.close();
	  		}
	   	}catch(Exception e){
	   		
	   	}
	    }
	    	 
		
	}
	
	//포인트 충전
	public void cashCharge(){
		 Connection conn = null; // 연결객체
	     ResultSet rs = null; // 결과를 보관할 객체
	     Statement stmt = null; // sql명령 객체
	     PreparedStatement pstmt = null; //동적 sql 명령객체
	     
	     try{
	    	 conn = Connect.getConnection();
	    	 
	    	 System.out.println("회원님의 아이디를 입력해주세요");
	    	 String getid = BufferUtil.readLine();
	    	 
	    	 String sql = "select mem_name, mem_cash"
	    			 	+ " from meber"
	    			 	+ " where mem_id ="+getid;
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql);
	         
	         while(rs.next()){
	    	 String name = rs.getString("mem_name");
	    	 String id =rs.getString("mem_id");
	    	 int cash = rs.getInt("mem_cash");
	    	 System.out.println("현재 보유 캐쉬 :");
	    	 System.out.println("===========================");
	    	 System.out.println("현재 보유 캐쉬 :"+cash);
        	 System.out.println("===========================");
	         }
	         
	    	 
	    	 
	     }catch(SQLException e){
	         e.printStackTrace();
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	   	 finally{
	   		 try{
	   		if(rs!=null){
	  			rs.close();
	  		}
	  		if(stmt!=null){
	  			stmt.close();
	  		}
	  		if(conn!=null){
	  			conn.close();
	  		}
	   	}catch(Exception e){
	   		
	   	}
	    }
	}


}