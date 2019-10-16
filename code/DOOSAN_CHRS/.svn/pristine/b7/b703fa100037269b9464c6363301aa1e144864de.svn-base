package com.ait.ess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArAnnualList {

	public ArAnnualList() {
	}

	public static Hashtable getArAnnualList(String Empid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//ArAnnualList annual = null;
		Hashtable hash = null;
		String sql = " select * from ar_annual_list_V where empid =?";
		Logger.getLogger(ArAnnualList.class).debug(sql);
		try {
			//annual = new ArAnnualList();
			con = ConnBean.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, Empid);
			rs = ps.executeQuery();
			if (rs.next()) {
				hash = new Hashtable();
				hash.put("year_days", new Integer(rs.getInt("YEAR_DAYS")));
				hash.put("usered_days", new Integer(rs
						.getInt("USERED_YEAR_DAYS")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ArAnnualList.class).error(ex.toString());
		} finally {
			SqlUtil.close(rs, ps, con);
		}
		return hash;
	}
}