/* 메소드 파라미터를 Vo 사용*/


package com.javaex.ex03;

import java.util.List;

public class AuthorApp {
	
	//메소드 일반
	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;  //데이터 계속 가져와야돼서 처음에 선언부터 해줌
		
		//리스트출력 0x777
		//DB에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);
		
		//작가등록
		AuthorVo iAuthorVo = new AuthorVo(3, "김일영", "강남하이미디어");
		int iCount = authorDao.authorInsert(iAuthorVo);
		
		if(iCount>0) {
			System.out.println("[등록되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}
		
		
		//리스트 출력
		//DB에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(authorList);
		
		
		//작가수정
		AuthorVo uAuthorVo = new AuthorVo(3, "김일영", "강남하이미디어");
		int uCount = authorDao.authorUpdate(uAuthorVo);
		
		if(uCount>0) {
			System.out.println("[수정되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}
		
		
		//리스트출력
		//DB에서 가져오기
		authorList = authorDao.getAuthorList();
		//리스트 포문으로 출력
		printList(authorList);
		
		
		
		
		//작가삭제
		//AuthorVo dAuthorVo = new AuthorVo(); 상황에 따라 쓰거나 안쓰거나.. 지금은 숫자 하나만 받으니까 그냥 안씀
		
		int dCount = authorDao.authorDelete(7);
		if(dCount>0) {
			System.out.println("[삭제되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}
		//리스트출력
		authorList = authorDao.getAuthorList();
		printList(authorList);
		
		
		
		/*
		//작가 1명의 정보
		??? = authorDao.getAuthorOne(3);
		*/
	}
	
	//리스트 출력 메소드
	public static void printList(List<AuthorVo> authorList)  {
		
		for(int i=0; i<authorList.size(); i++) {
			
			AuthorVo authorVo = authorList.get(i);
			System.out.println(authorVo.getAuthorId() + "\t" + authorVo.getAuthorName() + "\t\t" + authorVo.getAuthorDesc());
			
			/*이렇게도 표현 가능
			AuthorVo authorVo = authorList.get(i); //0x780에 담았다 ~
			
			int authorId = authorVo.getAuthorId();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();
			
			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
			*/
			
			/* 이렇게도 표현 가능
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
			*/
		}
		System.out.println("=====================================");
		System.out.println("");
	}

}
