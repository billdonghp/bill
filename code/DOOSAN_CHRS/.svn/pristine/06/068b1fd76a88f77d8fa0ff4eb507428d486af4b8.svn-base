/*
 * @(#)TrainingInfoDAO.java 1.0 2006-12-21 下午05:23:28
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

public class TrainingInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger
			.getLogger(TrainingInfoDAO.class);

	public TrainingInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * retrieve training inside information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveTrainingInside(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveTrainingInside", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve training inside information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert training inside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertTrainingInside(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertTrainingInside", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert training inside information Exception. ", e);
		}
	}

	/**
	 * update training inside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateTrainingInside(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateTrainingInside", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update training inside information Exception. ", e);
		}
	}

	/**
	 * delete training inside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteTrainingInside(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteTrainingInside", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete training inside information Exception. ", e);
		}
	}

	/**
	 * retrieve training outside information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveTrainingOutside(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveTrainingOutside", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve training outside information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert training outside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertTrainingOutside(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertTrainingOutside", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert training outside information Exception. ", e);
		}
	}

	/**
	 * update training outside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateTrainingOutside(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti(
					"hrm.empinfo.updateTrainingOutside", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update training outside information Exception. ", e);
		}
	}

	/**
	 * delete training outside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteTrainingOutside(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti(
					"hrm.empinfo.deleteTrainingOutside", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete training outside information Exception. ", e);
		}
	}

	/**
	 * insert studying information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertStudying(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("hrm.hrcommand.insertStudying",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert studying information Exception. ", e);
		}
	}

	/**
	 * update studying information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateStudying(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("hrm.hrcommand.updateStudying",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update studying information Exception. ", e);
		}
	}

	/**
	 * delete studying information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void delStudying(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("hrm.hrcommand.delStudying",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete studying information Exception. ", e);
		}
	}

	/**
	 * insert training information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertTrainingInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("inside") != null && map.get("inside") instanceof List
					&& ((List) map.get("inside")).size() > 0) {

				this.insertTrainingInside((List) map.get("inside"), false);
			}

			if (map.get("outside") != null
					&& map.get("outside") instanceof List
					&& ((List) map.get("outside")).size() > 0) {

				this.insertTrainingOutside((List) map.get("outside"), false);
			}

//			if (map.get("studying") != null
//					&& map.get("studying") instanceof List
//					&& ((List) map.get("studying")).size() > 0) {
//
//				this.insertStudying((List) map.get("studying"), false);
//			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Insert training information Exception. ", e);
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
	 * update training information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateTrainingInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("inside") != null && map.get("inside") instanceof List
					&& ((List) map.get("inside")).size() > 0)

				this.updateTrainingInside((List) map.get("inside"), false);

			if (map.get("outside") != null
					&& map.get("outside") instanceof List
					&& ((List) map.get("outside")).size() > 0)

				this.updateTrainingOutside((List) map.get("outside"), false);

//			if (map.get("studying") != null
//					&& map.get("studying") instanceof List
//					&& ((List) map.get("studying")).size() > 0) {
//
//				this.updateStudying((List) map.get("studying"), false);
//			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Update training information Exception. ", e);
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
	 * delete training information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteTrainingInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("inside") != null && map.get("inside") instanceof List
					&& ((List) map.get("inside")).size() > 0)

				this.deleteTrainingInside((List) map.get("inside"), false);

			if (map.get("outside") != null
					&& map.get("outside") instanceof List
					&& ((List) map.get("outside")).size() > 0)

				this.deleteTrainingOutside((List) map.get("outside"), false);

//			if (map.get("studying") != null
//					&& map.get("studying") instanceof List
//					&& ((List) map.get("studying")).size() > 0) {
//
//				this.delStudying((List) map.get("studying"), false);
//			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"delete training information Exception. ", e);
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
