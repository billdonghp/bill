package com.ait.hrm.entity;

public class PreviewUpgradeReports {
	private String empId ;
	private String empName ;
	private String deptName ;
	private String post_grade ;
	private String joinDate ;
	private float workAge ;
	private String evsGrade1 ;
	private String evsGrade2 ;
	private String evsGrade3 ;
	
	public PreviewUpgradeReports(){}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEvsGrade1() {
		return evsGrade1;
	}
	public void setEvsGrade1(String evsGrade1) {
		this.evsGrade1 = evsGrade1;
	}
	public String getEvsGrade2() {
		return evsGrade2;
	}
	public void setEvsGrade2(String evsGrade2) {
		this.evsGrade2 = evsGrade2;
	}
	public String getEvsGrade3() {
		return evsGrade3;
	}
	public void setEvsGrade3(String evsGrade3) {
		this.evsGrade3 = evsGrade3;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getPost_grade() {
		return post_grade;
	}
	public void setPost_grade(String post_grade) {
		this.post_grade = post_grade;
	}
	public float getWorkAge() {
		return workAge;
	}
	public void setWorkAge(float workAge) {
		this.workAge = workAge;
	}
}
