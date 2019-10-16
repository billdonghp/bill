package com.ait.ess.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * modify by lvhongbin at 2005-11-07
 * */

public class LeaveApply {
	private String empid;
  private String leavePerson;
  private String leaveTypeCode;
  private String leaveApplyDate;
  private String leaveDays;
  private String leaveApplyStartDate;
  private String leaveApplyEndDate;
  private String lastYearDates;
  private String leaveApplyNo;
  private String leaveContent;
  private String leaveTypeCodeName;
  private String leavePersonName;
  private int activity;
  private String updatedBy;
  private Date updateDate;
  private String createdBy;
  private Date createDate;
  private String person_id;
  
  private List affirmStatusList;
  
  private String format = "yyyy-MM-dd HH:mm:ss.SSS";
  
  public LeaveApply() {
	  //constructor
  }
  
  public String getEmpID()
  {
	  return empid;
  }
  public void setEmpID(String empid)
  {
	 this.empid = empid;
  }
  
  //leavePerson
  public String getLeavePerson() {
    return leavePerson;
  }
  public void setLeavePerson(String a_leavePerson) {
    leavePerson = a_leavePerson;
  }
  
  //leave Type Code	
  public String getLeaveTypeCode() {
    return leaveTypeCode;
  }
  
  public void setLeaveTypeCode(String a_leaveTypeCode) {
    leaveTypeCode = a_leaveTypeCode;
  }
  
  //leave Apply Date
  public void setLeaveApplyDate(String date) {
	    leaveApplyDate = date;
  }
  
  public String getLeaveApplyDate() {
    return leaveApplyDate;
  }
  

  
  //leave Days
  public String getLeaveDays() {
    return leaveDays;
  }
  
  public void setLeaveDays(String a_leaveDays) {
    leaveDays = a_leaveDays;
  }
  
  //leave Apply Start Date
  public void setLeaveApplyStartDate(String date) {
	    leaveApplyStartDate = date;
  }
  
  public String getLeaveApplyStartDate() {
    return leaveApplyStartDate;
  }
  
  public String getLeaveApplyStartDateStr(String dateFormat){
	  return new SimpleDateFormat(dateFormat)
	  					.format(leaveApplyStartDate);
  }
  
  public String getLeaveApplyStartDateStr(){
	  return getLeaveApplyStartDateStr(format);
  }

  
  //leave Apply EndDate
  public void setLeaveApplyEndDate(String date) {
	    leaveApplyEndDate = date;
  }
  
  public String getLeaveApplyEndDate() {
    return leaveApplyEndDate;
  }
  
  public String getLeaveApplyEndDate(String dateFormat){
	  return new SimpleDateFormat(dateFormat).format(leaveApplyEndDate);
  }
  
  public String getLeaveApplyEndDateStr(){
	  return getLeaveApplyEndDate(format);
  }
  
  //last Year Dates
  public String getLastYearDates() {
    return lastYearDates;
  }
  
  public void setLastYearDates(String a_lastYearDates) {
    lastYearDates = a_lastYearDates;
  }
  
  //leave Apply No
  public String getLeaveApplyNo() {
    return leaveApplyNo;
  }
  
  public void setLeaveApplyNo(String a_leaveApplyNo) {
    leaveApplyNo = a_leaveApplyNo;
  }
  
  //leave Content
  public String getLeaveContent() {
    return leaveContent;
  }
  
  public void setLeaveContent(String a_leaveContent) {
    leaveContent = a_leaveContent;
  }
  
  //leave Type Code Name
  public String getLeaveTypeCodeName() {
    return leaveTypeCodeName;
  }
  
  public void setLeaveTypeCodeName(String a_leaveTypeCodeName) {
    leaveTypeCodeName = a_leaveTypeCodeName;
  }
  
  //leave Person Name
  public String getLeavePersonName() {
    return leavePersonName;
  }
  
  public void setLeavePersonName(String a_leavePersonName) {
    leavePersonName = a_leavePersonName;
  }
  
  //activity
  public int getActivity() {
    return activity;
  }
  
  public void setActivity(int a_activity) {
    activity = a_activity;
  }
  
  //updated By
  public String getUpdatedBy() {
    return updatedBy;
  }
  
  public void setUpdatedBy(String a_updatedBy) {
    updatedBy = a_updatedBy;
  }
  
  //update Date
  public void setUpdateDate(Date date){
	  updateDate = date;
  }
  
  public Date getUpdateDate(){
	  return updateDate;
  }
  
  public String getUpdateDateStr(String dateFormat) {
    return new SimpleDateFormat(dateFormat).format(updateDate);
  }
  
  public String getUpdateDateStr(){
	  return getUpdateDateStr(format);
  }
  
  //created By
  public String getCreatedBy() {
    return createdBy;
  }
  
  public void setCreatedBy(String a_createdBy) {
    createdBy = a_createdBy;
  }
  
  //create Date
  public void setCreateDate(Date date) {
	    createDate = date;
  }
  
  public Date getCreateDate() {
    return createDate;
  }
  
  public String getCreateDateStr(String dateFormat) {
	 return new SimpleDateFormat(dateFormat).format(createDate);
  }
  
  public String getCreateDateStr(){
	  return getCreateDateStr(format);
  }
  
  //affirmStatusList
  public void setAffirmStatusList(List list){
      affirmStatusList = list;
  }
  
  public List getAffirmStatusList(){
      return affirmStatusList;
  }

public String getPerson_id() {
	return person_id;
}

public void setPerson_id(String person_id) {
	this.person_id = person_id;
}
  
}
