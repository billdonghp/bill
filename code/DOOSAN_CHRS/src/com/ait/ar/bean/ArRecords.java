package com.ait.ar.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArRecords {
	private int recordNo;

	private String empid;

	private String enterTime;

	private String outTime;

	private String lock;

	private String insert_by;

	private String state;

	private String update_date;

	private String update_by;

	private String state_js;

	private String insert_by_js;

	private String lock_js;
	
	private String operatorId;
	
	private Date insertTime;
	
	private String d_time;//标记手工修改前机器录入的时间
	
	private String remark;//备注
	
	private String active;//标志单条考勤数据在考勤汇总的时候是否有效
	
	//add by lvhongbin at 2006-01-11
	
	private String empName;
	private String deptId;
	private String deptName;
	private String dynamicGroupId;
	private String dynamicGroupName;
	private String chinese_pinyin;
	private String state_name;
	
	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getChinese_pinyin() {
		return chinese_pinyin;
	}

	public void setChinese_pinyin(String chinese_pinyin) {
		this.chinese_pinyin = chinese_pinyin;
	}

	public void setEmpName(String a_name){
		empName = a_name;
	}
	
	public String getEmpName(){
		return empName;
	}
	
	public String getEmpNameToUnicode(){
		return empName;
	}
	
	public void setDeptId(String a_deptId){
		deptId = a_deptId;
	}
	
	public String getDeptId(){
		return deptId;
	}
	
	public void setDeptName(String a_deptName){
		deptName = a_deptName;
	}
	
	public String getDeptName(){
		return deptName;
	}
	
	public void setDynamicGroupId(String a_dynamicGroupId){
		dynamicGroupId = a_dynamicGroupId;
	}
	
	public String getDynamicGroupId(){
		return dynamicGroupId;
	}
	
	public void setDynamicGroupName(String a_dynamicGroupName){
		dynamicGroupName = a_dynamicGroupName;
	}
	
	public String getDynamicGroupName(){
		return dynamicGroupName;
	}
	//add end

	public ArRecords() {
	}

	public int getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(int a_recordNo) {
		recordNo = a_recordNo;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String a_empid) {
		empid = a_empid;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String a_enterTime) {
		//enterTime = a_enterTime.substring(0, enterTime.lastIndexOf("."));
		enterTime = a_enterTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String a_outTime) {
		//outTime = a_outTime.substring(0, outTime.lastIndexOf("."));
		outTime = a_outTime;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String a_lock) {
		lock = a_lock;
	}

	public String getInsert_by() {
		return insert_by;
	}

	public void setInsert_by(String a_insert_by) {
		insert_by = a_insert_by;
	}

	public String getState() {
		return state;
	}

	public void setState(String a_state) {
		state = a_state;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String a_update_date) {
		update_date = a_update_date;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String a_update_by) {
		update_by = a_update_by;
	}

	public String getState_js() {
		return state_js;
	}

	public void setState_js(String a_state_js) {
		if (a_state_js.equals("S")){
			state_js = "异常";
		}else if (a_state_js.equals("N")){
			state_js = "正常";
		}else{
			state_js = a_state_js;
		}
	}

	public String getInsert_by_js() {
		return insert_by_js;
	}

	public void setInsert_by_js(String a_insert_by_js) {
		if (a_insert_by_js.equals(("M"))) {
			insert_by_js = "机器输入";
		} else if (a_insert_by_js.equals("H")) {
			insert_by_js = "手工输入";
		} else if (a_insert_by_js.equals("HM")) {
			insert_by_js = "手工修改";
		}else{
			insert_by_js = a_insert_by_js;
		}
	}

	public String getLock_js() {
		return lock_js;
	}

	public void setLock_js(String a_lock_js) {
		//if (a_lock_js.equals("Y"))
		//	lock_js = "是";
		//else if (a_lock_js.equals("N")) {
		//	lock_js = "否";
		//}
		lock_js = a_lock_js;
	}
	
	public void setOperatorId(String a_operatorId){
		operatorId = a_operatorId;
	}
	
	public String getOperatorId(){
		return operatorId;
	}
	
	public void setInsertTime(Date a_insertTime){
		insertTime = a_insertTime;
	}
	
	public Date getInsertTime(){
		return insertTime;
	}
	
	public String getInsertTimeStr(String timeFormat){
		return new SimpleDateFormat(timeFormat).format(insertTime);
	}
	
	public String getInsertTimeStr(){
		return getInsertTimeStr("yyyy-MM-dd HH:mm:SS");
	}
	
	public void setActive(String a_active){
		active = a_active;
	}
	
	public String getActive(){
		return active;
	}

		
	public String getD_time() {
		return d_time;
	}

	public void setD_time(String d_time) {
		this.d_time = d_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
