package com.ait.ga.dao.smock;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangbin
 * @Date 2009-7-22
 * @version 1.0
 */

public class SmockDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(SmockDAO.class);

	public SmockDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * select smock list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List selectSmockList(Object parameterObject)
			throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"ga.smock.selectSmockList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("select smock data Exception. ", e);
		}
		return list;
	}

	/**
	 * select smock list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List selectSmockList(Object parameterObject, int currentPage,
			int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"ga.smock.selectSmockList", parameterObject, currentPage,
					pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("select smock data by paging Exception. ", e);
		}
		return list;
	}

	/**
	 * select smock number
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object selectSmockNumber(Object parameterObject)
			throws GlRuntimeException {
		Object object = null;
		try {
			object = commonSQLMapAdapter.executeQuery(
					"ga.smock.selectSmockCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("select smock number Exception. ", e);
		}
		return object;
	}

	/**
	 * insert smock
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertSmock(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.insert("ga.smock.insertSmock", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("insert smock data Exception. ", e);
		}
	}

	/**
	 * update smock
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateSmock(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.update("ga.smock.updateSmock", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("update smock data Exception. ", e);
		}
	}

	/**
	 * delete smock
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteSmcok(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.update("ga.smock.deleteSmock", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete smock data Exception. ", e);
		}
	}
	
	/**
	 * get smock code name 
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getSmockCodeName(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smock.getSmockCodeName", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("get smock code name data Exception. ",e);
		}
		return list;
	}
	
	/**
	 *  get smock total record
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockTotalRecord(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smock.getSmockTotalRecord", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get smock total record data Exception. ",e);
		}
		return  list;
	}
	
	/**
	 * get smock provide record
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getSmockProvideRecord(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try {
			list  = commonSQLMapAdapter.executeQueryForMulti("ga.smock.getSmockProvideRecord", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get smock provide record data Exception. ",e);
		}
		return list ;
	}
	
	public List getSmockPeriodReport(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smock.getSmockPeriodReport", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("get smock period report data Exception .",e);
		}
		return list;
	}
}
