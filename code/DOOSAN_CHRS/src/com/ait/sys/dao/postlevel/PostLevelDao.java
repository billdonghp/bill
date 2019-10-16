/*
 * @(#)PostLevel.java 1.0 2007-9-9 下午03:10:05
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.postlevel;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;   
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;


public class PostLevelDao {
	
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	public PostLevelDao() {  
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
    
	  

	public List getPostLevelList() {
		List list=null;                     
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("sys.common.getPostLevelList");  
		} catch (SQLException e) {
			e.printStackTrace();   
		}                                      
		return list; 
	}
	
	public Object getPostLevelById(String id) {
		Object pb = null;
		try {
			pb = commonSQLMapAdapter.executeQuery("sys.common.getPostLevelById",id);
		          
		} catch (SQLException e) {  
			e.printStackTrace(); 
		}                                                         
		return pb;                     
	}                                   
	
	
   
	public void insertPostLevel(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.insertPostLevel", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePostLevel(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.updatePostLevel", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePostLevel(String levelid) {
		try {
			commonSQLMapAdapter.delete("sys.common.deletePostLevel", levelid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isPostLevelIdExsit(String postlevelid) {
		int num=0;
		boolean flag=false;
		try {
		num=(Integer)commonSQLMapAdapter.executeQuery("sys.common.isPostLevelIdExsit", postlevelid);
		if(num>=1)
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}           
		return flag;
	}

}

