package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Protocol {
  private static ServiceLocator services;
  private String protocolNo;
  private String protocolName;
  private String protocolCode;
  private String protocolPersonalName;
  private String protocolStartDate;
  private String protocolEndDate;
  private String protocolTerm;
  private String protocolContent;
  private String protocolEmpID;
  private String classTypeName;
  private String fellBackFee;
  private String yearStartDate;
  private String yearPlanEndDate;
  private String yearPlanNo;
  public Protocol() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public void addProtocol(Protocol protocol)throws Exception{
    Connection conn= null;
    PreparedStatement pstmt = null;
    String SQL = "insert into ETN_PROTOCOL (SEQ_PROTOCOL_ETN, PROTOCOL_CODE, PROTOCOL_NAME, "+
                 "PROTOCOL_PERSONAL_NAME, PROTOCOL_START_DATE, PROTOCOL_END_DATE, "+
                  "PROTOCOL_TERM, PROTOCOL_CONTENT,PROTOCOL_EMPID,YEARPLAN_NO, FELL_BACK_FEE)"+
                  "values(ETN_PROTOCOL_SEQ.nextval,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, protocol.getProtocolCode());
      pstmt.setString(2, protocol.getProtocolName());
      pstmt.setString(3, protocol.getProtocolPersonalName());
      pstmt.setString(4, protocol.getProtocolStartDate());
      pstmt.setString(5, protocol.getProtocolEndDate());
      pstmt.setString(6, protocol.getProtocolTerm());
      pstmt.setString(7, protocol.getProtocolContent());
      pstmt.setString(8, protocol.getProtocolEmpID());
      pstmt.setString(9, protocol.getYearPlanNo());
      pstmt.setString(10, protocol.getFellBackFee());
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
  public void updateProtocolDateToContract(String Startdate,String enddate,String empid)throws Exception{
    Connection conn= null;
    PreparedStatement pstmt = null;
    String SQL = "update HR_CONTRACT set START_SERVICE_DATE=to_date(?,'yyyy-mm-dd'),END_SERVICE_DATE=to_date(?,'yyyy-mm-dd') where EMPID=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, Startdate);
      pstmt.setString(2, enddate);
      pstmt.setString(3, empid);
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

  /**
   *
   * @throws Exception
   * @return Vector
   */
  public Vector getProtocol()throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="select * from ETN_PROTOCOL_V order by SEQ_PROTOCOL_ETN desc";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      while(rs.next()){
        vector.add(this.createProtocol(rs));
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return vector;
  }
  public String checkEmpidExist(String empid)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select EMPID from hr_employee where EMPID=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, empid);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getString("EMPID");
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return "";
  }
  public Protocol getProtocol(String protocolno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="select * from ETN_PROTOCOL_V where SEQ_PROTOCOL_ETN=? order by SEQ_PROTOCOL_ETN desc";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(protocolno));
      rs = pstmt.executeQuery();
      if(rs.next()){
        return this.createProtocol(rs);
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return null;
  }

  /**
   *
   * @param pc PageControl
   * @throws Exception
   * @return Vector
   */
  public Vector getProtocol(PageControl pc)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="select * from ETN_PROTOCOL_V order by SEQ_PROTOCOL_ETN desc";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();

      while( (pc.getI() < pc.getIntPagedSize()) && rs.next()){
        vector.add(this.createProtocol(rs));
        pc.setiii();
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return vector;
  }

  private Protocol createProtocol(ResultSet rs)throws Exception{
    Protocol protocol = new Protocol();
      try {
        protocol.setProtocolNo(rs.getString("SEQ_PROTOCOL_ETN") != null ?rs.getString("SEQ_PROTOCOL_ETN") : "");
        protocol.setProtocolCode(rs.getString("PROTOCOL_CODE") != null ?rs.getString("PROTOCOL_CODE") : "");
        protocol.setProtocolName(rs.getString("PROTOCOL_NAME") != null ?rs.getString("PROTOCOL_NAME") : "");
        protocol.setProtocolPersonalName(rs.getString("PROTOCOL_PERSONAL_NAME") != null ?rs.getString("PROTOCOL_PERSONAL_NAME") :"");
        protocol.setProtocolStartDate(rs.getString("PROTOCOL_START_DATE") != null ?rs.getString("PROTOCOL_START_DATE").substring(0,10) : "");
        protocol.setProtocolEndDate(rs.getString("PROTOCOL_END_DATE") != null ?rs.getString("PROTOCOL_END_DATE").substring(0,10) : "");
        protocol.setProtocolTerm(rs.getString("PROTOCOL_TERM") != null ?rs.getString("PROTOCOL_TERM") : "");
        protocol.setProtocolContent(rs.getString("PROTOCOL_CONTENT") != null ?rs.getString("PROTOCOL_CONTENT") : "");
        protocol.setProtocolEmpID(rs.getString("PROTOCOL_EMPID") != null ?rs.getString("PROTOCOL_EMPID") : "");
        protocol.setYearPlanNo(rs.getString("YEARPLAN_NO") != null ?rs.getString("YEARPLAN_NO") : "");
        protocol.setFellBackFee(rs.getString("FELL_BACK_FEE") != null ?rs.getString("FELL_BACK_FEE") : "");
        protocol.setYearStartDate(rs.getString("PLAN_START_DATE") != null ?rs.getString("PLAN_START_DATE").substring(0,10) : "");
        protocol.setYearPlanEndDate(rs.getString("PLAN_END_DATE") != null ?rs.getString("PLAN_END_DATE").substring(0,10) : "");
        protocol.setClassTypeName(rs.getString("PLAN_CLASS_TYPE_CODE") != null ?rs.getString("PLAN_CLASS_TYPE_CODE") : "");
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
      return protocol;
  }
  public void updateProtocol(Protocol protocol)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_PROTOCOL set PROTOCOL_CODE=?, PROTOCOL_NAME=?, "+
                  "PROTOCOL_PERSONAL_NAME=?, PROTOCOL_START_DATE=to_date(?,'yyyy-mm-dd'), PROTOCOL_END_DATE=to_date(?,'yyyy-mm-dd'), "+
                 " PROTOCOL_TERM=?, PROTOCOL_CONTENT=?,PROTOCOL_EMPID=?,YEARPLAN_NO=?, FELL_BACK_FEE=? where SEQ_PROTOCOL_ETN=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, protocol.getProtocolCode());
      pstmt.setString(2, protocol.getProtocolName());
      pstmt.setString(3, protocol.getProtocolPersonalName());
      pstmt.setString(4, protocol.getProtocolStartDate());
      pstmt.setString(5, protocol.getProtocolEndDate());
      pstmt.setString(6, protocol.getProtocolTerm());
      pstmt.setString(7, protocol.getProtocolContent());
      pstmt.setString(8, protocol.getProtocolEmpID());
      pstmt.setString(9, protocol.getYearPlanNo());
      pstmt.setString(10, protocol.getFellBackFee());
      pstmt.setInt(11, Integer.parseInt(protocol.getProtocolNo()));
      pstmt.executeUpdate();
    }
    catch (NumberFormatException ex) {
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      pstmt.close();
      conn.close();
    }
  }


  public void deleteProtocol(String protocolno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL= "delete ETN_PROTOCOL where SEQ_PROTOCOL_ETN = ?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, Integer.parseInt(protocolno));
      pstmt.executeUpdate();
    }
    catch (NumberFormatException ex) {
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
  public String getProtocolContent(String protocolno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select PROTOCOL_CONTENT from ETN_PROTOCOL where SEQ_PROTOCOL_ETN=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, Integer.parseInt(protocolno));
      rs = pstmt.executeQuery();
      if(rs.next()){
        return rs.getString("PROTOCOL_CONTENT");
      }
    }
    catch (NumberFormatException ex) {
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally{
      rs.close();
      pstmt.close();
      conn.close();
    }
    return "";
  }
  public String getProtocolNo() {
    return protocolNo;
  }
  public void setProtocolNo(String protocolNo) {
    this.protocolNo = protocolNo;
  }
  public String getProtocolName() {
    return protocolName;
  }
  public void setProtocolName(String protocolName) {
    this.protocolName = protocolName;
  }
  public String getProtocolCode() {
    return protocolCode;
  }
  public void setProtocolCode(String protocolCode) {
    this.protocolCode = protocolCode;
  }
  public String getProtocolPersonalName() {
    return protocolPersonalName;
  }
  public void setProtocolPersonalName(String protocolPersonalName) {
    this.protocolPersonalName = protocolPersonalName;
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
  public String getProtocolContent() {
    return protocolContent;
  }
  public void setProtocolContent(String protocolContent) {
    this.protocolContent = protocolContent;
  }
  public String getProtocolEmpID() {
    return protocolEmpID;
  }
  public void setProtocolEmpID(String protocolEmpID) {
    this.protocolEmpID = protocolEmpID;
  }
  public String getClassTypeName() {
    return classTypeName;
  }
  public void setClassTypeName(String classTypeName) {
    this.classTypeName = classTypeName;
  }
  public String getFellBackFee() {
    return fellBackFee;
  }
  public void setFellBackFee(String fellBackFee) {
    this.fellBackFee = fellBackFee;
  }
  public String getYearStartDate() {
    return yearStartDate;
  }
  public void setYearStartDate(String yearStartDate) {
    this.yearStartDate = yearStartDate;
  }
  public String getYearPlanEndDate() {
    return yearPlanEndDate;
  }
  public void setYearPlanEndDate(String yearPlanEndDate) {
    this.yearPlanEndDate = yearPlanEndDate;
  }
  public String getYearPlanNo() {
    return yearPlanNo;
  }
  public void setYearPlanNo(String yearPlanNo) {
    this.yearPlanNo = yearPlanNo;
  }
}
