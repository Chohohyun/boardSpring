package com.naver.erp;

// 게시판 검색 조건을 저장하는 BoardSearchDTO 클래스 선언
	// <문> 게시판 검색 조건들을 하나의 BoardSearchDTO 객체에 저장하는 이유는?
		// 다량의 데이터를 하나로 단위화 시킴으로써 DB 연동 시 편리함을 추구한다.
		// 특히 스프링에서는 다량의 파라미터값이 자동으로 DTO에 저장되기 때문에
		// 적극적으로 많이 사용된다.

public class ContactSearchDTO {
	private String keyword;
	private String[] gender;
	private String religion;
	private String[] school;
	private String[] skill;
	private int selectPageNo;
	
	private String graduate_year1;
	private String graduate_year2;
	private String graduate_month1;
	private String graduate_month2;
	
	private String ascDesc;
	private String selectOption;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String[] getGender() {
		return gender;
	}
	public void setGender(String[] gender) {
		this.gender = gender;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String[] getSchool() {
		return school;
	}
	public void setSchool(String[] school) {
		this.school = school;
	}
	public String[] getSkill() {
		return skill;
	}
	public void setSkill(String[] skill) {
		this.skill = skill;
	}
	public int getSelectPageNo() {
		return selectPageNo;
	}
	public void setSelectPageNo(int selectPageNo) {
		this.selectPageNo = selectPageNo;
	}
	public String getGraduate_year1() {
		return graduate_year1;
	}
	public void setGraduate_year1(String graduate_year1) {
		this.graduate_year1 = graduate_year1;
	}
	public String getGraduate_year2() {
		return graduate_year2;
	}
	public void setGraduate_year2(String graduate_year2) {
		this.graduate_year2 = graduate_year2;
	}
	public String getGraduate_month1() {
		return graduate_month1;
	}
	public void setGraduate_month1(String graduate_month1) {
		this.graduate_month1 = graduate_month1;
	}
	public String getGraduate_month2() {
		return graduate_month2;
	}
	public void setGraduate_month2(String graduate_month2) {
		this.graduate_month2 = graduate_month2;
	}
	public String getSelectOption() {
		return selectOption;
	}
	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
	public String getAscDesc() {
		return ascDesc;
	}
	public void setAscDesc(String ascDesc) {
		this.ascDesc = ascDesc;
	}
	
	
	
}
