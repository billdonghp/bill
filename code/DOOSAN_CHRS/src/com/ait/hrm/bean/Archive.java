package com.ait.hrm.bean;

public class Archive {
  private String empID;
  private String chineseName;
  private String deptID;
  private String department;
  private int fileEmpNo;
  private String filePositionID;
  private String fileDate;
  private String fileStatusCode;
  private String fileStatus;
  private String createDate;
  private String createdBy;
  private String updateDate;
  private String updatedby;
  private int orderNo;
  private int activity;
  private String fileLocatedInstitute;
  private String comments;
  private String fileNo;
  public Archive() {
  }
  public String getFileNo() {
    return fileNo;
  }
  public void setFileNo(String fileNo) {
    this.fileNo = fileNo;
  }
  public String getEmpID() {
    return empID;
  }
  public void setEmpID(String empID) {
    this.empID = empID;
  }
  public String getChineseName() {
    return chineseName;
  }
  public void setChineseName(String chineseName) {
    this.chineseName = chineseName;
  }
  public String getDeptID() {
    return deptID;
  }
  public void setDeptID(String deptID) {
    this.deptID = deptID;
  }
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public int getFileEmpNo() {
    return fileEmpNo;
  }
  public void setFileEmpNo(int fileEmpNo) {
    this.fileEmpNo = fileEmpNo;
  }
  public String getFilePositionID() {
    return filePositionID;
  }
  public void setFilePositionID(String filePositionID) {
    this.filePositionID = filePositionID;
  }
  public String getFileDate() {
    return fileDate;
  }
  public void setFileDate(String fileDate) {
    this.fileDate = fileDate;
  }
  public String getFileStatusCode() {
    return fileStatusCode;
  }
  public void setFileStatusCode(String fileStatusCode) {
    this.fileStatusCode = fileStatusCode;
  }
  public String getFileStatus() {
    return fileStatus;
  }
  public void setFileStatus(String fileStatus) {
    this.fileStatus = fileStatus;
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
  public String getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
  public String getUpdatedby() {
    return updatedby;
  }
  public void setUpdatedby(String updatedby) {
    this.updatedby = updatedby;
  }
  public int getOrderNo() {
    return orderNo;
  }
  public void setOrderNo(int orderNo) {
    this.orderNo = orderNo;
  }
  public int getActivity() {
    return activity;
  }
  public void setActivity(int activity) {
    this.activity = activity;
  }
  public String getFileLocatedInstitute() {
    return fileLocatedInstitute;
  }
  public void setFileLocatedInstitute(String fileLocatedInstitute) {
    this.fileLocatedInstitute = fileLocatedInstitute;
  }
  public String getComments() {
    return comments;
  }
  public void setComments(String comments) {
    this.comments = comments;
  }
}
