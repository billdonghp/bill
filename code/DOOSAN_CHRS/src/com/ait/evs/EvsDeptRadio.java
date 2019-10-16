/*
 * 创建日期 2005-7-13
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
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

/**
 * @author AIT Administrator
 */
public class EvsDeptRadio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String evPeriodId;

	private String evDeptId;

	private String evDeptName;

	private String evDeptGradeId;

	private String evDeptGradeName;

	private String evEmpGradeId;

	private String evEmpGradeName;

	private float evGradeProp;

	private float evWageRadio;

	private static ServiceLocator services;

	public EvsDeptRadio() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsDeptRadio(String evPeriodId, String evDeptId,
			String evDeptGradeId, String evEmpGradeId) {
		this.evPeriodId = evPeriodId;
		this.evDeptId = evDeptId;
		this.evDeptGradeId = evDeptGradeId;
		this.evEmpGradeId = evEmpGradeId;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsDeptRadio(String evPeriodId, String evDeptId,
			String evDeptGradeId, String evEmpGradeId, float evGradeProp) {
		this.evPeriodId = evPeriodId;
		this.evDeptId = evDeptId;
		this.evDeptGradeId = evDeptGradeId;
		this.evEmpGradeId = evEmpGradeId;
		this.evGradeProp = evGradeProp;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return 返回 evDeptGradeId。
	 */
	public String getEvDeptGradeId() {
		return evDeptGradeId;
	}

	/**
	 * @param evDeptGradeId
	 *            要设置的 evDeptGradeId。
	 */
	public void setEvDeptGradeId(String evDeptGradeId) {
		this.evDeptGradeId = evDeptGradeId;
	}

	/**
	 * @return 返回 evDeptGradeName。
	 */
	public String getEvDeptGradeName() {
		return evDeptGradeName;
	}

	/**
	 * @param evDeptGradeName
	 *            要设置的 evDeptGradeName。
	 */
	public void setEvDeptGradeName(String evDeptGradeName) {
		this.evDeptGradeName = evDeptGradeName;
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
	 * @return 返回 evEmpGradeId。
	 */
	public String getEvEmpGradeId() {
		return evEmpGradeId;
	}

	/**
	 * @param evEmpGradeId
	 *            要设置的 evEmpGradeId。
	 */
	public void setEvEmpGradeId(String evEmpGradeId) {
		this.evEmpGradeId = evEmpGradeId;
	}

	/**
	 * @return 返回 evEmpGradeName。
	 */
	public String getEvEmpGradeName() {
		return evEmpGradeName;
	}

	/**
	 * @param evEmpGradeName
	 *            要设置的 evEmpGradeName。
	 */
	public void setEvEmpGradeName(String evEmpGradeName) {
		this.evEmpGradeName = evEmpGradeName;
	}

	/**
	 * @return 返回 evGradeProp。
	 */
	public float getEvGradeProp() {
		return evGradeProp;
	}

	/**
	 * @param evGradeProp
	 *            要设置的 evGradeProp。
	 */
	public void setEvGradeProp(float evGradeProp) {
		this.evGradeProp = evGradeProp;
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
	 * @return 返回 evWageRadio。
	 */
	public float getEvWageRadio() {
		return evWageRadio;
	}

	/**
	 * @param evWageRadio
	 *            要设置的 evWageRadio。
	 */
	public void setEvWageRadio(float evWageRadio) {
		this.evWageRadio = evWageRadio;
	}

	private final static String BATCH_ADDEVDEPTRADIO_BYEVDEPT = "INSERT INTO evs_dept_radio ("
			+ " ev_period_id,ev_dept_id,ev_dept_grade_id,ev_grade_id,ev_grade_prop,ev_wage_radio) VALUES"
			+ " (?,?,?,?,?,?)";

	private final static String DEL_EVDEPTRADIO_BYPREIOD = " DELETE evs_dept_radio WHERE ev_period_id=? ";

	/**
	 * 从评价部门取得相应部门列表添加部门百分数
	 * 
	 * @param lEvDeptGrade
	 * @param evPeriodId
	 * @throws DataAccessException
	 */
	public void batchAddEvDeptRadioByEvDepts(List lEvDeptGrade,
			String evPeriodId) throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		List lEvsDept = null;

		try {
			EvsDept evsDept = new EvsDept();
			lEvsDept = evsDept.getDeptByPeriodId(evPeriodId);

			if (lEvsDept == null) {
				return;
			}

			int lEvsDeptSize = lEvsDept.size();
			con = services.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DEL_EVDEPTRADIO_BYPREIOD);
			pstmt.setString(1, evPeriodId);
			pstmt.execute();
			pstmt = null;
			for (int i = 0; i < lEvsDeptSize; i++) {// 部门列表
				pstmt = null;
				pstmt = con.prepareStatement(BATCH_ADDEVDEPTRADIO_BYEVDEPT);
				evsDept = (EvsDept) lEvsDept.get(i);
				pstmt.setString(1, evsDept.getEvPeriodID());
				pstmt.setString(2, evsDept.getEvDeptID());

				if (lEvDeptGrade == null) {
					con.rollback();
					return;
				}
				int lEvDeptGradeSize = lEvDeptGrade.size();
				for (int j = 0; j < lEvDeptGradeSize; j++) {// 部门等级列表
					EvsDeptGrade evsDeptGrade = (EvsDeptGrade) lEvDeptGrade
							.get(j);
					pstmt.setString(3, evsDeptGrade.getEvDeptGradeId());
					List lEvsEmpGrade = null;
					lEvsEmpGrade = evsDeptGrade.getLEvsEmpGrade();

					if (lEvsEmpGrade == null) {
						con.rollback();

						return;
					}
					int lEvsEmpGradeSize = lEvsEmpGrade.size();
					for (int k = 0; k < lEvsEmpGradeSize; k++) {// 员工等级列表
						EvsEmpGrade evsEmpGrade = (EvsEmpGrade) lEvsEmpGrade
								.get(k);
						pstmt.setString(4, evsEmpGrade.getEvEmpGradeId());
						pstmt.setFloat(5, evsEmpGrade.getEvGradeProp());
						pstmt.setFloat(6, evsEmpGrade.getEvWageRadio());
						pstmt.execute();
					}
				}
			}
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for batchAddEvDeptRadioByEvDepts statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for batchAddEvDeptRadioByEvDepts statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	/**
	 * 更新一个部门的百分数
	 * 
	 * @param evDeptId
	 * @param evPeriodId
	 * @param lEvDeptGrade
	 * @throws DataAccessException
	 */
	public void updateEvsDeptRadioByEvDept(String evDeptId, String evPeriodId,
			List lEvDeptGrade) throws DataAccessException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			if (lEvDeptGrade == null) {
				return;
			}
			int lEvDeptGradeSize = lEvDeptGrade.size();
			con = services.getConnection();
			con.setAutoCommit(false);
			this.delEvsDeptRadioByEvDept(evDeptId, evPeriodId, con);
			for (int j = 0; j < lEvDeptGradeSize; j++) {// 部门等级列表
				pstmt = null;
				pstmt = con.prepareStatement(BATCH_ADDEVDEPTRADIO_BYEVDEPT);

				pstmt.setString(1, evPeriodId);
				pstmt.setString(2, evDeptId);
				EvsDeptGrade evsDeptGrade = (EvsDeptGrade) lEvDeptGrade.get(j);
				pstmt.setString(3, evsDeptGrade.getEvDeptGradeId());
				List lEvsEmpGrade = null;
				lEvsEmpGrade = evsDeptGrade.getLEvsEmpGrade();

				if (lEvsEmpGrade == null) {
					con.rollback();
					return;
				}
				int lEvsEmpGradeSize = lEvsEmpGrade.size();
				for (int k = 0; k < lEvsEmpGradeSize; k++) {// 员工等级列表
					EvsEmpGrade evsEmpGrade = (EvsEmpGrade) lEvsEmpGrade.get(k);
					pstmt.setString(4, evsEmpGrade.getEvEmpGradeId());
					pstmt.setFloat(5, evsEmpGrade.getEvGradeProp());
					pstmt.setFloat(6, evsEmpGrade.getEvWageRadio());
					pstmt.execute();
				}
			}
			con.commit();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvsDeptRadioByEvDept statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvsDeptRadioByEvDept statistics",
					sle);
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			SqlUtil.close(pstmt, con);
		}

	}

	private final static String DEL_EVSDEPTRADIO_BY_EVDEPT = " DELETE evs_dept_radio WHERE "
			+ " ev_dept_id=? AND ev_period_id=? ";

	/**
	 * 删除评价部门百分数
	 * 
	 * @param evDeptId
	 * @param evPeriodId
	 * @param con
	 * @throws DataAccessException
	 */
	public void delEvsDeptRadioByEvDept(String evDeptId, String evPeriodId,
			Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(DEL_EVSDEPTRADIO_BY_EVDEPT);
			pstmt.setString(1, evDeptId);
			pstmt.setString(2, evPeriodId);
			pstmt.execute();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delEvsDeptRadioByEvDept statistics",
					sqle);
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
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
	 * 删除评价部门百分数
	 * 
	 * @param evDeptId
	 * @param evPeriodId
	 * @param con
	 * @throws DataAccessException
	 */
	public void delEvsDeptRadioByEvDept(String evDeptId, String evPeriodId)
			throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(DEL_EVSDEPTRADIO_BY_EVDEPT);
			pstmt.setString(1, evDeptId);
			pstmt.setString(2, evPeriodId);
			pstmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for delEvsDeptRadioByEvDept statistics",
					sqle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(pstmt, con);
		}

	}

	/**
	 * 取得部门百分数列表
	 * 
	 * @param evDeptId
	 * @param evPeriodId
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsDeptRadio(String evDeptId, String evPeriodId)
			throws DataAccessException {
		String dept_sql = "";
		String period_sql = "";

		if (!evDeptId.trim().equals("")) {
			dept_sql = " AND ev_dept_id='" + evDeptId + "'";
		}
		if (!evPeriodId.trim().equals("")) {
			period_sql = " AND ev_period_id='" + evPeriodId + "'";
		}
		String select_sql = "SELECT * FROM evs_dept_radio_v WHERE ev_dept_id is not null "
				+ dept_sql + period_sql;
		String order_sql = " ORDER BY ev_period_id,ev_dept_id,ev_dept_grade_id,ev_grade_id";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List lEvDeptRadio = new Vector();
		try {
			con = services.getConnection();

			pstmt = con.prepareStatement(select_sql + order_sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsDeptRadio evsDeptRadio = new EvsDeptRadio();
				evsDeptRadio.setEvDeptGradeId(rs.getString("ev_dept_grade_id"));
				evsDeptRadio.setEvDeptGradeName(rs
						.getString("ev_dept_grade_name"));
				evsDeptRadio.setEvDeptId(rs.getString("ev_dept_id"));
				evsDeptRadio.setEvDeptName(rs.getString("ev_dept_name"));
				evsDeptRadio.setEvEmpGradeId(rs.getString("ev_grade_id"));
				evsDeptRadio.setEvEmpGradeName(rs.getString("ev_grade_name"));
				evsDeptRadio.setEvGradeProp(rs.getFloat("ev_grade_prop"));
				evsDeptRadio.setEvPeriodId(rs.getString("ev_period_id"));
				evsDeptRadio.setEvWageRadio(rs.getFloat("ev_wage_radio"));
				lEvDeptRadio.add(evsDeptRadio);
			}
		} catch (SQLException sqle) {

			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsDeptRadio statistics", sqle);
		} catch (ServiceLocatorException sle) {

			throw new DataAccessException(
					"cant get connection for getEvsDeptRadio statistics", sle);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvDeptRadio;

	}

	/**
	 * 根据部门ID取得部门百分数列表
	 * 
	 * @param evDeptId
	 * @param evPeriodId
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsDeptRadioByDeptGrade(String evDeptId, String evPeriodId,
			String evDeptGradeId) throws DataAccessException {

		if (evDeptId == null || evPeriodId == null || evDeptGradeId == null) {
			return null;
		}
		String dept_sql = "";
		String period_sql = "";
		String dept_grade_sql = "";
		if (!evDeptId.trim().equals("")) {
			dept_sql = " AND ev_dept_id='" + evDeptId + "'";
		}
		if (!evPeriodId.trim().equals("")) {
			period_sql = " AND ev_period_id='" + evPeriodId + "'";
		}
		if (!evDeptGradeId.trim().equals("")) {
			dept_grade_sql = " AND ev_dept_grade_id='" + evDeptGradeId + "'";
		}

		String select_sql = "SELECT * FROM evs_dept_radio_v WHERE ev_dept_id is not null "
				+ dept_sql + period_sql + dept_grade_sql;
		String order_sql = " ORDER BY ev_period_id,ev_dept_id,ev_dept_grade_id,ev_grade_id";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List lEvDeptRadio = new Vector();
		try {
			con = services.getConnection();

			pstmt = con.prepareStatement(select_sql + order_sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvsDeptRadio evsDeptRadio = new EvsDeptRadio();
				evsDeptRadio.setEvDeptGradeId(rs.getString("ev_dept_grade_id"));
				evsDeptRadio.setEvDeptGradeName(rs
						.getString("ev_dept_grade_name"));
				evsDeptRadio.setEvDeptId(rs.getString("ev_dept_id"));
				evsDeptRadio.setEvDeptName(rs.getString("ev_dept_name"));
				evsDeptRadio.setEvEmpGradeId(rs.getString("ev_grade_id"));
				evsDeptRadio.setEvEmpGradeName(rs.getString("ev_grade_name"));
				evsDeptRadio.setEvGradeProp(rs.getFloat("ev_grade_prop"));
				evsDeptRadio.setEvPeriodId(rs.getString("ev_period_id"));
				evsDeptRadio.setEvWageRadio(rs.getFloat("ev_wage_radio"));
				lEvDeptRadio.add(evsDeptRadio);
			}
		} catch (SQLException sqle) {

			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsDeptRadioByDeptGrade statistics",
					sqle);
		} catch (ServiceLocatorException sle) {

			throw new DataAccessException(
					"cant get connection for getEvsDeptRadioByDeptGrade statistics",
					sle);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvDeptRadio;

	}

	private final static String GET_EVEMP_DEPTLIST = "SELECT DISTINCT  "
			+ " deptid,deptname,dept_level  FROM  HR_DEPARTMENT "
			+ " START WITH deptid IN (SELECT DISTINCT ev_dept_id  FROM EVS_DEPT_RADIO "
			+ " ) CONNECT  BY  PRIOR deptid=parent_dept_id ";

	/**
	 * 评价的部门列表
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public static List getDeptRadioDeptList() throws DataAccessException {
		List lEvDetps = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(GET_EVEMP_DEPTLIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Hashtable h_dept = new Hashtable();
				h_dept.put("deptId", rs.getString("deptid"));
				h_dept.put("deptName", rs.getString("deptname"));
				h_dept.put("deptLevel", rs.getString("dept_level"));
				lEvDetps.add(h_dept);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getDeptRadioDeptList statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getDeptRadioDeptList statistics",
					sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvDetps;
	}

	/**
	 * 取得部门百分数
	 * 
	 * @param evDeptId
	 * @param evPeriodId
	 * @return
	 * @throws DataAccessException
	 */
	public void getEvsDeptRadio() throws DataAccessException {

		String select_sql = "SELECT * FROM evs_dept_radio_v WHERE ev_period_id=? "
				+ " AND ev_dept_id=? AND ev_dept_grade_id=? AND ev_grade_id=? "
				+ " ORDER BY ev_period_id,ev_dept_id,ev_dept_grade_id,ev_grade_id";

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(select_sql);
			pstmt.setString(1, this.evPeriodId);
			pstmt.setString(2, this.evDeptId);
			pstmt.setString(3, this.evDeptGradeId);
			pstmt.setString(4, this.evEmpGradeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				this.setEvDeptGradeId(rs.getString("ev_dept_grade_id"));
				this.setEvDeptGradeName(rs.getString("ev_dept_grade_name"));
				this.setEvDeptId(rs.getString("ev_dept_id"));
				this.setEvDeptName(rs.getString("ev_dept_name"));
				this.setEvEmpGradeId(rs.getString("ev_grade_id"));
				this.setEvEmpGradeName(rs.getString("ev_grade_name"));
				this.setEvGradeProp(rs.getFloat("ev_grade_prop"));
				this.setEvPeriodId(rs.getString("ev_period_id"));
				this.setEvWageRadio(rs.getFloat("ev_wage_radio"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsDeptRadio statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvsDeptRadio statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}

	}

}