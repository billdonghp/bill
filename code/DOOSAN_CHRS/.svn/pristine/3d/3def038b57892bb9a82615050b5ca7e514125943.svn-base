package com.ait.ess.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.dao.AnnualBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.entity.LeaveApply;
import com.ait.ess.entity.MatchApply;
import com.ait.ess.entity.OTApply;
import com.ait.ess.entity.Supervisor;
import com.ait.ess.entity.Tolerence;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

public class EssDAO {

	//使用IBATIS
	private CommonSQLMapAdapter sqlMap = null;

	private static final Logger logger = Logger.getLogger(EssDAO.class);

	public EssDAO() {
		sqlMap = new CommonSQLMapAdapter("em2");
	}

	public void addOTApplyAffirmItem(OTApply otapply, String empid, String applyType) {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call ess_batch_ot_apply(?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?) }";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			cs.setString(1, empid);
			cs.setString(2, applyType);
			cs.setString(3, otapply.getOtDate());
			cs.setString(4, otapply.getOtStartTime());
			cs.setString(5, otapply.getOtEndTime());
			cs.setString(6, otapply.getOtWorkContent());
			cs.setString(7, otapply.getOtDeductTime());
			cs.setInt(8, otapply.getOtNextDays());
			cs.setString(9, otapply.getOtTypeCode());
			cs.setString(10, otapply.getCreatedBy());
			cs.execute();
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	/**
	 * deletePersonalInfo <br>
	 * before the insertion of a employee's personal infomation, the existing
	 * info should be deleted. @
	 */
	private void deletePersonalInfo(String empid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ESS_PERSONAL_INFO where empid='" + empid + "' ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}

	}

	public String getEssLeaveApplySeq() {
		String getSeq = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select ESS_LEAVEAPPLY_SEQ.nextval as LeaveApplySeq from dual ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				getSeq = rs.getString("LeaveApplySeq");
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return getSeq;
	}

	public String getEssMatchApplySeq() {
		String getSeq = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select ESS_MATCHAPPLY_SEQ.nextval as MatchApplySeq from dual ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				getSeq = rs.getString("MatchApplySeq");
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return getSeq;
	}

	public String getTolerenceApplySeq() {
		String tolerenceSeq = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select ESS_TOLERENCE_SEQ.nextval as TolerenceSeq from dual ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tolerenceSeq = rs.getString("TolerenceSeq");
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return tolerenceSeq;
	}

	public String getEssOTApplySeq() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " select ESS_OTAPPLY_SEQ.nextVal as OtApplySeq from dual ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("OtApplySeq") != null ? rs.getString("OtApplySeq") : "";
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return "";
	}

	public int addEssOTApply(OTApply OTapply, String[] empIDs) {
		int result = 0;
		int OtApplySeq = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int shiftDateType = 0;
		int calendarDateType = 0;
		double otHours = 0.0;
		double overtimedHours = 0.0;
		GregorianCalendar shiftFromTime = new GregorianCalendar();
		GregorianCalendar shiftToTime = new GregorianCalendar();
		String otTypeCode = null;
		String[] otTypeCodeA = null;
		String sql = "";
		String empID = "";
		List affirmlist = new ArrayList();
		String affirmorId = "";
		Logger.getLogger(getClass()).debug("OTDate : " + OTapply.getOtApplyDate().toString());
		try {
			conn = ConnBean.getConn();
			conn.setAutoCommit(false);
			/* 取日历日期性质 */
			sql = "SELECT OVERTYPEID FROM AR_CALENDER WHERE TO_CHAR(DDATE, 'YYYY-MM-DD') = ?";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, OTapply.getOtApplyDate());
			rs = pstmt.executeQuery();
			if (rs.next())
				calendarDateType = rs.getInt("OVERTYPEID");

			/* 取本次加班时间长度 */
			String[] otDateA = OTapply.getOtApplyDate().split("-");
			String[] otStartTimeA = OTapply.getOtStartTime().split(":");
			String[] otEndTimeA = OTapply.getOtEndTime().split(":");
			GregorianCalendar startTime = new GregorianCalendar(Integer.parseInt(otDateA[0]), Integer.parseInt(otDateA[1]) - 1, Integer.parseInt(otDateA[2]), Integer.parseInt(otStartTimeA[0]), Integer.parseInt(otStartTimeA[1]));
			GregorianCalendar endTime = new GregorianCalendar(Integer.parseInt(otDateA[0]), Integer.parseInt(otDateA[1]) - 1, Integer.parseInt(otDateA[2]), Integer.parseInt(otEndTimeA[0]), Integer.parseInt(otEndTimeA[1]));
			endTime.add(GregorianCalendar.DAY_OF_MONTH, OTapply.getOtNextDays());
			Logger.getLogger(getClass()).debug("startTime : [" + startTime.toString() + "] " + startTime.getTimeInMillis());
			Logger.getLogger(getClass()).debug("endTime : [" + endTime.toString() + "] " + endTime.getTimeInMillis());
			otHours = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000 / 60 / 60;
			Logger.getLogger(getClass()).debug("" + otHours);
			if (otHours <= 0) {
				result = 3;
				Logger.getLogger(getClass()).debug("开始结束时间错误");
				return result;
			}

			for (int i = 0; i < empIDs.length; i++) {
				/* 循环员工 */
				empID = empIDs[i];

				/* 判断当日是否已有加班记录 */
				sql = "SELECT * FROM ESS_APPLY_OT WHERE EMPID = ? AND TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD') = ? AND ACTIVITY <> '2'";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empID);
				pstmt.setString(2, OTapply.getOtApplyDate());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = 5;
					conn.rollback();
					Logger.getLogger(getClass()).debug("已有当日加班 [" + empID + "] " + OTapply.getOtApplyDate());
					return result;
				}

				/* 取班次日期性质 */
				sql = "SELECT AR_SHIFT010.DATATYPE FROM AR_SCHEDULE, AR_SHIFT010" + " WHERE AR_SCHEDULE.SHIFT_NO = AR_SHIFT010.SHIFT_NO" + " AND AR_SCHEDULE.EMPID = ? AND AR_SCHEDULE.AR_DATE_STR = ?";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empID);
				pstmt.setString(2, OTapply.getOtApplyDate().replaceAll("-", "/"));
				rs = pstmt.executeQuery();
				if (rs.next())
					shiftDateType = rs.getInt("DATATYPE");

				/* 取加班人本月已有加班数量 */
				sql = "SELECT SUM(QUANTITY) QUANTITY FROM AR_DETAIL" + " WHERE EMPID = ? AND AR_MONTH_STR = AR_GET_MONTHSTR(?)" + " AND (AR_ITEM_NO = 11 OR AR_ITEM_NO = 12 OR AR_ITEM_NO = 13)";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empID);
				pstmt.setString(2, OTapply.getOtApplyDate().replaceAll("-", "/"));
				rs = pstmt.executeQuery();
				if (rs.next())
					overtimedHours = rs.getDouble("QUANTITY");

				/* 综合判断 */
				if (OTapply.getMenuCode().equals("ess0209")) {
					/* 紧急加班 */
					if (otHours + overtimedHours > 36) {
						/* 加班超过36小时 */
						result = 4;
						conn.rollback();
						Logger.getLogger(getClass()).debug("紧急加班,超过36小时 [" + empID + "] " + OTapply.getOtApplyDate());
						return result;
					}
					if (shiftDateType == 0) {
						/* 非倒班人员 */
						switch (calendarDateType) {
						case 1:
							otTypeCode = "WorkingOtType04";
							break;
						case 2:
							otTypeCode = "WorkingOtType05";
							break;
						case 3:
							otTypeCode = "WorkingOtType06";
							break;
						}
					} else {
						/* 倒班人员 */
						if (calendarDateType == 3) {
							/* 节假日 */
							if (shiftDateType == 1) {
								/* 应该上班 -- 节假日加班 */
								otTypeCode = "WorkingOtType06";
							} else {
								/* 应该休息 -- 节假日加班 + 特勤加班 */
								otTypeCode = "WorkingOtType06+WorkingOtType05";
							}
						} else {
							/* 非节假日 */
							if (shiftDateType == 1) {
								/* 应该上班 -- 延时加班 */
								otTypeCode = "WorkingOtType04";
							} else {
								/* 应该休息 -- 特勤加班 */
								otTypeCode = "WorkingOtType05";
							}
						}
					}
				} else if (OTapply.getMenuCode().equals("ess0210")) {
					/* 超时加班 */
					if (otHours + overtimedHours <= 36) {
						/* 加班超过36小时 */
						result = 4;
						conn.rollback();
						Logger.getLogger(getClass()).debug("加班未超过36小时 [" + empID + "] " + OTapply.getOtApplyDate());
						return result;
					}
					if (shiftDateType == 0) {
						/* 非倒班人员 */
						switch (calendarDateType) {
						case 1:
							otTypeCode = "WorkingOtType07";
							break;
						case 2:
							otTypeCode = "WorkingOtType08";
							break;
						case 3:
							otTypeCode = "WorkingOtType09";
							break;
						}
					} else {
						/* 倒班人员 */
						if (calendarDateType == 3) {
							/* 节假日 */
							if (shiftDateType == 1) {
								/* 应该上班 -- 节假日加班 */
								otTypeCode = "WorkingOtType09";
							} else {
								/* 应该休息 -- 节假日加班 + 特勤加班 */
								otTypeCode = "WorkingOtType09+WorkingOtType08";
							}
						} else {
							/* 非节假日 */
							if (shiftDateType == 1) {
								/* 应该上班 -- 延时加班 */
								otTypeCode = "WorkingOtType07";
							} else {
								/* 应该休息 -- 特勤加班 */
								otTypeCode = "WorkingOtType08";
							}
						}
					}
				} else {
					/* 普通加班 */
					if (otHours + overtimedHours > 36) {
						/* 加班超过36小时 */
						result = 4;
						conn.rollback();
						Logger.getLogger(getClass()).debug("普通加班,超过36小时 [" + empID + "] " + OTapply.getOtApplyDate());
						Logger.getLogger(getClass()).debug("otHours : " + otHours);
						Logger.getLogger(getClass()).debug("overtimedHours : " + overtimedHours);
						return result;
					}
					if (shiftDateType == 0) {
						/* 非倒班人员 */
						switch (calendarDateType) {
						case 1:
							otTypeCode = "WorkingOtType01";
							break;
						case 2:
							otTypeCode = "WorkingOtType02";
							break;
						case 3:
							otTypeCode = "WorkingOtType03";
							break;
						}
					} else {
						/* 倒班人员 */
						if (calendarDateType == 3) {
							/* 节假日 */
							if (shiftDateType == 1) {
								/* 应该上班 -- 节假日加班 */
								otTypeCode = "WorkingOtType03";
							} else {
								/* 应该休息 -- 节假日加班 + 特勤加班 */
								otTypeCode = "WorkingOtType03-WorkingOtType02";
							}
						} else {
							/* 非节假日 */
							if (shiftDateType == 1) {
								/* 应该上班 -- 延时加班 */
								otTypeCode = "WorkingOtType01";
							} else {
								/* 应该休息 -- 特勤加班 */
								otTypeCode = "WorkingOtType02";
							}
						}
					}
				}

				Logger.getLogger(getClass()).debug("OverTypeID : " + otTypeCode);

				if (otTypeCode.equals("WorkingOtType01") || otTypeCode.equals("WorkingOtType04") || otTypeCode.equals("WorkingOtType07")) {
					/* 如果加班类型为延时,判断当日应出勤开始结束时间 */
					sql = "SELECT TO_DATE(? || TO_CHAR(AR_SHIFT020.FROM_TIME, ' HH24:MI'), 'YYYY-MM-DD HH24:MI') + AR_SHIFT020.BEGIN_DAY_OFFSET," + " TO_DATE(? || TO_CHAR(AR_SHIFT020.TO_TIME, ' HH24:MI'), 'YYYY-MM-DD HH24:MI') + AR_SHIFT020.END_DAY_OFFSET"
							+ " FROM AR_SHIFT010, AR_SHIFT020" + " WHERE AR_SHIFT010.SHIFT_NO = AR_SHIFT020.SHIFT_NO" + " AND AR_SHIFT010.SHIFT_NO = AR_GET_SHIFTNO(?, ?)" + " AND AR_SHIFT010.DATATYPE = 1";
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, OTapply.getOtApplyDate());
					pstmt.setString(2, OTapply.getOtApplyDate());
					pstmt.setString(3, empID);
					pstmt.setString(4, OTapply.getOtApplyDate().replaceAll("-", "/"));
					rs = pstmt.executeQuery();
					while (rs.next()) {
						shiftFromTime.setTime(rs.getTimestamp(1));
						shiftToTime.setTime(rs.getTimestamp(2));
						Logger.getLogger(getClass()).debug("rs.getDate(1) : " + rs.getDate(1).toString());
						Logger.getLogger(getClass()).debug("rs.getDate(2) : " + rs.getDate(2).toString());
						Logger.getLogger(getClass()).debug("shiftFromTime : " + shiftFromTime.toString());
						Logger.getLogger(getClass()).debug("shiftToTime : " + shiftToTime.toString());
						if (!(startTime.equals(shiftToTime) || startTime.after(shiftToTime) || endTime.equals(shiftFromTime) || endTime.before(shiftFromTime))) {
							result = 6;
							conn.rollback();
							Logger.getLogger(getClass()).debug("加班时间与班次时间冲突 [" + empID + "] " + OTapply.getOtApplyDate());
							return result;
						}
					}
				}

				if (otTypeCode == null) {
					result = 2;
					conn.rollback();
					Logger.getLogger(getClass()).debug("无法判断加班类型 [" + empID + "] " + OTapply.getOtApplyDate());
				} else {
					otTypeCodeA = otTypeCode.split("-");
					for (int j = 0; j < otTypeCodeA.length; j++) {
						sql = "SELECT ESS_OTAPPLY_SEQ.NEXTVAL FROM DUAL";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							OtApplySeq = rs.getInt(1);
						} else {
							result = 0;
							Logger.getLogger(getClass()).debug("取序列号失败? faint!");
							conn.rollback();
							return result;
						}
						sql = "INSERT INTO ESS_APPLY_OT (APPLY_OT_NO, EMPID, APPLY_OT_DATE, OT_FROM_TIME, OT_TO_TIME, APPLY_OT_REMARK," + " OT_DEDUCT_TIME, CREATE_DATE, APPLY_OT_FLAG, APPLY_OT_TYPE_CODE, CREATED_BY)"
								+ " VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, SYSDATE, ?, ?, ?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, OtApplySeq);
						pstmt.setString(2, empID);
						pstmt.setString(3, OTapply.getOtApplyDate());
						pstmt.setString(4, OTapply.getOtStartTime());
						pstmt.setString(5, OTapply.getOtEndTime());
						pstmt.setString(6, OTapply.getOtWorkContent());
						pstmt.setString(7, OTapply.getOtDeductTime());
						pstmt.setInt(8, OTapply.getOtNextDays());
						pstmt.setString(9, otTypeCodeA[j]);
						pstmt.setString(10, OTapply.getCreatedBy());
						if (pstmt.executeUpdate() == 1) {
							result = 1;
							Logger.getLogger(getClass()).debug("申请成功");
						} else {
							result = 0;
							Logger.getLogger(getClass()).debug("申请失败");
							conn.rollback();
							return result;
						}

						//从库中读取加班人的决裁者, 不再使用页面传入的值
						sql = "SELECT AFFIRMOR_ID FROM SY_AFFIRM_RELATION_TB " + "WHERE AFFIRM_TYPE_ID = 'OtApply' " + "AND AFFIRM_OBJECT = ? " + "ORDER BY AFFIRM_LEVEL";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, empID);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							Logger.getLogger(getClass()).debug("按个人读取决裁者");
							affirmlist.add(rs.getString("AFFIRMOR_ID"));
							while (rs.next())
								affirmlist.add(rs.getString("AFFIRMOR_ID"));
						} else {
							Logger.getLogger(getClass()).debug("按部门读取决裁者");
							sql = "SELECT SY_AFFIRM_RELATION_TB.AFFIRMOR_ID " + "FROM HR_EMPLOYEE, SY_AFFIRM_RELATION_TB " + "WHERE SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID = 'OtApply' " + "AND HR_EMPLOYEE.DEPTID = SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT " + "AND HR_EMPLOYEE.EMPID = ? "
									+ "ORDER BY SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, empID);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								affirmlist.add(rs.getString("AFFIRMOR_ID"));
								while (rs.next())
									affirmlist.add(rs.getString("AFFIRMOR_ID"));
							} else {
								result = 8;
								conn.rollback();
								Logger.getLogger(getClass()).debug("决裁者未定义");
								return result;
							}
						}

						/* 添加决裁者 */
						for (int k = 0; k < affirmlist.size(); k++) {
							affirmorId = StringUtil.checkNull((String) affirmlist.get(k));
							if (!affirmorId.equals("noperson")) {
								sql = "INSERT INTO ESS_AFFIRM (" + "ESS_AFFIRM_NO, " + "APPLY_NO, " + "AFFIRM_LEVEL, " + "AFFIRMOR_ID, " + "CREATE_DATE, " + "APPLY_TYPE" + ") VALUES (" + "ESS_AFFIRM_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 'OtApply')";
								Logger.getLogger(getClass()).debug(sql);
								pstmt = conn.prepareStatement(sql);
								pstmt.setInt(1, OtApplySeq);
								pstmt.setInt(2, k + 1);
								pstmt.setString(3, affirmorId);
								if (pstmt.executeUpdate() != 1) {
									result = 0;
									Logger.getLogger(getClass()).debug("决裁者" + affirmorId + "添加失败");
									conn.rollback();
									return result;
								}
							}
						}
					}
				}
			}
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				Logger.getLogger(getClass()).error(e.toString());
			}
			ex.printStackTrace();
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	public void addTolerenceApply(Tolerence tolerence, String empID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "insert into ESS_TOLERENCE_APPLY (TOLERENCE_NO, TOLERENCE_EMPID, TOLERENCE_APPLY_TIME,   " + "  TOLERENCE_FROM_TIME, TOLERENCE_TO_TIME, TOLERENCE_TARGET,   "
				+ "  CREATE_DATE,ACTIVITY) VALUES (?,?,SYSDATE,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,SYSDATE,0) ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, tolerence.getTolerenceNo());
			pstmt.setString(2, empID);
			pstmt.setString(3, tolerence.getTolerenceFromTime());
			pstmt.setString(4, tolerence.getTolerenceToTime());
			pstmt.setString(5, tolerence.getTolerenceTarget());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int addEssApplyAffirmor(String OtApplySeq, String[] affirmlist, String ApplyTypeCode) {
		int affRow = 0;
		int[] tmpArray;
		Connection conn = null;
		Statement state = null;
		String sql = null;
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			for (int i = 0; i < affirmlist.length; i++) {
				if (!affirmlist[i].equals("noperson")) {
					sql = "INSERT INTO ESS_AFFIRM (" + "ESS_AFFIRM_NO, " + "APPLY_NO, " + "AFFIRM_LEVEL, " + "AFFIRMOR_ID, " + "CREATE_DATE, " + "APPLY_TYPE" + ") VALUES (" + "ESS_AFFIRM_SEQ.NEXTVAL, " + "'" + OtApplySeq + "', " + "'" + (i + 1) + "', " + "'" + affirmlist[i]
							+ "', " + "SYSDATE, " + "'" + ApplyTypeCode + "') ";
					Logger.getLogger(getClass()).debug(sql);
					state.addBatch(sql);
				}// end for
			}// end if
			tmpArray = state.executeBatch();
			for (int i = 0; i < tmpArray.length; i++) {
				affRow += tmpArray[i];
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
		return affRow;
	}

	public List getEssSupervisorList(String empID) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from ESS_SUPERVISOR_GRANT_V where ar_supervisor_id = ? order by chinesename asc ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, empID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.createFamily(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	private Supervisor createFamily(ResultSet rs) {
		Supervisor supervisor = new Supervisor();
		try {
			supervisor.setEmpId((rs.getString("EMPID") != null) ? rs.getString("EMPID") : "");
			supervisor.setChineseName((rs.getString("CHINESENAME") != null) ? rs.getString("CHINESENAME") : "");
			supervisor.setDeptId((rs.getString("DEPTID") != null) ? rs.getString("DEPTID") : "");
			supervisor.setArSupervisorId((rs.getString("AR_SUPERVISOR_ID") != null) ? rs.getString("AR_SUPERVISOR_ID") : "");
			supervisor.setSupervisorName((rs.getString("AR_SUPERVISOR_NAME") != null) ? rs.getString("AR_SUPERVISOR_NAME") : "");
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return supervisor;
	}

	public int addEssLeaveApply(LeaveApply leaveapply, String empID) {
		int result = 0;
		Connection conn = null;
		Statement state = null;
		/* 添加年假检查 BY Pennix 20060412 */
		if (leaveapply.getLeaveTypeCode().equals("AH2")) {
			/* 如果申请类型为年假, 进行年假检查 */
			leaveapply.setLeaveDays(String.valueOf(Integer.parseInt(leaveapply.getLeaveDays()) * 8));
			AnnualBean annualBean = new AnnualBean();
			Annual annual = (Annual) annualBean.empAnnual(empID, leaveapply.getLeaveApplyStartDate().substring(0, leaveapply.getLeaveApplyStartDate().indexOf(" ")));
			Logger.getLogger(getClass()).debug("Leave Start Date : " + leaveapply.getLeaveApplyStartDate().substring(0, leaveapply.getLeaveApplyStartDate().indexOf(" ")));
			if (annual.getAnnualHoursLeft() <= Integer.parseInt(leaveapply.getLeaveDays())) {
				result = 2;
				Logger.getLogger(getClass()).debug(StringUtil.toISO("剩余年假数不足"));
				return result;
			}
		}
		String SQL = "INSERT INTO ESS_LEAVE_APPLY_TB(" + "LEAVE_NO, LEAVE_EMPID, APPLY_TIME, " + "LEAVE_TYPE, LEAVE_FROM_TIME, LEAVE_TO_TIME, " + "LEAVE_REASON, CREATE_DATE, ACTIVITY, " + "SET_LEAVE_DAYS, CREATED_BY) VALUES (" + "'" + leaveapply.getLeaveApplyNo() + "', " + "'"
				+ leaveapply.getLeavePerson() + "', " + "SYSDATE, " + "'" + leaveapply.getLeaveTypeCode() + "', " + "TO_DATE('" + leaveapply.getLeaveApplyStartDate() + "','YYYY-MM-DD HH24:MI:SS'), " + "TO_DATE('" + leaveapply.getLeaveApplyEndDate()
				+ "','YYYY-MM-DD HH24:MI:SS'), " + "'" + StringUtil.checkNull(leaveapply.getLeaveContent()) + "', " + "SYSDATE, " + "0, " + "'" + leaveapply.getLeaveDays() + "', " + "'" + leaveapply.getCreatedBy() + "'" + ") ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			if (state.executeUpdate(SQL) == 1) {
				result = 1;
				Logger.getLogger(getClass()).debug("申请成功, 请等待决裁者进行审批");
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
		return result;
	}

	public int addEssMatchApply(MatchApply matchapply, String empID) {
		int affRow = 0;
		Connection conn = null;
		Statement state = null;
		String SQL = "insert into ESS_MATCH_APPLY_TB(" + "MATCH_NO, MATCH_EMPID, APPLY_TIME, " + "MATCH_TYPE, MATCH_FROM_TIME, MATCH_TO_TIME, " + "CREATE_DATE, ACTIVITY, " + "created_by" + ")values(" + "'" + matchapply.getMatchApplyNo() + "', " + "'"
				+ matchapply.getMatchPerson() + "', " + "sysdate, " + "'" + matchapply.getMatchTypeCode() + "', " + "to_date('" + matchapply.getMatchApplyStartDate() + "','YYYY-MM-DD HH24:MI:SS'), " + "to_date('" + matchapply.getMatchApplyEndDate()
				+ "','YYYY-MM-DD HH24:MI:SS'), " + "sysdate, " + "0, " + "'" + matchapply.getCreatedBy() + "'" + ") ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			affRow = state.executeUpdate(SQL);
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
		return affRow;
	}

	public List getLeaveApplyList(String empID) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT " + "LEAVE_NO, LEAVE_EMPID, " + "he.chinesename, LEAVE_TYPE, " + "sy1.code_name as leave_type_name, " + "LEAVE_FROM_TIME, LEAVE_TO_TIME, " + "LEAVE_REASON, elat.CREATE_DATE " + "FROM ESS_LEAVE_APPLY_TB elat, " + "sy_code sy1, " + "hr_employee he "
				+ "where elat.LEAVE_TYPE = sy1.CODE_ID(+) " + "and elat.leave_empid = he.empid(+) " + "and LEAVE_EMPID='" + empID + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.createLeaveApply(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getMatchApplyList(String empID) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT " + "MATCH_NO, MATCH_EMPID, " + "he.chinesename, MATCH_TYPE, " + "sy1.code_name as match_type_name, " + "MATCH_FROM_TIME, MATCH_TO_TIME, " + "elat.CREATE_DATE " + "FROM ESS_MATCH_APPLY_TB elat, " + "sy_code sy1, " + "hr_employee he "
				+ "where elat.MATCH_TYPE = sy1.CODE_ID(+) " + "and elat.match_empid = he.empid(+) " + "and MATCH_EMPID='" + empID + "' ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.createMatchApply(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	private LeaveApply createLeaveApply(ResultSet rs) {
		LeaveApply leaveapply = new LeaveApply();
		try {
			leaveapply.setLeaveApplyNo((rs.getString("LEAVE_NO") != null) ? rs.getString("LEAVE_NO") : "");
			leaveapply.setLeavePerson((rs.getString("LEAVE_EMPID") != null) ? rs.getString("LEAVE_EMPID") : "");
			leaveapply.setLeaveApplyDate((rs.getString("CREATE_DATE") != null) ? rs.getString("CREATE_DATE") : "");
			leaveapply.setLeaveTypeCode((rs.getString("LEAVE_TYPE") != null) ? rs.getString("LEAVE_TYPE") : "");
			leaveapply.setLeaveDays((rs.getString("AR_SUPERVISOR_ID") != null) ? rs.getString("AR_SUPERVISOR_ID") : "");
			leaveapply.setLeaveApplyStartDate((rs.getString("LEAVE_FROM_TIME") != null) ? rs.getString("LEAVE_FROM_TIME") : "");
			leaveapply.setLeaveApplyEndDate((rs.getString("LEAVE_TO_TIME") != null) ? rs.getString("LEAVE_TO_TIME") : "");
			leaveapply.setLeaveContent((rs.getString("LEAVE_REASON") != null) ? rs.getString("LEAVE_REASON") : "");
			leaveapply.setLeaveTypeCodeName((rs.getString("leave_type_name") != null) ? rs.getString("leave_type_name") : "");
			leaveapply.setLeavePersonName((rs.getString("chinesename") != null) ? rs.getString("chinesename") : "");
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return leaveapply;
	}

	private MatchApply createMatchApply(ResultSet rs) {
		MatchApply matchapply = new MatchApply();
		try {
			matchapply.setMatchApplyNo((rs.getString("MATCH_NO") != null) ? rs.getString("MATCH_NO") : "");
			matchapply.setMatchPerson((rs.getString("MATCH_EMPID") != null) ? rs.getString("MATCH_EMPID") : "");
			matchapply.setMatchApplyDate((rs.getString("CREATE_DATE") != null) ? rs.getString("CREATE_DATE") : "");
			matchapply.setMatchTypeCode((rs.getString("MATCH_TYPE") != null) ? rs.getString("MATCH_TYPE") : "");
			matchapply.setMatchDays((rs.getString("AR_SUPERVISOR_ID") != null) ? rs.getString("AR_SUPERVISOR_ID") : "");
			matchapply.setMatchApplyStartDate((rs.getString("MATCH_FROM_TIME") != null) ? rs.getString("MATCH_FROM_TIME") : "");
			matchapply.setMatchApplyEndDate((rs.getString("MATCH_TO_TIME") != null) ? rs.getString("MATCH_TO_TIME") : "");
			// matchapply
			// .setLeaveContent((rs.getString("LEAVE_REASON") != null) ? rs
			// .getString("LEAVE_REASON")
			// : "");
			matchapply.setMatchTypeCodeName((rs.getString("match_type_name") != null) ? rs.getString("match_type_name") : "");
			matchapply.setMatchPersonName((rs.getString("chinesename") != null) ? rs.getString("chinesename") : "");
			// will define more attributes
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return matchapply;
	}

	public void addTolerenceContent(String TolerenceSeq, String[] tolerencTime, String[] tolerencTransfer, String[] tolerencPlace, String[] tolerencCompany, String[] tolerencContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = " insert into ESS_TOLERENCE_CONTENT(TOLERENCE_NO, TOLERENCE_TIME, TOLERENCE_TRANS, " + " TOLERENCE_CLIME, TOLERENCE_COMPANY, TOLERENCE_CONTENT)values(?,?,?,?,?,?) ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(TolerenceSeq));
			for (int i = 0; i < tolerencTime.length; i++) {
				if (!tolerencTime[i].equals("")) {
					pstmt.setString(2, tolerencTime[i]);
					pstmt.setString(3, tolerencTransfer[i]);
					pstmt.setString(4, StringUtil.toCN(tolerencPlace[i]));
					pstmt.setString(5, StringUtil.toCN(tolerencCompany[i]));
					pstmt.setString(6, StringUtil.toCN(tolerencContent[i]));
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void addTolerenceFeeContent(String TolerenceSeq, String[] tolerencFeeTime, String[] ticketFee, String[] houseFee, String[] workFee, String[] otherFee, String[] ticketCount, String[] houseCount, String[] workCount, String[] otherCount, String[] ticketFeeD,
			String[] houseFeeD, String[] workFeeD, String[] otherFeeD, String[] ticketCountD, String[] houseCountD, String[] workCountD, String[] otherCountD) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = " insert into ESS_TOLERENCE_FEE(TOLERENCE_NO, TOLERENCE_TIME, TOLERENCE_TICKET_FEE, " + " TOLERENCE_TICKET_COUNT, TOLERENCE_HOUSE_FEE, TOLERENCE_HOUSE_COUNT,   " + "  TOLERENCE_WORK_FEE, TOLERENCE_WORK_COUNT, TOLERENCE_OTHER_FEE, "
				+ "   TOLERENCE_OTHER_COUNT, TOLERENCE_TICKET_FEE_D, TOLERENCE_TICKET_COUNT_D,  " + "  TOLERENCE_HOUSE_FEE_D, TOLERENCE_HOUSE_COUNT_D, TOLERENCE_WORK_FEE_D,  " + "  TOLERENCE_WORK_COUNT_D, TOLERENCE_OTHER_FEE_D, TOLERENCE_OTHER_COUNT_D)  "
				+ "  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
		Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, NumberUtil.parseInt(TolerenceSeq));
			for (int i = 0; i < tolerencFeeTime.length; i++) {
				if (!tolerencFeeTime[i].equals("")) {
					pstmt.setString(2, tolerencFeeTime[i]);
					pstmt.setInt(3, NumberUtil.parseInt(ticketFee[i]));
					pstmt.setInt(4, NumberUtil.parseInt(houseFee[i]));
					pstmt.setInt(5, NumberUtil.parseInt(workFee[i]));
					pstmt.setInt(6, NumberUtil.parseInt(otherFee[i]));

					pstmt.setInt(7, NumberUtil.parseInt(ticketCount[i]));
					pstmt.setInt(8, NumberUtil.parseInt(houseCount[i]));
					pstmt.setInt(9, NumberUtil.parseInt(workCount[i]));
					pstmt.setInt(10, NumberUtil.parseInt(otherCount[i]));

					pstmt.setInt(11, NumberUtil.parseInt(ticketFeeD[i]));
					pstmt.setInt(12, NumberUtil.parseInt(houseFeeD[i]));
					pstmt.setInt(13, NumberUtil.parseInt(workFeeD[i]));
					pstmt.setInt(14, NumberUtil.parseInt(otherFeeD[i]));

					pstmt.setInt(15, NumberUtil.parseInt(ticketCountD[i]));
					pstmt.setInt(16, NumberUtil.parseInt(houseCountD[i]));
					pstmt.setInt(17, NumberUtil.parseInt(workCountD[i]));
					pstmt.setInt(18, NumberUtil.parseInt(otherCountD[i]));
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	/**
	 * 员工个人信息申请
	 * @param personal
	 */
	public void addEssPersonalInfo(Object personal) {
		try {
			sqlMap.startTransaction();
			sqlMap.delete("ess.apply.delEssPersonalInfo", personal);
			sqlMap.insert("ess.apply.insertEssPersonalInfo", personal);
			sqlMap.commitTransation();
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new GlRuntimeException("申请员工基本信息失败！", e);
		} finally {
			try {
				sqlMap.endTransation();
			} catch (SQLException e) {
				logger.error(e.toString());
				throw new GlRuntimeException("关闭连接　申请员工基本信息失败！", e);
			}
		}

	}

	/**
	 * 查讯个人信息申请
	 * @param personal
	 */
	public Object getEssPersonalInfo(Object paremterObject) {
		Object personal;
		try {
			personal = sqlMap.executeQuery("ess.apply.getEssPersonalInfo", paremterObject);
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得申请员工基本信息失败！", e);
		}
		return personal;
	}

	/**
	 * 查讯个人信息申请
	 * @param personal
	 */
	public List getEssPersonalInfoList(Object paremterObject, int currentPage, int pageSize) {
		List list;
		try {
			list = sqlMap.executeQueryForMulti("ess.apply.getEssPersonalInfoList", paremterObject, currentPage, pageSize);
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得申请员工基本信息失败！", e);
		}
		return list;
	}

	/**
	 * 查讯个人信息申请总数
	 * @param personal
	 */
	public Object getEssPersonalInfoListCnt(Object paremterObject) {
		Object object;
		try {
			object = sqlMap.executeQuery("ess.apply.getEssPersonalInfoListCnt", paremterObject);
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得申请员工基本信息记录数！", e);
		}
		return object;
	}

	/**
	 * 查讯个人信息申请未诀裁总数
	 * @param personal
	 */
	public Object getEssPersonalInfoCnt(Object paremterObject) {
		Object object;
		try {
			object = sqlMap.executeQuery("ess.apply.getEssPersonalInfoCnt", paremterObject);
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得申请员工基本信息未诀裁记录数！", e);
		}
		return object;
	}

	/**
	 * 个人信息申请决裁
	 * @param pessonal
	 */
	public void affimPersonalInfoApply(Object pessonal) {
		try {
			sqlMap.startTransaction();
			sqlMap.update("ess.apply.updateEssPersonalInfo", pessonal);
			sqlMap.delete("ess.apply.delHrPersonalInfo", pessonal);
			sqlMap.insert("ess.apply.insertHrPersonalInfoByApplyInfo", pessonal);
			sqlMap.update("ess.apply.updateHrPersonalInfoByApplyInfo", pessonal);
			sqlMap.commitTransation();
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new GlRuntimeException("决裁员工基本信息失败！", e);
		} finally {
			try {
				sqlMap.endTransation();
			} catch (SQLException e) {
				logger.error(e.toString());
				throw new GlRuntimeException("决裁员工基本信息失败关闭连接失败！", e);
			}
		}

	}

}
