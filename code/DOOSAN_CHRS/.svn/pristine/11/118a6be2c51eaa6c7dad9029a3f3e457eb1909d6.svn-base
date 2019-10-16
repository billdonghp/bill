package com.ait.ga.dao.smock;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class SmockProvideDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	private static final Logger logger = Logger.getLogger(SmockProvideDAO.class);
	
	public SmockProvideDAO(){
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * select smock provide list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockProvideList(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockprovide.selectSmockProvideList",parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select smock provide data Exception. ",e);
		}
		return list;
	}
    
	/**
	 * select smock provide list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockProvideList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockprovide.selectSmockProvideList", parameterObject, currentPage, pageSize);		
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select smock provide by paging data Exception. ",e);
		}
		return list;
	}
	
	public Object selectSmockProvideCut(Object parameterObject)throws GlRuntimeException{
		Object object;
		try{
			object = commonSQLMapAdapter.executeQuery("ga.smockprovide.selectSmockProvideCut", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select smock provide cut data Exception.",e);
		}
		return object;
	}
	
	/**
	 * insert smock provide 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertSmockProvide(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.insert("ga.smockprovide.insertSmockProvide",parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert smock provide data Exception.",e);
		}
	}
	
	/**
	 * select smock provide history record
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockProvideHistoryRecord(Object parameterObject)throws GlRuntimeException{
		List list = null;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockprovide.selectSmockProvideHistoryRecord", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select smock provide history record data Exception.",e);
		}
		return list;
	}
	
	/**
	 * delete smock provide
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteSmockProvide(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.smockprovide.deleteSmockProvide", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete smock provide data Exception.",e);
		}
	}
	
	/**
	 * get period name list 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getPeriodNameList(Object parameterObject)throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.smockprovide.getPeriodNameList", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("get period name list data Exception.",e);
		}
		return list;
	}
	
}
