/*
 * @(#)CommonDAO.java 1.0 2006-12-11 下午04:39:52
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.commons.dao;

import java.sql.SQLException;
import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午04:39:52
 * @version 1.0
 * 
 */
public class CommonDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;


	public CommonDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * retrieve code list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveCodeList(Object parameterObject)
			throws GlRuntimeException {		
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.RetrieveCodeList", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.retrieveArItemList() Exception: " + e.toString());
		}
		return list;
	}

	/**
	 * retrieve code list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveCodeListNotDsDy(Object parameterObject)
			throws GlRuntimeException {		
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.RetrieveCodeListNotDiy", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.retrieveArItemList() Exception: " + e.toString());
		}
		return list;
	}
	
	/**
	 * retrieve send email job status
	 * 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveSendEmailJobStatus() throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter
					.executeQuery("common.sys.retrieveSendEmailJobStatus");
		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.retrieveSendEmailJobStatus() Exception: "
							+ e.toString());
		}
		return result;
	}

	/**
	 * update send email job status
	 * 
	 * @throws GlRuntimeException
	 */
	public void updateSendEmailJobStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("common.sys.updateSendEmailJobStatus",
					parameterObject);
		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.updateSendEmailJobStatus() Exception: "
							+ e.toString());
		}
	}

	/**
	 * retrieve email list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEmailList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.retrieveEmailList", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.retrieveEmailList() Exception: " + e.toString());
		}
		return list;
	}

	/**
	 * update email send status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEmailSendStatus(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("common.sys.updateEmailSendStatus",
					parameterObject);
		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.updateEmailSendStatus() Exception: "
							+ e.toString());
		}
	}
	
	/**
	 * retrieve history menu
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveHistoryMenu(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.retrieveHistoryMenu", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.retrieveHistoryMenu() Exception: " + e.toString());
		}
		return list;
	}
	
	/**
	 * retrieve EmpDiffTag m
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List EmpDiffTag(Object parameterObject)	throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.EmpDiffTag", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException(
					"CommonDAO.EmpDiffTag() Exception: " + e.toString());
		}
		return list;
	}
	
	/**
	 * retrieve person id
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrievePersonId(Object parameterObject) throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQuery("common.sys.retrievePersonId", parameterObject);

		} catch (Exception e) {

			throw new GlRuntimeException("Retrieve person id Exception. ", e);
		}
		return object;
	}
	
	/**
	 * AJAX在评页面中通过下拉框里选择的工种得到该类别所对应的LIne
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	
	public List retrievecraftByLine(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrievecraftByLine", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrievecraftByLine() Exception: " + e.toString());
		}
		return list;
	}
	
	/**
	 * AJAX在评页面中通过下拉框里选择的工种得到该类别所对应的择能类型
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	
	public List retrievecraftByStype(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrievecraftByStype", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrievecraftByLine() Exception: " + e.toString());
		}
		return list;
	}
	
	/**
	 * AJAX在评页面中通过下拉框里选择的Line得到该类别所对应的机种
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	
	public List retrieveLineByAircraft(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveLineByAircraft", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrievecraftByLine() Exception: " + e.toString());
		}
		return list;
	}
	
	/**
	 * AJAX在评页面中通过下拉框里选择的Line得到该类别所对应的工序
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	
	public List retrieveLineByProcess(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveLineByProcess", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrievecraftByLine() Exception: " + e.toString());
		}
		return list;
	}
	public List retrieveLineByPro(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveLineByPro", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrieveLineByPro() Exception: " + e.toString());
		}
		return list;
	}
	public List retrieveSetupempcount(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveSetupempcount", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrieveSetupempcount() Exception: " + e.toString());
		}
		return list;
	}
	public List retrieveSetupempcount2(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveSetupempcount2", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrieveSetupempcount2() Exception: " + e.toString());
		}
		return list;
	}
	public List retrieveSetupempcount3(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveSetupempcount3", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrieveSetupempcount3() Exception: " + e.toString());
		}
		return list;
	}
	public List retrieveSetupcount(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveSetupcount", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrieveSetupcount() Exception: " + e.toString());
		}
		return list;
	}
	public List retrieveSetupcount1(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveSetupcount1", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrieveSetupcount1() Exception: " + e.toString());
		}
		return list;
	}
	/**
	 * AJAX在评页面中通过下拉框里选择的工序得到该类别所对应的作业内容
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	
	public List retrieveProcessByJcoent(Object parameterObject) throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("common.sys.retrieveProcessByJcoent", parameterObject);

		} catch (SQLException e) {

			throw new GlRuntimeException("CommonDAO.retrievecraftByLine() Exception: " + e.toString());
		}
		return list;
	}
	
	

	
}
