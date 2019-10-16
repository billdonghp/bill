/*
 * @(#)PluralityDAO.java 1.0 2006-12-23 下午05:24:41
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class PluralityComDAO {
    private static final Logger logger = Logger.getLogger(ExpInsideComDAO.class);

    private CommonSQLMapAdapter sqlMap = null;

    public PluralityComDAO() {
	sqlMap = new CommonSQLMapAdapter("em2");
    }
    /**
     * 添加兼职信息
     * @param lPlurality
     */
    public void insertPlurality(List lPlurality){
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertPlurality",lPlurality);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加兼职信息失败！", e);
	}
    }
    /**
     * 兼职解除
     * @param lPlurality
     */
    public void updatePlurality(List lPlurality){
	try {
	    sqlMap.updateForMulti("hrm.hrcommand.updatePlurality",lPlurality);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("兼职解除失败！", e);
	}
    }
    /**
     * 删除休职
     * @param lPlurality
     */
    public void delPlurality(List lPlurality){
	try {
	    sqlMap.deleteForMulti("hrm.hrcommand.delPlurality",lPlurality);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除休职信息失败！", e);
	}
    }
    /**
     * 删除休职信息
     * @param plurality
     */
    public void delPlurality(Object plurality){
	try {
	    sqlMap.delete("hrm.hrcommand.delPlurality",plurality);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除休职信息失败！", e);
	}
    }
    /**
     * 取休职信息
     * @param paramObject
     * @return
     */
    public List getPlurality(Object paramObject){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getPlurality",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得休职信息失败！", e);
	}
	return list;
    }
    /**
     * 分页取兼职信息
     * @param paramObject
     * @return
     */
    public List getPlurality(Object paramObject,int currentPage,
		int pageSize){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getPlurality",paramObject,currentPage,pageSize);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("分页取得兼职信息失败！", e);
	}
	return list;
    }
    /**
     * 取休职信息记录数
     * @param paramObject
     * @return
     */
    public Object getPluralityCnt(Object paramObject){
	Object size;
	try {
	    size=sqlMap.executeQuery("hrm.hrcommand.getPluralityCnt",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得休职记录数失败！", e);
	}
	return size;
    }
}
