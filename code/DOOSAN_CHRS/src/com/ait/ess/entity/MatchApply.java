package com.ait.ess.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * modify by lvhongbin at 2005-11-07
 * */

public class MatchApply {
	private String empid;
  private String matchPerson;
  private String matchTypeCode;
  private String matchApplyDate;
  private String matchDays;
  private String matchApplyStartDate;
  private String matchApplyEndDate;
  private String lastYearDates;
  private String matchApplyNo;
  private String matchContent;
  private String matchTypeCodeName;
  private String matchPersonName;
  private int activity;
  private String updatedBy;
  private Date updateDate;
  private String createdBy;
  private Date createDate;
  
  private List affirmStatusList;
  
  private String format = "yyyy-MM-dd HH:mm:ss.SSS";
  
  public MatchApply() {
	  //constructor
  }
  
  //leavePerson
  
  public String getEmpID()
  {
	  return empid;
  }
  public void setEmpId(String empid)
  {
	  this.empid=empid;
  }
  public String getMatchPerson() {
    return matchPerson;
  }
  public void setMatchPerson(String matchPerson) {
	  this.matchPerson = matchPerson;
  }
  
  //leave Type Code	
  public String getMatchTypeCode() {
    return matchTypeCode;
  }
  
  public void setMatchTypeCode(String matchTypeCode) {
	  this.matchTypeCode = matchTypeCode;
  }
  
  //leave Apply Date
  public void setMatchApplyDate(String date) {
	  matchApplyDate = date;
  }
  
  public String getMatchApplyDate() {
    return matchApplyDate;
  }
  

  
  //leave Days
  public String getMatchDays() {
    return matchDays;
  }
  
  public void setMatchDays(String a_matchDays) {
    matchDays = a_matchDays;
  }
  
  //leave Apply Start Date
  public void setMatchApplyStartDate(String date) {
	  matchApplyStartDate = date;
  }
  
  public String getMatchApplyStartDate() {
    return matchApplyStartDate;
  }
  
  public String getMatchApplyStartDateStr(String dateFormat){
	  return new SimpleDateFormat(dateFormat)
	  					.format(matchApplyStartDate);
  }
  
  public String getMatchApplyStartDateStr(){
	  return getMatchApplyStartDateStr(format);
  }

  
  //leave Apply EndDate
  public void setMatchApplyEndDate(String date) {
	  matchApplyEndDate = date;
  }
  
  public String getMatchApplyEndDate() {
    return matchApplyEndDate;
  }
  
  public String getMatchApplyEndDate(String dateFormat){
	  return new SimpleDateFormat(dateFormat).format(matchApplyEndDate);
  }
  
  public String getMatchApplyEndDateStr(){
	  return getMatchApplyEndDate(format);
  }
  
  //last Year Dates
  public String getLastYearDates() {
    return lastYearDates;
  }
  
  public void setLastYearDates(String a_lastYearDates) {
    lastYearDates = a_lastYearDates;
  }
  
  //leave Apply No
  public String getMatchApplyNo() {
    return matchApplyNo;
  }
  
  public void setMatchApplyNo(String a_matchApplyNo) {
	  matchApplyNo = a_matchApplyNo;
  }
  
  //leave Content
  public String getMatchContent() {
    return matchContent;
  }
  
  public void setMatchContent(String a_matchContent) {
	  matchContent = a_matchContent;
  }
  
  //leave Type Code Name
  public String getMatchTypeCodeName() {
    return matchTypeCodeName;
  }
  
  public void setMatchTypeCodeName(String a_matchTypeCodeName) {
	  matchTypeCodeName = a_matchTypeCodeName;
  }
  
  //leave Person Name
  public String getMatchPersonName() {
    return matchPersonName;
  }
  
  public void setMatchPersonName(String a_matchPersonName) {
	  matchPersonName = a_matchPersonName;
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
  
}
