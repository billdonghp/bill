package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class Teacher {
  private static ServiceLocator services;
  private String teacherNo;
  private String teacherName;
  private String teacherTypeCode;
  private String teacherTypeName;
  private String teacherBelongTo;
  private String teacherConnection;
  private String teacherFeeHour;
  private String teacherRecord;
  private String teacherModifyDate;
  private String teacherModifyPerson;
  private String teacherSex;
  private String teacherEnableClass;
  private String teacherSexName;
  private String teacherChecked;
  public Teacher() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public void addTeacher(Teacher teacher)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "insert into  ETN_TEACHER (SEQ_ETN_TEACHER, TEACHER_NAME, TEACHER_BELONG,"+
                 " TEACHER_SEX, TEACHER_ENABLE_CLASS, OUT_TEACHER_CONTENT, "+
                 " OUT_TEACHER_CONNECTION, OUT_TEACHER_FEE, TEACHER_FLAG)values(ETN_TEACHER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
      try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, teacher.getTeacherName());
      pstmt.setString(2, teacher.getTeacherBelongTo());
      pstmt.setString(3, teacher.getTeacherSex());
      pstmt.setString(4, teacher.getTeacherEnableClass());
      pstmt.setString(5, teacher.getTeacherRecord());
      pstmt.setString(6, teacher.getTeacherConnection());
      pstmt.setInt(7, Integer.parseInt(teacher.getTeacherFeeHour()));
      pstmt.setString(8, teacher.getTeacherTypeCode());
      pstmt.executeUpdate();
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
  public Vector getTeacherEtnPlanModify(String etnplanno)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "  SELECT * FROM ( select ETN_TEACHER_V.*, case when SEQ_ETN_TEACHER in  "+
                 "(select TEACHER_NO from ETN_PLAN_TEACHER where ETN_PLAN_NO = ?) then 'checked' else '' end as checked  "+
                "  from  ETN_TEACHER_V)  ";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createTeacher(rs));
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

  public Vector getTeacher()throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "  SELECT * FROM ETN_TEACHER_V";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createTeacher(rs));
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
  public Vector getTeacher(PageControl pc,String teachertype)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "  SELECT * FROM ETN_TEACHER_V where TEACHER_FLAG=? order by TEACHER_NAME asc";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1,teachertype);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();

      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) {
        vector.add(this.createTeacher(rs));
        pc.setiii();
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
  public Teacher getTeacherByTeacherNo(String teacherno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "  SELECT * FROM ETN_TEACHER_V where SEQ_ETN_TEACHER =?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(teacherno));
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return this.createTeacher(rs);
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

  public Vector getTeacherByTeacherType(String teachertype)throws Exception{
  Vector vector = new Vector();
  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  String SQL = "  SELECT * FROM ETN_TEACHER_V where TEACHER_FLAG =? order by TEACHER_NAME asc";

  try {
    conn = services.getConnection();
    pstmt = conn.prepareStatement(SQL);
    pstmt.setString(1,teachertype);
    rs = pstmt.executeQuery();
    while(rs.next()) {
      vector.add(this.createTeacher(rs));
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
  return vector;
}



  public Vector getTeacherByTeacherType(String teachertype,String teacherEnableClass)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "  SELECT * FROM ETN_TEACHER_V where TEACHER_FLAG =? order by TEACHER_NAME asc";
    //System.out.println(teacherEnableClass);
    teacherEnableClass=StringUtil.toCN(teacherEnableClass);
    //System.out.println(teacherEnableClass);

    if (!teacherEnableClass.trim().equals(""))
      SQL = "  SELECT * FROM ETN_TEACHER_V where TEACHER_FLAG =? and Teacher_Enable_Class like '%"+teacherEnableClass+"%' order by TEACHER_NAME asc";
    //System.out.println(SQL);
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1,teachertype);
      //if (!teacherEnableClass.equals("--请输入讲授课程--"))
      //  pstmt.setString(2,teacherEnableClass);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        vector.add(this.createTeacher(rs));
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
    return vector;
  }



  private Teacher createTeacher(ResultSet rs) throws Exception{
    Teacher teacher = new Teacher();
      try {
        teacher.setTeacherNo(rs.getString("SEQ_ETN_TEACHER") != null ? rs.getString("SEQ_ETN_TEACHER") : "");
        teacher.setTeacherName(rs.getString("TEACHER_NAME") != null ? rs.getString("TEACHER_NAME") : "");
        teacher.setTeacherTypeCode(rs.getString("TEACHER_FLAG") != null ? rs.getString("TEACHER_FLAG") : "");
        teacher.setTeacherTypeName(rs.getString("TEACHER_FLAG_NAME") != null ? rs.getString("TEACHER_FLAG_NAME") :  "");
        teacher.setTeacherBelongTo(rs.getString("TEACHER_BELONG") != null ? rs.getString("TEACHER_BELONG") : "");
        teacher.setTeacherSex(rs.getString("TEACHER_SEX") != null ? rs.getString("TEACHER_SEX") : "");
        teacher.setTeacherSexName(rs.getString("TEACHER_SEX_NAME") != null ? rs.getString("TEACHER_SEX_NAME") : "");
        teacher.setTeacherEnableClass(rs.getString("TEACHER_ENABLE_CLASS") != null ? rs.getString("TEACHER_ENABLE_CLASS") : "");
        teacher.setTeacherConnection(rs.getString("OUT_TEACHER_CONNECTION") != null ? rs.getString("OUT_TEACHER_CONNECTION") : "");
        teacher.setTeacherFeeHour(rs.getString("OUT_TEACHER_FEE") != null ? rs.getString("OUT_TEACHER_FEE") :"");
        teacher.setTeacherRecord(rs.getString("OUT_TEACHER_CONTENT") != null ? rs.getString("OUT_TEACHER_CONTENT") :"");
        teacher.setTeacherChecked(rs.getString("checked") != null ? rs.getString("checked") :"");
      }
      catch (SQLException ex) {
      }
      return teacher;
  }
  public void updateTeacher(Teacher teacher)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_TEACHER set TEACHER_NAME=?, TEACHER_BELONG=?, "+
                  "TEACHER_SEX=?, TEACHER_ENABLE_CLASS=?, OUT_TEACHER_CONTENT=?, "+
                  "OUT_TEACHER_CONNECTION=?, OUT_TEACHER_FEE=?, TEACHER_FLAG=? "+
                  "where SEQ_ETN_TEACHER=? ";
  try {
    conn = services.getConnection();
    pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, teacher.getTeacherName());
      pstmt.setString(2, teacher.getTeacherBelongTo());
      pstmt.setString(3, teacher.getTeacherSex());
      pstmt.setString(4, teacher.getTeacherEnableClass());
      pstmt.setString(5, teacher.getTeacherRecord());
      pstmt.setString(6, teacher.getTeacherConnection());
      pstmt.setInt(7, Integer.parseInt(teacher.getTeacherFeeHour()));
      pstmt.setString(8, teacher.getTeacherTypeCode());
      pstmt.setInt(9, Integer.parseInt(teacher.getTeacherNo()));
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
  public void deleteTeacher(String teacherno) throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "delete  ETN_TEACHER where SEQ_ETN_TEACHER =?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, Integer.parseInt(teacherno));
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
  public String getTeacherContent(String teacherno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "SELECT TEACHER_RECORD FROM ETN_TEACHER where SEQ_ETN_TEACHER=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(teacherno));
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getString("TEACHER_RECORD")!=null?rs.getString("TEACHER_RECORD"):"";
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
    return null;
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
  public String getTeacherTypeCode() {
    return teacherTypeCode;
  }
  public void setTeacherTypeCode(String teacherTypeCode) {
    this.teacherTypeCode = teacherTypeCode;
  }
  public String getTeacherTypeName() {
    return teacherTypeName;
  }
  public void setTeacherTypeName(String teacherTypeName) {
    this.teacherTypeName = teacherTypeName;
  }
  public String getTeacherBelongTo() {
    return teacherBelongTo;
  }
  public void setTeacherBelongTo(String teacherBelongTo) {
    this.teacherBelongTo = teacherBelongTo;
  }
  public String getTeacherConnection() {
    return teacherConnection;
  }
  public void setTeacherConnection(String teacherConnection) {
    this.teacherConnection = teacherConnection;
  }
  public String getTeacherFeeHour() {
    return teacherFeeHour;
  }
  public void setTeacherFeeHour(String teacherFeeHour) {
    this.teacherFeeHour = teacherFeeHour;
  }
  public String getTeacherRecord() {
    return teacherRecord;
  }
  public void setTeacherRecord(String teacherRecord) {
    this.teacherRecord = teacherRecord;
  }
  public String getTeacherModifyDate() {
    return teacherModifyDate;
  }
  public void setTeacherModifyDate(String teacherModifyDate) {
    this.teacherModifyDate = teacherModifyDate;
  }
  public String getTeacherModifyPerson() {
    return teacherModifyPerson;
  }
  public void setTeacherModifyPerson(String teacherModifyPerson) {
    this.teacherModifyPerson = teacherModifyPerson;
  }
  public String getTeacherSex() {
    return teacherSex;
  }
  public void setTeacherSex(String teacherSex) {
    this.teacherSex = teacherSex;
  }
  public String getTeacherEnableClass() {
    return teacherEnableClass;
  }
  public void setTeacherEnableClass(String teacherEnableClass) {
    this.teacherEnableClass = teacherEnableClass;
  }
  public String getTeacherSexName() {
    return teacherSexName;
  }
  public void setTeacherSexName(String teacherSexName) {
    this.teacherSexName = teacherSexName;
  }
  public String getTeacherChecked() {
    return teacherChecked;
  }
  public void setTeacherChecked(String teacherChecked) {
    this.teacherChecked = teacherChecked;
  }
}
