package com.ait.ess.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ess.entity.EssAffirm;
import com.ait.ess.entity.LeaveApply;
import com.ait.ess.entity.MatchApply;
import com.ait.ess.entity.OTApply;
import com.ait.ess.entity.Tolerence;
import com.ait.ess.entity.TolerenceContent;
import com.ait.ess.entity.TolerenceFeeContent;
import com.ait.util.NumberUtil;
import com.ait.util.PageControl;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

public class AffirmDAO {

	public AffirmDAO() {

	}

	public void MilitaryServicesAffirm(String militaryNo, String updatedBy, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_MILITARY_SERVICE " + "set activity='" + flag + "', " + "updated_by='" + updatedBy + "', " + "update_date=sysdate " + "where MILITARY_NO ='" + militaryNo + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int hrMilitaryServiceUpdate(String militaryNO, String flag) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_military(?,?)}";
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(militaryNO));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;
	}

	public void ForeignLanguageAffirm(String languageNo, String updatedBy, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_LANGUAGE set activity=?,updated_by=?,update_date=sysdate where LANGUAGE_NO =? ";

		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setString(2, updatedBy);
			pstmt.setInt(3, NumberUtil.parseInt(languageNo));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int hrLanguageUpdate(String languageNo, String flag) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_language(?,?)}";
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(languageNo));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;
	}

	public void healthAffirm(String healthNo, String updatedBy, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_HEALTH set activity=?,updated_by=?,update_date=sysdate where HEALTH_NO =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setString(2, updatedBy);
			pstmt.setInt(3, NumberUtil.parseInt(healthNo));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int hrHealthUpdate(String healthNo, String flag) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_health('" + healthNo + "', '" + flag + "')}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;
	}

	public void qulificationAffirm(String qualificationNo, String updatedBy, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_QUALIFICATION set activity=?,updated_by=?,update_date=sysdate where QUAL_NO =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setString(2, updatedBy);
			pstmt.setInt(3, NumberUtil.parseInt(qualificationNo));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int hrQualificationUpdate(String qualificationNo, String flag) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_qualification(?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(qualificationNo));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;
	}

	public void militaryAffirm(String militaryNO, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_MILITARY_SERVICE set activity=? where MILITARY_NO =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setInt(2, NumberUtil.parseInt(militaryNO));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void OTApplyAffirm(String otapplyNo, String flag, String updatedBy, String otLength) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ESS_APPLY_OT SET ACTIVITY = ?, UPDATED_BY = ?," + "ot_deduct_time=(TO_DATE(TO_CHAR(apply_ot_date, 'YYYY-MM-DD')||ot_to_time,'YYYY-MM-DD HH24:MI')-"
				+ "TO_DATE (TO_CHAR (apply_ot_date, 'YYYY-MM-DD') || ot_from_time,'YYYY-MM-DD HH24:MI')+apply_ot_flag) * 24 - ?" + " ,UPDATE_DATE = SYSDATE WHERE APPLY_OT_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setString(2, updatedBy);
			pstmt.setDouble(3, otapplyNo != null ? Double.parseDouble(otLength) : 0);
			pstmt.setInt(4, NumberUtil.parseInt(otapplyNo));
			if (NumberUtil.parseInt(flag) == 1 && pstmt.executeUpdate() == 1) {
				if (this.ApplyToAR(otapplyNo, "OTAPPLY") == 1)
					conn.commit();
				else
					conn.rollback();
			}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void savaOtApply(String otapplyNo, String flag, String updatedBy) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_ot_apply(?,?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(otapplyNo));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.setString(3, updatedBy);
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void LeaveApplyAffirm(String leaveApplyNo, String flag, String updatedBy) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ESS_LEAVE_APPLY_TB SET ACTIVITY = ?, UPDATED_BY = ?, UPDATE_DATE = SYSDATE WHERE LEAVE_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setString(2, updatedBy);
			pstmt.setInt(3, NumberUtil.parseInt(leaveApplyNo));
			if (NumberUtil.parseInt(flag) == 1 && pstmt.executeUpdate() == 1) {
				if (this.ApplyToAR(leaveApplyNo, "LEAVEAPPLY") == 1)
					conn.commit();
				else
					conn.rollback();
			}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				Logger.getLogger(getClass()).error(e.toString());
			}
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void MatchApplyAffirm(String matchApplyNo, String flag, String updatedBy) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ESS_MATCH_APPLY_TB SET ACTIVITY = ?, UPDATED_BY = ?, UPDATE_DATE = SYSDATE WHERE MATCH_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NumberUtil.parseInt(flag));

			pstmt.setString(2, updatedBy);
			pstmt.setInt(3, NumberUtil.parseInt(matchApplyNo));

			if (NumberUtil.parseInt(flag) == 1 && pstmt.executeUpdate() == 1) {
				if (this.ApplyToAR(matchApplyNo, "MATCHAPPLY") == 1)
					conn.commit();
				else
					conn.rollback();
			} else if (NumberUtil.parseInt(flag) == 2 && pstmt.executeUpdate() == 1) {
				conn.commit();
			}

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				Logger.getLogger(getClass()).error(e.toString());
			}
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void savaLeaveApply(String leaveApplyNo, String flag, String updatedBy) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_leave_apply(?,?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(leaveApplyNo));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.setString(3, updatedBy);
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}

	}

	public void savaMatchApply(String matchApplyNo, String flag, String updatedBy) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_match_apply(?,?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(matchApplyNo));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.setString(3, updatedBy);
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}

	}

	public void TolerenceApplyAffirm(String tolerenceNo, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_TOLERENCE_APPLY set ACTIVITY=? where TOLERENCE_NO =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setInt(2, NumberUtil.parseInt(tolerenceNo));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	/**
	 * savePersonalInfo <br>
	 * update hr_personal_info,
	 * 
	 * @param empid
	 *            String
	 * @param updatedBy
	 *            String
	 * @param flag
	 * String @
	 */
	private void savePersonalInfo(String empid, String updatedBy, String flag) {
		Connection conn = null;
		CallableStatement cs = null;
		String sql = "{call ess_save_personal_info(?,?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			cs = conn.prepareCall(sql);
			cs.setString(1, empid);
			cs.setString(2, updatedBy);
			cs.setInt(3, NumberUtil.parseInt(flag));
			cs.execute();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(cs, conn);
		}

	}

	public void PersonalInfoAffirm(String empid, String updatedBy, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = " update ESS_PERSONAL_INFO set ACTIVITY=?,updated_by=?,update_date=sysdate where empid =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setString(2, updatedBy);
			pstmt.setString(3, empid);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		savePersonalInfo(empid, updatedBy, flag);
	}

	public int hrFamilyUpdate(String familyNo, String flag) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call ess_update_family(?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(familyNo));
			cs.setInt(2, NumberUtil.parseInt(flag));
			cs.execute();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return 0;
	}

	/**
	 * FamilyAffirm
	 * 
	 * @param familyNo
	 *            String
	 * @param updatedBy
	 *            String
	 * @param flag
	 * String @
	 */
	public void FamilyAffirm(String familyNo, String updatedBy, String flag) {
		Connection conn = null;
		Statement state = null;
		String SQL = "update ESS_FAMILY " + "set " + "activity='" + NumberUtil.parseInt(flag) + "', " + "updated_by='" + updatedBy + "', " + "update_date=sysdate " + "where family_no ='" + NumberUtil.parseInt(familyNo) + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			state.executeUpdate(SQL);
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
	}

	/**
	 * getOtAffirmStatusList @
	 * @return List
	 * @param applyType
	 *            String,the attendance apply type
	 * @param seqNo
	 *            int, the sequence number of the application item
	 */
	// 添加裁决情况的子列表
	public List getAffirmStatusList(String applyType, String seqNo) {
		List list = new ArrayList();
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		String sql = "select * " + "from (SELECT ess_affirm_no, apply_no, affirm_flag, affirm_level, affirmor_id," + " get_emp_name (affirmor_id) affirmor_name, update_date, activity,apply_type FROM ess_affirm " + "where APPLY_TYPE='" + applyType + "'"
				+ "ORDER BY AFFIRM_FLAG DESC)  " + " WHERE apply_no='" + seqNo + "'" + " ORDER BY AFFIRM_FLAG ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			state = con.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				list.add(this.createAffirmRelation(rs));
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, state, con);
		}
		return list;
	}

	private EssAffirm createAffirmRelation(ResultSet rs) {
		EssAffirm affirm = new EssAffirm();
		try {
			affirm.setActivity(rs.getInt("ACTIVITY"));
			affirm.setAffirmFlag(rs.getInt("Affirm_flag"));
			affirm.setAffirmLevel(rs.getInt("Affirm_level"));
			affirm.setApplyNo(rs.getInt("Apply_no"));
			affirm.setEssAffirmNo(rs.getInt("Ess_affirm_no"));
			affirm.setAffirmorId(StringUtil.checkNull(rs.getString("Affirmor_id")));
			affirm.setAffirmorName(StringUtil.checkNull(rs.getString("Affirmor_name")));
			affirm.setApplyType(StringUtil.checkNull(rs.getString("Apply_type")));
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return affirm;
	}

	/**
	 * getApplyOTAffirmList
	 * 
	 * @param pc
	 *            PageControl
	 * @param sql
	 * String @
	 * @return List
	 */
	public List getApplyOTAffirmList(PageControl pc, String sql) {
		return getApplyOTAffirmList(pc, sql, null);
	}

	public List getApplyOTAffirmList(PageControl pc, String sql, String applyType) {
		List list = new ArrayList();
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String sqlString = " select * " + "from ESS_APPLY_OT_V " + sql + " " + "order by ACTIVITY , APPLY_OT_DATE DESC";
		Logger.getLogger(getClass()).debug(sqlString);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sqlString);
			pc.seti();
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();

			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				list.add(createOTApply(rs, applyType));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	private OTApply createOTApply(ResultSet rs, String applyType) {
		OTApply otapply = new OTApply();
		try {
			otapply.setEmpID(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
			otapply.setChineseName(rs.getString("CHINESENAME") != null ? rs.getString("CHINESENAME") : "");
			otapply.setOtApplySEQ((rs.getString("APPLY_OT_NO") != null) ? rs.getString("APPLY_OT_NO") : "");
			otapply.setOtApplyDate((rs.getString("APPLY_OT_DATE") != null) ? rs.getString("APPLY_OT_DATE").substring(0, 10) : "");
			otapply.setCreateDate((rs.getString("CREATE_DATE") != null ? rs.getString("CREATE_DATE").substring(0, 10) : ""));
			otapply.setOtDate((rs.getString("APPLY_OT_DATE") != null) ? rs.getString("APPLY_OT_DATE").substring(0, 10) : "");
			otapply.setOtDeductTime((rs.getString("OT_DEDUCT_TIME") != null) ? rs.getString("OT_DEDUCT_TIME") : "");
			otapply.setOtStartTime((rs.getString("OT_FROM_TIME") != null) ? rs.getString("OT_FROM_TIME") : "");
			otapply.setOtEndTime((rs.getString("OT_TO_TIME") != null) ? rs.getString("OT_TO_TIME") : "");
			otapply.setOtWorkContent((rs.getString("APPLY_OT_REMARK") != null) ? rs.getString("APPLY_OT_REMARK") : "");
			otapply.setActivity(rs.getInt("ACTIVITY"));
			otapply.setUpdatedBy(StringUtil.checkNull(rs.getString("updated_by")));
			otapply.setOtType(StringUtil.checkNull(rs.getString("APPLY_OT_TYPE")));
			otapply.setOtNextDays(rs.getInt("apply_ot_flag"));
			otapply.setOtLength(rs.getDouble("apply_ot_length"));
			// 添加裁决人裁决情况列表
			if (applyType != null) {
				try {
					Logger.getLogger(getClass()).debug("set AffirmStatusList to leaveapply leaveapply.getLeaveApplyNo() [" + otapply.getOtApplySEQ() + "]");
					otapply.setAffirmStatusList(getAffirmStatusList(applyType, otapply.getOtApplySEQ()));
				} catch (Exception ex) {
					Logger.getLogger(getClass()).debug(ex.toString());
				}
			}
			// leaveapply.setAffirmStatusList()
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return otapply;
	}

	public List getLeaveApplyAffirmList(PageControl pc, String sql) {
		return getLeaveApplyAffirmList(pc, sql, null);
	}

	public List getLeaveApplyAffirmList(PageControl pc, String sql, String applyType) {
		List list = new ArrayList();
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String sqlString = "select * " + "from ESS_LEAVE_APPLY_V " + sql + " order by LEAVE_FROM_TIME, ACTIVITY";
		Logger.getLogger(getClass()).debug(sqlString);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sqlString);
			pc.seti();
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();

			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				list.add(this.createLeaveApply(rs, applyType));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	public List getMatchApplyAffirmList(PageControl pc, String sql) {
		return getMatchApplyAffirmList(pc, sql, null);
	}

	public List getMatchApplyAffirmList(PageControl pc, String sql, String applyType) {
		List list = new ArrayList();
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String sqlString = "select * " + "from ESS_MATCH_APPLY_V " + sql + " order by activity, MATCH_FROM_TIME DESC";
		Logger.getLogger(getClass()).debug(sqlString);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sqlString);
			pc.seti();
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();

			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				list.add(this.createMatchApply(rs, applyType));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	// modify by lvhongbin at 2005-11-08
	private LeaveApply createLeaveApply(ResultSet rs, String applyType) {
		LeaveApply leaveapply = new LeaveApply();
		try {
			leaveapply.setEmpID(rs.getString("empid") != null ? rs.getString("empid") : "");
			leaveapply.setLeavePerson(rs.getString("empid") != null ? rs.getString("empid") : "");
			leaveapply.setLeavePersonName(rs.getString("CHINESENAME") != null ? rs.getString("CHINESENAME") : "");
			leaveapply.setLeaveApplyNo((rs.getString("LEAVE_NO") != null) ? rs.getString("LEAVE_NO") : "");
			leaveapply.setLeaveApplyDate((rs.getString("CREATE_DATE") != null) ? rs.getString("CREATE_DATE").substring(0, 10) : "");
			leaveapply.setLeaveApplyStartDate((rs.getString("LEAVE_FROM_TIME") != null) ? rs.getString("LEAVE_FROM_TIME") : "");
			leaveapply.setLeaveApplyEndDate((rs.getString("LEAVE_TO_TIME") != null) ? rs.getString("LEAVE_TO_TIME") : "");
			leaveapply.setLeaveDays((rs.getString("SET_LEAVE_DAYS") != null) ? rs.getString("SET_LEAVE_DAYS") : "");
			leaveapply.setLeaveTypeCode((rs.getString("LEAVE_TYPE") != null) ? rs.getString("LEAVE_TYPE") : "");
			leaveapply.setLeaveTypeCodeName((rs.getString("LEAVE_TYPE_NAME") != null) ? rs.getString("LEAVE_TYPE_NAME") : "");
			leaveapply.setActivity(rs.getInt("ACTIVITY"));
			leaveapply.setUpdatedBy(StringUtil.checkNull(rs.getString("updated_by")));
			leaveapply.setLeaveContent((rs.getString("LEAVE_REASON") != null) ? rs.getString("LEAVE_REASON") : "");
			// 添加裁决人裁决情况列表
			if (applyType != null) {
				try {
					Logger.getLogger(getClass()).debug("set AffirmStatusList to leaveapply leaveapply.getLeaveApplyNo() [" + leaveapply.getLeaveApplyNo() + "]");
					leaveapply.setAffirmStatusList(getAffirmStatusList(applyType, leaveapply.getLeaveApplyNo()));
				} catch (Exception ex) {
					Logger.getLogger(getClass()).debug(ex.toString());
				}
			}
			// leaveapply.setAffirmStatusList()
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex);
		}
		return leaveapply;
	}

	private MatchApply createMatchApply(ResultSet rs, String applyType) {
		MatchApply matchapply = new MatchApply();
		try {
			matchapply.setMatchPerson(rs.getString("empid") != null ? rs.getString("empid") : "");
			matchapply.setMatchPersonName(rs.getString("CHINESENAME") != null ? rs.getString("CHINESENAME") : "");
			matchapply.setMatchApplyNo((rs.getString("MATCH_NO") != null) ? rs.getString("MATCH_NO") : "");
			matchapply.setMatchApplyDate((rs.getString("CREATE_DATE") != null) ? rs.getString("CREATE_DATE").substring(0, 10) : "");
			matchapply.setMatchApplyStartDate((rs.getString("MATCH_FROM_TIME") != null) ? rs.getString("MATCH_FROM_TIME") : "");
			matchapply.setMatchApplyEndDate((rs.getString("MATCH_TO_TIME") != null) ? rs.getString("MATCH_TO_TIME") : "");
			matchapply.setMatchDays((rs.getString("SET_MATCH_DAYS") != null) ? rs.getString("SET_MATCH_DAYS") : "");
			matchapply.setMatchTypeCode((rs.getString("MATCH_TYPE") != null) ? rs.getString("MATCH_TYPE") : "");
			matchapply.setMatchTypeCodeName((rs.getString("MATCH_TYPE_NAME") != null) ? rs.getString("MATCH_TYPE_NAME") : "");
			matchapply.setActivity(rs.getInt("ACTIVITY"));
			matchapply.setUpdatedBy(StringUtil.checkNull(rs.getString("updated_by")));
			// matchapply.setLeaveContent((rs.getString("LEAVE_REASON") != null)
			// ? rs.getString("LEAVE_REASON"):"");
			// 添加裁决人裁决情况列表
			if (applyType != null) {
				try {
					Logger.getLogger(getClass()).debug("set AffirmStatusList to matchapply matchapply.getMatchApplyNo() [" + matchapply.getMatchApplyNo() + "]");
					matchapply.setAffirmStatusList(getAffirmStatusList(applyType, matchapply.getMatchApplyNo()));
				} catch (Exception ex) {
					Logger.getLogger(getClass()).debug(ex.toString());
				}
			}
			// leaveapply.setAffirmStatusList()
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex);
		}
		return matchapply;
	}

	// 执行决裁动作，通过/不通过
	public void updateAffirmorFlag(String applyNo, String affirmorID, String applyType, String flag) {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE ESS_AFFIRM SET AFFIRM_FLAG = ? WHERE APPLY_NO = ? AND AFFIRMOR_ID = ? AND APPLY_TYPE = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, NumberUtil.parseInt(flag));
			ps.setInt(2, NumberUtil.parseInt(applyNo));
			ps.setString(3, affirmorID);
			ps.setString(4, applyType);
			ps.executeUpdate(); // 修改决裁信息表
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(ps, conn);
		}
	}

	public void tolerenceApplyAffirm(String TolerenceNo, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update ESS_TOLERENCE_APPLY " + "set activity=? " + "where TolerenceNo =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(flag));
			pstmt.setInt(2, NumberUtil.parseInt(TolerenceNo));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public List getTolerenceApplyAffirmList(PageControl pc, String sql) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " select * " + "from ESS_TOLERENCE_APPLY_V " + sql;
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			pc.seti();
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();

			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				list.add(this.TolerenceApply(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	private Tolerence TolerenceApply(ResultSet rs) {
		Tolerence tolerence = new Tolerence();
		try {
			tolerence.setTolerenceNo(rs.getString("TOLERENCE_NO") != null ? rs.getString("TOLERENCE_NO") : "");
			tolerence.setTolerenceEmpId(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
			tolerence.setChineseName((rs.getString("CHINESENAME") != null) ? rs.getString("CHINESENAME") : "");
			tolerence.setTolerenceFromTime((rs.getString("TOLERENCE_FROM_TIME") != null) ? rs.getString("TOLERENCE_FROM_TIME").substring(0, 10) : "");
			tolerence.setTolerenceApplyTime((rs.getString("TOLERENCE_APPLY_TIME") != null) ? rs.getString("TOLERENCE_APPLY_TIME").substring(0, 10) : "");
			tolerence.setTolerenceToTime((rs.getString("TOLERENCE_TO_TIME") != null) ? rs.getString("TOLERENCE_TO_TIME").substring(0, 10) : "");
			tolerence.setTolerenceTarget((rs.getString("TOLERENCE_TARGET") != null) ? rs.getString("TOLERENCE_TARGET") : "");
			tolerence.setCreateDate((rs.getString("CREATE_DATE") != null) ? rs.getString("CREATE_DATE").substring(0, 10) : "");
			tolerence.setActivity(rs.getInt("ACTIVITY"));
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return tolerence;
	}

	public Tolerence getTolerenceApplyAffirm(String tolerenceNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " select * from ESS_TOLERENCE_APPLY_V  where TOLERENCE_NO =? ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(tolerenceNo));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return this.TolerenceApply(rs);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return null;
	}

	public List getTolerenceContentAffirm(String tolerenceNo) {
		List list = new ArrayList();
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String SQL = "select * " + "from ESS_TOLERENCE_CONTENT  " + "where  TOLERENCE_NO = '" + tolerenceNo + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(SQL);
			while (rs.next()) {
				list.add(this.TolerenceApplyContent(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	private TolerenceContent TolerenceApplyContent(ResultSet rs) {
		TolerenceContent tolerenceContent = new TolerenceContent();
		try {
			tolerenceContent.setTolerenceNo(rs.getString("TOLERENCE_NO") != null ? rs.getString("TOLERENCE_NO") : "");
			tolerenceContent.setTolerenceTime(rs.getString("TOLERENCE_TIME") != null ? rs.getString("TOLERENCE_TIME") : "");
			tolerenceContent.setTolerencCompany((rs.getString("TOLERENCE_COMPANY") != null) ? rs.getString("TOLERENCE_COMPANY") : "");
			tolerenceContent.setTolerencPlace((rs.getString("TOLERENCE_CLIME") != null) ? rs.getString("TOLERENCE_CLIME") : "");
			tolerenceContent.setTolerencContent((rs.getString("TOLERENCE_CONTENT") != null) ? rs.getString("TOLERENCE_CONTENT") : "");
			tolerenceContent.setTolerencTransfer((rs.getString("TOLERENCE_TRANS") != null) ? rs.getString("TOLERENCE_TRANS") : "");
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return tolerenceContent;
	}

	public List getTolerenceFeeContentAffirm(String tolerenceNo) {
		List list = new ArrayList();
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String SQL = " select * from " + "ESS_TOLERENCE_FEE	" + "where	TOLERENCE_NO ='" + tolerenceNo + "'";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(SQL);
			while (rs.next()) {
				list.add(this.TolerenceApplyFeeContent(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	private TolerenceFeeContent TolerenceApplyFeeContent(ResultSet rs) {
		TolerenceFeeContent tolerenceFeeContent = new TolerenceFeeContent();
		try {
			tolerenceFeeContent.setTolerenceNo(rs.getString("TOLERENCE_NO") != null ? rs.getString("TOLERENCE_NO") : "");
			tolerenceFeeContent.setTolerencFeeTime(rs.getString("TOLERENCE_TIME") != null ? rs.getString("TOLERENCE_TIME") : "");
			tolerenceFeeContent.setTicketFee(rs.getString("TOLERENCE_TICKET_FEE") != null ? rs.getString("TOLERENCE_TICKET_FEE") : "");
			tolerenceFeeContent.setHouseFee((rs.getString("TOLERENCE_HOUSE_FEE") != null) ? rs.getString("TOLERENCE_HOUSE_FEE") : "");
			tolerenceFeeContent.setWorkFee((rs.getString("TOLERENCE_WORK_FEE") != null) ? rs.getString("TOLERENCE_WORK_FEE") : "");
			tolerenceFeeContent.setOtherFee((rs.getString("TOLERENCE_OTHER_FEE") != null) ? rs.getString("TOLERENCE_OTHER_FEE") : "");
			tolerenceFeeContent.setTicketCount((rs.getString("TOLERENCE_TICKET_COUNT") != null) ? rs.getString("TOLERENCE_TICKET_COUNT") : "");
			tolerenceFeeContent.setHouseCount((rs.getString("TOLERENCE_HOUSE_COUNT") != null) ? rs.getString("TOLERENCE_HOUSE_COUNT") : "");
			tolerenceFeeContent.setWorkCount((rs.getString("TOLERENCE_WORK_COUNT") != null) ? rs.getString("TOLERENCE_WORK_COUNT") : "");
			tolerenceFeeContent.setOtherCount((rs.getString("TOLERENCE_OTHER_COUNT") != null) ? rs.getString("TOLERENCE_OTHER_COUNT") : "");
			tolerenceFeeContent.setTicketFeeD((rs.getString("TOLERENCE_TICKET_FEE_D") != null) ? rs.getString("TOLERENCE_TICKET_FEE_D") : "");
			tolerenceFeeContent.setHouseFeeD((rs.getString("TOLERENCE_HOUSE_FEE_D") != null) ? rs.getString("TOLERENCE_HOUSE_FEE_D") : "");
			tolerenceFeeContent.setWorkFeeD((rs.getString("TOLERENCE_WORK_FEE_D") != null) ? rs.getString("TOLERENCE_WORK_FEE_D") : "");
			tolerenceFeeContent.setOtherFeeD((rs.getString("TOLERENCE_OTHER_FEE_D") != null) ? rs.getString("TOLERENCE_OTHER_FEE_D") : "");
			tolerenceFeeContent.setTicketCountD((rs.getString("TOLERENCE_TICKET_COUNT_D") != null) ? rs.getString("TOLERENCE_TICKET_COUNT_D") : "");
			tolerenceFeeContent.setHouseCountD((rs.getString("TOLERENCE_HOUSE_COUNT_D") != null) ? rs.getString("TOLERENCE_HOUSE_COUNT_D") : "");
			tolerenceFeeContent.setWorkCountD((rs.getString("TOLERENCE_WORK_COUNT_D") != null) ? rs.getString("TOLERENCE_WORK_COUNT_D") : "");
			tolerenceFeeContent.setOtherCountD((rs.getString("TOLERENCE_OTHER_COUNT_D") != null) ? rs.getString("TOLERENCE_OTHER_COUNT_D") : "");
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return tolerenceFeeContent;
	}

	public int deleteTolerence(String tolerenceNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_TOLERENCE_APPLY where TOLERENCE_NO = '" + tolerenceNo + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteTolerenceContent(String tolerenceNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_TOLERENCE_CONTENT where TOLERENCE_NO = '" + tolerenceNo + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteTolerenceFeeContent(String tolerenceNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_TOLERENCE_FEE where TOLERENCE_NO = '" + tolerenceNo + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteLeaveApply(String leaveApplyNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_LEAVE_APPLY_TB where LEAVE_NO = '" + leaveApplyNo.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteMatchApply(String matchApplyNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_MATCH_APPLY_TB where MATCH_NO = '" + matchApplyNo.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteOTApply(String otApplyno) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_APPLY_OT where APPLY_OT_NO ='" + otApplyno.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteFamily(String familyNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_FAMILY where FAMILY_NO ='" + familyNo.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deletePersonalInfo(String empid) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_PERSONAL_INFO where EMPID ='" + empid.trim().trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteQualification(String qualificationNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_QUALIFICATION where QUAL_NO ='" + qualificationNo.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteForeignLanguage(String foreignLanguageNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_LANGUAGE where LANGUAGE_NO ='" + foreignLanguageNo.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deletMilitaryServices(String militaryServicesNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_MILITARY_SERVICE where MILITARY_NO ='" + militaryServicesNo.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteHealth(String healthNo) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_HEALTH where HEALTH_NO ='" + healthNo + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	public int deleteEssAffirm(String applyNO, String applyType) {
		int affRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete ESS_AFFIRM where apply_no='" + applyNO.trim() + "' and apply_type ='" + applyType.trim() + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return affRow;
	}

	// 人事确认后,将记录存入 AR_APPLY_RESULT 表
	public int ApplyToAR(String applyNo, String applyType) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		if (applyType.equalsIgnoreCase("OTAPPLY")) {
			sql = "INSERT INTO AR_APPLY_RESULT" + " (APPLY_NO, EMPID, APPLY_DATE, APPLY_TYPE, FROM_TIME, TO_TIME, APPLY_DEDUCT)" + " SELECT ?, EMPID, APPLY_OT_DATE, APPLY_OT_TYPE_CODE," + " TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME, 'YYYY-MM-DD HH24:MI'),"
					+ " TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG, 'YYYY-MM-DD ') || OT_TO_TIME, 'YYYY-MM-DD HH24:MI')," + " OT_DEDUCT_TIME FROM ESS_APPLY_OT" + " WHERE APPLY_OT_NO = ?";
		} else if (applyType.equalsIgnoreCase("LEAVEAPPLY")) {
			sql = "INSERT INTO AR_APPLY_RESULT" + " (APPLY_NO, EMPID, APPLY_TYPE, FROM_TIME, TO_TIME)" + " SELECT ?, LEAVE_EMPID, LEAVE_TYPE, LEAVE_FROM_TIME, LEAVE_TO_TIME" + " FROM ESS_LEAVE_APPLY_TB WHERE LEAVE_NO = ?";
		} else if (applyType.equalsIgnoreCase("MATCHAPPLY")) {
			sql = "INSERT INTO AR_APPLY_RESULT" + " (APPLY_NO, EMPID, APPLY_TYPE, FROM_TIME, TO_TIME)" + " SELECT ?, MATCH_EMPID, MATCH_TYPE, MATCH_FROM_TIME, MATCH_TO_TIME" + " FROM ESS_MATCH_APPLY_TB WHERE MATCH_NO = ?";
		} else {
			Logger.getLogger(getClass()).debug("applyType Error.......");
			return result;
		}
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, NumberUtil.parseInt(applyNo));
			ps.setInt(2, NumberUtil.parseInt(applyNo));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(ps, con);
		}
		return result;
	}
}