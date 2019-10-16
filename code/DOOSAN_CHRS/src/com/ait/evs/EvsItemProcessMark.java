/*
 * 创建日期 2005-7-13
 * 
 * Company: AIT
 * 
 * @author QING
 * 
 * @version 1.0
 */
package com.ait.evs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class EvsItemProcessMark implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String evEmpId;

	private String evEmpName;

	private String evPeriodId;

	private String evProcessId;

	private String evProcessName;

	private String evItemId;

	private String evItemName;

	private String evDeptId;

	private String evDeptName;

	private float evItemProcessMark;

	private static ServiceLocator services;

	public EvsItemProcessMark() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsItemProcessMark(String evEmpId, String evPeriodId) {
		this.evEmpId = evEmpId;
		this.evPeriodId = evPeriodId;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return 返回 evDeptId。
	 */
	public String getEvDeptId() {
		return evDeptId;
	}

	/**
	 * @param evDeptId
	 *            要设置的 evDeptId。
	 */
	public void setEvDeptId(String evDeptId) {
		this.evDeptId = evDeptId;
	}

	/**
	 * @return 返回 evDeptName。
	 */
	public String getEvDeptName() {
		return evDeptName;
	}

	/**
	 * @param evDeptName
	 *            要设置的 evDeptName。
	 */
	public void setEvDeptName(String evDeptName) {
		this.evDeptName = evDeptName;
	}

	/**
	 * @return 返回 evEmpId。
	 */
	public String getEvEmpId() {
		return evEmpId;
	}

	/**
	 * @param evEmpId
	 *            要设置的 evEmpId。
	 */
	public void setEvEmpId(String evEmpId) {
		this.evEmpId = evEmpId;
	}

	/**
	 * @return 返回 evEmpName。
	 */
	public String getEvEmpName() {
		return evEmpName;
	}

	/**
	 * @param evEmpName
	 *            要设置的 evEmpName。
	 */
	public void setEvEmpName(String evEmpName) {
		this.evEmpName = evEmpName;
	}

	/**
	 * @return 返回 evItemId。
	 */
	public String getEvItemId() {
		return evItemId;
	}

	/**
	 * @param evItemId
	 *            要设置的 evItemId。
	 */
	public void setEvItemId(String evItemId) {
		this.evItemId = evItemId;
	}

	/**
	 * @return 返回 evItemName。
	 */
	public String getEvItemName() {
		return evItemName;
	}

	/**
	 * @param evItemName
	 *            要设置的 evItemName。
	 */
	public void setEvItemName(String evItemName) {
		this.evItemName = evItemName;
	}

	/**
	 * @return 返回 evItemProcessMark。
	 */
	public float getEvItemProcessMark() {
		return evItemProcessMark;
	}

	/**
	 * @param evItemProcessMark
	 *            要设置的 evItemProcessMark。
	 */
	public void setEvItemProcessMark(float evItemProcessMark) {
		this.evItemProcessMark = evItemProcessMark;
	}

	/**
	 * @return 返回 evPeriodId。
	 */
	public String getEvPeriodId() {
		return evPeriodId;
	}

	/**
	 * @param evPeriodId
	 *            要设置的 evPeriodId。
	 */
	public void setEvPeriodId(String evPeriodId) {
		this.evPeriodId = evPeriodId;
	}

	/**
	 * @return 返回 evProcessId。
	 */
	public String getEvProcessId() {
		return evProcessId;
	}

	/**
	 * @param evProcessId
	 *            要设置的 evProcessId。
	 */
	public void setEvProcessId(String evProcessId) {
		this.evProcessId = evProcessId;
	}

	/**
	 * @return 返回 evProcessName。
	 */
	public String getEvProcessName() {
		return evProcessName;
	}

	/**
	 * @param evProcessName
	 *            要设置的 evProcessName。
	 */
	public void setEvProcessName(String evProcessName) {
		this.evProcessName = evProcessName;
	}

	/**
	 * 取得相应被评价者的相应评价流程相应评价项目的分数
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvEmpItemProcessMark() throws DataAccessException {
		List lEvEmpItemProcessMark = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT * FROM evs_item_process_mark_v WHERE ev_emp_id=? AND ev_period_id=? ";
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setString(1, this.evEmpId);
			pstmt.setString(2, this.evPeriodId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsItemProcessMark evsIPM = new EvsItemProcessMark();
				evsIPM.setEvDeptId(rs.getString("ev_dept_id"));
				evsIPM.setEvDeptName(rs.getString("ev_dept_name"));
				evsIPM.setEvEmpId(rs.getString("ev_emp_id"));
				evsIPM.setEvEmpName(rs.getString("ev_emp_name"));
				evsIPM.setEvItemId(rs.getString("ev_item_id"));
				evsIPM.setEvItemName(rs.getString("ev_item_name"));
				evsIPM.setEvItemProcessMark(rs.getFloat("ev_item_process_mark"));
				evsIPM.setEvPeriodId(rs.getString("ev_period_id"));
				evsIPM.setEvProcessId(rs.getString("ev_process_id"));
				evsIPM.setEvProcessName(rs.getString("ev_process_name"));
				lEvEmpItemProcessMark.add(evsIPM);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvEmpItemProcessMark statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvEmpItemProcessMark statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvEmpItemProcessMark;

	}

	/**
	 * 取得相应流程相应项目分数
	 * 
	 * @param evProcessId
	 * @throws DataAccessException
	 */
	public void getEvEmpItemProcessMarkByProcessId(String evProcessId,
			String evItemId) throws DataAccessException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT * FROM evs_item_process_mark_v2 "
				+ "WHERE ev_emp_id=? AND ev_period_id=? AND ev_process_id=? AND ev_item_id=? ";
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setString(1, this.evEmpId);
			pstmt.setString(2, this.evPeriodId);
			pstmt.setString(3, evProcessId);
			pstmt.setString(4, evItemId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				this.setEvDeptId(rs.getString("ev_dept_id"));
				this.setEvDeptName(rs.getString("ev_dept_name"));
				this.setEvEmpId(rs.getString("ev_emp_id"));
				this.setEvEmpName(rs.getString("ev_emp_name"));
				this.setEvItemId(rs.getString("ev_item_id"));
				this.setEvItemName(rs.getString("ev_item_name"));
				this.setEvItemProcessMark(rs.getFloat("ev_item_process_mark"));
				this.setEvPeriodId(rs.getString("ev_period_id"));
				this.setEvProcessId(rs.getString("ev_process_id"));
				this.setEvProcessName(rs.getString("ev_process_name"));

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvEmpItemProcessMarkByProcessId statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvEmpItemProcessMarkByProcessId statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}

	}
	/**
	 * 取得相应被评价者的相应评价流程相应评价项目的分数
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<EvsItemProcessMark> getEvEmpItemProcessMark(String evEmpId,String evPeriodId,String evProcessId) throws DataAccessException {
		List<EvsItemProcessMark> lEvEmpItemProcessMark = new Vector<EvsItemProcessMark>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT * FROM evs_item_process_mark ");
		sql.append(" WHERE ev_emp_id=?  ");
		sql.append(" 	AND ev_period_id=?   ");
		sql.append(" 	AND ev_process_id=?  ");
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, evEmpId);
			pstmt.setString(2, evPeriodId);
			pstmt.setString(3, evProcessId);
			Logger.getLogger(this.getClass()).debug(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsItemProcessMark evsIPM = new EvsItemProcessMark();
				evsIPM.setEvEmpId(rs.getString("ev_emp_id"));
				evsIPM.setEvItemId(rs.getString("ev_item_id"));
				evsIPM.setEvItemProcessMark(rs.getFloat("ev_mark"));
				evsIPM.setEvPeriodId(rs.getString("ev_period_id"));
				evsIPM.setEvProcessId(rs.getString("ev_process_id"));
				lEvEmpItemProcessMark.add(evsIPM);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvEmpItemProcessMark statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvEmpItemProcessMark statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvEmpItemProcessMark;

	}
	/**
	 * 取得评价项目各分数的人员总数
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int getEvEmpItemProcessMarkCnt(SimpleMap map,String evItemId,String evMarkValue) throws DataAccessException {
	    	int cnt=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String I_PERIOD_SQL="";
		String I_PROCESS_SQL="";
		
		String DEPT_SQL = "";
		String POSITION_SQL="";
		String PERIOD_SQL = "";
		String TYPE_SQL = "";
		String MASTER_SQL = "";
		String PROCESS_SQL = "";
		String PROCESS_MASTER_SQL="";

		if (!StringUtil.checkNull(map.getString("DEPTID")).equals("")) {

		    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
			    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
			    + map.get("DEPTID")
			    + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
		}
		if (!StringUtil.checkNull(map.getString("POSITION")).equals("")) {
		    POSITION_SQL = " AND position_code in (" + Constants.mPositionCondition.get(map.get("POSITION")) + ")";
		}
		
		if (!StringUtil.checkNull(map.getString("PERIODID")).equals("")) {
		    PERIOD_SQL = " AND ev_period_id='" + map.get("PERIODID") + "' ";
		    I_PERIOD_SQL = " AND ev_period_id='" + map.get("PERIODID") + "' ";
		}
		
		if (!StringUtil.checkNull(map.getString("TYPEID")).equals("")) {
		    TYPE_SQL = " AND ev_type_id='" + map.get("TYPEID") + "' ";
		    
		}
		
		if (!StringUtil.checkNull(map.getString("PROCESSID")).equals("")) {
		    PROCESS_SQL = " AND current_process_id='" + map.get("PROCESSID")+ "' " +
		    		  " AND ev_master_process_id='" + map.get("PROCESSID")+ "' ";
//		    PROCESS_SQL = 
//			  " AND ev_master_process_id='" + map.get("PROCESSID")+ "' ";
		    I_PROCESS_SQL=" AND ev_process_id='" + map.get("PROCESSID")+ "' ";
		    
		}
		
		if (!StringUtil.checkNull(map.getString("MASTERID")).equals("")) {
		    MASTER_SQL = " AND ev_master='" + map.get("MASTERID") + "' ";
		}
		
		if(!StringUtil.checkNull(map.getString("PROCESSMASTERID")).equals("")){
		    PROCESS_MASTER_SQL=" AND EXISTS ( SELECT * FROM EVS_MASTER " 
				  +" WHERE EVS_MASTER.EV_EMP_ID=EVS_MASTER_EMP_V2.EV_EMP_ID "
				  +" AND EVS_MASTER.EV_MASTER='"+map.getString("PROCESSMASTERID")+"'" 
		    		  +" AND EVS_MASTER.EV_PERIOD_ID=EVS_MASTER_EMP_V2.EV_PERIOD_ID ) ";		
		}
		
		StringBuffer sql=new StringBuffer();
		sql.append( " SELECT COUNT(ev_emp_id) cnt FROM EVS_ITEM_PROCESS_MARK ");
		sql.append( " 	   WHERE ev_emp_id in (SELECT ev_emp_id FROM evs_master_emp_v2 WHERE 1=1  ");
	        sql.append(DEPT_SQL);
	        sql.append(POSITION_SQL);
	        sql.append(PERIOD_SQL);
	        sql.append(TYPE_SQL);
	        sql.append(PROCESS_SQL);
	        sql.append(MASTER_SQL);
	        sql.append(PROCESS_MASTER_SQL);
	        sql.append(" ) ");
	        sql.append(I_PERIOD_SQL);
	        sql.append(I_PROCESS_SQL);
	        sql.append(" AND ev_item_id='"+evItemId+"'");
	        sql.append(" AND ev_mark='"+evMarkValue+"'");
	        
	        
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			Logger.getLogger(this.getClass()).debug(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt=rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvEmpItemProcessMarkCnt statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvEmpItemProcessMarkCnt statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return cnt;

	}
}