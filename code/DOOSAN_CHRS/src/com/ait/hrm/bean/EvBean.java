/*
 * @(#)EvBean.java 1.0 2007-9-26 上午09:57:22
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.bean;


public class EvBean {
	
	private String empID;
	private String firOpinion;
	private String secMark;
	private String secOpinion;
	private String evMark;
	private String evGrade;                
	private String evType;  
	private String firMark;
	private String evPeriod;
	private String ev_period_id;
	
	
	public String getEv_period_id() {
		return ev_period_id;
	}

	
	public void setEv_period_id(String ev_period_id) {
		this.ev_period_id = ev_period_id;
	}

	public String getEmpID() {
		return empID;
	}
	
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	
	public String getEvGrade() {
		return evGrade;
	}
	
	public void setEvGrade(String evGrade) {
		this.evGrade = evGrade;
	}
	
	public String getEvMark() {
		return evMark;
	}
	
	public void setEvMark(String evMark) {
		this.evMark = evMark;
	}
	
	public String getEvType() {
		return evType;
	}
	
	public void setEvType(String evType) {
		this.evType = evType;
	}
	
	public String getFirMark() {
		return firMark;
	}
	
	public void setFirMark(String firMark) {
		this.firMark = firMark;
	}
	
	public String getFirOpinion() {
		return firOpinion;
	}
	
	public void setFirOpinion(String firOpinion) {
		this.firOpinion = firOpinion;
	}
	
	public String getSecMark() {
		return secMark;
	}
	
	public void setSecMark(String secMark) {
		this.secMark = secMark;
	}
	
	public String getSecOpinion() {
		return secOpinion;
	}
	
	public void setSecOpinion(String secOpinion) {
		this.secOpinion = secOpinion;
	}

	
	public String getEvPeriod() {
		return evPeriod;
	}

	
	public void setEvPeriod(String evPeriod) {
		this.evPeriod = evPeriod;
	}
	
}

