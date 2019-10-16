package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex3 {
  private static ServiceLocator services;
  private String eduTerm;
  private String classType;
  private String eduGroup;
  private String eduTypeName;
  private String eduCountPerson;
  private String eduStartDate;
  private String eduEndDate;
  private String eduTime;
  private String eduPlace;
  private String totalFee;
  private String wholeSatisfact;
  private String teacherStatisfact;
  private String eligibilityRate;
  private String fellBackCount;
  private String awardCount;
  public Ex3() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
    public Vector getEx3(String term,String startdate,String enddate)throws Exception{
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "SELECT    "+
                    " SEQ_ETN_PLAN,COUNTPERSON,  "+
                    " PLAN_CLASS_TYPE,PLAN_EDU_TYPE_NAME, PLAN_FIT_GROUP_Y,  "+
                    " PLAN_EDU_PLACE, PLAN_START_DATE, PLAN_END_DATE,   "+
                    " PLAN_EDU_TIME, PLAN_PERRIOR_COUNT_Y,TOTAL_FEE,   "+
                    " WHOLE_SATISFACTION,EDU_TEACHER_TOTAL_SATIS,  "+
                    " ELIGIBILITY_RATE,COUNT_FELL_BACK,CONNT_AWARD  "+
                    " FROM ETN_PLAN_TERM_FOR_RE   ";
      String where_sql = "";
      String order_sql=" ORDER BY PLAN_CLASS_TYPE";
      if(!term.equals("")){
        if(!startdate.equals("") && !enddate.equals("")){
          where_sql ="  where PLAN_PERRIOR_COUNT_Y ="+term+" and PLAN_START_DATE < to_date('"+enddate+"','yyyy-mm-dd') and PLAN_END_DATE > to_date('"+startdate+"','yyyy-mm-dd')";
        }
        if(!startdate.equals("") && enddate.equals("")){
          where_sql ="  where PLAN_PERRIOR_COUNT_Y ="+term+" and PLAN_END_DATE > to_date('"+startdate+"','yyyy-mm-dd')";
        }
        if(startdate.equals("") && !enddate.equals("")){
          where_sql ="  where PLAN_PERRIOR_COUNT_Y ="+term+" and PLAN_START_DATE < to_date('"+enddate+"','yyyy-mm-dd')";
        }
        if(startdate.equals("") && enddate.equals("")){
          where_sql ="  where PLAN_PERRIOR_COUNT_Y ="+term;
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
          vector.add(this.createEx3(rs));
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
    private Ex3 createEx3(ResultSet rs) throws Exception{
      Ex3 ex3= new Ex3();
        try {
          ex3.setEduTerm(rs.getString("PLAN_PERRIOR_COUNT_Y") != null ? rs.getString("PLAN_PERRIOR_COUNT_Y") : "");
          ex3.setClassType(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
          ex3.setEduGroup(rs.getString("PLAN_FIT_GROUP_Y") != null ? rs.getString("PLAN_FIT_GROUP_Y") : "");
          ex3.setEduTypeName(rs.getString("PLAN_EDU_TYPE_NAME") != null ? rs.getString("PLAN_EDU_TYPE_NAME") : "");
          ex3.setEduCountPerson(rs.getString("COUNTPERSON") != null ? rs.getString("COUNTPERSON") : "");
          ex3.setEduStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) : "");
          ex3.setEduEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10) : "");
          ex3.setEduTime(rs.getString("PLAN_EDU_TIME") != null ? rs.getString("PLAN_EDU_TIME") : "");
          ex3.setEduPlace(rs.getString("PLAN_EDU_PLACE") != null ? rs.getString("PLAN_EDU_PLACE") : "");
          ex3.setTotalFee(rs.getString("TOTAL_FEE") != null ? rs.getString("TOTAL_FEE") : "");
          ex3.setWholeSatisfact(rs.getString("WHOLE_SATISFACTION") != null ? rs.getString("WHOLE_SATISFACTION") : "");
          ex3.setTeacherStatisfact(rs.getString("EDU_TEACHER_TOTAL_SATIS") != null ? rs.getString("EDU_TEACHER_TOTAL_SATIS") : "");
          ex3.setEligibilityRate(rs.getString("ELIGIBILITY_RATE") != null ? rs.getString("ELIGIBILITY_RATE") : "");
          ex3.setFellBackCount(rs.getString("COUNT_FELL_BACK") != null ? rs.getString("COUNT_FELL_BACK") : "");
          ex3.setAwardCount(rs.getString("CONNT_AWARD") != null ? rs.getString("CONNT_AWARD") : "");
         }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return ex3;
      }


  public String getEduTerm() {
    return eduTerm;
  }
  public void setEduTerm(String eduTerm) {
    this.eduTerm = eduTerm;
  }
  public String getClassType() {
    return classType;
  }
  public void setClassType(String classType) {
    this.classType = classType;
  }
  public String getEduGroup() {
    return eduGroup;
  }
  public void setEduGroup(String eduGroup) {
    this.eduGroup = eduGroup;
  }
  public String getEduTypeName() {
    return eduTypeName;
  }
  public void setEduTypeName(String eduTypeName) {
    this.eduTypeName = eduTypeName;
  }
  public String getEduCountPerson() {
    return eduCountPerson;
  }
  public void setEduCountPerson(String eduCountPerson) {
    this.eduCountPerson = eduCountPerson;
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
  public String getEduTime() {
    return eduTime;
  }
  public void setEduTime(String eduTime) {
    this.eduTime = eduTime;
  }
  public String getEduPlace() {
    return eduPlace;
  }
  public void setEduPlace(String eduPlace) {
    this.eduPlace = eduPlace;
  }
  public String getTotalFee() {
    return totalFee;
  }
  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }
  public String getWholeSatisfact() {
    return wholeSatisfact;
  }
  public void setWholeSatisfact(String wholeSatisfact) {
    this.wholeSatisfact = wholeSatisfact;
  }
  public String getTeacherStatisfact() {
    return teacherStatisfact;
  }
  public void setTeacherStatisfact(String teacherStatisfact) {
    this.teacherStatisfact = teacherStatisfact;
  }
  public String getEligibilityRate() {
    return eligibilityRate;
  }
  public void setEligibilityRate(String eligibilityRate) {
    this.eligibilityRate = eligibilityRate;
  }
  public String getFellBackCount() {
    return fellBackCount;
  }
  public void setFellBackCount(String fellBackCount) {
    this.fellBackCount = fellBackCount;
  }
  public String getAwardCount() {
    return awardCount;
  }
  public void setAwardCount(String awardCount) {
    this.awardCount = awardCount;
  }
}
