package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArItemByTransation {

	public ArItemByTransation() {
	}

	public void addItemByTransation(Vector vector) throws Exception {
		Connection conn = null;
		Statement state = null;
		try {
			String sql = null;
			conn = ConnBean.getConn();
			state = conn.createStatement();
			for (int i = 0; i < vector.size(); i++) {
				sql = (String)vector.get(i);
				Logger.getLogger(getClass()).debug(sql);
				state.addBatch(sql);
			}
			state.executeBatch();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
	}
}
