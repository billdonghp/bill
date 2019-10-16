package com.ait.ar.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ArItemCardBean {
	private DBConnection db = new DBConnection();
	/**
	 * 添加addArItemCard
	 * 
	 * @param values
	 *            ArrayList
	 */
	public void addArItemCard(ArrayList values) {
		int itemNo = ((Integer) values.get(0)).intValue();
		String groupNo = (String) values.get(1);
		int sk = ((Integer) values.get(2)).intValue();
		int from_flag = ((Integer) values.get(3)).intValue();
		String from_relation = (String) values.get(4);
		int from_offset = ((Integer) values.get(5)).intValue();
		int to_flag = ((Integer) values.get(6)).intValue();
		String to_relation = (String) values.get(7);
		int to_offset = ((Integer) values.get(8)).intValue();
		String unit = (String) values.get(9);
		int activity = ((Integer) values.get(10)).intValue();

		String sql = "insert into ar_item_card (item_No,group_No,flag,from_flag,from_relation,from_offset,to_flag,to_relation,to_offset,unit,activity)";
		sql += " values (" + itemNo + ",'" + groupNo + "'," + sk + ","
				+ from_flag + ",'" + from_relation + "'," + from_offset + ","
				+ to_flag + ",'" + to_relation + "'," + to_offset + ",'" + unit
				+ "'," + activity + ")";
		Logger.getLogger(getClass()).debug(sql);
		try {
			db.getConnectionOracle();
			db.update(sql);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			db.closeConnection();
		}
	}

	/**
	 * addArItemCard overload
	 * 
	 * @param values
	 *            ArrayList
	 * @param transation
	 *            String
	 * @return String
	 */
	public String addArItemCard(ArrayList values, String transation) {
		int itemNo = ((Integer) values.get(0)).intValue();
		String groupNo = (String) values.get(1);
		int sk = ((Integer) values.get(2)).intValue();
		int from_flag = ((Integer) values.get(3)).intValue();
		String from_relation = (String) values.get(4);
		int from_offset = ((Integer) values.get(5)).intValue();
		int to_flag = ((Integer) values.get(6)).intValue();
		String to_relation = (String) values.get(7);
		int to_offset = ((Integer) values.get(8)).intValue();
		String unit = (String) values.get(9);
		int activity = ((Integer) values.get(10)).intValue();

		String sql = "insert into ar_item_card (item_No,group_No,flag,from_flag,from_relation,from_offset,to_flag,to_relation,to_offset,unit,activity)";
		sql += " values (" + itemNo + ",'" + groupNo + "'," + sk + ","
				+ from_flag + ",'" + from_relation + "'," + from_offset + ","
				+ to_flag + ",'" + to_relation + "'," + to_offset + ",'" + unit
				+ "'," + activity + ")";
		Logger.getLogger(getClass()).debug("return sql is :" + sql);
		return sql;
	}

}
