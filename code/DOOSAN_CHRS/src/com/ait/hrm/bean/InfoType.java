package com.ait.hrm.bean;

public class InfoType {
  private String typeId;
  private String typeName;
  private String enTypeName;
  private String korTypeName;
  public String getEnTypeName() {
	return enTypeName;
}
public void setEnTypeName(String enTypeName) {
	this.enTypeName = enTypeName;
}
public String getKorTypeName() {
	return korTypeName;
}
public void setKorTypeName(String korTypeName) {
	this.korTypeName = korTypeName;
}
public InfoType() {
  }
  public String getTypeId() {
    return typeId;
  }
  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }
  public String getTypeName() {
    return typeName;
  }
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
