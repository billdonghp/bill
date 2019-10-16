package com.ait.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.log4j.Logger;

import com.ait.ar.bean.ArCalendar;
import com.ait.core.exception.GlRuntimeException;
import com.ait.utils.ConnBean;

public class DateUtil {

	public final static String DATE_PATTERN = "yyyy-MM-dd";

	public final static String DATE_PATTERN_2 = "yyyy/MM/dd";

	public final static String TIME_HM_PATTERN = "HH:mm";

	private static DateUtil instance;

	private static ServiceLocator services;

	public DateUtil() {
		if (instance == null) {
			getInstance();
		}
	}

	/**
	 * Date to String
	 * 
	 * @param d
	 * @param formatPattern
	 * @return
	 */
	public static String formatDate(Date d, String formatPattern) {
		if(d == null){
			return "";
		}
		SimpleDateFormat timeFormatter = new SimpleDateFormat(formatPattern);
		String sDate = timeFormatter.format(d);
		return sDate;
	}

	/**
	 * 取日期范围内的日期列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @return ArCalendar[]
	 */
	public static ArCalendar[] getArCalendarListInScope(String startDate,
			String endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select To_Char(DDate, 'yyyy-dd-mm') as DDate, IYear, IMonth, IDay, IWeek, WorkDayFlag, TypeId, OverTypeId, TypeId_Default"
				+ " from ar_calender where DDate between To_Date(?, 'yyyy-mm-dd') and To_Date(?, 'yyyy-mm-dd')";
		Logger.getLogger(DateUtil.class).debug(sql);
		List list = new ArrayList();
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ArCalendar ar = new ArCalendar();
				ar.setDdate(rs.getString(1));
				ar.setIyear(rs.getInt(2));
				ar.setImonth(rs.getInt(3));
				ar.setIday(rs.getInt(4));
				ar.setIweek(rs.getInt(5));
				ar.setWorkdayflag(rs.getInt(6));
				ar.setTypeid(rs.getInt(7));
				ar.setOvertypeid(rs.getInt(8));
				ar.setTypeid_default(rs.getInt(9));
				list.add(ar);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DateUtil.class).error(ex.toString());
			ex.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}

		return (ArCalendar[]) list.toArray(new ArCalendar[list.size()]);
	}

	public static List getYearList(int n) {
		List lYear = new Vector();
		Calendar ca = new GregorianCalendar();
		int year = ca.get(Calendar.YEAR);
		for (int i = year - n; i <= year + n; i++) {
			String option = "";
			if (year == i) {
				option = "<option value='" + i + "' selected>" + i
						+ "</option>";
			} else {
				option = "<option value='" + i + "'>" + i + "</option>";
			}
			lYear.add(option);
		}
		return lYear;
	}

	/**
	 * parseWeekDay
	 * 
	 * @param weekDay
	 *            String
	 * @return int
	 */
	public static int parseWeekDay(String weekDay) {
		HashMap map = new HashMap();
		map.put("星期日", "0");
		map.put("星期一", "1");
		map.put("星期二", "2");
		map.put("星期三", "3");
		map.put("星期四", "4");
		map.put("星期五", "5");
		map.put("星期六", "6");
		return NumberUtil.parseInt((String) map.get(weekDay));
	}

	/**
	 * getContractEndDate
	 * 
	 * @param startDate
	 *            String
	 * @return String
	 */
	public static String getContractEndDate(String startDate)
			throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(?,'YYYY-MM-DD') + INTERVAL '2' YEAR - INTERVAL '1' DAY AS CONTRACT_END_DATE FROM DUAL  ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startDate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1).substring(0, 10);
			}
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
			throw new DataAccessException("cant get next day", ex);
		} catch (ServiceLocatorException ex) {
			throw new DataAccessException(
					"cant get connection for get next day");
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return startDate;
	}

	/**
	 * getEndProbationDate
	 * 
	 * @param joinDate
	 *            String
	 * @return String
	 */
	public static String getEndProbationDate(String joinDate)
			throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(?,'YYYY-MM-DD') + INTERVAL '2' MONTH - INTERVAL '1' DAY AS END_PROBATION_DATE FROM DUAL  ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, joinDate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1).substring(0, 10);
			}
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
			throw new DataAccessException("cant get next day", ex);
		} catch (ServiceLocatorException ex) {
			throw new DataAccessException(
					"cant get connection for get next day");
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return joinDate;

	}

	public static DateUtil getInstance() {
		return new DateUtil();
	}

	public static String getSysdate() {
		String timeFormat = "yyyy-MM-dd";
		SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
		String sDate = timeFormatter.format(new java.util.Date());
		return sDate;
	}

	private static Connection getConnection() throws ServiceLocatorException {
		try {
			services = ServiceLocator.getInstance();
			return services.getConnection();
		} catch (ServiceLocatorException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return null;
	}

	/**
	 * 
	 * @param month
	 *            int
	 * @param year
	 *            int
	 * @return int
	 */
	public static int daysInMonth(int year, int month) {
		int daysinmonth = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			daysinmonth = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			daysinmonth = 30;
			break;
		case 2:
			if (year / 4 == 0 && year / 100 != 0 || (year / 400 == 0)) {
				daysinmonth = 29;
			} else {
				daysinmonth = 28;
			}
			break;
		default:
			daysinmonth = 0;
		}
		return daysinmonth;
	}

	/**
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param date
	 *            int
	 * @return int
	 */
	public static int getDay(int year, int month, int date) {
		int mm = 0;
		int yy = 0;
		int yd = 0;
		int day = 0;
		switch (month) {
		case 1:
			mm = 1;
			break;
		case 2:
			mm = 4;
			break;
		case 3:
			mm = 4;
			break;
		case 4:
			mm = 0;
			break;
		case 5:
			mm = 2;
			break;
		case 6:
			mm = 5;
			break;
		case 7:
			mm = 0;
			break;
		case 8:
			mm = 3;
			break;
		case 9:
			mm = 6;
			break;
		case 10:
			mm = 1;
			break;
		case 11:
			mm = 4;
			break;
		case 12:
			mm = 6;
			break;
		}
		yy = year - 1900;
		yd = yy / 4;
		day = (mm + yy + yd + date) % 7;
		if (day == 0) {
			day = 6;
		} else {
			day = day - 1;
		}
		return day;
	}

	/**
	 * getNextDay
	 * 
	 * @param today
	 *            String
	 * @return String
	 */
	public static String getNextDay(String today) throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(?,'YYYY-MM-DD') + INTERVAL '1' DAY AS NEXTDAY FROM DUAL  ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, today);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return StringUtil.parseDate(rs.getString(1).substring(0, 10));
			}
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
			throw new DataAccessException("cant get next day", ex);
		} catch (ServiceLocatorException ex) {
			throw new DataAccessException(
					"cant get connection for get next day");
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return today;
	}
	
//	每分钟
	public static List getTimeMMList() {
		List<String> list = new ArrayList<String>();
		for (int mm = 0; mm < 60; mm++) {
			list.add((mm < 10 ? "0" + mm : mm) + "");
		}
		return list;
	}
	
//每十分钟
	public static List getTimePerMMList() {
		List<String> list = new ArrayList<String>();
		for (int mm = 0; mm < 6; mm++) {
			list.add( mm + "0");
		}
		return list;
	}
	
	public static List getTimePerHourList() {
		List<String> list = new ArrayList<String>();
		for (int hour = 0; hour < 24; hour++) {
			list.add((hour < 10 ? "0" + hour : hour) + "");
		}
		return list;
	}
	
	public static List getTimeList() {
		List list = new ArrayList();
		for (int hour = 0; hour < 24; hour++) {
			list.add(("0" + hour).substring(("0" + hour).length() - 2,
					("0" + hour).length())
					+ ":00");
			list.add(("0" + hour).substring(("0" + hour).length() - 2,
					("0" + hour).length())
					+ ":30");
		}
		return list;
	}
	/**
	 * 获取休假时间段
	 * @return
	 */
	public static List getLeaveTimeList() {
		List list = new ArrayList();
		
			list.add(("0" + 8).substring(("0" + 8).length() - 2,
					("0" + 8).length())
					+ ":30");
			list.add(("0" + 12).substring(("0" + 12).length() - 2,
					("0" + 12).length())
					+ ":00");
			list.add(("0" + 13).substring(("0" + 13).length() - 2,
					("0" + 13).length())
					+ ":00");
			list.add(("0" + 17).substring(("0" + 17).length() - 2,
					("0" + 17).length())
					+ ":30");
	
		return list;
	}

	public static java.util.Date DateAdd(Date date, String type, int integer) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		return DateAdd(gregorianCalendar, type, integer).getTime();
	}

	// 返回某日期加减一个数值后的结果日期
	public static GregorianCalendar DateAdd(GregorianCalendar date,
			String type, int integer) {
		GregorianCalendar dateAfter = date;
		type = StringUtil.checkNull(type);
		if (type.equalsIgnoreCase("YEAR"))
			dateAfter.add(GregorianCalendar.YEAR, integer);
		else if (type.equalsIgnoreCase("MONTH"))
			dateAfter.add(GregorianCalendar.MONTH, integer);
		else if (type.equalsIgnoreCase("DAY"))
			dateAfter.add(GregorianCalendar.DAY_OF_MONTH, integer);
		else if (type.equalsIgnoreCase("WEEK"))
			dateAfter.add(GregorianCalendar.WEEK_OF_YEAR, integer);
		else if (type.equalsIgnoreCase("WEEK_OF_YEAR"))
			dateAfter.add(GregorianCalendar.WEEK_OF_YEAR, integer);
		else if (type.equalsIgnoreCase("WEEK_OF_MONTH"))
			dateAfter.add(GregorianCalendar.WEEK_OF_MONTH, integer);
		else if (type.equalsIgnoreCase("HOUR"))
			dateAfter.add(GregorianCalendar.HOUR, integer);
		else if (type.equalsIgnoreCase("MINUTE"))
			dateAfter.add(GregorianCalendar.MINUTE, integer);
		else if (type.equalsIgnoreCase("SECOND"))
			dateAfter.add(GregorianCalendar.SECOND, integer);
		else if (type.equalsIgnoreCase("MILLISECOND"))
			dateAfter.add(GregorianCalendar.MILLISECOND, integer);
		return dateAfter;
	}

	public static double DateCross(Date fromDate1, Date toDate1, Date fromDate2,
			Date toDate2, String type) {
		GregorianCalendar gregorianCalendar1 = new GregorianCalendar();
		gregorianCalendar1.setTime(fromDate1);
		GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
		gregorianCalendar2.setTime(toDate1);
		GregorianCalendar gregorianCalendar3 = new GregorianCalendar();
		gregorianCalendar1.setTime(fromDate2);
		GregorianCalendar gregorianCalendar4 = new GregorianCalendar();
		gregorianCalendar2.setTime(toDate2);
		return DateCross(gregorianCalendar1, gregorianCalendar2,gregorianCalendar3, gregorianCalendar4, type);
	}

	public static double DateCross(GregorianCalendar fromDate1,
			GregorianCalendar toDate1, GregorianCalendar fromDate2,
			GregorianCalendar toDate2, String type) {
		double length = 0;
		GregorianCalendar fromDate = fromDate1;
		GregorianCalendar toDate = toDate1;
		if (fromDate2.after(fromDate1))
			fromDate = fromDate2;
		if (toDate2.before(toDate1))
			toDate = toDate2;
		if (fromDate.after(toDate))
			length = 0;
		else
			length = DateDiff(fromDate, toDate, type);
		Logger.getLogger(DateUtil.class).debug(
				"length : " + String.valueOf(length));
		return length;
	}
	

	public static boolean isDateContain(GregorianCalendar fromDate1,
			GregorianCalendar toDate1, GregorianCalendar fromDate2,
			GregorianCalendar toDate2) {
	
		if ((fromDate1.after(fromDate2) || fromDate1.equals(fromDate2)) 
				&& (toDate2.after(toDate1) || toDate2.equals(toDate1))){
			return true;
		}
		return false;
	}
	
	// QPSS 申请休假以天为单位没有开始结束时间，重载计算时间差的方法
	public static long DateCross(GregorianCalendar fromDate1,
			GregorianCalendar toDate1, GregorianCalendar fromDate2,
			GregorianCalendar toDate2) {
		long length = 0;
		GregorianCalendar fromDate = fromDate1;
		GregorianCalendar toDate = toDate1;
		if (fromDate2.after(fromDate1))
			fromDate = fromDate2;
		if (toDate2.before(toDate1))
			toDate = toDate2;
		if (fromDate.after(toDate))
			length = 0;
		else 
			length = 1;
		Logger.getLogger(DateUtil.class).debug(
				"length : " + String.valueOf(length));
		return length;
	}

	public static double DateDiff(Date date1, Date date2, String type) {
		GregorianCalendar gregorianCalendar1 = new GregorianCalendar();
		gregorianCalendar1.setTime(date1);
		GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
		gregorianCalendar2.setTime(date2);
		return DateDiff(gregorianCalendar1, gregorianCalendar2, type);
	}

	// 返回两个日期之间的差
	public static double DateDiff(GregorianCalendar date1,GregorianCalendar date2, String type) {
		double difference = 0;
		double milliseconds = date2.getTimeInMillis() - date1.getTimeInMillis();
		type = StringUtil.checkNull(type);
		if (type.equalsIgnoreCase("YEAR")) {
			difference = date2.get(GregorianCalendar.YEAR) - date1.get(GregorianCalendar.YEAR);
			if (DateAdd(date1, "YEAR", Integer.parseInt(String.valueOf(difference))).after(date2))
				difference = difference - 1;
		} else if (type.equalsIgnoreCase("MONTH")) {
			difference = date2.get(GregorianCalendar.MONTH) - DateAdd(date1, "YEAR", Integer.parseInt(String.valueOf(DateDiff(date1, date2, "YEAR")))).get(GregorianCalendar.MONTH);
			if (DateAdd(date1, "MONTH", Integer.parseInt(String.valueOf(DateDiff(date1, date2, "YEAR") * 12 + difference))).after(date2))
				difference = difference - 1;
		} else if (type.equalsIgnoreCase("DAY"))
			difference = milliseconds / 1000 / 60 / 60 / 24;
		else if (type.equalsIgnoreCase("HOUR"))
			difference = milliseconds / 1000 / 60 / 60;
		else if (type.equalsIgnoreCase("MINUTE"))
			difference = milliseconds / 1000 / 60;
		else if (type.equalsIgnoreCase("SECOND"))
			difference = milliseconds / 1000;
		else if (type.equalsIgnoreCase("MILLISECOND"))
			difference = milliseconds;
		return difference;
	}

	public static GregorianCalendar ParseGregorianCalendar(String dateStr) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.clear();
		if (dateStr != null) {
			if (dateStr.length() == 10) {
				// yyyy-MM-dd
				gregorianCalendar.set(
						Integer.parseInt(dateStr.substring(0, 4)), Integer
								.parseInt(dateStr.substring(5, 7)) - 1, Integer
								.parseInt(dateStr.substring(8, 10)), 0, 0, 0);
			} else if (dateStr.length() == 13) {
				// yyyy-MM-dd hh
				gregorianCalendar.set(
						Integer.parseInt(dateStr.substring(0, 4)), Integer
								.parseInt(dateStr.substring(5, 7)) - 1, Integer
								.parseInt(dateStr.substring(8, 10)), Integer
								.parseInt(dateStr.substring(11, 13)), 0, 0);
			} else if (dateStr.length() == 16) {
				// yyyy-MM-dd hh:mm
				gregorianCalendar.set(
						Integer.parseInt(dateStr.substring(0, 4)), Integer
								.parseInt(dateStr.substring(5, 7)) - 1, Integer
								.parseInt(dateStr.substring(8, 10)), Integer
								.parseInt(dateStr.substring(11, 13)), Integer
								.parseInt(dateStr.substring(14, 16)), 0);
			} else if (dateStr.length() >= 19) {
				// yyyy-MM-dd hh:mm:ss
				gregorianCalendar.set(
						Integer.parseInt(dateStr.substring(0, 4)), Integer
								.parseInt(dateStr.substring(5, 7)) - 1, Integer
								.parseInt(dateStr.substring(8, 10)), Integer
								.parseInt(dateStr.substring(11, 13)), Integer
								.parseInt(dateStr.substring(14, 16)), Integer
								.parseInt(dateStr.substring(17, 19)));
			}
		}
		return gregorianCalendar;
	}

	/**
	 * 判断日期范围内是否存在 节假日
	 * 
	 * @param arList
	 * @return
	 */
	public static boolean isExistFestival(ArCalendar[] arList) {
		for (int i = 0; i < arList.length; i++) {
			if (arList[i].getTypeid() == 3)
				return true;
		}
		return false;
	}

	/**
	 * 判断日期范围内是否存在 周休
	 * 
	 * @param arList
	 * @return
	 */
	public static boolean isExistWeekend(ArCalendar[] arList) {
		for (int i = 0; i < arList.length; i++) {
			if (arList[i].getTypeid() == 2)
				return true;
		}
		return false;
	}

	/**
	 * 判断日期范围内是否存在 平时
	 * 
	 * @param arList
	 * @return
	 */
	public static boolean isExistNormal(ArCalendar[] arList) {
		for (int i = 0; i < arList.length; i++) {
			if (arList[i].getTypeid() == 1)
				return true;
		}
		return false;
	}

	/**
	 * String to Date
	 * 
	 * @param str
	 * @param parsePattern
	 * @return
	 */
	public static Date parseDate(String str, String parsePattern) {
		if (str == null || "".equals(str))
			return null;
		SimpleDateFormat parser = new SimpleDateFormat(parsePattern);
		ParsePosition pos = new ParsePosition(0);
		Date date = parser.parse(str, pos);
		if (date != null && pos.getIndex() == str.length()) {
			return date;
		}
		throw new GlRuntimeException("日期错误: " + str);
	}

	/**
	 * 日期加上一个数值之后的日期
	 * 
	 * @param d
	 * @param field
	 *            the time field.
	 *            加上天数：field= Calendar.DATE; 月：Calendar.MONTH;年：Calendar.YEAR
	 * @param amount
	 *            the amount of date or time to be added to the field.
	 * @return
	 * @see Calendar
	 */
	public static Date addDate(Date d, int field, int amount) {
		if (d == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(field, amount);
		return c.getTime();
	}

	/**
	 * 日期+天 add(2005-01-10, -5) return 2005-01-05
	 * 
	 * @param d
	 * @param amount
	 *            天数
	 * @return
	 */
	public static Date addDay(Date d, int amount) {
		return addDate(d, Calendar.DATE, amount);
	}

	/**
	 * 日期+月
	 * 
	 * @param d
	 * @param amount
	 *            月数
	 * @return
	 */
	public static Date addMonth(Date d, int amount) {
		return addDate(d, Calendar.MONTH, amount);
	}

	/**
	 * 日期+年
	 * 
	 * @param d
	 * @param amount
	 *            年数
	 * @return
	 */
	public static Date addYear(Date d, int amount) {
		return addDate(d, Calendar.YEAR, amount);
	}

	/**
	 * 得到当前日期所在月的第一天的日期
	 * @param d
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date d)	{   
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        final int firstDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);   
        c.set(Calendar.DATE, firstDay);
        return c.getTime();
    } 

	/**
	 * 得到当前日期所在月的最后一天的日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastDayOfMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		final int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DATE, lastDay);
		return c.getTime();
	}
	/**
	 * 取日期年份
	 * @param value
	 * @return
	 */
	public static int getYear(Date value){
		if (value == null)
			return 0;
		Calendar c = Calendar.getInstance();
		c.setTime(value);
		return c.get(Calendar.YEAR);
	}
	/**
	 * 根据出生日期计算年龄
	 * @param birthday 出生日期
	 * @return
	 */
	public static int calAge(Date birthday){
		int currenYear = getYear(new Date());
		int birthYear = getYear(birthday);
		return currenYear - birthYear;
	}
	

	/**
	 * 两个日期之差 end - start
	 * @param start
	 * @param end
	 * @param format 例如 "y,M,d"  注意：M表示月，m表示分钟
	 * @return  与format相关  例如：start= 2005-01-12, end = 2006-05-08 format = "y,M,d"  结果值就是1,3,26
	 */
	public static String formatPeriod(Date start, Date end, String format){
		if (start == null || end == null)
			return ""; 
		return DurationFormatUtils.formatPeriod(start.getTime(), end.getTime(), format);
	}
	
	/**
	 * 根据格式参数返回当前日期
	 * @param pOutformat
	 * @return String
	 */
    public static String getToday(String pOutformat) {

        SimpleDateFormat pOutformatter = new SimpleDateFormat(pOutformat,
                java.util.Locale.CHINA);

        String rDateString = null;
        Date vDate = new Date();

        try {
            rDateString = pOutformatter.format(vDate);

        } catch (Exception e) {
        }

        return rDateString;
    }
}
