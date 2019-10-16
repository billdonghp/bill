/*
 * @(#)PunishComDAO.java 1.0 2006-12-23 下午05:39:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class PunishComDAO {
    private static final Logger logger = Logger
	    .getLogger(ExpInsideComDAO.class);

    private CommonSQLMapAdapter sqlMap = null;

    public PunishComDAO() {
	sqlMap = new CommonSQLMapAdapter("em2");
    }
    /**
     * 添加惩戒信息
     * @param lPunishMent
     */
    public void insertPunishMent(List lPunishMent){
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertPunishMent",lPunishMent);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加惩戒信息失败！", e);
	}
    }
    /**
     * 删除惩戒信息
     * @param lPunishMent
     */
    public void delPunishMent(List lPunishMent){
	try {
	    sqlMap.deleteForMulti("hrm.hrcommand.delPunishMent",lPunishMent);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除惩戒信息失败！", e);
	}
    }
    /**
     * 删除惩戒信息
     * @param punishMent
     */
    public void delPunishMent(Object punishMent){
	try {
	    sqlMap.delete("hrm.hrcommand.delPunishMent",punishMent);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除惩戒信息失败！", e);
	}
    }
    /**
     * 取惩戒信息
     * @param paramObject
     * @return
     */
    public List getPunishMent(Object paramObject){
	List list;
	try{
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getPunishMent", paramObject);
	}catch(Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取惩戒信息失败！", e);
	}
	return list;
    }
    /**
     * 分页取惩戒信息
     * @param paramObject
     * @return
     */
    public List getPunishMent(Object paramObject,int currentPage,int pageSize){
	List list;
	try{
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getPunishMent", paramObject,currentPage,pageSize);
	}catch(Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("分页取惩戒信息失败！", e);
	}
	return list;
    }
    /**
     * 取惩戒信息记录数
     * @param paramObject
     * @return
     */
    public Object getPunishMentCnt(Object paramObject){
	Object size;
	try{
	    size=sqlMap.executeQuery("hrm.hrcommand.getPunishMentCnt", paramObject);
	}catch(Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取惩戒信息记录数失败！", e);
	}
	return size;
    }
}
