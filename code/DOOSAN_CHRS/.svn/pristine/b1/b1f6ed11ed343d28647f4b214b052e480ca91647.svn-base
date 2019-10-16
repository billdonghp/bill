package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Sydept;
import com.ait.util.SqlUtil;

public class SydeptBean {
	private DBConnection db = new DBConnection();

	private ResultSet rs = null;

	public SydeptBean() {
	}

	public ArrayList getEmpids(String deptid) throws Exception {
		ArrayList list = new ArrayList();
		String sql = 
				"select * " +
				"from hr_employee_v " +
				"where ACTIVITY = 1 and deptid='" + deptid + "' ";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			Sydept info = new Sydept();
			info.setEmpid(rs.getString("empid"));
			info.setEmpname(rs.getString("chinesename"));
			info.setDeptid(rs.getString("deptid"));
			info.setDeptname(rs.getString("department"));
			info.setStatus(rs.getString("status"));
			list.add(info);
		}
		db.closeConnection();
		return list;
	}

	public int updateDeptDD(ArrayList sqlValues) throws Exception {
		int affRow = 0;
		Connection conn = db.getConnectionOracle();
		Statement state = null;
		for(int i = 0 ; (sqlValues != null) && (i < sqlValues.size()); i++){
			String sql = (String)sqlValues.get(i);
			if(sql != null){
				state = conn.createStatement();
				affRow += state.executeUpdate(sql);
				Logger.getLogger(getClass()).debug(sql + " affect row : [" + affRow + "]");
				state.close();
			}
		}
		SqlUtil.close(rs, state, conn);
		Logger.getLogger(getClass()).debug("affect row : " + affRow);
		return affRow;
	}
}
