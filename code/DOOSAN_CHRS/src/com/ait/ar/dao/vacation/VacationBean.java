/*
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */

package com.ait.ar.dao.vacation;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class VacationBean {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(VacationBean.class);

	public VacationBean() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	/**
	 * retrieve vacation data for update visit card relation
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateVacation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.vacation.retrieveDataForUpdateVacation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve vacation data for update visit card relation  Exception. ", e);
		}
		return result;
	}
	
	/**
	 * update vacation relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateVacation(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.vacation.updateVacation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update vacation relation Exception. ", e);
		}
	}
	
	/**
	 * retrieve vacation holidy list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveVacationList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.vacation.RetrieveVacationList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve vacation list Exception. ", e);
		}
		return list;
	}
	
	
	/**
	 * retrieve vacation holidy list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveVacationStrt_monthList() throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.vacation.retrieveVacationStrt_month");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve vacation list Exception. ", e);
		}
		return list;
	}
	
	/**
	 * retrieve vacation list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveVacationCnt(Object parameterObject)
			throws GlRuntimeException {

		int result;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ar.vacation.RetrieveVacationCnt", parameterObject)
					.toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve Vacation list count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * insert vacation data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertVacation(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.vacation.insertVacation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert vacation data Exception. ", e);
		}
	}
	
	
	/**
	 * delete vacation data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteVacation(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("ar.vacation.deleteVacation", parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete attendance record data by batch Exception. ", e);
		}
	}
	
	public String checkTime(String str) {
		return str.substring(0, str.indexOf(" "));
	}
}

