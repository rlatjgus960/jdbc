package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsertApp {

	public static void main(String[] args) {
	      // TODO Auto-generated method stub
	      // 0. import java.sql.*;
	      Connection conn = null; //커넥션
	      PreparedStatement pstmt = null;  //걍 외워라 . 쿼리문 만든거
	      //ResultSet rs = null; //결과값 받아올때 쓰는거

	      try {
	          // 1. JDBC 드라이버 (Oracle) 로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	          // 2. Connection 얻어오기
	         String url = "jdbc:oracle:thin:@localhost:1521:xe";
	         conn = DriverManager.getConnection(url, "webdb", "1234");
	         
	         
	          // 3. SQL문 준비 / 바인딩 / 실행
	         String query = "";
	         query += " insert into author ";
	         query += " values(seq_author_id.nextval, ?, ?) ";
	         
		     pstmt = conn.prepareStatement(query);  
		     pstmt.setString(1,"김영하");	
		     pstmt.setString(2,"알쓸신잡");
		         
		        int count = pstmt.executeUpdate();
		        
	          // 4.결과처리
		        System.out.println(count +"건이 저장되었습니다.");

	      } catch (ClassNotFoundException e) {
	          System.out.println("error: 드라이버 로딩 실패 - " + e);
	      } catch (SQLException e) {
	          System.out.println("error:" + e);
	      } finally {
	         
	          // 5. 자원정리
	          try {
	        	
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

	      
	   }

	}