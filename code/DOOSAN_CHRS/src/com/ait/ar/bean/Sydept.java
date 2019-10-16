package com.ait.ar.bean;

public class Sydept {
  private String empid;
  private String empname;
  private String deptid;
  private String deptname;
  private String status;
  public Sydept() {
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmpname() {
    return empname;
  }
  public void setEmpname(String empname) {
    this.empname = empname;
  }
  public String getDeptid() {
    return deptid;
  }
  public void setDeptid(String deptid) {
    this.deptid = deptid;
  }
  public String getDeptname() {
    return deptname;
  }
  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }
  public void setStatus(String a_status){
	  status = a_status;
  }
  public String getStatus(){
	  return status;
  }
}
