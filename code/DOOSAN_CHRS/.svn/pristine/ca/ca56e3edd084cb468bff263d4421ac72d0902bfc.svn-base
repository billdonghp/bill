/*
 * @(#)RelationInfoDAO.java 1.0 2006-12-21 下午05:22:04
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

public class RelationInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger
			.getLogger(RelationInfoDAO.class);

	public RelationInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * retrieve family information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveFamilyInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveFamilyInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve family information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve military service information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveMilitaryServiceInfo(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQuery(
					"hrm.empinfo.retrieveMilitaryServiceInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve military service Exception. ", e);
		}
		return object;
	}
	
	/**
	 * insert family information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertFamilyInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("hrm.empinfo.insertFamilyInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert family information Exception. ", e);
		}
	}

	/**
	 * insert military service information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertMilitaryServiceInfo(List parameterObject,
			boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti(
					"hrm.empinfo.insertMilitaryServiceInfo", parameterObject,
					isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert military service information Exception. ", e);
		}
	}

	/**
	 * insert relation information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertRelationInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("military") != null
					&& map.get("military") instanceof List
					&& ((List) map.get("military")).size() > 0) {

				this.insertMilitaryServiceInfo((List) map.get("military"),
						false);
			}

			if (map.get("family") != null && map.get("family") instanceof List
					&& ((List) map.get("family")).size() > 0) {

				this.insertFamilyInfo((List) map.get("family"), false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Insert relation information Exception. ", e);
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
	 * update family information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateFamilyInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("hrm.empinfo.updateFamilyInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update family information Exception. ", e);
		}
	}

	/**
	 * update personal relation information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updatePersonalRelationInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update(
					"hrm.empinfo.updatePersonalRelationInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update personal relation information Exception. ", e);
		}
	}

	/**
	 * update military service information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateMilitaryServiceInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("hrm.empinfo.updateMilitaryServiceInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update military service information Exception. ", e);
		}
	}

	/**
	 * delete family information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteFamilyInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("hrm.empinfo.deleteFamilyInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete family information Exception. ", e);
		}
	}

	/**
	 * delete military service information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteMilitaryServiceInfo(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("hrm.empinfo.deleteMilitaryServiceInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete military service information Exception. ", e);
		}
	}

	/**
	 * update relation information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateRelationInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("personal") != null)
				this.updatePersonalRelationInfo(map.get("personal"));
			if (map.get("family") != null && map.get("family") instanceof List)
				this.updateFamilyInfo((List) map.get("family"), false);
			if (map.get("military") != null)
				this.updateMilitaryServiceInfo(map.get("military"));

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"update relation information Exception. ", e);
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
	 * delete relation information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteRelationInfo(Map map) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			if (map.get("family") != null && map.get("family") instanceof List
					&& ((List) map.get("family")).size() > 0)
				this.deleteFamilyInfo((List) map.get("family"), false);
			if (map.get("military") != null)
				this.deleteMilitaryServiceInfo(map.get("military"));

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"delete relation information Exception. ", e);
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
