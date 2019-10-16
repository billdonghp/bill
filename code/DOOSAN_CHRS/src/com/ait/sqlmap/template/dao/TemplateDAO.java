/*
 * @(#)TemplateDAO.java 1.0 2006-12-6 上午12:51:56
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.template.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-6 上午12:51:56
 * @version 1.0
 * 
 */
public class TemplateDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	
	private CommonSQLMapAdapter sqlServerAdapter = null;

	private static final Logger logger = Logger.getLogger(TemplateDAO.class);

	public TemplateDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("template");
		sqlServerAdapter = new CommonSQLMapAdapter("sqlserver");
	}

	/**
	 * retrieve attendance item list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveArItemList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"sqlmap.template.RetrieveArItemList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.RetrieveArItemList() Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve attendance item list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveArItemListCnt(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {
			object = commonSQLMapAdapter.executeQuery(
					"sqlmap.template.RetrieveArItemListCnt", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.RetrieveArItemListCnt() Exception. ", e);
		}
		return object;
	}

	/**
	 * test sqlmap for bean parameter
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object testSqlMap(Object parameterObject) throws GlRuntimeException {

		Object object;
		try {
			object = commonSQLMapAdapter.executeQuery(
					"sqlmap.template.testSqlMap", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.TestSqlMap() Exception. ", e);
		}
		return object;
	}

	/**
	 * insert data to AR_ITEM
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object insertArItem(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {
			object = commonSQLMapAdapter.insert("sqlmap.template.InsertArItem",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.InsertArItem() Exception. ", e);
		}
		return object;
	}

	/**
	 * create table test
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object createTableTest(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {
			object = commonSQLMapAdapter
					.update("sqlmap.template.createTableTest");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("TemplateDAO.Exception() Exception. ",
					e);
		}
		return object;
	}

	/**
	 * test transation
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object testTransation(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			// execute update attendance item
			commonSQLMapAdapter.update("sqlmap.template.UpdateArItemName",
					parameterObject);

			// execute selecte attendance item
			commonSQLMapAdapter.update("sqlmap.template.UpdateArItemNo",
					parameterObject);

			commonSQLMapAdapter.commitTransation();

			return "transation success.";

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.TestTransation() Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException(
						"TemplateDAO.TestTransation() end transation Exception. ",
						e);
			}
		}
	}

	/**
	 * retrieve attendance item list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArItemList(Object parameterObject, int currentPage,
			int pageSize) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"sqlmap.template.RetrieveArItemList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException(
					"TemplateDAO.RetrieveArItemList( paging ) Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve post list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePostList() throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter
					.executeQueryForMulti("sqlmap.template.retrievePostList");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.retrievePostList() Exception. ", e);
		}
		return list;
	}

	/**
	 * insert education information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEducationInfo(Object parameterObject)
			throws GlRuntimeException {

		try {
			commonSQLMapAdapter.insert("sqlmap.template.insertEducationInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.insertEducationInfo() Exception. ", e);
		}

	}
	
	/**
	 * insert post information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPostInfo(Object parameterObject)
			throws GlRuntimeException {

		try {
			commonSQLMapAdapter.insert("sqlmap.template.insertPostInfo",
					parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.insertPostInfo() Exception. ", e);
		}

	}
	
	/**
	 * retrieve post list for sqlserver
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrievePostListForSqlserver()
			throws GlRuntimeException {

		List list;
		try {
			list = sqlServerAdapter.executeQueryForMulti(
					"sqlmap.template.retrievePostListForSqlserver");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"TemplateDAO.retrievePostListForSqlserver() Exception. ", e);
		}
		return list;
	}

}
