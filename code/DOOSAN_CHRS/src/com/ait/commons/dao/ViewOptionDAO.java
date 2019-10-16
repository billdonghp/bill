package com.ait.commons.dao;

import java.sql.SQLException;
import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;

public class ViewOptionDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static ServiceLocator services;

	public ViewOptionDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * retrieve report table list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveReportTableList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.retrieveReportTableList", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"ViewOptionDAO.retrieveReportTableList() Exception: "
							+ e.toString());
		}
		return list;
	}

	/**
	 * retrieve report item list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveReportItemList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.retrieveReportItemList", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"ViewOptionDAO.retrieveReportItemList() Exception: "
							+ e.toString());
		}
		return list;
	}
	
	/**
	 * retrieve report item list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveReportItemForArMonthList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.retrieveReportItemForArMonthList", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"ViewOptionDAO.retrieveReportItemList() Exception: "
							+ e.toString());
		}
		return list;
	}
	
	/**
	 * get report item list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getReportItemList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.getReportItemList", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"ViewOptionDAO.getReportItemList() Exception: "
							+ e.toString());
		}
		return list;
	}

	/**
	 * retrieve report data list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveReportDataList(Object parameterObject,  int currentPage, int pageSize) throws GlRuntimeException {
	
	List list;
	try {
		
		list = commonSQLMapAdapter.executeQueryForMulti(
				"common.sys.retrieveReportDataList", parameterObject, currentPage, pageSize);
	
	} catch (SQLException e) {
	
		throw new GlRuntimeException(
				"Retrieve report data list by paging Exception: " + e.toString());
	}
	return list;
	}
	
	/**
	 * retrieve report data list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveReportDataList(Object parameterObject) throws GlRuntimeException {
	
	List list;
	try {
		
		list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveReportDataList", parameterObject);
	
	} catch (SQLException e) {
	
		throw new GlRuntimeException(
				"Retrieve report data list Exception: " + e.toString());
	}
	return list;
	}
	
} 
