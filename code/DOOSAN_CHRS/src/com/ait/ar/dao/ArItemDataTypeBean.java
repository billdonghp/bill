package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArItemDataTypeBean {
	/**
	 * 添加addArItemDataType
	 *
	 * @param values ArrayList
	 */
	public int addArItemDataType(ArrayList values) {
		int affRow = 0;
		Connection conn = null;
		Statement state = null;
		String sql = 
			"INSERT INTO AR_ITEM_DATATYPE(" +
			"ITEM_NO, " +
			"GROUP_NO, " +
			"DATATYPE, " +
			"ACTIVITY" +
		")VALUES(" +
			"'" + values.get(0).toString() + "', " +
			"'" + values.get(1).toString() + "', " +
			"'" + values.get(2).toString() + "', " +
			"'" + values.get(3).toString() + "'" +
		")";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			affRow = state.executeUpdate(sql);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
		return affRow;
	}

	/**
	 * addArItemDataType overload
	 * @param values ArrayList
	 * @param transation String
	 * @return String
	 */
	public String addArItemDataType(ArrayList values, String transation) {
		int item_No = Integer.parseInt(values.get(0).toString());
		String group_No = values.get(1).toString();
		String datatype = values.get(2).toString();
		int activity = Integer.parseInt(values.get(3).toString());
		String sql = "insert into ar_item_datatype (item_No,group_No,datatype,activity)";
		sql += " values (" + item_No + ",'" + group_No + "','" + datatype
				+ "'," + activity + ")";
		Logger.getLogger(getClass()).debug("return sql is : " + sql);
		return sql;
	}

}
