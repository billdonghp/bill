package com.ait.hrm.bean;

public class InfoTable {
  private String tableId;
  private String tableName;
  private String enTableName;
  private String korTableName;
  private String infoType;
  public InfoTable() {
  }
  public String getTableId() {
    return tableId;
  }
  public void setTableId(String tableId) {
    this.tableId = tableId;
  }
  public String getTableName() {
    return tableName;
  }
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
  public String getInfoType() {
    return infoType;
  }
  public void setInfoType(String infoType) {
    this.infoType = infoType;
  }
public String getEnTableName() {
	return enTableName;
}
public void setEnTableName(String enTableName) {
	this.enTableName = enTableName;
}
public String getKorTableName() {
	return korTableName;
}
public void setKorTableName(String korTableName) {
	this.korTableName = korTableName;
}
}
