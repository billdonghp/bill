/*
 * @(#)PaUtilDAO.java 1.0 2007-11-10 下午08:30:28
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.dao.paramItem;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;

public class PaParamItemDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PaParamItemDAO.class);

	public PaParamItemDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * retrieve pa param item list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamList(SimpleMap parameterObject) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.param.retrievePaParamList",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Param List Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa param item list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.param.retrievePaParamList",parameterObject,currentPage,pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Param List Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa param item list count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaParamListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.param.retrievePaParamListCnt",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Param List count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamConfigureList(SimpleMap parameterObject) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.param.retrievePaParamConfigureList",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Param configure item Configure Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa param item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamConfigureList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.param.retrievePaParamConfigureList",parameterObject,currentPage,pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Param configure item Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve pa param item list count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaParamConfigureListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.param.retrievePaParamConfigureListCnt",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Param configure List count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * delete pa param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void deletePaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.delete("pa.param.deletePaParamData",parameterObject) ;
			
			commonSQLMapAdapter.delete("pa.param.deletePaParamConfigure",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			try{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception ee){	}
			logger.error(e.toString());
			throw new GlRuntimeException("delete Pa Param configure item Configure Exception. ", e);
		}
	}
	
	/**
	 * validate update pa param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateUpdatePaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.param.validateUpdatePaParamConfigure",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" validate update Pa Param configure item Configure Exception. ", e);
		}
		
		return result ;
	}
	
	/**
	 * update pa param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void updatePaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.param.updatePaParamConfigure",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update Pa Param configure item Configure Exception. ", e);
		}
	}
	
	/**
	 * validate add pa param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateAddPaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.param.validateAddPaParamConfigure",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" validate add Pa Param configure item Configure Exception. ", e);
		}
		
		return result ;
	}
	
	/**
	 * add pa param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void insertPaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("pa.param.insertPaParamConfigure",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("add Pa Param configure item Configure Exception. ", e);
		}
	}
}

