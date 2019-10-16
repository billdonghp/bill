package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItemBasic;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArItemBasicBean {
	AdminBean admin=ApplicationContext.getTheadLocal();
	/**
	 * 得到动态组列表;
	 *
	 * @return ArrayList
	 */
	public ArrayList getGROUP() {
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		ArrayList list = null;
		String sql = 
									"SELECT " +
										"GROUP_NO, " +
										"GROUP_NAME,GROUP_EN_NAME,GROUP_KOR_NAME " +
									"FROM AR_DYNAMIC_GROUP where CPNY_ID='"+admin.getCpnyId()+"'";
	  Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			list = new ArrayList();
			while (rs.next()) {
				ArItemBasic info = new ArItemBasic();
				info.setGroupNo(rs.getInt("GROUP_NO"));
				info.setGroupName(rs.getString("GROUP_NAME"));
				info.setEnGroupName(rs.getString("GROUP_EN_NAME"));
				info.setKorGroupName(rs.getString("GROUP_KOR_NAME"));
				list.add(info);
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	/**
	 * 增加BASIC
	 *
	 * @param values ArrayList
	 */
	public int addArItemBasic(ArrayList values) {
		int affRow = 0;
		Connection conn = null;
		Statement state = null;
		String sql = 
									"INSERT INTO AR_ITEM_BASIC(" +
										"ITEM_NO, " +
										"GROUP_NO, " +
										"UNIT, " +
										"TOLERANT, " +
										"MAX, " +
										"MIN, " +
										"SCHEDULE_ITEM_YN, " +
										"ACTIVE_STAT, " +
										"REPLACES" +
									")VALUES(" +
										"'" + values.get(0).toString() + "', " +
										"'" + values.get(1).toString() + "', " +
										"'" + values.get(2).toString() + "', " +
										"'" + values.get(3).toString() + "', " +
										"'" + values.get(4).toString() + "', " +
										"'" + values.get(5).toString() + "', " +
										"'" + values.get(6).toString() + "', " +
										"'" + values.get(7).toString() + "', " +
										"'" + values.get(8).toString() + "'" + ")";
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
	 * addArItemBasic overload
	 * @param values ArrayList
	 * @param transation String
	 * @return Stirng
	 */
	public String addArItemBasic(ArrayList values, String transation) {
		int item_no = Integer.parseInt(values.get(0).toString());
		String group_no = values.get(1).toString();
		String unit = values.get(2).toString();
		int tolerant = Integer.parseInt(values.get(3).toString());
		String max = values.get(4).toString();
		String min = values.get(5).toString();
		String schedule_item_yn = values.get(6).toString();
		String active_stat = values.get(7).toString();
		String replaces = values.get(8).toString();
		String sql = 
			"INSERT INTO AR_ITEM_BASIC(" +
				"ITEM_NO, " +
				"GROUP_NO, " +
				"UNIT, " +
				"TOLERANT, " +
				"MAX, " +
				"MIN, " +
				"SCHEDULE_ITEM_YN, " +
				"ACTIVE_STAT, " +
				"REPLACES" +
			")VALUES(" + 
				item_no + ", " +
				"'" + group_no + "', " +
				"'" + unit + "'," + 
				tolerant + ", " +
				"'" + max + "'," +
				"'" + min + "', " +
				"'" + schedule_item_yn + "', " +
				"'" + active_stat + "', " +
				"'" + replaces + "" +
			"')";

		Logger.getLogger(getClass()).debug("return sql : " + sql);
		return sql;
	}

}
