package com.ait.etn;

import java.sql.*;
import java.util.Vector;
import com.ait.util.*;

public class TeachingMaterial {
  private static ServiceLocator services;
  private String planEduItemCode;
  private String planEduItemName;
  private String planClassTypeCode;
  private String planCode;
  private String className;
  private String classEdtion;
  private String classBrief;
  private String classNo;
  private String etnPlanNo;
  private String planPerrior;
  private String modifyDate;
  private String modifyPerson;
  public TeachingMaterial() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public void addTeachingMaterial(TeachingMaterial teachingmaterial)throws Exception {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "INSERT INTO ETN_TEACHINGMATERIAL_MANAGE ("+
                 "SEQ_TEACHTEXT_MA, PLAN_CODE, CLASS_NAME, "+
                  "CLASS_EDITION, CLASS_BRIEF)  "+
                  "VALUES(ETN_TEACHINGMATERIAL_SEQ.NEXTVAL,?,?,?,?)";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, teachingmaterial.getPlanCode());
      pstmt.setString(2, teachingmaterial.getClassName());
      pstmt.setString(3, teachingmaterial.getClassEdtion());
      pstmt.setString(4, teachingmaterial.getClassBrief());
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

  public Vector getTeachingMaterialByPlanCode(String plancode)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select * from ETN_TEACHINGMATERIAL_V where Plan_code=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1,plancode);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.creatTeachingMaterial(rs));
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


  public Vector getTeachingMaterial()throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select * from ETN_TEACHINGMATERIAL_V";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.creatTeachingMaterial(rs));
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
  public TeachingMaterial getTeachingMaterialbyclassno(String classno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select * from ETN_TEACHINGMATERIAL_V where SEQ_TEACHTEXT_MA=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1,classno);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return this.creatTeachingMaterial(rs);
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

  public Vector getTeachingMaterial(PageControl pc)throws Exception{
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select * from ETN_TEACHINGMATERIAL_V order by SEQ_TEACHTEXT_MA desc";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      pc.seti();
      for (int i = 0; i < pc.getI(); i++) {
        rs.next();
      }
      pc.setii();
      while ( (pc.getI() < pc.getIntPagedSize()) && rs.next()) {
        vector.add(this.creatTeachingMaterial(rs));
        pc.setiii();
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


  private TeachingMaterial creatTeachingMaterial(ResultSet rs) throws Exception {
       TeachingMaterial teachingmaterial = new TeachingMaterial();
        try {
          teachingmaterial.setClassNo(rs.getString("SEQ_TEACHTEXT_MA") != null ? rs.getString("SEQ_TEACHTEXT_MA") :"");
          teachingmaterial.setPlanCode(rs.getString("PLAN_CODE") != null ? rs.getString("PLAN_CODE") :"");
          teachingmaterial.setPlanEduItemName(rs.getString("PLAN_EDU_ITEM_NAME") != null ? rs.getString("PLAN_EDU_ITEM_NAME") :"");
          teachingmaterial.setPlanClassTypeCode(rs.getString("PLAN_CLASS_TYPE_CODE") != null ?rs.getString("PLAN_CLASS_TYPE_CODE") : "");
          //teachingmaterial.setEtnPlanNo(rs.getString("ETN_PLAN_NO") != null ?rs.getString("ETN_PLAN_NO") : "");
          teachingmaterial.setClassName(rs.getString("CLASS_NAME") != null ? rs.getString("CLASS_NAME") : "");
          teachingmaterial.setClassEdtion(rs.getString("CLASS_EDITION") != null ? rs.getString("CLASS_EDITION") : "");
          teachingmaterial.setClassBrief(rs.getString("CLASS_BRIEF") != null ? rs.getString("CLASS_BRIEF") : "");
          //teachingmaterial.setPlanPerrior(rs.getString("PLAN_PERRIOR_COUNT") != null ? rs.getString("PLAN_PERRIOR_COUNT") : "");
          teachingmaterial.setModifyDate(rs.getString("MODIFY_DATE") != null ? rs.getString("MODIFY_DATE").substring(0,10) : "");
          teachingmaterial.setModifyPerson(rs.getString("MODIFY_PERSON") != null ? rs.getString("MODIFY_PERSON") : "");
        }
        catch (SQLException ex) {
          ex.printStackTrace();
        }
        return teachingmaterial;
  }
  /**
   *
   * @param teachingmaterial TeachingMaterial
   * @throws Exception
   */
  public void updateTeachingMaterial(TeachingMaterial teachingmaterial)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL ="update ETN_TEACHINGMATERIAL_MANAGE set plan_code =?, CLASS_NAME=?,"+
                 "CLASS_EDITION=?, CLASS_BRIEF=? where SEQ_TEACHTEXT_MA=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, teachingmaterial.getPlanCode());
      pstmt.setString(2, teachingmaterial.getClassName());
      pstmt.setString(3, teachingmaterial.getClassEdtion());
      pstmt.setString(4, teachingmaterial.getClassBrief());
      pstmt.setInt(5, Integer.parseInt(teachingmaterial.getClassNo()));
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
  public void updateTeachingMaterialModify(String classno,String modifydate,String modifyer)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL ="update ETN_TEACHINGMATERIAL_MANAGE set "+
                 "MODIFY_DATE=to_date(?,'yyyy-mm-dd'), MODIFY_PERSON=?  where SEQ_TEACHTEXT_MA=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, modifydate);
      pstmt.setString(2, modifyer);
      pstmt.setInt(3, Integer.parseInt(classno));
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

  public void deleteTeachingMaterial(String classno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "delete ETN_TEACHINGMATERIAL_MANAGE where SEQ_TEACHTEXT_MA=?";

    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, classno);
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
  public String getClassBrief(String classno)throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select CLASS_BRIEF from ETN_TEACHINGMATERIAL_MANAGE where SEQ_TEACHTEXT_MA=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1,Integer.parseInt(classno));
      rs = pstmt.executeQuery();
      if(rs.next()){
        return rs.getString("CLASS_BRIEF");
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
    return "";
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
  public String getPlanCode() {
    return planCode;
  }
  public void setPlanCode(String planCode) {
    this.planCode = planCode;
  }
  public String getClassName() {
    return className;
  }
  public void setClassName(String className) {
    this.className = className;
  }
  public String getClassEdtion() {
    return classEdtion;
  }
  public void setClassEdtion(String classEdtion) {
    this.classEdtion = classEdtion;
  }
  public String getClassBrief() {
    return classBrief;
  }
  public void setClassBrief(String classBrief) {
    this.classBrief = classBrief;
  }
  public String getClassNo() {
    return classNo;
  }
  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }
  public String getEtnPlanNo() {
    return etnPlanNo;
  }
  public void setEtnPlanNo(String etnPlanNo) {
    this.etnPlanNo = etnPlanNo;
  }
  public String getPlanPerrior() {
    return planPerrior;
  }
  public void setPlanPerrior(String planPerrior) {
    this.planPerrior = planPerrior;
  }
  public String getModifyDate() {
    return modifyDate;
  }
  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
  }
  public String getModifyPerson() {
    return modifyPerson;
  }
  public void setModifyPerson(String modifyPerson) {
    this.modifyPerson = modifyPerson;
  }
}
