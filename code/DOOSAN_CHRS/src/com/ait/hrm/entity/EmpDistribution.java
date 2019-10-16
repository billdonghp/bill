/*
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.entity;

import java.util.Vector;


public class EmpDistribution {
	
	private String malecount;
	private String femalecount;
	private String count;
	private Vector datalist;
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public Vector getDatalist() {
		return datalist;
	}
	
	public void setDatalist(Vector datalist) {
		this.datalist = datalist;
	}
	
	public String getFemalecount() {
		return femalecount;
	}
	
	public void setFemalecount(String femalecount) {
		this.femalecount = femalecount;
	}
	
	public String getMalecount() {
		return malecount;
	}
	
	public void setMalecount(String malecount) {
		this.malecount = malecount;
	}
	
	
}

