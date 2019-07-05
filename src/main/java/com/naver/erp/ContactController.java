package com.naver.erp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ContactController {
	
	@Autowired
	ContactService contactService;

	@RequestMapping(value="/contactSearchForm.do")
	public ModelAndView getContactList(
			ContactSearchDTO contactSearchDTO,HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contactSearchForm.jsp");
		
		System.out.println(contactSearchDTO.getReligion()+"종교");

		if(contactSearchDTO.getSelectPageNo()==0) {
			contactSearchDTO.setSelectPageNo(1);
		}
		try{
			String uri = (String)session.getAttribute("uri");
			
			// 만약 전에 들렸던 클래스가 없거나
			// 전에 들렸던 클래스가 가칭 boardListForm 라면
			// Session 객체에 파라미터 값을 저장하기
			if(uri ==null || uri.equals("contactSearchForm")) {		

				session.setAttribute("contactSearchDTO",contactSearchDTO);

			}
			

			// 만약에 전에 들렸던 클래스가 있고
			// 전에 들렸던 클래스가 boardListForm이 아니라면
			// Session 객체에서 파라미터값 꺼내서 저장하기
			else {
				contactSearchDTO = (ContactSearchDTO) session.getAttribute("contactSearchDTO");
				System.out.println("여기 실행7");
			}
			session.setAttribute("uri","contactSearchForm");

			System.out.println("contactSearchDTO"+contactSearchDTO.getSelectPageNo());

			System.out.println("여긴된다.1");
			int contactListAllCnt = this.contactService.getContactListAllCnt(contactSearchDTO);

			System.out.println("여긴된다.1");
			int lastPageNo = contactListAllCnt / 5;
			if( contactListAllCnt % 5>0) {
				lastPageNo++;
			}
			if( lastPageNo < contactSearchDTO.getSelectPageNo() ){

				session.setAttribute("selectPageNo", "1");
				contactSearchDTO.setSelectPageNo(1);
			}
			System.out.println("여긴된다.2");
			List<Map<String,String>> contactList = this.contactService.getContactList(contactSearchDTO);

			System.out.println("여긴된다.3");

			mav.addObject("contactList",contactList);
			mav.addObject("contactListAllCnt",contactListAllCnt);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ContactController.getContactList(~)메소드 호출 시 에러 발생");
		}
		return mav;
	}
	
	
	@RequestMapping(value="/contactRegForm.do")
	public ModelAndView getContactRegForm(
			// 파라미터 명이 b_no 인 파라미터값을 받아오는 매개변수 b_no 선언
			// 만약 파라미터명이 없으면 null 값이 들어오므로 
			// 매개변수의 자료형은 String으로 하거나 
			// 아니면 defualtValue를 사용하여 원하는 기본값을 받아오도록 한다.

			@RequestParam(value="b_no",  defaultValue="0") int b_no,
			HttpSession session) {
		session.setAttribute("uri", "contactRegForm");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contactRegForm.jsp");
		try {

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ContactController.getBoardList(~)메소드 호출 시 에러 발생");
		}
		return mav;
	}
	


	@RequestMapping(value="/contactRegProc.do", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public int insertContact(
			//@RequestParam(value="subject") String subject,
			//@RequestParam(value="email") String email,
			//@RequestParam(value="writer") String writer,
			//@RequestParam(value="content") String content,
			//@RequestParam(value="pwd") String pwd
			// 파라미터값을 저장할 BoardDTO 객체를 매개변수로 선언
			// 파라미터명과 BoardDTO 객체의 속성변수명이 같으면
			// setter 메소드가 작동되어 파라미터값이 속성변수에 저장된다.
			// 속성변수명에 대응하는 파라미터명이 없으면 setter 메소드가 작동되지 않는다.
			// 속성변수의 자료형에 관계없이 무조건 null 값이 저장된다.
			// 이때, 속성변수의 자료형이 기본형일 경우 null 값이 저장될 수 없어 에러 발생한다.
			// 이런 에러를 피하려면 파라미터값이 기본형이거나 속성변수의 자료형을 String으로 해야한다.
			// 이런 에러가 발생하면 메소드안의 실행구문은 하나도 실행되지 않음.
			// 매개변수로 들어온 DTO 객체는 이 메소드가 끝난 후 호출되는 JSP 페이지로 그대로 이동한다.
			// 즉, HttpServletRequest 객체에 boardDTO 라는 키값명으로 저장된다.
			ContactDTO contactDTO){
		System.out.println(9999);
		int contactRegCnt=0;
		try {
			System.out.println(contactDTO.getDev_name()+"이름");
			System.out.println(contactDTO.getGraduate_day1()+"일");
			System.out.println(contactDTO.getGraduate_month1()+"월");
			System.out.println(contactDTO.getGraduate_year1()+"년");
			System.out.println(contactDTO.getJumin_num1()+"주민1");
			System.out.println(contactDTO.getJumin_num2()+"주민2");
			System.out.println(contactDTO.getReligion()+"종교");
			System.out.println(contactDTO.getSchool()+"스쿨");
			
			
			// BoardServiceImpl 객체의 insertBoard 메소드 호출로 게시판 입력하고 입력 연락처 행의 개수 얻기
			contactRegCnt = this.contactService.insertContact(contactDTO);
		} catch (Exception e) {
			// TODO: handle exception
			contactRegCnt=-1;
			System.out.println("ContactController.insertContact(~) 메소드 예외 발생!");
		}
		return contactRegCnt;
	}


	@RequestMapping(value="/contactUpDelForm.do", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ModelAndView goBoardUpDelForm(
			@RequestParam(value="developer_no") int developer_no,
			HttpSession session) {
		session.setAttribute("uri", "contactUpDelForm.do");
		// ModelAndView객체 생성하기
		// ModelAndView객체에 호출 JSP 페이지명을 저장하기
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contactUpDelForm.jsp");
		try {
			System.out.println(developer_no+"넘어온 값");
			ContactDTO ContactDTO = this.contactService.getUpDelBoardDTO(developer_no);
			mav.addObject("ContactDTO",ContactDTO);
		}catch(Exception e){
			System.out.println("ContactController.getUpDelBoardDTO(~) 메소드 예외 발생");
		}
		return mav;
	}
	@RequestMapping(value="/contactUpDelProc.do", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public int contactUpDelProc(
			ContactDTO contactDTO,
			@RequestParam(value="upDel") String upDel) {
		int boardUpDelCnt=0;
		try {
			System.out.println(contactDTO.getDev_name());
			System.out.println(contactDTO.getDev_no());
			System.out.println(contactDTO.getGraduate_day1());
			System.out.println(contactDTO.getGraduate_month1());
			System.out.println(contactDTO.getGraduate_year1());
			System.out.println(contactDTO.getJumin_num1());
			System.out.println(contactDTO.getJumin_num2());

			System.out.println(contactDTO.getReligion());
			System.out.println(contactDTO.getSchool());
			if(upDel.equals("up")){
				boardUpDelCnt = this.contactService.updateBoard(contactDTO);
				
			}
			else {
				boardUpDelCnt= this.contactService.deleteBoard(contactDTO);
				
			}
		}catch(Exception e){
			System.out.println("BoardController.boardUpDelProc(~) 메소드 예외 발생");
			return -10;
		}
		return boardUpDelCnt;
	}
}
