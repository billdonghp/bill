package com.ait.hrm.bean;

public class Department {

	private String deptID;

	private String deptName;

	private String deptEnName;
	
	private String korDept;

	private String companyID;

	private String parentDeptID;

	private String createdBy;

	private String createDate;

	private String updatedBy;

	private String updateDate;

	private String createdDate;

	private Integer orderNo;

	private Integer activity;

	private Integer deptLevel;

	public Department() {
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptID() {
		return deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getParentDeptID() {
		return parentDeptID;
	}

	public void setParentDeptID(String parentDeptID) {
		this.parentDeptID = parentDeptID;
	}

	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public String getDeptEnName() {
		return deptEnName;
	}

	public void setDeptEnName(String deptEnName) {
		this.deptEnName = deptEnName;
	}

	public String getKorDept() {
		return korDept;
	}

	public void setKorDept(String korDept) {
		this.korDept = korDept;
	}

}
