package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArCalendarType;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArCalendarTypeBean {
	public ArrayList getCalendarType(){
		ArrayList items = null;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String sql = 
								"SELECT " +
									"TYPEID, " +
									"TYPENAME,TYPE_EN_NAME,TYPE_KOR_NAME " +
								"FROM AR_CALENDER_TYPE";
		Logger.getLogger(getClass()).debug(sql);
		try{
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			items = new ArrayList();
			while (rs.next()) {
				ArCalendarType ar = new ArCalendarType();
				ar.setTypeNo(rs.getInt("TYPEID"));
				ar.setTypeName(rs.getString("TYPENAME"));
				ar.setEnTypeName(rs.getString("TYPE_EN_NAME"));
				ar.setKorTypeName(rs.getString("TYPE_KOR_NAME"));
				items.add(ar);
			}
		}catch(SQLException sqlex){
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			SqlUtil.close(rs, state, conn);
		}
		return items;
	}
}
