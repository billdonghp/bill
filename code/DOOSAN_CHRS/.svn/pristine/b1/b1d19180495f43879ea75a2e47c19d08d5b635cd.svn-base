/*
 * @(#)RewardInfoDAO.java 1.0 2006-12-21 下午05:22:34
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
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

public class RewardInfoDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(RewardInfoDAO.class);

	public RewardInfoDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * retrieve reward information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveRewardInfo(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"hrm.empinfo.retrieveRewardInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve reward information Exception. ", e);
		}
		return list;
	}

	/**
	 * insert reward information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertRewardInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("hrm.empinfo.insertRewardInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert reward information Exception. ", e);
		}
	}

	/**
	 * update reward information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateRewardInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("hrm.empinfo.updateRewardInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"update reward information Exception. ", e);
		}
	}
	
	/**
	 * delete reward information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteRewardInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.deleteForMulti("hrm.empinfo.deleteRewardInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"delete reward information Exception. ", e);
		}
	}
	
	/**
	 * 分页取奖励信息
	 * @param paramObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
    public List getRewardInfo(Object paramObject,int currentPage,int pageSize){
	List list;
	try{
	    list=commonSQLMapAdapter.executeQueryForMulti("hrm.hrcommand.getRewardInfo", paramObject,currentPage,pageSize);
	}catch(Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("分页取奖励信息失败！", e);
	}
	return list;
    }
    
    /**
     * 取奖励信息记录数
     * @param paramObject
     * @return
     */
    public Object getRewardCnt(Object paramObject){
    	Object size;
    	try{
    	    size=commonSQLMapAdapter.executeQuery("hrm.hrcommand.getRewardCnt", paramObject);
    	}catch(Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取奖励信息记录数失败！", e);
    	}
    	return size;
    }
    
    
	
}

