/*
 * @(#)PresentDAO.java 1.0 2009-7-13 下午01:47:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.dao.washhouse;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-13 下午01:47:53
 * @version 1.0
 * 
 */
public class WashhouseDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(WashhouseDAO.class);

	public WashhouseDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * Retrieve clothing list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveClothingList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveClothingList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve clothing list error." + e);
			throw new GlRuntimeException("Retrieve clothing list error.", e);
		}

		return result;
	}

	/**
	 * Retrieve clothing list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveClothingList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveClothingList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve clothing list by paging error." + e);
			throw new GlRuntimeException("Retrieve clothing list by paging error.", e);
		}

		return result;
	}

	/**
	 * Retrieve clothing list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveClothingListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.washhouse.retrieveClothingListCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve clothing list count error." + e);
			throw new GlRuntimeException("Retrieve clothing list count error.", e);
		}

		return result;
	}
	
	/**
	 * Retrieve clothing code
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveClothingCode(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveClothingCode", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve clothing code error." + e);
			throw new GlRuntimeException("Retrieve clothing code error.", e);
		}

		return result;
	}

	/**
	 * Insert clothing
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertClothing(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.washhouse.insertClothing", parameterObject);
		} catch (Exception e) {

			logger.error("Insert clothing error." + e);
			throw new GlRuntimeException("Insert clothing error.", e);
		}
	}

	/**
	 * Update clothing
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateClothing(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.washhouse.updateClothing", parameterObject);
		} catch (Exception e) {

			logger.error("Update clothing error." + e);
			throw new GlRuntimeException("Update clothing error.", e);
		}
	}

	/**
	 * Delete clothing
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteClothing(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.washhouse.deleteClothing", parameterObject);
		} catch (Exception e) {

			logger.error("Delete present error." + e);
			throw new GlRuntimeException("Delete clothing error.", e);
		}
	}
	
	/**
	 * Retrieve wash apply list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveWashApplyList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveWashApplyList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve wash apply list error." + e);
			throw new GlRuntimeException("Retrieve wash apply list error.", e);
		}

		return result;
	}
	
	/**
	 * Retrieve wash apply list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveWashApplyList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveWashApplyList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve wash apply list by paging error." + e);
			throw new GlRuntimeException("Retrieve wash apply list by paging error.", e);
		}

		return result;
	}
	
	/**
	 * Retrieve wash apply list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveWashApplyExcelList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveWashApplyExcelList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve wash apply list by paging error." + e);
			throw new GlRuntimeException("Retrieve wash apply excel list  error.", e);
		}

		return result;
	}

	/**
	 * Retrieve wash apply count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveWashApplyCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.washhouse.retrieveWashApplyCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve wash apply count error." + e);
			throw new GlRuntimeException("Retrieve wash apply count error.", e);
		}

		return result;
	}
	
	/**
	 * Retrieve wash apply detail
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveWashApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.retrieveWashApplyDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve wash apply detail error." + e);
			throw new GlRuntimeException("Retrieve wash apply detail error.", e);
		}

		return result;
	}

	/**
	 * Insert wash apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertWashApply(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.washhouse.insertWashApply", parameterObject);
		} catch (Exception e) {

			logger.error("Insert wash apply error." + e);
			throw new GlRuntimeException("Insert wash apply error.", e);
		}
	}
	
	/**
	 * Insert wash apply detial
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertWashApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.washhouse.insertWashApplyDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Insert wash apply detial error." + e);
			throw new GlRuntimeException("Insert wash apply detail error.", e);
		}
	}
	
	/**
	 * Insert wash apply detail multi
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertWashApplyDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.washhouse.insertWashApplyDetail", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert wash apply detial by multi error." + e);
			throw new GlRuntimeException("Insert wash apply detail multi error.", e);
		}
	}
	
	/**
	 * Get current apply No
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object getCurrApplyNo() throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.washhouse.getCurrApplyNo");
		} catch (Exception e) {

			logger.error("Get current apply No error." + e);
			throw new GlRuntimeException("Get current apply No error.", e);
		}

		return result;
	}
	
	/**
	 * apply wash
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object applyWash(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// insert apply data
			this.insertWashApply(parameterObject);
			
			// insert apply detail data
			List detailList = (List)parameterObject.get("detail");
			this.insertWashApplyDetail(detailList, false);
			
			// get apply No
			Object result = this.getCurrApplyNo();
			
			commonSQLMapAdapter.commitTransation();
			
			return result;
			
		} catch (Exception e) {

			logger.error("Apply wash error." + e);
			throw new GlRuntimeException("Apply wash error.", e);
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
	 * update wash apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateWashApply(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.washhouse.updateWashApply", parameterObject);
		} catch (Exception e) {

			logger.error("Update wash apply error." + e);
			throw new GlRuntimeException("Update wash apply error.", e);
		}
	}
	
	/**
	 * Update wash apply by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateWashApply(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("ga.washhouse.updateWashApply", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Update wash apply by batch error." + e);
			throw new GlRuntimeException("Update wash apply by batch error.", e);
		}
	}
	
	/**
	 * Update wash detail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateWashDetail(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.washhouse.updateWashDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Update wash detail error." + e);
			throw new GlRuntimeException("Update wash detail error.", e);
		}
	}
	
	/** 
	 * Update wash detail by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateWashDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.updateForMulti("ga.washhouse.updateWashDetail", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Update wash detail by batch error." + e);
			throw new GlRuntimeException("Update wash detail batch error.", e);
		}
	}
	
	/**
	 * Confirm wash by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void confirmWash(List parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// update apply data
			this.updateWashApply(parameterObject, false);
			
			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {

			logger.error("Confirm wash by batch error." + e);
			throw new GlRuntimeException("Confirm wash error.", e);
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
	 * get wash affirm mail
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getWashAffirmMail(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.getAffirmMail", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve affirm mail error." + e);
			throw new GlRuntimeException("Retrieve affirm mail error.", e);
		}

		return result;
	}
	
	/**
	 * get wash apply Statistics by person
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getWashStatisticsByPerson(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.getWashStatisticsByPerson", parameterObject);
		} catch (Exception e) {

			logger.error("Get wash apply Statistics by person error." + e);
			throw new GlRuntimeException("Get wash apply Statistics by person error.", e);
		}

		return result;
	}
	
	/**
	 * get wash apply Statistics by department
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getWashStatisticsByDept(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.washhouse.getWashStatisticsByDept", parameterObject);
		} catch (Exception e) {

			logger.error("Get wash apply Statistics by dept error." + e);
			throw new GlRuntimeException("Get wash apply Statistics by dept error.", e);
		}

		return result;
	}
}
