package com.naver.erp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naver.erp.UserDTO;


// 가상 URL 주소로 접속하면 호출되는 메소드를 소유한 컨트롤러 클래스 선언
// @Controller 를 붙임으로써 컨트롤러 클래스임을 지정한다.
@Controller
public class LoginController {
	// 속성변수 loginService 선언하고, LoginService라는 인터페이스를 구현한 클래스를 객체화하여 저장
	// *****************************************************
	// @Autowired 이 붙은 속성변수에는 인터페이스 자료형을 쓰고
	//  이 인터페이스를 구현한 클래스를 객체화하여 저장한다.
	// LoginService라는 인터페이스를 구현한 클래스의 이름을 몰라도 관계없다. 1개 존재하기만 하면 된다.
	
	@Autowired
	private LoginService loginService;
	
	// 가상주소 /erp/loginForm.do로 접속하면 호출되는 메소드 선언
	@RequestMapping(value="/loginForm.do")
	public ModelAndView loginForm(
			// HttpSession 객체가 들어올 매개변수 선언
			// 매개변수에 자료형이 HttpSession이면 웹서버가
			// 생성한 HttpSession 객체가 들어온다.
			HttpSession session) {

		// HttpSession 객체에 저장된 로그인 아이디 삭제하기
		session.removeAttribute("admin_id");

		// <참고>HttpSession 객체에 저장된 모든 데이터 제거한다.
		//session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm.jsp");
		return mav;
	}
	
	@RequestMapping(value="/goRegForm.do")
	public ModelAndView regForm(
			// HttpSession 객체가 들어올 매개변수 선언
			// 매개변수에 자료형이 HttpSession이면 웹서버가
			// 생성한 HttpSession 객체가 들어온다.
			HttpSession session) {


		// <참고>HttpSession 객체에 저장된 모든 데이터 제거한다.
		//session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("regForm.jsp");
		return mav;
	}
	@RequestMapping(
			value="/userRegForm.do",
			 method = RequestMethod.POST,produces="application/json;charset=UTF-8"
			)

	@ResponseBody 
	public int userRegProc(
			HttpSession session,HttpServletResponse response,
			UserDTO userDTO
			) {
		int userRegCnt=0;
		try {
			session.removeAttribute("uri");
			
			userRegCnt = this.loginService.getUserRegCnt(userDTO);
			System.out.println("됩시다");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("LoginController.loginProc(~) 에서 에러 발생");
			userRegCnt=-1;
		} 
		return userRegCnt;
	}
	// 가상주소 /erp/loginForm.do로 접속하면 호출되는 메소드 선언
	/*@RequestMapping(value="/loginProc.do" , method=RequestMethod.POST)
	public ModelAndView loginProc(
			// admin_id 라는 파라미터명의 파라미터값이 들어올 String 형 매개변수 선언하기
			// <주의> admin_id 라는 파라미터명의 파라미터값이 없으면 메소드 내용 실행 안되고 에러 발생
			
			// @RequestParam( value="파라미터명", required=true|false, defaultValue="디폴트값") 자료형 파라미터값 저장할 매개변수 명
			
			// 파라미터명의 파라미터값을 파라미터갑 저장하 매개변수명에 저장한다.
			// required=true => 파라미터명이 없을 경우 에러 발생, 메소드 안의 코딩은 한줄도 실행 안된다. 생략가능
			// required=false => 파라미터명이 없을 경우 null 값을 매개변수에 저장, 만약 매개변수 자료형이 기본형이면 에러 발생
			// defaultValue="디폴트값" => 파라미터값이 없을 경우 디폴트 값을 매개변수에 저장, required=false가 있어야함
			// 생략 시 매개변수에는 null저장됨
			//@RequestParam(value="admin_id") String admin_id,
			//@RequestParam(value="pwd") String pwd
			
			// 프레임워크에서 자동으로 loginForm.do 에서 serialize 한 것을 HashMap으로 변환해서 저장
			@RequestParam Map<String,String> admin_id_pwd
			){
		
		// ModelAndView 객체 생성하기
		// ModelAndView 객체에 호출 JSP 페이지명을 저장하기
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginProc.jsp");
		int admin_idCnt=0;
		try {
			// HashMap 객체에 로그인 아이디 암호 저장하기
			//Map<String,String> admin_id_pwd = new HashMap<String,String>();
			//admin_id_pwd.put("admin_id", admin_id);
			//admin_id_pwd.put("pwd", pwd);
			admin_idCnt = this.loginService.getAdminCnt(admin_id_pwd);
			
			mav.addObject("admin_idCnt",admin_idCnt);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("LoginController.loginProc(~) 에서 에러발생");
			mav.addObject("admin_idCnt",-1);
		}
		// ModelAndView 객체를 리턴하기
		return mav;
	}*/
	
	@RequestMapping(
			value="/loginProc.do",
			method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public int loginProc(
			HttpSession session,HttpServletResponse response,
			// 파리미터명, 파라미터값이 저장된 HashMap 객체를 받아오는 매개변수 선언
			// 파라미터명은 키값으로 파라미터 값으로는 키값에 대응하는 저장 문자열 HashMap 객체 저장된다.
			@RequestParam Map<String,String> paramsMap
			) {
		int admin_idCnt=0;
		try {
			session.removeAttribute("uri");
			admin_idCnt = this.loginService.getAdminCnt(paramsMap);
			if(admin_idCnt==1) {
				session.setAttribute("admin_id", paramsMap.get("admin_id"));
				// 아이디 암호 저장의사가 없을경우
				// 아이디 암호 저장한 Cookies 객체 생성하고 쿠키값을 null로 덮어 씌우고 수명 없애기
				// 그리고 이 쿠키를 HttpServletRequest 객체에 저장하기
				if(paramsMap.get("is_login")==null) {
					// Cookie 객체 생성하고 쿠키명 cookie, 쿠기값 null로 설정
					/*Cookie cookie1 = new Cookie("admin_id",null);
					cookie1.setMaxAge(0);
					response.addCookie(cookie1);
					Cookie cookie2 = new Cookie("pwd",null);
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);*/
					
					Util.addCookie(response, "admin_id", null, 0);
					Util.addCookie(response, "pwd", null, 0);
				}
				else {
					// Cookie 객체 생성하고 쿠키명 admin_id, 쿠기값 admin_id, 수명 60*60*24로 설정
					/*Cookie cookie1 = new Cookie("admin_id",admin_id);
					cookie1.setMaxAge(60*60*24);
					response.addCookie(cookie1);
					
					// Cookie 객체 생성하고 쿠키명 pwd, 쿠기값 pwd, 수명 60*60*24로 설정
					Cookie cookie2 = new Cookie("pwd",pwd);
					cookie2.setMaxAge(60*60*24);
					response.addCookie(cookie2);*/
					

					Util.addCookie(response, "admin_id",paramsMap.get("admin_id") , 60*60*24);
					Util.addCookie(response, "pwd", paramsMap.get("pwd"), 60*60*24);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("LoginController.loginProc(~) 에서 에러 발생");
			admin_idCnt=-1;
		} 
		return admin_idCnt;
	}
}
