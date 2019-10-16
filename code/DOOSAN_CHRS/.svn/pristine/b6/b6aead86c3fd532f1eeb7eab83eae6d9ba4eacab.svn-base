package com.ait.evs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 为一些基本数据执行批量增加。 批量操作的表 1.评价期间：EVS_PERIOD 2.评价期间评价类型关系表EVS_PERIOD_TYPE 3.评价项目
 * EVS_TYPE_ITEM 4.评价流程 EVS_TYPE_PROCESS 5.评价项目流程关系 EVS_RELATION 6.评价列
 * EVS_COLUMN 7.评价共同项目 EVS_COMMON_ITEM_DETAIL EVS_COMMON_COLUMN_DETAIL 8.评价部门
 * EVS_DEPT 9.评价部门百分数 EVS_DEPT_RADIO 或 EVS_NORM 10.评价者与被评价者 EVS_EMP EVS_MASTER
 * 
 * @author ait
 * 
 */
public class EvsBatch {

	private static ServiceLocator services;

	private List evTypeList;

	private int monthCount;// 流程的时间的增量

	private String evPeriodIdOld;

	private String evPeriodId;

	private String evPeriodName;
	
	private String evPeriodShortName;

	private String year;

	private String message;// 返回值说明操作状态

	private Map hEvTypeFirstProcess;

	public String getEvPeriodId() {
		return evPeriodId;
	}

	public void setEvPeriodId(String evPeriodId) {
		this.evPeriodId = evPeriodId;
	}

	public List getEvTypeList() {
		return evTypeList;
	}

	public void setEvTypeList(List evTypeList) {
		this.evTypeList = evTypeList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEvPeriodName() {
		return evPeriodName;
	}

	public void setEvPeriodName(String evPeriodName) {
		this.evPeriodName = evPeriodName;
	}

	public int getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}

	public String getEvPeriodIdOld() {
		return evPeriodIdOld;
	}

	public void setEvPeriodIdOld(String evPeriodIdOld) {
		this.evPeriodIdOld = evPeriodIdOld;
	}

	public Map getHEvTypeFirstProcess() {
		return hEvTypeFirstProcess;
	}

	public void setHEvTypeFirstProcess(Map evTypeFirstProcess) {
		hEvTypeFirstProcess = evTypeFirstProcess;
	}

	public EvsBatch(String evPeriodIdOld, String year, int monthCount,
			String evPeriodName) {
		this.evPeriodIdOld = evPeriodIdOld;
		this.year = year;
		this.monthCount = monthCount;
		this.evTypeList = new Vector();
		this.hEvTypeFirstProcess = new HashMap();
		this.evPeriodName = evPeriodName;
		this.message = "操作成功";
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public EvsBatch(String evPeriodIdOld, String year, int monthCount,
		String evPeriodName,String evPeriodShortName) {
	this.evPeriodIdOld = evPeriodIdOld;
	this.year = year;
	this.monthCount = monthCount;
	this.evTypeList = new Vector();
	this.hEvTypeFirstProcess = new HashMap();
	this.evPeriodName = evPeriodName;
	this.evPeriodShortName=evPeriodShortName;
	this.message = "操作成功";
	Logger.getLogger(this.getClass()).debug(this.toString());
	try {
		services = ServiceLocator.getInstance();
	} catch (ServiceLocatorException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}

}
	/**
	 * for del
	 * 
	 * @param evPeriodId
	 */
	public EvsBatch(String evPeriodId) {
		this.evPeriodId = evPeriodId;
		this.evTypeList = new Vector();
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String batchAddAll() throws DataAccessException {
		Connection con = null;
		try {
		    
			con = services.getConnection();
			con.setAutoCommit(false);

			this.getEvPeriodMaxId(con);

			this.addEvPeriod(con);
			this.addEvPeriodType(con);
			this.getEvTypeList(con);

			if (evTypeList.size() == 0) {
				message = "操作失败";
				try {
					con.rollback();
				} catch (SQLException e) {
				}
			}
			this.addEvTypeProcess(con);

			this.addEvTypeItem(con);

			this.addEvRelation(con);

			//this.addEvColumn(con);

			this.addEvDept(con);

			//this.addEvDeptRadio(con);

			this.addEvEmp(con);

			this.addEvMaster(con);

			//this.batchAddCommonItem(con);

			con.commit();
		} catch (DataAccessException e1) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchAdd");
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchAdd");
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchAdd");
		} finally {
			SqlUtil.close(con);
		}
		return message;

	}

	public String batchDelAll() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			 

				this.getEvTypeList(con);
				if (evTypeList.size() == 0) {
					message = "操作失败";
					try {
						con.rollback();
					} catch (SQLException e) {
					}
				}

				//this.batchDelCommonItem(con);
				this.delEvEmp(con);
				//this.delEvDeptRadio(con);
				this.delEvDept(con);
				//this.delEvColumn(con);
				//有可能添加
				//this.delEvRelation(con);
				this.delEvTypeItem(con);
				this.delEvTypeProcess(con);
				this.delEvPeriodType(con);
				this.delEvPeriod(con);
			 
			con.commit();
		} catch (DataAccessException e1) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchDel");
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchDel");
		} finally {
			SqlUtil.close(con);
		}
		return message;

	}

	/**
	 * 取得相应评价期间是否在评价数据存在
	 * 
	 * @param con
	 * @return
	 * @throws DataAccessException
	 */
	private boolean isHaveData(Connection con) throws DataAccessException {
		boolean b = true;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM EVS_ITEM_PROCESS_MARK ");
		sql.append(" WHERE ev_period_id=? ");
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodId);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				b = false;
			}
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for isHaveData statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt);
		}
		return b;
	}

	private final static String GET_EVSPERIOD_MAXID = " SELECT  TRIM(TO_CHAR(MAX(SUBSTR(ev_period_id,5,7))+1,'00')) AS maxPeriod FROM evs_period WHERE ev_year=? ";

	/**
	 * 取得最大的评价期间的ID值
	 * 
	 * @param con
	 * @return
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private void getEvPeriodMaxId(Connection con) throws DataAccessException,
			SQLException {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(GET_EVSPERIOD_MAXID);
			pstmt.setString(1, this.year);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("maxPeriod") != null) {
					this.setEvPeriodId(this.year + rs.getString("maxPeriod"));
				} else {
					this.setEvPeriodId(this.year + "01");
				}
			}
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvPeriodMaxId statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt);
		}
	}

	private final static String DEL_EV_PERIOD = " DELETE evs_period WHERE ev_period_id=?";

	/**
	 * 删除评价期间
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvPeriod(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_EV_PERIOD);
			pstmt.setString(1, this.evPeriodId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvPeriod exception; ", ex);

		} finally {
			SqlUtil.close(null, pstmt);
		}

	}

	/**
	 * 添加评价期间
	 * 
	 * @param con
	 */
	private final static String ADD_EV_PERIOD = "INSERT INTO evs_period(ev_year,ev_period_id,ev_period_name) VALUES(?,?,?)";

	private void addEvPeriod(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			// delEvPeriod(con);
			pstmt = con.prepareStatement(ADD_EV_PERIOD);
			int i=0;
			pstmt.setString(++i, this.year);
			pstmt.setString(++i, this.evPeriodId);
			pstmt.setString(++i, this.evPeriodName); 
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addEvPeriod exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String DEL_EV_PERIOD_TYPE = "DELETE evs_period_type WHERE ev_period_id=?";

	/**
	 * 删除评价期期间类型关系
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvPeriodType(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_EV_PERIOD_TYPE);
			pstmt.setString(1, this.evPeriodId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvPeriodType exception; ",
					ex);

		} finally {
			SqlUtil.close(null, pstmt);
		}

	}

	/**
	 * 添加评价期间类型关系
	 * 
	 * @param con
	 */
	private final static String ADD_EV_PERIOD_TYPE = " INSERT INTO evs_period_type (ev_period_id,ev_type_id)"
			+ " SELECT  ?,ev_type_id FROM evs_period_type WHERE ev_period_id=?";

	private void addEvPeriodType(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			// this.delEvPeriodType(con);
			pstmt = con.prepareStatement(ADD_EV_PERIOD_TYPE);

			pstmt.setString(1, this.evPeriodId);
			pstmt.setString(2, this.evPeriodIdOld);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant add addEvPeriodType exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String GET_EVTYPE_LIST = "SELECT get_sys_code(ev_type_id) as ev_type_name,ev_period_id,ev_type_id FROM evs_period_type WHERE ev_period_id=? ";

	/**
	 * 设置所以评价类型
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void getEvTypeList(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(GET_EVTYPE_LIST);
			pstmt.setString(1, this.evPeriodId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsType evType = new EvsType();
				evType.setEvPeriodID(StringUtil.checkNull(rs
						.getString("ev_period_id")));
				evType.setEvTypeID(StringUtil.checkNull(rs
						.getString("ev_type_id")));
				evType.setEvTypeName(StringUtil.checkNull(rs
						.getString("ev_type_name")));
				evTypeList.add(evType);
			}

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  setEvTypeList exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String DEL_EV_TYPE_PROCESS = " DELETE evs_type_process WHERE ev_period_id=? AND ev_type_id=? ";

	/**
	 * 删除评价流程
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvTypeProcess(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			if (this.evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(DEL_EV_TYPE_PROCESS);
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvTypeProcess exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价流程
	 * 
	 * @param con
	 */
	private void addEvTypeProcess(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_type_process ");
		sql
				.append(" ( ev_period_id, ev_type_id, ev_process_id, ev_process_order, ev_process_sdate, ev_process_edate) ");
		sql
				.append(" SELECT ?,ev_type_id,ev_process_id,ev_process_order,ADD_MONTHS(ev_process_sdate,?),ADD_MONTHS(ev_process_edate,?) ");
		sql.append(" FROM evs_type_process ");
		sql.append(" WHERE ev_period_id=? AND ev_type_id=? ");
		// 取得相应评价类型的第一个流程
		StringBuffer firstSql = new StringBuffer();
		firstSql
				.append(" SELECT distinct ev_process_id  FROM evs_type_process ");
		firstSql
				.append(" WHERE ev_process_order=1 AND ev_period_id=? AND ev_type_id= ?");
		try {
			// this.delEvTypeProcess(con);
			if (this.evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					rs = null;
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, this.evPeriodId);
					pstmt.setInt(2, this.monthCount);
					pstmt.setInt(3, this.monthCount);
					pstmt.setString(4, this.evPeriodIdOld);
					pstmt.setString(5, evType.getEvTypeID());
					pstmt.executeUpdate();
					pstmt = null;
					pstmt = con.prepareStatement(firstSql.toString());
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, evType.getEvTypeID());
					rs = pstmt.executeQuery();
					if (rs.next()) {
						hEvTypeFirstProcess.put(evType.getEvTypeID(), rs
								.getString("ev_process_id"));
					}

				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant add addEvTypeProcess exception; ", ex);
		} finally {
			SqlUtil.close(rs, pstmt);
		}
	}

	private final static String DEL_EV_TYPE_ITEM = " DELETE evs_type_item WHERE ev_period_id=? AND ev_type_id=? ";

	/**
	 * 删除评价项目
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvTypeItem(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			if (this.evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(DEL_EV_TYPE_ITEM);
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvTypeItem exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价项目
	 * 
	 * @param con
	 */
	private void addEvTypeItem(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_type_item ");
		sql.append(" ( ev_period_id, ev_type_id, ev_item_id, ev_item_order ) ");
		sql.append(" SELECT ?, ev_type_id, ev_item_id, ev_item_order ");
		sql.append(" FROM evs_type_item ");
		sql.append(" WHERE ev_period_id=? AND ev_type_id=? ");
		try {
			// this.delEvTypeItem(con);
			if (this.evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, this.evPeriodIdOld);
					pstmt.setString(3, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addEvTypeItem exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String DEL_EV_RELATION = " DELETE evs_relation WHERE ev_period_id=? AND ev_type_id=? ";

	/**
	 * 删除评价项目流程关系
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvRelation(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			if (evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(DEL_EV_RELATION);
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvRelation exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价项目流程关系
	 * 
	 * @param con
	 */
	private void addEvRelation(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_relation ");
		sql
				.append(" (ev_period_id, ev_type_id, ev_item_id, ev_process_id, ev_process_prop, ev_operate_id, ev_item_prop, ev_marks_value, ev_marks_name, ev_marks_default) ");
		sql
				.append(" SELECT  ?, ev_type_id, ev_item_id, ev_process_id, ev_process_prop, ev_operate_id, ev_item_prop, ev_marks_value, ev_marks_name, ev_marks_default");
		sql.append(" FROM evs_relation ");
		sql.append(" WHERE ev_period_id=? AND ev_type_id=? ");
		try {
			// this.delEvRelation(con);
			if (this.evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, this.evPeriodIdOld);
					pstmt.setString(3, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addEvRelation exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String DEL_EV_COLUMN = " DELETE evs_column WHERE ev_period_id=? AND ev_type_id=? ";

	/**
	 * 删除评价项目列
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvColumn(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			if (evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(DEL_EV_COLUMN);
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvColumn exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价项目列
	 * 
	 * @param con
	 */
	private void addEvColumn(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_column");
		sql.append(" (ev_period_id, ev_type_id, ev_item_id, ev_column_id) ");
		sql.append(" SELECT ?, ev_type_id, ev_item_id, ev_column_id ");
		sql.append(" FROM evs_column ");
		sql.append(" WHERE ev_period_id=? AND ev_type_id=? ");
		try {
			// this.delEvColumn(con);
			if (this.evTypeList != null) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, this.evPeriodIdOld);
					pstmt.setString(3, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addEvColumn exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String DEL_EV_DEPT = " DELETE evs_dept WHERE ev_period_id=?";

	/**
	 * 删除评价部门
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvDept(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_EV_DEPT);
			pstmt.setString(1, this.evPeriodId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvDept exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价部门
	 * 
	 * @param con
	 */
	private void addEvDept(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_dept ");
		sql
				.append(" (ev_period_id, ev_dept_id, ev_dept_name, ev_dept_level, ev_parent_dept_id, ev_dept_norm, activity) ");
		sql
				.append(" SELECT ?, ev_dept_id, ev_dept_name, ev_dept_level, ev_parent_dept_id, ev_dept_norm, 1");
		sql.append(" FROM evs_dept ");
		sql.append(" WHERE ev_period_id=? ");
		try {
			// this.delEvDept(con);
			pstmt = null;
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodId);
			pstmt.setString(2, this.evPeriodIdOld);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  addEvDept exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	private final static String DEL_EV_DEPT_RADIO = " DELETE evs_dept_radio WHERE ev_period_id=?";

	/**
	 * 删除评价部门百分数
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void delEvDeptRadio(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_EV_DEPT_RADIO);
			pstmt.setString(1, this.evPeriodId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvDeptRadio exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价部门百分数
	 * 
	 * @param con
	 */
	private void addEvDeptRadio(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_dept_radio ");
		sql
				.append(" (EV_PERIOD_ID, EV_DEPT_ID, EV_DEPT_GRADE_ID, EV_GRADE_ID, EV_GRADE_PROP, EV_WAGE_RADIO) ");
		sql
				.append(" SELECT ?, EV_DEPT_ID, EV_DEPT_GRADE_ID, EV_GRADE_ID, EV_GRADE_PROP, EV_WAGE_RADIO ");
		sql.append(" FROM evs_dept_radio ");
		sql.append(" WHERE ev_period_id=? ");
		try {
			// //this.delEvDeptRadio(con);
			pstmt = null;
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodId);
			pstmt.setString(2, this.evPeriodIdOld);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  addEvDeptRadio exception; ",
					ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 删除被评价者
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private final static String DEL_EV_EMP = " DELETE evs_emp WHERE ev_period_id=? AND ev_type_id=? ";

	private void delEvEmp(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			// //this.delEvMaster(con);
			if (null != evTypeList) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(DEL_EV_EMP);
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvEmp exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 删除评价者
	 * 
	 * @param con

	private final static String DEL_EV_MASTER = " DELETE evs_master WHERE ev_period_id=? ";

	private void delEvMaster(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_EV_MASTER);
			pstmt.setString(1, this.evPeriodId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvMaster exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}


	 * 添加被评价者
	 * 
	 * @param con
	 */
	private void addEvEmp(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_emp ");
		sql.append(" (ev_period_id, ev_emp_id, ev_emp_name, ev_dept_id, ev_dept_name, ev_type_id, ev_process_id, activity) ");
		sql.append(" SELECT ?, ev_emp_id, ev_emp_name, HR_GET_DEPTID(ev_emp_id),HR_GET_FULL_DEPTNAME(HR_GET_DEPTID(ev_emp_id)), ev_type_id, ?, 1 ");
		sql.append(" FROM evs_emp ");
		sql.append(" WHERE ev_period_id=? AND ev_type_id=? ");
		try {
			// this.delEvEmp(con);
			if (null != evTypeList) {
				for (int i = 0, k = evTypeList.size(); i < k; i++) {
					EvsType evType = (EvsType) evTypeList.get(i);
					pstmt = null;
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, this.evPeriodId);
					pstmt.setString(2, StringUtil
							.checkNull(this.hEvTypeFirstProcess.get(evType
									.getEvTypeID())));
					pstmt.setString(3, this.evPeriodIdOld);
					pstmt.setString(4, evType.getEvTypeID());
					pstmt.executeUpdate();
				}
			}
			// this.addEvMaster(con);
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  addEvEmp exception; ", ex);
		} catch (Exception ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  addEvEmp exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 添加评价者
	 * 
	 * @param con
	 */
	private void addEvMaster(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_master ");
		sql.append(" (ev_period_id, ev_emp_id, ev_process_id, ev_master, ev_appendable,ev_submitted) ");
		sql.append(" SELECT ?, ev_emp_id, ev_process_id, ev_master, ev_appendable,0 ");
		sql.append(" FROM evs_master ");
		sql.append(" WHERE ev_period_id=?  ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodId);
			pstmt.setString(2, this.evPeriodIdOld);
			pstmt.executeUpdate();
			Logger.getLogger(this.getClass()).debug(sql.toString());
			Logger.getLogger(this.getClass()).debug("－－－－评价者添加完成－－－－");
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  addEvMaster exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 删除共同项目
	 * 
	 * @param con
	 * @return
	 */
	private String batchDelCommonItem(Connection con)
			throws DataAccessException {
		for (int i = 0, k = this.evTypeList.size(); i < k; i++) {
			EvsType evType = (EvsType) evTypeList.get(i);
			List commonItemList = null;
			try {
				commonItemList = this.getCommonItemDetail2(con, evType
						.getEvTypeID());
				if (null != commonItemList) {
					for (int j = 0, m = commonItemList.size(); j < m; j++) {
						try {

							this.delEvCommonItemDetail(con,
									((Integer) commonItemList.get(j))
											.intValue());

						} catch (DataAccessException e1) {
							try {
								con.rollback();
							} catch (SQLException e) {
							}
							e1.printStackTrace();
							throw new DataAccessException(
									"cant  batchDelCommonItem exception; ", e1);

						} catch (Exception e2) {
							try {
								con.rollback();
							} catch (SQLException e) {
							}
							e2.printStackTrace();
							throw new DataAccessException(
									"cant  batchDelCommonItem exception; ", e2);
						}
					}
				}
			} catch (DataAccessException e) {
				try {
					con.rollback();
				} catch (SQLException sqle) {
				}
				e.printStackTrace();
				throw new DataAccessException(
						"cant  batchAddCommonItem exception; ", e);
			}
		}
		return null;
	}

	/**
	 * 删除共同项目
	 * 
	 * @param con
	 * @param seqCommonItemDetailOld
	 * @return
	 * @throws DataAccessException
	 */
	private void delEvCommonItemDetail(Connection con,
			int seqCommonItemDetailOld) throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE  evs_common_item_detail  ");
		sql.append(" WHERE seq_ev_common_item_detail=?  ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, seqCommonItemDetailOld);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant  delEvCommonItemDetail exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * 批量增加评价共同项目
	 * 
	 * @param con
	 * @return
	 * @throws DataAccessException
	 */
	private String batchAddCommonItem(Connection con)
			throws DataAccessException {
		for (int i = 0, k = this.evTypeList.size(); i < k; i++) {
			EvsType evType = (EvsType) evTypeList.get(i);
			List commonItemList = null;
			try {
				commonItemList = this.getCommonItemDetail(con, evType
						.getEvTypeID());
				if (null != commonItemList) {
					for (int j = 0, m = commonItemList.size(); j < m; j++) {
						try {

							this.addEvCommonItemDetail(con,
									((Integer) commonItemList.get(j))
											.intValue());

						} catch (DataAccessException e1) {
							try {
								con.rollback();
							} catch (SQLException e) {
							}
							e1.printStackTrace();
							throw new DataAccessException(
									"cant  batchAddCommonItem exception; ", e1);

						} catch (Exception e2) {
							try {
								con.rollback();
							} catch (SQLException e) {
							}
							e2.printStackTrace();
							throw new DataAccessException(
									"cant  batchAddCommonItem exception; ", e2);
						}
					}
				}
			} catch (DataAccessException e) {
				try {
					con.rollback();
				} catch (SQLException sqle) {
				}
				e.printStackTrace();
				throw new DataAccessException(
						"cant  batchAddCommonItem exception; ", e);
			}
		}
		return null;

	}

	/**
	 * 取得相应评价类型的共同项目
	 * 
	 * @param con
	 * @param evTypeId
	 * @return
	 * @throws DataAccessException
	 */
	private List getCommonItemDetail(Connection con, String evTypeId)
			throws DataAccessException {
		List lItemDetailSeq = new Vector();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM evs_common_item_detail ");
		sql.append(" WHERE ev_period_id=? ");
		sql.append(" AND ev_type_id=? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodIdOld);
			pstmt.setString(2, evTypeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lItemDetailSeq.add(new Integer(rs
						.getInt("seq_ev_common_item_detail")));
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant  getCommonItemDetail exception; ", ex);
		} catch (Exception e2) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			e2.printStackTrace();
			throw new DataAccessException(
					"cant  batchAddCommonItem exception; ", e2);
		} finally {
			SqlUtil.close(rs, pstmt);
		}
		return lItemDetailSeq;
	}

	/**
	 * 取得相应评价类型的共同项目
	 * 
	 * @param con
	 * @param evTypeId
	 * @return
	 * @throws DataAccessException
	 */
	private List getCommonItemDetail2(Connection con, String evTypeId)
			throws DataAccessException {
		List lItemDetailSeq = new Vector();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM evs_common_item_detail ");
		sql.append(" WHERE ev_period_id=? ");
		sql.append(" AND ev_type_id=? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodId);
			pstmt.setString(2, evTypeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lItemDetailSeq.add(new Integer(rs
						.getInt("seq_ev_common_item_detail")));
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant  getCommonItemDetail exception; ", ex);
		} catch (Exception e2) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			e2.printStackTrace();
			throw new DataAccessException(
					"cant  batchAddCommonItem exception; ", e2);
		} finally {
			SqlUtil.close(rs, pstmt);
		}
		return lItemDetailSeq;
	}

	/**
	 * 增加共同项目
	 * 
	 * @param con
	 * @param seqCommonItemDetailOld
	 * @return
	 * @throws DataAccessException
	 */
	private void addEvCommonItemDetail(Connection con,
			int seqCommonItemDetailOld) throws DataAccessException {
		int seq = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_common_item_detail  ");
		sql
				.append(" (seq_ev_common_item_detail, ev_period_id, ev_item_id, ev_detail_prop, ev_detail_order, ev_type_id) ");
		sql
				.append(" SELECT seq_ev_common_item_detail.nextval, ?, ev_item_id, ev_detail_prop, seq_ev_common_item_detail.nextval, ev_type_id");
		sql.append(" FROM evs_common_item_detail ");
		sql.append(" WHERE seq_ev_common_item_detail=?  ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, this.evPeriodId);
			pstmt.setInt(2, seqCommonItemDetailOld);
			pstmt.executeUpdate();
			pstmt = null;
			pstmt = con
					.prepareStatement("SELECT seq_ev_common_item_detail.currval as seq FROM dual");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				seq = rs.getInt("seq");
			}
			if (seq == -1) {
				con.rollback();
			} else {
				this.addEvCommonColumnDetail(con, seq, seqCommonItemDetailOld);
			}

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant  addEvCommonItemDetail exception; ", ex);
		} finally {
			SqlUtil.close(rs, pstmt);
		}
	}

	/**
	 * 添加相应共同项目的的列内容
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private void addEvCommonColumnDetail(Connection con,
			int seqCommonItemDetail, int seqCommonItemDetailOld)
			throws DataAccessException {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO evs_common_column_detail  ");
		sql
				.append(" (seq_ev_common_item_detail, ev_column_detail, ev_column_id) ");
		sql.append(" SELECT ?, ev_column_detail, ev_column_id");
		sql.append(" FROM evs_common_column_detail ");
		sql.append(" WHERE seq_ev_common_item_detail=?  ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, seqCommonItemDetail);
			pstmt.setInt(2, seqCommonItemDetailOld);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant  addEvCommonColumnDetail exception; ", ex);
		} finally {
			SqlUtil.close(null, pstmt);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
	    return new ToStringBuilder(this).append("evPeriodId", this.evPeriodId)
	    	.append("evPeriodName", this.evPeriodName).append("evTypeList",
	    		this.evTypeList).append("HEvTypeFirstProcess",
	    		this.getHEvTypeFirstProcess()).append("evPeriodIdOld",
	    		this.evPeriodIdOld).append("year", this.year).append(
	    		"message", this.message).append("monthCount",
	    		this.monthCount).toString();
	}

}