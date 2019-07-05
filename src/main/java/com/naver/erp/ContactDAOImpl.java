package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


// Repository를 붙임으로써 DAO 클래스 임을 지정하게 되고, bean 태그로 자동 등록된다.
@Repository
public class ContactDAOImpl implements ContactDAO{

	// SqlSessionTemplate 객체를 생성해 속변 sqlSession 에 저장
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int getContactListAllCnt(ContactSearchDTO contactSearchDTO) {

		int contactListAllCnt = this.sqlSession.selectOne("com.naver.erp.ContactDAO.getContactListAllCnt",contactSearchDTO);
		return contactListAllCnt;
	}

	@Override
	public List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO) {

		List<Map<String, String>> contactList = this.sqlSession.selectList("com.naver.erp.ContactDAO.getContactList",contactSearchDTO);
		return contactList;
	}

	@Override
	public int insertContact(ContactDTO contactDTO) {
		int contactRegCnt = this.sqlSession.insert("com.naver.erp.ContactDAO.insertContact",contactDTO);
		return contactRegCnt;
	}

	@Override
	public void insertSkill(ContactDTO contactDTO) {
		 this.sqlSession.insert("com.naver.erp.ContactDAO.insertSkill",contactDTO);

	}

	@Override
	public ContactDTO getContact(int developer_no) {
		ContactDTO contactDTO =  this.sqlSession.selectOne("com.naver.erp.ContactDAO.getContactDTO",developer_no);
		return contactDTO;
	}

	@Override
	public List<String> getSkill(int developer_no) {
		List<String> skill = this.sqlSession.selectList("com.naver.erp.ContactDAO.getSkill",developer_no);

		return skill;
	}

	@Override
	public int getContactCnt(ContactDTO contactDTO) {
		int contactCnt = this.sqlSession.selectOne("com.naver.erp.ContactDAO.getContactCnt",contactDTO);
		return contactCnt;
	}

	@Override
	public int updateContact(ContactDTO contactDTO) {
		int updateCnt = this.sqlSession.update("com.naver.erp.ContactDAO.updateCnt",contactDTO);
		return updateCnt;
	}

	@Override
	public int skillDelCnt(ContactDTO contactDTO) {
		int skillDelCnt = this.sqlSession.delete("com.naver.erp.ContactDAO.skillDelCnt",contactDTO);
		return skillDelCnt;
	}

	@Override
	public int skillUpCnt(ContactDTO contactDTO) {
		int skillUpCnt = this.sqlSession.insert("com.naver.erp.ContactDAO.skillUpCnt",contactDTO);
		return skillUpCnt;
	}

	@Override
	public int contactDelCnt(ContactDTO contactDTO) {
		int contactDelCnt = this.sqlSession.delete("com.naver.erp.ContactDAO.contactDelCnt",contactDTO);
		return contactDelCnt;
	}

}