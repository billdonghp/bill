package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex7 {
  private static ServiceLocator services;
  private String classType;
  private String eduTerm;
  private String eduStartDate;
  private String eduEndDate;
  private String department;
  private String empid;
  private String name;
  private String joinDate;
  private String postGrade;
  private String postGroup;
  private String protocolStartDate;
  private String protocolEndDate;
  private String protocolTerm;
  public Ex7() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
    public Vector getEx7(String etnplanno)throws Exception{
      String Sql_order =" order by PLAN_START_DATE desc ";
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "select PLAN_CLASS_TYPE,PLAN_PERRIOR_COUNT,PLAN_START_DATE, PLAN_END_DATE, DEPARTMENT,   "+
                  "  EMPID,CHINESENAME,JOINDATE,POST_GROUP_NAME,POST_GRADE,PROTOCOL_START_DATE, PROTOCOL_END_DATE, PROTOCOL_TERM   "+
                   " from ETN_PLAN_PERSONAL_TOTAL";
      String  where_sql = "";
      //String order_sql=" ORDER BY PLAN_CLASS_TYPE";
      if(!etnplanno.equals("")){
        where_sql = "     where SEQ_ETN_PLAN = "+etnplanno;
      }
      //System.out.println(SQL+where_sql+Sql_order);
      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL+where_sql+Sql_order);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          vector.add(this.createEx7(rs));
        }
      }
      catch (SQLException ex) {
         ex.printStackTrace();
      }
      catch (ServiceLocatorException ex) {
      }
      finally{
        if(rs!=null){
          rs.close();
        }
        if(pstmt!=null){
          pstmt.close();
        }
        if(conn!=null){
          conn.close();
        }
      }
      return vector;
    }
    private Ex7 createEx7(ResultSet rs) throws Exception{
      Ex7 ex7= new Ex7();
        try {
          ex7.setClassType(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
          ex7.setEduTerm(rs.getString("PLAN_PERRIOR_COUNT") != null ? rs.getString("PLAN_PERRIOR_COUNT") : "");
          ex7.setEduStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) : "");
          ex7.setEduEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10) : "");
          ex7.setDepartment(rs.getString("DEPARTMENT") != null ? rs.getString("DEPARTMENT") : "");
          ex7.setEmpid(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
          ex7.setName(rs.getString("CHINESENAME") != null ? rs.getString("CHINESENAME") : "");
          ex7.setJoinDate(rs.getString("JOINDATE") != null ? rs.getString("JOINDATE").substring(0,10) : "");
          ex7.setPostGroup(rs.getString("POST_GROUP_NAME") != null ? rs.getString("POST_GROUP_NAME") : "");
          ex7.setPostGrade(rs.getString("POST_GRADE") != null ? rs.getString("POST_GRADE") : "");
          ex7.setProtocolStartDate(rs.getString("PROTOCOL_START_DATE") != null ? rs.getString("PROTOCOL_START_DATE").substring(0,10) : "");
          ex7.setProtocolEndDate(rs.getString("PROTOCOL_END_DATE") != null ? rs.getString("PROTOCOL_END_DATE").substring(0,10) : "");
          ex7.setProtocolTerm(rs.getString("PROTOCOL_TERM") != null ? rs.getString("PROTOCOL_TERM") : "");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return ex7;
      }

  public String getClassType() {
    return classType;
  }
  public void setClassType(String classType) {
    this.classType = classType;
  }
  public String getEduTerm() {
    return eduTerm;
  }
  public void setEduTerm(String eduTerm) {
    this.eduTerm = eduTerm;
  }
  public String getEduStartDate() {
    return eduStartDate;
  }
  public void setEduStartDate(String eduStartDate) {
    this.eduStartDate = eduStartDate;
  }
  public String getEduEndDate() {
    return eduEndDate;
  }
  public void setEduEndDate(String eduEndDate) {
    this.eduEndDate = eduEndDate;
  }
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
  }
  public String getPostGrade() {
    return postGrade;
  }
  public void setPostGrade(String postGrade) {
    this.postGrade = postGrade;
  }
  public String getPostGroup() {
    return postGroup;
  }
  public void setPostGroup(String postGroup) {
    this.postGroup = postGroup;
  }
  public String getProtocolStartDate() {
    return protocolStartDate;
  }
  public void setProtocolStartDate(String protocolStartDate) {
    this.protocolStartDate = protocolStartDate;
  }
  public String getProtocolEndDate() {
    return protocolEndDate;
  }
  public void setProtocolEndDate(String protocolEndDate) {
    this.protocolEndDate = protocolEndDate;
  }
  public String getProtocolTerm() {
    return protocolTerm;
  }
  public void setProtocolTerm(String protocolTerm) {
    this.protocolTerm = protocolTerm;
  }
}
