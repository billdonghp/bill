/*
 * @(#)HrSalaryDao.java 1.0 2007-1-23 下午09:30:33
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.pa;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2007-1-23 下午09:30:33
 * @version 1.0
 * 
 */
public class HrSalaryDao {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	
	public HrSalaryDao() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	public List searchEmployeeInfo(Object parameterObject)
		throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.pa.retrieveEmployeeInfoByEmployeeInfo", 
					parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when searching employees according to some employee information. ", e);
		}
		return list;
	}
}

