/*
 * @(#)ResignComDAO.java 1.0 2006-12-23 下午05:32:28
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.hrcommand;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class ResignComDAO {
    private static final Logger logger = Logger
	    .getLogger(ExpInsideComDAO.class);

    private CommonSQLMapAdapter sqlMap = null;

    public ResignComDAO() {
	sqlMap = new CommonSQLMapAdapter("em2");
    }

    /**
     * 添加离职发令
     * 
     * @param lResignation
     */
    public void insertResignation(List lResignation) {
	try {
	    sqlMap.insertForMulti("hrm.hrcommand.insertResignation",lResignation);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("添加离职信息失败！", e);
	}

    }

    /**
     * 删除离职信息
     * 
     * @param lResignation
     */
    public void delResignation(List lResignation) {
	try {
	    sqlMap.deleteForMulti("hrm.hrcommand.delResignation", lResignation);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除离职信息失败！", e);
	}

    }

    /**
     * 删除离职信息
     * 
     * @param lResignation
     */
    public void delResignation(Object resignation) {
	try {
	    sqlMap.delete("hrm.hrcommand.delResignation", resignation);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("删除离职信息失败！", e);
	}

    }

    /**
     * 执行退社发令
     * 
     * @param lResignation
     */
    public void executeResignation(Object resignation) {
	try {
	    sqlMap.startTransaction();
	    sqlMap.update("hrm.hrcommand.updateEmployeeDateLeft",resignation);
	    sqlMap.update("hrm.hrcommand.updateResignation",resignation);
	    sqlMap.commitTransation();
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("执行离职发令失败！", e);
	} finally {
	    try {
		sqlMap.endTransation();
	    } catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException("关闭连接失败！", e);

	    }
	}
    }
    /**
     * 批量执行退社发令
     * 
     * @param lResignation
     */
    public void executeResignation(List lResignation) {
	try {
	    sqlMap.startTransaction();
	    sqlMap.updateForMulti("hrm.hrcommand.updateEmployeeDateLeft",lResignation,false);
	    sqlMap.updateForMulti("hrm.hrcommand.updateResignation",lResignation,false);
	    sqlMap.commitTransation();    
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("批量执行离职发令失败！", e);
	} finally {
	    try {
		sqlMap.endTransation();
	    } catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException("关闭连接失败！", e);

	    }
	}
    }

    /**
     * 取退社信息
     * 
     * @param paramObject
     * @return
     */
    public List getResignation(Object paramObject) {
	List list;
	try {
	    list = sqlMap.executeQueryForMulti("hrm.hrcommand.getResignation",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取退社信息失败！", e);
	}
	return list;
    }
    /**
     * 取退社信息
     * 
     * @param paramObject
     * @return
     */
    public List getResignation(Object paramObject,int currentPage,int pageSize) {
	List list;
	try {
	    list = sqlMap.executeQueryForMulti("hrm.hrcommand.getResignation",paramObject,currentPage,pageSize);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取退社信息失败！", e);
	}
	return list;
    }
    
    
    public List getResignInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getResignInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退社信息失败！", e);   
    	}
    	return list;
        }
    
    
    public List getRewardInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getRewardInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退奖励息失败！", e);   
    	}
    	return list;
        }
    
    
    public List getExperienceInsideInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getExperienceInsideInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退部门调动息失败！", e);   
    	}
    	return list;
        }
    
    
    public List getSuspensionInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getSuspensionInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退休职息失败！", e);   
    	}
    	return list;
        }
    
    
    public List getPluralityInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getPluralityInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退兼职息失败！", e);   
    	}
    	return list;
        }
    
    public List getDispatchInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getDispatchInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退派遣息失败！", e);   
    	}
    	return list;
        }
    
    
    public List getSdutyingInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getSdutyingInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退留学息失败！", e);   
    	}
    	return list;
        }
    
    public List getPulishInfo(Object paramObject) {
    	List list;
    	try {
    	    list = sqlMap.executeQueryForMulti("hrm.common.getPulishInfo",paramObject);
    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException("取退惩戒息失败！", e);   
    	}
    	return list;
        }
    /**
     * 取退社信息记录数
     * 
     * @param paramObject
     * @return
     */
    public Object getResignationCnt(Object paramObject) {
	Object size;
	try {
	    size = sqlMap.executeQuery("hrm.hrcommand.getResignationCnt",paramObject);
	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException("取退社信息记录数失败！", e);
	}
	return size;
    }
}
