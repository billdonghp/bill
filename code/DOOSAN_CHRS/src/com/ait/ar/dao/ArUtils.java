package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArUtils {
	
	AdminBean admin=(AdminBean)ApplicationContext.getTheadLocal();

	public String getStartDateStr() {
		GregorianCalendar date = this.getStartDate(new GregorianCalendar());
		Logger.getLogger(getClass()).debug("month value : " + String.valueOf(date.get(GregorianCalendar.MONTH)));
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
	}

	public String getStartDateStr(GregorianCalendar date) {
		date = this.getStartDate(date);
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
	}

	public String getEndDateStr () {
		GregorianCalendar date = this.getEndDate(new GregorianCalendar());
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
	}

	public String getEndDateStr (GregorianCalendar date) {
		date = this.getEndDate(date);
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
	}

	public GregorianCalendar getStartDate(GregorianCalendar date) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT MAX(START_DATE) FROM AR_STATISTIC_DATE WHERE ( TO_DATE(?, 'YYYY-MM-DD') BETWEEN VALID_DATE_FROM AND VALID_DATE_TO ) AND CPNY_ID='"+admin.getCpnyId()+"' ";
			
			pstmt = conn.prepareStatement(sql);
			Logger.getLogger(getClass()).debug("month value : " + String.valueOf(date.get(GregorianCalendar.MONTH)));
			Logger.getLogger(getClass()).debug(new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime()));
			
			pstmt.setString(1, new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (date.get(GregorianCalendar.DAY_OF_MONTH) < rs.getInt(1)) {
					date.add(GregorianCalendar.MONTH, -1);
				}
				date.set(GregorianCalendar.DAY_OF_MONTH, rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return date;
	}

	public GregorianCalendar getEndDate(GregorianCalendar date) {
		date = this.getStartDate(date);
		date.add(GregorianCalendar.MONTH, 1);
		date.add(GregorianCalendar.DAY_OF_MONTH, -1);
		return date;
	}

}
