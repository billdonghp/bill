package com.ait.ess.bean;

public class EssAssessRelation {
  private int assessNo;
  private String empID;
  private String chinesename;
  private String assessorId;
  private String assessorName;
  public EssAssessRelation() {
  }
  public int getAssessNo() {
    return assessNo;
  }
  public String getAssessorId() {
    return assessorId;
  }
  public String getAssessorName() {
    return assessorName;
  }
  public String getChinesename() {
    return chinesename;
  }
  public String getEmpID() {
    return empID;
  }
  public void setAssessNo(int assessNo) {
    this.assessNo = assessNo;
  }
  public void setAssessorId(String assessorId) {
    this.assessorId = assessorId;
  }
  public void setAssessorName(String assessorName) {
    this.assessorName = assessorName;
  }
  public void setChinesename(String chinesename) {
    this.chinesename = chinesename;
  }
  public void setEmpID(String empID) {
    this.empID = empID;
  }
}
