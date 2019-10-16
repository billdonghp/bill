/*
 * @(#)DeparntMentChangeDAO.java 1.0 2007-9-29 上午10:29:45
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.department;

import java.sql.SQLException;
import org.apache.log4j.Logger;  
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;


public class DepartMentChangeDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	
	public DepartMentChangeDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	
	
	public void changeDepartmentByMonth()throws GlRuntimeException
	{
		
		try {  
		 commonSQLMapAdapter.insert("hrm.department.insertDeparntmentIntoHistory");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    
	}   
	
	
	public boolean isExistHistoryData(Object obj)throws GlRuntimeException
	{
		boolean flag=false;
		try {  
		 int num=(Integer)commonSQLMapAdapter.executeQuery("hrm.department.isExistHistoryData",obj);  
		 if(num>0)
		  flag=true;
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
      return  flag;
	}   
	
	
	
}

