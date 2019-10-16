package com.ait.sy.sy0106.bean;

public class Ent {
  public Ent() {
  }
  public int grantEmpNo = 0;
  public String cnpyID;
  public String empID;
  public String decTypeID;
  public String decLevelID;
  public String createDate;
  public String creatorID;
  public String modifyDate;
  public String modifierID;
  public int activity = 0;
  public int orderNo = 0;
  public int decFlag = 0;

  public int getGrantEmpNo() {
    return this.grantEmpNo;
  }

  public void setGrantEmpNo(int grantEmpNo) {
    this.grantEmpNo =grantEmpNo;
  }

  public String getCnpyID() {
    return this.editNull(cnpyID);
  }

  public void setCnpyID(String cnpyID) {
    this.cnpyID = cnpyID;
  }

  public String getEmpID() {
    return this.editNull(empID);
  }


  public void setEmpID(String empID) {
    this.empID = empID;
  }

  public String getDecTypeID() {
    return this.editNull(decTypeID);
  }

  public void setDecTypeID(String decTypeID) {
    this.decTypeID = decTypeID;
  }

  public String getDecLevelID() {
    return this.editNull(decLevelID);
  }

  public void setDecLevelID(String decLevelID) {
    this.decLevelID = decLevelID;
  }

  public String getCreateDate() {
    return this.editNull(createDate);
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getCreatorID() {
    return this.editNull(creatorID);
  }

  public void setCreatorID(String creatorID) {
    this.creatorID = creatorID;
  }

  public String getModifyDate() {
    return this.editNull(modifyDate);
  }

  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String getModifierID() {
    return this.editNull(modifierID);
  }

  public void setModifierID(String modifierID) {
    this.modifierID = modifierID;
  }

  public int getActivity() {
    return this.activity;
  }

  public void setActivity(int activity) {
    this.activity = activity;
  }

  public int getOrderNo() {
    return this.orderNo;
  }

  public void setOrderNo(int orderNo) {
    this.orderNo = orderNo;
  }

  public int getDecFlag() {
    return this.decFlag;
  }

  public void setDecFlag(int decFlag) {
    this.decFlag = decFlag;
  }

  public String editNull(String s) {
    if (s == null || s.equals("")) {
      return "";
    }
    else {
      return s;
    }
  }

}
