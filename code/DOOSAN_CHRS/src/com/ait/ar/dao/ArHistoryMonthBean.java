package com.ait.ar.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArHistoryMonth;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArHistoryMonthBean {

	private DBConnection db = new DBConnection();

	private ResultSet rs = null;

	private ResultSetMetaData rsm = null;

	public ArrayList data = null;

	private String loginID = null;

	private CommonSQLMapAdapter commonSQLMapAdapter = new CommonSQLMapAdapter("em2");

	// 返回考勤汇总列表
	public ArrayList getArHistory(String fArMonth, String tArMonth, String type, String key) throws Exception {
		// String nbsp = null;
		String colsname = getclosName(tArMonth, type);
		String sql = "select " + "CHINESENAME, " + "ar_history_month." + colsname + " " + "from(" + "ar_history_month " + "inner join hr_employee_full_info " + "on hr_employee_full_info.empid = ar_history_month.empid" + ") " + "where(" + "("
				+ "hr_employee_full_info.EMPID like '%" + key + "%'" + ") " + "or (" + "hr_employee_full_info.CHINESENAME like '%" + key + "%'" + ") " + "or (" + "hr_employee_full_info.DEPTID like '%" + key + "%'" + ") " + "or (" + "hr_employee_full_info.DEPARTMENT like '%"
				+ key + "%'" + ") " + "or (" + "hr_employee_full_info.SEX like '%" + key + "%'" + ")" + ")" + "and (" + "ar_month_str BETWEEN '" + fArMonth + "' " + "and '" + tArMonth + "'" + ")" + "and ar_history_month.EMPID in (" + db.getArPopedom(loginID) + ")" + /* 联合考勤员权限 */
				"order by ar_month_str desc";
		Logger.getLogger(getClass()).debug(sql);
		ArrayList rss = new ArrayList();
		try {
			if (type.equals("month")) {
				executeHistoryProc(fArMonth);// 汇总考勤数据
			}
			ArHistoryMonth ar = null;
			db.getConnectionOracle();
			rs = db.getResultSet(sql);
			rsm = rs.getMetaData();
			int cols = rsm.getColumnCount();
			while (rs.next()) {
				ArrayList data1 = new ArrayList();
				for (int i = 1; i <= cols; i++) {
					data1.add(this.convertFormat(checkNull(rs.getString(i))));
				}
				ar = new ArHistoryMonth();
				ar.setData(data1);
				ar.setEmpID(rs.getString("empid"));
				ar.setMonthStr(rs.getString("ar_month_str"));
				ar.setLock_yn(rs.getString("lock_yn"));
				rss.add(ar);
			}
			db.closeConnection();
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return rss;
	}

	// 得到考勤表格名称
	public ArrayList getTables(String ArMonth, String type) throws Exception {

		String colsname = getclosName(ArMonth, type);
		String sql = "select " + colsname + " from ar_history_month";
		Logger.getLogger(getClass()).debug(sql);
		ArrayList tables = new ArrayList();
		String temp = null;
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		rsm = rs.getMetaData();
		int cols = rsm.getColumnCount();
		for (int i = 1; i <= cols; i++) {
			temp = rsm.getColumnName(i);
			tables.add(temp);
		}
		db.closeConnection();
		return tables;
	}

	// 设定登陆人ID
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	// 取得查询月份的标准开始日期
	public String getBeginDate(String month) {
		String beginDate = null;
		try {
			String sql = "select TO_CHAR(Ar_Get_Startdate('" + month + "'),'yyyy-mm-dd') from dual";
			Logger.getLogger(getClass()).debug(sql);
			db.getConnectionOracle();
			rs = db.getResultSet(sql);
			if (rs.next())
				beginDate = rs.getString(1);
			db.closeConnection();
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return beginDate;
	}

	// 取得查询月份的标准结束日期
	public String getEndDate(String month) {
		String endDate = null;
		try {
			String sql = "select TO_CHAR(Ar_Get_Enddate('" + month + "'),'yyyy-mm-dd') from dual";
			Logger.getLogger(getClass()).debug(sql);
			db.getConnectionOracle();
			rs = db.getResultSet(sql);
			if (rs.next())
				endDate = rs.getString(1);
			db.closeConnection();
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return endDate;
	}

	/* 执行月考勤查看的存储过程 */
	public void executeHistoryProc(String date) {
		try {
			String beginDate = this.getBeginDate(date);
			String endDate = this.getEndDate(date);
			// 做汇总计算
			String sql = "{call Ar_arCAL(to_date('" + beginDate + "','yyyy-mm-dd'),to_date('" + endDate + "','yyyy-mm-dd'),'" + loginID + "')}";
			Logger.getLogger(getClass()).debug(sql);
			db.getConnectionOracle();
			db.updatePROC(sql);
			db.closeConnection();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
	}

	/* 转化日期类型格式为“yyyy-mm-dd” */
	public String convertFormat(String s) {
		// if(s.indexOf("00.00.00")!=-1){
		if (s.length() >= 20) {
			return s.substring(0, s.indexOf(" "));
		} else
			return s;
	}

	public String getMonth(int month) {
		String temp = Integer.toString(month);
		if (month < 10 && temp.length() < 2)
			temp = "0" + temp;
		return temp;
	}

	public String getDay(int day) {
		String temp = Integer.toString(day);
		if (day < 10 && temp.length() < 2)
			temp = "0" + temp;
		return temp;
	}

	public String getclosName(String ArMonth, String type) {
		String sql = "call ar_html_sql_history(?,?,?)";
		Logger.getLogger(getClass()).debug(sql);
		Connection conn = null;
		CallableStatement proc = null;
		String colsname = null;
		try {
			conn = ConnBean.getConn();
			proc = conn.prepareCall(sql);
			proc.setString(1, ArMonth);
			proc.setString(2, type);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			colsname = proc.getString(3).trim();
			if (colsname.substring(colsname.length() - 1).equals(","))
				colsname = colsname.substring(0, colsname.length() - 2);
		} catch (SQLException sqlex) {
			Logger.getLogger(getClass()).error(sqlex.toString());
		}
		finally {
			SqlUtil.close(proc, conn);
		}
		return colsname;
	}

	public String checkNull(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	public void delArHistroyMonth(String empid, String arMonthStr) throws Exception {
		String sql = "delete from ar_history_month " + "where empid='" + empid + "' " + "and ar_month_str='" + arMonthStr + "' ";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		db.update(sql);
		db.closeConnection();
	}

	public void updateArHistroyMonth(String sql) throws Exception {
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		db.update(sql);
		db.closeConnection();
	}

	/**
	 * retrieve monthly attendance information
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getArData(Object parameterObject) {

		List list = null;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttMonthly", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance information Exception. ", e);
		}
		return list;
	}
	
	/**
	 * retrieve ar Personal information
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getArPersonalData(Object parameterObject) {

		Object result = null;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.common.RetrieveArPersonalData", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve ar Personal information Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve monthly attendance information for paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 */
	public List getArData(Object parameterObject, int currentPage, int pageSize) {

		List list = null;

		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttMonthly", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve monthly attendance count
	 * 
	 * @param parameterObject
	 * @return int
	 */
	public int getArDataCnt(Object parameterObject) {

		int result;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery("ar.common.RetrieveAttMonthlyCnt", parameterObject).toString());

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance count information Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve monthly attendance report
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveMonthlyAttReport(Object parameterObject) {

		List list = null;

		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveMonthlyAttReport", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance report Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve monthly attendance detail report
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveMonthlyAttDetailReport(Object parameterObject) {

		List list = null;

		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveMonthlyAttDetailReport", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance detail report Exception. ", e);
		}
		return list;
	}
	
	
	
	
	public List retrieveMonthlyConsumptioDisReport(Object parameterObject) {

		List list = null;
  
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveMonthlyConsumptioDis", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance detail report Exception. ", e);
		}
		return list;
	}
	
	public List retrieveMonthlyeatryReport(Object parameterObject) {

		List list = null;
  
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveMonthlyeatryReport", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance detail report Exception. ", e);
		}
		return list;
	}
	
	public List retrieveVisitoridCardReport(Object parameterObject) {

		List list = null;
  
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveVisitoridCardReport", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance detail report Exception. ", e);
		}
		return list;
	}
	

	public List retrieveDailyOtReport(Object parameterObject) {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveDailyOtReport", parameterObject);

		} catch (Exception e) {              
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance detail report Exception. ", e);
		}
		return list;
	}
	
	
	public List retrieveOverTimeDailyReport(Object parameterObject) {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveOverTimeDailyReport", parameterObject);

		} catch (Exception e) {              
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve monthly attendance detail report Exception. ", e);
		}
		return list;
	}
	
	
	public List<SimpleMap> getArColumns(String cpnyId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COLUMN_NAME, ITEM_NAME, ITEM_EN_NAME, ITEM_KOR_NAME, GET_CODE_NAME(UNIT) UNIT FROM USER_TAB_COLUMNS A, AR_STA_ITEM B "
					+ " WHERE TABLE_NAME = 'AR_HISTORY'  AND DATA_TYPE = 'NUMBER' AND COLUMN_NAME = STA_ITEM_ID " 
					+ " AND (B.CPNY_ID IS NULL OR B.CPNY_ID ='" + cpnyId + "')" 
					+ " AND NOT EXISTS (SELECT * FROM PA_DISTINCT_LIST WHERE COLUMN_NAME = FIELD_NAME AND TABLE_NAME = 'PA_HR_V')" 
					+ " ORDER BY B.CAL_ORDER ";
         
		Logger.getLogger(getClass()).debug(sql);
		List<SimpleMap> list = new ArrayList();
		//String columns = "";
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SimpleMap map = new SimpleMap();
				map.set("COLUMN_NAME", rs.getString("COLUMN_NAME"));
				map.set("ITEM_NAME", rs.getString("ITEM_NAME"));
				map.set("ITEM_EN_NAME", rs.getString("ITEM_EN_NAME"));
				map.set("ITEM_KOR_NAME", rs.getString("ITEM_KOR_NAME"));
				map.set("UNIT", rs.getString("UNIT"));
				list.add(map);
			}
				
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
	}

	public String getArColumns2(String cpnyId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT column_name " +
					 "FROM user_tab_columns A, "+
					 " 	   AR_STA_ITEM B "+
					 "WHERE table_name = 'AR_HISTORY' "+
					   "AND data_type = 'NUMBER' "+
					   "AND A.COLUMN_NAME = B.STA_ITEM_ID "+
					   "AND (B.CPNY_ID IS NULL OR B.CPNY_ID ='" + cpnyId + "') "+
					   "AND NOT EXISTS (SELECT * "+
					   					 "FROM pa_distinct_list "+
					                    "WHERE column_name = field_name AND table_name = 'PA_HR_V')";
		Logger.getLogger(getClass()).debug(sql);
		String columns = "";
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
				if (columns.equals(""))
					columns = "\"" + rs.getString("COLUMN_NAME").trim() + "\"\n ";
				else
					columns = columns + ",\"" + rs.getString("COLUMN_NAME").trim() + "\"\n ";
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		//Logger.getLogger(getClass()).debug("Attendance columns: " + columns);
		return columns;
	}
	
	/**
	 * retrieve ar email
	 * 
	 * @param parameterObject
	 * @return int
	 */
	public String getArEmail(Object parameterObject) {

		String result;
		try {

			result = (String)commonSQLMapAdapter.executeQuery("ar.common.getArEmail", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve getArEmail information Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve ar email
	 * 
	 * @param parameterObject
	 * @return int
	 */
	public String getArDetailEmail(Object parameterObject) {

		String result;
		try {

			result = (String)commonSQLMapAdapter.executeQuery("ar.common.getArDetailEmail", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve getArEmail information Exception. ", e);
		}
		return result;
	}
	
	
	
}
