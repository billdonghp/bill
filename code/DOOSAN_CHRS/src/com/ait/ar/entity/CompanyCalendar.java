package com.ait.ar.entity;

public class CompanyCalendar {
  private String calendarDate;
  private int calendarType;
  private int workFlag;
  private String createdBy;
  private String createDate;
  private String updateDate;
  private String updatedBy;
  private int orderNo;
  private int activity;
  private String statisticPeriod;
  private String calendarDay;
  private String department;
  private String deptid;
  private int calendarNo;
  public CompanyCalendar() {
  }
  public String getCalendarDate() {
    return calendarDate;
  }
  public void setCalendarDate(String calendarDate) {
    this.calendarDate = calendarDate;
  }
  public int getCalendarType() {
    return calendarType;
  }
  public void setCalendarType(int calendarType) {
    this.calendarType = calendarType;
  }
  public String getCalendarDay() {
    return calendarDay;
  }
  public void setCalendarDay(String calendarDay) {
    this.calendarDay = calendarDay;
  }
  public int getWorkFlag() {
    return workFlag;
  }
  public void setWorkFlag(int workFlag) {
    this.workFlag = workFlag;
  }
  public String getCreatedBy() {
    return createdBy;
  }
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
  public String getUpdatedBy() {
    return updatedBy;
  }
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  public int getOrderNo() {
    return orderNo;
  }
  public void setOrderNo(int orderNo) {
    this.orderNo = orderNo;
  }
  public int getActivity() {
    return activity;
  }
  public void setActivity(int activity) {
    this.activity = activity;
  }
  public String getStatisticPeriod() {
    return statisticPeriod;
  }
  public void setStatisticPeriod(String statisticPeriod) {
    this.statisticPeriod = statisticPeriod;
  }
  public int getCalendarNo() {
    return calendarNo;
  }

  public String getDeptid() {
    return deptid;
  }

  public String getDepartment() {
    return department;
  }

  public void setCalendarNo(int calendarNo) {
    this.calendarNo = calendarNo;
  }

  public void setDeptid(String deptid) {
    this.deptid = deptid;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
