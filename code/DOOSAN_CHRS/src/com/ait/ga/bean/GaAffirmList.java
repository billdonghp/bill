package com.ait.ga.bean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */

public class GaAffirmList {
	private Integer affirmNo; //记录序号

	private String applyType; //申请类型 OtApply/LeaveApply/MatchApply

	private Integer applyNo; //申请记录序号
	private String applyNoFa; //维修申请记录序号


	private String affirmorId; //决裁者工号

	private String affirmorName; //决裁者姓名	

	private String affirmorDuty; //决裁者职责
	
	private Integer affirmLevel; //决裁级别
	
	private Integer affirmOldLevel; //原来决裁级别(决裁管理里面的决裁等级)

	private Integer affirmFlag; //决裁情况 0-未决裁,1-已通过,2-已否决

	private String createdBy;

	private String affirmObjectid; //被决裁者工号

	private String affirmObjectname; //被决裁者姓名	

	

	public String getApplyNoFa() {
		return applyNoFa;
	}

	public void setApplyNoFa(String applyNoFa) {
		this.applyNoFa = applyNoFa;
	}
	
	public Integer getAffirmFlag() {
		return affirmFlag;
	}

	public void setAffirmFlag(Integer affirmFlag) {
		this.affirmFlag = affirmFlag;
	}

	public Integer getAffirmLevel() {
		return affirmLevel;
	}

	public void setAffirmLevel(Integer affirmLevel) {
		this.affirmLevel = affirmLevel;
	}

	public Integer getAffirmNo() {
		return affirmNo;
	}

	public void setAffirmNo(Integer affirmNo) {
		this.affirmNo = affirmNo;
	}

	public String getAffirmObjectid() {
		return affirmObjectid;
	}

	public void setAffirmObjectid(String affirmObjectid) {
		this.affirmObjectid = affirmObjectid;
	}

	public String getAffirmObjectname() {
		return affirmObjectname;
	}

	public void setAffirmObjectname(String affirmObjectname) {
		this.affirmObjectname = affirmObjectname;
	}

	public String getAffirmorId() {
		return affirmorId;
	}

	public void setAffirmorId(String affirmorId) {
		this.affirmorId = affirmorId;
	}

	public String getAffirmorName() {
		return affirmorName;
	}

	public void setAffirmorName(String affirmorName) {
		this.affirmorName = affirmorName;
	}

	public String getAffirmorDuty() {
		return affirmorDuty;
	}

	public void setAffirmorDuty(String affirmorDuty) {
		this.affirmorDuty = affirmorDuty;
	}

	public Integer getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getAffirmOldLevel() {
		return affirmOldLevel;
	}

	public void setAffirmOldLevel(Integer affirmOldLevel) {
		this.affirmOldLevel = affirmOldLevel;
	}

}
