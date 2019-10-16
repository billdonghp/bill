package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class PlanTeacher {
  private static ServiceLocator services;
  private String etnPlanNo;
  private String teacherNo;
  private String teacherName;
  private String outTeacherFee;
  public PlanTeacher() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public Vector getEtnPlanTeacher(String etnplanno)throws Exception{
    Vector  vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "SELECT ETN_PLAN_NO, TEACHER_NO, TEACHER_NAME,  "+
                   " OUT_TEACHER_FEE  "+
                   " FROM ETN_PLAN_TEACHER_V  where etn_plan_no = ? ";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      while(rs.next()) {
        PlanTeacher  planteacher = new PlanTeacher();
        planteacher.setEtnPlanNo(rs.getString("ETN_PLAN_NO")!=null?rs.getString("ETN_PLAN_NO"):"");
        planteacher.setTeacherNo(rs.getString("TEACHER_NO")!=null?rs.getString("TEACHER_NO"):"");
        planteacher.setTeacherName(rs.getString("TEACHER_NAME")!=null?rs.getString("TEACHER_NAME"):"");
        planteacher.setOutTeacherFee(rs.getString("OUT_TEACHER_FEE")!=null?rs.getString("OUT_TEACHER_FEE"):"");
        vector.add(planteacher);
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
  public String getOutTeacherFee() {
    return outTeacherFee;
  }
  public void setOutTeacherFee(String outTeacherFee) {
    this.outTeacherFee = outTeacherFee;
  }
}
