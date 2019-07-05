package com.naver.erp;

import java.util.List;

// 게시판 검색 조건을 저장하는 BoardSearchDTO 클래스 선언
	// <문> 게시판 검색 조건들을 하나의 BoardSearchDTO 객체에 저장하는 이유는?
		// 다량의 데이터를 하나로 단위화 시킴으로써 DB 연동 시 편리함을 추구한다.
		// 특히 스프링에서는 다량의 파라미터값이 자동으로 DTO에 저장되기 때문에
		// 적극적으로 많이 사용된다.

public class ContactDTO {
	private String dev_no;
	private String dev_name;
	private String jumin_num1;
	private String jumin_num2;
	private String religion;
	private String school;
	private String graduate_year1;
	private String graduate_month1;
	private String graduate_day1;
	private List<String> skill;
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getJumin_num1() {
		return jumin_num1;
	}
	public void setJumin_num1(String jumin_num1) {
		this.jumin_num1 = jumin_num1;
	}
	public String getJumin_num2() {
		return jumin_num2;
	}
	public void setJumin_num2(String jumin_num2) {
		this.jumin_num2 = jumin_num2;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public List<String> getSkill() {
		return skill;
	}
	public void setSkill(List<String> skill) {
		this.skill = skill;
	}
	public String getGraduate_year1() {
		return graduate_year1;
	}
	public void setGraduate_year1(String graduate_year1) {
		this.graduate_year1 = graduate_year1;
	}
	public String getGraduate_month1() {
		return graduate_month1;
	}
	public void setGraduate_month1(String graduate_month1) {
		this.graduate_month1 = graduate_month1;
	}
	public String getGraduate_day1() {
		return graduate_day1;
	}
	public void setGraduate_day1(String graduate_day1) {
		this.graduate_day1 = graduate_day1;
	}
	public String getDev_no() {
		return dev_no;
	}
	public void setDev_no(String dev_no) {
		this.dev_no = dev_no;
	}
	
}
