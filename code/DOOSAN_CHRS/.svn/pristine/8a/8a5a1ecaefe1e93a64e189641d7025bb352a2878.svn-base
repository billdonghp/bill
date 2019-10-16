/*
 * @(#)BasicInfoDAO.java 1.0 2006-12-21 下午05:18:34
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.empinfo;

import java.util.List;
import java.util.Map;                                      
                                                    
import org.apache.log4j.Logger;                     

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-21 下午05:23:50
 * @version 1.0
 * 
 */

public class BasicInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(BasicInfoDAO.class);

	public BasicInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * retrieve education information
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List retrieveEducationInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveEducationInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve education information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve personal information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrievePersonalInfo(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQuery(
					"hrm.empinfo.retrievePersonalInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve personal information Exception. ", e);
		}
		return object;
	}

	/**
	 * retrieve language information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveLanguageInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveLanguageInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve language information Exception. ", e);
		}
		return list;
	}



	/**
	 * retrieve IT level information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveITLevelInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveITLevelInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve IT level information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve contract information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveContractInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.contract.retrieveContractInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve contract information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert education information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEducationInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertEducationInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert education information Exception. ", e);
		}
	}

	/**
	 * insert language information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertLanguageInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertLanguageInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert language information Exception. ", e);
		}
	}

	/**
	 * insert IT level information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertITLevelInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("hrm.empinfo.insertITLevelInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert IT level information Exception. ", e);
		}
	}

	/**
	 * insert basic information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertBasicInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("education") != null
					&& map.get("education") instanceof List
					&& ((List) map.get("education")).size() > 0) {

				this.insertEducationInfo((List) map.get("education"), false);
			}

			if (map.get("language") != null
					&& map.get("language") instanceof List
					&& ((List) map.get("language")).size() > 0) {

				this.insertLanguageInfo((List) map.get("language"), false);
			}

			if (map.get("ITLevel") != null
					&& map.get("ITLevel") instanceof List
					&& ((List) map.get("ITLevel")).size() > 0) {

				this.insertITLevelInfo((List) map.get("ITLevel"), false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Insert basic information Exception. ", e);
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
	 * update education information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEducationInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateEducationInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update education information Exception. ", e);
		}
	}

	/**
	 * update personal information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePersonalBasicInfo(Object parameterObject)             
			throws GlRuntimeException {                

		try {
		
			commonSQLMapAdapter.update("hrm.empinfo.updatePersonalBasicInfo",
					parameterObject);                   
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update personal information Exception. ", e);
		}
	}
	/**
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateHrEmployeeInfo(Object parameterObject)
			throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.update("hrm.empinfo.updateHrEmployeeInfo",
					parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update employee info information Exception. ", e);
		}
	}

	/**
	 * update language information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateLanguageInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateLanguageInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update language information Exception. ", e);
		}
	}

	/**
	 * update costcenter information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateCostcenterInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("hrm.empinfo.updateCostcenterInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update costcenter information Exception. ", e);
		}
	}

	/**
	 * update IT level information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateITLevelInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("hrm.empinfo.updateITLevelInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update IT level information Exception. ", e);
		}
	}

	/**
	 * update basic information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateBasicInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("education") != null
					&& map.get("education") instanceof List)
				this.updateEducationInfo((List) map.get("education"), false);
			if (map.get("personal") != null)      
			{
				this.updatePersonalBasicInfo(map.get("personal"));
			    this.updateHrEmployeeInfo(map.get("personal"));
			}
			if (map.get("language") != null
					&& map.get("language") instanceof List)
				this.updateLanguageInfo((List) map.get("language"), false);
			if (map.get("costcenter") != null)
				this.updateCostcenterInfo(map.get("costcenter"));
			if (map.get("ITLevel") != null
					&& map.get("ITLevel") instanceof List)
				this.updateITLevelInfo((List) map.get("ITLevel"), false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Update basic information Exception. ", e);
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
	 * delete education information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEducationInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteEducationInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete education information Exception. ", e);
		}
	}

	/**
	 * delete language information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteLanguageInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteLanguageInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete language information Exception. ", e);
		}
	}

	/**
	 * delete IT level information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteITLevelInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("hrm.empinfo.deleteITLevelInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete IT level information Exception. ", e);
		}
	}

	/**
	 * delete basic information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteBasicInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("education") != null
					&& map.get("education") instanceof List)
				this.deleteEducationInfo((List) map.get("education"), false);
			if (map.get("language") != null
					&& map.get("language") instanceof List)
				this.deleteLanguageInfo((List) map.get("language"), false);
			if (map.get("ITLevel") != null
					&& map.get("ITLevel") instanceof List)
				this.deleteITLevelInfo((List) map.get("ITLevel"), false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"delete basic information Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

}
