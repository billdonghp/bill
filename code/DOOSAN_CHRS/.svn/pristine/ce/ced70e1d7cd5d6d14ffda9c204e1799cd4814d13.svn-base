/*
 * @(#)PostImantenaceService.java 1.0 2007-9-6 下午05:41:30
 *        
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sy.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.bean.ReportItem;
import com.ait.sy.bean.ReportTable;
import com.ait.sy.dao.AdminDAO;
import com.ait.sy.dao.InterfaceDAO;
import com.ait.sy.dao.SyParamDAO;
import com.ait.sys.dao.common.MenuDAO;
import com.ait.sys.dao.essLeave.EssLeaveDAO;
import com.ait.sys.dao.essOvertime.EssOvertimeDAO;
import com.ait.sys.dao.post.PostDAO;
import com.ait.sys.dao.post.SelectListDao;
import com.ait.sys.dao.postgrade.PostGradeDAO;
import com.ait.sys.dao.postgroup.PostGroupDAO;
import com.ait.sys.dao.postlevel.PostLevelDao;
import com.ait.util.DataAccessException;

public class SysService {

	private static SysService syservice;

	private InterfaceDAO interfacedao;

	private PostGroupDAO postgroupdao;

	private PostGradeDAO postgradedao;

	private PostDAO postdao;

	private PostLevelDao postleveldao;

	private SelectListDao getselectlistdao;

	private MenuDAO menuDAO;

	private AdminDAO adminDAO;

	private SyParamDAO syParamDAO;
	
	private EssLeaveDAO essleavedao ;
	
	private EssOvertimeDAO essOvertimeDAO ;

	/**
	 * 修改单例方法 by Pennix at 20080124
	 * @return
	 */
	public static SysService getInstance() {
		if (syservice != null)
			return syservice;
		else {
			syservice = new SysService();
			return syservice;
		}
	}

	private SysService() {
		postgroupdao = new PostGroupDAO();
		postgradedao = new PostGradeDAO();
		postdao = new PostDAO();
		postleveldao = new PostLevelDao();
		getselectlistdao = new SelectListDao();
		menuDAO = new MenuDAO();
		adminDAO = new AdminDAO();
		syParamDAO = new SyParamDAO();
		interfacedao = new InterfaceDAO();
		essleavedao = new EssLeaveDAO() ;
		essOvertimeDAO = new EssOvertimeDAO() ;
	}

	public AdminBean login(String username, String password) throws DataAccessException {
		return adminDAO.login(username, password);
	}
	
	public AdminBean searchEmp(String username,String cpnyId) throws DataAccessException {
		return adminDAO.searchEmp(username,cpnyId);
	}
	
	public void updateUserIP(String userIP,String personId) throws DataAccessException {
		   adminDAO.updateUserIP(userIP,personId);
	}

	public void saveParam(HttpServletRequest request, Class cls,String cpnyId) {
		try {
			syParamDAO.saveParam(request, cls,cpnyId);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}

	public List getPostList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return postdao.getPostList(parameterObject, currentPage, pageSize);
	}

	public Object getPostListCnt(Object parameterObject) throws GlRuntimeException {
		return postdao.getPostListCnt(parameterObject);
	}

	public boolean isPostIdExist(String postid) {
		return postdao.isPostIdExsit(postid);

	}

	public boolean isPostGradeIdExist(String posgradetid) {
		return postgradedao.isPostGradeIdExsit(posgradetid);

	}

	public List retrievePostGroupList() {
		return getselectlistdao.retrievePostGroupList();

	}

	public Object getPostById(String id) {
		return postdao.getPostById(id);

	}

	public void insertPost(SimpleMap map) {
		postdao.InsertPost(map);
	}

	public void updatePost(SimpleMap map) {
		postdao.UpdatePost(map);
	}

	public void deletePost(String postid) {
		postdao.deletePost(postid);
	}

	public void deleteCascade(String groupid) {
		postgroupdao.deleteCascade(groupid);
	}

	public void updateCascade(SimpleMap map) {
		postgroupdao.updateCascade(map);
	}

	public List getPostGroup() {
		return postgroupdao.getPostGroup();

	}

	public Object getPostGroupForUpdate(String id) {
		return postgroupdao.getPostGroupForUpdate(id);

	}

	public void insertPostGroup(SimpleMap map) {
		postgroupdao.InsertPostGroup(map);
	}

	public void updatePostGroup(SimpleMap map) {
		postgroupdao.UpdatePostGroup(map);
	}

	public void deletePostGroup(String groupid) {
		postgroupdao.deletePostGroup(groupid);
	}

	public boolean isGroupIdExsit(String groupid) {
		return postgroupdao.isGroupIdExsit(groupid);
	}

	public List getPostGradeList() {
		List list = postgradedao.getPostGrade();
		return list;
	}

	public Object getPostGradeById(String id) {
		Object obj = postgradedao.getPostGradeById(id);
		return obj;
	}

	public void insertPostGrade(SimpleMap map) {
		postgradedao.InsertPostGrade(map);
	}

	public void updatePostGrade(SimpleMap map) {
		postgradedao.UpdatePostGrade(map);
	}

	public void deletePostGrade(String groupid) {
		postgradedao.deletePostGrade(groupid);
	}

	public List getPostLevelList() {
		List list = postleveldao.getPostLevelList();
		return list;
	}

	public void insertPostLevel(SimpleMap map) {
		postleveldao.insertPostLevel(map);
	}

	public void updatePostLevel(SimpleMap map) {
		postleveldao.updatePostLevel(map);
	}

	public void deletePostLevel(String levelid) {
		postleveldao.deletePostLevel(levelid);
	}

	public boolean isPostLevelIdExsit(String postlevelid) {
		return postleveldao.isPostLevelIdExsit(postlevelid);
	}

	public Object getPostLevelById(String postlevelid) {
		return postleveldao.getPostLevelById(postlevelid);
	}

	public Object getMenuEnt(Object parameterObject) throws GlRuntimeException {
		return menuDAO.getMenuEnt(parameterObject);
	}
	
	/**
	 * retrieve data for view ess leave param
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List getEssLeaveParamList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return essleavedao.getEssLeaveParamList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve data for view ess overtime param
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List getEssOvertimeParamList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return essOvertimeDAO.getEssOvertimeParamList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve data for update ess leave param
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object getEssLeaveParam(Object parameterObject) throws GlRuntimeException {
		return essleavedao.getEssLeaveParam(parameterObject);
	}
	
	/**
	 * retrieve data for update ess overtime param
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object getEssOvertimeParam(Object parameterObject) throws GlRuntimeException {
		return essOvertimeDAO.getEssOvertimeParam(parameterObject);
	}

	/**
	 * retrieve data for view ess leave param count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int getEssLeaveParamListCnt(Object parameterObject) throws GlRuntimeException {
		return essleavedao.getEssLeaveParamListCnt(parameterObject);
	}
	
	/**
	 * retrieve data for view ess overtime param count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int getEssOvertimeParamListCnt(Object parameterObject) throws GlRuntimeException {
		return essOvertimeDAO.getEssOvertimeParamListCnt(parameterObject);
	}
	
	/**
	 * validate Insert Ess Leave Param data
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validateInsertEssLeaveParam(Object parameterObject) throws GlRuntimeException {
		return essleavedao.validateInsertEssLeaveParam(parameterObject);
	}
	
	/**
	 * validate Insert Ess Overtime Param data
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validateInsertEssOvertimeParam(Object parameterObject) throws GlRuntimeException {
		return essOvertimeDAO.validateInsertEssOvertimeParam(parameterObject);
	}
	/**
	 * validate Insert Ess Leave Param data
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public List EssLeaveParam(Object parameterObject) throws GlRuntimeException {
		return essleavedao.EssLeaveParam(parameterObject);
	}
	
	/**
	 * insert Ess Leave Param
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEssLeaveParam(Object parameterObject) throws GlRuntimeException {
		essleavedao.insertEssLeaveParam(parameterObject);
	}
	
	/**
	 * insert Ess Overtime Param
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEssOvertimeParam(Object parameterObject) throws GlRuntimeException {
		essOvertimeDAO.insertEssOvertimeParam(parameterObject);
	}
	
	/**
	 * delete Ess Leave Param
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEssLeaveParam(Object parameterObject) throws GlRuntimeException {
		essleavedao.deleteEssLeaveParam(parameterObject);
	}
	
	/**
	 * delete Ess Overtime Param
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEssOvertimeParam(Object parameterObject) throws GlRuntimeException {
		essOvertimeDAO.deleteEssOvertimeParam(parameterObject);
	}
	
	/**
	 * validate update Ess Leave Param data
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validateUpdateEssLeaveParam(Object parameterObject) throws GlRuntimeException {
		return essleavedao.validateUpdateEssLeaveParam(parameterObject);
	}
	
	/**
	 * validate update Ess Overtime Param data
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validateUpdateEssOvertimeParam(Object parameterObject) throws GlRuntimeException {
		return essOvertimeDAO.validateUpdateEssOvertimeParam(parameterObject);
	}
	
	/**
	 * update Ess Leave Param
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEssLeaveParam(Object parameterObject) throws GlRuntimeException {
		essleavedao.updateEssLeaveParam(parameterObject);
	}
	
	/**
	 * update Ess Overtime Param
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEssOvertimeParam(Object parameterObject) throws GlRuntimeException {
		essOvertimeDAO.updateEssOvertimeParam(parameterObject);
	}

	/**
	 * 取指定接口用户的所有表名
	 * @author Pennix
	 * @param ifUser 如果为空,取默认值INTERFACE_USERNAME = "IF_EHR"
	 * @return
	 */
	public List<String> getIfTables(String ifUser) {
		return interfacedao.getIfTables(ifUser);
	}

	/**
	 * 取指定接口表的所有列
	 * @author Pennix
	 * @param tableName
	 * @return
	 */
	public List<String[]> getIfColumns(String ifUser, String tableName) {
		return interfacedao.getIfColumns(ifUser, tableName);
	}

	/**
	 * 保存接口表映射
	 * @author Pennix
	 * @param emTable
	 * @param ifTable
	 * @param set
	 * @return
	 */
	public int saveMapping(String emTable, int orderNo, String ifTable, Set<String[]> set) {
		return interfacedao.saveMapping(emTable, orderNo, ifTable, set);
	}

	/**
	 * 取得当前用户的所有表名
	 * @author Pennix
	 * @return
	 */
	public List<String> getEmTables() {
		return interfacedao.getEmTables();
	}

	/**
	 * 取得指定表的所有列
	 * @author Pennix
	 * @param tableName
	 * @return
	 */
	public List<String[]> getEmColumns(String emTable, String ifTable) {
		return interfacedao.getEmColumns(emTable, ifTable);
	}

	/**
	 * 取得指定人事表的更新顺序号
	 * @author Pennix
	 * @param emTable
	 * @return
	 */
	public int getEmOrder(String emTable) {
		return interfacedao.getEmOrder(emTable);
	}
	
	public List retrieveCanBeBuildMenu() {
		List list = null;
		try {
			list = menuDAO.retrieveCanBeBuildMenu(null);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List retrieveTableByMenu(Object parameterObject) {
		List list = null;
		try {
			list = menuDAO.retrieveTableByMenu(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List retrieveReportItemListByTableName(Object parameterObject) {
		List list = null;
		try {
			list = menuDAO.retrieveReportItemListByTableName(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List retrieveReportItemListNotInRtNo(Object parameterObject) {
		List list = null;
		try {
			list = menuDAO.retrieveReportItemListNotInRtNo(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public void insertReportTable(List<ReportTable> parameterList, List<ReportTable> delList, List<ReportTable> updateList,String cpnyId) {
		try {
			menuDAO.insertReportTable(parameterList, delList, updateList,cpnyId);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}
	
	public void insertReportItem(List<ReportItem> parameterList) {
		try {
			menuDAO.insertReportItem(parameterList);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}
	
	public void deleteReportItem(Object parameterObject) {
		try {
			menuDAO.deleteReportItem(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}
	
	public void updateReportItem(List<ReportItem> parameterList) {
		try {
			menuDAO.updateReportItem(parameterList);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}


	public Integer retrieveReportTableNextPK() {
		Integer result = null;
		try {
			result = menuDAO.retrieveReportTableNextPK();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/**
	 * retrieve employee division
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getEmpDiff(Object parameterObject) throws GlRuntimeException {
		
		return menuDAO.getEmpDiff(parameterObject);
		
	}
	
	/**
	 * grant validate
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getGrantValidate(Object parameterObject) throws GlRuntimeException {
		
		return menuDAO.getGrantValidate(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getHelpInfo(Object parameterObject) throws GlRuntimeException {
		
		return menuDAO.getHelpInfo(parameterObject);
	}
	
	
}