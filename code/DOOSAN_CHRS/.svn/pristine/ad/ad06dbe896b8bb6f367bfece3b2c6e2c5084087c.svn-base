/*
 * @(#)HealthInfoDAO.java 1.0 2006-12-21 下午05:20:00
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.empinfo;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-21 下午05:23:50
 * @version 1.0
 * 
 */

public class HealthInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HealthInfoDAO.class);

	public HealthInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * retrieve health information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveHealthInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveHealthInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve health information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert health information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertHealthInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("hrm.empinfo.insertHealthInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert health information Exception. ", e);
		}
	}

	/**
	 * update health information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateHealthInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("hrm.empinfo.updateHealthInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update health information Exception. ", e);
		}
	}

	/**
	 * delete health information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteHealthInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("hrm.empinfo.deleteHealthInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete health information Exception. ", e);
		}
	}

}
