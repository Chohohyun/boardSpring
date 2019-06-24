package com.naver.erp;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


// Repository를 붙임으로써 DAO 클래스 임을 지정하게 되고, bean 태그로 자동 등록된다.
@Repository
public class BoardDAOImpl implements BoardDAO{
	
	// SqlSessionTemplate 객체를 생성해 속변 sqlSession 에 저장

		// Autowired의 역할 => 속성변수에 붙은 자료형인 인터페이스를 구현한 클래스를 객체화하여 저장한다.
	@Autowired
	private SqlSessionTemplate sqlSession;
	

	// 1개 게시판 글 출력번호 수정하고 수정 행의 개수 리턴하는 메소드 선언
	public int updatePrint_no(BoardDTO boardDTO) {
		// SqlSessionTemplate 객체의 update(~,~) 메소드 호출하여
		// 게시판 글 출력번호 수정하고 수정 행의 개수 얻기
		
		// int update("쿼리문 설정 xml 파일 안에 mapper 태그의 namespace 속성값.update태그 id 속성값", update 쿼리에 삽입되는 외부 데이터 자료형)
		
			// 쿼리문설정 xml 파일 안에 namespace 속성값을 가진 mapper 태그 안에 update 태그 id 속성값을 가진 태그 내부의
			// update 쿼리문에 update쿼리에 삽입되는 외부 데이터 자료형을 삽입하여 쿼리를 실행한 후 리턴되는 정수 데이터를 int로 리턴한다.
			
			// 리턴 자료형은 무조건 int 형이고, resultType 속성은 없다.
		
			// 외부데이터 자료형이 DTO일 경우 DTO 내부의 속성변수는 #{속성변수명} 또는 ${속성변수명}으로 삽입된다.
			// #{속성변수명}일 경우 속성변수의 자료형이 String이면 '(싱글쿼트)가 붙어 삽입된다.
			// ${속성변수명}일 경우 속성변수의 자료형이 무엇이든 '(싱글쿼트)가 삽입된다.
		int updatePrint_noCnt = this.sqlSession.update("com.naver.erp.BoardDAO.updatePrint_no",boardDTO);
		return updatePrint_noCnt;
	}
	
	// 게시판 글 입력 후 입력 적용 행의 개수 리턴하는 메소드 선언
	public int insertBoard(BoardDTO boardDTO) {
		
	// int insert( "쿼리문 설정 xml 파일 안에 mapper 태그의 namespace 속성값.insert태그 id 속성값", insert 쿼리에 삽입되는 외부 데이터 자료형)
		// 쿼리문설정 xml 파일 안에 namespace 속성값을 가진 mapper 태그 안에 update 태그 id 속성값을 가진 태그 내부의
		// update 쿼리문에 update쿼리에 삽입되는 외부 데이터 자료형을 삽입하여 쿼리를 실행한 후 리턴되는 정수 데이터를 int로 리턴한다.
		
		// 리턴 자료형은 무조건 int 형이고, resultType 속성은 없다.
	
		// 외부데이터 자료형이 DTO일 경우 DTO 내부의 속성변수는 #{속성변수명} 또는 ${속성변수명}으로 삽입된다.
		// #{속성변수명}일 경우 속성변수의 자료형이 String이면 '(싱글쿼트)가 붙어 삽입된다.
		// ${속성변수명}일 경우 속성변수의 자료형이 무엇이든 '(싱글쿼트)가 삽입된다.
		System.out.println("여긴된다.");
		int boardRegCnt = this.sqlSession.insert("com.naver.erp.BoardDAO.insertBoard",boardDTO);
		System.out.println(boardRegCnt);
		return boardRegCnt;
	}

}
