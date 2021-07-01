package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSelectOneApp {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  au.author_id, ";
			query += "        au.author_name, ";
			query += "        au.author_desc, ";
			query += "        bo.book_id, ";
			query += "        bo.title, ";
			query += "        bo.pubs, ";
			query += "        to_char(bo.pub_date, 'YYYY-MM-DD') pubDate, ";
			query += "        bo.author_id ";
			query += " from book bo, author au ";
			query += " where bo.author_id = au.author_id ";
			query += " and book_id = ? ";
		    
			pstmt = conn.prepareStatement(query);
		    pstmt.setInt(1, 5);
		    
			rs = pstmt.executeQuery();
			
			
		    // 4.결과처리
			
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_Name");
				String authorDesc = rs.getString("author_desc");
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pubDate");
				int boAuthorId = rs.getInt("author_id");
				
				System.out.println(authorId + ", " + authorName + ", " + authorDesc + ", " +bookId + ", " + title + ", " + pubs+ ", " + pubDate + ", " + boAuthorId);
			}

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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

	}

}