package com.ait.hrm.entity;

public class EndContractReport {
	private String deptId ;
	private String deptName ;
	private int fst_season_now ;
	private int scd_season_now ;
	private int thd_season_now ;
	private int fth_season_now ;
	private int fst_season_next ;
	private int scd_season_next ;
	private int thd_season_next ;
	private int fth_season_next ;
	private int after_next_year ;
	private int total ;
	
	public EndContractReport(){}
	
	public int getAfter_next_year() {
		return after_next_year;
	}
	public void setAfter_next_year(int after_next_year) {
		this.after_next_year = after_next_year;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getFst_season_next() {
		return fst_season_next;
	}
	public void setFst_season_next(int fst_season_next) {
		this.fst_season_next = fst_season_next;
	}
	public int getFst_season_now() {
		return fst_season_now;
	}
	public void setFst_season_now(int fst_season_now) {
		this.fst_season_now = fst_season_now;
	}
	public int getFth_season_next() {
		return fth_season_next;
	}
	public void setFth_season_next(int fth_season_next) {
		this.fth_season_next = fth_season_next;
	}
	public int getFth_season_now() {
		return fth_season_now;
	}
	public void setFth_season_now(int fth_season_now) {
		this.fth_season_now = fth_season_now;
	}
	public int getScd_season_next() {
		return scd_season_next;
	}
	public void setScd_season_next(int scd_season_next) {
		this.scd_season_next = scd_season_next;
	}
	public int getScd_season_now() {
		return scd_season_now;
	}
	public void setScd_season_now(int scd_season_now) {
		this.scd_season_now = scd_season_now;
	}
	public int getThd_season_next() {
		return thd_season_next;
	}
	public void setThd_season_next(int thd_season_next) {
		this.thd_season_next = thd_season_next;
	}
	public int getThd_season_now() {
		return thd_season_now;
	}
	public void setThd_season_now(int thd_season_now) {
		this.thd_season_now = thd_season_now;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
