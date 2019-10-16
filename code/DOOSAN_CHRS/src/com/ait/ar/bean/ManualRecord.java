package com.ait.ar.bean;

/**
 * <p>Title: </p>
 *
 * <p>Description:
 * <br>
 * 由考勤员手工输入的刷卡记录
 *  </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class ManualRecord {
  private int record_no;
  private String empid;
  private String enter_time;
  private String out_time;
  private String lock_yn;
  public ManualRecord() {
  }
  public String getEmpid() {
    return empid;
  }
  public String getEnter_time() {
    return enter_time;
  }
  public String getLock_yn() {
    return lock_yn;
  }
  public String getOut_time() {
    return out_time;
  }
  public int getRecord_no() {
    return record_no;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public void setEnter_time(String enter_time) {
    this.enter_time = enter_time;
  }
  public void setLock_yn(String lock_yn) {
    this.lock_yn = lock_yn;
  }
  public void setOut_time(String out_time) {
    this.out_time = out_time;
  }
  public void setRecord_no(int record_no) {
    this.record_no = record_no;
  }
}
