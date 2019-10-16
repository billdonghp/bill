package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex5 {
  private static ServiceLocator services;
  private String classType;
  private String eduTerm;
  private String eduGroup;
  private String eduType;
  private String totalFee;
  private String teacherFee;
  private String textFee;
  private String placeFee;
  private String eattingFee;
  private String houseFee;
  private String trafficFee;
  private String otherFee;
  public Ex5() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
    public Vector getEx5(String etnplanno,String startdate,String enddate)throws Exception{
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "SELECT PLAN_CLASS_TYPE,PLAN_EDU_TYPE_NAME,FEE_TEACH_TEXT,FEE_GROUND,   "+
                    " FEE_EATTING, FEE_QUARTER, FEE_TRRAFIC,(FEE_PASSPORT+FEE_VISA+FEE_INSURE+FEE_LIVING+FEE_OTHER) as T_otherFee,  "+
                    " COUNT_TEACHER_FEE,  TOTAL_FEE, PLAN_PERRIOR_COUNT,PLAN_FIT_GROUP  FROM ETN_PLAN_TOTAL_V ";

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
          vector.add(this.createEx5(rs));
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
    private Ex5 createEx5(ResultSet rs) throws Exception{
      Ex5 ex5= new Ex5();
        try {
          ex5.setClassType(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
          ex5.setEduTerm(rs.getString("PLAN_PERRIOR_COUNT") != null ? rs.getString("PLAN_PERRIOR_COUNT") : "");
          ex5.setEduGroup(rs.getString("PLAN_FIT_GROUP") != null ? rs.getString("PLAN_FIT_GROUP") : "");
          ex5.setEduType(rs.getString("PLAN_EDU_TYPE_NAME") != null ? rs.getString("PLAN_EDU_TYPE_NAME") : "");
          ex5.setTotalFee(rs.getString("TOTAL_FEE") != null ? rs.getString("TOTAL_FEE") : "");
          ex5.setTeacherFee(rs.getString("COUNT_TEACHER_FEE") != null ? rs.getString("COUNT_TEACHER_FEE") : "");
          ex5.setTextFee(rs.getString("FEE_TEACH_TEXT") != null ? rs.getString("FEE_TEACH_TEXT") : "");
          ex5.setPlaceFee(rs.getString("FEE_GROUND") != null ? rs.getString("FEE_GROUND") : "");
          ex5.setEattingFee(rs.getString("FEE_EATTING") != null ? rs.getString("FEE_EATTING") : "");
          ex5.setHouseFee(rs.getString("FEE_QUARTER") != null ? rs.getString("FEE_QUARTER") : "");
          ex5.setTrafficFee(rs.getString("FEE_TRRAFIC") != null ? rs.getString("FEE_TRRAFIC") : "");
          ex5.setOtherFee(rs.getString("T_otherFee") != null ? rs.getString("T_otherFee") : "");
         }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return ex5;
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
  public String getEduGroup() {
    return eduGroup;
  }
  public void setEduGroup(String eduGroup) {
    this.eduGroup = eduGroup;
  }
  public String getEduType() {
    return eduType;
  }
  public void setEduType(String eduType) {
    this.eduType = eduType;
  }
  public String getTotalFee() {
    return totalFee;
  }
  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }
  public String getTeacherFee() {
    return teacherFee;
  }
  public void setTeacherFee(String teacherFee) {
    this.teacherFee = teacherFee;
  }
  public String getTextFee() {
    return textFee;
  }
  public void setTextFee(String textFee) {
    this.textFee = textFee;
  }
  public String getPlaceFee() {
    return placeFee;
  }
  public void setPlaceFee(String placeFee) {
    this.placeFee = placeFee;
  }
  public String getEattingFee() {
    return eattingFee;
  }
  public void setEattingFee(String eattingFee) {
    this.eattingFee = eattingFee;
  }
  public String getHouseFee() {
    return houseFee;
  }
  public void setHouseFee(String houseFee) {
    this.houseFee = houseFee;
  }
  public String getTrafficFee() {
    return trafficFee;
  }
  public void setTrafficFee(String trafficFee) {
    this.trafficFee = trafficFee;
  }
  public String getOtherFee() {
    return otherFee;
  }
  public void setOtherFee(String otherFee) {
    this.otherFee = otherFee;
  }
}
