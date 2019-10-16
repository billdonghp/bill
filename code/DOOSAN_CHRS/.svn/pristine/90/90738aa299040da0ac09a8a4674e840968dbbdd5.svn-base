/*
 * @(#)OtherInfoDAO.java 1.0 2006-12-21 下午05:20:22
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

public class OtherInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(OtherInfoDAO.class);

	public OtherInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
 
	/**
	 * retrieve experience information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveExperienceInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveExperienceInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve experience information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert experience information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertExperienceInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertExperienceInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert experience information Exception. ", e);
		}
	}

	/**
	 * update experience information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateExperienceInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateExperienceInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update experience information Exception. ", e);
		}
	}

	/**
	 * delete experience information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteExperienceInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteExperienceInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete experience information Exception. ", e);
		}
	}

	/**
	 * retrieve qualification information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveQualificationInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveQualificationInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve qualification information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert qualification information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertQualificationInfo(List parameterObject,
			boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertQualificationInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert qualification information Exception. ", e);
		}
	}

	/**
	 * update qualification information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateQualificationInfo(List parameterObject,
			boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateQualificationInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update qualification information Exception. ", e);
		}
	}

	/**
	 * delete qualification information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteQualificationInfo(List parameterObject,
			boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteQualificationInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete qualification information Exception. ", e);
		}
	}

	/**
	 * insert Other information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertOtherInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("qualification") != null
					&& map.get("qualification") instanceof List
					&& ((List) map.get("qualification")).size() > 0) {

				this.insertQualificationInfo((List) map.get("qualification"),
						false);
			}

			if (map.get("experience") != null
					&& map.get("experience") instanceof List
					&& ((List) map.get("experience")).size() > 0) {

				this.insertExperienceInfo((List) map.get("experience"), false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Insert Other information Exception. ", e);
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
	 * update Other information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateOtherInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("qualification") != null
					&& map.get("qualification") instanceof List)
				this.updateQualificationInfo((List) map.get("qualification"),
						false);

			if (map.get("experience") != null
					&& map.get("experience") instanceof List)
				this.updateExperienceInfo((List) map.get("experience"), false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Update Other information Exception. ", e);
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
	 * delete Other information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteOtherInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("qualification") != null
					&& map.get("qualification") instanceof List)
				this.deleteQualificationInfo((List) map.get("qualification"),
						false);
			if (map.get("experience") != null
					&& map.get("experience") instanceof List)
				this.deleteExperienceInfo((List) map.get("experience"), false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"delete Other information Exception. ", e);
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
