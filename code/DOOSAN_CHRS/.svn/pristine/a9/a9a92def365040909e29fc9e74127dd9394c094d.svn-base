/*
 * @(#)PostDAO.java 1.0 2007-9-8 上午02:00:34
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.essLeave;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil ; 

public class EssLeaveDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EssLeaveDAO.class);

	public EssLeaveDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	public List getEssLeaveParamList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException
	{  
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("sys.common.getEssLeaveParamList",parameterObject,currentPage,pageSize);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssLeaveParamList. ", e);
		}
		return list;
	}
	
	public Object getEssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		Object result ;
		try{
			result = commonSQLMapAdapter.executeQuery("sys.common.getEssLeaveParamList",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssLeaveParam. ", e);
		}
		return result;
	}
	
	public int getEssLeaveParamListCnt(Object parameterObject) throws GlRuntimeException {
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("sys.common.getEssLeaveParamListCnt", parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssLeaveParamListCnt. ", e);
		}
		return result;
	}
	
	public int validateInsertEssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		int result = 0 ;
		try{
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("sys.common.validateInsertEssLeaveParam",parameterObject));  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when validateInsertEssLeaveParam. ", e);
		}
		return result ;
	}
	public List EssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		List list=null;
		try{
			list =commonSQLMapAdapter.executeQueryForMulti("sys.common.EssLeaveParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssLeaveParam. ", e);
		}
		return list ;
	}
	
	
	public void insertEssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		try{
			commonSQLMapAdapter.insert("sys.common.insertEssLeaveParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when insertEssLeaveParam. ", e);
		}
	}
	
	public void deleteEssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		try{
			commonSQLMapAdapter.delete("sys.common.deleteEssLeaveParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when deleteEssLeaveParam. ", e);
		}
	}
	
	public int validateUpdateEssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		int result = 0 ;
		try{
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("sys.common.validateUpdateEssLeaveParam",parameterObject));  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when validateUpdateEssLeaveParam. ", e);
		}
		return result ;
	}
	
	public void updateEssLeaveParam(Object parameterObject) throws GlRuntimeException
	{  
		try{
			commonSQLMapAdapter.update("sys.common.updateEssLeaveParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when updateEssLeaveParam. ", e);
		}
	}
	

}
