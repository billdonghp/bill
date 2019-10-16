/*
 * @(#)PostGroupDAO.java 1.0 2007-9-7 上午12:15:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.dao.postgroup;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
                 

public class PostGroupDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	public PostGroupDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	  

	public List getPostGroup() {
		List list=null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("sys.common.getPostGroup"); 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}                                      
		return list; 
	}
	
	public Object getPostGroupForUpdate(String id) {
		Object pb = null;
		try {
			pb = commonSQLMapAdapter.executeQuery("sys.common.getPostGroupForUpdate",id);
		          
		} catch (SQLException e) {
			e.printStackTrace(); 
		}                                                         
		return pb;                     
	}                                   
	
	
   
	public void InsertPostGroup(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("sys.common.InsertPostGroup", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void UpdatePostGroup(SimpleMap map) {
		try {
			commonSQLMapAdapter.update("sys.common.UpdatePostGroup", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}                 
	}

	public void deletePostGroup(String postgroupid) {
		try {
			commonSQLMapAdapter.delete("sys.common.deletePostGroup", postgroupid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isGroupIdExsit(String postgroupid) {
		int num=0;
		boolean flag=false;
		try {
		num=(Integer) commonSQLMapAdapter.executeQuery("sys.common.isGroupIdExsit", postgroupid);
			if(num>=1)
				flag=true;
		} catch (SQLException e) {
			e.printStackTrace();     
		}
		return flag;               
	}    
	
	public void deletePostByGroupId(String ID)
	{
		try {
			commonSQLMapAdapter.delete("sys.common.deletePostByGroupId",ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePostByForGroupId(SimpleMap map)
	{
		try {
			commonSQLMapAdapter.update("sys.common.updatePostByForGroupId",map);           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteCascade(String groupid) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			this.deletePostByGroupId(groupid);
			this.deletePostGroup(groupid);
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Insert basic information Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	public void updateCascade(SimpleMap map) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			this.updatePostByForGroupId(map);
			this.UpdatePostGroup(map);

			commonSQLMapAdapter.commitTransation();            

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException(
					"Insert basic information Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
}
    
