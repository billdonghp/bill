package com.ait.ar.bean;

public class DynamicGroup {
  private int groupNo;
  private String groupName;
  private String groupEnName;
  private String groupKoName;
  private String description;
  private String createDate;
  private String createdBy;
  private String updateDate;
  private String updatedBy;
  private String groupProperty;
  private String groupPropertyName;
  private String groupQualification;
  private String groupQualificationName;
  private String sysGroupType;
  private String sysGroupTypeName;
  private String cpny_id;

  
public String getCpny_id() {
	return cpny_id;
}

public void setCpny_id(String cpny_id) {
	this.cpny_id = cpny_id;
}

public String getGroupEnName() {
	return groupEnName;
}

public void setGroupEnName(String groupEnName) {
	this.groupEnName = groupEnName;
}

public String getGroupKoName() {
	return groupKoName;
}

public void setGroupKoName(String groupKoName) {
	this.groupKoName = groupKoName;
}
public DynamicGroup() {
  }
  public String getCreateDate() {
    return createDate;
  }
  public String getCreatedBy() {
    return createdBy;
  }
  public String getDescription() {
    return description;
  }
  public String getGroupName() {
    return groupName;
  }
  public int getGroupNo() {
    return groupNo;
  }
  public String getUpdatedBy() {
    return updatedBy;
  }
  public String getUpdateDate() {
    return updateDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
  public void setGroupNo(int groupNo) {
    this.groupNo = groupNo;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  public String getGroupProperty() {
    return groupProperty;
  }
  public void setGroupProperty(String groupProperty) {
    this.groupProperty = groupProperty;
  }
  public String getGroupPropertyName() {
    return groupPropertyName;
  }
  public void setGroupPropertyName(String groupPropertyName) {
    this.groupPropertyName = groupPropertyName;
  }
  public String getGroupQualification() {
    return groupQualification;
  }
  public void setGroupQualification(String groupQualification) {
    this.groupQualification = groupQualification;
  }
  public String getGroupQualificationName() {
    return groupQualificationName;
  }
  public void setGroupQualificationName(String groupQualificationName) {
    this.groupQualificationName = groupQualificationName;
  }
  public String getSysGroupType() {
    return sysGroupType;
  }
  public void setSysGroupType(String sysGroupType) {
    this.sysGroupType = sysGroupType;
  }
  public String getSysGroupTypeName() {
    return sysGroupTypeName;
  }
  public void setSysGroupTypeName(String sysGroupTypeName) {
    this.sysGroupTypeName = sysGroupTypeName;
  }
}
