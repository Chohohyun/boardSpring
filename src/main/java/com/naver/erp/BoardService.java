package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface BoardService {

	// 글을 집어넣는 메소드선언
	int insertBoard(BoardDTO boardDTO);

	List<Map<String, String>> getBoardList();

	int getBoardListAllCnt();
}
