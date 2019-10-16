package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex6 {
  private static ServiceLocator services;
  private String classtype;
  private String department;
  private String empid;
  private String chinesename;
  private String joinDate;
  private String postGroup;
  private String postGrade;
  public Ex6() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
    public Vector getEx6(String etnplanno,String startdate,String enddate)throws Exception{
      String Sql_order =" order by PLAN_START_DATE desc ";
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "select PLAN_CLASS_TYPE,PLAN_START_DATE, PLAN_END_DATE, DEPARTMENT,  "+
                   " EMPID,CHINESENAME,JOINDATE,POST_GROUP_NAME,POST_GRADE   "+
                   " from ETN_NO_EDU_FOR_RE  ";

               String where_sql = "";
               //String order_sql=" ORDER BY PLAN_CLASS_TYPE";
               if(!etnplanno.equals("")){
                 if(!startdate.equals("") && !enddate.equals("")){
                   where_sql ="  where SEQ_ETN_PLAN ="+etnplanno+" and PLAN_START_DATE < to_date('"+enddate+"','yyyy-mm-dd') and PLAN_END_DATE > to_date('"+startdate+"','yyyy-mm-dd')";
                 }
                 if(!startdate.equals("") && enddate.equals("")){
                   where_sql ="  where SEQ_ETN_PLAN ="+etnplanno+" and PLAN_END_DATE > to_date('"+startdate+"','yyyy-mm-dd')";
                 }
                 if(startdate.equals("") && !enddate.equals("")){
                   where_sql ="  where SEQ_ETN_PLAN ="+etnplanno+" and PLAN_START_DATE < to_date('"+enddate+"','yyyy-mm-dd')";
                 }
                 if(startdate.equals("") && enddate.equals("")){
                   where_sql ="  where SEQ_ETN_PLAN ="+etnplanno;
                 }

               }else{
                 if(!startdate.equals("") && !enddate.equals("")){
                   where_sql ="  where PLAN_START_DATE < to_date('"+enddate+"','yyyy-mm-dd') and PLAN_END_DATE > to_date('"+startdate+"','yyyy-mm-dd')";
                 }
                 if(!startdate.equals("") && enddate.equals("")){
                   where_sql ="  where PLAN_END_DATE > to_date('"+startdate+"','yyyy-mm-dd')";
                 }
                 if(startdate.equals("") && !enddate.equals("")){
                   where_sql ="  where PLAN_START_DATE < to_date('"+enddate+"','yyyy-mm-dd')";
                 }
               }
              // System.out.println(SQL+where_sql+Sql_order);

      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL+where_sql+Sql_order);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          vector.add(this.createEx6(rs));
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
    private Ex6 createEx6(ResultSet rs) throws Exception{
      Ex6 ex6= new Ex6();
        try {
          ex6.setClasstype(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
          ex6.setDepartment(rs.getString("DEPARTMENT") != null ? rs.getString("DEPARTMENT") : "");
          ex6.setEmpid(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
          ex6.setChinesename(rs.getString("CHINESENAME") != null ? rs.getString("CHINESENAME") : "");
          ex6.setJoinDate(rs.getString("JOINDATE") != null ? rs.getString("JOINDATE").substring(0,10) : "");
          ex6.setPostGroup(rs.getString("POST_GROUP_NAME") != null ? rs.getString("POST_GROUP_NAME") : "");
          ex6.setPostGrade(rs.getString("POST_GRADE") != null ? rs.getString("POST_GRADE") : "");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return ex6;
      }

  public String getClasstype() {
    return classtype;
  }
  public void setClasstype(String classtype) {
    this.classtype = classtype;
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
  public String getChinesename() {
    return chinesename;
  }
  public void setChinesename(String chinesename) {
    this.chinesename = chinesename;
  }
  public String getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
  }
  public String getPostGroup() {
    return postGroup;
  }
  public void setPostGroup(String postGroup) {
    this.postGroup = postGroup;
  }
  public String getPostGrade() {
    return postGrade;
  }
  public void setPostGrade(String postGrade) {
    this.postGrade = postGrade;
  }
}
