/*
 * @(#)TemplateServices.java 1.0 2006-12-6 上午01:18:22
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.template.services;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.template.dao.TemplateDAO;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-6 上午01:18:22
 * @version 1.0
 * 
 */
public class TemplateServices {

	private TemplateDAO templateDAO;

	public TemplateServices() {

		templateDAO = new TemplateDAO();
	}

	/**
	 * retrieve attendance item list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArItemList(Object parameterObject)
			throws GlRuntimeException {

		return templateDAO.retrieveArItemList(parameterObject);
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

		return templateDAO.retrieveArItemListCnt(parameterObject);
	}

	/**
	 * test sqlmap for bean parameter
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object testSqlMap(Object parameterObject) throws GlRuntimeException {

		return templateDAO.testSqlMap(parameterObject);
	}

	/**
	 * insert data to AR_ITEM
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object IinsertArItem(Object parameterObject)
			throws GlRuntimeException {

		return templateDAO.insertArItem(parameterObject);
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

		return templateDAO.testTransation(parameterObject);
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

		return templateDAO.retrieveArItemList(parameterObject, currentPage,
				pageSize);
	}

	/**
	 * retrieve post list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePostList() throws GlRuntimeException {

		return templateDAO.retrievePostList();
	}

	/**
	 * insert education informationo
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEducationInfo(Object parameterObject)
			throws GlRuntimeException {

		templateDAO.insertEducationInfo(parameterObject);
	}

	/**
	 * create table test
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public void createTableTest(Object parameterObject)
			throws GlRuntimeException {

		templateDAO.createTableTest(parameterObject);
	}
	
	/**
	 * insert post information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPostInfo(Object parameterObject)
			throws GlRuntimeException {
		templateDAO.insertPostInfo(parameterObject);
	}
	
	/**
	 * retrieve post list for sqlserver
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrievePostListForSqlserver()
			throws GlRuntimeException {
		return templateDAO.retrievePostListForSqlserver();
	}
}
