/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-7-13
 */
package com.ait.ess.bean;
/**
 * 决裁方法的参数对象(决裁、申请信息更新)
 * @version 1.0
 */
public class EssAffirmParam {
 
	private int affirmNo;	    	//记录序号
	private int applyNo;         //申请序号
	private String fromDate;     //开始日期 yyyy-MM-dd
	private String fromTime;     //开始时间 hh:mm
	private String toDate;       //结束日期 yyyy-MM-dd
	private String toTime;       //结束时间 hh:mm
	private String deductTime;   //扣除时间
	private double otLength;	 //加班长度
	private String remark;       //备注
	private String person_id;
	
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public int getAffirmNo() {
		return affirmNo;
	}
	public void setAffirmNo(int affirmNo) {
		this.affirmNo = affirmNo;
	}
	public String getDeductTime() {
		return deductTime;
	}
	public void setDeductTime(String deductTime) {
		this.deductTime = deductTime;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public int getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
	}
	public double getOtLength() {
		return otLength;
	}
	public void setOtLength(double otLength) {
		this.otLength = otLength;
	}

}
