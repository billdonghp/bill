/*
 * @(#)AccountsInfoDAO.java 1.0 2007-1-5 下午02:03:00
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.empinfo;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-5 下午02:03:00
 * @version 1.0
 * 
 */

public class AccountsInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger
			.getLogger(AccountsInfoDAO.class);

	public AccountsInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * retrieve accounts information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveAccountsInfo(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"hrm.empinfo.retrieveAccountsInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve accounts information Exception. ", e);
		}
		return result;
	}

	/**
	 * insert accounts information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAccountsInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("hrm.empinfo.insertAccountsInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert accounts information Exception. ", e);
		}
	}

	/**
	 * update accounts information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAccountsInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("hrm.empinfo.updateAccountsInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update accounts information Exception. ", e);
		}
	}

	/**
	 * delete accounts information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteAccountsInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("hrm.empinfo.deleteAccountsInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete accounts information Exception. ", e);
		}
	}
}
