package com.ait.ar.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetailStat;
                       
public class ArDetailStatBean {
	private DBConnection db = new DBConnection();

	private ResultSet rs = null;                
                    
	/**
	 * getDetailStat                                         
	 *
	 * @param deptID String
	 * @param ar_month String
	 * @return ArrayList
	 */
	public ArrayList getDetailStat(String deptID, int year, int month)
			throws Exception {
		String ar_month = year + this.getMonth(month);
		ArrayList empIDs = this.getEmps(deptID);
		ArrayList empIDStats = new ArrayList();
		ArrayList empStats = new ArrayList();
		String sql = null;
		db.getConnectionOracle();
		for (int i = 0; i < empIDs.size(); i++) {
			sql = 
						"select " +
						"empid, " +
						"stat, to_char(to_date(ar_date_str,'yyyy/mm/dd'),'dd') as d " +
						"from AR_DETAIL_STAT where empid='" + (String) empIDs.get(i) + "' " +
						"and get_armonth(to_date(ar_date_str,'yyyy/mm/dd'))='" + ar_month + "' ";
			Logger.getLogger(getClass()).debug(sql);
			rs = db.getResultSet(sql);
			while (rs.next()) {//取一个人的 一个月数据
				ArDetailStat ar = new ArDetailStat();
				ar.setEmpID(rs.getString("empid"));
				ar.setStat(rs.getInt("stat"));
				ar.setDay(rs.getInt("d"));
				empIDStats.add(ar);
			}
			empStats.add(empIDStats);//保存每个人
		}
		db.closeConnection();
		return empStats;
	}

	/**
	 * statEmpid
	 *
	 * @param empID String
	 */
	public void statEmpid(String empID, int year, int month) throws Exception {
		String ar_month = year + this.getMonth(month);
		String sql = 
									"update ar_detail_stat set " +
									"stat=0 " +
									"where empid='" + empID + "' " +
									"and get_armonth(to_date(ar_date_str,'yyyy/mm/dd'))='" + ar_month + "' ";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		db.update(sql);
		db.closeConnection();
	}

	/**
	 * statClos
	 *
	 * @param arMonth String
	 * @param day int
	 */
	public void statClos(int year, int month, int day) throws Exception {
		String ar_month = year + this.getMonth(month);
		String sql = 
								"update ar_detail_stat set " +
								"stat=0 " +
								"where get_armonth(to_date(ar_date_str,'yyyy/mm/dd'))='" + ar_month + "' " +
								"and to_char(to_date(ar_date_str,'yyyy/mm/dd'),'dd')=" + day;
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		db.update(sql);
		db.closeConnection();
	}

	/**
	 * statDay
	 *
	 * @param empID String
	 * @param arMonth String
	 * @param day int
	 */
	public void statDay(String empID, int year, int month, int day)
			throws Exception {
		String ar_month = year + this.getMonth(month);
		String sql = "update ar_detail_stat set stat=0 where get_armonth(to_date(ar_date_str,'yyyy/mm/dd'))='"
				+ ar_month + "'";
		sql += " and to_char(to_date(ar_date_str,'yyyy/mm/dd'),'dd')=" + day
				+ " and empid='" + empID + "'";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		db.update(sql);
		db.closeConnection();
	}

	/**
	 * getClos
	 *
	 * @param arMonth String
	 * @return ArrayList
	 */
	public ArrayList getClos(int year, int month) throws Exception {
		String ar_month = year + this.getMonth(month);
		ArrayList clos = new ArrayList();
		String sql = "select iday from ar_calender where get_armonth(ddate)='"
				+ ar_month + "'";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			clos.add(Integer.toString(rs.getInt("iday")));
		}
		db.closeConnection();
		return clos;
	}

	/**
	 * getDay
	 *
	 * @param ar_date_str String
	 * @return int
	 */
	public int getDay(String ar_date_str) {
		int day = 0;
		ar_date_str = ar_date_str.substring(ar_date_str.lastIndexOf("/"),
				ar_date_str.length());
		day = Integer.parseInt(ar_date_str);
		return day;
	}

	/**
	 * getEmps
	 *
	 * @param deptID String
	 * @return ArrayList
	 */
	public ArrayList getEmps(String deptID) throws Exception {
		ArrayList empIDs = new ArrayList();
		String sql = "select * from hr_employee where ACTIVITY = 1 AND deptid='" + deptID + "'";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			empIDs.add(rs.getString("empid"));
		}
		db.closeConnection();
		return empIDs;
	}

	public String getMonth(int month) {
		String temp = Integer.toString(month);
		if (month < 10 && temp.length() < 2)
			temp = "0" + temp;
		return temp;
	}

}
