/*
 * @(#)PostDAO.java 1.0 2007-9-8 上午02:00:34
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.essOvertime;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil ; 

public class EssOvertimeDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EssOvertimeDAO.class);

	public EssOvertimeDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	public List getEssOvertimeParamList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException
	{  
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("sys.common.getEssOvertimeParamList",parameterObject,currentPage,pageSize);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssOvertimeParamList. ", e);
		}
		return list;
	}
	
	public Object getEssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		Object result ;
		try{
			result = commonSQLMapAdapter.executeQuery("sys.common.getEssOvertimeParamList",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssOvertimeParam. ", e);
		}
		return result;
	}
	
	public int getEssOvertimeParamListCnt(Object parameterObject) throws GlRuntimeException {
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("sys.common.getEssOvertimeParamListCnt", parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssOvertimeParamListCnt. ", e);
		}
		return result;
	}
	
	public int validateInsertEssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		int result = 0 ;
		try{
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("sys.common.validateInsertEssOvertimeParam",parameterObject));  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when validateInsertEssOvertimeParam. ", e);
		}
		return result ;
	}
	public List EssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		List list=null;
		try{
			list =commonSQLMapAdapter.executeQueryForMulti("sys.common.EssOvertimeParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when getEssOvertimeParam. ", e);
		}
		return list ;
	}
	
	
	public void insertEssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		try{
			commonSQLMapAdapter.insert("sys.common.insertEssOvertimeParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when insertEssOvertimeParam. ", e);
		}
	}
	
	public void deleteEssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		try{
			commonSQLMapAdapter.delete("sys.common.deleteEssOvertimeParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when deleteEssOvertimeParam. ", e);
		}
	}
	
	public int validateUpdateEssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		int result = 0 ;
		try{
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("sys.common.validateUpdateEssOvertimeParam",parameterObject));  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when validateUpdateEssOvertimeParam. ", e);
		}
		return result ;
	}
	
	public void updateEssOvertimeParam(Object parameterObject) throws GlRuntimeException
	{  
		try{
			commonSQLMapAdapter.update("sys.common.updateEssOvertimeParam",parameterObject);  
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("The information of Exception when updateEssOvertimeParam. ", e);
		}
	}
	

}
