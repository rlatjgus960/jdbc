package com.javaex.ex05;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList; //얘 안쓰이는거같은데 꼭 써야하나!

		BookDao bookDao = new BookDao();
		List<BookVo> bookList; 

		
		// (1)
		
		
		/*
		// 작가테이블 책테이블 생성
		// 작가테이블 시퀀스, 책테이블 시퀀스 생성
		authorDao.authorTable();
		authorDao.authorSeq();

		bookDao.bookTable();
		bookDao.bookSeq();
	
		*/
		
		
		// authorDao.authorInsert(); 이용해서 작가 6명 데이터 추가
		AuthorVo AuthorVo1 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(AuthorVo1);

		AuthorVo AuthorVo2 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(AuthorVo2);

		AuthorVo AuthorVo3 = new AuthorVo("이고잉", "프로그래머");
		authorDao.authorInsert(AuthorVo3);

		AuthorVo AuthorVo4 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(AuthorVo4);

		AuthorVo AuthorVo5 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(AuthorVo5);

		AuthorVo AuthorVo6 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(AuthorVo6);

		/*
		if (aCount6 > 0) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + aCount6 + ")]");
		}
		*/

		
		
		// bookDao.bookInsert(); 책 8권 추가
		BookVo bookVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "1988-02-22", 1);
		bookDao.bookInsert(bookVo1);

		BookVo bookVo2 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(bookVo2);

		BookVo bookVo3 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert(bookVo3);

		BookVo bookVo4 = new BookVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 3);
		bookDao.bookInsert(bookVo4);

		BookVo bookVo5 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert(bookVo5);

		BookVo bookVo6 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(bookVo6);

		BookVo bookVo7 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(bookVo7);

		BookVo bookVo8 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(bookVo8);

		// 책 수정, 삭제 테스트
		/*책 수정
		BookVo uBookVo = new BookVo("몰라", "ㅁㄹ", "2021-07-04", 3, 5);
		bookDao.bookUpdate(uBookVo);
		*/
		
		/*책 삭제
		bookDao.bookDelete(3);
		*/
		

		// 책 리스트 출력
		// bookDao.getBookList(); --> 결과 8개
		bookList = bookDao.getBookList();
		printList(bookList);


		
		// (2)
		// 스캐너를 통해서 사용자한테 키워드 입력받음
		// 검색어를 입력해주세요 (시작화면)
		// 검색어 : 문
		System.out.println("검색어를 입력해주세요.");
		System.out.print("검색어 : ");
		String search = sc.nextLine();
		

		// bookDao.getBookList(); 파라미터는 결정해서 넣기 --> 4개출력
		// 검색된 책 정보 출력되도록
		List<BookVo> searchList = bookDao.getBookList(search);
		printList(searchList);
		
		
		
		/* 
		for (BookVo b : bookList) {
			if (b.getAuthorName().contains(search) || b.getPubs().contains(search) || b.getTitle().contains(serch)) {
				System.out.println(b.getBookId() + ", " + b.getTitle() + ", " + b.getPubs() + ", "
					+ b.getPubDate() + ", " + b.getAuthorId() + ", " + b.getAuthorName() + ", "
					+ b.getAuthorDesc());
			}
		}
		*/
		
		sc.close();
	}

	public static void printList(List<BookVo> bookList) {
		
		System.out.println("");
		System.out.println("=========================== 도서목록 ===========================");

		for (int i = 0; i < bookList.size(); i++) {

			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId() + ", " + bookVo.getTitle() + ", " + bookVo.getPubs() + ", "
					+ bookVo.getPubDate() + ", " + bookVo.getAuthorId() + ", " + bookVo.getAuthorName() + ", "
					+ bookVo.getAuthorDesc());
			
			/*
			System.out.println(bookVo.getBookId() + ", " + bookVo.getTitle() + ", " + bookVo.getPubs() + ", "
					+ bookVo.getPubDate() + ", "  + bookVo.getAuthorName());
			*/

		}
		System.out.println("==============================================================");
		System.out.println("");
	}
	
}
