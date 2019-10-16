package com.ait.ev.bean;

import java.util.ArrayList;


public class EvaluateAffirm {
	
	private String deptId;
	
	private String position;
	
	private String affirmorId;
	
	private int level;
	
	private String affirmorName;
	
	private String deptName;
	
	private String positionName;
	
	private String fourthDept;
	
	private ArrayList AffirmorList;

	public ArrayList getAffirmorList() {
		return AffirmorList;
	}

	public void setAffirmorList(ArrayList affirmorList) {
		AffirmorList = affirmorList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAffirmorId() {
		return affirmorId;
	}

	public void setAffirmorId(String affirmorId) {
		this.affirmorId = affirmorId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getFourthDept() {
		return fourthDept;
	}

	public void setFourthDept(String fourthDept) {
		this.fourthDept = fourthDept;
	}

	public String getAffirmorName() {
		return affirmorName;
	}

	public void setAffirmorName(String affirmorName) {
		this.affirmorName = affirmorName;
	}
	
	
	
	
}