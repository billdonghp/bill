/*
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */

package com.ait.ar.dao.vacation;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class VacationEmpDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(VacationBean.class);

	public VacationEmpDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	
	/**
	 * Retrieve VacationEmpREST_VAC_CNT
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVacationEmpREST_VAC_CNT(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.vacation.RetrieveVacationEmpREST_VAC_CNT", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Validate vacationEmp date Exception. ", e);
		}
		return result;
	}
	
	/**
	 * validate empid and vac_tp and strt_date and end date
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateVacationEmp(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.vacation.validateVacationEmp", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Validate vacationEmp date Exception. ", e);
		}
		return result;
	}
	
	/**
	 * validate empid and tot_vac_cnt
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateVacationEmpByUpdate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.vacation.validateVacationEmpByUpdate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Validate vacationEmp date Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve vacationEmp data for update vacationEmp relation
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateVacationEmp(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.vacation.retrieveDataForUpdateVacationEmp", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve vacationEmp data for update visit card relation  Exception. ", e);
		}
		return result;
	}
	
	/**
	 * update vacationEmp relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateVacationEmp(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.vacation.updateVacationEmp", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update vacationEmp relation Exception. ", e);
		}
	}
	
	/**
	 * retrieve vacation holidy list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveVacationEmpList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.vacation.RetrieveVacationEmpList", parameterObject,
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
	 * @throws GlRuntimeException
	 */
	public List retrieveVacationEmpList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.vacation.RetrieveVacationEmpList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve vacation list Exception. ", e);
		}
		return list;
	}
	
	/**
	 * init vacationEmp 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void initVacationEmp() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.executeQuery("ar.vacation.InitVacationEmp");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve init vacationEmp Exception. ", e);
		}
	}
	
	/**
	 * retrieve vacation list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveVacationEmpCnt(Object parameterObject)
			throws GlRuntimeException {

		int result;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ar.vacation.RetrieveVacationEmpCnt", parameterObject)
					.toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve VacationEmp list count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * insert vacationEmp data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertVacationEmp(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.vacation.insertVacationEmp", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert vacationEmp data Exception. ", e);
		}
	}
	
	
	/**
	 * delete vacation data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteVacationEmp(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("ar.vacation.deleteVacationEmp", parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete attendance record data by batch Exception. ", e);
		}
	}
	
	public String checkTime(String str) {
		return str.substring(0, str.indexOf(" "));
	}
	
	/**
	 * retrieve arfactroytotal list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArFactroyTotalList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.vacation.RetrieveArFactroyTotalList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve arfactroytotal list Exception. ", e);
		}
		return list;
	}
	
	
	/**
	 * retrieve arfactroytotal sum
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArFactroyTotalListSum(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ar.vacation.RetrieveArFactroyTotalListSum", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve arfactroytotal sum Exception. ", e);
		}
		return  list;
	}
}

