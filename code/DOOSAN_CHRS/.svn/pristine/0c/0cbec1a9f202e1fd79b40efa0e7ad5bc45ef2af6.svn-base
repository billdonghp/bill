package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex2 {
  private static ServiceLocator services;
  private String planEduItemCode;
  private String planEduItemName;
  private String countPerson;
  private String planEduTime;
  private String totalFee;
  private String totalTime;
  private String startDate;
  private String endDate;
  public Ex2() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
    public Vector getEx2(String eduItemCode)throws Exception{
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "SELECT  PLAN_EDU_ITEM_CODE, PLAN_EDU_ITEM_NAME, COUNTPERSON_G,   "+
          " PLAN_EDU_TIME_G, TOTAL_FEE_G, TOTALTIME  FROM ETN_ITEM_FOR_RE ";
          String  where_sql = "";
          if(!eduItemCode.equals("")){
               where_sql = "     where PLAN_EDU_ITEM_CODE = '"+eduItemCode+"'";
          }

      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL+where_sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          vector.add(this.createEx2(rs));
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
    private Ex2 createEx2(ResultSet rs) throws Exception{
      Ex2 ex2= new Ex2();
        try {
          ex2.setPlanEduItemCode(rs.getString("PLAN_EDU_ITEM_CODE") != null ? rs.getString("PLAN_EDU_ITEM_CODE") : "");
          ex2.setPlanEduItemName(rs.getString("PLAN_EDU_ITEM_NAME") != null ? rs.getString("PLAN_EDU_ITEM_NAME") : "");
          ex2.setCountPerson(rs.getString("COUNTPERSON_G") != null ? rs.getString("COUNTPERSON_G") : "");
          ex2.setPlanEduTime(rs.getString("PLAN_EDU_TIME_G") != null ? rs.getString("PLAN_EDU_TIME_G") : "");
          ex2.setTotalFee(rs.getString("TOTAL_FEE_G") != null ? rs.getString("TOTAL_FEE_G") : "");
          ex2.setTotalTime(rs.getString("TOTALTIME") != null ? rs.getString("TOTALTIME") : "");
         }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return ex2;
      }


  public String getPlanEduItemCode() {
    return planEduItemCode;
  }
  public void setPlanEduItemCode(String planEduItemCode) {
    this.planEduItemCode = planEduItemCode;
  }
  public String getPlanEduItemName() {
    return planEduItemName;
  }
  public void setPlanEduItemName(String planEduItemName) {
    this.planEduItemName = planEduItemName;
  }
  public String getCountPerson() {
    return countPerson;
  }
  public void setCountPerson(String countPerson) {
    this.countPerson = countPerson;
  }
  public String getPlanEduTime() {
    return planEduTime;
  }
  public void setPlanEduTime(String planEduTime) {
    this.planEduTime = planEduTime;
  }
  public String getTotalFee() {
    return totalFee;
  }
  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }
  public String getTotalTime() {
    return totalTime;
  }
  public void setTotalTime(String totalTime) {
    this.totalTime = totalTime;
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
}
