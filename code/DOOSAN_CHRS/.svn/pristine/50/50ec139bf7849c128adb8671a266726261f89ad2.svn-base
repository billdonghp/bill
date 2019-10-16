/*
 * @(#)PostGradeDAO.java 1.0 2007-9-7 上午12:13:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.postgrade;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;


public class PostGradeDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	public PostGradeDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	public List getPostGrade() {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("sys.common.getPostGrade");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}           

	public void InsertPostGrade(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.InsertPostGrade", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdatePostGrade(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.UpdatePostGrade", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}  

	public void deletePostGrade(String postgradeid) {
		try {
			commonSQLMapAdapter.delete("sys.common.deletePostGrade", postgradeid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object getPostGradeById(String id) {
		Object pb = null;
		try {
			pb = commonSQLMapAdapter.executeQuery("sys.common.getPostGradeById",id);
		          
		} catch (SQLException e) {
			e.printStackTrace(); 
		}                                                         
		return pb;                     
	}
	
	public boolean isPostGradeIdExsit(String postgradeid) {
		int num = 0;
		boolean flag=false;
		try {
			num =(Integer) commonSQLMapAdapter.executeQuery("sys.common.isPostGradeIdExsit", postgradeid);
			if(num>=1)                  
				flag=true;   
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
            return flag;
	}
}

