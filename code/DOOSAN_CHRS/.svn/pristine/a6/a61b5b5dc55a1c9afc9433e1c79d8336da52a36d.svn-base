/*
 * @(#)UpgradeDAO.java 1.0 2006-12-23 下午05:26:47
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class ExpInsideComDAO {
    private static final Logger logger = Logger
	    .getLogger(ExpInsideComDAO.class);

    private CommonSQLMapAdapter sqlMap = null;

    public ExpInsideComDAO() {
	sqlMap = new CommonSQLMapAdapter("em2");
    }

    /**
     * 批量添加调动信息
     * 
     * @param lExpInside
     */
    public void insertExpInside(List lExpInside) {
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertExpInside", lExpInside);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加调动信息失败！", e);
	}
    }
    
    /**
     * 批量添加调动信息并将员工属性保存到hr_experience_inside_history 表
     * 
     * @param lExpInside
     */
    public void insertExpInsideAndSaveOldEmpProperty(List lExpInside) {
    	try {
    	    sqlMap.insertForMulti("hrm.hrcommand.insertExpInside", lExpInside);
    	    sqlMap.insertForMulti("hrm.hrcommand.saveOldExpProperty", lExpInside);    
    	} catch (Exception e) {  
    		 logger.error(e.toString());
    		    throw new GlRuntimeException("添加调动信息失败！", e);              
    	} finally {
    	    try {
    		sqlMap.endTransation();
    	    } catch (SQLException e) {
    		logger.error(e.toString());
    		 logger.error(e.toString());
    		    throw new GlRuntimeException("添加调动信息失败！", e);
    	    }
    	}
        }
    
    
    

    /**
     * 添加调动信息
     * 
     */
    public void insertExpInside(Object expInside) {
	try {
	    sqlMap.insert("hrm.hrcommand.insertExpInside", expInside);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加调动信息失败！", e);
	}
    }

    /**
     * 取得调动列表
     * 
     * @param parameterObject
     * @return
     */
    public List getExpInside(Object parameterObject) {
	List list;
	try {

	    list = sqlMap.executeQueryForMulti("hrm.hrcommand.getExpInside",
		    parameterObject);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "getExpInside information Exception. ", e);
	}
	return list;
    }
    /**
     * 分页取得调动列表
     * 
     * @param parameterObject
     * @return
     */
    public List getExpInside(Object parameterObject,int currentPage,
		int pageSize) {
	List list;
	try {

	    list = sqlMap.executeQueryForMulti("hrm.hrcommand.getExpInside",
		    parameterObject,currentPage,pageSize);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "getExpInside information Exception. ", e);
	}
	return list;
    }                                                          
    
    
    
    public List searchEmpHistoryById(Object parameterObject) {
    	List list;  
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.hrcommand.searchEmpHistoryById", parameterObject);
    	} catch (Exception e) {  
    	    logger.error(e.toString());
    	    throw new GlRuntimeException(
    		    "searchEmpHistoryById information Exception. ", e);
    	}
    	return list;
        }
    /**
     * 分页取得调动列表计录数
     * 
     * @param parameterObject
     * @return
     */
    public Object getExpInsideCnt(Object parameterObject) {
	Object size;
	try {

	    size = sqlMap.executeQuery("hrm.hrcommand.getExpInsideCnt",parameterObject);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "getExpInsideCnt information Exception. ", e);
	}
	return size;
    }
    /**
     * 删除发令信息
     * @param expInside
     */
    public void delExpInside(Object expInside) {
	try {
	    sqlMap.delete("hrm.hrcommand.delExpInside", expInside);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("delExpInside Exception ", e);
	}
    }
    /**
     * 删除发令信息
     * @param expInside
     */
    public void delExpInside(List lExpInside) {
	try {
	    sqlMap.deleteForMulti("hrm.hrcommand.delExpInside", lExpInside);
	} catch (SQLException e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("delExpInside Exception ", e);
	}
    }

    /**
     * 执行发令
     * 
     * @param expInside
     */
    public void executeExpInside(Object expInside) {
	try {
	    sqlMap.startTransaction();
	    sqlMap.update("hrm.hrcommand.updateEmployeeByExp", expInside);
	    sqlMap.update("hrm.hrcommand.updateEmpPostByExp", expInside);
	    sqlMap.update("hrm.hrcommand.updateExpInside", expInside);
	    sqlMap.commitTransation();
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("executeExpInside  Exception. ", e);
	} finally {
	    try {
		sqlMap.endTransation();
	    } catch (SQLException e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
			"executeExpInside endTransaction Exception. ", e);
	    }
	}
    }

    /**
     * 批量执行发令
     * 
     * @param expInside
     */
    public void executeExpInside(List lExpInside) {
	try {
	    sqlMap.startTransaction();
	    sqlMap.updateForMulti("hrm.hrcommand.updateEmployeeByExp",lExpInside,false);//更新员工表数据
	    sqlMap.updateForMulti("hrm.hrcommand.updateEmpPostByExp", lExpInside,false);//更改员工职位属性
	    sqlMap.updateForMulti("hrm.hrcommand.updateExpInside", lExpInside,false);//更改标示为1
	    sqlMap.commitTransation();
	} catch (Exception e) {  
	    logger.error(e.toString());
	    throw new GlRuntimeException("batch executeExpInside  Exception. ", e);
	} finally {
	    try {
		sqlMap.endTransation();
	    } catch (SQLException e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
			" batch executeExpInside endTransaction Exception. ", e);
	    }
	}
    }
    
    public void updateEmpStatus() {
    	try {
    	  
    	    sqlMap.update("hrm.hrcommand.updateEmpStatus");
    	 
    	} catch (Exception e) {  
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("batch executeExpInside  Exception. ", e);
    	} finally {
    	    try {
    		sqlMap.endTransation();
    	    } catch (SQLException e) {
    		logger.error(e.toString());
    		throw new GlRuntimeException(
    			" batch executeExpInside endTransaction Exception. ", e);
    	    }
    	}
        }

}
