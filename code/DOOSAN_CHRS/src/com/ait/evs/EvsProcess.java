package com.ait.evs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class EvsProcess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServiceLocator services;
	private static final Logger logger = Logger.getLogger(EvsProcess.class) ;

	public EvsProcess() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsProcess(String evPeriodId, String evTypeId, String evProcessId,
			String evProcessName, int evProcessOrder, String evProcessSDate,
			String evProcessEDate) {
		this.evProcessID = evProcessId;
		this.evPeriodID = evPeriodId;
		this.evTypeID = evTypeId;
		this.evProcessName = evProcessName;
		this.evProcessSDate = evProcessSDate;
		this.evProcessEDate = evProcessEDate;
		this.evProcessOrder = evProcessOrder;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsProcess(String evPeriodId, String evTypeId) {
		this.evPeriodID = evPeriodId;
		this.evTypeID = evTypeId;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsProcess(String evPeriodId, String evTypeId, String evProcessId) {
		this.evPeriodID = evPeriodId;
		this.evTypeID = evTypeId;
		this.evProcessID = evProcessId;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEvEmpId() {
		return evEmpId;
	}

	public void setEvEmpId(String evEmpId) {
		this.evEmpId = evEmpId;
	}

	public String getEvProcessID() {
		return evProcessID;
	}

	public void setEvProcessID(String evProcessID) {
		this.evProcessID = evProcessID;
	}

	public String getEvPeriodID() {
		return evPeriodID;
	}

	public void setEvPeriodID(String evPeriodID) {
		this.evPeriodID = evPeriodID;
	}

	public String getEvTypeID() {
		return evTypeID;
	}

	public void setEvTypeID(String evTypeID) {
		this.evTypeID = evTypeID;
	}

	public String getEvtypeName() {
		return evtypeName;
	}

	public void setEvtypeName(String evtypeName) {
		this.evtypeName = evtypeName;
	}

	public String getEvProcessName() {
		return evProcessName;
	}

	public void setEvProcessName(String evProcessName) {
		this.evProcessName = evProcessName;
	}

	public int getEvProcessOrder() {
		return evProcessOrder;
	}

	public void setEvProcessOrder(int evProcessOrder) {
		this.evProcessOrder = evProcessOrder;
	}

	public String getEvProcessSDate() {
		return evProcessSDate;
	}

	public void setEvProcessSDate(String evProcessSDate) {
		this.evProcessSDate = evProcessSDate;
	}

	public String getEvProcessEDate() {
		return evProcessEDate;
	}

	public void setEvProcessEDate(String evProcessEDate) {
		this.evProcessEDate = evProcessEDate;
	}

	private final static String DEL_EVS_TYPEPROCESS = "DELETE evs_type_process "
			+ " WHERE ev_period_id=? AND ev_type_id=? AND ev_process_id=? ";

	private final static String DEL_RELATION = " DELETE evs_relation "
			+ " WHERE ev_type_id=? AND ev_period_id=? "
			+ " AND ev_process_id=? ";

	/**
	 * 删除评价流程评价类型关系并删除相关EVS_RELATION 表中信息
	 * 
	 * @throws DataAccessException
	 */
	public void delEvsTypeProcess() throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DEL_RELATION);
			pstmt.setString(1, this.evTypeID);
			pstmt.setString(2, this.evPeriodID);
			pstmt.setString(3, this.evProcessID);
			pstmt.executeUpdate();
			pstmt = null;
			pstmt = con.prepareStatement(DEL_EVS_TYPEPROCESS);
			pstmt.setString(1, this.evPeriodID);
			pstmt.setString(2, this.evTypeID);
			pstmt.setString(3, this.evProcessID);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delEvsTypeProcess statistics", sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for delEvsTypeProcess statistics", sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	public void delEvsTypeProcess(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(DEL_RELATION);
			pstmt.setString(1, this.evTypeID);
			pstmt.setString(2, this.evPeriodID);
			pstmt.setString(3, this.evProcessID);
			pstmt.executeUpdate();
			pstmt = null;
			pstmt = con.prepareStatement(DEL_EVS_TYPEPROCESS);
			pstmt.setString(1, this.evPeriodID);
			pstmt.setString(2, this.evTypeID);
			pstmt.setString(3, this.evProcessID);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delEvsTypeProcess statistics", sqle);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (SQLException e) {
				}
			}
		}
	}

	private final static String ADD_EVS_TYPEPROCESS = " INSERT INTO "
			+ " evs_type_process(ev_type_id,ev_period_id,ev_process_id,ev_process_order,"
			+ " ev_process_sdate,ev_process_edate) VALUES (?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'))";

	/**
	 * 添加评价流程评价类型关系
	 * 
	 * @throws DataAccessException
	 */
	public void addEvsTypeProcess() throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			this.delEvsTypeProcess(con);
			pstmt = con.prepareStatement(ADD_EVS_TYPEPROCESS);
			pstmt.setString(1, this.evTypeID);
			pstmt.setString(2, this.evPeriodID);
			pstmt.setString(3, this.evProcessID);
			pstmt.setInt(4, this.evProcessOrder);
			pstmt.setString(5, this.evProcessSDate);
			pstmt.setString(6, this.evProcessEDate);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant add addEvsTypeProcess exception; ", ex);
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for adding addEvsTypeProcess");
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	private final static String UPDATE_EVSPROCESS_BY_TYPEPERIOD = " UPDATE evs_type_process SET ev_process_order=?,"
			+ " ev_process_sdate=to_date(?,'yyyy-mm-dd'),ev_process_edate=to_date(?,'yyyy-mm-dd') "
			+ " WHERE ev_period_id=? AND ev_type_id=? AND ev_process_id=? ";

	/**
	 * 批量更新评价流程
	 * 
	 * @param lEvsProcess
	 * @throws DataAccessException
	 */
	public void updateEvsProcessByTypePeriod(List lEvsProcess)
			throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			if (lEvsProcess != null) {
				int lEvProcessSize = lEvsProcess.size();
				for (int i = 0; i < lEvProcessSize; i++) {
					EvsProcess evs = (EvsProcess) lEvsProcess.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(UPDATE_EVSPROCESS_BY_TYPEPERIOD);
					pstmt.setInt(1, evs.getEvProcessOrder());
					pstmt.setString(2, evs.getEvProcessSDate());
					pstmt.setString(3, evs.getEvProcessEDate());
					pstmt.setString(4, evs.getEvPeriodID());
					pstmt.setString(5, evs.getEvTypeID());
					pstmt.setString(6, evs.getEvProcessID());
					pstmt.executeUpdate();
				}
			}
			con.commit();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant update updateEvsProcessByTypePeriod exception; ", ex);
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateing updateEvsProcessByTypePeriod");
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	private final static String GET_PROCESS_BY_TYPEPERIOD = "  SELECT ev_period_id,"
			+ " ev_period_name,ev_type_id,ev_type_name, "
			+ " ev_process_id,ev_process_name,ev_process_order, "
			+ " TO_CHAR(ev_process_edate,'yyyy-mm-dd') AS ev_process_edate,"
			+ " TO_CHAR(ev_process_sdate,'yyyy-mm-dd') AS ev_process_sdate "
			+ " FROM EVS_TYPE_PROCESS_V "
			+ " WHERE ev_period_id=? AND ev_type_id=? "
			+ " ORDER BY ev_process_order ";

	/**
	 * 取得相应评价类型评价季度的评价的流程列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getProcessByTypePeriod() throws DataAccessException,
			ServiceLocatorException {
		List lEvProcess = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_PROCESS_BY_TYPEPERIOD);
			pstmt.setString(1, this.evPeriodID);
			pstmt.setString(2, this.evTypeID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsProcess evsProcess = new EvsProcess();
				evsProcess.setEvPeriodID(rs.getString("ev_period_id"));
				evsProcess.setEvProcessEDate(rs.getString("ev_process_edate"));
				evsProcess.setEvProcessSDate(rs.getString("ev_process_sdate"));
				evsProcess.setEvProcessID(rs.getString("ev_process_id"));
				evsProcess.setEvProcessName(rs.getString("ev_process_name"));
				evsProcess.setEvProcessOrder(rs.getInt("ev_process_order"));
				evsProcess.setEvTypeID(rs.getString("ev_type_id"));
				evsProcess.setEvtypeName(rs.getString("ev_type_name"));
				lEvProcess.add(evsProcess);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getItemByTypePeriod statistics",
					sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvProcess;
	}

	private final static String GET_PROCESS_BY_TYPEPERIODPROCESS = "  SELECT ev_period_id,"
			+ " ev_period_name,ev_type_id,ev_type_name, "
			+ " ev_process_id,ev_process_name,ev_process_order, "
			+ " TO_CHAR(ev_process_edate,'yyyy-mm-dd') AS ev_process_edate,"
			+ " TO_CHAR(ev_process_sdate,'yyyy-mm-dd') AS ev_process_sdate "
			+ " FROM EVS_TYPE_PROCESS_V "
			+ " WHERE ev_period_id=? AND ev_type_id=? AND ev_process_id=? "
			+ " ORDER BY ev_process_order ";

	/**
	 * 取得相应评价类型评价季度的评价的流程
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public void getProcessByTypePeriodProcess() throws DataAccessException,
			ServiceLocatorException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_PROCESS_BY_TYPEPERIODPROCESS);
			pstmt.setString(1, this.evPeriodID);
			pstmt.setString(2, this.evTypeID);
			pstmt.setString(3, this.evProcessID);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				this.setEvPeriodID(rs.getString("ev_period_id"));
				this.setEvProcessEDate(rs.getString("ev_process_edate"));
				this.setEvProcessSDate(rs.getString("ev_process_sdate"));
				this.setEvProcessID(rs.getString("ev_process_id"));
				this.setEvProcessName(rs.getString("ev_process_name"));
				this.setEvProcessOrder(rs.getInt("ev_process_order"));
				this.setEvTypeID(rs.getString("ev_type_id"));
				this.setEvtypeName(rs.getString("ev_type_name"));

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getProcessByTypePeriodProcess statistics",
					sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}

	}

	private final static String GET_PROCESS_BY_OPERATE = " SELECT DISTINCT get_code_name(ev_process_id) AS ev_process_name,ev_process_id,ev_operate_id "
			+ " FROM evs_relation WHERE ev_operate_id=? ";

	/**
	 * 取得相应评价类型评价季度的评价的流程列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getProcessByOperate(String operateId)
			throws DataAccessException {
		List lEvProcess = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_PROCESS_BY_OPERATE);
			pstmt.setString(1, operateId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsProcess evsProcess = new EvsProcess();
				evsProcess.setEvProcessID(rs.getString("ev_process_id"));
				evsProcess.setEvProcessName(rs.getString("ev_process_name"));
				lEvProcess.add(evsProcess);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getProcessByOperate statistics",
					sqle);
		} catch (ServiceLocatorException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getProcessByOperate statistics",
					sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvProcess;
	}
	/**
	 * 取得当前流程之前所有流程
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<EvsProcess> getPreEvsProcess(SimpleMap map) throws DataAccessException{
	    List<EvsProcess> lEvsProcess=new ArrayList<EvsProcess>();
            String A_PERIOD_SQL = "";
            String A_TYPE_SQL = "";
            String B_PERIOD_SQL = "";
            String B_TYPE_SQL = "";
            String B_PROCESS_SQL = "";
	    if (!StringUtil.checkNull(map.getString("PERIODID")).equals("")) {
		A_PERIOD_SQL = " AND a.ev_period_id='" + map.get("PERIODID") + "' ";
		B_PERIOD_SQL = " AND b.ev_period_id='" + map.get("PERIODID") + "' ";
            }else{
        	return null;
            }
            
            if (!StringUtil.checkNull(map.getString("TYPEID")).equals("")) {
                A_TYPE_SQL = " AND a.ev_type_id='" + map.get("TYPEID") + "' ";
                B_TYPE_SQL = " AND b.ev_type_id='" + map.get("TYPEID") + "' ";
            }else{
        	A_TYPE_SQL = " AND (a.ev_type_id ='EVTYPE001' or a.ev_type_id='EVTYPE004')";
                B_TYPE_SQL = " AND (b.ev_type_id ='EVTYPE001' or b.ev_type_id='EVTYPE004')";
            }
            
            if (!StringUtil.checkNull(map.getString("PROCESSID")).equals("")) {
                B_PROCESS_SQL = " AND b.ev_process_id='" + map.get("PROCESSID")+ "' ";
                
            }else{
        	return null;
            }
            StringBuffer sql=new StringBuffer();
            sql.append(" select a.* from evs_type_process_v a where 1=1 ");
            sql.append("	and a.ev_process_order< (");
            sql.append("		SELECT max(b.ev_process_order) FROM evs_type_process b WHERE 1=1 ");
            sql.append(B_PERIOD_SQL+B_TYPE_SQL+B_PROCESS_SQL);
            sql.append("		)");
            sql.append(A_PERIOD_SQL+A_TYPE_SQL);
            sql.append("  order by a.ev_process_order ");
            Logger.getLogger(this.getClass()).debug(sql.toString());
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
		con = services.getConnection();
		pstmt = con.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			EvsProcess evsProcess = new EvsProcess();
			evsProcess.setEvPeriodID(rs.getString("ev_period_id"));
			evsProcess.setEvProcessEDate(rs.getString("ev_process_edate"));
			evsProcess.setEvProcessSDate(rs.getString("ev_process_sdate"));
			evsProcess.setEvProcessID(rs.getString("ev_process_id"));
			evsProcess.setEvProcessName(rs.getString("ev_process_name"));
			evsProcess.setEvProcessOrder(rs.getInt("ev_process_order"));
			evsProcess.setEvTypeID(rs.getString("ev_type_id"));
			evsProcess.setEvtypeName(rs.getString("ev_type_name"));
			lEvsProcess.add(evsProcess);
		}
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		throw new DataAccessException(
				"cant execute query for getPreEvsProcess statistics",
				sqle);
	}catch (ServiceLocatorException sqle) {
		sqle.printStackTrace();
		throw new DataAccessException(
				"cant execute query for getPreEvsProcess statistics",
				sqle);
	} finally {
		SqlUtil.close(rs, pstmt, con);
	}
	    return lEvsProcess;
	}
	/**
	 * 取得当前流程之前下一流程
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public EvsProcess getNextEvsProcess(SimpleMap map) throws DataAccessException{

	    EvsProcess evsProcess = new EvsProcess();
            String A_PERIOD_SQL = "";
            String A_TYPE_SQL = "";
            String B_PERIOD_SQL = "";
            String B_TYPE_SQL = "";
            String B_PROCESS_SQL = "";
	    if (!StringUtil.checkNull(map.get("PERIODID")).equals("")) {
		A_PERIOD_SQL = " AND a.ev_period_id='" + map.get("PERIODID") + "' ";
		B_PERIOD_SQL = " AND b.ev_period_id='" + map.get("PERIODID") + "' ";
            }
            
            if (!StringUtil.checkNull(map.get("TYPEID")).equals("")) {
                A_TYPE_SQL = " AND a.ev_type_id='" + map.get("TYPEID") + "' ";
                B_TYPE_SQL = " AND b.ev_type_id='" + map.get("TYPEID") + "' ";
            }else{
        	A_TYPE_SQL = " AND (a.ev_type_id ='EVTYPE001' or a.ev_type_id='EVTYPE004')";
                B_TYPE_SQL = " AND (b.ev_type_id ='EVTYPE001' or b.ev_type_id='EVTYPE004')";
            }
            
            if (!StringUtil.checkNull(map.get("PROCESSID")).equals("")) {
                B_PROCESS_SQL = " AND b.ev_process_id='" + map.get("PROCESSID")+ "' ";
                
            }
            StringBuffer sql=new StringBuffer();
            sql.append(" select a.* from evs_type_process_v a where 1=1 ");
            sql.append("	and a.ev_process_order= (");
            sql.append("		SELECT max(b.ev_process_order) FROM evs_type_process b WHERE 1=1 ");
            sql.append(B_PERIOD_SQL+B_TYPE_SQL+B_PROCESS_SQL);
            sql.append("		)+1");
            sql.append(A_PERIOD_SQL+A_TYPE_SQL);
            Logger.getLogger(this.getClass()).debug(sql.toString());
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
		con = services.getConnection();
		pstmt = con.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			evsProcess.setEvPeriodID(rs.getString("ev_period_id"));
			evsProcess.setEvProcessEDate(rs.getString("ev_process_edate"));
			evsProcess.setEvProcessSDate(rs.getString("ev_process_sdate"));
			evsProcess.setEvProcessID(rs.getString("ev_process_id"));
			evsProcess.setEvProcessName(rs.getString("ev_process_name"));
			evsProcess.setEvProcessOrder(rs.getInt("ev_process_order"));
			evsProcess.setEvTypeID(rs.getString("ev_type_id"));
			evsProcess.setEvtypeName(rs.getString("ev_type_name"));
		}
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		throw new DataAccessException(
				"cant execute query for getNextEvsProcess statistics",
				sqle);
	}catch (ServiceLocatorException sqle) {
		sqle.printStackTrace();
		throw new DataAccessException(
				"cant execute query for getNextEvsProcess statistics",
				sqle);
	} finally {
		SqlUtil.close(rs, pstmt, con);
	}
	    return evsProcess;
	}
	
public List getEvsMasters(String periodId,String ev_dept_id)  {
		
		List list = new ArrayList() ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =  "SELECT T.EV_DEPT_ID, T.EV_DEPT_LEVEL, T.EV_PARENT_DEPT_ID, T.DEPT_TYPE_ID, T.MANAGER_EMP_ID" +
							"  FROM EVS_DEPT T" + 
							" START WITH T.EV_DEPT_ID = '" + ev_dept_id +"'" + 
							"        AND T.EV_PERIOD_ID = '" + periodId +"'" + 
							" CONNECT BY PRIOR T.EV_PARENT_DEPT_ID = T.EV_DEPT_ID" + 
							"       AND T.EV_PERIOD_ID = '" + periodId +"'";

		logger.debug(sql) ;
		
		try {
		
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery() ;
			while(rs.next()){
				HashMap map = new HashMap() ;
				map.put("EV_DEPT_ID", rs.getString("EV_DEPT_ID")) ;
				map.put("EV_DEPT_LEVEL", rs.getString("EV_DEPT_LEVEL")) ;
				map.put("EV_PARENT_DEPT_ID", rs.getString("EV_PARENT_DEPT_ID")) ;
				map.put("DEPT_TYPE_ID", rs.getString("DEPT_TYPE_ID")) ;
				map.put("MANAGER_EMP_ID", rs.getString("MANAGER_EMP_ID")) ;
				
				list.add(map) ;
			}

		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list ;
	}
	
	
	private String evProcessID;

	private String evPeriodID;
	
	private String evEmpId;

	private String evTypeID;

	private String evtypeName;

	private String evProcessName;

	private int evProcessOrder;

	private String evProcessSDate;

	private String evProcessEDate;
}