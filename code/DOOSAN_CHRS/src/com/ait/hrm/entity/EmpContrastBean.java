/*
 * @(#)EmpContrastBean.java 1.0 2007-10-8 下午05:04:56
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.entity;


public class EmpContrastBean {
	
	private String prevMonthCount;
	private String thisMonthIn;
	private String thisMonthOut;
	private String thisMonthCount;
	private String diff;
	
	public String getDiff() {
		return diff;
	}
	
	public void setDiff(String diff) {
		this.diff = diff;
	}
	
	public String getPrevMonthCount() {
		return prevMonthCount;
	}
	
	public void setPrevMonthCount(String prevMonthCount) {
		this.prevMonthCount = prevMonthCount;
	}
	
	public String getThisMonthCount() {
		return thisMonthCount;
	}
	
	public void setThisMonthCount(String thisMonthCount) {
		this.thisMonthCount = thisMonthCount;
	}
	
	public String getThisMonthIn() {
		return thisMonthIn;
	}
	
	public void setThisMonthIn(String thisMonthIn) {
		this.thisMonthIn = thisMonthIn;
	}
	
	public String getThisMonthOut() {
		return thisMonthOut;
	}
	
	public void setThisMonthOut(String thisMonthOut) {
		this.thisMonthOut = thisMonthOut;
	}
}

