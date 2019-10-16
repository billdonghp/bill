/*
 * @(#)InfoDAO.java 1.0 2007-1-11 下午02:49:45
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.report;

import java.util.List;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.hrcommand.DispatchComDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2007-1-11 下午02:49:45
 * @version 1.0
 * 
 */
public class InfoDAO {

	private CommonSQLMapAdapter sqlMap = null;

	private static final Logger logger = Logger.getLogger(DispatchComDAO.class);

	public InfoDAO() {
		sqlMap = new CommonSQLMapAdapter("em2");
	}

	/**
	 * 取得搜索信息类型
	 * @return
	 */
	public List getInfoType() {
		List list;
		try {
			list = sqlMap.executeQueryForMulti("hrm.report.getInfoType");
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得搜索信息类型失败！", e);
		}
		return list;
	}

	/**
	 * 取得搜索信息类型表
	 * @return
	 */
	public List getInfoTable(Object param) {
		List list;
		try {
			list = sqlMap.executeQueryForMulti("hrm.report.getInfoTable", param);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得搜索信息类型表失败！", e);
		}
		return list;
	}

	/**
	 * 取得搜索信息类型表列
	 * @return
	 */
	public List getInfoField(Object param) {
		List list;
		try {
			list = sqlMap.executeQueryForMulti("hrm.report.getInfoField", param);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("取得搜索信息类型表列失败！", e);
		}
		return list;
	}
}
