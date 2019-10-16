/*
 * @(#)AttDetailLockDAO.java 1.0 2008-3-3 下午09:43:06
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.dao.detaillock;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2008-3-3 下午09:43:06
 * @version 1.0
 * 
 */
public class AttDetailLockDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(AttDetailLockDAO.class);

	public AttDetailLockDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * Retrieve detail lock list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveDetailLockList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.detaillock.retrieveDetailLockList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve detail lock list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * Retrieve detail Supervisor lock list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveDetailSupervisorLockList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.detaillock.retrieveDetailSupervisorLockList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve detail Supervisor lock list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * Retrieve detail Supervisor lock list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveDetailSupervisorLockList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.detaillock.retrieveDetailSupervisorLockList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve detail Supervisor lock list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * Retrieve detail Supervisor lock count
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public int retrieveDetailSupervisorLockCnt(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result =  NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ar.detaillock.retrieveDetailSupervisorLockCnt", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve detail Supervisor lock count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * Update detail lock data
	 * 
	 * @param paramList
	 * @throws GlRuntimeException
	 */
	public void updateDetailLock(List paramList) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.updateForMulti("ar.detaillock.updateDetailLock", paramList, false);
			
			commonSQLMapAdapter.updateForMulti("ar.detaillock.updateDetailSupervisorLock", paramList, false);
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update detail lock data Exception. ", e);
		}
		finally{
			try{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception e){}
		}
	}
	
	/**
	 * Update detail supervisor lock data
	 * 
	 * @param paramList
	 * @throws GlRuntimeException
	 */
	public void updateDetailSupervisorLock(List paramList) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.deleteForMulti("ar.detaillock.deleteDetailSupervisorLock", paramList, false) ;
			
			commonSQLMapAdapter.insertForMulti("ar.detaillock.insertDetailSupervisorLock", paramList, false);
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update detail supervisor lock data Exception. ", e);
		}
		finally{
			try{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception e){}
		}

	}

	/**
	 * Delete detail lock data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteDetailLock(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.detaillock.deleteDetailLock", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete detail lock data Exception. ", e);
		}
	}
	
	/**
	 * validate detail lock data
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateDetailLockData(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = (SimpleMap)commonSQLMapAdapter.executeQuery("ar.detaillock.validateDetailLockData", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate detail lock data Exception. ", e);
		}
		
		return result;
	}

}
