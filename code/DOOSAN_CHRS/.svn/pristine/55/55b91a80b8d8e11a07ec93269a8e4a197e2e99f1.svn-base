/*
 * @(#)PostGradeDAO.java 1.0 2007-9-7 上午12:13:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.dao.postgrade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil ;


public class PostGradeDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PostGradeDAO.class);

	public PostGradeDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	public List getPostGrade(SimpleMap parameterObject) {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("pa.postgrade.getPostGrade",parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}
	
	public List getDeptPostGrade(SimpleMap parameterObject) {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("pa.postgrade.getDeptPostGrade",parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}
	
	public List getPostGradeYear(SimpleMap parameterObject) {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("pa.postgrade.getPostGradeYear",parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}           

	public void InsertPostGrade(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("pa.postgrade.InsertPostGrade", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int validateInsertPostGrade(SimpleMap map) {
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.postgrade.validateInsertPostGrade", map)) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result ;
	}

	public void UpdatePostGrade(SimpleMap map) {
		try {
			commonSQLMapAdapter.insert("pa.postgrade.UpdatePostGrade", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateDeptPostGrade(List updateList) {
		try {
			commonSQLMapAdapter.updateForMulti("pa.postgrade.UpdateDeptPostGrade", updateList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}  

	public void deletePostGrade(SimpleMap parameterObject) {
		try {
			commonSQLMapAdapter.delete("pa.postgrade.deletePostGrade", parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object getPostGradeById(SimpleMap parameterObject) {
		Object pb = null;
		try {
			pb = commonSQLMapAdapter.executeQuery("pa.postgrade.getPostGradeById",parameterObject);
		          
		} catch (SQLException e) {
			e.printStackTrace(); 
		}                                                         
		return pb;                     
	}
	
	public List getPostGradeTypeList() {
		List pb = new ArrayList();
		try {
			pb = commonSQLMapAdapter.executeQueryForMulti("pa.postgrade.getPostGradeTypeList");
		          
		} catch (SQLException e) {
			e.printStackTrace(); 
		}                                                         
		return pb;                     
	}
	public void deletePostGrade(String postgradeid) {
		try {
			commonSQLMapAdapter.delete("pa.postgrade.deletePostGrade", postgradeid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List getPostGradeYear() {
		List list = null;
		try {
			list = (List) commonSQLMapAdapter.executeQueryForMulti("pa.postgrade.getPostGradeYear");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;   
	}    
}

