package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


// 가상 URL 주소로 접속하면 호출되는 메소드를 소유한 컨트롤러 클래스 선언
// @Controller 를 붙임으로써 컨트롤러 클래스임을 지정한다.
@Controller
public class BoardController {
	
	// 속성변수 boardService 선언하고, BoardService 인터페이스를 구현받은 객체를 생성해 저장
	// 관용적으로 BoardService 인터페이스를 구현받은 객체명은 BoardServiceImpl 이다.
		// Autowired의 역할 => 속성변수에 붙은 자료형인 인터페이스를 구현한 클래스를 객체화하여 저장한다.
		// 인터페이스를 구현한 클래스가 1개가 아니면 에러가 발생한다.
		// 단 @Autowired(required=false) 로 선언하면 0개이어도 에러없이 null 저장한다.
		// Spring 에서 지원하는 어노테이션이다.
	
		// 자바에서 지원하는 @Inject 를 사용할수도 있으나 required=false 사용불가능 하다.
		// @Inject 사용하려면 pom.xml 에 아래를 API 수입 설정을 해야한다.
		// <dependency><groupId>javax.inject</groupId><artifactId>javax.inject</artifactId><version>1</version></dependency>
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/boardListForm.do")
	public ModelAndView getBoardList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardListForm.jsp");
		try {
			int boardListAllCnt = this.boardService.getBoardListAllCnt();
			List<Map<String,String>> boardList = this.boardService.getBoardList();
			mav.addObject("boardList",boardList);
			mav.addObject("boardListAllCnt",boardListAllCnt);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ContactController.getBoardList(~)메소드 호출 시 에러 발생");
		}
		return mav;
	}
	@RequestMapping(value="/boardRegForm.do")
	public ModelAndView getBoardRegForm(
			// 파라미터 명이 b_no 인 파라미터값을 받아오는 매개변수 b_no 선언
			// 만약 파라미터명이 없으면 null 값이 들어오므로 
			// 매개변수의 자료형은 String으로 하거나 
			// 아니면 defualtValue를 사용하여 원하는 기본값을 받아오도록 한다.
			
			@RequestParam(value="b_no",  defaultValue="0") int b_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardRegForm.jsp");
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ContactController.getBoardList(~)메소드 호출 시 에러 발생");
		}
		return mav;
	}
	
	
	@RequestMapping(value="/boardRegProc.do", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public int insertBoard(
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
			BoardDTO boardDTO){
		System.out.println(boardDTO.getSubject());
		System.out.println(boardDTO.getEmail());
		System.out.println(boardDTO.getContent());
		System.out.println(boardDTO.getPwd());
		System.out.println(boardDTO.getB_no());
		System.out.println(9999);
		int boardRegCnt=0;
		try {
			// BoardServiceImpl 객체의 insertBoard 메소드 호출로 게시판 입력하고 입력 연락처 행의 개수 얻기
			boardRegCnt = this.boardService.insertBoard(boardDTO);
		} catch (Exception e) {
			// TODO: handle exception
			boardRegCnt=-1;
			System.out.println("BoardController.insertBoard(~) 메소드 예외 발생!");
		}
		return boardRegCnt;
	}
	
	
}
