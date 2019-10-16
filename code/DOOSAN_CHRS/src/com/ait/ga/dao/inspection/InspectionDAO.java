package com.ait.ga.dao.inspection;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-10
 * 
 */

public class InspectionDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	private static final Logger logger = Logger.getLogger(InspectionDAO.class);
	
	public InspectionDAO(){
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * Retrieve inspection list
	 * 
	 * @param Object
	 * @return List
	 */
	public List retrieveInspectionList(Object parameterObject)throws GlRuntimeException{
		List temp;
		try{
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.inspection.selectInspectionList", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"retrieveInspectionList data Exception. ",e);
		}
		return temp;
	}
	
	/**
	 * Retrieve inspection number
	 * 
	 * @param Object
	 * @return int
	 */
	public Object retrieveInspectionNumber(Object parameterObject)throws GlRuntimeException{
		Object temp;
		try{
			temp = commonSQLMapAdapter.executeQuery("ga.inspection.selectInspectionCut", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"retrieveInspectionNumber data Exception. ",e);
		}
		return temp;
	}
	
	/**
	 * Retrieve inspection list by paging
	 * 
	 * @param Object
	 * @param int currentPage
	 * @param int pageSize
	 * @return List
	 */
	public List retrieveInspectionList(Object parameterObject,int  currentPage, int pageSize)throws GlRuntimeException{
		List temp;
		try{
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.inspection.selectInspectionList", parameterObject, currentPage, pageSize);
		}catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve inspection list by paging data Exception. ",e);
		}
		return temp;
	}
	
	/**
	 * Insert inspection 
	 * 
	 * @param Object
	 */
	public void insertInspection(Object parameterObject)throws GlRuntimeException{
		
		try{
			commonSQLMapAdapter.insert("ga.inspection.insertInspection",parameterObject);
		}catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Insert inspection data Exception. ",e);
		}
	}
	
	/**
	 * Update inspection 
	 * 
	 * @param Object
	 * 
	 */
	public void updateInspection(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.update("ga.inspection.updateInspection",parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Update inspection Exception. ",e);
		}
	}
	
	/**
	 * Confirm inspection 
	 * 
	 * @param Object
	 * 
	 */
	public void confirmInspection(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.update("ga.inspection.confirmInspection",parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Update inspection Exception. ",e);
		}
	}
	
	/**
	 * Delete inspection
	 * 
	 * @param Object
	 */
	public void deleteInspection(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.update("ga.inspection.deleteInspection",parameterObject);
		}catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Delete inspection data Exception. ",e);
		}
	}
	
	/**
	 * Sesrch Asset
	 * 
	 * @param Object
	 * @return List
	 */
	public  List searchAsset(Object parameterObject)throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.inspection.searchAsset",parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"search Asset data Exception. ",e);
		}
		return list;
	}
}
	
