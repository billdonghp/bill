package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex1 {
  private static ServiceLocator services;
  private String etnPlanNo;
  private String classType;
  private String eduTerm;
  private String eduTypeCode;
  private String eduTypeName;
  private String countPerson;
  private String eduTime;
  private String totalFee;
  private String startDate;
  private String endDate;
  private String eduGroup;
  private String danwei;
  public Ex1() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public Vector getEx1(String etnplanno,String startdate,String enddate)throws Exception{
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "SELECT  SEQ_ETN_PLAN,PLAN_EDU_TIME_DANWEI,  "+
                   " PLAN_CLASS_TYPE, PLAN_EDU_TYPE_CODE, PLAN_EDU_TYPE_NAME, PLAN_START_DATE, PLAN_END_DATE,  "+
                    " PLAN_EDU_TIME,PLAN_PERRIOR_COUNT_Y,TOTAL_FEE, COUNTPERSON,PLAN_FIT_GROUP_Y  FROM ETN_RESULT_FOR_RE  ";
      String where_sql = "";
      String order_sql=" ORDER BY PLAN_CLASS_TYPE";
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

      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL+where_sql+order_sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          vector.add(this.createEx1(rs));
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
    private Ex1 createEx1(ResultSet rs) throws Exception{
      Ex1 ex1 = new Ex1();
        try {
          ex1.setEtnPlanNo(rs.getString("SEQ_ETN_PLAN") != null ? rs.getString("SEQ_ETN_PLAN") : "");
          ex1.setClassType(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
          ex1.setEduTerm(rs.getString("PLAN_PERRIOR_COUNT_Y") != null ? rs.getString("PLAN_PERRIOR_COUNT_Y") : "");
          ex1.setEduGroup(rs.getString("PLAN_FIT_GROUP_Y") != null ? rs.getString("PLAN_FIT_GROUP_Y") : "");
          ex1.setEduTypeName(rs.getString("PLAN_EDU_TYPE_NAME") != null ? rs.getString("PLAN_EDU_TYPE_NAME") : "");
          ex1.setCountPerson(rs.getString("COUNTPERSON") != null ? rs.getString("COUNTPERSON") : "");
          ex1.setEduTime(rs.getString("PLAN_EDU_TIME") != null ? rs.getString("PLAN_EDU_TIME") : "");
          ex1.setTotalFee(rs.getString("TOTAL_FEE") != null ? rs.getString("TOTAL_FEE") : "");
          ex1.setStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) : "");
          ex1.setEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10) : "");
		ex1.setDanwei(rs.getString("PLAN_EDU_TIME_DANWEI"));
		 
		 }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return ex1;
      }


  public String getEtnPlanNo() {
    return etnPlanNo;
  }
  public void setEtnPlanNo(String etnPlanNo) {
    this.etnPlanNo = etnPlanNo;
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
  public String getEduTypeCode() {
    return eduTypeCode;
  }
  public void setEduTypeCode(String eduTypeCode) {
    this.eduTypeCode = eduTypeCode;
  }
  public String getEduTypeName() {
    return eduTypeName;
  }
  public void setEduTypeName(String eduTypeName) {
    this.eduTypeName = eduTypeName;
  }
  public String getCountPerson() {
    return countPerson;
  }
  public void setCountPerson(String countPerson) {
    this.countPerson = countPerson;
  }
  public String getEduTime() {
    return eduTime;
  }
  public void setEduTime(String eduTime) {
    this.eduTime = eduTime;
  }
  public String getTotalFee() {
    return totalFee;
  }
  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }



  public String getEduGroup() {
    return eduGroup;
  }
  public void setEduGroup(String eduGroup) {
    this.eduGroup = eduGroup;
  }



  public String getDanwei() {
    return danwei;
  }
  public void setDanwei(String danwei) {
    this.danwei = danwei;
  }
}
