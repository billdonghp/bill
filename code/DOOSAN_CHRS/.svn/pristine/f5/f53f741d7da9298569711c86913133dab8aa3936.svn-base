package com.ait.etn;


import java.sql.*;
import java.util.Vector;
import com.ait.util.*;


public class EmailBean {
  private static ServiceLocator services;
  private String empid;
  private String email;
  private String etnplanno;
  private String chinesename;
  public EmailBean() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {

    }
  }
   public Vector getEmpidEmail(String etnplanno)throws Exception{
     Vector vector = new Vector();
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String SQL = "select email,CHINESENAME from ETN_FACT_GROUP_EMAIL where SEQ_ETN_PLAN=?";

     try {
       conn = services.getConnection();
       pstmt = conn.prepareStatement(SQL);
       pstmt.setInt(1,Integer.parseInt(etnplanno));
       rs = pstmt.executeQuery();
       while (rs.next()) {
         EmailBean emailbean = new EmailBean();
         emailbean.setEmail(rs.getString("email")!=null?rs.getString("email"):"");
         emailbean.setChinesename(rs.getString("CHINESENAME")!=null?rs.getString("CHINESENAME"):"");
         vector.add(emailbean);
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


  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getEtnplanno() {
    return etnplanno;
  }
  public void setEtnplanno(String etnplanno) {
    this.etnplanno = etnplanno;
  }
  public String getChinesename() {
    return chinesename;
  }
  public void setChinesename(String chinesename) {
    this.chinesename = chinesename;
  }
}
