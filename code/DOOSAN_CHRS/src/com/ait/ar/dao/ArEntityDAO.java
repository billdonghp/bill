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

import org.apache.log4j.Logger;

import com.ait.ar.entity.CompanyCalendar;
import com.ait.util.DataAccessException;
import com.ait.util.DateUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArEntityDAO {
	public ArEntityDAO() {
	}

	//---------------------------------------------//
	public int modifyCalendar(CompanyCalendar calendar, String calendarDate){
		int affRow = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = 
									"UPDATE AR_CALENDAR_DEPT " + 
									"SET CALENDAR_TYPE='" + calendar.getCalendarType() + "', " + 
									"WORK_FLAG='" + calendar.getWorkFlag() + "'," + 
									"UPDATE_DATE=TO_DATE('" + calendar.getUpdateDate() + "','YYYY-MM-DD')," + 
									"UPDATED_BY='" + calendar.getUpdatedBy() + "' " + 
									"WHERE TO_CHAR(CALENDAR_DATE,'YYYY-MM-DD')='" + calendarDate + "' ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			affRow = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(pstmt, con);
		}
		return affRow;
	}

	public List getCompanyCalendar(String month) throws DataAccessException {

		List list = null;
		int start = getCalendarStart(month);
		for (int i = 0; i < start; i++) {
			list.add(new CompanyCalendar());
		}
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = 
								"SELECT " +
									"CALENDAR_DATE, " +
									"CALENDAR_TYPE, " +
									"WORK_FLAG, " +
									"CALENDAR_NO, " +
									"CALENDAR_DAY, " +
									"CREATE_DATE, " +
									"CREATED_BY, " +
									"UPDATE_DATE, " +
									"UPDATED_BY " +
								"FROM AR_CALENDAR_V " +
								"WHERE TO_CHAR(CALENDAR_DATE,'YYYY-MM')='" + month + "' " +
								"ORDER BY CALENDAR_DATE";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			while (rs.next()) {
				CompanyCalendar companyCalendar = new CompanyCalendar();
				companyCalendar.setCalendarDate(rs.getString("CALENDAR_DATE") != null ? rs.getString("CALENDAR_DATE") : "");
				companyCalendar.setCalendarType(rs.getInt("CALENDAR_TYPE"));
				companyCalendar.setWorkFlag(rs.getInt("WORK_FLAG"));
				companyCalendar.setCalendarNo(rs.getInt("CALENDAR_NO"));
				companyCalendar.setCalendarDay(rs.getString("CALENDAR_DAY") != null ? rs.getString("CALENDAR_DAY") : "");
				companyCalendar.setCreateDate(rs.getString("CREATE_DATE") != null ? rs.getString("CREATE_DATE") : "");
				companyCalendar.setCreatedBy(rs.getString("CREATED_BY") != null ? rs.getString("CREATED_BY") : "");
				companyCalendar.setUpdateDate(rs.getString("UPDATE_DATE") != null ? rs.getString("UPDATE_DATE") : "");
				companyCalendar.setUpdatedBy(rs.getString("UPDATED_BY") != null ? rs.getString("UPDATED_BY") : "");
				list.add(companyCalendar);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}
	
	public int addCalendar(String deptID, String year){
		int affRow = 0;
		Connection conn = null;
		Statement state = null;
		String first_date = year + "-01-01";
		String last_date = year + "-12-31";
		Vector vector = getSubDeptList(deptID);
		String sql = null;
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			for (int i = 0; i < vector.size(); i++) {
				deptID = (String) vector.elementAt(i);
                String nextDay = first_date;
				for (String date = first_date; date.compareTo(last_date) < 0; date = nextDay){
                    nextDay = DateUtil.getNextDay(date);
					sql = 
							"INSERT INTO AR_CALENDAR_DEPT(" +
								"CALENDAR_NO, " +
								"DEPTID, " +
								"CALENDAR_DATE" +
							")VALUES(" +
								"AR_CALENDAR_DEPT_SEQ.NEXTVAL, " +
								"'" + deptID + "', " +
								"TO_Date('" + date + "', 'yyyy-mm-dd')" +
							") ";
					Logger.getLogger(getClass()).debug(sql);
					state.addBatch(sql);
				}
			}
			int [] affRowArray = state.executeBatch();
			for(int i = 0; i < affRowArray.length; i++){
				affRow += affRowArray[i];
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
			SqlUtil.close(state, conn);
		}
		return affRow;
	}

	private int getCalendarStart(String month){
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = 
								"SELECT CALENDAR_DAY " +
								"FROM AR_CALENDAR_dept_V " +
								"WHERE TO_CHAR(CALENDAR_DATE,'YYYY-MM')='" + month + "' " +
								"AND TO_CHAR(CALENDAR_DATE,'DD')='01' ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String weekDay = rs.getString(1);
				if (weekDay.endsWith("日"))
					return 0;
				if (weekDay.endsWith("一"))
					return 1;
				if (weekDay.endsWith("二"))
					return 2;
				if (weekDay.endsWith("三"))
					return 3;
				if (weekDay.endsWith("四"))
					return 4;
				if (weekDay.endsWith("五"))
					return 5;
				if (weekDay.endsWith("六"))
					return 6;
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return 0;
	}

	private Vector getSubDeptList(String deptID) {
		Vector vector = new Vector();
		//deptID = StringUtil.parseDeptID(deptID);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = 
								"SELECT DEPTID " +
								"FROM HR_DEPT_TREE_V " +
								"WHERE DEPTID LIKE '" + deptID + "' ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vector.add(rs.getString(1));
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return vector;
	}

	public List getDepartmentCalendar(String deptID, String month){
		List list = new ArrayList();
		int start = getCalendarStart(month);
		for (int i = 0; i < start; i++) {
			list.add(new CompanyCalendar());
		}
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = 
								"SELECT " +
									"CALENDAR_DATE, " +
									"CALENDAR_TYPE, " +
									"WORK_FLAG, " +
									"CALENDAR_NO, " +
									"DEPTID, " +
									"STATISTIC_PERIOD, " +
									"DEPTNAME, " +
									"CALENDAR_DAY, " +
									"CREATE_DATE, " +
									"CREATED_BY, " +
									"UPDATE_DATE, " +
									"UPDATED_BY " +
								"FROM AR_CALENDAR_DEPT_V " +
								"WHERE DEPTID = '" + deptID + "' " +
								"AND TO_CHAR(CALENDAR_DATE,'YYYY-MM')='" + month + "' " +
								"ORDER BY CALENDAR_DATE";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CompanyCalendar companyCalendar = new CompanyCalendar();
				companyCalendar .setCalendarDate(rs.getString("CALENDAR_DATE") != null ? rs.getString("CALENDAR_DATE").substring(0, 10) : "");
				companyCalendar.setCalendarType(rs.getInt("CALENDAR_TYPE"));
				companyCalendar.setWorkFlag(rs.getInt("WORK_FLAG"));
				companyCalendar.setCalendarNo(rs.getInt("CALENDAR_NO"));
				companyCalendar.setDeptid(rs.getString("DEPTID") != null ? rs.getString("DEPTID") : "");
				companyCalendar.setStatisticPeriod(rs.getString("STATISTIC_PERIOD") != null ? rs.getString("STATISTIC_PERIOD") : "");
				companyCalendar.setDepartment(rs.getString("DEPTNAME") != null ? rs.getString("DEPTNAME") : "");
				companyCalendar.setCalendarDay(rs.getString("CALENDAR_DAY") != null ? rs.getString("CALENDAR_DAY") : "");
				companyCalendar.setCreateDate(rs.getString("CREATE_DATE") != null ? rs.getString("CREATE_DATE").substring(0, 10) : "");
				companyCalendar.setCreatedBy(rs.getString("CREATED_BY") != null ? rs.getString("CREATED_BY") : "");
				companyCalendar.setUpdateDate(rs.getString("UPDATE_DATE") != null ? rs.getString("UPDATE_DATE").substring(0, 10) : "");
				companyCalendar.setUpdatedBy(rs.getString("UPDATED_BY") != null ? rs.getString("UPDATED_BY") : "");
				list.add(companyCalendar);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;

	}

	public boolean addCalendarProcedure(String deptID, String year, String createdBy){
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call AR_add_calendardept('" + deptID + "', '" + year + "', '" + createdBy + "') }";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			cs = con.prepareCall(sql);
			return cs.execute();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
			return false;
		} finally {
			SqlUtil.close(cs, con);
		}
	}
}
