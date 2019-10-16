package com.ait.sy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.sy.bean.Affirm;
import com.ait.util.DataAccessException;
import com.ait.util.DebugUtil;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.PageControl;

public class AffirmDAO {
	private static ServiceLocator services;

	public AffirmDAO() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}

	public void updateAffirmRelation(Affirm affirm) throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call sy_update_affirm(?,?,?,?,?) }";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, affirm.getAffirmLevel());
			cs.setString(2, affirm.getAffirmObject());
			cs.setString(3, affirm.getAffirmorID());
			cs.setString(4, affirm.getAffirmTypeID());
			cs.setString(5, affirm.getCreatedBy());
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for updating affirm relation" + e);
		} catch (SQLException e) {
			throw new DataAccessException("cant update affirm relation" + e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void updateAgentAffirmRelation(Affirm affirm)
			throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call sy_update_agentaffirm(?,?,?,?,?) }";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, affirm.getAffirmLevel());
			cs.setString(2, affirm.getAffirmObject());
			cs.setString(3, affirm.getAffirmorID());
			cs.setString(4, affirm.getAffirmTypeID());
			cs.setString(5, affirm.getCreatedBy());
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for updating affirm relation" + e);
		} catch (SQLException e) {
			throw new DataAccessException("cant update affirm relation" + e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public Vector getDeptList(String PERSON_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
				+ "      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
				+ "      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
				+ "      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID "
				+ "   AND HDT.FOURTHDEPT = HD.DEPTID AND HD.cpny_id=(select cpny_id from hr_employee where person_id='"
				+ PERSON_ID + "')) " + " ORDER BY FOURTHDEPTID,DEPTID ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getDeptAgentList(String PERSON_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
				+ "      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
				+ "      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
				+ "      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID "
				+ "   AND HDT.FOURTHDEPT = HD.DEPTID AND (SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '"
				+ PERSON_ID
				+ "') AND HD.cpny_id=(select cpny_id from hr_employee where person_id='"
				+ PERSON_ID + "')) " + " ORDER BY FOURTHDEPTID,DEPTID ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getEmpList1(String PERSON_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.Chinese_Pinyin  AFFIRM_OBJECT_EN_NAME "
				+ ",'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE "
				+ "WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.person_id and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='"
				+ PERSON_ID + "')";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getEmpList2(String PERSON_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.Chinese_Pinyin  AFFIRM_OBJECT_EN_NAME "
				+ ",'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE "
				+ "WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.person_id  AND ( SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '"
				+ PERSON_ID
				+ "') and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='"
				+ PERSON_ID + "')";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getEmpListByAffirm(String affirmId, String PERSON_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String affirmSql = "";
		if (!affirmId.equals("")) {
			affirmSql = " AND AFFIRMOR_ID='" + affirmId + "'";
		}
		String SQL = " SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME "
				+ " ,'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.PERSON_ID "
				+ affirmSql
				+ " and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='"
				+ PERSON_ID + "')";

		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getEmpListByAgentAffirm(String affirmId, String PERSON_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String affirmSql = "";
		if (!affirmId.equals("")) {
			affirmSql = " AND AFFIRMOR_ID='" + affirmId + "'";
		}
		String SQL = " SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME "
				+ " ,'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.PERSON_ID AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID in ('LeaveApply','OtApply','ArModifyApply') AND (SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '"
				+ PERSON_ID
				// + "' or SY_AFFIRM_RELATION_TB.AFFIRMOR_ID_BACK = '" +
				// PERSON_ID
				+ "')"
				+ affirmSql
				+ " and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='"
				+ PERSON_ID + "')";

		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getDeptListByAffirm(String affirmId, PageControl pc,
			String cnpy_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String affirmSql = "";
		if (!affirmId.equals("")) {
			affirmSql = " AND AFFIRMOR_ID='" + affirmId + "'";
		}
		String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
				+ "      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
				+ "      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
				+ "      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID "
				+ "   AND HDT.FOURTHDEPT = HD.DEPTID "
				+ affirmSql
				+ " and HD.CPNY_ID='"
				+ cnpy_ID
				+ "' )ORDER BY FOURTHDEPTID,DEPTID ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			pc.seti();
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();
			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				vector.add(createAffirmRelationDept(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getDeptListByAgentAffirm(String personId, String affirmId,
			PageControl pc, String cnpy_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String affirmSql = "";
		if (!affirmId.equals("")) {
			affirmSql = " AND AFFIRMOR_ID='" + affirmId + "'";
		}
		String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
				+ "      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
				+ "      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
				+ "      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID in ('LeaveApply','OtApply','ArModifyApply') AND (SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '"
				+ personId
				// + "' or SY_AFFIRM_RELATION_TB.AFFIRMOR_ID_BACK = '" +
				// personId
				+ "') AND HDT.FOURTHDEPT = HD.DEPTID "
				+ affirmSql
				+ " and HD.CPNY_ID='"
				+ cnpy_ID
				+ "' )ORDER BY FOURTHDEPTID,DEPTID ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			pc.seti();
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();
			while ((pc.getI() < 20) && rs.next()) {
				vector.add(createAffirmRelationDept(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public Vector getDeptListByAgentAffirmAppoint(String personId,
			String affirmId, PageControl pc, String cnpy_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String affirmSql = "";
		String affirmSql1 = "";

		if (!affirmId.equals("")) {
			affirmSql = " AND ACT.AFFIRMOR_ID='" + affirmId + "'";
			affirmSql1 = " AND ACT.AFFIRMOR_ACT_ID='" + affirmId + "'";
		}

		String SQL = " select ACT.AFFIRMOR_ID,ACT.AFFIRMOR_ACT_ID,ACT.AFFIRM_TYPE_ID,to_char(ACT.CREATE_DATE,'yyyy-mm-dd') CREATE_DATE,ACT.CREATED_BY,Y.CHINESENAME,D.DEPTNAME "
				+ " from SY_AFFIRM_RELATION_TB_ACT ACT, HR_EMPLOYEE Y, HR_DEPARTMENT D where ACT.AFFIRMOR_ID = Y.PERSON_ID AND Y.DEPTID = D.DEPTID AND  ACT.ACTIVITY = 1 "
				+ affirmSql + affirmSql1;
		Logger.getLogger(getClass()).debug(SQL);
		try {
			pc.seti();
			conn = services.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();
			while ((pc.getI() < 20) && rs.next()) {
				vector.add(createAffirmRelationDeptAppoint(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return vector;
	}

	public void udpateAffirm(String oldId, String newId,
			String affirmRelationNo, String affirmTypeId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();
			String sql = "";
			if (affirmRelationNo.equals("") || affirmRelationNo == null) {
				sql = " UPDATE sy_affirm_relation_tb SET affirmor_id='" + newId
						+ "'  WHERE  affirmor_id='" + oldId + "' ";
			} else {
				sql = " UPDATE sy_affirm_relation_tb SET affirmor_id='" + newId
						+ "'  WHERE  affirmor_id='" + oldId
						+ "'  and affirm_relation_no='" + affirmRelationNo
						+ "'";
			}

			if (affirmTypeId != null && affirmTypeId.length() > 0) {
				sql += " and AFFIRM_TYPE_ID = '" + affirmTypeId + "'";
			}

			Logger.getLogger(getClass()).debug("update affirm sql " + sql);
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			// / conn.commit();

			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			try {
				conn.rollback();
			} catch (SQLException e) {
				Logger.getLogger(getClass()).debug(e.toString());
			}
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * public void udpateAffirm(String oldId,String newId,String isUpdate){
	 * Connection conn = null; PreparedStatement stmt = null; try {
	 * Logger.getLogger(getClass()).debug("update affirm"); conn =
	 * services.getConnection(); conn.setAutoCommit(false);
	 * 
	 * if("1".equals(isUpdate)){stmt=conn.prepareStatement(
	 * " UPDATE ess_affirm SET affirmor_id=? WHERE affirmor_id=? AND  affirm_flag=0 "
	 * ); stmt.setString(1, newId); stmt.setString(2, oldId);
	 * stmt.executeUpdate(); }stmt=conn.prepareStatement(
	 * " UPDATE sy_affirm_relation_tb SET affirmor_id=?  WHERE  affirmor_id=? "
	 * ); stmt.setString(1, newId); stmt.setString(2, oldId);
	 * stmt.executeUpdate(); conn.commit();
	 * 
	 * Logger.getLogger(getClass()).debug("update affirm ok "); } catch
	 * (SQLException ex) { Logger.getLogger(getClass()).debug(ex.toString());
	 * try{ conn.rollback(); }catch(SQLException e){
	 * Logger.getLogger(getClass()).debug(e.toString()); } } catch
	 * (ServiceLocatorException ex) {
	 * Logger.getLogger(getClass()).debug(ex.toString()); } finally {
	 * SqlUtil.close(stmt, conn); } }
	 */

	public boolean validateUdpateAffirm_ESS(String oldId, String newId) {
		boolean flag = true;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = services.getConnection();

			String sql = "SELECT HR.EMPID AS empID, HR.CHINESENAME AS chineseName "
					+ "  FROM HR_EMPLOYEE HR "
					+ " WHERE HR.Person_Id = ? "
					+ "   AND EXISTS (SELECT SA.AFFIRMOR_ID "
					+ "  			   FROM SY_AFFIRM_RELATION_TB_AUTO SA "
					+ "				  WHERE SA.AFFIRMOR_ID_BACK = ? "
					+ "					AND (HR.Person_Id > ? OR HR.Person_Id < ? ))";

			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newId);
			stmt.setString(2, oldId);
			stmt.setString(3, oldId);
			stmt.setString(4, oldId);

			rs = stmt.executeQuery();
			if (!rs.next()) {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}

		return flag;
	}

	public ResultSet selectChinesename(String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = services.getConnection();

			String sql = "SELECT HR.EMPID, HR.CHINESENAME, HR.EMAIL, E.DEPTNAME, Y.CPNY_NAME "
					+ "  FROM HR_EMPLOYEE HR, HR_DEPARTMENT E, HR_COMPANY Y  "
					+ " WHERE HR.DEPTID = E.DEPTID AND HR.CPNY_ID = Y.CPNY_ID AND HR.PERSON_ID = ? ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, adminId);

			rs = stmt.executeQuery();

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
		}

		return rs;
	}

	public boolean validateUdpateAffirm_ESS2(String oldId, String newId) {
		boolean flag = true;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 查找，是否存在，oldId和newId，同在一组决裁，newId已经通过了，就不能在进行替换，否则会出现重复
			conn = services.getConnection();
			String[] tablename = { "ESS_AFFIRM", "AR_DETAIL_AFFIRM" };// 修改加班、勤态、考勤修改决裁
			for (int i = 0; i < tablename.length; i++) {
				String sql = "";
				if (tablename[i].equals("AR_DETAIL_AFFIRM")) {
					sql = "SELECT A.AR_DETAIL_NO,A.AFFIRMOR_ID " + "  FROM "
							+ tablename[i]
							+ " A, "
							+ "		(SELECT C.AR_DETAIL_NO, MAX(C.AFFIRM_LEVEL) AS MAX_AFFIRM_LEVEL "
							+ "         FROM "
							+ tablename[i]
							+ " C WHERE C.AFFIRM_FLAG < 2 GROUP BY C.AR_DETAIL_NO) D "
							+ " WHERE A.AR_DETAIL_NO = D.AR_DETAIL_NO "
							+ "   AND A.AFFIRMOR_ID = ? "
							+ "   AND A.AFFIRM_LEVEL = D.MAX_AFFIRM_LEVEL "
							+ "   AND A.AFFIRM_FLAG = 0 "
							+ "   AND NOT EXISTS (SELECT B.AR_DETAIL_NO FROM "
							+ tablename[i]
							+ " B WHERE A.AR_DETAIL_NO = B.AR_DETAIL_NO AND AFFIRM_FLAG = 2) "
							+ "   AND EXISTS ( "
							+ "             SELECT E.AR_DETAIL_NO "
							+ "                FROM "
							+ tablename[i]
							+ " E "
							+ "               WHERE A.AR_DETAIL_NO = E.AR_DETAIL_NO "
							+ "                 AND E.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1 "
							+ "                 AND E.AFFIRM_FLAG = 1 "
							+ "                 AND E.AFFIRMOR_ID = ? ) ";
				} else {
					sql = "SELECT A.APPLY_NO,A.AFFIRMOR_ID " + "  FROM "
							+ tablename[i]
							+ " A, "
							+ "		(SELECT C.APPLY_NO, MAX(C.AFFIRM_LEVEL) AS MAX_AFFIRM_LEVEL "
							+ "         FROM "
							+ tablename[i]
							+ " C WHERE C.AFFIRM_FLAG < 2 GROUP BY C.APPLY_NO) D "
							+ " WHERE A.APPLY_NO = D.APPLY_NO "
							+ "   AND A.AFFIRMOR_ID = ? "
							+ "   AND A.AFFIRM_LEVEL = D.MAX_AFFIRM_LEVEL "
							+ "   AND A.AFFIRM_FLAG = 0 "
							+ "   AND NOT EXISTS (SELECT B.APPLY_NO FROM "
							+ tablename[i]
							+ " B WHERE A.APPLY_NO = B.APPLY_NO AND AFFIRM_FLAG = 2) "
							+ "   AND EXISTS ( "
							+ "             SELECT E.APPLY_NO "
							+ "                FROM "
							+ tablename[i]
							+ " E "
							+ "               WHERE A.APPLY_NO = E.APPLY_NO "
							+ "                 AND E.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1 "
							+ "                 AND E.AFFIRM_FLAG = 1 "
							+ "                 AND E.AFFIRMOR_ID = ? ) ";
				}
				Logger.getLogger(getClass()).debug("sql = " + sql);

				stmt = conn.prepareStatement(sql);

				stmt.setString(1, oldId);
				stmt.setString(2, newId);

				rs = stmt.executeQuery();

				if (rs.next()) {
					flag = false;
				}
			}

		} catch (Exception e) {
			flag = false;
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}

		return flag;
	}

	public boolean validateUdpateAffirm_ESS2_Appoint(String oldId,
			String newId, String appointType) {
		boolean flag = true;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 查找，是否存在，oldId和newId，同在一组申请过类型决裁，newId已经通过了，就不能在进行替换，否则会出现重复
			conn = services.getConnection();
			String sql = " select * from SY_AFFIRM_RELATION_TB_ACT T where T.AFFIRMOR_ID = ? AND T.AFFIRMOR_ACT_ID = ? AND T.AFFIRM_TYPE_ID = ? ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.setString(3, appointType);

			rs = stmt.executeQuery();

			if (rs.next()) {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}

		return flag;
	}

	public void udpateAffirm_ESS(String oldId, String newId, String adminId,
			String module) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();
			conn.setAutoCommit(false);
			String sql = "";
			// String []tablename={"ESS_AFFIRM","AR_DETAIL_AFFIRM"};
			List tablename = new ArrayList();
			if (module.equals("AR")) {
				tablename.add("ESS_AFFIRM");
				tablename.add("AR_DETAIL_AFFIRM");
			}
			for (int i = 0; i < tablename.size(); i++) {
				// 当出现，oldId和newId，在同一申请决裁中，
				// 把大于oldId的决裁级别 - 1，为发邮件做准备
				if (tablename.get(i).equals("AR_DETAIL_AFFIRM")) {

					sql = " UPDATE "
							+ tablename.get(i)
							+ " A SET A.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1, UPDATE_DATE=sysdate, UPDATE_BY=? "
							+ "  WHERE A.AFFIRM_FLAG = 0 "
							+ "    AND EXISTS( "
							+ "               SELECT * "
							+ "  			   FROM "
							+ tablename.get(i)
							+ " E "
							+ " 				  WHERE E.AFFIRMOR_ID = ? "
							+ "   			    AND A.AR_DETAIL_NO = E.AR_DETAIL_NO AND E.AFFIRM_FLAG = 0 "
							+ "   				AND A.AFFIRM_LEVEL > E.AFFIRM_LEVEL "
							+ "   				AND EXISTS (SELECT * FROM "
							+ tablename.get(i)
							+ " T WHERE T.AFFIRMOR_ID = ? AND E.AR_DETAIL_NO = T.AR_DETAIL_NO ) "
							+

							" 				) ";

					Logger.getLogger(getClass()).debug("sql = " + sql);

					stmt = conn.prepareStatement(sql);
					stmt.setString(1, adminId);
					stmt.setString(2, oldId);
					stmt.setString(3, newId);
					stmt.executeUpdate();

					// 删除重复的决裁者
					sql = " DELETE "
							+ tablename.get(i)
							+ " E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
							+ "    AND EXISTS (SELECT * FROM "
							+ tablename.get(i)
							+ " T WHERE T.AFFIRMOR_ID = ? AND E.AR_DETAIL_NO = T.AR_DETAIL_NO ) ";
					Logger.getLogger(getClass()).debug("sql = " + sql);

					stmt = conn.prepareStatement(sql);
					stmt.setString(1, oldId);
					stmt.setString(2, newId);
					stmt.executeUpdate();

					// 更新蕨菜信息表
					// stmt=conn.prepareStatement(" UPDATE "+tablename.get(i)+" SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATE_BY=?  WHERE affirmor_id in(select affirmor_id from sy_affirm_relation_tb where AFFIRMOR_ID_BACK=? and affirm_type_id in ('LeaveApply','OtApply','ArModifyApply')) AND  affirm_flag=0 ");
					stmt = conn
							.prepareStatement(" UPDATE "
									+ tablename.get(i)
									+ " SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATE_BY=?  WHERE affirmor_id =? AND  affirm_flag=0 ");
					stmt.setString(1, newId);
					stmt.setString(2, adminId);
					stmt.setString(3, oldId);
					stmt.executeUpdate();
				} else {
					sql = " UPDATE "
							+ tablename.get(i)
							+ " A SET A.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1, UPDATE_DATE=sysdate, UPDATED_BY=? "
							+ "  WHERE A.AFFIRM_FLAG = 0 "
							+ "    AND EXISTS( "
							+ "               SELECT * "
							+ "  			   FROM "
							+ tablename.get(i)
							+ " E "
							+ " 				  WHERE E.AFFIRMOR_ID = ? "
							+ "   			    AND A.APPLY_NO = E.APPLY_NO AND E.AFFIRM_FLAG = 0 "
							+ "   				AND A.AFFIRM_LEVEL > E.AFFIRM_LEVEL "
							+ "   				AND EXISTS (SELECT * FROM "
							+ tablename.get(i)
							+ " T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) "
							+

							" 				) ";

					Logger.getLogger(getClass()).debug("sql = " + sql);

					stmt = conn.prepareStatement(sql);
					stmt.setString(1, adminId);
					stmt.setString(2, oldId);
					stmt.setString(3, newId);
					stmt.executeUpdate();

					// 删除重复的决裁者
					sql = " DELETE "
							+ tablename.get(i)
							+ " E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
							+ "    AND EXISTS (SELECT * FROM "
							+ tablename.get(i)
							+ " T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
					Logger.getLogger(getClass()).debug("sql = " + sql);

					stmt = conn.prepareStatement(sql);
					stmt.setString(1, oldId);
					stmt.setString(2, newId);
					stmt.executeUpdate();
					// 更新蕨菜信息表
					// stmt=conn.prepareStatement(" UPDATE "+tablename.get(i)+" SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATED_BY=?  WHERE affirmor_id in(select affirmor_id from sy_affirm_relation_tb where AFFIRMOR_ID_BACK=? AND affirm_type_id in ('LeaveApply','OtApply','ArModifyApply')) AND  affirm_flag=0 ");
					stmt = conn
							.prepareStatement(" UPDATE "
									+ tablename.get(i)
									+ " SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATED_BY=?  WHERE affirmor_id =? AND  affirm_flag=0 ");
					stmt.setString(1, newId);
					stmt.setString(2, adminId);
					stmt.setString(3, oldId);
					stmt.executeUpdate();
				}
			}

			// 更新系统决裁表
			// sql =
			// "UPDATE SY_AFFIRM_RELATION_TB S SET S.AFFIRM_FLAG = 0, UPDATE_DATE=sysdate, UPDATED_BY=? "
			// +
			// " WHERE S.AFFIRM_RELATION_NO IN ( " +
			// "   SELECT SA.AFFIRM_RELATION_NO " +
			// "     FROM SY_AFFIRM_RELATION_TB SA " +
			// "     WHERE SA.AFFIRMOR_ID = ? " +
			// "       AND EXISTS ( SELECT * " +
			// "                	    FROM SY_AFFIRM_RELATION_TB T " +
			// "               	   WHERE SA.AFFIRM_TYPE_ID = T.AFFIRM_TYPE_ID "
			// +
			// "                 	     AND SA.AFFIRM_OBJECT = T.AFFIRM_OBJECT "
			// +
			// "                 		 AND T.AFFIRMOR_ID = ? " +
			// "     		 )) " +
			// "       AND  Affirm_Type_Id in ('LeaveApply','OtApply','ArModifyApply') "
			// ;
			//            
			// Logger.getLogger(getClass()).debug("sql = " + sql);
			//            
			// stmt=conn.prepareStatement(sql);
			// stmt.setString(1, adminId);
			// stmt.setString(2, oldId);
			// stmt.setString(3, newId);
			// stmt.executeUpdate();

			// stmt=conn.prepareStatement(" UPDATE sy_affirm_relation_tb SET affirmor_id=?, UPDATE_DATE=sysdate, UPDATED_BY=?  WHERE  AFFIRMOR_ID_BACK=? AND AFFIRM_FLAG = 1 AND Affirm_Type_Id in ('LeaveApply','OtApply','ArModifyApply') ");

			stmt = conn
					.prepareStatement(" UPDATE sy_affirm_relation_tb_auto SET affirmor_id=?, UPDATE_DATE=sysdate, UPDATE_BY=?  WHERE  AFFIRMOR_ID_BACK=? and MODULE=?");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.setString(4, module);
			stmt.executeUpdate();
			conn.commit();

			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			try {
				conn.rollback();
			} catch (SQLException e) {
				Logger.getLogger(getClass()).debug(e.toString());
			}
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	public void udpateAffirm_ESS_Appoint(String oldId, String newId,
			String adminId, String module, String appointType) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement psA = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();
			conn.setAutoCommit(false);
			String sql = "";
			List tablename = new ArrayList();
			if (module.equals("AR")) {
				tablename.add("ESS_AFFIRM");
				tablename.add("AR_DETAIL_AFFIRM");
			} else if (module.equals("GA")) {
				if(appointType.equals("SealApply")){
				tablename.add("GA_SEAL_AFFIRM");
				}
				if(appointType.equals("PresentApply")){
				tablename.add("GA_PRESENT_AFFIRM");
				}
				if(appointType.equals("ExpressApply")){
				tablename.add("ga_express_affirm");
				}
				if(appointType.equals("SecurityComplateApply")){
				tablename.add("SE_COMPLETRECTIFICAT_AFFIRM");
				}
				if(appointType.equals("Visiter_Apply")){
				tablename.add("ga_visiter_apply_affirm");
				}
				if(appointType.equals("checkApply")){
				tablename.add("GA_CHECK_APPLY_AFFIRM");
				}
				if(appointType.equals("temporarycardApply")){
				tablename.add("GA_TEMPCARD_APPLICATION_AFFIRM");
				}
			}
			int insert = 0;
			for (int i = 0; i < tablename.size(); i++) {
				// 当出现，oldId和newId，在同一申请决裁中，
				// 把大于oldId的决裁级别 - 1，为发邮件做准备
				if (module.equals("AR")) {
					if (tablename.get(i).equals("AR_DETAIL_AFFIRM")) {

						sql = " UPDATE "
								+ tablename.get(i)
								+ " A SET A.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1, UPDATE_DATE=sysdate, UPDATE_BY=? "
								+ "  WHERE A.AFFIRM_FLAG = 0 "
								+ "    AND EXISTS( "
								+ "               SELECT * "
								+ "  			   FROM "
								+ tablename.get(i)
								+ " E "
								+ " 				  WHERE E.AFFIRMOR_ID = ? "
								+ "   			    AND A.AR_DETAIL_NO = E.AR_DETAIL_NO AND E.AFFIRM_FLAG = 0 "
								+ "   				AND A.AFFIRM_LEVEL > E.AFFIRM_LEVEL "
								+ "   				AND EXISTS (SELECT * FROM "
								+ tablename.get(i)
								+ " T WHERE T.AFFIRMOR_ID = ? AND E.AR_DETAIL_NO = T.AR_DETAIL_NO ) "
								+

								" 				) ";

						Logger.getLogger(getClass()).debug("sql = " + sql);

						stmt = conn.prepareStatement(sql);
						stmt.setString(1, adminId);
						stmt.setString(2, oldId);
						stmt.setString(3, newId);
						stmt.executeUpdate();

						// 删除重复的决裁者
						sql = " DELETE "
								+ tablename.get(i)
								+ " E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
								+ "    AND EXISTS (SELECT * FROM "
								+ tablename.get(i)
								+ " T WHERE T.AFFIRMOR_ID = ? AND E.AR_DETAIL_NO = T.AR_DETAIL_NO ) ";
						Logger.getLogger(getClass()).debug("sql = " + sql);

						stmt = conn.prepareStatement(sql);
						stmt.setString(1, oldId);
						stmt.setString(2, newId);
						stmt.executeUpdate();

						// 更新蕨菜信息表
						stmt = conn
								.prepareStatement(" UPDATE "
										+ tablename.get(i)
										+ " SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATE_BY=?  WHERE affirmor_id =? AND  affirm_flag=0 ");
						stmt.setString(1, newId);
						stmt.setString(2, adminId);
						stmt.setString(3, oldId);
						stmt.executeUpdate();
					} else {
						sql = " UPDATE "
								+ tablename.get(i)
								+ " A SET A.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1, UPDATE_DATE=sysdate, UPDATED_BY=? "
								+ "  WHERE A.AFFIRM_FLAG = 0 "
								+ "    AND EXISTS( "
								+ "               SELECT * "
								+ "  			   FROM "
								+ tablename.get(i)
								+ " E "
								+ " 				  WHERE E.AFFIRMOR_ID = ? "
								+ "   			    AND A.APPLY_NO = E.APPLY_NO AND E.AFFIRM_FLAG = 0 "
								+ "   				AND A.AFFIRM_LEVEL > E.AFFIRM_LEVEL "
								+ "   				AND EXISTS (SELECT * FROM "
								+ tablename.get(i)
								+ " T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) "
								+

								" 				) ";

						Logger.getLogger(getClass()).debug("sql = " + sql);

						stmt = conn.prepareStatement(sql);
						stmt.setString(1, adminId);
						stmt.setString(2, oldId);
						stmt.setString(3, newId);
						stmt.executeUpdate();

						// 删除重复的决裁者
						sql = " DELETE "
								+ tablename.get(i)
								+ " E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
								+ "    AND EXISTS (SELECT * FROM "
								+ tablename.get(i)
								+ " T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
						Logger.getLogger(getClass()).debug("sql = " + sql);

						stmt = conn.prepareStatement(sql);
						stmt.setString(1, oldId);
						stmt.setString(2, newId);
						stmt.executeUpdate();
						stmt = conn
								.prepareStatement(" UPDATE "
										+ tablename.get(i)
										+ " SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATED_BY=?  WHERE affirmor_id =? AND  affirm_flag=0 ");
						stmt.setString(1, newId);
						stmt.setString(2, adminId);
						stmt.setString(3, oldId);
						stmt.executeUpdate();
					}
				} else if (module.equals("GA")) {
					if (insert == 0) {
						if (tablename.get(i).equals("GA_SEAL_AFFIRM")) {
							this.UpdateAffirm_Seal(oldId, newId,
									"GA_SEAL_AFFIRM", adminId);
						} else if (tablename.get(i).equals("GA_PRESENT_AFFIRM")) {
							this.UpdateAffirm_Present(oldId, newId,
									"GA_PRESENT_AFFIRM", adminId);
						} else if (tablename.get(i).equals("ga_express_affirm")) {
							this.UpdateAffirm_express(oldId, newId,
									"ga_express_affirm", adminId);
						} else if (tablename.get(i).equals(
								"SE_COMPLETRECTIFICAT_AFFIRM")) {
							this.UpdateAffirm_COMPLETRECTIFICAT(oldId, newId,
									"SE_COMPLETRECTIFICAT_AFFIRM", adminId);
						} else if (tablename.get(i).equals(
								"ga_visiter_apply_affirm")) {
							this.UpdateAffirm_Visiter(oldId, newId,
									"ga_visiter_apply_affirm", adminId);
						} else if (tablename.get(i).equals(
								"GA_CHECK_APPLY_AFFIRM")) {
							this.UpdateAffirm_CHECK(oldId, newId,
									"GA_CHECK_APPLY_AFFIRM", adminId);
						} else if (tablename.get(i).equals(
								"GA_TEMPCARD_APPLICATION_AFFIRM")) {
							this.UpdateAffirm_TEMPCARD(oldId, newId,
									"GA_TEMPCARD_APPLICATION_AFFIRM", adminId);
						}
					}
					insert++;
				}
			}

//			stmt = conn
//					.prepareStatement(" UPDATE sy_affirm_relation_tb_auto SET affirmor_id=?, UPDATE_DATE=sysdate, UPDATE_BY=?  WHERE  AFFIRMOR_ID_BACK=? and MODULE=?");
//			stmt.setString(1, newId);
//			stmt.setString(2, adminId);
//			stmt.setString(3, oldId);
//			stmt.setString(4, module);
//			stmt.executeUpdate();
//			conn.commit();

			String sqlApp = " insert into SY_AFFIRM_RELATION_TB_ACT(AFFIRMOR_ID ,AFFIRMOR_ACT_ID,AFFIRM_TYPE_ID,CREATE_DATE,CREATED_BY,UPDATE_DATE,UPDATED_BY) values( "
					+ " ?,?,?,sysdate,?,sysdate,?) ";
			psA = conn.prepareStatement(sqlApp);
			psA.setString(1, oldId);
			psA.setString(2, newId);
			psA.setString(3, appointType);
			psA.setString(4, adminId);
			psA.setString(5, adminId);
			psA.executeUpdate();
			conn.commit();

			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			try {
				conn.rollback();
			} catch (SQLException e) {
				Logger.getLogger(getClass()).debug(e.toString());
			}
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	public void recoverAffirm_ESS(String affirmId, String module) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("recover affirm");
			conn = services.getConnection();

			// stmt=conn.prepareStatement(" UPDATE sy_affirm_relation_tb SET affirmor_id = AFFIRMOR_ID_BACK, AFFIRM_FLAG = 1,UPDATE_DATE=sysdate, UPDATED_BY=? WHERE  AFFIRMOR_ID_BACK=? AND Affirm_Type_Id in ('LeaveApply','OtApply','ArModifyApply') ");
			stmt = conn
					.prepareStatement(" UPDATE sy_affirm_relation_tb_auto SET affirmor_id = AFFIRMOR_ID_BACK, UPDATE_DATE=sysdate, UPDATE_BY=? WHERE  AFFIRMOR_ID_BACK=? and MODULE=?");
			stmt.setString(1, affirmId);
			stmt.setString(2, affirmId);
			stmt.setString(3, module);
			stmt.executeUpdate();

			Logger.getLogger(getClass()).debug("recover affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	public void recoverAffirm_ESS_Appoint(String adminId,String appointType) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("recover affirm");
			conn = services.getConnection();

			stmt = conn
					.prepareStatement(" delete from SY_AFFIRM_RELATION_TB_ACT Y WHERE Y.AFFIRMOR_ID = ?  AND Y.AFFIRM_TYPE_ID = ? AND Y.ACTIVITY = 1");
			stmt.setString(1, adminId);
			stmt.setString(2, appointType);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("recover affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * begin update ga_seal_affirm
	 */

	public void UpdateAffirm_Seal(String oldId, String newId, String tableName,
			String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE GA_SEAL_AFFIRM E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM GA_SEAL_AFFIRM T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE GA_SEAL_AFFIRM f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.UPDATE_BY=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * update GA_PRESENT_AFFIRM
	 */

	public void UpdateAffirm_Present(String oldId, String newId,
			String tableName, String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE GA_PRESENT_AFFIRM E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM GA_PRESENT_AFFIRM T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE GA_PRESENT_AFFIRM f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.updated_by=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * update ga_express_affirm
	 */

	public void UpdateAffirm_express(String oldId, String newId,
			String tableName, String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE ga_express_affirm E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM ga_express_affirm T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE ga_express_affirm f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.updated_by=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * update SE_COMPLETRECTIFICAT_AFFIRM
	 */

	public void UpdateAffirm_COMPLETRECTIFICAT(String oldId, String newId,
			String tableName, String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE SE_COMPLETRECTIFICAT_AFFIRM E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM SE_COMPLETRECTIFICAT_AFFIRM T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE SE_COMPLETRECTIFICAT_AFFIRM f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.updated_by=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * update GA_PRESENT_AFFIRM
	 */

	public void UpdateAffirm_Visiter(String oldId, String newId,
			String tableName, String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE ga_visiter_apply_affirm E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM ga_visiter_apply_affirm T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE ga_visiter_apply_affirm f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.updated_by=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0 ");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * update GA_PRESENT_AFFIRM
	 */

	public void UpdateAffirm_CHECK(String oldId, String newId,
			String tableName, String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE GA_CHECK_APPLY_AFFIRM E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM GA_CHECK_APPLY_AFFIRM T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE GA_CHECK_APPLY_AFFIRM f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.updated_by=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0 ");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/*
	 * update GA_TEMPCARD_APPLICATION_AFFIRM end
	 */

	public void UpdateAffirm_TEMPCARD(String oldId, String newId,
			String tableName, String adminId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Logger.getLogger(getClass()).debug("update affirm");
			conn = services.getConnection();

			// 删除重复的决裁者
			String sql = " DELETE GA_TEMPCARD_APPLICATION_AFFIRM E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 "
					+ "    AND EXISTS (SELECT * FROM GA_TEMPCARD_APPLICATION_AFFIRM T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) ";
			Logger.getLogger(getClass()).debug("sql = " + sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oldId);
			stmt.setString(2, newId);
			stmt.executeUpdate();

			stmt = conn
					.prepareStatement(" UPDATE GA_TEMPCARD_APPLICATION_AFFIRM f SET f.affirmor_id = ?, f.UPDATE_DATE=sysdate, f.updated_by=? WHERE  f.activity = 1 and f.affirmor_id = ? and f.affirm_flag = 0 ");
			stmt.setString(1, newId);
			stmt.setString(2, adminId);
			stmt.setString(3, oldId);
			stmt.executeUpdate();
			conn.commit();
			Logger.getLogger(getClass()).debug("update affirm ok ");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}

	/**
	 * 
	 * @param deptId
	 * @return
	 */
	public Vector getDeptList(String deptId, PageControl pc, String cnpy_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
				+ "      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
				+ "      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
				+ "      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID AND HDT.DEPTID = HD.DEPTID and HD.cpny_ID=?) T "
				+ " WHERE EXISTS (SELECT B1.DEPTID FROM HR_DEPARTMENT B1 WHERE B1.DEPTID = T.DEPTID "
				+ " AND B1.DEPTID = ? START WITH B1.DEPTID = ? CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID) "
				+ " ORDER BY FOURTHDEPTID,DEPTID ";

		Logger.getLogger(getClass()).debug(SQL + " param: " + deptId);
		try {
			pc.seti();
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cnpy_ID);
			pstmt.setString(2, deptId);
			pstmt.setString(3, deptId);
			rs = pstmt.executeQuery();
			pc.setii();
			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				vector.add(createAffirmRelationDept(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public Vector getEmpList(String empId, String cnpy_ID) {
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME "
				+ ",'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE "
				+ "WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.Person_Id and HR_EMPLOYEE.Cpny_Id='"
				+ cnpy_ID + "'" + "AND HR_EMPLOYEE.Person_Id = ?";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vector.add(createAffirmRelationDept(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public List getAffirmRelation(String deptid, String affirmtypeid) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_V v, hr_employee e WHERE AFFIRM_OBJECT =? and AFFIRM_TYPE_ID =? and v.affirmor_id = e.person_id(+) order by AFFIRM_LEVEL";
		// Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, deptid);
			pstmt.setString(2, affirmtypeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createAffirmRelation(rs));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getAffirmRelation(String deptid, String affirmtypeid,
			String affirmFlag) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_V v, hr_employee e WHERE AFFIRM_FLAG = ? AND AFFIRM_OBJECT =? and AFFIRM_TYPE_ID =? and e.person_id(+) = v.affirmor_id order by AFFIRM_LEVEL";
		// Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, affirmFlag);
			pstmt.setString(2, deptid);
			pstmt.setString(3, affirmtypeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createAffirmRelation(rs));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getAffirmRelationAppoint(String affirmorId,
			String affirmtypeid, String affirmFlag) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_ACT v, hr_employee e WHERE v.activity = ? and AFFIRMOR_ID =? and AFFIRM_TYPE_ID = ? and e.person_id(+) = v.affirmor_id";
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, affirmFlag);
			pstmt.setString(2, affirmorId);
		    pstmt.setString(3, affirmtypeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createAffirmRelationAppoint(rs));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public String getDeptidByEmpid(String empid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT deptid hr_employee e WHERE e.person_id = ?";
		// Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return StringUtil.checkNull(rs.getString("deptid"));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return new String("");
	}

	private Affirm createAffirmRelationDept(ResultSet rs) {
		Affirm affirm = new Affirm();
		try {
			affirm.setAffirmObject(rs.getString("AFFIRM_OBJECT") != null ? rs
					.getString("AFFIRM_OBJECT") : "");
			affirm
					.setAffirmObjectName(rs.getString("AFFIRM_OBJECT_NAME") != null ? rs
							.getString("AFFIRM_OBJECT_NAME")
							: "");
			affirm
					.setAffirmObjectEnName(rs
							.getString("AFFIRM_OBJECT_EN_NAME") != null ? rs
							.getString("AFFIRM_OBJECT_EN_NAME") : "");
			affirm.setFourthDeptName(StringUtil.checkNull(rs
					.getString("FOURTHDEPTNAME")));

		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return affirm;
	}

	private Affirm createAffirmRelationDeptAppoint(ResultSet rs) {
		Affirm affirm = new Affirm();
		try {
			affirm.setAffirmorID(rs.getString("AFFIRMOR_ID") != null ? rs
					.getString("AFFIRMOR_ID") : "");
			affirm.setAffirmorName(rs.getString("CHINESENAME") != null ? rs
					.getString("CHINESENAME") : "");
			affirm.setAffirmorDeptName(rs.getString("DEPTNAME") != null ? rs
					.getString("DEPTNAME") : "");
			affirm
					.setAffirmorActId(rs.getString("AFFIRMOR_ACT_ID") != null ? rs
							.getString("AFFIRMOR_ACT_ID")
							: "");
			affirm
					.setAffirmorTypeId(rs.getString("AFFIRM_TYPE_ID") != null ? rs
							.getString("AFFIRM_TYPE_ID")
							: "");
			affirm.setCreateDate(rs.getString("CREATE_DATE") != null ? rs
					.getString("CREATE_DATE") : "");
			affirm.setCreatedBy(rs.getString("CREATED_BY") != null ? rs
					.getString("CREATED_BY") : "");
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return affirm;
	}

	private Affirm createAffirmRelation(ResultSet rs) {
		Affirm affirm = new Affirm();
		try {
			affirm
					.setAffirmRelationNo(rs.getString("AFFIRM_RELATION_NO") != null ? rs
							.getString("AFFIRM_RELATION_NO")
							: "");
			affirm.setCompanyID(rs.getString("COMPANY_ID") != null ? rs
					.getString("COMPANY_ID") : "");
			affirm.setAffirmorID(rs.getString("AFFIRMOR_ID") != null ? rs
					.getString("AFFIRMOR_ID") : "");
			affirm.setAffirmorName(rs.getString("CHINESENAME") != null ? rs
					.getString("CHINESENAME") : "空缺");
			affirm
					.setAffirmorEnName(rs.getString("CHINESE_PINYIN") != null ? rs
							.getString("CHINESE_PINYIN")
							: "");
			affirm.setAffirmTypeID(rs.getString("AFFIRM_TYPE_ID") != null ? rs
					.getString("AFFIRM_TYPE_ID") : "");
			affirm.setAffirmLevel(rs.getInt("AFFIRM_LEVEL"));
			affirm.setAffirmObject(rs.getString("AFFIRM_OBJECT") != null ? rs
					.getString("AFFIRM_OBJECT") : "");
			affirm
					.setAffirmObjectName(rs.getString("AFFIRM_OBJECT_NAME") != null ? rs
							.getString("AFFIRM_OBJECT_NAME")
							: "");
			affirm.setAffirmFlag(rs.getString("AFFIRM_FLAG") != null ? rs
					.getString("AFFIRM_FLAG") : "");
			affirm.setCreateDate(rs.getString("CREATE_DATE") != null ? rs
					.getString("CREATE_DATE") : "");
			affirm.setCreatedBy(rs.getString("CREATED_BY") != null ? rs
					.getString("CREATED_BY") : "");
			affirm.setUpdateDate(rs.getString("UPDATE_DATE") != null ? rs
					.getString("UPDATE_DATE") : "");
			affirm.setUpdatedBy(rs.getString("UPDATED_BY") != null ? rs
					.getString("UPDATED_BY") : "");
			affirm.setActivity(rs.getString("ACTIVITY") != null ? rs
					.getString("ACTIVITY") : "");
			affirm.setChinesePinYin(rs.getString("CHINESE_PINYIN") != null ? rs
					.getString("CHINESE_PINYIN") : "");
			affirm.setAffirmorDeptName(rs.getString("DEPTNAME") != null ? rs
					.getString("DEPTNAME") : "");
			affirm.setEmpId(rs.getString("EMPID") != null ? rs
					.getString("EMPID") : "");
			affirm
					.setAffirmorDeptEnName(rs.getString("DEPT_EN_NAME") != null ? rs
							.getString("DEPT_EN_NAME")
							: "");
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return affirm;
	}

	private Affirm createAffirmRelationAppoint(ResultSet rs) {
		Affirm affirm = new Affirm();
		try {
			affirm.setAffirmorID(rs.getString("AFFIRMOR_ID") != null ? rs
					.getString("AFFIRMOR_ID") : "");
			affirm.setAffirmorName(rs.getString("AFFIRMOR_NAME") != null ? rs
					.getString("AFFIRMOR_NAME") : "");
			affirm
					.setAffirmorActName(rs.getString("AFFIRMOR_NAME_ACT") != null ? rs
							.getString("AFFIRMOR_NAME_ACT")
							: "");
			// 委任人所属部门
			affirm.setDeptName(rs.getString("DEPTNAME") != null ? rs
					.getString("DEPTNAME") : "");
			affirm
					.setAffirmorTypeId(rs.getString("AFFIRM_OBJECT_NAME") != null ? rs
							.getString("AFFIRM_OBJECT_NAME")
							: "");
			affirm.setCreateDate(rs.getString("CREATE_DATE") != null ? rs
					.getString("CREATE_DATE") : "");
			
			
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return affirm;
	}

	/**
	 * getAffirmorList <br>
	 * 根据输入的员工工号和申请类型，找到此员工的所有决裁者。
	 * 
	 * @param objectId
	 *            String
	 * @param applyType
	 *            String
	 * @throws DataAccessException
	 * @return List
	 */
	public List getAffirmorList(String objectId, String applyType)
			throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_get_affirmor(?,?,?,?,?)}";
		List list = new ArrayList();
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setString(1, objectId);
			cs.setString(2, applyType);
			for (int i = 1;; i++) {
				cs.setInt(3, i);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
				cs.execute();
				Map map = new HashMap();
				map.put("affirmorID", StringUtil.checkNull(cs.getString(4)));
				map.put("affirmor", StringUtil.checkNull(cs.getString(5)));
				if (cs.getString(4) == null)
					break;
				list.add(map);
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
		return list;
	}

	public void affirmLevelUp(String affirmRelationNo) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_upward_affirmor(?)}";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(affirmRelationNo));
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void affirmLevelDown(String affirmRelationNo) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_downward_affirmor(?)}";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(affirmRelationNo));
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void affirmLevelDelete(String affirmRelationNo) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_delete_affirmor(?)}";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(affirmRelationNo));
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

}
