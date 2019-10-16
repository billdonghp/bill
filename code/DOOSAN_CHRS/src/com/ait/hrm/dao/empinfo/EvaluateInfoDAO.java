/*
 * @(#)EvaluateInfoDAO.java 1.0 2006-12-21 下午05:19:40
 * 
 * Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.empinfo;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-21 下午05:23:50
 * @version 1.0
 * 
 */

public class EvaluateInfoDAO {
    private CommonSQLMapAdapter commonSQLMapAdapter = null;

    private static final Logger logger	= Logger.getLogger(EvaluateInfoDAO.class);

    public EvaluateInfoDAO() {

	commonSQLMapAdapter = new CommonSQLMapAdapter();
    }

    /**
         * retrieve retrieveEvaluateType1 inside information
         * 
         * @param parameterObject
         * @return List
         * @throws GlRuntimeException
         */
    public List retrieveEvaluate1(Object parameterObject)
	    throws GlRuntimeException {

	List list;
	try {

	    list = commonSQLMapAdapter.executeQueryForMulti(
		    "hrm.empinfo.retrieveEvaluateByMonth", parameterObject);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "Retrieve retrieveEvaluateByMonth inside information Exception. ", e);
	}
	return list;
    }

    /**
         * retrieve retrieveEvaluateType2 inside information
         * 
         * @param parameterObject
         * @return List
         * @throws GlRuntimeException
         */
    public List retrieveEvaluate2(Object parameterObject)
	    throws GlRuntimeException {

	List list;
	try {

	    list = commonSQLMapAdapter.executeQueryForMulti(
		    "hrm.empinfo.retrieveEvaluateByYear", parameterObject);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "Retrieve retrieveEvaluateByYear inside information Exception. ", e);
	}
	return list;
    }
    
    
    
 public List retrieveEvaluate(Object parameterObject)
    throws GlRuntimeException {

	List list;
	try {
	        
	    list = commonSQLMapAdapter.executeQueryForMulti(  
		    "hrm.empinfo.retrieveEvaluate", parameterObject);
	
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "Retrieve retrieveEvaluateByYear inside information Exception. ", e);
	}
	return list;
}
 
 
 public void  updateEvaluateById(List parameterObject,boolean isAutoTrans)
 throws GlRuntimeException {
	try {
	        
	   commonSQLMapAdapter.updateForMulti("hrm.empinfo.updateEvaluate", parameterObject,isAutoTrans);             
	
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "updateEvaluate information Exception. ", e);
	}
}
 
 
 public void insertEvaluate(List parameterObject, boolean isAutoTrans)
 throws GlRuntimeException {

	List list;
	try {      
	     commonSQLMapAdapter.insertForMulti("hrm.empinfo.insertEvaluate", parameterObject,isAutoTrans);  
	} catch (Exception e) {
	    logger.error(e.toString());  
	    throw new GlRuntimeException(
		    "insertEvaluate information Exception. ", e);
	}  
}

 public void deleteEvaluateById(List parameterObject,boolean isAutoTrans)
 throws GlRuntimeException {

	try {
	        
	   commonSQLMapAdapter.deleteForMulti( "hrm.empinfo.deleteEvaluate", parameterObject,isAutoTrans);
	
	} catch (Exception e) {
	    logger.error(e.toString());  
	    throw new GlRuntimeException(
		    "deleteEvaluate information Exception. ", e);
	}
}
 
 
}
