package com.ait.ess.entity;

public class OvertimeView {
	private String from_time;
	private String to_time;
	private String type;
	private String empid;
	private String ar_date_str;
	public String getAr_date_str() {
		return ar_date_str;
	}
	public void setAr_date_str(String ar_date_str) {
		this.ar_date_str = ar_date_str;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getFrom_time() {
		return from_time;
	}
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	public String getTo_time() {
		return to_time;
	}
	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
