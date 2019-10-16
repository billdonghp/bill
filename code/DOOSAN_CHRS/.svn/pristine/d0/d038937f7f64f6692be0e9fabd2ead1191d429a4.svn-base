/*
 * @(#)MenuDAO.java 1.0 2007-9-11 下午03:38:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.common;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sy.bean.ReportItem;
import com.ait.sy.bean.ReportItemReference;
import com.ait.sy.bean.ReportTable;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-11 下午03:38:54
 * @version 1.0
 * 
 */
public class MenuDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(MenuDAO.class);

	public MenuDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * get menu information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public Object getMenuEnt(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("sys.common.getMenuEnt", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get menu information Exception. ", e);
		}
		return result;
	}
	

	/**
	 * retrieve can be build menu information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveCanBeBuildMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("sys.common.retrieveCanBeBuildMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve can be build menu information Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve table by menu information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveTableByMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("sys.common.retrieveTableByMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve table by menu information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve table item information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List<ReportItemReference> retrieveReportItemListByTableName(Object parameterObject) throws GlRuntimeException {

		List<ReportItemReference> result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("sys.common.retrieveReportItemListByTableName", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve table item information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve table item not in param information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List<ReportItemReference> retrieveReportItemListNotInRtNo(Object parameterObject) throws GlRuntimeException {

		List<ReportItemReference> result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("sys.common.retrieveReportItemListNotInRtNo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve table item not in param  information Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve report table next Primary Key information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public Integer retrieveReportTableNextPK() throws GlRuntimeException {
		Integer rt_no = 0;
		try {

			rt_no = (Integer) commonSQLMapAdapter.executeQuery("sys.common.retrieveReportTableNextPK");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve report table next Primary Key Exception. ", e);
		}

		return rt_no;
	}

	/**
	 * insert report table
	 * 
	 * @param ReportTable
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void insertReportTable(List<ReportTable> reportTablelist,List<ReportTable> delList,List<ReportTable> updateList,String cpnyId) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.startTransaction();
			if(updateList.size() > 0)
				commonSQLMapAdapter.updateForMulti("sys.common.UpdateReportTable", updateList,false);
			if(delList.size() > 0){
				commonSQLMapAdapter.deleteForMulti("sys.common.deleteReportItemByRtno", delList, false);
				commonSQLMapAdapter.deleteForMulti("sys.common.deleteReportTable", delList, false);
			}
			if(reportTablelist.size() > 0){
				for (ReportTable table : reportTablelist) {
					if(table.getReportItems().size() > 0)
						commonSQLMapAdapter.insertForMulti("sys.common.insertReportItem",table.getReportItems(),false);
				}
				commonSQLMapAdapter.insertForMulti("sys.common.insertReportTable",reportTablelist,false);
			}
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert report table Exception. ", e);
		}finally{
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	public void insertReportItem(List<ReportItem> reportItemlist) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.insertForMulti("sys.common.insertReportItem",reportItemlist);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert report item Exception. ", e);
		}
	}
	
	public void deleteReportItem(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.delete("sys.common.deleteReportItemByRino",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete report item Exception. ", e);
		}
	}
	
	public void updateReportItem(List<ReportItem> reportItemlist) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.updateForMulti("sys.common.updateReportItem",reportItemlist);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update report item Exception. ", e);
		}
	}
	
	/**
	 * retrieve employee division
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getEmpDiff(Object parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("sys.common.getEmpDiff",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve employee division Exception. ", e);
		}

		return result;
	}
	
	/**
	 * grant validate
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getGrantValidate(Object parameterObject) throws GlRuntimeException {
		
		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("sys.common.getGrantValidate",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get grant validate Exception. ", e);
		}

		return result;
	}
	
	/**
	 * retrieve help info
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getHelpInfo(Object parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("sys.common.getHelpInfo",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve help info division Exception. ", e);
		}

		return result;
	}
}
