package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface ContactService {

	int getContactListAllCnt(ContactSearchDTO contactSearchDTO);

	List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO);

	int insertContact(ContactDTO contactDTO);

	ContactDTO getUpDelBoardDTO(int developer_no);

	int updateBoard(ContactDTO contactDTO);

	int deleteBoard(ContactDTO contactDTO);

}
