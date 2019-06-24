package com.naver.erp;

import java.util.Map;

public interface BoardDAO {

	int insertBoard(BoardDTO boardDTO);

	int updatePrint_no(BoardDTO boardDTO);

}
