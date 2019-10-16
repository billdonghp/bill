package com.ait.ar.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.ar.bean.CalendarDay;
import com.ait.ar.bean.Day;
import com.ait.ar.bean.EmpMonthWork;
import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

public class ArWorkCalendarBean {
	private DBConnection db = new DBConnection();

	private ResultSet rs = null;

	int fristDay = 0;

	private String loginID = null;

	// 设定登陆人ID
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	// 验证该员工是否在考勤员的权限内
	public boolean validate(String empID) throws Exception {
		boolean returnVal = false;
		String sql = "SELECT 1 " + "FROM DUAL " + "WHERE '" + empID + "' IN ("
				+ db.getArPopedom(loginID) + ")";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();

		rs = db.getResultSet(sql);
		if (rs.next()) {
			returnVal = true;
		}
		db.closeConnection();
		return returnVal;
	}

	public ArrayList getDay(String month_str) {
		ArrayList months = new ArrayList();
		String sql = "SELECT * " + "FROM AR_CALENDER "
				+ "WHERE GET_ARMONTH(DDATE)='" + month_str + "' "
				+ "ORDER BY DDATE";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		try {
			while (rs.next()) {
				Day day = new Day();
				day.setDayNumber(rs.getInt("iday"));
				day.setWeek(rs.getInt("iweek"));
				switch (rs.getInt("iweek")) {
				case 0:
					day.setWeekName("<font color='red'>日</font>");
					break;
				case 1:
					day.setWeekName("一");
					break;
				case 2:
					day.setWeekName("二");
					break;
				case 3:
					day.setWeekName("三");
					break;
				case 4:
					day.setWeekName("四");
					break;
				case 5:
					day.setWeekName("五");
					break;
				case 6:
					day.setWeekName("<font color='red'>六</font>");
					break;
				}
				months.add(day);
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		db.closeConnection();
		return months;
	}

	/**
	 * 取员工某月的日历
	 * 
	 * @Date 2006-04-27
	 * @author Pennix
	 * @param empId
	 * @param arMonth
	 * @return
	 */
	public ArrayList getEmpCalendar(String empId, String arMonth, String cpny_id) {
		empId = StringUtil.checkNull(empId);
		arMonth = StringUtil.checkNull(arMonth);
		ArrayList calendarList = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = ConnBean.getConn();
			if (empId.equals(loginID)) {
				// 员工查看自己的考勤
				sql = "SELECT AR_CALENDER.DDATE,"
						+ " AR_CALENDER.TYPEID,"
						+ " AR_CALENDER.OVERTYPEID,"
						+ " AR_CALENDER.WORKDAYFLAG,"
						+ " AR_CALENDER.SHIFT_NO,"
						+ " T_SCHEDULE.SCHEDULE_SHIFT_NO,"
						+ " T_SCHEDULE.DATATYPE"
						+ " FROM AR_CALENDER,"
						+ " (SELECT AR_SCHEDULE.SHIFT_NO SCHEDULE_SHIFT_NO,"
						+ " AR_SCHEDULE.AR_DATE_STR,"
						+ " AR_SHIFT010.DATATYPE"
						+ " FROM AR_SCHEDULE, AR_SHIFT010"
						+ " WHERE AR_SCHEDULE.SHIFT_NO = AR_SHIFT010.SHIFT_NO"
						+ " AND AR_SCHEDULE.EMPID = ?) T_SCHEDULE"
						+ " WHERE TO_CHAR(AR_CALENDER.DDATE, 'YYYY/MM/DD') = T_SCHEDULE.AR_DATE_STR(+)"
						+ " AND AR_CALENDER.DDATE BETWEEN AR_GET_STARTDATE_EMPID(?,?) AND AR_GET_ENDDATE_EMPID(?,?) and Cpny_Id=? "
						+ " ORDER BY AR_CALENDER.DDATE";
				ps = conn.prepareStatement(sql);
				ps.setString(1, empId);
				ps.setString(2, arMonth);
				ps.setString(3, empId);
				ps.setString(4, arMonth);
				ps.setString(5, empId);
				ps.setString(6, cpny_id);
				
			} else {
				// 验证是否有考勤权限
				sql = "SELECT HR_EMPLOYEE.EMPID"
						+ " FROM HR_EMPLOYEE, AR_SUPERVISOR_INFO"
						+ " WHERE HR_EMPLOYEE.person_id = ? AND HR_EMPLOYEE.ACTIVITY = 1 "
						+ " AND HR_EMPLOYEE.DEPTID = AR_SUPERVISOR_INFO.SUPERVISED_DEPTID and HR_EMPLOYEE.cpny_id= ? "
						+ " AND AR_SUPERVISOR_INFO.AR_SUPERVISOR_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, empId);
				ps.setString(2, cpny_id);
				ps.setString(3, loginID);
				rs = ps.executeQuery();
				sql = "";
				if (rs.next()) {
					sql = "SELECT AR_CALENDER.DDATE,"
							+ " AR_CALENDER.TYPEID,"
							+ " AR_CALENDER.OVERTYPEID,"
							+ " AR_CALENDER.WORKDAYFLAG,"
							+ " AR_CALENDER.SHIFT_NO,"
							+ " T_SCHEDULE.SCHEDULE_SHIFT_NO,"
							+ " T_SCHEDULE.DATATYPE"
							+ " FROM AR_CALENDER,"
							+ " (SELECT AR_SCHEDULE.SHIFT_NO SCHEDULE_SHIFT_NO,"
							+ " AR_SCHEDULE.AR_DATE_STR,"
							+ " AR_SHIFT010.DATATYPE"
							+ " FROM AR_SCHEDULE, HR_EMPLOYEE, AR_SUPERVISOR_INFO, AR_SHIFT010"
							+ " WHERE AR_SCHEDULE.SHIFT_NO = AR_SHIFT010.SHIFT_NO"
							+ " AND AR_SCHEDULE.EMPID = HR_EMPLOYEE.PERSON_ID"
							+ " AND HR_EMPLOYEE.DEPTID = AR_SUPERVISOR_INFO.SUPERVISED_DEPTID"
							+ " AND HR_EMPLOYEE.PERSON_ID = ?"
							+ " AND AR_SUPERVISOR_INFO.AR_SUPERVISOR_ID = ?) T_SCHEDULE"
							+ " WHERE TO_CHAR(AR_CALENDER.DDATE, 'YYYY/MM/DD') = T_SCHEDULE.AR_DATE_STR(+)"
							+ " AND AR_CALENDER.DDATE BETWEEN AR_GET_STARTDATE_EMPID(?,?) AND AR_GET_ENDDATE_EMPID(?,?)  and Cpny_Id=? "
							+ " ORDER BY AR_CALENDER.DDATE";
					ps = conn.prepareStatement(sql);
					ps.setString(1, empId);
					ps.setString(2, loginID);
					ps.setString(3, arMonth);
					ps.setString(4, empId);
					ps.setString(5, arMonth);
					ps.setString(6, empId);
					ps.setString(7, cpny_id);
				}
			}
			Logger.getLogger(getClass()).debug(sql);
			Logger.getLogger(getClass()).debug("empId = " + empId);
			Logger.getLogger(getClass()).debug("loginID = " + loginID);
			Logger.getLogger(getClass()).debug("arMonth = " + arMonth);
			Logger.getLogger(getClass()).debug("empId = " + empId);
			Logger.getLogger(getClass()).debug("arMonth = " + arMonth);
			Logger.getLogger(getClass()).debug("empId = " + empId);
			Logger.getLogger(getClass()).debug("cpny_id = " + cpny_id);
			if (!sql.equals("")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					CalendarDay calendarDay = new CalendarDay();
					calendarDay.setCalendarDay(rs.getDate("DDATE"));
					calendarDay.setDayTypeId(rs.getInt("TYPEID"));
					calendarDay.setOverTypeId(rs.getInt("OVERTYPEID"));
					calendarDay.setWorkDayFlag(rs.getInt("WORKDAYFLAG"));
					calendarDay.setShiftNo(rs.getInt("SHIFT_NO"));
					if (rs.getString("SCHEDULE_SHIFT_NO") != null) {
						calendarDay.setShiftNo(rs.getInt("SCHEDULE_SHIFT_NO"));
						// 日历来源:0为取日历,1为取排班
						calendarDay.setCalendarType(1);
						// 非法定假
						if (calendarDay.getDayTypeId() != 3) {
							// 取排班表中对应的班次的班次性质
							calendarDay.setDayTypeId(rs.getInt("DATATYPE"));
							if (calendarDay.getDayTypeId() == 1) {
								// 设置为平时加班,工作状态
								calendarDay.setOverTypeId(1);
								calendarDay.setWorkDayFlag(1);
							} else {
								// 设置为周末加班,休息状态
								calendarDay.setOverTypeId(2);
								calendarDay.setWorkDayFlag(0);
							}
						}
					}
					calendarList.add(calendarDay);
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, ps, conn);
		}
		return calendarList;
	}

	/**
	 * 修改员工班次信息
	 * 
	 * @param request
	 */
	public void modEmpCalendar(HttpServletRequest request) {
		String dates[] = request.getParameterValues("days");
		String empid = request.getParameter("person_id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		String sql = "";
		try {

			if (dates == null)
				return;

			conn = JdbcTemplate.getTemplate().getConnection();
			for (int i = 0; i < dates.length; i++) {
				// 删除旧班次
				sql = "DELETE FROM AR_SCHEDULE WHERE EMPID = ? AND ar_date_str = ?";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empid);
				pstmt.setString(2, dates[i]);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
				// 添加新班次
				sql = "INSERT INTO AR_SCHEDULE "
						+ "(PK_NO, EMPID, SHIFT_NO, AR_DATE_STR, LOCK_YN) "
						+ "VALUES " + "(Ar_Schedule_Seq.NEXTVAL, ?, ?, ?, 'N')";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empid);
				pstmt.setString(2, request.getParameter("shifts_" + dates[i]));
				pstmt.setString(3, dates[i]);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
				/*
				// 重算考勤明细
				sql = "{CALL AR_DETAIL_CALCULATE(?, ?, ?)}";
				cstmt = conn.prepareCall(sql);
				cstmt.setString(1, empid);
				cstmt.setString(2, dates[i]);
				cstmt.setString(3, "");
				Logger.getLogger(getClass()).debug(
						"Calculating ArDetail of [" + dates[i] + "] .....");
				cstmt.execute();
				cstmt.close();
				cstmt = null;
				*/
				
			}
			conn.close() ;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("修改员工班次失败", e);
		}
	}

	/**
	 * 删除员工班次信息
	 * 
	 * @param request
	 */
	public void delEmpCalendar(HttpServletRequest request) {
		String dates[] = request.getParameterValues("days");
		String empid = request.getParameter("person_id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		String sql = "";
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			for (int i = 0; i < dates.length; i++) {
				// 删除旧班次
				sql = "DELETE FROM AR_SCHEDULE WHERE EMPID = ? AND ar_date_str = ?";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empid);
				pstmt.setString(2, dates[i]);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
				
				/*
				// 重算考勤明细
				sql = "{CALL AR_DETAIL_CALCULATE(?, ?, ?)}";
				cstmt = conn.prepareCall(sql);
				cstmt.setString(1, empid);
				cstmt.setString(2, dates[i]);
				cstmt.setString(3, "");
				Logger.getLogger(getClass()).debug(
						"Calculating ArDetail of [" + dates[i] + "] .....");
				cstmt.execute();
				cstmt.close();
				cstmt = null;
				*/
			}
			conn.close() ;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("删除员工班次失败", e);
		}
	}

	public String getCalender(String empID, String month_str) throws Exception {
		if (month_str.length() != 6)
			return null;

		ArrayList months = this.getDay(month_str);
		if (months.size() <= 0)
			return null;
		int out = 0;// 去掉第1行 显示出几个的个数
		String temp = null;// 中间字符串
		String frist = "";// 第1行TABLE
		String Calender = "";// 返回的日历TABLE
		months = this.getEmpMonthWork(month_str, empID, months);
		frist = this.getfristTable(months);// 得到第1行TABLE
		temp = frist.substring(frist.lastIndexOf("*") + 1, frist.length());
		frist = frist.substring(0, frist.lastIndexOf("*"));
		out = new Integer(temp).intValue();// 已经添加了几天
		Calender += frist;
		Calender += this.geteverTable(out, months);// /得到剩余的TABLE
		Logger.getLogger(getClass()).debug(Calender);
		return Calender;

	}

	public String getfristTable(ArrayList days) throws Exception {// 得到第1行Table
		Day day = null;
		Day day1 = null;
		String table = "";
		int out = 0;
		day = (Day) days.get(0);// 得到第1天
		if (day.getWeek() == 0) {
			for (int j = 0; j < 7; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}
				table += "</table>";
				table += "</td>";
			}
			out = 7;
		}
		//
		if (day.getWeek() == 1) {
			table += "<td>&nbsp;</td>";
			for (int j = 0; j < 6; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}

				table += "</table>";
				table += "</td>";

			}
			out = 6;
		}
		//
		if (day.getWeek() == 2) {
			table += "<td>&nbsp;</td><td>&nbsp;</td>";
			for (int j = 0; j < 5; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}

				table += "</table>";
				table += "</td>";

			}
			out = 5;
		}
		//
		if (day.getWeek() == 3) {
			table += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
			for (int j = 0; j < 4; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}

				table += "</table>";
				table += "</td>";

			}
			out = 4;
		}
		//
		if (day.getWeek() == 4) {
			table += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
			for (int j = 0; j < 3; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}

				table += "</table>";
				table += "</td>";

			}
			out = 3;
		}
		//
		if (day.getWeek() == 5) {
			table += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
			for (int j = 0; j < 2; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}

				table += "</table>";
				table += "</td>";

			}
			out = 2;
		}
		//
		if (day.getWeek() == 6) {
			table += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
			for (int j = 0; j < 1; j++) {
				day1 = (Day) days.get(j);
				table += "<td valign='top'>";
				table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
				table += "<tr><td height='5' valign='top' class='fsize'>"
						+ day1.getDayNumber() + "</td></tr>";
				if (day1.getDescription().equals("无")) {
					table += "<tr><td height='40' align='center'><a href='#' >"
							+ day1.getDescription() + "</a></td></tr>";
				} else {
					// temp = "edit" + day1.getDayNumber();
					table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
							+ day1.getDayNumber()
							+ "')>"
							+ day1.getDescription() + "</a></td></tr>";
				}

				table += "</table>";
				table += "</td>";

			}
			out = 1;
		}
		return "<tr align='center'>" + table + "</tr>*" + out;
	}

	public String geteverTable(int out, ArrayList months) throws Exception {
		int r = 0;
		int rows = 0;
		// String temp1 = null;
		if ((months.size() - out) % 7 != 0) {// 算出 剩余的有几行
			rows = (months.size() - out) / 7 + 1;
		} else {
			rows = (months.size() - out) / 7;
		}
		rows = rows * 7;// 总共多少格子
		rows = rows - (months.size() - out);// 到最后一行剩余几格子
		String table = "";
		Day day = null;
		for (int j = out; j < months.size(); j++) {
			day = (Day) months.get(j);
			if (r == 0 || r == 7 || r == 14 || r == 21 || r == 28) {// 加换行
				table += "<tr>";
			}
			table += "<td valign='top'>";
			table += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
			table += "<tr><td height='5' valign='top' class='fsize'>"
					+ day.getDayNumber() + "</td></tr>";
			if (day.getDescription().equals("无")) {
				table += "<tr><td height='40' align='center'><a href='#' >"
						+ day.getDescription() + "</a></td></tr>";
			} else {
				// temp1 = "edit" + day.getDayNumber();
				table += "<tr><td height='40' align='center'><a href='#' onMouseDown=find('"
						+ day.getDayNumber()
						+ "')>"
						+ day.getDescription()
						+ "</a></td></tr>";
			}

			table += "</table>";
			table += "</td>";

			if (r == 6 || r == 13 || r == 20 || r == 27 || r == 34) {// 加换行
				table += "</tr>";
			}
			r += 1;
		}
		// 补空格
		if (rows > 0) {
			String temp = "";
			for (int i = 0; i < rows; i++) {
				temp += "<td>&nbsp;</td>";
			}
			table += temp + "</tr>";
		}
		return table;
	}

	//
	public String getDiv(int day, ArrayList workType, ArrayList hours,
			ArrayList starHours) throws Exception {
		if (workType.size() != hours.size())
			return null;
		String div = "";
		int width = 0;
		int residual = 480;
		int sum = 0;
		float temp = 0;
		int sum1 = 0;
		String fontColor = null;
		div += "<div id='day"
				+ day
				+ "' style=' left:0px; top:0px; z-index:1; background-color: #FFFFEC; layer-background-color: #FFFFEC; border: 1px double #000000; overflow: hidden; visibility: hidden;' onmouseout='hideSelf(this)'>";
		// get div
		div += "<table width='480' border='1' cellpadding='4' cellspacing='0' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF'>";
		div += "<tr align='center' bgcolor='#F5F5F5'>";
		div += "<td>03</td><td>06</td><td>09</td><td>12</td>";
		div += "<td>15</td><td>18</td><td>21</td><td>24</td>";
		div += "<td>03</td><td>06</td><td>09</td><td>12</td>";
		div += "<td>15</td><td>18</td><td>21</td><td>24</td>";
		div += "</tr></table>";
		// 1相素等于6分钟
		div += "<table width='480' border='0' cellspacing='0' cellpadding='0' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF'>";
		div += "<tr>";
		for (int i = 0; i < hours.size(); i++) {
			if (((String) workType.get(i)).equals("常白班")) {
				fontColor = "#FFFFCC";
			}
			if (((String) workType.get(i)).equals("夜2")) {
				fontColor = "#FFCCFF";
			}
			if (((String) workType.get(i)).equals("夜3")) {
				fontColor = "#CCCCFF";
			}

			if (((String) workType.get(i)).equals("白2")) {
				fontColor = "#99CCFF";
			}
			if (((String) workType.get(i)).equals("白3")) {
				fontColor = "#99CC99";
			}
			if (((String) workType.get(i)).equals("值夜")) {
				fontColor = "#00CCFF";
			}
			if (((String) workType.get(i)).equals("休息")) {
				fontColor = "#666699";
			}
			sum1 = ((Integer) starHours.get(i)).intValue();
			sum1 = sum1 * 60 / 6;
			width = sum1;
			sum += width;
			if (width < residual) {// 当前的宽度小于剩余的宽度
				div += "<td width='" + width + "'><font size='-1'></font></td>";
			}
			residual = residual - width;
			temp = ((Float) hours.get(i)).floatValue();
			temp = temp * 60 / 6;// 算出应该占多宽
			width = (int) temp;// 给宽
			sum += width;// 得到总共加了TD的宽度

			if (width < residual) {// 当前的宽度小于剩余的宽度
				div += "<td width='" + width + "' bgcolor='" + fontColor
						+ "'><font size='-1'>" + (String) workType.get(i)
						+ "</font></td>";
			}
			if (width > residual) {// 当前的宽度大于剩余的宽度
				div += "<td width='" + residual + "' bgcolor='" + fontColor
						+ "'><font size='-1'>" + (String) workType.get(i)
						+ "...</font></td>";
			}
			if (i == hours.size() - 1) {
				if ((480 - sum) > 0) {
					div += "<td width='" + (480 - sum) + "'></td>";
				}
			}
			residual = 480 - sum; // 得到剩余的宽度
		}
		div += "</tr>";
		div += "</table>";
		div += "</div>";
		return div;

	}

	/**
	 * getEmpMonthWork
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param empID
	 *            String
	 * @return ArrayList
	 */
	public ArrayList getEmpMonthWork(String month_str, String empID,
			ArrayList months) throws Exception {
		if (month_str == null || month_str == "" || empID == null
				|| empID == "")
			return null;
		String temp = "select * from AR_CALENDER_SHIFT_V  where  AR_MONTH_STR='"
				+ month_str + "'";
		temp += " order by ar_date_str asc";
		String sql = "select * from ar_daily_v where empID='" + empID
				+ "' and ar_month_str='" + month_str + "'";
		sql += " order by ar_date_str asc";
		// sql += " and ar_daily_v.EMPID in ("+db.getArPopedom(loginID)+")";
		// /*联合考勤员权限*/
		ArrayList empWork;
		ArrayList empWork_1;
		ArrayList empWork_2;
		empWork_1 = (ArrayList) this.getCalendarResult(sql, empID);
		if (this.whetherAllDate(empWork_1, months)) {
			empWork = empWork_1;
		} else {
			sql = this.getCalendarSql(month_str, empID);
			empWork_2 = (ArrayList) this.getCalendarResult(sql, empID);
			empWork_1 = (ArrayList) this.fillUndefineCalender(empWork_1,
					empWork_2);
			if (this.whetherAllDate(empWork_1, months)) {
				empWork = empWork_1;
			} else {
				if (sql.equals(temp)) {
					empWork = empWork_1;
				} else {
					sql = temp;
					empWork_2 = (ArrayList) this.getCalendarResult(sql, empID);
					empWork_1 = (ArrayList) this.fillUndefineCalender(
							empWork_1, empWork_2);
					empWork = empWork_1;
				}
			}
		}

		Day d_day = null;
		EmpMonthWork e_emp = null;
		for (int j = 0; j < months.size(); j++) {
			d_day = (Day) months.get(j);
			for (int x = 0; x < empWork.size(); x++) {
				e_emp = (EmpMonthWork) empWork.get(x);
				d_day.setStarHours(e_emp.getStarHour());
				if (e_emp.getDay() == d_day.getDayNumber()) {

					d_day.setDescription(e_emp.getDescription());
					d_day.setHours(e_emp.getHours());
					break;
				}
			}
		}
		return months;
	}

	/**
	 * return sql for search work calendar
	 * 
	 * @param month_str
	 *            String
	 * @param empID
	 *            String
	 * @return String
	 */
	private String getCalendarSql(String month_str, String empID) {
		ResultSet result;
		Vector vector = new Vector();
		String sql = null;
		String sql_1 = "SELECT DISTINCT GROUP_ID  "
				+ "FROM AR_CALENDER_GROUP_SHIFT_V " + "WHERE AR_MONTH_STR = '"
				+ month_str + "'";
		Logger.getLogger(getClass()).debug(sql_1);
		try {
			db.getConnectionOracle();
			rs = db.getResultSet(sql_1); // 取得在month_str月份中存在数据的动态组ID
			while (rs.next()) {
				vector.add(rs.getString("GROUP_ID"));
			}
			db.closeConnection();

			db.getConnectionOracle();
			for (int i = 0; i < vector.size(); i++) { /* 通过动态组ID得到查询该组人员的SQL语句 */
				int groupID = Integer.parseInt((String) vector.elementAt(i));
				String sql_2 = "select ar_get_dynamicgroup_numbers('" + groupID
						+ "') from dual";
				Logger.getLogger(getClass()).debug(sql_2);
				rs = db.getResultSet(sql_2);
				rs.next();
				String sql_3 = rs.getString(1);
				sql_3 = "select empID  from  (" + sql_3 + ") where empID ='"
						+ empID + "'"; // 判断该人员是否在该动态组中
				Logger.getLogger(getClass()).debug(sql_3);
				result = db.getResultSet(sql_3);
				if (result.next()) {
					result.close();
					sql = "select * " + "from AR_CALENDER_GROUP_SHIFT_V "
							+ "where AR_MONTH_STR='" + month_str + "' "
							+ "and GROUP_ID='" + groupID + "' "
							+ "order by ar_date_str asc";
					break;
				}
			}
			db.closeConnection();

			if (sql == null) {
				sql = "select * from AR_CALENDER_SHIFT_V  where  AR_MONTH_STR='"
						+ month_str + "'";
				sql += " order by ar_date_str asc";
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		Logger.getLogger(getClass()).debug("return sql is : " + sql);
		return sql;
	}

	/**
	 * return calendar result by sql
	 * 
	 * @param sql
	 *            String
	 * @param empID
	 *            String
	 * @param months
	 *            ArrayList
	 * @return List
	 */
	private List getCalendarResult(String sql, String empID) {
		List list = null;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String temp = null;
		int i = 0;
		String day = null;
		String month = null;
		try {
			Logger.getLogger(getClass()).debug(sql);
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			list = new ArrayList();
			while (rs.next()) {
				EmpMonthWork emp = new EmpMonthWork();
				emp.setEmpID(empID);
				emp.setHours(rs.getInt("hours"));
				emp.setDescription(rs.getString("ar_name") == null ? "无" : rs
						.getString("ar_name"));
				month = rs.getString("ar_month_str");
				temp = month.substring(0, 4) + "/";
				month = month.substring(month.length() - 2, month.length());
				temp += month + "/";
				month = this.outZero(month);
				emp.setMonth(Integer.parseInt(month));
				day = rs.getString("ar_date_str");
				day = day.substring(day.lastIndexOf("/") + 1, day.length());
				temp += day;
				day = this.outZero(day);
				emp.setStarHour(getStarHours(empID, temp, conn));
				emp.setDay(Integer.parseInt(day));
				list.add(emp);
				if (i == 0)
					fristDay = Integer.parseInt(day);
				i += 1;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	/**
	 * 判断是否包含显示月中的每一天的数据
	 * 
	 * @param list
	 *            ArrayList
	 * @param months
	 *            ArrayList
	 * @return boolean
	 */
	private boolean whetherAllDate(ArrayList list, ArrayList months) {
		EmpMonthWork e_emp;
		Day d_day = null;
		int flag = 0;
		for (int j = 0; j < months.size(); j++) {
			d_day = (Day) months.get(j);
			for (int k = 0; k < list.size(); k++) {
				e_emp = (EmpMonthWork) list.get(k);
				if (d_day.getDayNumber() == e_emp.getDay()) {
					flag = 1;
					break;
				}
			}
			if (flag != 1)
				break;
			if (j < months.size() - 1)
				flag = 0;
		}
		if (flag == 1)
			return true;
		else
			return false;
	}

	/**
	 * fill null calender
	 * 
	 * @param empWork
	 *            ArrayList
	 * @param empWorkFill
	 *            ArrayList
	 * @return List
	 */
	private List fillUndefineCalender(ArrayList empWork, ArrayList empWorkFill) {
		EmpMonthWork e_emp;
		EmpMonthWork e_emp_fill;
		ArrayList empWorkCalender = empWork;
		int size = empWorkCalender.size();
		int flag = 0;
		for (int i = 0; i < empWorkFill.size(); i++) {
			e_emp_fill = (EmpMonthWork) empWorkFill.get(i);
			flag = 0;
			for (int j = 0; j < size; j++) {
				e_emp = (EmpMonthWork) empWork.get(j);
				if (e_emp_fill.getDay() == e_emp.getDay()) {
					flag = 1;
					break;
				}
			}
			if (flag != 1)
				empWorkCalender.add(e_emp_fill);
		}
		return empWorkCalender;
	}

	/**
	 * getGroupCalender
	 * 
	 * @return String
	 */
	public String getGroupCalender(String month_str) throws Exception {
		if (month_str.length() != 6)
			return null;
		ArrayList months = this.getDay(month_str);
		if (months.size() <= 0)
			return null;
		Day day = null;
		String groupcalender = "";
		groupcalender += "<table width='100%' border='1' cellspacing='0' cellpadding='0' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF' style='padding: 2px 2px 2px 2px;'>";
		groupcalender += "<tr bgcolor='#F5F5F5'>";
		groupcalender += "<td>序号</td>";
		groupcalender += "<td>工号</td>";
		groupcalender += "<td>姓名</td>";
		for (int i = 0; i < months.size(); i++) {
			day = (Day) months.get(i);
			groupcalender += "<td>" + day.getDayNumber() + "</td>";
		}
		ArrayList parent_item = this.getArItemTitle();
		int itemSum = 0;
		for (int i = 0; i < parent_item.size(); i++) {
			itemSum = this.getSum("AR_ITEM_TITLE_V", "parent_item",
					(String) parent_item.get(i));
			groupcalender += "<td colspan='" + itemSum + "' align='center'>"
					+ (String) parent_item.get(i) + "</td>";
		}
		groupcalender += "<td>休假</td>";
		groupcalender += "<td>旷工</td>";
		groupcalender += "<td>迟到</td>";
		groupcalender += "<td>早退</td>";
		groupcalender += "<td>本人</td>";
		groupcalender += "</tr>";
		groupcalender += "<tr bgcolor='#F5F5F5'>";
		groupcalender += "<td>&nbsp;</td>";
		groupcalender += "<td>&nbsp;</td>";
		groupcalender += "<td>&nbsp;</td>";
		for (int i = 0; i < months.size(); i++) {
			day = (Day) months.get(i);
			groupcalender += "<td>" + day.getWeekName() + "</td>";
		}
		ArrayList parItem = null;
		for (int i = 0; i < parent_item.size(); i++) {
			parItem = this.getItem((String) parent_item.get(i));
			for (int j = 0; j < parItem.size(); j++) {
				groupcalender += "<td>" + (String) parItem.get(j) + "</td>";
			}
		}
		groupcalender += "<td>全天</td>";
		groupcalender += "<td>&nbsp;</td>";
		groupcalender += "<td>&nbsp;</td>";
		groupcalender += "<td>&nbsp;</td>";
		groupcalender += "<td>&nbsp;</td>";
		groupcalender += "</tr></table>";
		return groupcalender;
	}

	public int getSum(String table, String col, String values) throws Exception {
		db.getConnectionOracle();
		String sql = "select count(*) as fsum from " + table + " where " + col
				+ "='" + values + "'";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		int flag = 0;
		if (rs.next()) {
			flag = rs.getInt("fsum");
		}
		db.closeConnection();
		return flag;
	}

	//
	public int getSum_schedule(String ar_date, String month_str, String empID)
			throws Exception {
		db.getConnectionOracle();
		String sql = "select count(*) as fsum from ar_schedule where empID='"
				+ empID + "' and ar_get_monthstr(ar_date_str)='" + month_str
				+ "' and ar_date_str='" + ar_date + "'";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		int flag = 0;
		if (rs.next()) {
			flag = rs.getInt("fsum");
		}
		db.closeConnection();
		return flag;
	}

	//
	public int getSum_detail(String ar_date, String month_str, String empID)
			throws Exception {
		db.getConnectionOracle();
		String sql = "select count(*) as fsum from ar_detail where empID='"
				+ empID + "' and ar_month_str='" + month_str
				+ "' and ar_date_str='" + ar_date + "'";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		int flag = 0;
		if (rs.next()) {
			flag = rs.getInt("fsum");
		}
		db.closeConnection();
		return flag;
	}

	//
	public int getSum_records(String ar_date, String empID) throws Exception {
		db.getConnectionOracle();
		String sql = "select count(*) as fsum from ar_records where empID='"
				+ empID + "' and ar_date_str='" + ar_date + "'";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		int flag = 0;
		if (rs.next()) {
			flag = rs.getInt("fsum");
		}
		db.closeConnection();
		return flag;
	}

	public ArrayList getItem(String item_name) throws Exception {
		ArrayList item = new ArrayList();
		db.getConnectionOracle();
		String sql = "select * from  AR_ITEM_TITLE_V where parent_item='"
				+ item_name + "'";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		while (rs.next()) {
			item.add(rs.getString("short_name"));
		}
		db.closeConnection();
		return item;
	}

	public ArrayList getArItemTitle() throws Exception {
		ArrayList parent_item = new ArrayList();
		db.getConnectionOracle();
		String sql = "select distinct parent_item from AR_ITEM_TITLE_V";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		while (rs.next()) {
			parent_item.add(rs.getString("parent_item"));
		}
		db.closeConnection();
		return parent_item;
	}

	//
	//
	// getShiftItem
	public String getShiftItem(int item_no) throws Exception {
		ResultSet rs1 = null;
		String item_name = null;
		String sql = "select short_name from ar_item where item_no=" + item_no;
		Logger.getLogger(getClass()).debug(sql);
		rs1 = db.getResultSet(sql);
		if (rs1.next()) {
			item_name = rs1.getString("short_name");
		}
		rs1.close();
		return item_name;
	}

	//
	//
	public float getMinUnit(int item_no) throws Exception {
		ResultSet rs2 = null;
		String minUnit = null;
		String sql = "select min_unit from ar_item where item_no=" + item_no;
		Logger.getLogger(getClass()).debug(sql);
		rs2 = db.getResultSet(sql);
		if (rs2.next()) {
			minUnit = rs2.getString("min_unit");
		}
		rs2.close();
		return new Float(minUnit).floatValue();
	}

	//
	//
	public String getEditDiv(int day, String month_str, String empID)
			throws Exception {
		String str = null;
		String ar_date = null;
		String editDiv = "";
		String check = null;
		// int fromYear = 0;
		// int fromMonth = 0;
		// int toYear = 0;
		// int toMonth = 0;
		int fromDay = 0;
		int toDay = 0;
		int checkDay = 0;
		int fromHours = 0;
		int toHours = 0;
		int fromMinute = 0;
		int toMinute = 0;
		int checkMinute = 0;
		int checkHours = 0;
		int sum = 0;
		int PK_NO;
		float round = 0;
		float minUnit = 0;
		String from_time = null;
		String to_time = null;
		String lock = null;
		int x = 0;
		ArrayList s_day = new ArrayList();
		s_day.add("当日");
		s_day.add("次日");
		s_day.add("三日");
		// int s_d = 0;

		str = Integer.toString(day);
		if (day < 10) {
			str = this.insertZero(str);
		}
		ar_date = month_str + str;
		ar_date = this.conversionDate(ar_date);
		editDiv += "<form name='editForm" + day + "' method='post'>";
		editDiv += "<div id='edit"
				+ day
				+ "' style='position:absolute; left:0px; top:0px; z-index:1; background-color: #FFFFEC; layer-background-color: #FFFFEC; border: 1px double #000000; overflow: hidden;'>";
		editDiv += "<table width='480' border='1'  align='center' cellpadding='0' cellspacing='0' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF' style='padding: 2px 2px 2px 2px;'>";
		sum = this.getSum_schedule(ar_date, month_str, empID);
		editDiv += "<tr><td colspan='11' align='center' >" + day
				+ "日</td></tr>";
		editDiv += "<tr>";
		editDiv += "<td width='101' rowspan='" + sum
				+ "' align='center'>日历</td>";
		// 考勤日历开始
		String sql = "select AR_SCHEDULE.*, FROM_TIME, TO_TIME from ar_schedule, AR_SHIFT020 where empID='"
				+ empID
				+ "' AND AR_SCHEDULE.SHIFT_NO = AR_SHIFT020.SHIFT_NO and ar_get_monthstr(ar_date_str)='"
				+ month_str
				+ "' and ar_date_str='"
				+ ar_date
				+ "' order by FROM_TIME";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {

			x += 1;
			if (x > 1) {
				editDiv += "<tr>";
			}
			PK_NO = rs.getInt("PK_NO");
			editDiv += "<td width='42'>"
					+ this.getShiftItem(rs.getInt("ar_item_no")) + "</td>";
			editDiv += "<td width='66'><select name='spaceHours_c" + PK_NO
					+ "' size='1' onchange=changespTime('spaceHours_c" + PK_NO
					+ "','from_time_c" + PK_NO + "','to_time_c" + PK_NO
					+ "','tospaceDay_c" + PK_NO + "','fromspaceDay_c" + PK_NO
					+ "')>";
			from_time = rs.getString("ar_date_str");
			// fromYear = this.get_Year(from_time);
			// fromMonth = this.get_Month(from_time);
			fromDay = this.get_Day(from_time);
			fromHours = this.get_Hours(from_time);
			fromMinute = this.get_Minute(from_time);
			to_time = rs.getString("to_time");
			// toYear = this.get_Year(to_time);
			// toMonth = this.get_Month(to_time);
			toDay = this.get_Day(to_time);
			toHours = this.get_Hours(to_time);
			toMinute = this.get_Minute(to_time);
			checkDay = toDay - fromDay;// 得到相差天数
			minUnit = this.getMinUnit(rs.getInt("ar_item_no"));// 得到最小单位
			if (toMinute < fromMinute) {// 结束分小于开始分18：20==00：10
				if (checkDay == 0) {
					checkHours = toHours - fromHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
			}
			if (toMinute > fromMinute) {// 结束分大于开始分
				if (checkDay == 0) {
					checkHours = toHours - fromHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
			}
			if (toMinute == fromMinute) {//
				if (checkDay == 0) {
					checkHours = toHours - fromHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
			}

			round = (float) checkMinute / (float) 60;
			// 不同的“四舍五入” 1： 四舍五入||0.5：0.25入
			round = this.Round(minUnit, round);
			// checkHours=toHours-fromHours;//得到想差时间

			lock = rs.getString("lock_yn");
			if (lock == null) {// 判断是否被锁
				lock = "N";
			}

			for (float i = 0; i <= 72; i += minUnit) {
				check = "";
				if (i == (float) round) {
					check = "selected";
				}
				editDiv += "<option value='" + i + "' " + check + ">" + i
						+ "</option>";
			}
			editDiv += "</select></td>";
			editDiv += "<td width='54'><select name='fromspaceDay_c" + PK_NO
					+ "' onchange=changespDay('" + minUnit + "','spaceHours_c"
					+ PK_NO + "','from_time_c" + PK_NO + "','to_time_c" + PK_NO
					+ "','tospaceDay_c" + PK_NO + "','fromspaceDay_c" + PK_NO
					+ "')>";
			editDiv += "<option value=''>[请选择]</option>";

			if (fromDay < day) {// 8/9
				editDiv += "<option value='" + fromDay
						+ "' selected>作日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>当日</option>";
				editDiv += "<option value='" + (fromDay + 2) + "'>次日</option>";
				editDiv += "<option value='" + (fromDay + 3) + "'>三日</option>";
			}
			if (fromDay == day) {
				editDiv += "<option value='" + (fromDay - 1) + "'>作日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>当日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>次日</option>";
				editDiv += "<option value='" + (fromDay + 2) + "'>三日</option>";

			}
			if (fromDay > day && fromDay - day == 1) {// 10 9
				editDiv += "<option value='" + (fromDay - 2) + "'>作日</option>";
				editDiv += "<option value='" + (fromDay - 1) + "'>当日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>次日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>三日</option>";
			}
			if (fromDay > day && fromDay - day == 2) {
				editDiv += "<option value='" + (fromDay - 3) + "'>作日</option>";
				editDiv += "<option value='" + (fromDay - 2) + "'>当日</option>";
				editDiv += "<option value='" + (fromDay - 1) + "'>次日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>三日</option>";
			}

			editDiv += "</select></td>";
			editDiv += "<td width='22'>";
			editDiv += "<table width='22' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='20'><img src='../images/up2.gif' name='Image3113' width='20' height='10' border='0' id='Image31' onClick=changeTime(1,1,'from_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td><img src='../images/down2.gif' name='Image21' width='20' height='10' border='0' id='Image21' onClick=changeTime(1,2,'from_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			from_time = from_time.substring(from_time.indexOf(" ") + 1,
					from_time.lastIndexOf(":"));
			from_time = from_time.trim();
			editDiv += "<td width='35'><input name='from_time_c" + PK_NO
					+ "' type='text' size='5' value=" + from_time
					+ " readonly='true'></td>";
			editDiv += "<td width='23'><table width='23' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='21' align='left'><img src='../images/up2.gif' name='Image311' width='20' height='10' border='0' id='Image31' onClick=changeTime(2,1,'from_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td align='left'><img src='../images/down2.gif' name='Image211' width='20' height='10' border='0' id='Image21' onClick=changeTime(2,2,'from_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			// /////////
			editDiv += "<td width='54'><select name='tospaceDay_c" + PK_NO
					+ "' onchange=changespDay('" + minUnit + "','spaceHours_c"
					+ PK_NO + "','from_time_c" + PK_NO + "','to_time_c" + PK_NO
					+ "','tospaceDay_c" + PK_NO + "','fromspaceDay_c" + PK_NO
					+ "')>";
			editDiv += "<option value=''>[请选择]</option>";
			if (toDay == day) {
				editDiv += "<option value='" + toDay + "' selected>当日</option>";
				editDiv += "<option value='" + (toDay + 1) + "'>次日</option>";
				editDiv += "<option value='" + (toDay + 2) + "'>三日</option>";

			}
			if (toDay > day && toDay - day == 1) {// 10 9
				editDiv += "<option value='" + (toDay - 1) + "'>当日</option>";
				editDiv += "<option value='" + toDay + "' selected>次日</option>";
				editDiv += "<option value='" + (toDay + 1) + "'>三日</option>";
			}
			if (toDay > day && toDay - day == 2) {
				editDiv += "<option value='" + (toDay - 2) + "'>当日</option>";
				editDiv += "<option value='" + (toDay - 1) + "'>次日</option>";
				editDiv += "<option value='" + toDay + "' selected>三日</option>";
			}

			editDiv += "</select></td>";
			editDiv += "<td width='22'>";
			editDiv += "<table width='22' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='20'><img src='../images/up2.gif' name='Image3113' width='20' height='10' border='0' id='Image31' onClick=changeTime(1,1,'to_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td><img src='../images/down2.gif' name='Image21' width='20' height='10' border='0' id='Image21' onClick=changeTime(1,2,'to_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			to_time = to_time.substring(to_time.indexOf(" ") + 1, to_time
					.lastIndexOf(":"));
			to_time = to_time.trim();
			editDiv += "<td width='35'><input name='to_time_c" + PK_NO
					+ "' type='text' size='5' value='" + to_time
					+ "' readonly='true'></td>";
			editDiv += "<td width='23'><table width='23' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='21' align='left'><img src='../images/up2.gif' name='Image311' width='20' height='10' border='0' id='Image31' onClick=changeTime(2,1,'to_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "');></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td align='left'><img src='../images/down2.gif' name='Image211' width='20' height='10' border='0' id='Image21' onClick=changeTime(2,2,'to_time_c"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_c"
					+ PK_NO
					+ "','from_time_c"
					+ PK_NO
					+ "','to_time_c"
					+ PK_NO
					+ "','tospaceDay_c"
					+ PK_NO
					+ "','fromspaceDay_c" + PK_NO + "');></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			editDiv += "</tr>";

			// //////日历结束
		}
		db.closeConnection();
		editDiv += "<tr>";
		editDiv += "</tr>";
		// /////考勤明细开始
		x = 0;
		sum = this.getSum_detail(ar_date, month_str, empID);
		db.getConnectionOracle();
		sql = "select * from ar_detail where empID='" + empID
				+ "' and ar_month_str='" + month_str + "' and ar_date_str='"
				+ ar_date + "' order by from_time";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		while (rs.next()) {
			x += 1;
			if (x == 1) {
				editDiv += "<td rowspan='" + sum + "' align='center'>明细</td>";
			}
			if (x > 1) {
				editDiv += "<tr>";
			}
			PK_NO = rs.getInt("PK_NO");

			editDiv += "<td width='42'>" + this.getShiftItem(1) + "</td>";
			editDiv += "<td width='66'><select name='spaceHours_mx" + PK_NO
					+ "' size='1' onchange=changespTime('spaceHours_mx" + PK_NO
					+ "','from_time_mx" + PK_NO + "','to_time_mx" + PK_NO
					+ "','tospaceDay_mx" + PK_NO + "','fromspaceDay_mx" + PK_NO
					+ "')>";
			from_time = rs.getString("from_time");
			fromDay = this.get_Day(from_time);
			fromHours = this.get_Hours(from_time);
			fromMinute = this.get_Minute(from_time);
			to_time = rs.getString("to_time");
			toDay = this.get_Day(to_time);
			toHours = this.get_Hours(to_time);
			toMinute = this.get_Minute(to_time);

			checkDay = toDay - fromDay;// 得到相差天数
			minUnit = this.getMinUnit(rs.getInt("ar_item_no"));// 得到最小单位
			if (toMinute < fromMinute) {// 结束分小于开始分18：20==00：10
				if (checkDay == 0) {
					checkHours = toHours - fromHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
			}
			if (toMinute > fromMinute) {// 结束分大于开始分
				if (checkDay == 0) {
					checkHours = toHours - fromHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
			}
			if (toMinute == fromMinute) {//
				if (checkDay == 0) {
					checkHours = toHours - fromHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
			}

			round = (float) checkMinute / (float) 60;
			// 不同的“四舍五入” 1： 四舍五入||0.5：0.25入
			round = this.Round(minUnit, round);

			lock = rs.getString("lock_yn");
			if (lock == null) {// 判断是否被锁
				lock = "N";
			}

			for (float i = 0; i <= 48; i += minUnit) {
				check = "";
				if (i == (float) round) {
					check = "selected";
				}
				editDiv += "<option value='" + i + "' " + check + ">" + i
						+ "</option>";
			}
			editDiv += "</select></td>";
			editDiv += "<td width='54'><select name='fromspaceDay_mx" + PK_NO
					+ "' onchange=changespDay('" + minUnit + "','spaceHours_mx"
					+ PK_NO + "','from_time_mx" + PK_NO + "','to_time_mx"
					+ PK_NO + "','tospaceDay_mx" + PK_NO + "','fromspaceDay_mx"
					+ PK_NO + "')>";
			editDiv += "<option value=''>[请选择]</option>";
			if (fromDay < day) {// 8/9
				editDiv += "<option value='" + fromDay
						+ "' selected>作日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>当日</option>";
				editDiv += "<option value='" + (fromDay + 2) + "'>次日</option>";
				editDiv += "<option value='" + (fromDay + 3) + "'>三日</option>";
			}
			if (fromDay == day) {
				editDiv += "<option value='" + (fromDay - 1) + "'>作日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>当日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>次日</option>";
				editDiv += "<option value='" + (fromDay + 2) + "'>三日</option>";

			}
			if (fromDay > day && fromDay - day == 1) {// 10 9
				editDiv += "<option value='" + (fromDay - 2) + "'>作日</option>";
				editDiv += "<option value='" + (fromDay - 1) + "'>当日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>次日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>三日</option>";
			}
			if (fromDay > day && fromDay - day == 2) {
				editDiv += "<option value='" + (fromDay - 3) + "'>作日</option>";
				editDiv += "<option value='" + (fromDay - 2) + "'>当日</option>";
				editDiv += "<option value='" + (fromDay - 1) + "'>次日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>三日</option>";
			}

			editDiv += "</select></td>";
			editDiv += "<td width='22'>";
			editDiv += "<table width='22' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='20'><img src='../images/up2.gif' name='Image3113' width='20' height='10' border='0' id='Image31' onClick=changeTime(1,1,'from_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td><img src='../images/down2.gif' name='Image21' width='20' height='10' border='0' id='Image21' onClick=changeTime(1,2,'from_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			from_time = from_time.substring(from_time.indexOf(" ") + 1,
					from_time.lastIndexOf(":"));
			from_time = from_time.trim();
			editDiv += "<td width='35'><input name='from_time_mx" + PK_NO
					+ "' type='text' size='5' value=" + from_time
					+ " readonly='true'></td>";
			editDiv += "<td width='23'><table width='23' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='21' align='left'><img src='../images/up2.gif' name='Image311' width='20' height='10' border='0' id='Image31' onClick=changeTime(2,1,'from_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td align='left'><img src='../images/down2.gif' name='Image211' width='20' height='10' border='0' id='Image21' onClick=changeTime(2,2,'from_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			// /////////
			editDiv += "<td width='54'><select name='tospaceDay_mx" + PK_NO
					+ "' onchange=changespDay('" + minUnit + "','spaceHours_mx"
					+ PK_NO + "','from_time_mx" + PK_NO + "','to_time_mx"
					+ PK_NO + "','tospaceDay_mx" + PK_NO + "','fromspaceDay_mx"
					+ PK_NO + "')>";
			editDiv += "<option value=''>[请选择]</option>";
			if (toDay == day) {
				editDiv += "<option value='" + toDay + "' selected>当日</option>";
				editDiv += "<option value='" + (toDay + 1) + "'>次日</option>";
				editDiv += "<option value='" + (toDay + 2) + "'>三日</option>";

			}
			if (toDay > day && toDay - day == 1) {// 10 9
				editDiv += "<option value='" + (toDay - 1) + "'>当日</option>";
				editDiv += "<option value='" + toDay + "' selected>次日</option>";
				editDiv += "<option value='" + (toDay + 1) + "'>三日</option>";
			}
			if (toDay > day && toDay - day == 2) {
				editDiv += "<option value='" + (toDay - 2) + "'>当日</option>";
				editDiv += "<option value='" + (toDay - 1) + "'>次日</option>";
				editDiv += "<option value='" + toDay + "' selected>三日</option>";
			}

			editDiv += "</select></td>";
			editDiv += "<td width='22'>";
			editDiv += "<table width='22' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='20'><img src='../images/up2.gif' name='Image3113' width='20' height='10' border='0' id='Image31' onClick=changeTime(1,1,'to_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td><img src='../images/down2.gif' name='Image21' width='20' height='10' border='0' id='Image21' onClick=changeTime(1,2,'to_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			to_time = to_time.substring(to_time.indexOf(" ") + 1, to_time
					.lastIndexOf(":"));
			to_time = to_time.trim();
			editDiv += "<td width='35'><input name='to_time_mx" + PK_NO
					+ "' type='text' size='5' value='" + to_time
					+ "' readonly='true'></td>";
			editDiv += "<td width='23'><table width='23' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='21' align='left'><img src='../images/up2.gif' name='Image311' width='20' height='10' border='0' id='Image31' onClick=changeTime(2,1,'to_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "');></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td align='left'><img src='../images/down2.gif' name='Image211' width='20' height='10' border='0' id='Image21' onClick=changeTime(2,2,'to_time_mx"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_mx"
					+ PK_NO
					+ "','from_time_mx"
					+ PK_NO
					+ "','to_time_mx"
					+ PK_NO
					+ "','tospaceDay_mx"
					+ PK_NO
					+ "','fromspaceDay_mx" + PK_NO + "');></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			editDiv += "</tr>";
			// 考勤明细结束
			/*
			 * editDiv+=""; editDiv+=""; editDiv+=""; editDiv+=""; editDiv+="";
			 * editDiv+="";
			 */

		}
		db.closeConnection();
		// /考勤明细结束
		editDiv += "<tr></tr>";
		// 刷卡时间开始
		x = 0;
		sum = this.getSum_records(ar_date, empID);
		db.getConnectionOracle();
		sql = "select * from ar_records where empID='" + empID
				+ "' and ar_date_str='" + ar_date + "' order by enter_time";
		Logger.getLogger(getClass()).debug(sql);
		rs = db.getResultSet(sql);
		while (rs.next()) {
			x += 1;
			if (x == 1) {
				editDiv += "<td rowspan='" + sum + "' align='center'>刷卡时间</td>";
			}
			if (x > 1) {
				editDiv += "<tr>";
			}
			PK_NO = rs.getInt("record_no");

			editDiv += "<td width='42'></td>";
			editDiv += "<td width='66'><select name='spaceHours_sk" + PK_NO
					+ "' size='1' onchange=changespTime('spaceHours_sk" + PK_NO
					+ "','from_time_sk" + PK_NO + "','to_time_sk" + PK_NO
					+ "','tospaceDay_sk" + PK_NO + "','fromspaceDay_sk" + PK_NO
					+ "')>";
			from_time = rs.getString("enter_time");
			fromDay = this.get_Day(from_time);
			fromHours = this.get_Hours(from_time);
			fromMinute = this.get_Minute(from_time);
			to_time = rs.getString("out_time");
			toDay = this.get_Day(to_time);
			toHours = this.get_Hours(to_time);
			toMinute = this.get_Minute(to_time);

			checkDay = toDay - fromDay;// 得到相差天数
			// minUnit=this.getMinUnit(rs.getInt("ar_item_no"));//得到最小单位
			minUnit = new Float("0.5").floatValue();
			if (toMinute < fromMinute) {// 结束分小于开始分18：20==00：10
				if (checkDay == 0) {
					checkHours = toHours - fromHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours - 1;
					checkMinute = 60 - fromMinute + toMinute;
					checkMinute += checkHours * 60;
				}
			}
			if (toMinute > fromMinute) {// 结束分大于开始分
				if (checkDay == 0) {
					checkHours = toHours - fromHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
			}
			if (toMinute == fromMinute) {//
				if (checkDay == 0) {
					checkHours = toHours - fromHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 1) {
					checkHours = 24 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
				if (checkDay == 2) {
					checkHours = 48 - fromHours + toHours;
					checkMinute = toMinute - fromMinute;
					checkMinute += checkHours * 60;
				}
			}
			round = (float) checkMinute / (float) 60;
			// 不同的“四舍五入” 1： 四舍五入||0.5：0.25入
			round = this.Round(minUnit, round);

			lock = rs.getString("lock_yn");
			if (lock == null) {// 判断是否被锁
				lock = "N";
			}

			for (float i = 0; i <= 48; i += minUnit) {
				check = "";
				if (i == (float) round) {
					check = "selected";
				}
				editDiv += "<option value='" + i + "' " + check + ">" + i
						+ "</option>";
			}
			editDiv += "</select></td>";
			editDiv += "<td width='54'><select name='fromspaceDay_sk" + PK_NO
					+ "' onchange=changespDay('" + minUnit + "','spaceHours_sk"
					+ PK_NO + "','from_time_sk" + PK_NO + "','to_time_sk"
					+ PK_NO + "','tospaceDay_sk" + PK_NO + "','fromspaceDay_sk"
					+ PK_NO + "')>";
			editDiv += "<option value=''>[请选择]</option>";
			if (fromDay < day) {// 8/9
				editDiv += "<option value='" + fromDay
						+ "' selected>作日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>当日</option>";
				editDiv += "<option value='" + (fromDay + 2) + "'>次日</option>";
				editDiv += "<option value='" + (fromDay + 3) + "'>三日</option>";
			}
			if (fromDay == day) {
				editDiv += "<option value='" + (fromDay - 1) + "'>作日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>当日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>次日</option>";
				editDiv += "<option value='" + (fromDay + 2) + "'>三日</option>";

			}
			if (fromDay > day && fromDay - day == 1) {// 10 9
				editDiv += "<option value='" + (fromDay - 2) + "'>作日</option>";
				editDiv += "<option value='" + (fromDay - 1) + "'>当日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>次日</option>";
				editDiv += "<option value='" + (fromDay + 1) + "'>三日</option>";
			}
			if (fromDay > day && fromDay - day == 2) {
				editDiv += "<option value='" + (fromDay - 3) + "'>作日</option>";
				editDiv += "<option value='" + (fromDay - 2) + "'>当日</option>";
				editDiv += "<option value='" + (fromDay - 1) + "'>次日</option>";
				editDiv += "<option value='" + fromDay
						+ "' selected>三日</option>";
			}

			editDiv += "</select></td>";

			editDiv += "<td width='22'>";
			editDiv += "<table width='22' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='20'><img src='../images/up2.gif' name='Image3113' width='20' height='10' border='0' id='Image31' onClick=changeTime(1,1,'from_time_sk"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ PK_NO
					+ "','from_time_sk"
					+ PK_NO
					+ "','to_time_sk"
					+ PK_NO
					+ "','tospaceDay_sk"
					+ PK_NO
					+ "','fromspaceDay_sk" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td><img src='../images/down2.gif' name='Image21' width='20' height='10' border='0' id='Image21' onClick=changeTime(1,2,'from_time_sk"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ PK_NO
					+ "','from_time_sk"
					+ PK_NO
					+ "','to_time_sk"
					+ PK_NO
					+ "','tospaceDay_sk"
					+ PK_NO
					+ "','fromspaceDay_sk" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			from_time = from_time.substring(from_time.indexOf(" ") + 1,
					from_time.lastIndexOf(":"));
			from_time = from_time.trim();
			editDiv += "<td width='35'><input name='from_time_sk" + PK_NO
					+ "' type='text' size='5' value=" + from_time
					+ " readonly='true'></td>";
			editDiv += "<td width='23'><table width='23' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='21' align='left'><img src='../images/up2.gif' name='Image311' width='20' height='10' border='0' id='Image31' onClick=changeTime(2,1,'from_time_sk"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ PK_NO
					+ "','from_time_sk"
					+ PK_NO
					+ "','to_time_sk"
					+ PK_NO
					+ "','tospaceDay_sk"
					+ PK_NO
					+ "','fromspaceDay_sk" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td align='left'><img src='../images/down2.gif' name='Image211' width='20' height='10' border='0' id='Image21' onClick=changeTime(2,2,'from_time_sk"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ PK_NO
					+ "','from_time_sk"
					+ PK_NO
					+ "','to_time_sk"
					+ PK_NO
					+ "','tospaceDay_sk"
					+ PK_NO
					+ "','fromspaceDay_sk" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			// /////////
			editDiv += "<td width='54'><select name='tospaceDay_sk" + PK_NO
					+ "' onchange=changespDay('" + minUnit + "','spaceHours_sk"
					+ PK_NO + "','from_time_sk" + PK_NO + "','to_time_sk"
					+ PK_NO + "','tospaceDay_sk" + PK_NO + "','fromspaceDay_sk"
					+ PK_NO + "')>";
			editDiv += "<option value=''>[请选择]</option>";
			if (toDay == day) {
				editDiv += "<option value='" + toDay + "' selected>当日</option>";
				editDiv += "<option value='" + (toDay + 1) + "'>次日</option>";
				editDiv += "<option value='" + (toDay + 2) + "'>三日</option>";

			}
			if (toDay > day && fromDay - day == 1) {// 10 9
				editDiv += "<option value='" + (toDay - 1) + "'>当日</option>";
				editDiv += "<option value='" + toDay + "' selected>次日</option>";
				editDiv += "<option value='" + (toDay + 1) + "'>三日</option>";
			}
			if (toDay > day && fromDay - day == 2) {
				editDiv += "<option value='" + (toDay - 2) + "'>当日</option>";
				editDiv += "<option value='" + (toDay - 1) + "'>次日</option>";
				editDiv += "<option value='" + toDay + "' selected>三日</option>";
			}

			editDiv += "</select></td>";
			editDiv += "<td width='22'>";
			editDiv += "<table width='22' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='20'><img src='../images/up2.gif' name='Image3113' width='20' height='10' border='0' id='Image31' onClick=changeTime(1,1,'to_time_sk"
					+ x
					+ "_"
					+ day
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ x
					+ "_"
					+ day
					+ "','from_time_sk"
					+ x
					+ "_"
					+ day
					+ "','to_time_sk"
					+ x
					+ "_"
					+ day
					+ "','tospaceDay_sk"
					+ x
					+ "_"
					+ day
					+ "','fromspaceDay_sk" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td><img src='../images/down2.gif' name='Image21' width='20' height='10' border='0' id='Image21' onClick=changeTime(1,2,'to_time_sk"
					+ x
					+ "_"
					+ day
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ x
					+ "_"
					+ day
					+ "','from_time_sk"
					+ x
					+ "_"
					+ day
					+ "','to_time_sk"
					+ x
					+ "_"
					+ day
					+ "','tospaceDay_sk"
					+ x
					+ "_"
					+ day
					+ "','fromspaceDay_sk" + PK_NO + "'); ></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			to_time = to_time.substring(to_time.indexOf(" ") + 1, to_time
					.lastIndexOf(":"));
			to_time = to_time.trim();
			editDiv += "<td width='35'><input name='to_time_sk" + PK_NO
					+ "' type='text' size='5' value='" + to_time
					+ "' readonly='true'></td>";
			editDiv += "<td width='23'><table width='23' border='0' cellspacing='0' cellpadding='1'>";
			editDiv += "<tr>";
			editDiv += "<td width='21' align='left'><img src='../images/up2.gif' name='Image311' width='20' height='10' border='0' id='Image31' onClick=changeTime(2,1,'to_time_sk"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ PK_NO
					+ "','from_time_sk"
					+ PK_NO
					+ "','to_time_sk"
					+ PK_NO
					+ "','tospaceDay_sk"
					+ PK_NO
					+ "','fromspaceDay_sk" + PK_NO + "');></td>";
			editDiv += "</tr>";
			editDiv += "<tr>";
			editDiv += "<td align='left'><img src='../images/down2.gif' name='Image211' width='20' height='10' border='0' id='Image21' onClick=changeTime(2,2,'to_time_sk"
					+ PK_NO
					+ "','"
					+ lock
					+ "');changeText('"
					+ minUnit
					+ "','spaceHours_sk"
					+ PK_NO
					+ "','from_time_sk"
					+ PK_NO
					+ "','to_time_sk"
					+ PK_NO
					+ "','tospaceDay_sk"
					+ PK_NO
					+ "','fromspaceDay_sk" + PK_NO + "');></td>";
			editDiv += "</tr>";
			editDiv += "</table></td>";
			editDiv += "</tr>";
		}
		db.closeConnection();

		editDiv += "<tr>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td><input type='button' value='更新' onclick=editSubmit('"
				+ day + "')></td>";
		editDiv += "<td><input type='button' value='取消' onclick=closeE()></td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "<td>&nbsp;</td>";
		editDiv += "</tr></table></div></form>";
		Logger.getLogger(getClass()).debug("out to page : " + editDiv);
		return editDiv;
	}

	public String outZero(String str) {
		if (str.length() != 2)
			return str;
		int flag = 0;
		String temp = null;
		temp = str.substring(0, 1);
		flag = Integer.parseInt(temp);
		if (flag == 0)
			return str.substring(1, 2);
		else
			return str;
	}

	//
	//
	public String insertZero(String str) {
		if (str.length() > 1)
			return str;
		int flag = 0;
		flag = Integer.parseInt(str);
		if (flag < 10)
			return "0" + str;
		else
			return str;
	}

	//
	//
	public String conversionDate(String str) {
		if (str.length() != 8)
			return str;
		String y = str.substring(0, 4);
		String m = str.substring(4, 6);
		String d = str.substring(6, 8);
		if (Integer.parseInt(d) >= 21) {
			int temp = Integer.parseInt(m) - 1;
			String temp1 = Integer.toString(temp);
			if (temp < 10 && temp1.length() < 2) {
				temp1 = "0" + temp1;
			}
			str = y + "/" + temp1 + "/" + d;
		} else {
			str = y + "/" + m + "/" + d;
		}
		return str;
	}

	// 四舍五入 方法 特别方法(0.25|0.5)
	public float Round(float minUnit, float round) {
		String temp = Float.toString(round);
		temp = temp.substring(0, temp.indexOf("."));
		float outtemp = (float) Integer.parseInt(temp);
		float temp1 = 0;
		float returntemp = 0;
		if (minUnit == (float) 0.5) {
			temp1 = round - outtemp;
			if (temp1 == (float) 0.5)
				return round;
			if (temp1 == (float) 0.25)
				returntemp = outtemp + (float) 0.5;
			if (temp1 == (float) 0.75)
				returntemp = outtemp + (float) 1;
			if (temp1 > (float) 0.5 && temp1 < (float) 0.75)
				returntemp = outtemp + (float) 0.5;
			if (temp1 > (float) 0.5 && temp1 > (float) 0.75)
				returntemp = outtemp + (float) 1;
			if (temp1 < (float) 0.5 && temp1 > (float) 0.25)
				returntemp = outtemp + (float) 0.5;
			if (temp1 < (float) 0.5 && temp1 < (float) 0.25)
				returntemp = outtemp;
		}
		if (minUnit == (float) 1.0) {
			temp1 = round - outtemp;
			if (temp1 == (float) 0.5)
				return round;
			if (temp1 > (float) 0.5)
				returntemp = outtemp + (float) 1;
			if (temp1 < (float) 0.5) {
				returntemp = outtemp;
			}
		}
		return returntemp;
	}

	public int get_Year(String time) {
		return Integer.parseInt(time.substring(0, time.indexOf("-")));
	}

	public int get_Month(String time) {
		return Integer.parseInt(time.substring(time.indexOf("-") + 1, time
				.lastIndexOf("-")));
	}

	public int get_Day(String time) {
		return Integer.parseInt(time.substring(time.lastIndexOf("-") + 1, time
				.indexOf(" ")));
	}

	public int get_Hours(String time) {
		return Integer.parseInt(time.substring(time.indexOf(" ") + 1, time
				.indexOf(":")));// 得到小时
	}

	public int get_Minute(String time) {
		return Integer.parseInt(time.substring(time.indexOf(":") + 1, time
				.lastIndexOf(":")));
	}

	public int getStarHours(String empID, String ar_month, Connection conn) {
		int h = 0;
		Statement state = null;
		ResultSet rs = null;
		String sql = "SELECT TO_CHAR(MIN(AR_SHIFT020.FROM_TIME), 'HH24') AS FROM_TIME "
				+ "FROM AR_SCHEDULE, AR_SHIFT020 "
				+ "WHERE AR_SCHEDULE.SHIFT_NO = AR_SHIFT020.SHIFT_NO AND AR_DATE_STR='"
				+ ar_month + "' " + "AND EMPID='" + empID + "'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				h = rs.getInt("FROM_TIME");
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, state);
		}
		return h;
	}

	public String getCode(String description) {
		String code = null;
		if (description.equals("常白班"))
			code = "CBB";
		if (description.equals("夜2"))
			code = "Y2";
		if (description.equals("夜3"))
			code = "Y3";
		if (description.equals("白2"))
			code = "B2";
		if (description.equals("白3"))
			code = "B3";
		if (description.equals("值班"))
			code = "ZB";
		if (description.equals("休息"))
			code = "XX";
		return code;
	}

	public ArrayList getEmpMonthWorkGroup(String ar_month, String empID,
			ArrayList days) throws Exception {
		if (days.size() <= 0)
			return null;
		String sql = "select * from ar_daily_v where empid='" + empID
				+ "' and ar_month_str='" + ar_month + "' order by ar_date_str";
		Logger.getLogger(getClass()).debug(sql);
		// ArrayList emps = new ArrayList();
		String day = null;
		Day d_day = null;
		EmpMonthWork e_emp = null;
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			EmpMonthWork emp = new EmpMonthWork();
			emp.setEmpID(rs.getString("empID"));
			emp.setDescription(this.getCode(rs.getString("ar_name")));
			day = rs.getString("ar_date_str");
			day = day.substring(day.lastIndexOf("/") + 1, day.length());
			emp.setDay(Integer.parseInt(day));
		}
		db.closeConnection();
		for (int j = 0; j < days.size(); j++) {
			d_day = (Day) days.get(j);
			for (int x = 0; x < days.size(); x++) {
				e_emp = (EmpMonthWork) days.get(x);
				d_day.setStarHours(e_emp.getStarHour());
				if (e_emp.getDay() == d_day.getDayNumber()) {
					d_day.setDescription(e_emp.getDescription());
					d_day.setHours(e_emp.getHours());
					break;
				}
			}
		}
		return days;
	}

	public boolean EditShift(String sql) throws Exception {
		db.getConnectionOracle();
		int flag = db.update(sql);
		db.closeConnection();
		if (flag > 0)
			return true;
		else
			return false;
	}

}
