package com.ait.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ait.ar.dao.DBConnection;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;

public class EntEmpTree {
	private static ServiceLocator services;

	public EntEmpTree() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}

	Vector Vlist = new Vector(); // 产生Vector的对象Vlis

	private int deptNo = 0;

	private String deptID;

	private String deptName;

	private String cpnyID;

	private String parentDeptID;

	private int deptLevel = 0;

	private String empID;   

	private String ChineseName="";

	private String deptEnName="";
	
	private String englishName="";   
	public int getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptID() {
		return this.editNull(deptID);
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getDeptName() {
		return this.editNull(deptName);
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCpnyID() {
		return this.editNull(cpnyID);
	}

	public void setCpnyID(String cpnyID) {
		this.cpnyID = cpnyID;
	}

	public String getParentDeptID() {
		return this.editNull(parentDeptID);
	}

	public void setParentDeptID(String parentDeptID) {
		this.parentDeptID = parentDeptID;
	}

	public int getDeptLevel() {
		return this.deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getEmpID() {
		return this.editNull(empID);
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getChineseName() {
		return this.editNull(ChineseName);
	}

	public void setChineseName(String ChineseName) {
		this.ChineseName = ChineseName;
	}

	public String editNull(String s) {
		if (s == null || s.equals("")) {
			return "";
		} else {
			return s;
		}
	}

	public Vector getDeptSingleTree() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null; // 建立参数声明
		ResultSet rs = null; // 建立数据集
		String DEPT_SINGLE_TREE = "select * from HR_EMPLOYEE_TREE_V";
		conn = services.getConnection();
		try {
			pstmt = conn.prepareStatement(DEPT_SINGLE_TREE);  
			rs = pstmt.executeQuery();              
			EntEmpTree entEmpTree = null;
			while (rs.next()) {
				entEmpTree = new EntEmpTree();
				entEmpTree.setEmpID(rs.getString("empid")!=null?rs.getString("empid"):"");
				entEmpTree.setChineseName(rs.getString("ChineseName")!=null?rs.getString("ChineseName"):"");
				entEmpTree.setDeptID(rs.getString("DeptID")!=null?rs.getString("DeptID"):"");
				entEmpTree.setDeptName(rs.getString("deptname")!=null?rs.getString("deptname"):"");  
				entEmpTree.setDeptEnName(rs.getString("DEPT_EN_NAME")!=null?rs.getString("DEPT_EN_NAME"):"");
				entEmpTree.setEnglishName(rs.getString("ENGLISHNAME")!=null?rs.getString("ENGLISHNAME"):"");
				entEmpTree.setCpnyID(rs.getString("cpny_id")!=null?rs.getString("cpny_id"):"");
				entEmpTree.setDeptLevel(rs.getInt("Dept_Level"));
				entEmpTree.setParentDeptID(rs.getString("parent_dept_id")!=null?rs.getString("parent_dept_id"):"");
				Vlist.add(entEmpTree);
			}
			return Vlist;                           
		} catch (SQLException e) {
			System.out.print(e + "");  
		} finally {
			if (rs != null) {
				rs.close();
			} // 关闭数据集
			if (pstmt != null) {
				pstmt.close();
			} // 关闭参数声明
			if (conn != null) {
				conn.close();
			} // 关闭连接

		}
		return Vlist; // 返回记录列表
	}

	/* 针对考勤模块对该方法重载 */
	public Vector getDeptSingleTree(String empID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null; // 建立参数声明
		ResultSet rs = null; // 建立数据集
		DBConnection db = new DBConnection();
		String sql = " where HR_EMPLOYEE_TREE_V.EMPID in ("
				+ db.getArPopedom(empID) + ") ORDER BY DEPTID"; /* 联合考勤员权限 */
		String DEPT_SINGLE_TREE = "select * from HR_EMPLOYEE_TREE_V";
		conn = services.getConnection();
		try {
			pstmt = conn.prepareStatement(DEPT_SINGLE_TREE + sql);
			rs = pstmt.executeQuery();
			EntEmpTree entEmpTree = null;
			while (rs.next()) {
				entEmpTree = new EntEmpTree();
				entEmpTree.setEmpID(rs.getString("empid"));
				entEmpTree.setChineseName(rs.getString("ChineseName"));
				entEmpTree.setDeptID(rs.getString("DeptID"));
				entEmpTree.setDeptName(rs.getString("deptname"));
				entEmpTree.setCpnyID(rs.getString("cpny_id"));
				entEmpTree.setDeptLevel(rs.getInt("Dept_Level"));
				entEmpTree.setParentDeptID(rs.getString("parent_dept_id"));
				Vlist.add(entEmpTree);
			}
			return Vlist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			} // 关闭数据集
			if (pstmt != null) {
				pstmt.close();
			} // 关闭参数声明
			if (conn != null) {
				conn.close();
			} // 关闭连接
		}
		return Vlist; // 返回记录列表
	}

	
	public String getDeptEnName() {
		return deptEnName;
	}

	
	public void setDeptEnName(String deptEnName) {
		this.deptEnName = deptEnName;
	}

	
	public String getEnglishName() {
		return englishName;
	}

	
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

}
