/*
 * @(#)PostGradeDAO.java 1.0 2007-9-7 上午12:13:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.dao.vouchertype;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil ;


public class VoucherTypeDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(VoucherTypeDAO.class);

	public VoucherTypeDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	
	
	public List getDeptVoucherList(SimpleMap parameterObject) {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("pa.vouchertype.getDeptVoucherList",parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}
	
	public List getDeptVoucherTypeList(SimpleMap parameterObject) {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("pa.vouchertype.getDeptVoucherTypeList",parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}
	
	public void updateDeptVoucherType(List updateList) {
		try {
			commonSQLMapAdapter.updateForMulti("pa.vouchertype.updateDeptVoucherType", updateList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
}

