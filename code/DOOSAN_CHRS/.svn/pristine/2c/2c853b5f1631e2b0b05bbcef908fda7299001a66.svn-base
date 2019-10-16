package com.ait.ess.business;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;


public class EssSecret {
  private static ServiceLocator services;
  private String empID;
  private String passWord;
  private String userName;
  private String oldPassword;
  private boolean userCheck;

  public EssSecret() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }

  /**
   * checkOldPassword
   *
   * @param oldPassword String
   * @param empid String
   * @return boolean
   */
  private boolean checkOldPassword(String oldPassword, String empid) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select PASSWORD from sy_admin where  ADMINID = ?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, empid);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        String pass = rs.getString("PASSWORD")!= null?rs.getString("PASSWORD"):"";
        if(pass.equals(oldPassword)){
          return true;
        }
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally {
      SqlUtil.close(rs, pstmt, conn);
    }
    return false;
  }

  /**
   * checkUser
   * <br>
   * to check the user name duplication, if we found another user has already choosed the
   * specified user name, we should inform the current user to choose other usernames.
   *
   * @return boolean
   */
  private boolean checkUser(String userName, String empid) throws Exception{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select username from sy_admin where username=? and ADMINID <> ?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, userName);
      pstmt.setString(2, empid);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return true;
      }
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally {
      SqlUtil.close(rs, pstmt, conn);
    }
    return false;
  }

  public int updateUserName(EssSecret esssecret)throws Exception{
    boolean check = false;
    boolean oldPass = false;
    check = this.checkUser(esssecret.getUserName(),esssecret.getEmpID());
    oldPass = this.checkOldPassword(esssecret.getOldPassword(),esssecret.getEmpID());
    if(check) { return -2;} // 当返回-2时，说明此用户名已经存在了
    if(!oldPass) { return -3;}; // 当返回-3时，说明此用户输入的原密码不正确
    Connection conn = null;
    PreparedStatement pstmt = null;
    String SQL = "update SY_ADMIN set USERNAME=?, PASSWORD=? where ADMINID=?";
    try {
      conn = services.getConnection();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, esssecret.getUserName());
      pstmt.setString(2, esssecret.getPassWord());
      pstmt.setString(3, esssecret.getEmpID());
      return pstmt.executeUpdate();
    }
    catch (SQLException ex) {
    }
    catch (ServiceLocatorException ex) {
    }
    finally {
      SqlUtil.close(pstmt, conn);
    }
    return 0;
  }


  public String getEmpID() {
    return empID;
  }
  public void setEmpID(String empID) {
    this.empID = empID;
  }
  public String getPassWord() {
    return passWord;
  }
  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }
  public String getUserName() {
    return userName;
  }

  public boolean isUserCheck() {
    return userCheck;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setUserCheck(boolean userCheck) {
    this.userCheck = userCheck;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }
}
