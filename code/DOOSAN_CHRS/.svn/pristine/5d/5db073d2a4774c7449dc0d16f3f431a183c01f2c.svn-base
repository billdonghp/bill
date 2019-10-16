/*
 * @(#)AttTaskDAO.java 1.0 2009-7-7 下午04:09:30
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.dao.task;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-7 下午04:09:30
 * @version 1.0
 * 
 */
public class AttTaskDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(AttTaskDAO.class);

	public AttTaskDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * insert daily attendance to history table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertDailyAttToHistory(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertDailyAttToHistory", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert daily attendance to history table Exception. ", e);
		}
	}
	
	/**
	 * delete daily attendance
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteDatail(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteDatail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete daily attendance Exception. ", e);
		}
	}
	
	/**
	 * insert card record to history table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertRecordToHistory(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertRecordToHistory", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert card record to history table Exception. ", e);
		}
	}
	
	/**
	 * insert eatery card record to history table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEateryRecordToHistory(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertEateryRecordToHistory", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert eatery card record to history table Exception. ", e);
		}
	}
	
	/**
	 * delete card record
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCardRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteCardRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete card record Exception. ", e);
		}
	}
	
	/**
	 * delete eatery card record
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEateryCardRecord(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteEateryCardRecord", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete eatery card record Exception. ", e);
		}
	}

	/**
	 * delete datail operation history
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteDetailOperation(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteDetailOperation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete datail operation history Exception. ", e);
		}
	}
	
	/**
	 * sparate daily attendance
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void sparateDailyAttenance(Object parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			this.insertDailyAttToHistory(parameterObject);
			this.deleteDatail(parameterObject);

			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {
			
			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("Sparate daily attendance Exception. ", e);
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
	 * sparate card record
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void sparateCardRecord(Object parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			this.insertRecordToHistory(parameterObject);
			this.deleteCardRecord(parameterObject);

			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {
			
			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("Sparate card record Exception. ", e);
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
	 * sparate eatery card record
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void sparateEateryCardRecord(Object parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			this.insertEateryRecordToHistory(parameterObject);
			this.deleteEateryCardRecord(parameterObject);

			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {
			
			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("Sparate eatery card record Exception. ", e);
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
	 * delete ar ot datail operation history
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteArOtDetail() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteArOtDetail");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete ar ot datail operation  Exception. ", e);
		}
	}
	
	
	/**
	 * delete T ar ot datail operation history
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteTArOtDetail() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteTArOtDetail");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete T ar ot datail operation  Exception. ", e);
		}
	}
	
	
	
	/**
	 * insert ar ot datail operation history
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertArOtDetail() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.insertArOtDetail");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert ar ot datail operation  Exception. ", e);
		}
	}
	
	
	/**
	 * sparate emp attendance
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void sparateEmpAttenance(Object parameterObject) throws GlRuntimeException {

		try {
			//DICC,DISD Sales&Marketing 人员信息更新到 DICI中，为了可以使用行政管理模块
			commonSQLMapAdapter.startTransaction();
			
			this.insertDiccToDiciEmployee(parameterObject);
			
			this.insertDiccToDiciAdmin(parameterObject);
			
			this.deleteDiccToDiciEmployee(parameterObject);

			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {
			
			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("Sparate daily attendance Exception. ", e);
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
	 * insert DICC Employee to DICI Employee
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertDiccToDiciEmployee(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertDiccToDiciEmployee", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert DICC Employee to DICI Employee table Exception. ", e);
		}
	}
	
	/**
	 * insert DICC Admin to DICI Admin
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertDiccToDiciAdmin(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertDiccToDiciAdmin", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert DICC Admin to DICI Admin table Exception. ", e);
		}
	}
	
	
	/**
	 * delete DiccToDiciEmployee
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteDiccToDiciEmployee(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteDiccToDiciEmployee", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Delete DiccToDiciEmployee Exception. ", e);
		}
	}
	
	
	
	
	
}

