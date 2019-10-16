/*
 * @(#)TipMessageDao.java 1.0 2007-8-29 下午04:15:48
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.tipmessagedao;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class TipMessageDao {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	public TipMessageDao() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	public String getLogOnPersonAffirmMaxLevel(String statementname, Object obj) {
		String MaxLevel = "";
		try {
			MaxLevel = (String) commonSQLMapAdapter.executeQuery(statementname, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return MaxLevel;
	}

	public int getCount(String statementname, SimpleMap map) {
		int num = 0;
		try {
			num = (Integer) commonSQLMapAdapter.executeQuery(statementname, map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return num;
	}

	public List getHealthOverTimeInfoList(String statementname) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(statementname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return list;
	}

	public List getExpiredProbation(String statementname) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(statementname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return list;
	}

	/**
	 *  得到过期合同列表
	 * @param statementname
	 * @param map
	 * @return
	 */
	public List getExpiredContract(String statementname) {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti(statementname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return list;
	}

	public int getPersonalApplyOtCount(Object sm) {
		int num = 0;
		try {
			num = (Integer) commonSQLMapAdapter.executeQuery("hrm.tip.getPersonalApplyOtCount", sm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return num;       
	}

	public int getPersonalInfoApplyCount(String empid) {
		int num = 0;
		try {
			num = (Integer) commonSQLMapAdapter.executeQuery("hrm.tip.getPersonalInfoApplyCount", empid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return num;
	}

	public int getPersonalApplyErvectionCount(String empid) {
		int num = 0;
		try {
			num = (Integer) commonSQLMapAdapter.executeQuery("hrm.tip.getPersonalApplyErvectionCount", empid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return num;
	}

	public int getPersonalApplyTrainningCount(String empid) {
		int num = 0;
		try {
			num = (Integer) commonSQLMapAdapter.executeQuery("hrm.tip.getPersonalApplyTrainningCount", empid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return num;
	}

	public int getPersonalApplyLeavelCount(String empid) {
		int num = 0;
		try {
			num = (Integer) commonSQLMapAdapter.executeQuery("hrm.tip.getPersonalApplyLeavelCount", empid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return num;
	}
}
