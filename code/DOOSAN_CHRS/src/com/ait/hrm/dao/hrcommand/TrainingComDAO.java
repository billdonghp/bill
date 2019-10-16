/*
 * @(#)TrainingComDAO.java 1.0 2006-12-23 下午05:32:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class TrainingComDAO {
    private static final Logger logger = Logger.getLogger(ExpInsideComDAO.class);

    private CommonSQLMapAdapter sqlMap = null;

    public TrainingComDAO() {
	sqlMap = new CommonSQLMapAdapter("em2");
    }
    /**
     * 添加培训信息
     * @param lStudying
     */
    public void insertStudying(List lStudying){
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertStudying",lStudying);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加培训信息失败！", e);
	}
	
    }
    /**
     * 删除培训信息
     * @param lStudying
     */
    public void delStudying(List lStudying){
	try {
	    sqlMap.updateForMulti("hrm.hrcommand.delStudying",lStudying);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除培训信息失败！", e);
	}
    }
    /**
     * 取得培训信息
     * @param paramObject
     * @return
     */
    public List getStudying(Object paramObject){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getStudying",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得培训信息失败！", e);
	}
	return list;
    }
    /**
     * 分页取得培训信息
     * @param paramObject
     * @return
     */
    public List getStudying(Object paramObject,int currentPage,int pageSize){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getStudying",paramObject,currentPage,pageSize);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得培训信息失败！", e);
	}
	return list;
    }
    /**
     * 取得培训信息记录数
     * @param paramObject
     * @return
     */
    public Object getStudyingCnt(Object paramObject){
	Object size;
	try {
	    size=sqlMap.executeQuery("hrm.hrcommand.getStudyingCnt",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得培训信息记录数失败！", e);
	}
	return size;
    }
}

