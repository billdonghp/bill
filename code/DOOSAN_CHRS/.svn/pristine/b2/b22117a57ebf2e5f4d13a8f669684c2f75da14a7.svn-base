package com.ait.ar.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays ;

import org.apache.log4j.Logger;

import com.ait.ar.bean.SupervisorInfo;
import com.ait.ar.dao.SupervisorInfoDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

public class SupervisorInfoDAOImpl implements SupervisorInfoDAO {

	private static ServiceLocator services;

	public SupervisorInfoDAOImpl() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}

	}

	/**
	 * addSupervisorInfo
	 *
	 * @param supervisorInfo Supervisor
	 */
	public int addSupervisorInfo(SupervisorInfo supervisorInfo)
			throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call ar_add_supervisorInfo(?,?,?) }";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setString(1, supervisorInfo.getSupervisorID());
			cs.setString(2, supervisorInfo.getSupervisedDeptID());
			cs.setString(3, supervisorInfo.getCreatedBy());
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for adding supervisor info!", e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;
	}

	/**
	 * getSupervisedDeptList
	 *
	 * @param supervisorID String
	 * @return List
	 */
	public List getSupervisorInfoList(String supervisorID)
			throws DataAccessException {
		List list = new ArrayList();
		Connection con = null;
		String sql = 
			"select AR_SUPERVISOR_ID, SUPERVISED_DEPTID " +
				"from AR_SUPERVISOR_INFO " +
				"where AR_SUPERVISOR_ID=?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, supervisorID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.createSupervisorInfo(rs));
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting supervisorInfo" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * deleteSupervisorInfo
	 * <br>
	 * 考勤员直接管理的对象是员工，我们可以根据输入的员工工号来删除被考勤对象
	 * @param empID String
	 * @return int
	 */
	public int deleteSupervisorInfo(SupervisorInfo supervisorInfo)
			throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call ar_delete_supervisorInfo('" + supervisorInfo.getSupervisorID() + "','" + supervisorInfo.getSupervisedDeptID() + "') }";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for adding supervisor info!", e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;

	}

	/**
	 * getDeptListWithObject
	 * <br>
	 * 根据输入的考勤员编号,找到该考勤员所考勤的员工所在的所有部门.
	 * @param supervisorId String
	 * @return List
	 */
	public List getDeptListWithObject(String supervisorId)
			throws DataAccessException {
		List list = new ArrayList();
		Connection con = null;
		String sql = 
			"SELECT " +
			"DISTINCT DEPTID " +
			"FROM HR_EMPLOYEE " +
			"WHERE person_id IN (" +
				"SELECT SUPERVISED_DEPTID " + 
				"FROM AR_SUPERVISOR_INFO " +
				"WHERE AR_SUPERVISOR_ID='" + supervisorId + "')";
		Logger.getLogger(getClass()).debug(sql);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;

	}

	/**
	 * createSupervisorInfo
	 *
	 * @param rs ResultSet
	 * @return SupervisorInfo
	 */
	private SupervisorInfo createSupervisorInfo(ResultSet rs) {
		SupervisorInfo supervisorInfo = new SupervisorInfo();
		try {
			supervisorInfo.setSupervisedDeptID(StringUtil.checkNull(rs
					.getString("SUPERVISED_DEPTID")));
			supervisorInfo.setSupervisorID(StringUtil.checkNull(rs
					.getString("AR_SUPERVISOR_ID")));
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		return supervisorInfo;
	}

  /**
   * editSupervisorScreenGrantNo
   *
   * @param supervisorInfo Supervisor
   */
	public void editSupervisorScreenGrantNo(String supervisorId) throws DataAccessException 
	{
		String supervisorScreenGrantNo = "1" ;
		String screenGrantNo = "" ;
		Connection con = null;
		String sql = "SELECT T.ADMINID,T.SCREEN_GRANT_NO FROM SY_ADMIN T WHERE T.ADMINID = '" + supervisorId + "'";
		String updateSql = "UPDATE SY_ADMIN T SET T.SCREEN_GRANT_NO = ? WHERE T.ADMINID = ? ";
		Logger.getLogger(getClass()).debug(sql);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				screenGrantNo  = rs.getString(2) ;
			}
			
			String[] screenNo = screenGrantNo.split(",") ;
			
			Arrays.sort(screenNo) ;
			
			if (Arrays.binarySearch(screenNo, supervisorScreenGrantNo) == -1 )
			{
				Logger.getLogger(getClass()).debug(updateSql);
				pstmt = con.prepareStatement(updateSql);
				pstmt.setString(1, screenGrantNo + "," + supervisorScreenGrantNo);
				pstmt.setString(2, supervisorId);
				pstmt.executeUpdate() ;
 			}
			
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}

	 /**
	   * deleteSupervisorScreenGrantNo
	   *
	   * @param supervisorInfo Supervisor
	   */
	public void deleteSupervisorScreenGrantNo(String supervisorID) throws DataAccessException {
		String supervisorScreenGrantNo = "1" ;
		String screenGrantNo = "" ;
		StringBuffer grantNo = new StringBuffer(20) ;
		Connection con = null;
		String sql = "SELECT T.ADMINID,T.SCREEN_GRANT_NO FROM SY_ADMIN T WHERE T.ADMINID = '" + supervisorID + "'";
		String updateSql = "UPDATE SY_ADMIN T SET T.SCREEN_GRANT_NO = ? WHERE T.ADMINID = ? ";
		Logger.getLogger(getClass()).debug(sql);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				screenGrantNo  = rs.getString(2) ;
			}
			
			String[] screenNo = screenGrantNo.split(",") ;
			
			Arrays.sort(screenNo) ;
			
			if (Arrays.binarySearch(screenNo, supervisorScreenGrantNo) != -1 )
			{
				Logger.getLogger(getClass()).debug(updateSql);
				for(int i = 0 ; i < screenNo.length ; ++ i)
				{
					if (screenNo[i].equals(supervisorScreenGrantNo))
					{
						continue ;
					}
					grantNo.append(screenNo[i]) ;
					grantNo.append(",") ;
				}
				
				pstmt = con.prepareStatement(updateSql);
				pstmt.setString(1, grantNo.substring(0,grantNo.lastIndexOf(",")));
				pstmt.setString(2, supervisorID);
				pstmt.executeUpdate() ;
 			}
			
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
	}
	
	/**
	 * 
	 * @param supervisorID
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getSupervisorItems(String supervisorID)throws GlRuntimeException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<SimpleMap> list = new ArrayList<SimpleMap>();
		
		String sql  = "select a.item_group_id from ar_supervisor a where a.supervisor_id = "+supervisorID;
		try{
			conn = ConnBean.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				String itemId = rs.getString("item_group_id");
				String itemStr[] = null;

				if(itemId != null){
					itemStr = itemId.split(","); 
				
					for (String string : itemStr) {
						SimpleMap simpleMap = new SimpleMap();
						simpleMap.setString("itemId", string.trim());
						list.add(simpleMap);
					}
				}
			}
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
		}finally{
			SqlUtil.close(rs, st, conn);
		}
		return list;
	}
	
	public void upDateItemId(String supervisorID,String itemId) throws GlRuntimeException{
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "update ar_supervisor a set a.item_group_id = ? where a.supervisor_id = ? ";
		try{
			conn = ConnBean.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemId);
			pst.setString(2, supervisorID);
			pst.executeUpdate();
			
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
		}finally{
			SqlUtil.close(pst,conn);
		}
	}
}
