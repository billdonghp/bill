package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArShift010;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArShift010Bean {
	private DBConnection db = new DBConnection();

	private ResultSet rs = null;

	private String loginID = null;

	private CommonSQLMapAdapter commonSQLMapAdapter = new CommonSQLMapAdapter(
			"em2");

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);
	

	// 得到SHIFT010
	public ArrayList getShift010() throws Exception {
		AdminBean admin=ApplicationContext.getTheadLocal();
		ArrayList shift010s = new ArrayList();
		String sql = "SELECT SHIFT_NO, SHIFT_NAME,SHIFT_EN_NAME,SHIFT_KOR_NAME FROM AR_SHIFT010  WHERE (CPNY_ID='"+admin.getCpnyId()+"'  OR CPNY_ID IS NULL)  ORDER BY SHIFT_NAME";
		Logger.getLogger(getClass()).debug(sql);
		Connection conn = null;
		conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ArShift010 ar = new ArShift010();
			ar.setShift_no(rs.getInt("shift_no"));
			ar.setShift_Name(rs.getString("shift_name"));
			ar.setShift_En_Name(rs.getString("SHIFT_EN_NAME"));
			ar.setShift_Kor_Name(rs.getString("SHIFT_KOR_NAME"));
			shift010s.add(ar);
		}
		SqlUtil.close(rs, stmt, conn);
		return shift010s;

	}

	/**
	 * retrieve shift list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveShiftList() throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter
					.executeQueryForMulti("ar.common.retrieveShiftList");

		} catch (SQLException e) {

			logger.error("retrieve shift list Exception.");
			throw new GlRuntimeException("retrieve shift list Exception.", e);
		}

		return list;
	}

	// 设定登陆人ID
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	// 验证该员工是否在考勤员的权限内
	public boolean validate(String empID) throws Exception {
		String sql = "select 1 from dual where '" + empID + "'";
		sql += " in (" + db.getArPopedom(loginID) + ")";
		Logger.getLogger(getClass()).debug(sql);
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		if (rs.next()) {
			db.closeConnection();
			return true;
		} else {
			db.closeConnection();
			return false;
		}
	}
}
