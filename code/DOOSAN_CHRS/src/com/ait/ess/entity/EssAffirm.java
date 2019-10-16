package com.ait.ess.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EssAffirm {
  private int essAffirmNo;
  private String affirmorId;
  private String affirmorName;
  private Date updateDate;
  private String applyType;
  private int affirmFlag;
  private int activity;
  private int affirmLevel;
  private int applyNo;
  
  private String format = "yyyy-MM-dd HH:mm:ss.SSS";

  public EssAffirm() {
	  //constructor
  }
  
  //ess affirm number
  public void setEssAffirmNo(int a_essAffirmNo) {
	  essAffirmNo = a_essAffirmNo;
  }
  
  public int getEssAffirmNo(){
	  return essAffirmNo;
  }
  
  //affirmor Id
  public void setAffirmorId(String a_affirmorId) {
	    affirmorId = a_affirmorId;
  }
  
  public String getAffirmorId(){
	  return affirmorId;
  }
  
  //affirmor Name
  public void setAffirmorName(String a_affirmorName) {
	 affirmorName = a_affirmorName;
  }
  
  public String getAffirmorName() {
	return affirmorName;
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
  
  //apply Type
  public void setApplyType(String a_applyType) {
    applyType = a_applyType;
  }
  
  public String getApplyType() {
	    return applyType;
  }
  
  //affirm Flag
  public void setAffirmFlag(int a_affirmFlag) {
	affirmFlag = a_affirmFlag;
  }
	
  public int getAffirmFlag() {
	return affirmFlag;
  }
  
  //activity
  public void setActivity(int a_activity) {
	    activity = a_activity;
  }
  
  public int getActivity() {
    return activity;
  }
  
  //affirmLevel
  public void setAffirmLevel(int a_affirmLevel) {
    affirmLevel = a_affirmLevel;
  }
  
  public int getAffirmLevel() {
	return affirmLevel;
  }
  
  //apply No
  public void setApplyNo(int a_applyNo) {
    applyNo = a_applyNo;
  }

  public int getApplyNo() {
    return applyNo;
  }
}
