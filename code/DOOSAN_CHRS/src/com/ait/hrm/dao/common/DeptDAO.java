package com.ait.hrm.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.LGRuntimeException;
import com.ait.hrm.bean.Department;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class DeptDAO {

	private static final Logger log = Logger.getLogger(DeptDAO.class);

	public DeptDAO() {
		super();
	}

	/**
	 * getSupervisedDeptList
	 * 
	 * @param supervisorID
	 *            String
	 * @return List
	 */
	public List getSupervisedDeptList(String supervisorID)
			throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM HR_DEPT_TREE_V WHERE DEPTID IN (SELECT SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO WHERE AR_SUPERVISOR_ID =?)";
		List list = new ArrayList();
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, supervisorID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createDepartment(rs));
			}
		} catch (SQLException e) {
			throw new DataAccessException("cant get all department", e);
		} catch (ServiceLocatorException e) {

		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;

	}

	public List getAllDept() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		// 统一从HR_DEPT_TREE_V视图中查询部门数据
		String SQL = "select hdtv.deptid,hdtv.deptname,hdtv.dept_level from HR_DEPT_TREE_V hdtv ORDER BY hdtv.DEPTID";
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				list.add(createDepartment(rs));
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug("can not get all department \n" + ex.toString());
			throw new LGRuntimeException("can not get all department", ex);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
	}

	public static List getGrantDept(String adminId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String sql = "SELECT DEPTID, DEPTNAME, DEPT_LEVEL, PARENT_DEPT_ID "
					+ "FROM HR_DEPT_TREE_V "
					+ "WHERE DEPTID IN "
						+ "(SELECT SY_ADMIN_DEPTID.ADMIN_DEPTID "
							+ "FROM SY_ADMIN_DEPTID, SY_ADMIN "
							+ "WHERE SY_ADMIN_DEPTID.ADMIN_NO = SY_ADMIN.ADMINNO "
								+ "AND SY_ADMIN.ADMINID = ?"
						+ ") ORDER BY DEPTID";
		log.debug(sql);
		try {
			con =  JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			log.debug("adminId : " + adminId);
			pstmt.setString(1, adminId);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(createDepartment(rs));
		} catch (Exception e) {
			log.error(e.toString());
			throw new LGRuntimeException("cant get all department", e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * changeDeptName
	 * 
	 * @param deptID
	 *            String
	 * @return int
	 */
	public int changeDeptName(String deptID, String newName)
			throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con
					.prepareStatement("UPDATE HR_DEPARTMENT SET DEPTNAME=? WHERE DEPTID=?");
			pstmt.setString(1, newName);
			pstmt.setString(2, deptID);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("cant update department", e);
		} catch (ServiceLocatorException e) {
		} finally {
			SqlUtil.close(pstmt, con);
		}
		return 0;
	}

	private static Department createDepartment(ResultSet rs) {
		Department department = new Department();
		try {
			department.setDeptID(rs.getString("DEPTID"));
			department.setDeptName(rs.getString("DEPTNAME"));
			department.setDeptLevel(rs.getInt("DEPT_LEVEL"));
		} catch (Exception ex) {
	//		Logger.getLogger(getClass()).error(ex.toString());
			throw new LGRuntimeException("根据ResultSet创建Department,检测get字段是否正确", ex);
		}
		return department;
	}
}
