package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class EtnFee {
  private static ServiceLocator services;
  private String teachTextFee;
  private String groundFee;
  private String eattingFee;
  private String quarterFee;
  private String trafficFee;
  private String passportFee;
  private String visaFee;
  private String insureFee;
  private String livingFee;
  private String otherFee;
  private String etnPlanNo;
  private String teacherNo;
  private String teacherName;
  private String teachTime;
  private String teacherFeeHour;
  private String teacherCountFee;
  private String etnTeacherFee;
  private String totalFee;
  public EtnFee() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public void addEtnFee(EtnFee etnfee)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "insert into Etn_Fee(ETN_PLAN_NO, FEE_TEACH_TEXT, FEE_GROUND, "+
                  "FEE_EATTING, FEE_QUARTER, FEE_TRRAFIC, "+
                  "FEE_PASSPORT, FEE_VISA, FEE_INSURE, "+
                  "FEE_LIVING, FEE_OTHER)values(?,?,?,?,?,?,?,?,?,?,?)";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, Integer.parseInt(etnfee.getEtnPlanNo()));
      pstmt.setFloat(2, Float.parseFloat(etnfee.getTeachTextFee()));
      pstmt.setFloat(3, Float.parseFloat(etnfee.getGroundFee()));
      pstmt.setFloat(4, Float.parseFloat(etnfee.getEattingFee()));
      pstmt.setFloat(5, Float.parseFloat(etnfee.getQuarterFee()));
      pstmt.setFloat(6, Float.parseFloat(etnfee.getTrafficFee()));
      pstmt.setFloat(7, Float.parseFloat(etnfee.getPassportFee()));
      pstmt.setFloat(8, Float.parseFloat(etnfee.getVisaFee()));
      pstmt.setFloat(9, Float.parseFloat(etnfee.getInsureFee()));
      pstmt.setFloat(10, Float.parseFloat(etnfee.getLivingFee()));
      pstmt.setFloat(11, Float.parseFloat(etnfee.getOtherFee()));
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
  public Vector getTeacherExist(String etnplanno)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select TEACHER_NO, TEACHER_NAME, OUT_TEACHER_FEE from ETN_PLAN_TEACHER_V where ETN_PLAN_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createTeacherExist(rs));
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
  public void addEtnTeacherFee(String etnplanno,String[] teacherno,String[] teachername,String[] teacherfeehour,String[] teachedutime)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "insert into ETN_TEACHER_FEE (ETN_PLAN_NO, TEACHER_NO, TEACHER_NAME, TEACHER_EDU_TIME, TEACHER_FEE_HOUR) values(?,?,?,?,?)";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      for(int i=0;i<teacherno.length;i++){
        pstmt.setInt(2,Integer.parseInt(teacherno[i]));
        pstmt.setString(3,StringUtil.toCN(teachername[i]));
        pstmt.setString(4,teachedutime[i]);
        pstmt.setInt(5,Integer.parseInt(teacherfeehour[i]));
        pstmt.executeUpdate();
      }
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
  public Vector getTeacherFee(String etnplanno)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select TEACHER_NO, TEACHER_NAME,TEACHER_EDU_TIME,TEACHER_FEE_HOUR,(TEACHER_EDU_TIME*TEACHER_FEE_HOUR) as teachercountfee from ETN_TEACHER_FEE where ETN_PLAN_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createTeacherFee(rs));
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
  public String getTeacherFeeByTwoNo(String etnplanno,String teacherno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select TEACHER_EDU_TIME  from ETN_TEACHER_FEE where ETN_PLAN_NO=? and TEACHER_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      pstmt.setInt(2,Integer.parseInt(teacherno));
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getString("TEACHER_EDU_TIME")!=null?rs.getString("TEACHER_EDU_TIME"):"";
      }
    }
    catch (SQLException ex) {
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
    return "";
  }

  private EtnFee createTeacherExist(ResultSet rs) throws Exception{
    EtnFee etnfee = new EtnFee();
      try {
        etnfee.setTeacherNo(rs.getString("TEACHER_NO") != null ? rs.getString("TEACHER_NO") : "");
        etnfee.setTeacherName(rs.getString("TEACHER_NAME") != null ? rs.getString("TEACHER_NAME") : "");
        etnfee.setTeacherFeeHour(rs.getString("OUT_TEACHER_FEE") != null ? rs.getString("OUT_TEACHER_FEE") : "");
        }
      catch (SQLException ex) {
      }
      return etnfee;
    }
    private EtnFee createTeacherFee(ResultSet rs) throws Exception{
      EtnFee etnfee = new EtnFee();
        try {
          etnfee.setTeacherNo(rs.getString("TEACHER_NO") != null ? rs.getString("TEACHER_NO") : "");
          etnfee.setTeacherName(rs.getString("TEACHER_NAME") != null ? rs.getString("TEACHER_NAME") : "");
          etnfee.setTeacherFeeHour(rs.getString("TEACHER_FEE_HOUR") != null ? rs.getString("TEACHER_FEE_HOUR") : "");
          etnfee.setTeachTime(rs.getString("TEACHER_EDU_TIME") != null ? rs.getString("TEACHER_EDU_TIME") : "");
          etnfee.setTeacherCountFee(rs.getString("teachercountfee") != null ? rs.getString("teachercountfee") : "");
          }
        catch (SQLException ex) {
        }
        return etnfee;
      }
    public EtnFee getEtnFee(String etnplanno)throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String SQL = "SELECT FEE_TEACH_TEXT, FEE_GROUND, "+
                   " FEE_EATTING, FEE_QUARTER, FEE_TRRAFIC, "+
                   "  FEE_PASSPORT, FEE_VISA, FEE_INSURE, "+
                   "  FEE_LIVING,FEE_OTHER, COUNT_TEACHER_FEE, TOTAL_FEE "+
                    " FROM ETN_PLAN_FEE_V where ETN_PLAN_NO=?";
      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL);
        pstmt.setInt(1,Integer.parseInt(etnplanno));
        rs = pstmt.executeQuery();
        while (rs.next()) {
          return this.createEtnFee(rs);
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
      return null;
    }
    private EtnFee createEtnFee(ResultSet rs) throws Exception{
      EtnFee etnfee = new EtnFee();
        try {
          etnfee.setTeachTextFee(rs.getString("FEE_TEACH_TEXT") != null ? rs.getString("FEE_TEACH_TEXT") : "");
          etnfee.setGroundFee(rs.getString("FEE_GROUND") != null ? rs.getString("FEE_GROUND") : "");
          etnfee.setEattingFee(rs.getString("FEE_EATTING") != null ? rs.getString("FEE_EATTING") : "");
          etnfee.setQuarterFee(rs.getString("FEE_QUARTER") != null ? rs.getString("FEE_QUARTER") : "");
          etnfee.setTrafficFee(rs.getString("FEE_TRRAFIC") != null ? rs.getString("FEE_TRRAFIC") : "");
          etnfee.setPassportFee(rs.getString("FEE_PASSPORT") != null ? rs.getString("FEE_PASSPORT") : "");
          etnfee.setVisaFee(rs.getString("FEE_VISA") != null ? rs.getString("FEE_VISA") : "");
          etnfee.setInsureFee(rs.getString("FEE_INSURE") != null ? rs.getString("FEE_INSURE") : "");
          etnfee.setLivingFee(rs.getString("FEE_LIVING") != null ? rs.getString("FEE_LIVING") : "");
          etnfee.setOtherFee(rs.getString("FEE_OTHER") != null ? rs.getString("FEE_OTHER") : "");
          etnfee.setEtnTeacherFee(rs.getString("COUNT_TEACHER_FEE") != null ? rs.getString("COUNT_TEACHER_FEE") : "");
          etnfee.setTotalFee(rs.getString("TOTAL_FEE") != null ? rs.getString("TOTAL_FEE") : "");
         }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return etnfee;
      }
    public void updateEtnFee(EtnFee etnfee)throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      String SQL = "update ETN_FEE set FEE_TEACH_TEXT=?, FEE_GROUND=?, "+
                  "FEE_EATTING=?, FEE_QUARTER=?, FEE_TRRAFIC=?, "+
                  "FEE_PASSPORT=?, FEE_VISA=?, FEE_INSURE=?, "+
                  "FEE_LIVING=?, FEE_OTHER=?"+
                  "where  ETN_PLAN_NO=?";

     try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setFloat(1, Float.parseFloat(etnfee.getTeachTextFee()));
      pstmt.setFloat(2, Float.parseFloat(etnfee.getGroundFee()));
      pstmt.setFloat(3, Float.parseFloat(etnfee.getEattingFee()));
      pstmt.setFloat(4, Float.parseFloat(etnfee.getQuarterFee()));
      pstmt.setFloat(5, Float.parseFloat(etnfee.getTrafficFee()));
      pstmt.setFloat(6, Float.parseFloat(etnfee.getPassportFee()));
      pstmt.setFloat(7, Float.parseFloat(etnfee.getVisaFee()));
      pstmt.setFloat(8, Float.parseFloat(etnfee.getInsureFee()));
      pstmt.setFloat(9, Float.parseFloat(etnfee.getLivingFee()));
      pstmt.setFloat(10, Float.parseFloat(etnfee.getOtherFee()));
      pstmt.setInt(11, Integer.parseInt(etnfee.getEtnPlanNo()));
      pstmt.executeQuery();
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
  public void updateEtnTeacherFee(String etnplanno,String[] teacherno,String[] teachedutime)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_TEACHER_FEE set TEACHER_EDU_TIME=? where ETN_PLAN_NO=? and TEACHER_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(2,Integer.parseInt(etnplanno));
      for(int i=0;i<teacherno.length;i++){
        pstmt.setInt(3,Integer.parseInt(teacherno[i]));
        pstmt.setInt(1,Integer.parseInt(teachedutime[i]));
        pstmt.executeUpdate();
      }
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
  public void DeleteEtnTeacherFee(String etnplanno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "delete ETN_TEACHER_FEE where ETN_PLAN_NO = ?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
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




  public String getTeachTextFee() {
    return teachTextFee;
  }
  public void setTeachTextFee(String teachTextFee) {
    this.teachTextFee = teachTextFee;
  }
  public String getGroundFee() {
    return groundFee;
  }
  public void setGroundFee(String groundFee) {
    this.groundFee = groundFee;
  }
  public String getEattingFee() {
    return eattingFee;
  }
  public void setEattingFee(String eattingFee) {
    this.eattingFee = eattingFee;
  }
  public String getQuarterFee() {
    return quarterFee;
  }
  public void setQuarterFee(String quarterFee) {
    this.quarterFee = quarterFee;
  }
  public String getTrafficFee() {
    return trafficFee;
  }
  public void setTrafficFee(String trafficFee) {
    this.trafficFee = trafficFee;
  }
  public String getPassportFee() {
    return passportFee;
  }
  public void setPassportFee(String passportFee) {
    this.passportFee = passportFee;
  }
  public String getVisaFee() {
    return visaFee;
  }
  public void setVisaFee(String visaFee) {
    this.visaFee = visaFee;
  }
  public String getInsureFee() {
    return insureFee;
  }
  public void setInsureFee(String insureFee) {
    this.insureFee = insureFee;
  }
  public String getLivingFee() {
    return livingFee;
  }
  public void setLivingFee(String livingFee) {
    this.livingFee = livingFee;
  }
  public String getOtherFee() {
    return otherFee;
  }
  public void setOtherFee(String otherFee) {
    this.otherFee = otherFee;
  }
  public String getEtnPlanNo() {
    return etnPlanNo;
  }
  public void setEtnPlanNo(String etnPlanNo) {
    this.etnPlanNo = etnPlanNo;
  }
  public String getTeacherNo() {
    return teacherNo;
  }
  public void setTeacherNo(String teacherNo) {
    this.teacherNo = teacherNo;
  }
  public String getTeacherName() {
    return teacherName;
  }
  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }
  public String getTeachTime() {
    return teachTime;
  }
  public void setTeachTime(String teachTime) {
    this.teachTime = teachTime;
  }
  public String getTeacherFeeHour() {
    return teacherFeeHour;
  }
  public void setTeacherFeeHour(String teacherFeeHour) {
    this.teacherFeeHour = teacherFeeHour;
  }
  public String getTeacherCountFee() {
    return teacherCountFee;
  }
  public void setTeacherCountFee(String teacherCountFee) {
    this.teacherCountFee = teacherCountFee;
  }
  public String getEtnTeacherFee() {
    return etnTeacherFee;
  }
  public void setEtnTeacherFee(String etnTeacherFee) {
    this.etnTeacherFee = etnTeacherFee;
  }
  public String getTotalFee() {
    return totalFee;
  }
  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }
}
