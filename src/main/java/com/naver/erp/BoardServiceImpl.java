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

	/*// 로그인하면 나올 게시판 얻어오기
	public List<Map<String,String>> getBoardList(){
		List<Map<String,String>> boardList = this.boardDAO.getBoardList();
		return boardList;
	}

	// 로그인하면 출력할 총 갯수
	public int getBoardListAllCnt() {
		int boardListAllCnt = this.boardDAO.getBoardListAllCnt();
		return boardListAllCnt;
	}*/

	// 로그인하면 나올 게시판 얻어오기
	public List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO){
		List<Map<String,String>> boardList = this.boardDAO.getBoardList(boardSearchDTO);
		return boardList;
	}

	// 로그인하면 출력할 총 갯수
	public int getBoardListAllCnt(BoardSearchDTO boardSearchDTO) {
		int boardListAllCnt = this.boardDAO.getBoardListAllCnt(boardSearchDTO);
		return boardListAllCnt;
	}


	// 상세보기 들어가기
	public BoardDTO getBoardDTO(int b_no) {
		BoardDTO boardDTO = this.boardDAO.getBoardDTO(b_no);
		if(boardDTO!=null) {
			int readcount = this.boardDAO.updateReadcount(b_no);
			boardDTO.setReadcount(boardDTO.getReadcount()+1);
		}
		return boardDTO;
	}

	@Override
	public BoardDTO getUpDelBoardDTO(int b_no) {
		BoardDTO boardDTO = this.boardDAO.getBoardDTO(b_no);
		return boardDTO;
	}

	@Override
	public int updateBoard(BoardDTO boardDTO) {
		// 수정할 게시판 글의 b_no 데이터 꺼내기
		int b_no = boardDTO.getB_no();
		
		// 수정할 게시판의 존재개수를 BoardDAOImpl 에게 명령한 후 알아내기
		int boardCnt = this.boardDAO.getBoardCnt(boardDTO);
		if(boardCnt==0) {
			return -1;
		}
		// 수정할 게시판의 비밀번호 존재 개수를 BoardDAOImpl 에게 명령한 후 알아내기
		int pwdCnt = this.boardDAO.getPwdCnt(boardDTO);
		if(pwdCnt==0) {
			return 0;
		}
		// 게시판 수정 명령한 후 수정 적용행의 개수 알아내기
		int updateCnt = this.boardDAO.updateBoard(boardDTO);
		return updateCnt;
	}

	@Override
	public int deleteBoard(BoardDTO boardDTO) {

		System.out.println("여기까진된다");
		// 삭제할 게시판의 존재개수를 BoardDAOImpl 에게 명령한 후 알아내기
		int boardCnt = this.boardDAO.getBoardCnt(boardDTO);
		if(boardCnt==0) {
			return -1;
		}

		System.out.println("여기까진된다");
		// 삭제할 게시판의 비밀번호 존재 개수를 BoardDAOImpl 에게 명령한 후 알아내기
		int pwdCnt = this.boardDAO.getPwdCnt(boardDTO);
		if(pwdCnt==0) {
			return -2;
		}
		System.out.println("여기까진된다");
		// 삭제할 게시판의 아들글 존재 개수를 BoardDAOImpl 에게 명령한 후 알아내기
		int sonCnt = this.boardDAO.getSonCnt(boardDTO);
		if(sonCnt>0) {
			return -3;
		}
		System.out.println("여기까진된다");
		// 삭제될 게시판 이후 글의 출력 순서 번호를 1씩 감소 시킨 후 수정 적용행의 개수 알아내기 
		int upPrintNoCnt = this.boardDAO.upPrintNo(boardDTO);
		

		System.out.println("여기까진된다");
		// 게시판 삭제 명령한 후 삭제 적용행의 개수 알아내기
		int deleteCnt = this.boardDAO.deleteBoard(boardDTO);

		System.out.println("여기까진된다");
		System.out.println(deleteCnt+"delete");
		return deleteCnt;
	}
}

