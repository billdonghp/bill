package com.ait.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;


public class Deptree {
	private final static Logger log = Logger.getLogger(Deptree.class);
  private static ServiceLocator services;
  private String deptId;
  private String deptName;
  private String parentDeptId;
  private String parentDeptName;
  private String deptLevel;
  private String checkState;
  private String deptEnName;
  private String cpnyName;
  public Deptree() {
    try {
      services = ServiceLocator.getInstance();
    }
    catch (ServiceLocatorException ex) {
    }
  }
  public Vector getDept(String cpnyId)throws Exception {  
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String SQL = "select DEPTID, DEPTNAME, PARENT_DEPT_ID, DEPT_LEVEL ,DEPT_EN_NAME from hr_dept_tree_v where cpny_id='"+cpnyId+"' and Activity = 1 ";
    log.debug(SQL);
    try {
      conn = services.getConnection();   
      pstmt = conn.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vector.add(this.createDept(rs));
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
  
  //不区分法人 获取全部部门
  public Vector getDeptAll(String cpnyId)throws Exception {  
	    Vector vector = new Vector();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    PreparedStatement psCp = null;
	    ResultSet rsCp = null;
	    String SQLCpn1 = "";
	    String SQLCpn2 = "";//select DEPTID, DEPTNAME, PARENT_DEPT_ID, DEPT_LEVEL ,DEPT_EN_NAME from hr_dept_tree_v where  Activity = 1 ";
	    
	    if(cpnyId != null){
	        SQLCpn1 = "select DEPTID, DEPTNAME, PARENT_DEPT_ID, DEPT_LEVEL ,DEPT_EN_NAME from hr_dept_tree_v where cpny_id='"+cpnyId+"' and Activity = 1 ";
	    }
	    
	    if(cpnyId != null){
	        SQLCpn2 = "select DEPTID, DEPTNAME, PARENT_DEPT_ID, DEPT_LEVEL ,DEPT_EN_NAME from hr_dept_tree_v where cpny_id <>'"+cpnyId+"' and Activity = 1 ";
	    }
	    
	    log.debug(SQLCpn1 + SQLCpn2);
	    try {
	      conn = services.getConnection();   
	      pstmt = conn.prepareStatement(SQLCpn1);
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	        vector.add(this.createDept(rs));
	      }
	      psCp = conn.prepareStatement(SQLCpn2);
	      rsCp = psCp.executeQuery();
	      while (rsCp.next()) {
		        vector.add(this.createDept(rsCp));
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
  
//获取法人列表
  public Vector getCompanyId()throws Exception {  
	    Vector vector = new Vector();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String SQL = " select H.CPNY_ID,H.CPNY_LOCATION,H.CPNY_NAME from HR_COMPANY H  where H.ACTIVITY = 1 ";
	    log.debug(SQL);
	    try {
	      conn = services.getConnection();   
	      pstmt = conn.prepareStatement(SQL);
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	        vector.add(this.addCompanyId(rs));
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
  
  private Deptree createDept(ResultSet rs)throws Exception{
    Deptree etndepartment = new Deptree();
        try {
          etndepartment.setDeptId(rs.getString("DEPTID") != null ?
                                  rs.getString("DEPTID") : "");
          etndepartment.setDeptName(rs.getString("DEPTNAME") != null ?
                                    rs.getString("DEPTNAME") : "");
          etndepartment.setParentDeptId(rs.getString("PARENT_DEPT_ID") != null ?
                                        rs.getString("PARENT_DEPT_ID") : "");
          etndepartment.setDeptLevel(rs.getString("DEPT_LEVEL") != null ?
                                     rs.getString("DEPT_LEVEL") :"");  
          etndepartment.setDeptEnName(rs.getString("DEPT_EN_NAME") != null ?  
                  rs.getString("DEPT_EN_NAME") : "");  
        }
        catch (SQLException ex) {
        }
        return etndepartment;
  }   
  
  private Deptree addCompanyId(ResultSet rs)throws Exception{
	    Deptree etndepartment = new Deptree();
	        try {
	          etndepartment.setDeptId(rs.getString("CPNY_ID") != null ?
	                                  rs.getString("CPNY_ID") : "");
	          etndepartment.setDeptName(rs.getString("CPNY_LOCATION") != null ?
	                                    rs.getString("CPNY_LOCATION") : "");
	          etndepartment.setCpnyName(rs.getString("CPNY_NAME") != null ?
	                                        rs.getString("CPNY_NAME") : "");
	        }
	        catch (SQLException ex) {
	        }
	        return etndepartment;
	  }
  
  public String getCpnyName() {
		return cpnyName;
	}
	public void setCpnyName(String cpnyName) {
		this.cpnyName = cpnyName;
	}
  
  public String getDeptId() {
    return deptId;
  }
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }
  public String getDeptName() {
    return deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  public String getParentDeptId() {
    return parentDeptId;
  }
  public void setParentDeptId(String parentDeptId) {
    this.parentDeptId = parentDeptId;
  }
  public String getParentDeptName() {
    return parentDeptName;
  }
  public void setParentDeptName(String parentDeptName) {
    this.parentDeptName = parentDeptName;
  }
  public String getDeptLevel() {
    return deptLevel;
  }
  public void setDeptLevel(String deptLevel) {
    this.deptLevel = deptLevel;
  }
  public String getCheckState() {
    return checkState;
  }
  public void setCheckState(String checkState) {
    this.checkState = checkState;
  }

public String getDeptEnName() {
	return deptEnName;
}

public void setDeptEnName(String deptEnName) {
	this.deptEnName = deptEnName;
}

}
