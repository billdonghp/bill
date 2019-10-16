package com.ait.hrm.bean;

public class Probation {
  private String empID;
  private String department;
  private String chineseName;
  private String startDate;
  private String endProbationDate;
  private String deptID;
  private String content;
  private float probationMark;
  private int activity;
  private String scheduleProbationDate;
  private int expiredDays;
  private String createBy;
  private String updateBy;

  public String getEmpID() {
    return empID;
  }
  public void setEmpID(String empID) {
    this.empID = empID;
  }
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public String getChineseName() {
    return chineseName;
  }
  public void setChineseName(String chineseName) {
    this.chineseName = chineseName;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndProbationDate() {
    return endProbationDate;
  }
  public void setEndProbationDate(String endProbationDate) {
    this.endProbationDate = endProbationDate;
  }
  public String getDeptID() {
    return deptID;
  }
  public void setDeptID(String deptID) {
    this.deptID = deptID;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public float getProbationMark() {
    return probationMark;
  }
  public void setProbationMark(float probationMark) {
    this.probationMark = probationMark;
  }
  public int getActivity() {
    return activity;
  }
  public void setActivity(int activity) {
    this.activity = activity;
  }
  public String getScheduleProbationDate() {
    return scheduleProbationDate;
  }
  public void setScheduleProbationDate(String scheduleProbationDate) {
    this.scheduleProbationDate = scheduleProbationDate;
  }
  public int getExpiredDays() {
    return expiredDays;
  }
  public void setExpiredDays(int expiredDays) {
    this.expiredDays = expiredDays;
  }
public String getCreateBy() {
	return createBy;
}
public void setCreateBy(String createBy) {
	this.createBy = createBy;
}
public String getUpdateBy() {
	return updateBy;
}
public void setUpdateBy(String updateBy) {
	this.updateBy = updateBy;
}

}
