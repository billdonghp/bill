package com.ait.etn;


import java.sql.*;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.*;
import com.ait.utils.ConnBean;


  public class YearPlan {
  private String planCode;
  private String planEduItemCode;
  private String planEduItemName;
  private String planClassTypeCode;
  private String planClassTypeName;
  private String planFitDepartMentType;
  private String planDepartmentTypeName;
  private String planFitGroup;
  private String planEduTime;
  private String planPeriorCount;
  private String planStartDate;
  private String planEndDate;
  private String planEduTypeCode;
  private String planEduTypeName;
  private String planIntendFee;
  private String planTotalEduTime;
  private String planTotalPeriorCount;
  private String planTotalFee;
  private String planYearPlanNO;
  private String planColor;
  private String yearPlanCode;
  private String yearPlanCalenderFlag;
  private String managerDepartment;
  private String promotionNeed;
  private String promotionNeedCode;
  private String type_edu_result;
  private String planOrTemp;
  private String typeEduName;
  private Integer planEduTimeDanWei;
  private String planeduremark;
  private String planeduempnum;
  
  public YearPlan() {
  }
   public boolean addEtnYearPlanTotalCount(){
  	 boolean result = false;
     Connection con = null;
     String sql = "{ call etn_yearplan_pro() }";
     Logger.getLogger(getClass()).debug(sql);
     CallableStatement cs = null;
     try {
       con = ConnBean.getConn();
       cs = con.prepareCall(sql);
       result = cs.execute();
     }catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally {
      SqlUtil.close(cs, con);
    }
    return result;
  }

  public void addYearPlan(YearPlan yearplan){
     Connection conn = null;
     PreparedStatement pstmt = null;
     String sql = "INSERT INTO ETN_YEAR_PLAN ("+
         "SEQ_YEAR_PLAN, PLAN_CODE, PLAN_EDU_ITEM_CODE, "+
          "PLAN_CLASS_TYPE_CODE, PLAN_FIT_GROUP,"+
          "PLAN_EDU_TIME, PLAN_PERRIOR_COUNT, PLAN_START_DATE, "+
          "PLAN_END_DATE, PLAN_EDU_TYPE_CODE, PLAN_INTEND_FEE,PLAN_COLOR,PLAN_FLAG,MANAGE_DEPARTMENT, PROMOTION_NEEDED,TYPE_EDU_RESULT,PLAN_EDU_TIME_DANWEI,PLAN_EDU_REMARK,PLAN_EDU_EMP_NUM)"+
          "VALUES(ETN_YEAR_PLAN_SEQ.NEXTVAL,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,'0',?,?,?,?,?,?)";
     Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, yearplan.getPlanCode());
      pstmt.setString(2, yearplan.getPlanEduItemCode());
      pstmt.setString(3, yearplan.getPlanClassTypeCode());
      pstmt.setString(4, yearplan.getPlanFitGroup());
      pstmt.setFloat(5, Float.parseFloat(yearplan.getPlanEduTime()));
      pstmt.setInt(6, Integer.parseInt(yearplan.getPlanPeriorCount()));
      pstmt.setString(7, yearplan.getPlanStartDate());
      pstmt.setString(8, yearplan.getPlanEndDate());
      pstmt.setString(9, yearplan.getPlanEduTypeCode());
      pstmt.setFloat(10, Float.parseFloat(yearplan.getPlanIntendFee()));
      pstmt.setString(11, yearplan.getPlanColor());
      pstmt.setString(12, yearplan.getManagerDepartment());
      pstmt.setString(13, yearplan.getPromotionNeed());
      pstmt.setString(14, yearplan.getPlanOrTemp());
      pstmt.setInt(15,yearplan.getPlanEduTimeDanWei().intValue());
      pstmt.setString(16,yearplan.getPlaneduremark());
      pstmt.setString(17,yearplan.getPlaneduempnum());
      pstmt.executeUpdate();
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
      SqlUtil.close(pstmt, conn);
    }
  }
  /**
   *
   * @
   * @return Vector
   */
  public Vector getYearPlan(){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM ETN_YEAR_PLAN_V ";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createYearPlan(rs));
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }




  ///
public Vector getAllClassType(){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select distinct plan_class_type_code  from etn_year_plan";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(rs.getString("plan_class_type_code"));
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }
  ////
  /**
   *
   * @param plancode String
   * @
   * @return YearPlan
   */
  public YearPlan getYearPlan(String yearplanno){
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = " SELECT * FROM ETN_YEAR_PLAN_V WHERE SEQ_YEAR_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, Integer.parseInt(yearplanno));
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return this.createYearPlan(rs);
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return null;
  }

  public Vector getYearPlanTypeCode(){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select SEQ_YEAR_PLAN,PLAN_CODE,PLAN_CLASS_TYPE_CODE,PLAN_PERRIOR_COUNT from ETN_YEAR_PLAN_V";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        YearPlan yearplan = new YearPlan();
            yearplan.setPlanYearPlanNO(rs.getString("SEQ_YEAR_PLAN")!=null?rs.getString("SEQ_YEAR_PLAN"):"");
            yearplan.setPlanCode(rs.getString("PLAN_CODE")!=null?rs.getString("PLAN_CODE"):"");
            yearplan.setPlanClassTypeName(rs.getString("PLAN_CLASS_TYPE_CODE")!=null?rs.getString("PLAN_CLASS_TYPE_CODE"):"");
            yearplan.setPlanPeriorCount(rs.getString("PLAN_PERRIOR_COUNT")!=null?rs.getString("PLAN_PERRIOR_COUNT"):"");
            vector.add(yearplan);
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }




  public Vector getYearPlan(PageControl pc){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM ETN_YEAR_PLAN_V order by PLAN_START_DATE desc ";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();
      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) {
        vector.add(this.createYearPlan(rs));
        pc.setiii();
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }



   public Vector getYearPlanByClassType(PageControl pc,String classtype){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM ETN_YEAR_PLAN_V where PLAN_CLASS_TYPE_CODE ='"+classtype+"' order by PLAN_START_DATE desc ";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();
      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) {
        vector.add(this.createYearPlan(rs));
        pc.setiii();
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }

  /**
   *
   * @param rs ResultSet
   * @
   * @return YearPlan
   */
  private YearPlan createYearPlan(ResultSet rs){
      YearPlan yearplan = new YearPlan();
        try {
          yearplan.setPlanYearPlanNO(rs.getString("SEQ_YEAR_PLAN") != null ? rs.getString("SEQ_YEAR_PLAN") : "");
          yearplan.setPlanCode(rs.getString("PLAN_CODE") != null ? rs.getString("PLAN_CODE") : "");
          yearplan.setPlanEduItemCode(rs.getString("PLAN_EDU_ITEM_CODE") != null ?rs.getString("PLAN_EDU_ITEM_CODE") : "");
          yearplan.setPlanEduItemName(rs.getString("PLAN_EDU_ITEM_NAME") != null ?
                                      rs.getString("PLAN_EDU_ITEM_NAME") : "");
          yearplan.setPlanClassTypeCode(rs.getString("PLAN_CLASS_TYPE_CODE") != null ?
                                        rs.getString("PLAN_CLASS_TYPE_CODE") :
                                        "");
          yearplan.setPlanFitGroup(rs.getString("PLAN_FIT_GROUP") != null ?rs.getString("PLAN_FIT_GROUP") : "");
          yearplan.setPlanEduTime(rs.getString("PLAN_EDU_TIME") != null ? rs.getString("PLAN_EDU_TIME") : "");
          yearplan.setPlanPeriorCount(rs.getString("PLAN_PERRIOR_COUNT") != null ?rs.getString("PLAN_PERRIOR_COUNT") : "");
          yearplan.setPlanStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) : "");
          yearplan.setPlanEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10) : "");
          yearplan.setPlanEduTypeCode(rs.getString("PLAN_EDU_TYPE_CODE") != null ? rs.getString("PLAN_EDU_TYPE_CODE") : "");
          yearplan.setPlanEduTypeName(rs.getString("PLAN_EDU_TYPE_NAME") != null ?rs.getString("PLAN_EDU_TYPE_NAME") : "");
          yearplan.setPlanIntendFee(rs.getString("PLAN_INTEND_FEE") != null ? rs.getString("PLAN_INTEND_FEE") : "");
          yearplan.setPlanTotalEduTime(rs.getString("TOTALTIME") != null ?rs.getString("TOTALTIME") : "");
          yearplan.setPlanTotalPeriorCount(rs.getString("TOTALPERRIOR") != null ?rs.getString("TOTALPERRIOR") : "");
          yearplan.setPlanTotalFee(rs.getString("TOTALFEE") != null ?rs.getString("TOTALFEE") : "");
          yearplan.setPlanColor(rs.getString("PLAN_COLOR") != null ? rs.getString("PLAN_COLOR") : "");
          yearplan.setManagerDepartment(rs.getString("MANAGE_DEPARTMENT") != null ? rs.getString("MANAGE_DEPARTMENT") : "");
          yearplan.setPromotionNeed(rs.getString("PROMOTION_NEEDED") != null ? rs.getString("PROMOTION_NEEDED") : "");
          yearplan.setPromotionNeedCode(rs.getString("PROMOTION_NEEDED_NAME") != null ? rs.getString("PROMOTION_NEEDED_NAME") : "");
          yearplan.setPlanOrTemp(rs.getString("TYPE_EDU_RESULT") != null ? rs.getString("TYPE_EDU_RESULT") : "");
          yearplan.setTypeEduName(rs.getString("TYPE_EDU_NAME") != null ? rs.getString("TYPE_EDU_NAME") : "");

          yearplan.setPlanEduTimeDanWei(rs.getString("PLAN_EDU_TIME_DANWEI") != null ? Integer.valueOf(rs.getString("PLAN_EDU_TIME_DANWEI")) : Integer.valueOf("0"));
          yearplan.setPlaneduremark(rs.getString("PLAN_EDU_REMARK") != null ? rs.getString("PLAN_EDU_REMARK") : "");
          yearplan.setPlaneduempnum(rs.getString("PLAN_EDU_EMP_NUM") != null ? rs.getString("PLAN_EDU_EMP_NUM") : "");
        }
        catch (SQLException ex) {
          Logger.getLogger(getClass()).error(ex.toString());
        }
        return yearplan;
      }
      private YearPlan createYearPlanForCalender(ResultSet rs){
          YearPlan yearplan = new YearPlan();
            try {
              yearplan.setPlanYearPlanNO(rs.getString("SEQ_YEAR_PLAN") != null ? rs.getString("SEQ_YEAR_PLAN") : "");
              yearplan.setPlanCode(rs.getString("PLAN_CODE") != null ? rs.getString("PLAN_CODE") : "");
              yearplan.setPlanEduItemCode(rs.getString("PLAN_EDU_ITEM_CODE") != null ?rs.getString("PLAN_EDU_ITEM_CODE") : "");
              yearplan.setPlanClassTypeCode(rs.getString("PLAN_CLASS_TYPE_CODE") != null ?
                                            rs.getString("PLAN_CLASS_TYPE_CODE") :
                                            "");
              yearplan.setPlanFitGroup(rs.getString("PLAN_FIT_GROUP") != null ?rs.getString("PLAN_FIT_GROUP") : "");
              yearplan.setPlanEduTime(rs.getString("PLAN_EDU_TIME") != null ? rs.getString("PLAN_EDU_TIME") : "");
              yearplan.setPlanPeriorCount(rs.getString("PLAN_PERRIOR_COUNT") != null ?rs.getString("PLAN_PERRIOR_COUNT") : "");
              yearplan.setPlanStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) : "");
              yearplan.setPlanEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10) : "");
              yearplan.setPlanEduTypeCode(rs.getString("PLAN_EDU_TYPE_CODE") != null ? rs.getString("PLAN_EDU_TYPE_CODE") : "");
              yearplan.setPlanIntendFee(rs.getString("PLAN_INTEND_FEE") != null ? rs.getString("PLAN_INTEND_FEE") : "");
              yearplan.setPlanColor(rs.getString("PLAN_COLOR") != null ? rs.getString("PLAN_COLOR") : "");
              yearplan.setYearPlanCalenderFlag(rs.getString("FLAG") != null ? rs.getString("FLAG") : "");


              yearplan.setPlaneduremark(rs.getString("PLAN_EDU_REMARK") != null ? rs.getString("PLAN_EDU_REMARK") : "");
            }
            catch (SQLException ex) {
              Logger.getLogger(getClass()).error(ex.toString());
            }
            return yearplan;
      }

  public void updateYearPlan(YearPlan yearplan){
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "update ETN_YEAR_PLAN set "+
                 "plan_code =? ,PLAN_EDU_ITEM_CODE=?, "+
                 "PLAN_CLASS_TYPE_CODE=?,PLAN_FIT_GROUP=?,"+
                 "PLAN_EDU_TIME=?, PLAN_PERRIOR_COUNT=?, PLAN_START_DATE=to_date(?,'yyyy-mm-dd'), "+
                 "PLAN_END_DATE=to_date(?,'yyyy-mm-dd'),  PLAN_EDU_TYPE_CODE=?, PLAN_INTEND_FEE=?,PLAN_COLOR=?,MANAGE_DEPARTMENT=?, PROMOTION_NEEDED=?,TYPE_EDU_RESULT=? ,PLAN_EDU_TIME_DANWEI=?,PLAN_EDU_REMARK=?,PLAN_EDU_EMP_NUM=?"+
                 " where SEQ_YEAR_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
     try {
       conn = ConnBean.getConn();
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, yearplan.getPlanCode());
       pstmt.setString(2, yearplan.getPlanEduItemCode());
       pstmt.setString(3, yearplan.getPlanClassTypeCode());
       pstmt.setString(4, yearplan.getPlanFitGroup());
       pstmt.setString(5, yearplan.getPlanEduTime());
       pstmt.setInt(6, Integer.parseInt(yearplan.getPlanPeriorCount()));
       pstmt.setString(7, yearplan.getPlanStartDate());
       pstmt.setString(8, yearplan.getPlanEndDate());
       pstmt.setString(9, yearplan.getPlanEduTypeCode());
       pstmt.setInt(10, Integer.parseInt(yearplan.getPlanIntendFee()));
       pstmt.setString(11, yearplan.getPlanColor());
       pstmt.setString(12, yearplan.getManagerDepartment());
       pstmt.setString(13, yearplan.getPromotionNeed());
       pstmt.setString(14,yearplan.getPlanOrTemp());

       pstmt.setInt(15,yearplan.getPlanEduTimeDanWei().intValue());
       pstmt.setString(16,yearplan.getPlaneduremark());
       pstmt.setString(17,yearplan.getPlaneduempnum());
       pstmt.setInt(18, Integer.parseInt(yearplan.getPlanYearPlanNO()));
       
       
       pstmt.executeUpdate();
     }
     catch (NumberFormatException ex) {
       System.out.println("update yearplan ");
       Logger.getLogger(getClass()).error(ex.toString());
     }
     catch (SQLException ex) {
       Logger.getLogger(getClass()).error(ex.toString());
     }finally{
    	 SqlUtil.close(pstmt, conn);
     }
   }
   public void deleteYearPlan(String yearplanno){
     Connection conn = null;
     PreparedStatement pstmt = null;
     String sql = "delete ETN_YEAR_PLAN where SEQ_YEAR_PLAN=?";
     Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, yearplanno);
      pstmt.executeUpdate();
    }
    catch (SQLException ex){
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }
   }
   public void deleteDepartment(String plancode){
     Connection conn= null;
     PreparedStatement pstmt = null;
     String sql = " delete  ETN_YEARPLAN_FIT_DEPT where plan_code = ?";
     Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, plancode);
      pstmt.executeUpdate();
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }

   }
  public void addFitDepartment(String[] deptid,String plancode){
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "insert into ETN_YEARPLAN_FIT_DEPT (PLAN_CODE, FIT_DEPARTMENT_ID) values (?,?)";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, plancode);
      for (int i = 0; i < deptid.length; i++) {
        pstmt.setString(2, deptid[i]);
        pstmt.executeUpdate();
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }
  }
  public Vector getFitDepartment(String plancode){
     Vector vector = new Vector();
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = " select DEPTNAME from ETN_YEARPLAN_FIT_DEPT eyfd,HR_DEPARTMENT hd where eyfd.FIT_DEPARTMENT_ID = hd.DEPTID(+) and plan_code = ?";
     Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, plancode);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(rs.getString("DEPTNAME"));
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }
    return vector;
  }
  public YearPlan getYearPlanItemCodeInEtnPlan(String yearplanno){
    YearPlan yearplan = new YearPlan();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select PLAN_EDU_ITEM_CODE, PLAN_CLASS_TYPE_CODE,PLAN_CODE,PLAN_PERRIOR_COUNT from ETN_YEAR_PLAN where SEQ_YEAR_PLAN =?";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, yearplanno);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        yearplan.setPlanClassTypeCode( rs.getString("PLAN_CLASS_TYPE_CODE")!=null?rs.getString("PLAN_CLASS_TYPE_CODE"):"");
        yearplan.setPlanEduItemCode( rs.getString("PLAN_EDU_ITEM_CODE")!=null?rs.getString("PLAN_EDU_ITEM_CODE"):"");
        yearplan.setYearPlanCode( rs.getString("PLAN_CODE")!=null?rs.getString("PLAN_CODE"):"");
        yearplan.setPlanPeriorCount(rs.getString("PLAN_PERRIOR_COUNT")!=null?rs.getString("PLAN_PERRIOR_COUNT"):"");
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }
    return yearplan;
  }
  public YearPlan getYearPlanTotal(String startdate){
    String startdate_r = startdate.substring(0,4);
    YearPlan yearplan = new YearPlan();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql ="SELECT YEAR_PLAN_YEAR, TOTAL_EDU_TIME, TOTAL_EDU_PERRIOR, TOTAL_EDU_FEE "+
                 " FROM  ETN_YEAR_PLAN_STATISTIC  where SUBSTR (TO_CHAR (YEAR_PLAN_YEAR, 'yyyy-mm-dd'), 1,4) = ? ";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,startdate_r);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        yearplan.setPlanTotalEduTime(rs.getString("TOTAL_EDU_TIME") != null ?
                                     rs.getString("TOTAL_EDU_TIME") : "");
        yearplan.setPlanTotalPeriorCount(rs.getString("TOTAL_EDU_PERRIOR") != null ?
                                         rs.getString("TOTAL_EDU_PERRIOR") : "");
        yearplan.setPlanTotalFee(rs.getString("TOTAL_EDU_FEE") != null ?
                                 rs.getString("TOTAL_EDU_FEE") : "");
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }

    return yearplan;
  }
  public Vector getYearPlanCalender(String today){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql ="select * from ETN_YEARPLAN_FOR_CALENDER_V where PLAN_START_DATE<=to_date(?,'yyyy-mm-dd') and  PLAN_END_DATE>=to_date(?,'yyyy-mm-dd')  "+
                 "order by SEQ_YEAR_PLAN desc";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,today);
      pstmt.setString(2,today);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createYearPlanForCalender(rs));
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(pstmt, conn);
    }
    return vector;
  }


  public boolean IsExitAtWeek(YearPlan yearplan,int currweek){
    String remark=yearplan.getPlaneduremark();
    if(remark==null || remark.trim().equals("")){
      return true;
    }else{
      String r[]=remark.split(",");
      for(int i=0;i<r.length;i++){
        if(Integer.parseInt(r[i])==currweek){
          return true;
        }
      }
      return false;
    }
  }

  public String getPlanCode() {
    return planCode;
  }
  public void setPlanCode(String planCode) {
    this.planCode = planCode;
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
  public String getPlanClassTypeCode() {
    return planClassTypeCode;
  }
  public void setPlanClassTypeCode(String planClassTypeCode) {
    this.planClassTypeCode = planClassTypeCode;
  }
  public String getPlanClassTypeName() {
    return planClassTypeName;
  }
  public void setPlanClassTypeName(String planClassTypeName) {
    this.planClassTypeName = planClassTypeName;
  }
  public String getPlanFitDepartMentType() {
    return planFitDepartMentType;
  }
  public void setPlanFitDepartMentType(String planFitDepartMentType) {
    this.planFitDepartMentType = planFitDepartMentType;
  }
  public String getPlanDepartmentTypeName() {
    return planDepartmentTypeName;
  }
  public void setPlanDepartmentTypeName(String planDepartmentTypeName) {
    this.planDepartmentTypeName = planDepartmentTypeName;
  }
  public String getPlanFitGroup() {
    return planFitGroup;
  }
  public void setPlanFitGroup(String planFitGroup) {
    this.planFitGroup = planFitGroup;
  }
  public String getPlanEduTime() {
    return planEduTime;
  }
  public void setPlanEduTime(String planEduTime) {
    this.planEduTime = planEduTime;
  }
  public String getPlanPeriorCount() {
    return planPeriorCount;
  }
  public void setPlanPeriorCount(String planPeriorCount) {
    this.planPeriorCount = planPeriorCount;
  }
  public String getPlanStartDate() {
    return planStartDate;
  }
  public void setPlanStartDate(String planStartDate) {
    this.planStartDate = planStartDate;
  }
  public String getPlanEndDate() {
    return planEndDate;
  }
  public void setPlanEndDate(String planEndDate) {
    this.planEndDate = planEndDate;
  }
  public String getPlanEduTypeCode() {
    return planEduTypeCode;
  }
  public void setPlanEduTypeCode(String planEduTypeCode) {
    this.planEduTypeCode = planEduTypeCode;
  }
  public String getPlanEduTypeName() {
    return planEduTypeName;
  }
  public void setPlanEduTypeName(String planEduTypeName) {
    this.planEduTypeName = planEduTypeName;
  }
  public String getPlanIntendFee() {
    return planIntendFee;
  }
  public void setPlanIntendFee(String planIntendFee) {
    this.planIntendFee = planIntendFee;
  }
  public String getPlanTotalEduTime() {
    return planTotalEduTime;
  }
  public void setPlanTotalEduTime(String planTotalEduTime) {
    this.planTotalEduTime = planTotalEduTime;
  }
  public String getPlanTotalPeriorCount() {
    return planTotalPeriorCount;
  }
  public void setPlanTotalPeriorCount(String planTotalPeriorCount) {
    this.planTotalPeriorCount = planTotalPeriorCount;
  }
  public String getPlanTotalFee() {
    return planTotalFee;
  }
  public void setPlanTotalFee(String planTotalFee) {
    this.planTotalFee = planTotalFee;
  }
  public String getPlanYearPlanNO() {
    return planYearPlanNO;
  }
  public void setPlanYearPlanNO(String planYearPlanNO) {
    this.planYearPlanNO = planYearPlanNO;
  }
  public String getPlanColor() {
    return planColor;
  }
  public void setPlanColor(String planColor) {
    this.planColor = planColor;
  }
  public String getYearPlanCode() {
    return yearPlanCode;
  }
  public void setYearPlanCode(String yearPlanCode) {
    this.yearPlanCode = yearPlanCode;
  }
  public String getYearPlanCalenderFlag() {
    return yearPlanCalenderFlag;
  }
  public void setYearPlanCalenderFlag(String yearPlanCalenderFlag) {
    this.yearPlanCalenderFlag = yearPlanCalenderFlag;
  }
  public String getManagerDepartment() {
    return managerDepartment;
  }
  public void setManagerDepartment(String managerDepartment) {
    this.managerDepartment = managerDepartment;
  }
  public String getPromotionNeed() {
    return promotionNeed;
  }
  public void setPromotionNeed(String promotionNeed) {
    this.promotionNeed = promotionNeed;
  }
  public String getPromotionNeedCode() {
    return promotionNeedCode;
  }
  public void setPromotionNeedCode(String promotionNeedCode) {
    this.promotionNeedCode = promotionNeedCode;
  }
  public String getType_edu_result() {
    return type_edu_result;
  }
  public void setType_edu_result(String type_edu_result) {
    this.type_edu_result = type_edu_result;
  }
  public String getPlanOrTemp() {
    return planOrTemp;
  }
  public void setPlanOrTemp(String planOrTemp) {
    this.planOrTemp = planOrTemp;
  }
  public String getTypeEduName() {
    return typeEduName;
  }
  public void setTypeEduName(String typeEduName) {
    this.typeEduName = typeEduName;
  }
  public Integer getPlanEduTimeDanWei() {
    return planEduTimeDanWei;
  }
  public void setPlanEduTimeDanWei(Integer planEduTimeDanWei) {
    this.planEduTimeDanWei = planEduTimeDanWei;
  }
  public String getPlaneduremark() {
    return planeduremark;
  }
  public void setPlaneduremark(String planeduremark) {
    this.planeduremark = planeduremark;
  }
  public void setPlaneduempnum(String planeduempnum){
      this.planeduempnum=planeduempnum;
  }
  public String getPlaneduempnum(){
      return this.planeduempnum;
  }

}
