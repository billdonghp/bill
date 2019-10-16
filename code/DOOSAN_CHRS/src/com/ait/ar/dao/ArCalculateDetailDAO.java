/*
 * @(#)ArCalculateDetailDAO.java 1.0 2007-11-8 上午11:57:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-11-8 上午11:57:32
 * @version 1.0
 * 
 */
public class ArCalculateDetailDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ArCalculateDetailDAO.class);

	public ArCalculateDetailDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * calculate attendance detail data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public Object calculateAttDetail(List parameterObject)
			throws GlRuntimeException {

		Object result = null ;
		
		try {
			
			commonSQLMapAdapter.insertForMulti("ar.common.calculateAttDetail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Calculate attendance detail data Exception. ", e);
		}
		
		return result;
	}
	
	/**
	 * retrieve date list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDateListForCalc(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.common.retrieveDateListForCalc", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve date list Exception. ", e);
		}
		return list;
	}
	
	/**
	 * retrieve employee list by supervisor
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListBySupervisor(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.common.retrieveEmpListBySupervisor", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve employee list by supervisor Exception. ", e);
		}
		return list;
	}


}
