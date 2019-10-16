/*
 * @(#)GetSelectListDao.java 1.0 2007-9-9 下午07:42:41
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.post;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
  

public class SelectListDao {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(SelectListDao.class);

	public SelectListDao() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	public List retrievePostGroupList()
	{
		List list =null;
		try {
			list=(List) commonSQLMapAdapter.executeQueryForMulti("sys.common.retrievepostgrouplist");  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}              
		return list;
	}

}

