package com.ait.ess.bean;

public class EssAssessor {
  private int assessNo;
  private String assessorId;
  private int activity;
  private String createDate;
  private String createdBy;
  private String updateDate;
  private String updatedBy;
  public EssAssessor() {
  }
  public int getActivity() {
    return activity;
  }
  public void setActivity(int activity) {
    this.activity = activity;
  }
  public void setAssessNo(int assessNo) {
    this.assessNo = assessNo;
  }
  public void setAssessorId(String assessorId) {
    this.assessorId = assessorId;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  public String getUpdatedBy() {
    return updatedBy;
  }
  public String getUpdateDate() {
    return updateDate;
  }
  public String getCreatedBy() {
    return createdBy;
  }
  public String getCreateDate() {
    return createDate;
  }
  public String getAssessorId() {
    return assessorId;
  }
  public int getAssessNo() {
    return assessNo;
  }
}
