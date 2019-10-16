package com.ait.ar.bean;

import java.util.List;

public class ArDate {
	private String code;

	private String codeName;

	private String describe;

	private String fromDate;

	private String toDate;

	private int starDate;

	private int endDate;

	private int statNo;
	
	private String cpnyId;
	
	private String cpnyName;
	
	private List<String> deptList ;
	
	private String doorTypeFlag ; 
	
	public ArDate() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCpnyId() {
		return cpnyId;
	}

	public void setCpnyId(String cpnyId) {
		this.cpnyId = cpnyId;
	}

	public String getCpnyName() {
		return cpnyName;
	}

	public void setCpnyName(String cpnyName) {
		this.cpnyName = cpnyName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getStarDate() {
		return starDate;
	}

	public void setStarDate(int starDate) {
		this.starDate = starDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public int getStatNo() {
		return statNo;
	}

	public void setStatNo(int statNo) {
		this.statNo = statNo;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptiList) {
		this.deptList = deptiList;
	}

	public String getDoorTypeFlag() {
		return doorTypeFlag;
	}

	public void setDoorTypeFlag(String doorTypeFlag) {
		this.doorTypeFlag = doorTypeFlag;
	}

}
