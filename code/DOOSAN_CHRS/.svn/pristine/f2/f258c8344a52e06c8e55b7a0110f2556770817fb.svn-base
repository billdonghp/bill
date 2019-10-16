/*
 * @(#)DispatchDAO.java 1.0 2006-12-23 下午04:35:40
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
/**
 * 发令> 派遣
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-23 下午05:23:10
 * @version 1.0
 *
 */
public class DispatchComDAO {
    private CommonSQLMapAdapter sqlMap = null;

    private static final Logger logger = Logger.getLogger(DispatchComDAO.class);
    public DispatchComDAO(){
	sqlMap = new CommonSQLMapAdapter("em2");
    }
    /**
     * 添加派遣信息
     * @param lDispatch
     */
    public void insertDispatch(List lDispatch){
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertDispatch",lDispatch);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加派遣信息失败！", e);
	}
    }
    /**
     * 派遣期满本职信息
     * @param lDispatch
     */
    public void updateDispatch(List lDispatch){
	try {
	    sqlMap.updateForMulti("hrm.hrcommand.updateDispatch",lDispatch);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("派遣期满本职信息失败！", e);
	}
    }
    /**
     * 删除派遣信息
     * @param lDispatch
     */
    public void delDispatch(List lDispatch){
	try {
	    sqlMap.deleteForMulti("hrm.hrcommand.delDispatch",lDispatch);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除派遣信息失败！", e);
	}
    }
    /**
     * 删除派遣信息
     * @param dispatch
     */
    public void delDispatch(Object dispatch){
	try {
	    sqlMap.delete("hrm.hrcommand.delDispatch",dispatch);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除派遣信息失败！", e);
	}
    }
    /**
     * 取得派遣和信息
     * @param paramObject
     * @return
     */
    public List getDispatch(Object paramObject){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getDispatch",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得派遣信息失败！", e);
	}
	return list;
    }
    /**
     * 分页取得派遣和信息
     * @param paramObject
     * @return
     */
    public List getDispatch(Object paramObject,int currentPage,
		int pageSize){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getDispatch",paramObject,currentPage,pageSize);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("分页取得派遣信息失败！", e);
	}
	return list;
    }
    /**
     * 取得派遣信息记录数
     * @param paramObject
     * @return
     */
    public Object getDispatchCnt(Object paramObject){
	Object size;
	try {
	    size=sqlMap.executeQuery("hrm.hrcommand.getDispatchCnt",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取得派遣记录数失败！", e);
	}
	return size;
    }
}

