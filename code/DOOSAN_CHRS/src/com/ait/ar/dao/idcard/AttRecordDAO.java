/*
 * @(#)AttRecordDAO.java 1.0 2007-9-26 下午09:23:51
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.dao.idcard;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-9-26 下午09:23:51
 * @version 1.0
 * 
 */
public class AttRecordDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(AttRecordDAO.class);

	public AttRecordDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	// attendance record operation
	
	
	/**
	 * retrieve attendance record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAttRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveAttRecordList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve atendance record list by paging Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve attendance record list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveAttRecordList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveAttRecordList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve atendance record list Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve attendance record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveAttRecordListCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.retrieveAttRecordListCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve atendance record list count Exception. ", e);
		}
		return result;
	}

	/**
	 * insert attendance record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAttRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.idcard.insertAttRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert attendance record data Exception. ", e);
		}
	}
	
	/**
	 * insert attendance records data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAttRecords(List parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ar.idcard.insertAttRecords", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert attendance records data Exception. ", e);
		}
	}

	/**
	 * retrieve data for update attendance record 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public void retrieveDataForUpdateAttRecord(Object parameterObject) throws GlRuntimeException {

		
		try {

			commonSQLMapAdapter.update("ar.idcard.retrieveDataForUpdateAttRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve data for update attendance record  Exception. ", e);
		}
	
	}

	/**
	 * update attendance record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAttRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.idcard.updateAttRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update attendance record data Exception. ", e);
		}
	}

	/**
	 * delete attendance record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteAttRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.idcard.deleteAttRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete attendance record data Exception. ", e);
		}
	}
	
	/**
	 * validate R_TIEM
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateAttRecordDate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.validateAttRecordDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Validate R_TIEM Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * delete attendance record data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteAttRecord(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("ar.idcard.deleteAttRecord", parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete attendance record data by batch Exception. ", e);
		}
	}

	/**
	 * retrieve employee list by card
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListByCard(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveEmpListByCard", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee list by ID Card Exception. ", e);
		}
		return list;
	}
	
	
	
	// visit card relation operation
	
	
	/**
	 * retrieve visit card list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVisitCardList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveVisitCardList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve visit card list by paging Exception. ", e);
		}
		return result;
	}
	

	/**
	 * retrieve visit card list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVisitCardList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveVisitCardList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve visit card list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve visit card list count
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveVisitCardListCnt(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.retrieveVisitCardListCnt", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve visit card list count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve visit card no list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVisitCardNoList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveVisitCard", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve visit card no list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * add visit card relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertVisitCard(Object parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.update("ar.idcard.updateEndDateByInsert", parameterObject);
			commonSQLMapAdapter.insert("ar.idcard.insertVisitCard", parameterObject);

			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {
			
			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("Insert visit card relation Exception. ", e);
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
	 * retrieve visit card data for update visit card relation
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateVisitCard(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.retrieveDataForUpdateVisitCard", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve visit card data for update visit card relation  Exception. ", e);
		}
		return result;
	}

	/**
	 * update visit card relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateVisitCard(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.idcard.updateVisitCard", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update visit card relation Exception. ", e);
		}
	}
	
	/**
	 * validate start date or end date
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateVisitCardDate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.validateVisitCardDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Validate visit card date Exception. ", e);
		}
		return result;
	}
	
	//	 eatery record operation
	
	
	/**
	 * retrieve eatery record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEateryRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveEateryRecordList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve eatery record list by paging Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve eatery record list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEateryRecordList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveEateryRecordList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve eatery record list Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve eatery record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveEateryRecordListCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.retrieveEateryRecordListCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve eatery record list count Exception. ", e);
		}
		return result;
	}

	/**
	 * insert eatery record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEateryRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.idcard.insertEateryRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert eatery record data Exception. ", e);
		}
	}

	/**
	 * retrieve data for update eatery record 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateEateryRecord(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.retrieveDataForUpdateEateryRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve data for update eatery record Exception. ", e);
		}
		return result;
	}

	/**
	 * update eatery record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEateryRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.idcard.updateEateryRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update eatery record data Exception. ", e);
		}
	}

	/**
	 * delete eatery record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEateryRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.idcard.deleteEateryRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete eatery record data Exception. ", e);
		}
	}

	/**
	 * delete eatery record data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteEateryRecord(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("ar.idcard.deleteEateryRecord", parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete eatery record data by batch Exception. ", e);
		}
	}

	/**
	 * retrieve employee list by eatery
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListByEatery(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.idcard.retrieveEmpListByEatery", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve visit list by eatery Exception. ", e);
		}
		return list;
	}
	
	/**
	 * validate eatery record date
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateEateryRecordDate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.idcard.validateEateryRecordDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Validate eatery record date Exception. ", e);
		}
		return result;
	}
	
	/**
	 * check attendance record
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public int checkAttRecord(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ar.idcard.checkAttRecord", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("check atendance record Exception. ", e);
		}
		return result;
	}
}
