package com.naver.erp;

import java.util.List;
import java.util.Map;

public interface ContactDAO {

	int getContactListAllCnt(ContactSearchDTO contactSearchDTO);

	List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO);

	int insertContact(ContactDTO contactDTO);

	void insertSkill(ContactDTO contactDTO);

	ContactDTO getContact(int developer_no);

	List<String> getSkill(int developer_no);

	int getContactCnt(ContactDTO contactDTO);

	int updateContact(ContactDTO contactDTO);

	int skillDelCnt(ContactDTO contactDTO);

	int skillUpCnt(ContactDTO contactDTO);

	int contactDelCnt(ContactDTO contactDTO);

}
