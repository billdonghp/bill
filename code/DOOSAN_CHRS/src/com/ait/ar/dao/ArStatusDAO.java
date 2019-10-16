/*
 * @(#)ArStatusDAO.java 1.0 2007-2-8 上午11:57:32
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
 * @Date 2007-2-8 上午11:57:32
 * @version 1.0
 * 
 */
public class ArStatusDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ArStatusDAO.class);

	public ArStatusDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * retrieve monthly status list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveMonthlyStatusList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.common.retrieveMonthlyStatusList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve monthly status list Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve monthly status list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveMonthlyStatusList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.common.retrieveMonthlyStatusList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve monthly status list by paging Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve monthly status list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveMonthlyStatusCnt(Object parameterObject)
			throws GlRuntimeException {

		int result;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ar.common.retrieveMonthlyStatusCnt", parameterObject)
					.toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve monthly status list count Exception. ", e);
		}
		return result;
	}

	/**
	 * insert monthly satatus
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertMonthlyStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertMonthlyStatus",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert monthly satatus Exception. ",
					e);
		}
	}

	/**
	 * update daily attendance status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateDailyAttStatus(Object parameterObject)
			throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();

			commonSQLMapAdapter.update("ar.common.updateDailyAttStatus",parameterObject);
			
			// update daily status
			commonSQLMapAdapter.update("ar.detaillock.updateDetailLock", parameterObject);
			
			// update daily Supervisor status
			commonSQLMapAdapter.update("ar.detaillock.updateDetailSupervisorLock", parameterObject);
			
			commonSQLMapAdapter.commitTransation() ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Update daily attendance status Exception. ", e);
		}
		finally {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	/**
	 * update monthly attendance status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateMonthlyAttStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.common.updateMonthlyAttStatus",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Update monthly attendance status Exception. ", e);
		}
	}

	/**
	 * update monthly pay status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateMonthlyPayStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.common.updateMonthlyPayStatus",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Update monthly pay status Exception. ", e);
		}
	}

	/**
	 * update daily status and monthly status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAttStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			// update daily status
			commonSQLMapAdapter.update("ar.common.updateDailyAttStatus", parameterObject);

			// update monthly status
			commonSQLMapAdapter.update("ar.common.updateMonthlyAttStatus", parameterObject);

			// update daily status
			commonSQLMapAdapter.update("ar.detaillock.updateDetailLock", parameterObject);
			
			// update daily Supervisor status
			commonSQLMapAdapter.update("ar.detaillock.updateDetailSupervisorLock", parameterObject);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Update daily status and monthly status Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	/**
	 * update daily status,monthly status and pay status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePayStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			// update daily status
			commonSQLMapAdapter.update("ar.common.updateDailyAttStatus",
					parameterObject);

			// update monthly status
			commonSQLMapAdapter.update("ar.common.updateMonthlyAttStatus",
					parameterObject);

			// update pay status
			commonSQLMapAdapter.update("ar.common.updateMonthlyPayStatus",
					parameterObject);
			
			// update daily status
			commonSQLMapAdapter.update("ar.detaillock.updateDetailLock", parameterObject);
			
			// update daily Supervisor status
			commonSQLMapAdapter.update("ar.detaillock.updateDetailSupervisorLock", parameterObject);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Update daily status,monthly status and pay status Exception. ",
					e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	public List kretrieveMonthlyStatusList(Object parameterObject)
	throws GlRuntimeException {

	List list;
	try {
	
		list = commonSQLMapAdapter.executeQueryForMulti(
				"ar.common.kretrieveMonthlyStatusList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve monthly status list Exception. ", e);
	}
	return list;
	}

}
