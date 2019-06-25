package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
	// 1개 게시판 글 입력 후 적용 행의 개수 리턴하는 메소드 선언
	int insertBoard(BoardDTO boardDTO);

	// 1개 게시판 글 출력번호 수정하고 수정행의 개수 리턴하는 메소드 선언
	int updatePrint_no(BoardDTO boardDTO);

	
	// 검색한 게시판 목록 리턴하는 메소드 선언
	List<Map<String, String>> getBoardList();

	int getBoardListAllCnt();


	// 검색한 게시판 목록 리턴하는 메소드 선언
	List<Map<String, String>> getBoardList(BoardSearchDTO boardSearchDTO);

	int getBoardListAllCnt(BoardSearchDTO boardSearchDTO);
	
	
	BoardDTO getBoardDTO(int b_no);

	int updateReadcount(int b_no);
	
	

	int getBoardCnt(BoardDTO boardDTO);

	int getPwdCnt(BoardDTO boardDTO);

	int updateBoard(BoardDTO boardDTO);

	int getSonCnt(BoardDTO boardDTO);

	int deleteBoard(BoardDTO boardDTO);

	int upPrintNo(BoardDTO boardDTO);

}
