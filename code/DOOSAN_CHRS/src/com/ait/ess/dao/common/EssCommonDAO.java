/*
 * @(#)EssCommonDAO.java 1.0 2007-6-20 下午03:02:45
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ess.dao.common;

import java.util.List;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-6-20 下午03:02:44
 * @version 1.0
 * 
 */
public class EssCommonDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EssCommonDAO.class);

	public EssCommonDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyOTDetail(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQuery(             
					"ess.common.retrieveApplyOTDetail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve overtime apply detail Exception. ", e);
		}
		return object;
	}
	
	
 public Object getReportType(Object parameterObject)
	throws GlRuntimeException {
   Object object;
   try {
	object = commonSQLMapAdapter.executeQuery(             
			"ess.common.getReportType", parameterObject);
} catch (Exception e) {
	logger.error(e.toString());
	throw new GlRuntimeException(
			"Retrieve overtime apply detail Exception. ", e);
}
return object;
}
 
 
 public Object getLeaveReportType(Object parameterObject)
	throws GlRuntimeException {
Object object;
try {
	object = commonSQLMapAdapter.executeQuery(             
			"ess.common.getLeaveReportType", parameterObject);
} catch (Exception e) {
	logger.error(e.toString());
	throw new GlRuntimeException(
			"Retrieve overtime apply detail Exception. ", e);
}
return object;
}
 
 
 public List getOverTimeApplerList(Object parameterObject)
	throws GlRuntimeException {
  List list;
try {
	list = commonSQLMapAdapter.executeQueryForMulti(          
			"ess.common.getOverTimeApplerList", parameterObject);
} catch (Exception e) {
	logger.error(e.toString());
	throw new GlRuntimeException(
			"Retrieve overtime apply detail Exception. ", e);
}
return list;
}
 
 
 public List getLeaveApplerList(Object parameterObject)
	throws GlRuntimeException {
List list;
try {
	list = commonSQLMapAdapter.executeQueryForMulti(          
			"ess.common.getLeaveApplerList", parameterObject);
} catch (Exception e) {
	logger.error(e.toString());
	throw new GlRuntimeException(
			"Retrieve leave apply detail Exception. ", e);
}
return list;
}
	
	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyOTAffirmor(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.retrieveApplyOTAffirmor", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve overtime apply detail Exception. ", e);
		}
		return object;
	}
	
	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyAffirmor(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.retrieveApplyAffirmor", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve overtime apply detail Exception. ", e);
		}
		return object;
	}

	/**
	 * retrieve apply leave detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyLeaveDetail(Object parameterObject)
			throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQuery(
					"ess.common.retrieveApplyLeaveDetail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve leave apply detail Exception. ", e);
		}
		return object;
	}
	
	public List regularlySentMailInfoList(Object parameterObject) throws GlRuntimeException {

	List object=null;
	try {
	
		object = commonSQLMapAdapter.executeQueryForMulti(
				"ess.common.regularlySentMailInfoList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"regularlySentMailInfoList Exception. ", e);
	}
	return object;
	}
	public void insertRegularlySentMailInfo(Object parameterObject) throws GlRuntimeException {
	
		try {
		
			commonSQLMapAdapter.update(
					"ess.common.insertRegularlySentMailInfo", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insertRegularlySentMailInfo Exception. ", e);
		}
	
	}
	
	public void updateRegularlySentMailCounts(Object parameterObject) throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.update(
					"ess.common.updateRegularlySentMailCounts", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"updateRegularlySentMailCounts Exception. ", e);
		}
	
	}
	
	
	public List regularlyUpdateOtAffirmorInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularlyUpdateOtAffirmorInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlyUpdateOtAffirmorInfoList Exception. ", e);
		}
		return object;
	}
	
	public void updateRegularlyOtAffirmorInfo(Object parameterObject) throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.update(
					"ess.common.updateRegularlyOtAffirmorInfo", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"updateRegularlyOtAffirmorInfo Exception. ", e);
		}
	
	}
	
	public void insertRegularlyOtAffirmorInfo(Object parameterObject) throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.insert(
					"ess.common.insertRegularlyOtAffirmorInfo", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insertRegularlyOtAffirmorInfo Exception. ", e);
		}
	
	}
	
	
	/**
	 * delete RegularlyOtAffirmorInfo
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteRegularlyOtAffirmorInfo(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ess.common.deleteRegularlyOtAffirmorInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("deleteRegularlyOtAffirmorInfo Exception. ", e);
		}
	}
	
	
	public List regularly780SentOtEmpidInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularly780SentOtEmpidInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularly780SentOtEmpidInfoList Exception. ", e);
		}
		return object;
	}
	
	public List regularly610SentOtEmpidInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularly610SentOtEmpidInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularly610SentOtEmpidInfoList Exception. ", e);
		}
		return object;
	}
	
	public List regularly780SentOtInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularly780SentOtInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularly780SentOtInfoList Exception. ", e);
		}
		return object;
	}
	
	public List regularly610SentOtInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularly610SentOtInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularly610SentOtInfoList Exception. ", e);
		}
		return object;
	}
	
	
	public void insertRegularlySentOtInfoRecords(Object parameterObject) throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.insert(
					"ess.common.insertRegularlySentOtInfoRecords", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insertRegularlySentOtInfoRecords Exception. ", e);
		}
	
	}
	
	public List regularlySentArModifyEmpidInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularlySentArModifyEmpidInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentArModifyEmpidInfoList Exception. ", e);
		}
		return object;
	}
	
	
	public List regularlySentArAbnormalEmpidInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularlySentArAbnormalEmpidInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentArAbnormalEmpidInfoList Exception. ", e);
		}
		return object;
	}
	
	
	public List regularlySentArModifyInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularlySentArModifyInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentArModifyInfoList Exception. ", e);
		}
		return object;
	}
	
	
	public List regularlySentArAbnormalInfoList(Object parameterObject) throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"ess.common.regularlySentArAbnormalInfoList", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentArAbnormalInfoList Exception. ", e);
		}
		return object;
	}
	
	public void insertRegularlySentArModifyInfoRecords(Object parameterObject) throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.insert(
					"ess.common.insertRegularlySentArModifyInfoRecords", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insertRegularlySentArModifyInfoRecords Exception. ", e);
		}
	
	}
	
public void insertRegularlySentArAbnormalInfoRecords(Object parameterObject) throws GlRuntimeException {
		
		try {
		
			commonSQLMapAdapter.insert(
					"ess.common.insertRegularlySentArAbnormalInfoRecords", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insertRegularlySentArAbnormalInfoRecords Exception. ", e);
		}
	
	}
	

	
}
