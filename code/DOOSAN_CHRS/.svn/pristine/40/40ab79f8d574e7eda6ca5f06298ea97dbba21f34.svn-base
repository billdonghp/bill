package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class TeacherDocument {
  private static ServiceLocator services;
  private String eduStartDate;
  private String eduEndDate;
  private String eduClassNameGroup;
  private String eduTime;
  private String teacherSatisfact;
  private String eduManageDept;
  public TeacherDocument() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public Vector getTeacherDocument(String teacherno)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select PLAN_START_DATE,PLAN_END_DATE,CLASS_NAME_GROUP,TEACHER_EDU_TIME,SCORE , EDU_DEPARTMENT "+
                 " from ETN_TEACHER_DOCUMENT where TEACHER_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1,teacherno);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.creatTeacherDocument(rs));
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


  private TeacherDocument creatTeacherDocument(ResultSet rs) throws Exception {
       TeacherDocument teacherdocument = new TeacherDocument();
        try {
          teacherdocument.setEduStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0,10) :"");
          teacherdocument.setEduEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0,10)  :"");
          teacherdocument.setEduClassNameGroup(rs.getString("CLASS_NAME_GROUP") != null ? rs.getString("CLASS_NAME_GROUP") :"");
          teacherdocument.setEduTime(rs.getString("TEACHER_EDU_TIME") != null ?rs.getString("TEACHER_EDU_TIME") : "");
          teacherdocument.setTeacherSatisfact(rs.getString("SCORE") != null ? rs.getString("SCORE") : "");
          teacherdocument.setEduManageDept(rs.getString("EDU_DEPARTMENT") != null ? rs.getString("EDU_DEPARTMENT") : "");
        }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return teacherdocument;
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
  public String getEduClassNameGroup() {
    return eduClassNameGroup;
  }
  public void setEduClassNameGroup(String eduClassNameGroup) {
    this.eduClassNameGroup = eduClassNameGroup;
  }
  public String getEduTime() {
    return eduTime;
  }
  public void setEduTime(String eduTime) {
    this.eduTime = eduTime;
  }
  public String getTeacherSatisfact() {
    return teacherSatisfact;
  }
  public void setTeacherSatisfact(String teacherSatisfact) {
    this.teacherSatisfact = teacherSatisfact;
  }
  public String getEduManageDept() {
    return eduManageDept;
  }
  public void setEduManageDept(String eduManageDept) {
    this.eduManageDept = eduManageDept;
  }
}
