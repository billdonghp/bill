package com.ait.hrm.bean;
public class InfoField {
	  private String fieldId;
	  private String enFieldId;
	  private String fieldName;
	  private String enFieldName;
	  private String korFieldName;
	  private String tableId;
	  private String fieldCode;
	  private String codeName;
	  /**
	 * @return codeName
	 */
	public String getCodeName() {
		return codeName;
	}
	/**
	 * @param codeName 要设置的 codeName
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	/**
	 * @return fieldCode
	 */
	public String getFieldCode() {
		return fieldCode;
	}
	/**
	 * @param fieldCode 要设置的 fieldCode
	 */
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public InfoField() {
	  }
	  public String getFieldId() {
	    return fieldId;
	  }
	  public void setFieldId(String fieldId) {
	    this.fieldId = fieldId;
	  }
	  public String getFieldName() {
	    return fieldName;
	  }
	  public void setFieldName(String fieldName) {
	    this.fieldName = fieldName;
	  }
	  public String getTableId() {
	    return tableId;
	  }
	  public void setTableId(String tableId) {
	    this.tableId = tableId;
	  }
	public String getEnFieldName() {
		return enFieldName;
	}
	public void setEnFieldName(String enFieldName) {
		this.enFieldName = enFieldName;
	}
	public String getKorFieldName() {
		return korFieldName;
	}
	public void setKorFieldName(String korFieldName) {
		this.korFieldName = korFieldName;
	}
	public String getEnFieldId() {
		return enFieldId;
	}
	public void setEnFieldId(String enFieldId) {
		this.enFieldId = enFieldId;
	}
	}
