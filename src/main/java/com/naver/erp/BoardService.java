package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface BoardService {

	// 글을 집어넣는 메소드선언
	int insertBoard(BoardDTO boardDTO);
/*
	List<Map<String, String>> getBoardList();

	int getBoardListAllCnt();*/
	
	List<Map<String, String>> getBoardList(BoardSearchDTO boardSearchDTO);

	int getBoardListAllCnt(BoardSearchDTO boardSearchDTO);
	
	
	

	BoardDTO getBoardDTO(int b_no);

	BoardDTO getUpDelBoardDTO(int b_no);

	int updateBoard(BoardDTO boardDTO);

	int deleteBoard(BoardDTO boardDTO);
}
