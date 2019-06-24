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

}
