/*
 * @(#)AppendInfoDAO.java 1.0 2006-12-21 下午05:19:03
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

public class AppendInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(AppendInfoDAO.class);

	public AppendInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * retrieve additional information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAdditionalInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveAdditionalInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve additional information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert additional information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertAdditionalInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertAdditionalInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert additional information Exception. ", e);
		}
	}

	/**
	 * update additional information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateAdditionalInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateAdditionalInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update additional information Exception. ", e);
		}
	}

	/**
	 * delete additional information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteAdditionalInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteAdditionalInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete additional information Exception. ", e);
		}
	}
}
