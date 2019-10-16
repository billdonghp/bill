package com.ait.ess.bean;

import java.util.ArrayList;

public class EssMatchBean {

	private int matchNo;				//申请序号

	private String empID;				//员工号

	private String chineseName;			//员工姓名

	private String deptName;			//部门名称

	private String matchDate;			//值班日期

	private String matchFromTime;		//值班开始时间

	private String matchToTime;			//值班結束時間

	private int activity;				//人事確認結果

	private int opFlag = -1;			//可操作状态 -1 不可操作; 0 可通过/否决; 1 可通过; 2 可否决

	private String createDate;			//申請時間

	private String createdBy;			//申請人

	private String matchTypeCode;		//值班类型代码

	private String matchTypeName;		//值班类型名称

	private ArrayList AffirmorList;		//决裁者列表
	
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public ArrayList getAffirmorList() {
		return AffirmorList;
	}

	public void setAffirmorList(ArrayList affirmorList) {
		AffirmorList = affirmorList;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchToTime() {
		return matchToTime;
	}

	public void setMatchToTime(String matchToTime) {
		this.matchToTime = matchToTime;
	}

	public String getMatchFromTime() {
		return matchFromTime;
	}

	public void setMatchFromTime(String matchFromTime) {
		this.matchFromTime = matchFromTime;
	}

	public int getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	public String getMatchTypeCode() {
		return matchTypeCode;
	}

	public void setMatchTypeCode(String matchTypeCode) {
		this.matchTypeCode = matchTypeCode;
	}

	public String getMatchTypeName() {
		return matchTypeName;
	}

	public void setMatchTypeName(String matchTypeName) {
		this.matchTypeName = matchTypeName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(int opFlag) {
		this.opFlag = opFlag;
	}
}
