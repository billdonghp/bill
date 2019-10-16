package com.ait.etn;


import java.sql.*;
import com.ait.util.*;


public class EndResult {
  private static ServiceLocator services;
  private String etnPlanNo;
  private String eligibilityRate;
  private String makeUpSuccessRate;
  private String countFellBack;
  public EndResult() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public void addEtnResult(EndResult endresult)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "insert into ETN_WHOLE_RESULT (ETN_PLAN_NO, ELIGIBILITY_RATE, MAKEUP_SUCCESS_RATE, "+
                  "COUNT_FELL_BACK)values (?,?,?,?)";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, endresult.getEtnPlanNo());
      pstmt.setString(2, endresult.getEligibilityRate());
      pstmt.setString(3, endresult.getMakeUpSuccessRate());
      pstmt.setString(4, endresult.getCountFellBack());
      pstmt.executeUpdate();
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      pstmt.close();
      conn.close();
    }
  }
  public EndResult getEndResult(String etnplanno)throws Exception{
    EndResult endresult = new EndResult();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="select  ELIGIBILITY_RATE, MAKEUP_SUCCESS_RATE,COUNT_FELL_BACK from ETN_WHOLE_RESULT where ETN_PLAN_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      if (rs.next()) {
        endresult = this.createEndResult(rs);
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
    return endresult;
  }


  private EndResult createEndResult(ResultSet rs) throws Exception {
       EndResult endresult = new EndResult();
        try {
          endresult.setEligibilityRate(rs.getString("ELIGIBILITY_RATE") != null ? rs.getString("ELIGIBILITY_RATE") :"");
          endresult.setMakeUpSuccessRate(rs.getString("MAKEUP_SUCCESS_RATE") != null ? rs.getString("MAKEUP_SUCCESS_RATE") :"");
          endresult.setCountFellBack(rs.getString("COUNT_FELL_BACK") != null ? rs.getString("COUNT_FELL_BACK") : "");
        }
        catch (SQLException ex) {
        }
        return endresult;
      }
 public void updateEtnResult(EndResult endresult)throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "update ETN_WHOLE_RESULT set  ELIGIBILITY_RATE=?, MAKEUP_SUCCESS_RATE=?, "+
                      "COUNT_FELL_BACK=? where ETN_PLAN_NO=?";

        try {
          conn = services.getConnection();
          pstmt = conn.prepareStatement(SQL);
          pstmt.setString(1, endresult.getEligibilityRate());
          pstmt.setString(2, endresult.getMakeUpSuccessRate());
          pstmt.setString(3, endresult.getCountFellBack());
          pstmt.setString(4, endresult.getEtnPlanNo());
          pstmt.executeUpdate();
        }
        catch (SQLException ex) {
        }
        catch (ServiceLocatorException ex) {
        }
        finally{
          pstmt.close();
          conn.close();
        }
      }







  public String getEtnPlanNo() {
    return etnPlanNo;
  }
  public void setEtnPlanNo(String etnPlanNo) {
    this.etnPlanNo = etnPlanNo;
  }
  public String getEligibilityRate() {
    return eligibilityRate;
  }
  public void setEligibilityRate(String eligibilityRate) {
    this.eligibilityRate = eligibilityRate;
  }
  public String getMakeUpSuccessRate() {
    return makeUpSuccessRate;
  }
  public void setMakeUpSuccessRate(String makeUpSuccessRate) {
    this.makeUpSuccessRate = makeUpSuccessRate;
  }
  public String getCountFellBack() {
    return countFellBack;
  }
  public void setCountFellBack(String countFellBack) {
    this.countFellBack = countFellBack;
  }
}
