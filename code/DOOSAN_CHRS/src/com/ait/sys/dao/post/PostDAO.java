/*
 * @(#)PostDAO.java 1.0 2007-9-8 上午02:00:34
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.post;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class PostDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PostDAO.class);

	public PostDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}


	
	public List getPostList(Object parameterObject,int currentPage,int pageSize)
	throws GlRuntimeException{  
	List list;
	try{
		list = commonSQLMapAdapter.executeQueryForMulti("sys.common.getPostList",
				parameterObject,currentPage,pageSize);  
	}catch(Exception e){
		logger.error(e.toString());
		throw new GlRuntimeException(
		"The information of Exception when getPostList  items. ", e);
	}
	return list;
}
	
	public Object getPostListCnt(Object parameterObject)
	throws GlRuntimeException{  
	Object obj;
	try{
		obj = commonSQLMapAdapter.executeQuery("sys.common.getPostListCnt",parameterObject);  
	}catch(Exception e){
		logger.error(e.toString());                            
		throw new GlRuntimeException(
		"The information of Exception when getPostList  items. ", e);
	}
	return obj;
}

	public void InsertPost(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.InsertPost", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdatePost(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.UpdatePost", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePost(String postid) {
		try {
			commonSQLMapAdapter.delete("sys.common.deletePost", postid);         
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object getPostById(String id) {  
		Object pb = null;
		try {
			pb = commonSQLMapAdapter.executeQuery("sys.common.getPostById",id);
		          
		} catch (SQLException e) {
			e.printStackTrace(); 
		}                                                         
		return pb;                     
	}            
	public boolean isPostIdExsit(String postid) {
		int num = 0;
		boolean flag=false;
		try {
			num =(Integer) commonSQLMapAdapter.executeQuery("sys.common.isPostIdExsit", postid);
			if(num>=1)                  
				flag=true; 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
            return flag;
	} 
}
