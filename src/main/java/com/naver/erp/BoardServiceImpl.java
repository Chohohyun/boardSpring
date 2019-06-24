package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 서비스 클래스인 BoardServiceImpl 클래스 선언
// 서브스 클래스에는 @Service 와 @Transcational를 붙인다.
// @Service => 서비스 클래스 임을 지정하고 bean 태그로 자동 등록된다.
// @Transactional => 서비스 클래스의 메소드 내부에서 일어나는 모든 작업에는 트랜잭션이 걸린다.
@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	// 속성변수 boardDAO 선언하고, BoardDAO 라는 인터페이스를
	// 구현한 클래스를 객체화 하여 저장
	// **********************************************************
	// Autowired이 붙은 속성변수에는 인터페이스 자료형을 쓰고
	// 이 인터페이를 구현한 클래스를 객체화하여 저장
	@Autowired
	private BoardDAO boardDAO;

	public int insertBoard(BoardDTO boardDTO) {
		if(boardDTO.getB_no()>0) {
			int updatePrint_noCnt = this.boardDAO.updatePrint_no(boardDTO);
		}
		int boardRegCnt = this.boardDAO.insertBoard(boardDTO);
		return boardRegCnt;
	}
	
	// 로그인하면 나올 게시판 얻어오기
	public List<Map<String,String>> getBoardList(){
		List<Map<String,String>> boardList = this.boardDAO.getBoardList();
		return boardList;
	}

	// 로그인하면 출력할 총 갯수
	public int getBoardListAllCnt() {
		int boardListAllCnt = this.boardDAO.getBoardListAllCnt();
		return boardListAllCnt;
	}
	
	// 상세보기 들어가기
	public BoardDTO getBoardDTO(int b_no) {
		BoardDTO boardDTO = this.boardDAO.getBoardDTO(b_no);
		System.out.println("여긴됐따");
		if(boardDTO!=null) {
			System.out.println("여긴 되나?");
			int readcount = this.boardDAO.updateReadcount(b_no);

			System.out.println("여긴 되나?2");
		}
		return boardDTO;
	}
}
