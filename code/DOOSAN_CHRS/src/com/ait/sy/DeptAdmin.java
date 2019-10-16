package com.ait.sy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class DeptAdmin {
	private static ServiceLocator services;

	private String deptId;

	private String deptName;

	private String parentDeptId;

	private String parentDeptName;

	private int deptLevel;

	private String checkState;

	private String cpnyid;

	public DeptAdmin() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}

	public static boolean checkSubDept(String deptID) {
		String sql = "select count(deptid) from hr_department where parent_dept_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = services.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptID);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			if (count == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
            Logger.getLogger("checkSubDept").debug("DeptAdmin.java:checkSubDept():" + e.getMessage());
			return false;
		} finally {
            SqlUtil.close(rs, ps, conn);
		}
	}

	public static boolean checkDeptEmp(String deptID) {
		String sql = "select count(empid) from hr_employee where deptid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = services.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptID);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			if (count == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
            Logger.getLogger("checkDeptEmp").debug("DeptAdmin.java:checkDeptEmp():"
					+ e.getMessage());
			return false;
		} finally {
            SqlUtil.close(rs, ps, conn);
		}
	}

	public boolean createDept() {
		String sql = "insert into hr_department (deptid, deptname, cpny_id, parent_dept_id, dept_level) values (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = services.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.deptId);
			ps.setString(2, this.deptName);
			ps.setString(3, this.getCpnyId());
			ps.setString(4, this.parentDeptId);
			ps.setInt(5, this.deptLevel);
			return ps.execute();
		} catch (Exception e) {
		    Logger.getLogger(getClass()).debug("DeptAdmin.java:createDept():" + e.getMessage());
			return false;
		} finally {
            SqlUtil.close(ps, conn);
		}
	}

	public void modifyDept() {
		String sql = "update hr_department set deptname = ? where deptid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = services.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.deptName);
			ps.setString(2, this.deptId);
			ps.executeUpdate();
		} catch (Exception e) {
            Logger.getLogger(getClass()).debug("DeptAdmin.java:modifyDept():" + e.getMessage());
		} finally {
            SqlUtil.close(ps, conn);
		}
	}

	public void removeDept() {
		String sql = "delete from hr_department where deptid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = services.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.deptId);
			ps.execute();
		} catch (Exception e) {
            Logger.getLogger(getClass()).debug("DeptAdmin.java:removeDept():" + e.getMessage());
		} finally {
            SqlUtil.close(ps, conn);
		}
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

	public int getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getCpnyId() {
		return cpnyid;
	}

	public void setCpnyId(String cpnyid) {
		this.cpnyid = cpnyid;
	}

}
