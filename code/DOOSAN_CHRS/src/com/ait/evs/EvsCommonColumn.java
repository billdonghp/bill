package com.ait.evs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class EvsCommonColumn {

	private static ServiceLocator services;

	private String evColumnId;

	private String evColumnName;

	private String evColumnDetail;

	private int evColumnOrder;

	private int seqEvCommonItemDetail;

	/**
	 * @return 返回 evColumnId。
	 */
	public String getEvColumnId() {
		return evColumnId;
	}

	/**
	 * @param evColumnId
	 *            要设置的 evColumnId。
	 */
	public void setEvColumnId(String evColumnId) {
		this.evColumnId = evColumnId;
	}

	/**
	 * @return 返回 evColumnName。
	 */
	public String getEvColumnName() {
		return evColumnName;
	}

	/**
	 * @param evColumnName
	 *            要设置的 evColumnName。
	 */
	public void setEvColumnName(String evColumnName) {
		this.evColumnName = evColumnName;
	}

	/**
	 * @return 返回 evColumnOrder。
	 */
	public int getEvColumnOrder() {
		return evColumnOrder;
	}

	/**
	 * @param evColumnOrder
	 *            要设置的 evColumnOrder。
	 */
	public void setEvColumnOrder(int evColumnOrder) {
		this.evColumnOrder = evColumnOrder;
	}

	/**
	 * @return 返回 seqEvCommonItemDetail。
	 */
	public int getSeqEvCommonItemDetail() {
		return seqEvCommonItemDetail;
	}

	/**
	 * @param seqEvCommonItemDetail
	 *            要设置的 seqEvCommonItemDetail。
	 */
	public void setSeqEvCommonItemDetail(int seqEvCommonItemDetail) {
		this.seqEvCommonItemDetail = seqEvCommonItemDetail;
	}

	/**
	 * @return 返回 evColumnDetail。
	 */
	public String getEvColumnDetail() {
		return evColumnDetail;
	}

	/**
	 * @param evColumnDetail
	 *            要设置的 evColumnDetail。
	 */
	public void setEvColumnDetail(String evColumnDetail) {
		this.evColumnDetail = evColumnDetail;
	}

	public EvsCommonColumn() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add update
	public EvsCommonColumn(String evColumnId, String evColumnDetail) {
		this.evColumnDetail = evColumnDetail;
		this.evColumnId = evColumnId;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// del
	public EvsCommonColumn(int seqEvCommonItemDetail) {
		this.seqEvCommonItemDetail = seqEvCommonItemDetail;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 增加共同项目列
	private final static String ADD_EV_COMMON_COLUMN = "INSERT INTO evs_common_column_detail(seq_ev_common_item_detail,ev_column_detail,ev_column_id)"
			+ " VALUES(?,?,?)";

	public void addEvCommonColumn() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			addEvCommonColumn(con);
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for addEvCommonColumn statistics", sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for addEvCommonColumn statistics", sle);
		} finally {
			SqlUtil.close(con);
		}
	}

	public void addEvCommonColumn(Connection con) throws DataAccessException {

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(ADD_EV_COMMON_COLUMN);
			pstmt.setInt(1, this.seqEvCommonItemDetail);
			pstmt.setString(2, this.evColumnDetail);
			pstmt.setString(3, this.evColumnId);
			pstmt.execute();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for addEvCommonColumn statistics", sqle);
		} finally {
			SqlUtil.close(pstmt, null);
		}

	}

	// 修改共同项目列
	private final static String UPDATE_EV_COMMON_COLUMN = "UPDATE evs_common_column_detail"
			+ " SET ev_column_detail=? WHERE seq_ev_common_item_detail=? AND ev_column_id=? ";

	public void updateEvCommonColumn() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			updateEvCommonColumn(con);
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvCommonColumn statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvCommonColumn statistics",
					sle);
		} finally {
			SqlUtil.close(con);
		}
	}

	public void updateEvCommonColumn(Connection con) throws DataAccessException {

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(UPDATE_EV_COMMON_COLUMN);
			pstmt.setString(1, this.evColumnDetail);
			pstmt.setInt(2, this.seqEvCommonItemDetail);
			pstmt.setString(3, this.evColumnId);
			pstmt.execute();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvCommonColumn statistics",
					sqle);
		} finally {
			SqlUtil.close(pstmt, null);
		}

	}

	// 删除共同项目列
	private final static String DEL_EV_COMMON_COLUMN = "DELETE evs_common_column_detail"
			+ " WHERE seq_ev_common_item_detail=? ";

	public void delEvCommonColumn() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			delEvCommonColumn(con);
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delEvCommonColumn statistics", sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for delEvCommonColumn statistics", sle);
		} finally {
			SqlUtil.close(con);
		}
	}

	public void delEvCommonColumn(Connection con) throws DataAccessException {

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DEL_EV_COMMON_COLUMN);
			pstmt.setInt(1, this.seqEvCommonItemDetail);
			pstmt.execute();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delEvCommonColumn statistics", sqle);
		} finally {
			SqlUtil.close(pstmt, null);
		}

	}
}