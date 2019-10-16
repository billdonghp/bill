/*
 * 创建日期 2005-7-5
 * 
 * Company: AIT
 * 
 * @author QING
 * @version 1.0
 */
package com.ait.evs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

/**
 * @author AIT Administrator
 */
public class EvsRelation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServiceLocator services;

	private EvsProcess evsProcess;

	private EvsType evsType;

	private EvsItem evsItem;

	private EvsPeriod evsPeriod;

	private EvsOperate evsOperate;

	private float evProcessProp;

	private float evItemProp;

	private String evMarksValues;

	private String evMarksName;

	private String isSave = "0";

	private float evMarkDefault;

	public EvsRelation() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public EvsRelation(EvsPeriod evsPeriod, EvsType evsType, EvsItem evsItem,
			EvsProcess evsProcess, EvsOperate evsOperate, float evProcessProp,
			float evItemProp, String evMarksValues, String evMarksName,
			float evMarkDefault) {
		this.evsType = evsType;
		this.evsItem = evsItem;
		this.evsOperate = evsOperate;
		this.evsPeriod = evsPeriod;
		this.evsProcess = evsProcess;
		this.evItemProp = evItemProp;
		this.evMarkDefault = evMarkDefault;
		this.evMarksName = evMarksName;
		this.evMarksValues = evMarksValues;
		this.evProcessProp = evProcessProp;

		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public EvsRelation(EvsPeriod evsPeriod, EvsType evsType, EvsItem evsItem,
			EvsProcess evsProcess) {
		this.evsType = evsType;
		this.evsItem = evsItem;
		this.evsPeriod = evsPeriod;
		this.evsProcess = evsProcess;

		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return 返回 evItemProp。
	 */
	public float getEvItemProp() {
		return evItemProp;
	}

	/**
	 * @param evItemProp
	 *            要设置的 evItemProp。
	 */
	public void setEvItemProp(float evItemProp) {
		this.evItemProp = evItemProp;
	}

	/**
	 * @return 返回 evMarkDefault。
	 */
	public float getEvMarkDefault() {
		return evMarkDefault;
	}

	/**
	 * @param evMarkDefault
	 *            要设置的 evMarkDefault。
	 */
	public void setEvMarkDefault(float evMarkDefault) {
		this.evMarkDefault = evMarkDefault;
	}

	/**
	 * @return 返回 evMarksName。
	 */
	public String getEvMarksName() {
		return evMarksName;
	}

	/**
	 * @param evMarksName
	 *            要设置的 evMarksName。
	 */
	public void setEvMarksName(String evMarksName) {
		this.evMarksName = evMarksName;
	}

	/**
	 * @return 返回 evMarksValues。
	 */
	public String getEvMarksValues() {
		return evMarksValues;
	}

	/**
	 * @param evMarksValues
	 *            要设置的 evMarksValues。
	 */
	public void setEvMarksValues(String evMarksValues) {
		this.evMarksValues = evMarksValues;
	}

	/**
	 * @return 返回 evProcessProp。
	 */
	public float getEvProcessProp() {
		return evProcessProp;
	}

	/**
	 * @param evProcessProp
	 *            要设置的 evProcessProp。
	 */
	public void setEvProcessProp(float evProcessProp) {
		this.evProcessProp = evProcessProp;
	}

	/**
	 * @return 返回 evsItem。
	 */
	public EvsItem getEvsItem() {
		return evsItem;
	}

	/**
	 * @param evsItem
	 *            要设置的 evsItem。
	 */
	public void setEvsItem(EvsItem evsItem) {
		this.evsItem = evsItem;
	}

	/**
	 * @return 返回 evsOperate。
	 */
	public EvsOperate getEvsOperate() {
		return evsOperate;
	}

	/**
	 * @param evsOperate
	 *            要设置的 evsOperate。
	 */
	public void setEvsOperate(EvsOperate evsOperate) {
		this.evsOperate = evsOperate;
	}

	/**
	 * @return 返回 evsPeriod。
	 */
	public EvsPeriod getEvsPeriod() {
		return evsPeriod;
	}

	/**
	 * @param evsPeriod
	 *            要设置的 evsPeriod。
	 */
	public void setEvsPeriod(EvsPeriod evsPeriod) {
		this.evsPeriod = evsPeriod;
	}

	/**
	 * @return 返回 evsProcess。
	 */
	public EvsProcess getEvsProcess() {
		return evsProcess;
	}

	/**
	 * @param evsProcess
	 *            要设置的 evsProcess。
	 */
	public void setEvsProcess(EvsProcess evsProcess) {
		this.evsProcess = evsProcess;
	}

	/**
	 * @return 返回 evsType。
	 */
	public EvsType getEvsType() {
		return evsType;
	}

	/**
	 * @param evsType
	 *            要设置的 evsType。
	 */
	public void setEvsType(EvsType evsType) {
		this.evsType = evsType;
	}

	private final static String ADD_EVRELATION = " INSERT INTO "
			+ "evs_relation(ev_period_id,ev_type_id,ev_item_id,ev_process_id,ev_operate_id,"
			+ " ev_marks_name,ev_marks_value,ev_marks_default,ev_item_prop,ev_process_prop)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?)";

	/**
	 * 批量添加项目流程关系信息
	 * 
	 * @param lEvsRelation
	 * @param evPeriodId
	 * @param evTypeId
	 * @throws DataAccessException
	 */
	public void addEvRelation(List lEvsRelation, String evPeriodId,
			String evTypeId) throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			if (lEvsRelation != null) {
				int evsRelationSize = lEvsRelation.size();
				for (int i = 0; i < evsRelationSize; i++) {
					pstmt = null;
					pstmt = con.prepareStatement(ADD_EVRELATION);
					EvsRelation evsRelation = (EvsRelation) lEvsRelation.get(i);

					this.delRelationByPeriodType(con, evPeriodId, evTypeId,
							evsRelation.evsItem.getEvItemID(),
							evsRelation.evsProcess.getEvProcessID());// 先删除关系表信息
					pstmt.setString(1, evsRelation.evsPeriod.getEvPeriodID());
					pstmt.setString(2, evsRelation.evsType.getEvTypeID());
					pstmt.setString(3, evsRelation.evsItem.getEvItemID());
					pstmt.setString(4, evsRelation.evsProcess.getEvProcessID());
					pstmt.setString(5, evsRelation.evsOperate.getEvOperateId());
					pstmt.setString(6, evsRelation.evMarksName);
					pstmt.setString(7, evsRelation.evMarksValues);
					pstmt.setFloat(8, evsRelation.evMarkDefault);
					pstmt.setFloat(9, evsRelation.evItemProp);
					pstmt.setFloat(10, evsRelation.evProcessProp);
					pstmt.executeUpdate();
				}
				con.commit();
			} else {
				con.rollback();
			}

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addEvRelation exception; ",
					ex);
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for adding addEvRelation");
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	private final static String DEL_RELATION_BY_PERIODTYPE = " DELETE evs_relation WHERE ev_period_id=? AND ev_type_id=? AND ev_item_id=? AND ev_process_id=?";

	/**
	 * 删除相应评价期间相应评价类型的项目流程信息
	 * 
	 * @throws DataAccessException
	 */
	public void delRelationByPeriodType(Connection con, String ev_period_id,
			String ev_type_id, String ev_item_id, String ev_process_id)
			throws DataAccessException {

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_RELATION_BY_PERIODTYPE);
			pstmt.setString(1, ev_period_id);
			pstmt.setString(2, ev_type_id);
			pstmt.setString(3, ev_item_id);
			pstmt.setString(4, ev_process_id);
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delRelationByPeriodType statistics",
					sqle);
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
	 * 删除相应评价期间相应评价类型的项目流程信息
	 * 
	 * @throws DataAccessException
	 */
	public void delRelationByPeriodTypeItemProcess() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			this.delRelationByPeriodType(con, this.evsPeriod.getEvPeriodID(),
					this.evsType.getEvTypeID(), this.evsItem.getEvItemID(),
					this.evsProcess.getEvProcessID());
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delRelationByPeriodTypeItemProcess statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for delRelationByPeriodTypeItemProcess statistics",
					sle);
		} finally {
			SqlUtil.close(con);
		}
	}

	/**
	 * 删除相应评价期间相应评价类型的项目流程信息
	 * 
	 * @throws DataAccessException
	 */
	public void delRelationByPeriodType() throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();

			pstmt = con.prepareStatement(DEL_RELATION_BY_PERIODTYPE);
			pstmt.setString(1, this.evsPeriod.getEvPeriodID());
			pstmt.setString(2, this.evsType.getEvTypeID());
			pstmt.executeUpdate();

		} catch (SQLException sqle) {

			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delRelationByPeriodType statistics",
					sqle);
		} catch (ServiceLocatorException sle) {

			throw new DataAccessException(
					"cant get connection for delRelationByPeriodType statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	private final static String GET_EVRELATION_BY_PERIOD_TYPE_ITEM_PROCESS = " SELECT * FROM  evs_relation WHERE ev_type_id=? AND ev_period_id=? "
			+ " AND ev_item_id=? AND ev_process_id=? ";

	/**
	 * 取得相应评价期间评价类型评价流程评价项目的项目流程关系信息
	 * 
	 * @throws DataAccessException
	 * 
	 */
	public void getEvRelationByPeriodTypeItemProcess()
			throws DataAccessException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con
					.prepareStatement(GET_EVRELATION_BY_PERIOD_TYPE_ITEM_PROCESS);
			pstmt.setString(1, this.evsType.getEvTypeID());
			pstmt.setString(2, this.evsPeriod.getEvPeriodID());
			pstmt.setString(3, this.evsItem.getEvItemID());
			pstmt.setString(4, this.evsProcess.getEvProcessID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				this.isSave = "1";
				this.setEvItemProp(rs.getFloat("ev_item_prop"));
				this.setEvMarkDefault(rs.getFloat("ev_marks_default"));
				this.setEvMarksName(rs.getString("ev_marks_name"));
				this.setEvMarksValues(rs.getString("ev_marks_value"));
				this.setEvProcessProp(rs.getFloat("ev_process_prop"));
				EvsOperate evsOperate = new EvsOperate(rs
						.getString("ev_operate_id") != null ? rs
						.getString("ev_operate_id") : "", "");
				this.setEvsOperate(evsOperate);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvRelationByPeriodTypeItemProcess statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvRelationByPeriodTypeItemProcess statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}

	/**
	 * @return 返回 isSave。
	 */
	public String getIsSave() {
		return isSave;
	}

	/**
	 * @param isSave
	 *            要设置的 isSave。
	 */
	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}
}