package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ContactServiceImpl implements ContactService{
	// 속성변수 boardDAO 선언하고, BoardDAO 라는 인터페이스를
	// 구현한 클래스를 객체화 하여 저장
	// **********************************************************
	// Autowired이 붙은 속성변수에는 인터페이스 자료형을 쓰고
	// 이 인터페이를 구현한 클래스를 객체화하여 저장
	@Autowired
	private ContactDAO contactDAO;

	@Override
	public int getContactListAllCnt(ContactSearchDTO contactSearchDTO) {
		int contactListAllCnt = this.contactDAO.getContactListAllCnt(contactSearchDTO);
		return contactListAllCnt;
	}

	@Override
	public List<Map<String, String>> getContactList(ContactSearchDTO contactSearchDTO) {
		List<Map<String,String>> contactList = this.contactDAO.getContactList(contactSearchDTO);
		return contactList;
	}

	@Override
	public int insertContact(ContactDTO contactDTO) {
		int contactRegCnt = this.contactDAO.insertContact(contactDTO);
		if(contactDTO.getSkill()!=null) {
			this.contactDAO.insertSkill(contactDTO);
		}

		return contactRegCnt;
	}

	@Override
	public ContactDTO getUpDelBoardDTO(int developer_no) {
		ContactDTO contactDTO = this.contactDAO.getContact(developer_no);
		
		List<String> skill = this.contactDAO.getSkill(developer_no);
		contactDTO.setSkill(skill);

		return contactDTO;
	}

	@Override
	public int updateBoard(ContactDTO contactDTO) {
		// 수정할 게시판의 존재개수를 BoardDAOImpl 에게 명령한 후 알아내기
		System.out.println("업데이트 여기까진 된다.1");
		int contactCnt = this.contactDAO.getContactCnt(contactDTO);
		System.out.println("업데이트 여기까진 된다.2");
		if(contactCnt==0) {
			return -1;
		}
		// 게시판 스킬 삭제후 다시 넣기
		int skillDelCnt = this.contactDAO.skillDelCnt(contactDTO);


		// 게시판 스킬 넣기
		int skillUpCnt = this.contactDAO.skillUpCnt(contactDTO);
		// 게시판 수정 명령한 후 수정 적용행의 개수 알아내기
		System.out.println("업데이트 여기까진 된다.3");
		int updateCnt = this.contactDAO.updateContact(contactDTO);
		return updateCnt;
	}

	@Override
	public int deleteBoard(ContactDTO contactDTO) {
		// 삭제할 게시판의 존재개수를 BoardDAOImpl 에게 명령한 후 알아내기
		int contactCnt = this.contactDAO.getContactCnt(contactDTO);
		if(contactCnt==0) {
			return -1;
		}         
		int skillDelCnt = this.contactDAO.skillDelCnt(contactDTO);
		
		int contactDelCnt = this.contactDAO.contactDelCnt(contactDTO);
		
		return contactDelCnt;
	}
}
