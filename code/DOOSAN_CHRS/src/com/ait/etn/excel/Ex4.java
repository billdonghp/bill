package com.ait.etn.excel;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Ex4 {
  private static ServiceLocator services;
  private String eduItem;
  private String classType;
  private String eduStartDate;
  private String eduEndDate;
  private String theDate;
  private String manageDepartment;
  private String appraise;
  private String promotionNeed;
  private String empid;
  private String chinesename;
  public Ex4() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }

  }
    public Vector getEx4(String contentType , String content)throws Exception{
      Vector vector = new Vector();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "SELECT EMPID,CHINESENAME,PLAN_EDU_ITEM_NAME, PLAN_CLASS_TYPE, EXAM_SCORE,  "+
                   " PLAN_START_DATE, PLAN_END_DATE,(PLAN_END_DATE-PLAN_START_DATE+1) as THEDATES,  MANAGE_DEPARTMENT,  "+
                   " PROMOTION_NEEDED_NAME FROM ETN_PLAN_PERSONAL_TOTAL   ";
      String where_sql = "";
      String order_sql=" ORDER BY PLAN_CLASS_TYPE";
      if(!contentType.equals("")){
         if(!content.equals("")){
           where_sql="where " + contentType + " = '" + content + "'";
         }
         if(content.equals("")){
           where_sql="";
         }
      }

      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL+where_sql+order_sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          vector.add(this.createEx4(rs));
        }
      }
      catch (SQLException ex) {
         ex.printStackTrace();
      }
      catch (ServiceLocatorException ex){
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
    private Ex4 createEx4(ResultSet rs) throws Exception{
      Ex4 ex4= new Ex4();
        try {
          ex4.setEmpid(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
          ex4.setChinesename(rs.getString("CHINESENAME") != null ? rs.getString("CHINESENAME") : "");
          ex4.setEduItem(rs.getString("PLAN_EDU_ITEM_NAME") != null ? rs.getString("PLAN_EDU_ITEM_NAME") : "");
          ex4.setClassType(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
          ex4.setEduStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) : "");
          ex4.setEduEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10)  : "");
          ex4.setTheDate(rs.getString("THEDATES") != null ? rs.getString("THEDATES") : "");
          ex4.setManageDepartment(rs.getString("MANAGE_DEPARTMENT") != null ? rs.getString("MANAGE_DEPARTMENT") : "");
          ex4.setAppraise(rs.getString("EXAM_SCORE") != null ? rs.getString("EXAM_SCORE") : "");
          ex4.setPromotionNeed(rs.getString("PROMOTION_NEEDED_NAME") != null ? rs.getString("PROMOTION_NEEDED_NAME") : "");
         }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return ex4;
      }

  public String getEduItem() {
    return eduItem;
  }
  public void setEduItem(String eduItem) {
    this.eduItem = eduItem;
  }
  public String getClassType() {
    return classType;
  }
  public void setClassType(String classType) {
    this.classType = classType;
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
  public String getTheDate() {
    return theDate;
  }
  public void setTheDate(String theDate) {
    this.theDate = theDate;
  }
  public String getManageDepartment() {
    return manageDepartment;
  }
  public void setManageDepartment(String manageDepartment) {
    this.manageDepartment = manageDepartment;
  }
  public String getAppraise() {
    return appraise;
  }
  public void setAppraise(String appraise) {
    this.appraise = appraise;
  }
  public String getPromotionNeed() {
    return promotionNeed;
  }
  public void setPromotionNeed(String promotionNeed) {
    this.promotionNeed = promotionNeed;
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
}
