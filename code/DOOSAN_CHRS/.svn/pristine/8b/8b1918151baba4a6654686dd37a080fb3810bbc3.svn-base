package com.ait.ga.dao.smock;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class SmockRelationDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(SmockDAO.class);

	public SmockRelationDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * select smock relation list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List selectSmockRelationList(Object parameterObject)
			throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"ga.smockrelation.selectSmockRelationList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("select smock relation data Exception. ", e);
		}
		return list;
	}

	/**
	 * select smock relation list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List selectSmockRelationList(Object parameterObject, int currentPage,
			int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(
					"ga.smockrelation.selectSmockRelationList", parameterObject, currentPage,
					pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("select smock relation data by paging Exception. ", e);
		}
		return list;
	}

	/**
	 * select smock relation number
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object selectSmockRelationNumber(Object parameterObject)
			throws GlRuntimeException {
		Object object = null;
		try {
			object = commonSQLMapAdapter.executeQuery(
					"ga.smockrelation.selectSmockRelationCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("select smock relation number Exception. ", e);
		}
		return object;
	}

	/**
	 * insert smock relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertSmockRelation(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.insert("ga.smockrelation.insertSmockRelation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("insert smock relation data Exception. ", e);
		}
	}

	/**
	 * update smock relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateSmockRelation(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.update("ga.smockrelation.updateSmockRelation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("update smock relation data Exception. ", e);
		}
	}

	/**
	 * delete smock relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteSmcokRelation(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.update("ga.smockrelation.deleteSmockRelation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("delete smock relation data Exception. ", e);
		}
	}
	
	/**
	 * get smock name list
	 * @return list
	 */
	public List getSmockNameList(){
		List list =null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockrelation.getSmockName");
		}catch(Exception e){
			logger.error(e.toString());
			new GlRuntimeException("get smock name list",e);
		}
		return list;
	}
	
	/**
	 * get employee info
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getEmployeeInfoList(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockrelation.getEmployeeInfo", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			new GlRuntimeException("get employee info data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * get smock relation unit 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockRelationUnitList(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockrelation.getSmockRelationUnit", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			new GlRuntimeException("get smock relation unit data Exception. ",e);
		}
		return list;
	}
}
