package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

/**
 * @author Pennix
 * @date Apr 21, 2009
 */
public class ArImportShiftDAO {

	private static final Logger logger = Logger.getLogger(ArImportShiftDAO.class);
	
	public String getPersonIdByEmpId(String empId, String cpnyId) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getConnection();
			pstmt = conn.prepareStatement("SELECT get_personid(?, ?) AS PERSONID FROM DUAL ");
			pstmt.setString(1, empId);
			pstmt.setString(2, cpnyId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			else
				return "ERROR";
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	
	public boolean isArSupervisorExist(String personId, String supervisorId) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getConnection();
			
			logger.debug("CHECK_AR_SUPERVISOR = " + CHECK_AR_SUPERVISOR);
			
			pstmt = conn.prepareStatement(CHECK_AR_SUPERVISOR);
			pstmt.setString(1, personId);
			pstmt.setString(2, supervisorId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					return true;
				else
					return false;
			} else
				return false;
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	
	public List<String> getAllShiftList() throws ServiceLocatorException, SQLException {
		List<String> shiftList = new ArrayList<String>() ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getConnection();
			
			String sql = " SELECT SHIFT_NO FROM AR_SHIFT010 " ;
			
			logger.debug("GET_ALL_SHIFIT = " + sql);
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				shiftList.add(rs.getString("SHIFT_NO")) ;
			} 
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return shiftList ;
	}
	
	public boolean isExist(String dateStr, String personId) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getConnection();
			
			logger.debug("CHECK_EXIST = " + CHECK_EXIST);
			
			pstmt = conn.prepareStatement(CHECK_EXIST);
			pstmt.setString(1, dateStr);
			pstmt.setString(2, personId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					return true;
				else
					return false;
			} else
				return false;
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	public int insert(String dateStr, String personId, int shiftNo) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = serviceLocator.getConnection();
			
			logger.debug("INSERT = " + INSERT);
			
			pstmt = conn.prepareStatement(INSERT);
			logger.debug(INSERT);
			pstmt.setString(1, personId);
			pstmt.setInt(2, shiftNo);
			pstmt.setString(3, dateStr);
			pstmt.setString(4, personId);
			return pstmt.executeUpdate();
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int update(String dateStr, String personId, int shiftNo) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = serviceLocator.getConnection();
			
			logger.debug("UPDATE = " + UPDATE);
			
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setInt(1, shiftNo);
			pstmt.setString(2, dateStr);
			pstmt.setString(3, personId);
			return pstmt.executeUpdate();
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	private static final String CHECK_EXIST = "SELECT COUNT(*) FROM ar_schedule WHERE ar_date_str = ? AND empid = ?";
	
	private static final String CHECK_AR_SUPERVISOR = "SELECT COUNT(HR.PERSON_ID) FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = ? AND HR.ACTIVITY = 1 AND EXISTS (SELECT T.AR_SUPERVISOR_NO FROM AR_SUPERVISOR_INFO T WHERE HR.DEPTID = T.SUPERVISED_DEPTID AND T.AR_SUPERVISOR_ID = ? ) AND ROWNUM = 1";
	
	private static final String INSERT = "INSERT INTO ar_schedule (pk_no, empid, shift_no, ar_date_str, lock_yn) SELECT ar_schedule_seq.NEXTVAL, ?, ?, ?, 'N' FROM HR_EMPLOYEE A WHERE A.ACTIVITY = 1 AND A.PERSON_ID = ? ";

	private static final String UPDATE = "UPDATE ar_schedule SET shift_no = ? WHERE ar_date_str = ? AND empid = ?";

	private static ArImportShiftDAO INSTANCE = null;

	private static ServiceLocator serviceLocator = null;

	private ArImportShiftDAO() {
		try {
			serviceLocator = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
		}
	}

	public static ArImportShiftDAO getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ArImportShiftDAO();
		return INSTANCE;
	}
}