package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class EtnTeacherSatisfact {
  private static ServiceLocator services;
  private String etnPlanNo;
  private String empID;
  private String classNo;
  private String className;
  private String teacherNo;
  private String teacherName;
  private String score;
  private String wholeSatisfact;
  private String eduTermSatisfact;
  private String eduPlaceSatisfact;
  private String eduMethodSatisfact;
  private String classNameGroup;
  private String eduDepartment;
  private String teacherTotalSatisfact;
  private String eduClassContentSatisfact;
  public EtnTeacherSatisfact() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public void addEtnTeacherSatisfact(String etnplanno,String[] teacherno,String[] score,String[] classname,String[] eduDepartment)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "insert into ETN_TEACHER_SATISFACT(ETN_PLAN_NO, TEACHER_NO, SCORE, "+
                 "  CLASS_NAME_GROUP,EDU_DEPARTMENT)"+
                  " values (?,?,?,?,?)";


    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, etnplanno);
      for(int i=0; i<teacherno.length; i++){
        pstmt.setString(2, teacherno[i]);
        pstmt.setString(3, score[i]);
        pstmt.setString(4, StringUtil.toCN(classname[i]));
        pstmt.setString(5, StringUtil.toCN(eduDepartment[i]));
        pstmt.executeUpdate();
      }
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
  public void addWholeSatisfact(EtnTeacherSatisfact etnteachersatisfact)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "insert into ETN_WHOLE_SATISFACTION (ETN_PLAN_NO, WHOLE_SATISFACTION, EDU_TERM_SATISFACTION, "+
                   " EDU_PLACE_SATISFACTION, EDU_METHORD_SATISFACT,EDU_CLASS_SATISFACTION)values(?,?,?,?,?,?)  ";



      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL);
        pstmt.setFloat(1, Float.parseFloat(etnteachersatisfact.getEtnPlanNo()));
        pstmt.setFloat(2, Float.parseFloat(etnteachersatisfact.getWholeSatisfact()));
        pstmt.setFloat(3, Float.parseFloat(etnteachersatisfact.getEduTermSatisfact()));
        pstmt.setFloat(4, Float.parseFloat(etnteachersatisfact.getEduPlaceSatisfact()));
        pstmt.setFloat(5, Float.parseFloat(etnteachersatisfact.getEduMethodSatisfact()));
        pstmt.setFloat(6, Float.parseFloat(etnteachersatisfact.getEduClassContentSatisfact()));
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

public boolean addEtnTeacherSatisfactProcedure(String etnplanno)throws DataAccessException {
    Connection con = null;
    String sql = "{ call etn_teacher_satisfact_total(?) }";
    CallableStatement cs = null;
    try {
      con = services.getConnection();
      cs = con.prepareCall(sql);
      cs.setInt(1,Integer.parseInt(etnplanno));
      return cs.execute();
    }
    catch (SQLException ex) {
     ex.printStackTrace();
     throw new DataAccessException("cant execute query ", ex);
   }
   catch (ServiceLocatorException ex) {
     throw new DataAccessException("cant get connection ", ex);
   }
   finally {
     SqlUtil.close(cs, con);
   }
 }
  /** public boolean addEtnWholeSatisfactProcedure(String etnplanno)throws DataAccessException {
   Connection con = null;
   String sql = "{ call etn_satisfaction_for_whole(?) }";
   CallableStatement cs = null;
   try {
     con = services.getConnection();
     cs = con.prepareCall(sql);
     cs.setInt(1,Integer.parseInt(etnplanno));
     return cs.execute();
   }
   catch (SQLException ex) {
    ex.printStackTrace();
    throw new DataAccessException("cant execute query ", ex);
  }
  catch (ServiceLocatorException ex) {
    throw new DataAccessException("cant get connection ", ex);
  }
  finally {
    SqlUtil.close(cs, con);
  }
 }*/


  public EtnTeacherSatisfact getEtnTeacherSatisfact(String etnplanno,String teacherno)throws Exception{
    EtnTeacherSatisfact etnteachersatisfact = new EtnTeacherSatisfact();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="select SCORE, CLASS_NAME_GROUP,EDU_DEPARTMENT from ETN_TEACHER_SATISFACT where ETN_PLAN_NO=? and TEACHER_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      pstmt.setString(2,teacherno);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        etnteachersatisfact.setScore(rs.getString("SCORE")!=null?rs.getString("SCORE"):"");
        etnteachersatisfact.setClassNameGroup(rs.getString("CLASS_NAME_GROUP")!=null?rs.getString("CLASS_NAME_GROUP"):"");
        etnteachersatisfact.setEduDepartment(rs.getString("EDU_DEPARTMENT")!=null?rs.getString("EDU_DEPARTMENT"):"");
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
    return etnteachersatisfact;
  }
  public void updateEtnTeacherSatisfact(String etnplanno,String[] teacherno,String[] score,String[] educlass,String[] eduDepartment)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_TEACHER_SATISFACT set  "+
                 "SCORE=?, CLASS_NAME_GROUP=? ,EDU_DEPARTMENT=? where ETN_PLAN_NO=?and TEACHER_NO=? ";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(4, etnplanno);
      for(int i=0; i<teacherno.length; i++){
        pstmt.setString(1, score[i]);
        pstmt.setString(2, StringUtil.toCN(educlass[i]));
        pstmt.setString(3, StringUtil.toCN(eduDepartment[i]));
        pstmt.setString(5, teacherno[i]);
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
  public void updateWholeSatisfact(EtnTeacherSatisfact etnteachersatisfact)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update ETN_WHOLE_SATISFACTION set WHOLE_SATISFACTION=?, EDU_TERM_SATISFACTION=?, "+
                 " EDU_PLACE_SATISFACTION=?, EDU_METHORD_SATISFACT=?,EDU_CLASS_SATISFACTION=?  where ETN_PLAN_NO=?";


      try {
        conn = services.getConnection();
        pstmt = conn.prepareStatement(SQL);
        pstmt.setFloat(1,Float.parseFloat(etnteachersatisfact.getWholeSatisfact()));
        pstmt.setFloat(2,Float.parseFloat(etnteachersatisfact.getEduTermSatisfact()));
        pstmt.setFloat(3,Float.parseFloat(etnteachersatisfact.getEduPlaceSatisfact()));
        pstmt.setFloat(4,Float.parseFloat(etnteachersatisfact.getEduMethodSatisfact()));
        pstmt.setFloat(5,Float.parseFloat(etnteachersatisfact.getEduClassContentSatisfact()));
        pstmt.setFloat(6, Float.parseFloat(etnteachersatisfact.getEtnPlanNo()));
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

  public EtnTeacherSatisfact getEtnTeacherSatisfact(String etnplanno)throws Exception{
    EtnTeacherSatisfact etnteachersatisfact = new EtnTeacherSatisfact();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="select SCORE from ETN_TEACHER_SATISFACT where ETN_PLAN_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      if(rs.next()) {
           etnteachersatisfact.setScore(rs.getString("SCORE")!=null?rs.getString("SCORE"):"");
           return etnteachersatisfact ;
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
  public Vector getEndEtnTeacherSatisfact(String etnplanno)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL ="SELECT TEACHER_NO, SCORE, TEACHER_NAME   "+
                "FROM ETN_TEACHER_SATISFACT_V WHERE ETN_PLAN_NO=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      while(rs.next()) {
           EtnTeacherSatisfact etnteachersatisfact = new EtnTeacherSatisfact();
           etnteachersatisfact.setTeacherNo(rs.getString("TEACHER_NO")!=null?rs.getString("TEACHER_NO"):"");
           etnteachersatisfact.setScore(rs.getString("SCORE")!=null?rs.getString("SCORE"):"");
           etnteachersatisfact.setTeacherName(rs.getString("TEACHER_NAME")!=null?rs.getString("TEACHER_NAME"):"");
           vector.add(etnteachersatisfact);
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
  public void deleteTeacherSatisfact(String etnplanno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt =null;
    String SQL = "delete ETN_TEACHER_SATISFACT where etn_plan_no =? ";

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


  public EtnTeacherSatisfact getWholeSatisfact(String etnplanno)throws Exception{
    EtnTeacherSatisfact etnteachersatisfact = new EtnTeacherSatisfact();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL =" SELECT  WHOLE_SATISFACTION, EDU_TERM_SATISFACTION,  EDU_PLACE_SATISFACTION, EDU_METHORD_SATISFACT,EDU_TEACHER_TOTAL_SATIS,EDU_CLASS_SATISFACTION  "+
                 " FROM ETN_WHOLE_SATISFACTION where ETN_PLAN_NO =?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(etnplanno));
      rs = pstmt.executeQuery();
      if(rs.next()) {
         etnteachersatisfact.setWholeSatisfact(rs.getString("WHOLE_SATISFACTION")!=null?rs.getString("WHOLE_SATISFACTION"):"");
         etnteachersatisfact.setEduTermSatisfact(rs.getString("EDU_TERM_SATISFACTION")!=null?rs.getString("EDU_TERM_SATISFACTION"):"");
         etnteachersatisfact.setEduPlaceSatisfact(rs.getString("EDU_PLACE_SATISFACTION")!=null?rs.getString("EDU_PLACE_SATISFACTION"):"");
         etnteachersatisfact.setEduMethodSatisfact(rs.getString("EDU_METHORD_SATISFACT")!=null?rs.getString("EDU_METHORD_SATISFACT"):"");
         etnteachersatisfact.setTeacherTotalSatisfact(rs.getString("EDU_TEACHER_TOTAL_SATIS")!=null?rs.getString("EDU_TEACHER_TOTAL_SATIS"):"");
         etnteachersatisfact.setEduClassContentSatisfact(rs.getString("EDU_CLASS_SATISFACTION")!=null?rs.getString("EDU_CLASS_SATISFACTION"):"");
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
    return etnteachersatisfact;
  }


   public String getTeacherSatisfact(String etnplanno, String teacherno)throws Exception{
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;

     String SQL="SELECT SCORE "+
         " FROM ETN_TEACHER_SATISFACT "+
         " where ETN_PLAN_NO=? and  TEACHER_NO=?";


    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, Integer.parseInt(etnplanno));
      pstmt.setInt(2, Integer.parseInt(teacherno));
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getString("SCORE") != null ? rs.getString("SCORE") : "";
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


  public String getEtnPlanNo() {
    return etnPlanNo;
  }
  public void setEtnPlanNo(String etnPlanNo) {
    this.etnPlanNo = etnPlanNo;
  }
  public String getEmpID() {
    return empID;
  }
  public void setEmpID(String empID) {
    this.empID = empID;
  }
  public String getClassNo() {
    return classNo;
  }
  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }
  public String getClassName() {
    return className;
  }
  public void setClassName(String className) {
    this.className = className;
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
  public String getScore() {
    return score;
  }
  public void setScore(String score) {
    this.score = score;
  }
  public String getWholeSatisfact() {
    return wholeSatisfact;
  }
  public void setWholeSatisfact(String wholeSatisfact) {
    this.wholeSatisfact = wholeSatisfact;
  }
  public String getEduTermSatisfact() {
    return eduTermSatisfact;
  }
  public void setEduTermSatisfact(String eduTermSatisfact) {
    this.eduTermSatisfact = eduTermSatisfact;
  }
  public String getEduPlaceSatisfact() {
    return eduPlaceSatisfact;
  }
  public void setEduPlaceSatisfact(String eduPlaceSatisfact) {
    this.eduPlaceSatisfact = eduPlaceSatisfact;
  }
  public String getEduMethodSatisfact() {
    return eduMethodSatisfact;
  }
  public void setEduMethodSatisfact(String eduMethodSatisfact) {
    this.eduMethodSatisfact = eduMethodSatisfact;
  }
  public String getClassNameGroup() {
    return classNameGroup;
  }
  public void setClassNameGroup(String classNameGroup) {
    this.classNameGroup = classNameGroup;
  }
  public String getEduDepartment() {
    return eduDepartment;
  }
  public void setEduDepartment(String eduDepartment) {
    this.eduDepartment = eduDepartment;
  }
  public String getTeacherTotalSatisfact() {
    return teacherTotalSatisfact;
  }
  public void setTeacherTotalSatisfact(String teacherTotalSatisfact) {
    this.teacherTotalSatisfact = teacherTotalSatisfact;
  }
  public String getEduClassContentSatisfact() {
    return eduClassContentSatisfact;
  }
  public void setEduClassContentSatisfact(String eduClassContentSatisfact) {
    this.eduClassContentSatisfact = eduClassContentSatisfact;
  }
}
