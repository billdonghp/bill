/*
 * @(#)SuspendDAO.java 1.0 2006-12-23 下午05:27:07
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class SuspendComDAO {
    private static final Logger logger = Logger
	    .getLogger(ExpInsideComDAO.class);

    private CommonSQLMapAdapter sqlMap = null;

    public SuspendComDAO() {
	sqlMap = new CommonSQLMapAdapter("em2");
    }
    /**
     * 批量添加休职信息
     * @param lSuspend
     */
    public void insertSuspension(List lSuspend){
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertSuspension", lSuspend);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加休职信息失败！", e);
	}
    }
    /**
     * 复职
     * @param lSuspend
     */
    public void updateSuspension(List lSuspend)
    {
	try {
	    sqlMap.updateForMulti("hrm.hrcommand.updateSuspension", lSuspend);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("复职失败！", e);
	}
  }
    
    /**
     * 复职同时修改员工状态
     * @param lSuspend  
     */  
    public void updateSuspensionCascade(List lSuspend) {
    	    this.updateSuspension(lSuspend);
    	    this.updateEmployeeSuspensionStatus(lSuspend);    
        }
    
    public void updateEmployeeSuspensionStatus(List lSuspend) {
    	try {
    		  sqlMap.updateForMulti("hrm.hrcommand.updateEmployeeSuspensionStatus1", lSuspend);
    	} catch (Exception e) {         
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("复职失败！", e);
    	}   
       }
    
    
	/**
	 * 休职生效时更改员工状态
	 * 
	 * @param lResignation
	 */
	
	 public void batchExeModifyEmpStatus(List list){
			try {
			    sqlMap.updateForMulti("hrm.hrcommand.updateEmployeeSuspensionStatus2",list);
			} catch (Exception e) {
			    logger.error(e.toString());
			    throw new GlRuntimeException("修改员工状态失败！", e);  
			}
		    }
	 
	 
	 
	 public List getSuspensionForUpdateEmployeeStatus(){
		 List list=null;
			try {
				list=sqlMap.executeQueryForMulti("hrm.hrcommand.getSuspensionForUpdateEmployeeStatus");
			} catch (Exception e) {
			    logger.error(e.toString());
			    throw new GlRuntimeException("获取数据失败！", e);  
			}
			return list;
		 }    
  
    /**
     * 批量删除休职信息
     * @param lSuspend
     */
    public void delSuspension(List lSuspend){
	try {
	    sqlMap.deleteForMulti("hrm.hrcommand.delSuspension", lSuspend);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除休职信息失败！", e);
	}
    }
    /**
     * 删除休职信息
     * @param suspend
     */
    public void delSuspension(Object suspend){
	try {
	    sqlMap.delete("hrm.hrcommand.delSuspension", suspend);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除休职信息失败！", e);
	}
    }
    /**
     *  取得休职信息
     * @param paramObject
     * @return
     */
    public List getSuspension(Object paramObject){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getSuspension", paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("职得休职信息失败！", e);
	}
	return list;
    }
    /**
     *  分页取得休职信息
     * @param paramObject
     * @return
     */
    public List getSuspension(Object paramObject,int currentPage,int pageSize){
	List list;
	try {
	    list=sqlMap.executeQueryForMulti("hrm.hrcommand.getSuspension", paramObject,currentPage,pageSize);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("职得休职信息失败！", e);
	}
	return list;
    }
    /**
     *  取得休职信息记录数
     * @param paramObject
     * @return
     */
    public Object getSuspensionCnt(Object paramObject){
	Object size;
	try {
	    size=sqlMap.executeQuery("hrm.hrcommand.getSuspensionCnt", paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("职得休职信息记录数失败！", e);
	}
	return size;
    }
}
