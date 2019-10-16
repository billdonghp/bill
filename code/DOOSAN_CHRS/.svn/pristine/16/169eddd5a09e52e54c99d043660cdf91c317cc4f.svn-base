package com.ait.ess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class EssUtil {

	private static final Logger log = Logger.getLogger(EssUtil.class);

	/**综合考勤班次信息,取得休假申请中有效的休假长度
	 * 
	 * @param leaveNo	休假申请序号
	 * @return 休假小时数
	 */
	public static double getActualLeaveHour (int leaveNo) {
		double hours = 0.0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT LEAVE_EMPID, "
							+ "TO_CHAR(LEAVE_FROM_TIME, 'YYYY-MM-DD') LEAVE_FROM_DATE, "
							+ "TO_CHAR(LEAVE_TO_TIME, 'YYYY-MM-DD') LEAVE_TO_DATE, "
							+ "TO_CHAR(LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
							+ "TO_CHAR(LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
							+ "LEAVE_TYPE, "
							+ "APPLYLEAVETYPE "
						+ "FROM ESS_LEAVE_APPLY_TB "
						+ "WHERE LEAVE_NO = ?";
			log.debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, leaveNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String empId = rs.getString("LEAVE_EMPID");
				String fromTime = rs.getString("LEAVE_FROM_TIME");
				String toTime = rs.getString("LEAVE_TO_TIME");
				String leaveType= rs.getString("LEAVE_TYPE");
				String applyLeaveType= rs.getString("APPLYLEAVETYPE");
				log.debug("fromTime : " + fromTime.toString());
				log.debug("toTime : " + toTime.toString());
				hours = getActualLeaveHour(empId, fromTime, toTime,leaveType,applyLeaveType);
			}
		} catch (Exception e) {
			log.error(e.toString());
			throw new GlRuntimeException("取实际请假时间出错", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return hours;
	}
	/**取得休假小时数
	 * 
	 * @param leaveNo	休假申请序号
	 * @return 休假小时数
	 */
	public static double getActualLeaveLeftHour (int leaveNo) {
		double hours = 0.0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT LEAVE_EMPID, "
							+ "TO_CHAR(LEAVE_FROM_TIME, 'YYYY-MM-DD') LEAVE_FROM_DATE, "
							+ "TO_CHAR(LEAVE_TO_TIME, 'YYYY-MM-DD') LEAVE_TO_DATE, "
							+ "TO_CHAR(LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
							+ "TO_CHAR(LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME "
						+ "FROM ESS_LEAVE_APPLY_TB "
						+ "WHERE LEAVE_NO = ?";
			log.debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, leaveNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String empId = rs.getString("LEAVE_EMPID");
				String fromTime = rs.getString("LEAVE_FROM_TIME");
				String toTime = rs.getString("LEAVE_TO_TIME");
				log.debug("fromTime : " + fromTime.toString());
				log.debug("toTime : " + toTime.toString());				
				GregorianCalendar formatFrom = DateUtil.ParseGregorianCalendar(fromTime);
				GregorianCalendar formatTo = DateUtil.ParseGregorianCalendar(toTime);				
				hours=DateUtil.DateDiff(formatFrom, formatTo, "HOUR");
			}
		} catch (Exception e) {
			log.error(e.toString());
			throw new GlRuntimeException("取实际请假时间出错", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return hours;
	}

	/**综合考勤班次信息,取得休假申请中有效的休假长度
	 * 
	 * @param empId	休假员工号
	 * @param leaveFromTime 休假开始时间 (yyyy-MM-dd hh:mm)
	 * @param leaveFromTime 休假结束时间 (yyyy-MM-dd hh:mm)
	 * @return 休假小时数
	 */
	public static double getActualLeaveHour (String empId, String leaveFromTime, String leaveToTime,String leaveType,String applyLeaveType) {
		AdminBean admin =(AdminBean)ApplicationContext.getTheadLocal();
		double hours = 0.0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "SELECT ESS_GET_LEAVE_LENGTH_REPORT2(?,?,?,?,?,?) FROM DUAL";		
		log.debug(sql);
		log.debug("empId: " + empId);
		log.debug("leaveFromTime: " + leaveFromTime);
		log.debug("leaveToTime: " + leaveToTime);
		log.debug("leaveType: " + leaveType);
		log.debug("CpnyId: " + admin.getCpnyId());
		log.debug("leaveToTime: " + leaveToTime);
		log.debug("applyLeaveType: " + applyLeaveType);
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			pstmt.setString(2, leaveFromTime);
			pstmt.setString(3, leaveToTime);
			pstmt.setString(4, leaveType);
			pstmt.setString(5, applyLeaveType);
			pstmt.setString(3, leaveToTime);
			pstmt.setString(6, admin.getCpnyId());
			rs = pstmt.executeQuery();
			/*String normalWorkShortName = Configuration.getInstance().getString("/configuration/em2/ar-item-shortname/normal-work","");
			while (rs.next()) {
				sql = "SELECT TO_CHAR(AR_SHIFT020.BEGIN_DAY_OFFSET + "
					+ "TO_DATE(? || TO_CHAR(AR_SHIFT020.FROM_TIME, ' HH24:MI'), 'YYYY/MM/DD HH24:MI'), 'YYYY-MM-DD HH24:MI'), "
					+ "TO_CHAR(AR_SHIFT020.END_DAY_OFFSET + "
					+ "TO_DATE(? || TO_CHAR(AR_SHIFT020.TO_TIME, ' HH24:MI'), 'YYYY/MM/DD HH24:MI'), 'YYYY-MM-DD HH24:MI') "
					+ "FROM AR_SHIFT020, AR_ITEM "
					+ "WHERE AR_SHIFT020.AR_ITEM_NO = AR_ITEM.ITEM_NO "
					+ "AND AR_SHIFT020.SHIFT_NO = AR_GET_SHIFTNO(?, ?,?) ";
				log.debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rs.getString(1));
				pstmt.setString(2, rs.getString(1));
				pstmt.setString(3, empId);
				pstmt.setString(4, rs.getString(1));		
				pstmt.setString(5,admin.getCpnyId());		
				
				log.debug(" DDATE: "+rs.getString(1));
				log.debug(" DDATE: "+rs.getString(1));
				log.debug(" empId: "+empId);
				log.debug(" DDATE: "+rs.getString(1));
				log.debug(" normalWorkShortName: "+normalWorkShortName);
				
				ResultSet shiftRs = pstmt.executeQuery();*/
				while (rs.next()) {
					hours +=rs.getDouble(1);
					log.debug("hours : " + String.valueOf(hours));
				}
			
		} catch (Exception e) {
			log.error(e.toString());
			throw new GlRuntimeException("取实际请假时间出错", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return hours;
	}
	
}
