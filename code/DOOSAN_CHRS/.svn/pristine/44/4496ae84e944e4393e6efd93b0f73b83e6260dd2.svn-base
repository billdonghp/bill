/*
 * @(#)TipMessageViewDAO.java 1.0 2007-9-17 下午03:29:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.tipmessagesearch;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class TipMessageViewDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	public TipMessageViewDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	public List getHealthTipMessageByConditon(SimpleMap map,int currentPage,int pageSize) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getHealthTipMessageByConditon", map, currentPage,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block   
			e.printStackTrace();
		}
		return list;
	}
	
	public List getHealthTipMessageByConditon(SimpleMap map) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getHealthTipMessageByConditon", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block   
			e.printStackTrace();
		}
		return list;
	}                           
	
    /**
     * 分页取得调动列表计录数
     *            
     * @param parameterObject
     * @return
     */
    public Object getHealthTipMessageCNT(Object parameterObject) {
	Object size;
	try {                                       

	    size = commonSQLMapAdapter.executeQuery("hrm.tipmessagesearch.getHealthTipMessageCNT",parameterObject);

	} catch (Exception e) {
	    logger.error(e.toString());
	    throw new GlRuntimeException(
		    "getHealthTipMessageCNT information Exception. ", e);
	}
	return size;
    }
    
    
    public Object getExpiredProbationCNT(Object parameterObject) {
    	Object size;
    	try {                                       

    	    size = commonSQLMapAdapter.executeQuery("hrm.tipmessagesearch.getExpiredProbationCNT",parameterObject);

    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException(
    		    "getExpiredProbationCNT information Exception. ", e);
    	}
    	return size;
        }
               
    
    public Object getUpgradeInfoFieldCNT(Object parameterObject) {
    	Object size;
    	try {                                       

    	    size = commonSQLMapAdapter.executeQuery("hrm.tipmessagesearch.getUpgradeInfoFieldCNT",parameterObject);

    	} catch (Exception e) {
    	    logger.error(e.toString());
    	    throw new GlRuntimeException(
    		    "getUpgradeInfoFieldCNT information Exception. ", e);
    	}
    	return size;
        }

	public List getExpiredProbationByCondition(SimpleMap map,int currentPage,int pageSize) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getExpiredProbationByCondition", map,currentPage,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List getExpiredProbationByCondition(SimpleMap map) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getExpiredProbationByCondition", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List getUpgradeInfoFieldByCondition(SimpleMap map) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getUpgradeInfoFieldByCondition", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List getUpgradeInfoFieldByCondition(SimpleMap map,int currentPage,int pageSize) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getUpgradeInfoFieldByCondition", map,currentPage, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}                          

	public List getFourthDept() {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.tipmessagesearch.getFourthDept");  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}}
