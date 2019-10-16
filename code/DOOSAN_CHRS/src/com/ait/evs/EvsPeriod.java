package com.ait.evs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class EvsPeriod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServiceLocator services;

	private static final Logger logger = Logger.getLogger(EvsPeriod.class) ;

	public EvsPeriod() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsPeriod(String ev_year, String ev_period_id,
			String ev_period_name, List levType) {
		this.evYear = ev_year;
		this.evPeriodName = ev_period_name;
		this.evPeriodID = ev_period_id;
		this.lEvTypes = levType;
		
		 
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public EvsPeriod(String ev_year, String ev_period_id,
		String ev_period_name, List levType, String empid) {
	this.evYear = ev_year;
	this.evPeriodName = ev_period_name;
	this.evPeriodID = ev_period_id;
	this.lEvTypes = levType;
	this.empid = empid;
	
	try {
		services = ServiceLocator.getInstance();
	} catch (ServiceLocatorException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	 

 
	public EvsPeriod(String ev_period_id) {
		this.evPeriodID = ev_period_id;

		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEvYear() {
		return evYear;
	}

	public void setEvYear(String evYear) {
		this.evYear = evYear;
	}

	public String getEvParentPeriodID() {
		return evParentPeriodID;
	}

	public void setEvParentPeriodID(String evParentPeriodID) {
		this.evParentPeriodID = evParentPeriodID;
	}

	public int getEvParentPeriodFlag() {
		return evParentPeriodFlag;
	}

	public void setEvParentPeriodFlag(int evParentPeriodFlag) {
		this.evParentPeriodFlag = evParentPeriodFlag;
	}

	public String getEvPeriodID() {
		return evPeriodID;
	}

	public void setEvPeriodID(String evPeriodID) {
		this.evPeriodID = evPeriodID;
	}

	public String getEvPeriodName() {
		return evPeriodName;
	}

	public void setEvPeriodName(String evPeriodName) {
		this.evPeriodName = evPeriodName;
	}

	public int getEvPublic() {
		return evPublic;
	}

	public void setEvPublic(int evPublic) {
		this.evPublic = evPublic;
	}

	/**
	 * 取得评价年列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvsYearList() throws DataAccessException,
			ServiceLocatorException {
		List lEvYear = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSYEAR_LIST = " SELECT DISTINCT ev_year FROM evs_period order by ev_year desc ";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSYEAR_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvYear.add(rs.getString("ev_year"));
			}
			return lEvYear;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	public List getEvsYearIDList() throws DataAccessException,
	ServiceLocatorException {
		List lEvYear = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSYEAR_LIST = " SELECT ev_period_id as id, ev_year as name FROM evs_period order by ev_year desc ";
		
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSYEAR_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvYear.add(rs.getString("id"));
				lEvYear.add(rs.getString("name"));
			}
			return lEvYear;
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
}
	public void delEvsTypePeriod() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			this.delEvsTypePeriod(con);
		} catch (SQLException sqle) {

			sqle.printStackTrace();
			throw new DataAccessException("删除评价期间类型失败", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant execute query for delEvsTypePeriod statistics", sle);
		} finally {
			if (con != null) {
				try {
					con.close();
					con = null;
				} catch (SQLException se) {
				}
			}
		}
	}

	/**
	 * 删除相关评价期间的评价类型
	 * 
	 * @param con
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private void delEvsTypePeriod(Connection con) throws DataAccessException,
			SQLException {

		PreparedStatement pstmt = null;
		final String DEL_EVS_TYPE_PERIOD = " DELETE evs_period_type  WHERE ev_period_id=? ";

		try {
			pstmt = con.prepareStatement(DEL_EVS_TYPE_PERIOD);
			pstmt.setString(1, this.evPeriodID);
			pstmt.execute();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException("删除评价期间失败", sqle);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (SQLException se) {
				}
			}
		}
	}

	/**
	 * 删除评价期间
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public void delEvPeriod() throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		final String DEL_EV_PERIOD = " DELETE evs_period  WHERE ev_period_id=?  ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			this.delEvsTypePeriod(con);// 先删除其相关的评价类型
			pstmt = con.prepareStatement(DEL_EV_PERIOD);
			pstmt.setString(1, this.evPeriodID);
			pstmt.executeUpdate();
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException("删除评价期间出错", sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant execute query for delEvPeriod statistics", sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	/*
	 * 更新评价期间
	 */
	public void updateEvsPeriodById() throws DataAccessException {
		AdminBean admin  = new AdminBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE evs_period set ev_period_name=? ,UPDATE_DATE = sysdate,UPDATED_BY = ? WHERE ev_period_id=? ";
		System.out.print("333"+admin.getAdminID());
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			pstmt.setString(++i, this.evPeriodName);
			pstmt.setString(++i, this.empid);
			pstmt.setString(++i, this.evPeriodID);
			pstmt.executeUpdate();

			con.commit();
			this.addEvsPeriodType();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvTypeByCode statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvTypeByCode statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	/**
	 * 添加评价期间与评价类型关系
	 * 
	 * @param con
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private void addEvsPeriodType() throws DataAccessException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SELECT_EV_TYPE_PERIOD = " SELECT * FROM  EVS_PERIOD_TYPE WHERE ev_period_id=? AND ev_type_id=? ";
		final String ADD_EV_TYPE_PERIOD = " INSERT INTO EVS_period_type(ev_period_id,ev_type_id) VALUES (?,?)";
		final String DEL_EV_TYPE_PERIOD = " DELETE EvS_period_type  WHERE ev_period_id=? AND ev_type_id=? ";
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			EvsPeriod evsPeriod = this.getEvsPeriodByID2();
			List lEvTypesOld = evsPeriod.getlEvTypes();
			int size = this.lEvTypes.size();

			// 找出相应删除的期间类型列表
			for (int i = 0; i < lEvTypesOld.size(); i++) {
				EvsType evsType = (EvsType) lEvTypesOld.get(i);
				if (this.lEvTypes.indexOf(evsType) != -1) {
					lEvTypesOld.remove(i);
				}
			}

			// 删除评价期间类型
			if (lEvTypesOld != null) {
				int lEvTypesOldSize = lEvTypesOld.size();
				for (int i = 0; i < lEvTypesOldSize; i++) {
					pstmt = null;
					EvsType evsType = (EvsType) lEvTypesOld.get(i);
					if (!this.getEvPeriodTypeIsSave(evsType.getEvTypeID())) {
						pstmt = con.prepareStatement(DEL_EV_TYPE_PERIOD);
						pstmt.setString(1, this.evPeriodID);
						pstmt.setString(2, evsType.getEvTypeID());
						pstmt.execute();
					}
				}
			}
			// 更新评价期间类型
			size = lEvTypes.size();
			for (int i = 0; i < size; i++) {
				pstmt = null;
				rs = null;
				EvsType evsType = (EvsType) lEvTypes.get(i);
				pstmt = con.prepareStatement(SELECT_EV_TYPE_PERIOD);
				pstmt.setString(1, this.evPeriodID);
				pstmt.setString(2, evsType.getEvTypeID());
				rs = pstmt.executeQuery();
				if (!rs.next()) {
					pstmt = null;
					pstmt = con.prepareStatement(ADD_EV_TYPE_PERIOD);
					pstmt.setString(1, this.evPeriodID);
					pstmt.setString(2, evsType.getEvTypeID());
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
					"cant add addEvsPeriodType exception; ", ex);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for addEvsPeriodType statistics", sle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}

	// 是否在外键存在
	public boolean getEvPeriodTypeIsSave(String evTypeId)
			throws DataAccessException {
		boolean isSave = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = services.getConnection();
			String sql = " SELECT * FROM  (SELECT DISTINCT ev_period_id,ev_type_id FROM EVS_TYPE_ITEM "
					+ " UNION  "
					+ " SELECT DISTINCT ev_period_id,ev_type_id FROM EVS_TYPE_PROCESS ) "
					+ " WHERE  ev_period_id=? AND ev_type_id=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, this.evPeriodID);
			pstmt.setString(2, evTypeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isSave = true;

			}

		} catch (SQLException ex) {

			ex.printStackTrace();
			throw new DataAccessException(
					"cant add getEvPeriodTypeIsSave exception; ", ex);
		} catch (ServiceLocatorException sle) {

			throw new DataAccessException(
					"cant get connection for getEvPeriodTypeIsSave statistics",
					sle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return isSave;
	}
        /**
         * 取得评价期间列表
         * @param evTypeId
         * @return
         * @throws DataAccessException
         */
	public List getEvPeriodByTypeAndYear(String evYear,String evType)
			throws DataAccessException {
		List<EvsPeriod> lEvsPeriod=new ArrayList<EvsPeriod>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = services.getConnection();
			StringBuffer sql=new StringBuffer();
                        sql.append(" SELECT *	");
                        sql.append("   FROM evs_period a ");
                        sql.append("   WHERE a.ev_year = ? ");
//                        sql.append("     AND EXISTS ( ");
//                        sql.append("             SELECT * ");
//                        sql.append("                FROM evs_period_type b ");
//                        sql.append("             WHERE ev_type_id =? ");
//                        sql.append("                    AND a.ev_period_id = b.ev_period_id) ");
                        sql.append(" order by ev_period_id ");
                        pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, evYear);
		//	pstmt.setString(2, evType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    lEvsPeriod.add(this.createEvsPeriod(rs));
			}

		} catch (SQLException ex) {

			ex.printStackTrace();
			throw new DataAccessException(
					"cant add getEvPeriodByTypeAndYear exception; ", ex);
		} catch (ServiceLocatorException sle) {

			throw new DataAccessException(
					"cant get connection for getEvPeriodByTypeAndYear statistics",
					sle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvsPeriod;
	}

	public static void getCurrentPeriod() {
	}

	private final static String GET_EVSPERIOD_MAXID = " SELECT  TRIM(TO_CHAR(MAX(SUBSTR(ev_period_id,5,7))+1,'00')) AS maxPeriod FROM EVS_PERIOD WHERE ev_year=? ";

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
			pstmt.setString(1, this.evYear);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("maxPeriod") != null) {
					this.setEvPeriodID(this.evYear + rs.getString("maxPeriod"));
				} else {
					this.setEvPeriodID(this.evYear + "01");
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
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt);
		}
	}

	/**
	 * 添加评价期间与评价类型关系
	 * 
	 * @param con
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private void addEvsPeriodType(Connection con) throws DataAccessException,
			SQLException {

		PreparedStatement pstmt = null;
		final String ADD_EV_TYPE_PERIOD = "INSERT INTO evs_period_type(ev_period_id,ev_type_id) VALUES (?,?)";
		try {
			int size = this.lEvTypes.size();
			for (int i = 0; i < size; i++) {
				pstmt = null;
				EvsType evsType = (EvsType) this.lEvTypes.get(i);
				pstmt = con.prepareStatement(ADD_EV_TYPE_PERIOD);
				pstmt.setString(1, this.evPeriodID);
				pstmt.setString(2, evsType.getEvTypeID());
				pstmt.executeUpdate();
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException(
					"cant add addEvsPeriodType exception; ", ex);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (SQLException se) {
				}
			}
		}
	}

	/**
	 * 
	 * 添加评价期间
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public void addPeriod() throws SQLException, DataAccessException {
		 AdminBean admin  = new AdminBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		final String ADD_EV_PERIOD = "INSERT INTO evs_period(ev_year,ev_period_id,ev_period_name,CREATE_DATE,CREATED_BY) VALUES(?,?,?,sysdate,?)";
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			this.getEvPeriodMaxId(con);// 设置评价期间ID
			int i=0;
			pstmt = con.prepareStatement(ADD_EV_PERIOD);
			pstmt.setString(++i, this.evYear);
			pstmt.setString(++i, this.evPeriodID);
			pstmt.setString(++i, this.evPeriodName);
			pstmt.setString(++i, this.empid);
			pstmt.executeUpdate();
			this.addEvsPeriodType(con);// 添加评价期间与评价类型关系
			con.commit();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addPeriod exception; ", ex);
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for adding addPeriod");
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	/**
	 * 取得相应的评价期间
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public EvsPeriod getEvsPeriodByID2() throws DataAccessException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT * FROM evs_period WHERE ev_period_id='"
				+ this.evPeriodID + "' ";
		String ORDER_SQL = " ORDER BY ev_year,ev_period_id";
		EvsPeriod evsPeriod = new EvsPeriod();
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				evsPeriod.setEvPeriodName(rs.getString("ev_period_name"));
				evsPeriod.setEvYear(rs.getString("ev_year"));
				evsPeriod.setlEvTypes(this.getEvTypeByEvPeriodId(this.evPeriodID));
				evsPeriod.setCreatetime(rs.getString("create_date"));
				evsPeriod.setEmpid(rs.getString("created_by"));
				evsPeriod.setUempid(rs.getString("updated_by"));
				evsPeriod.setUpdatetime(rs.getString("update_date"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsPeriodByID2 statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvsPeriodByID2 statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return evsPeriod;

	}

	/**
	 * 取得相应的评价期间
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public void getEvsPeriodByID() throws DataAccessException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT * FROM evs_period WHERE ev_period_id='"
				+ this.evPeriodID + "' ";
		String ORDER_SQL = " ORDER BY ev_year,ev_period_id";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				this.setEvPeriodName(rs.getString("ev_period_name"));
				this.setEvYear(rs.getString("ev_year"));
				this.setlEvTypes(this.getEvTypeByEvPeriodId(this.evPeriodID));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsPeriodByID statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvsPeriodByID statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}

	}

	/**
	 * 根据评价年取得相应年份的评价期间
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsPeriodByYear(String year) throws DataAccessException {
		List lEvsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT * FROM evs_period where activity ='1' ";
		String ORDER_SQL = " ORDER BY ev_period_id DESC ";
		if (!year.trim().equals("")) {
			SELECT_SQL = SELECT_SQL + " and  ev_year='" + year + "'";
		}
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvsPeriod.add(this.createEvsPeriod(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvsPeriod;
	}

	private final static String GET_EVSTYPE_BY_EVSPERIODID = "SELECT ev_type_id,ev_type_name FROM evs_period_type_v WHERE ev_period_id=? ";

	/**
	 * 根据评价期间取得此期间参与评价的类型
	 * 
	 * @param evsPeriodId
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvTypeByEvPeriodId(String evsPeriodId)
			throws DataAccessException {
		List lEvsType = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSTYPE_BY_EVSPERIODID);
			pstmt.setString(1, evsPeriodId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsType evsType = new EvsType(rs.getString("ev_type_id"), rs
						.getString("ev_type_name"));
				lEvsType.add(evsType);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvTypeByEvPeriodId statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvTypeByEvPeriodId statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvsType;
	}
	private final static String GET_EVSDEPT = "select t.ev_period_id, t.ev_dept_id, t.ev_dept_level, " +
			" t.ev_dept_name from evs_dept t START WITH t.ev_dept_level = (SELECT MIN(ev_dept_level) " +
			" FROM evs_dept) CONNECT BY PRIOR t.ev_dept_id = t.ev_parent_dept_id ";

	/**
	 * 取得相应的评价部门
	 * 
	 * @param evsPeriodId
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsDeptid()
			throws DataAccessException {
		List lEvsDept = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSDEPT); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsDept evsdept = new EvsDept(rs.getString("ev_dept_id"), rs
						.getString("ev_dept_name"));
				lEvsDept.add(evsdept);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvTypeByEvPeriodId statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvTypeByEvPeriodId statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvsDept;
	}
	
	
	
	public EvsPeriod createEvsPeriod(ResultSet rs) throws DataAccessException {
		EvsPeriod evsPeriod = new EvsPeriod();
		try {

			evsPeriod.setEvPeriodID((rs.getString("ev_period_id") != null) ? rs.getString("ev_period_id") : "");
			evsPeriod.setEvPeriodName((rs.getString("ev_period_name") != null) ? rs.getString("ev_period_name"): "");
			evsPeriod.setEvYear((rs.getString("ev_year") != null) ? rs.getString("ev_year") : "");
			evsPeriod.setlEvTypes(this.getEvTypeByEvPeriodId(rs.getString("ev_period_id")));
			evsPeriod.setEmpid(rs.getString("created_by"));
			evsPeriod.setUempid(rs.getString("updated_by"));
			evsPeriod.setCreatetime(rs.getString("create_date"));
			evsPeriod.setUpdatetime(rs.getString("update_date"));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return evsPeriod;
	}

	private final static String GET_CURRENT_EVPERIOD_Y = "SELECT ev_period_id FROM ( "
			+ " SELECT ev_period_id  FROM evs_period_v  "
			+ " WHERE  SYSDATE BETWEEN ev_period_sdate AND ev_period_edate "
			+ " ORDER BY ev_period_edate )  WHERE ROWNUM=1";

	private final static String GET_CURRENT_EVPERIOD = "SELECT ev_period_id FROM evs_period "
		+ " ORDER BY ev_period_ID DESC ";
	/**
	 * 取当前时间的下的评价期间
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public String getCurrentEvPeriod() throws DataAccessException {
		String evPeriodId = "";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_CURRENT_EVPERIOD);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				evPeriodId = rs.getString("ev_period_id");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvTypeByEvPeriodId statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvTypeByEvPeriodId statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return evPeriodId;
	}

	private final static String GET_CURRENT_EVTYPE = "SELECT ev_period_id FROM ( "
		+ " SELECT ev_period_id  FROM evs_period_v  "
		+ " WHERE  SYSDATE BETWEEN ev_period_sdate AND ev_period_edate "
		+ " ORDER BY ev_period_edate )  WHERE ROWNUM=1";
	
	/**
	 * 取当前时间的下的评价类型
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public String getCurrentEvtype() throws DataAccessException {
		String evPeriodId = "";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_CURRENT_EVPERIOD);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				evPeriodId = rs.getString("ev_period_id");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvTypeByEvPeriodId statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvTypeByEvPeriodId statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return evPeriodId;
	}
	
	
	
	private final static String GET_EVPERIOD_BY_EVYEAR = "SELECT ev_period_id FROM ( "
			+ " SELECT ev_period_id  FROM evs_period_v  "
			+ " WHERE ev_year =? "
			+ " ORDER BY ev_period_edate )  WHERE ROWNUM=1";

	/**
	 * 取得相应评价年的第一个评价期间
	 * 
	 * @param evYear
	 * @return
	 * @throws DataAccessException
	 */
	public String getFirstEvPeriodByEvYear(String evYear)
			throws DataAccessException {
		String evPeriodId = "";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVPERIOD_BY_EVYEAR);
			pstmt.setString(1, evYear);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				evPeriodId = rs.getString("ev_period_id");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvPeriodByEvYear statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvPeriodByEvYear statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return evPeriodId;
	}

	private final static String GET_EVPERIODS_BY_EVYEAR = " SELECT * FROM evs_period_v WHERE ev_year=? order by ev_period_edate ";

	/**
	 * 取得相应评价年的所有评价期间
	 * 
	 * @param evYear
	 * @return
	 * @throws DataAccessException
	 */
	public static Hashtable getEvPeriodByEvYear(String evYear)
			throws DataAccessException {
		Hashtable hEvPeriod = new Hashtable();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(GET_EVPERIODS_BY_EVYEAR);
			pstmt.setString(1, evYear);
			rs = pstmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				if (rs.getString("ev_period_id") != null) {
					hEvPeriod.put("Period" + i, rs.getString("ev_period_id"));
				}
				i++;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvPeriodByEvYear statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvPeriodByEvYear statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return hEvPeriod;
	}

	
	public int getEvsPeriodGrant(String empid) throws DataAccessException {
		int grant =0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT COUNT(*) COU FROM SY_ADMIN T WHERE T.ADMINID = '" + empid + "' AND Instr(',' || t.screen_grant_no || ',' , ',1,') > 0 " ;
		logger.debug(SELECT_SQL) ;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				grant=Integer.parseInt(rs.getString("cou"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return grant;
	}
	
	private String evYear;

	private String evParentPeriodID;

	private int evParentPeriodFlag;

	private String evPeriodID;

	private String evPeriodName;
	private String empid;
	private String createtime;
	private String uempid;
	private String updatetime;
	
	private int evPublic;

	private List lEvTypes;

 

	 
	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUempid() {
		return uempid;
	}

	public void setUempid(String uempid) {
		this.uempid = uempid;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public List getlEvTypes() {
		return this.lEvTypes;
	}

	public void setlEvTypes(List evTypes) {
		this.lEvTypes = evTypes;
	}

}