package com.ait.etn;


import java.sql.*;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.*;
import com.ait.utils.ConnBean;
import com.ait.etn.EtnDepartment;
import com.ait.util.ServiceLocatorException;


public class EtnDepartment {
  private String deptId;
  private String deptName;
  private String enDeptName;
  private String korDeptName;
  private String parentDeptId;
  private String parentDeptName;
  private String deptLevel;
  private String checkState;
  public EtnDepartment() {

  }
  public Vector getDept(){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = 
    	"SELECT " +
	    	"DISTINCT DEPTID, " +
	    	"DEPTNAME, " +
	    	"PARENT_DEPT_ID, " +
	    	"DEPT_LEVEL "+
    	"FROM HR_DEPARTMENT HR, " +
    		"ETN_YEARPLAN_FIT_DEPT EYFD " +
    	"WHERE HR.DEPTID = EYFD.FIT_DEPARTMENT_ID(+) " +
    	"START WITH PARENT_DEPT_ID = 'LGYX' " +
    	"CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID " +
    	"ORDER SIBLINGS BY DEPTID";
    	Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
      	EtnDepartment etndepartment = new EtnDepartment();
        etndepartment.setDeptId(rs.getString("DEPTID") != null ?rs.getString("DEPTID") : "");
        etndepartment.setDeptName(rs.getString("DEPTNAME") != null ?rs.getString("DEPTNAME") : "");
        etndepartment.setParentDeptId(rs.getString("PARENT_DEPT_ID") != null ?rs.getString("PARENT_DEPT_ID") : "");
        etndepartment.setDeptLevel(rs.getString("DEPT_LEVEL") != null ?rs.getString("DEPT_LEVEL") : "");
        vector.add(etndepartment);
      }
    }
    catch (SQLException ex) {
    	Logger.getLogger(getClass()).error(ex.toString());
    }finally{
    	SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }
//20051106

   public Vector getDept(String parentDepartmentId){
    Vector vector = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
		String sql = 
									"SELECT " + 
												"LEVEL, " +// --级别。添加了三个虚部门，级别号码都会小于1
												//"LPAD(' ', " +// --缩进符号
												//"LEVEL*3) " +// --添加缩进符号个数
												//"||DEPTID " +// --部门ID
												//"AS DEPTID, " +// --列名
												"DEPTID, " + 
												"DEPTNAME, " + 
												"CPNY_ID, " + 
												"DATE_CREATED, " + 
												"DATE_ENDED, " + 
												"PARENT_DEPT_ID, " + 
												"DEPT_LEVEL, " + //手工指定的级别
												"MANAGER_EMP_ID, " + 
												"CREATE_DATE, " + 
												"CREATED_BY, " + 
												"UPDATE_DATE, " + 
												"UPDATED_BY, " + 
												"ORDERNO, " + 
												"ACTIVITY, " + 
												"DEPTNO " + 
											"FROM HR_DEPARTMENT ";
										if(!(parentDepartmentId == null || 
													parentDepartmentId.equalsIgnoreCase("") || 
													parentDepartmentId.equalsIgnoreCase("null"))){
											sql += "WHERE PARENT_DEPT_ID = '" + parentDepartmentId + "' "; //如果有上级就显示特定上级的
										}
									sql += 
											"START WITH PARENT_DEPT_ID IN(SELECT CPNY_ID FROM HR_COMPANY) " +
											"CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID " +
											"ORDER SIBLINGS BY ORDERNO ASC";
    Logger.getLogger(getClass()).debug(sql);
    try {
      conn = ConnBean.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();  
      while (rs.next()) {
      	EtnDepartment etndepartment = new EtnDepartment();
        etndepartment.setDeptId(rs.getString("DEPTID") != null ?rs.getString("DEPTID") : "");
        etndepartment.setDeptName(rs.getString("DEPTNAME") != null ?rs.getString("DEPTNAME") : "");
        etndepartment.setParentDeptId(rs.getString("PARENT_DEPT_ID") != null ?rs.getString("PARENT_DEPT_ID") : "");
        etndepartment.setDeptLevel(rs.getString("DEPT_LEVEL") != null ?rs.getString("DEPT_LEVEL") : "");
        vector.add(etndepartment);
      }
    }
    catch (SQLException ex) {
    	Logger.getLogger(getClass()).error(ex.toString());
    }
    finally{
      SqlUtil.close(rs, pstmt, conn);
    }
    return vector;
  }
   
   
   public Vector getDept1()throws Exception {
	    Vector vector = new Vector();
	    Connection conn = null;
	    PreparedStatement pstmt = null;    
	    ResultSet rs = null;
	    String SQL = " select  DEPTID, DEPTNAME,DEPT_EN_NAME,DEPT_KOR_NAME, PARENT_DEPT_ID, DEPT_LEVEL "+
	   " from hr_department HD where (HD.date_ended >= "+
	     "   sysdate or HD.date_ended = '' or HD.date_ended is null) "+
	     "   AND (HD.DATE_CREATED IS NULL OR HD.DATE_CREATED<=SYSDATE)"+
	     "    start with DEPTID = 'Z000000' "+
	      "   connect by prior deptid =parent_dept_id order siblings by DEPTID";
	    try {
	      conn =  ConnBean.getConn();
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
   
   
   private EtnDepartment createDept(ResultSet rs)throws Exception{
	    EtnDepartment etndepartment = new EtnDepartment();
	        try {
	          etndepartment.setDeptId(rs.getString("DEPTID") != null ?
	                                  rs.getString("DEPTID") : "");
	          etndepartment.setDeptName(rs.getString("DEPTNAME") != null ?
	                                    rs.getString("DEPTNAME") : "");
	          etndepartment.setEnDeptName(rs.getString("DEPT_EN_NAME") != null ?
	                                    rs.getString("DEPT_EN_NAME") : "");
	          etndepartment.setKorDeptName(rs.getString("DEPT_KOR_NAME") != null ?
                      rs.getString("DEPT_KOR_NAME") : "");
	          etndepartment.setParentDeptId(rs.getString("PARENT_DEPT_ID") != null ?
	                                        rs.getString("PARENT_DEPT_ID") : "");
	          etndepartment.setDeptLevel(rs.getString("DEPT_LEVEL") != null ?
	                                     rs.getString("DEPT_LEVEL") : "");
	        }
	        catch (SQLException ex) {
	        }
	        return etndepartment;
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
public String getEnDeptName() {
	return enDeptName;
}
public void setEnDeptName(String enDeptName) {
	this.enDeptName = enDeptName;
}
public String getKorDeptName() {
	return korDeptName;
}
public void setKorDeptName(String korDeptName) {
	this.korDeptName = korDeptName;
}

}
