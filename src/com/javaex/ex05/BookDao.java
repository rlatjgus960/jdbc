package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	//필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	//생성자
	
	
	//메소드-gs
	
	
	//메소드-일반
	
	
	//DB연결
	public void getConnection() {
		
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
		    Class.forName(driver);
		    // 2. Connection 얻어오기
		    conn = DriverManager.getConnection(url, id, pw);
		    

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	
	//자원정리
	public void close() {
		//5. 자원정리
		try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }

	}
	
	
	
	/*
	//책 테이블 생성
	public void bookTable() {
		
		int count = -1;
		
		this.getConnection();
		
		try {
		   
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";  
	        query += " create table book( "; 
	        query += "    book_id number(10), ";
	        query += "    title varchar2(100) not null, ";
	        query += "    pubs varchar2(100), ";
	        query += "    pub_date date, ";
	        query += "    author_id number(10), ";
	        query += "    primary key(book_id), ";
	        query += "    constraint book_fk foreign key (author_id) ";
	        query += "    references author(author_id)";
	        query += "	  on delete cascade ";
	        query += " ) ";
	         
		    pstmt = conn.prepareStatement(query); 
		    		         
		    count = pstmt.executeUpdate(); 
		        
	        // 4.결과처리
		    System.out.println("책 테이블 생성 완료");

		} catch (SQLException e) {
		    e.printStackTrace();
		} 
		
		this.close();

	}
	
	//책 시퀀스 생성
	public void bookSeq() {
		
		int count = -1;
		
		this.getConnection();
		
		try {
		   
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";  
	        query += " create sequence seq_book_id "; 
	        query += " increment by 1 ";
	        query += " start with 1 ";
	        query += " NOCACHE ";
	         
		    pstmt = conn.prepareStatement(query); 
		    		         
		    count = pstmt.executeUpdate(); 
		        
	        // 4.결과처리
		    System.out.println("책 시퀀스 생성 완료");

		} catch (SQLException e) {
		    e.printStackTrace();
		} 
		
		this.close();

	}
	*/
		
		
	
	
	
	//책 삭제하기
	public int bookDelete(int bookId) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
		   
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";  
	        query += " delete from book "; 
	        query += " where book_id = ? ";
	         
		    pstmt = conn.prepareStatement(query); 
		    pstmt.setInt(1,bookId);
		         
		    count = pstmt.executeUpdate(); 
		        
	        // 4.결과처리
		    System.out.println(count +"건 삭제");

		} catch (SQLException e) {
		    e.printStackTrace();
		} 
		
		this.close();

		
		return count;
	}
	
	
	
	//책 수정하기
	public int bookUpdate(BookVo bookVo) {
		
		int count = -1;

		this.getConnection();

		try {
		   			
		    // 3. SQL문 준비 / 바인딩 / 실행
	         String query = "";
	         query += " update book ";
	         query += " set title = ?, ";
	         query += "       pubs = ?, ";
	         query += "       pub_date = ?, ";
	         query += "       author_id = ? ";
	         query += " where book_id = ? ";
	         
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, bookVo.getTitle());
	         pstmt.setString(2, bookVo.getPubs());
	         pstmt.setString(3, bookVo.getPubDate());
	         pstmt.setInt(4,bookVo.getAuthorId());
	         pstmt.setInt(5,bookVo.getBookId());
	         
	         count = pstmt.executeUpdate();
	         
	         // 4.결과처리
	         System.out.println(count + "건 수정");

		} catch (SQLException e) {
		    System.out.println("error:" + e);

		}
		
		this.close();
		
		return count;
	}

	//책 등록하기
	public int bookInsert(BookVo bookVo) {
		
		int count = -1; 
		
		this.getConnection();
		
		try {
		    
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
	        query += " insert into book ";
	        query += " values(seq_book_id.nextval, ?, ?, ?, ?) ";
	         
		    pstmt = conn.prepareStatement(query);  
		    pstmt.setString(1,bookVo.getTitle());	
		    pstmt.setString(2,bookVo.getPubs());
		    pstmt.setString(3,bookVo.getPubDate());
		    pstmt.setInt(4,bookVo.getAuthorId());
		         
		    count = pstmt.executeUpdate();
		    // 4.결과처리
		    System.out.println("책 " + count + "건 등록");

		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		this.close();
		
		return count; 
	}
	
	
	
	//////////////////////////////////////////////////////////////
	
	
	
	//책 리스트 가져오기
	public List<BookVo> getBookList() {
		
		//리스트 생성
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		this.getConnection();

		try {
		  
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " select  bo.book_id, ";
		    query += "         bo.title, ";
	        query += "         bo.pubs, ";
	        query += "         to_char(bo.pub_date, 'YYYY-MM-DD') pubDate, ";
	        query += "         bo.author_id, ";
	        query += "         au.author_name, ";
	        query += "         au.author_desc ";
		    query += " from book bo, author au ";
		    query += " where bo.author_id = au.author_id ";
		    query += " order by book_id asc ";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pubDate");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
			
				bookList.add(bookVo);
			}
		    
		    

		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		this.close();
		
		return bookList;
		
	}
	
	
	
	
	//책 검색
	public List<BookVo> getBookList(String search) {
		
		//리스트 생성
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		this.getConnection();

		try {
		  
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " select  bo.book_id, ";
		    query += "         bo.title, ";
	        query += "         bo.pubs, ";
	        query += "         to_char(bo.pub_date, 'YYYY-MM-DD') pubDate, ";
	        query += "         bo.author_id, ";
	        query += "         au.author_name, ";
	        query += "         au.author_desc ";
		    query += " from book bo, author au ";
		    query += " where bo.author_id = au.author_id ";
		    query += " and (bo.title || bo.pubs || au.author_name) like " + "'%" + search + "%' ";
		    query += " order by book_id asc ";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pubDate");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
			
				bookList.add(bookVo);
			}
		    
		    

		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		this.close();
		
		return bookList;
		
	}
}