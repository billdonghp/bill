package com.ait.ga.bean;

import java.sql.Date;

public class WasteBean {
	private int WASTEUNITPRICE_ID ;
	private String USER_ID ;
	private String USER_NAME ;
	private String USER_DEPT ;
	private String WASTE_ID ;
	private String WASTE_NAME ;
	private String WASTE_ATPRESENTUNITPRICE ;
	private String WASTE_APPLYUNITPRICE ;
	private String WASTE_UNITPRICEOKDATE ;
	private Date WASTE_APPLYDATE ;
	private String REMARK ;
	private String ACTIVE ;
	public String getACTIVE() {
		return ACTIVE;
	}
	public void setACTIVE(String active) {
		ACTIVE = active;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getUSER_DEPT() {
		return USER_DEPT;
	}
	public void setUSER_DEPT(String user_dept) {
		USER_DEPT = user_dept;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String user_name) {
		USER_NAME = user_name;
	}
	public Date getWASTE_APPLYDATE() {
		return WASTE_APPLYDATE;
	}
	public void setWASTE_APPLYDATE(Date waste_applydate) {
		WASTE_APPLYDATE = waste_applydate;
	}
	public String getWASTE_APPLYUNITPRICE() {
		return WASTE_APPLYUNITPRICE;
	}
	public void setWASTE_APPLYUNITPRICE(String waste_applyunitprice) {
		WASTE_APPLYUNITPRICE = waste_applyunitprice;
	}
	public String getWASTE_ATPRESENTUNITPRICE() {
		return WASTE_ATPRESENTUNITPRICE;
	}
	public void setWASTE_ATPRESENTUNITPRICE(String waste_atpresentunitprice) {
		WASTE_ATPRESENTUNITPRICE = waste_atpresentunitprice;
	}
	public String getWASTE_NAME() {
		return WASTE_NAME;
	}
	public void setWASTE_NAME(String waste_name) {
		WASTE_NAME = waste_name;
	}
	public String getWASTE_UNITPRICEOKDATE() {
		return WASTE_UNITPRICEOKDATE;
	}
	public void setWASTE_UNITPRICEOKDATE(String waste_unitpriceokdate) {
		WASTE_UNITPRICEOKDATE = waste_unitpriceokdate;
	}
	public int getWASTEUNITPRICE_ID() {
		return WASTEUNITPRICE_ID;
	}
	public void setWASTEUNITPRICE_ID(int wasteunitprice_id) {
		WASTEUNITPRICE_ID = wasteunitprice_id;
	}
	public String getWASTE_ID() {
		return WASTE_ID;
	}
	public void setWASTE_ID(String waste_id) {
		WASTE_ID = waste_id;
	}
}
