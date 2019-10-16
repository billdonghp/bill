/*
 * @(#)HrmAbstractCommand.java 1.0 2006-12-19 下午08:11:30
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-19 下午08:11:30
 * @version 1.0
 * 
 */
public abstract class HrmAbstractCommand implements Command {

	// child class implements the abstract methode

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	/**
	 * put employee basic information to request
	 * 
	 * @param request
	 * @param response
	 * @throws GlRuntimeException
	 */
	public void putBasicInfo(HttpServletRequest request)
			throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			
			String empID = request.getParameter("empID") != null ? request
					.getParameter("empID") : adminId;
					
			BasicInfo basic = (BasicInfo) services.retrieveBasicInfo(empID);

			request.setAttribute("basic", basic);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put basic information to request Exception.",
					e);
		}
	}

	/**
	 * put department tree to request
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	public void putDeptTree(HttpServletRequest request)
			throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();
			SimpleMap parameterObject = new SimpleMap();
			List list = services.retrieveDeptTree(parameterObject);
			request.setAttribute("dept_list", list);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put department tree to request Exception.",
					e);
		}
	}

	/**
	 * put department tree to request
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	public void putDeptTreeByAdmin(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("ADMIN_ID", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			HrmServices services = HrmServices.getInstance();

			List list = services.retrieveDeptTree(parameterObject);

			request.setAttribute("dept_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put department tree to request Exception.",
					e);
		}
	}

	/**
	 * put post tree to request
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	public void putPost(HttpServletRequest request) throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			List list = services.retrievePostList();

			request.setAttribute("post_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post tree to request Exception.", e);
		}
	}
	
	public void putPostGrade(HttpServletRequest request) throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			List list = services.retrievePostGradeList();

			request.setAttribute("post_grade_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post grade to request Exception.", e);
		}
	}
	
	
	
	public void putGradeLevel(HttpServletRequest request) throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			List list = services.retrieveGradeLevelList();

			request.setAttribute("post_grade_level_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post grade level to request Exception.", e);
		}
	}

	/**
	 * get employee list by deptid,(empid or chinesename ) ,adminid, postCode
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	@SuppressWarnings("unchecked")
	public void putEmpListByAdmin(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap sMap = new SimpleMap();
			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String cpnyId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getCpnyId();

			sMap = ObjectBindUtil.getData(request);
			sMap.put("ADMINID", adminId);
			sMap.put("cpnyId", cpnyId);
			
			HrmServices services = HrmServices.getInstance();

			List list = services.retrieveEmpList(sMap);

			request.setAttribute("emp_list", list);
			request.setAttribute("empSize", list.size());
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put employee list to request Exception.", e);
		}
	}
	
	/**
	 * get pa employee list by deptid,(empid or chinesename ) , postCode
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	@SuppressWarnings("unchecked")
	public void putPaEmpListByAdmin(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap sMap = new SimpleMap();
			AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");

			sMap = ObjectBindUtil.getData(request);
			sMap.put("ADMINID", admin.getAdminID());
			sMap.put("cpnyId", admin.getCpnyId());
			
			HrmServices services = HrmServices.getInstance();

			List list = services.retrievePaEmpList(sMap);

			request.setAttribute("emp_list", list);
			request.setAttribute("empSize", list.size());
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put employee list to request Exception.", e);
		}
	}
	
	/**
	 * get employee list by deptid,(empid or chinesename ) ,adminid, postCode
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	@SuppressWarnings("unchecked")
 public void putEmpListByAdminIncumbency(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap sMap = new SimpleMap();
			AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");

			sMap = ObjectBindUtil.getData(request);
			sMap.put("ADMINID", admin.getAdminID());
			sMap.put("cpnyId", admin.getCpnyId());
			
			HrmServices services = HrmServices.getInstance();
  
			List list = services.retrieveEmpListIncumbency(sMap);                  

			request.setAttribute("emp_list", list);
			
			request.setAttribute("empSize", list.size());
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put employee list to request Exception.", e);
		}
	}
	
	public List getEmpListByAdminIncumbency(Object obj,int currentPage,int pageSize)
	throws GlRuntimeException {
		List list;
try {
	 HrmServices services = HrmServices.getInstance();
	 list = services.retrieveEmpListIncumbency(obj,currentPage,pageSize);                  
    return list;
} catch (Exception e) {

	Logger.getLogger(getClass()).error(e.toString());
	throw new GlRuntimeException(
			"HrmAbstractCommand put employee list to request Exception.", e);
}
}
	
	/**
	 * get employee list by deptid,(empid or chinesename ),postCode
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	@SuppressWarnings("unchecked")
	public void putEmpList(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap sMap = new SimpleMap();

			sMap = ObjectBindUtil.getData(request);

			HrmServices services = HrmServices.getInstance();

			List list = services.retrieveEmpList(sMap);

			request.setAttribute("emp_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put employee list to request Exception.", e);
		}
	}

	/**
	 * put toolbar information to request
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
	public void putToolbarInfo(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap map = new SimpleMap();
  
			// get paramter from request object         
			List toolMenuList = null;
			List menuentList = null;
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();          
			String menu_code = request.getParameter("menu_code");
			String operation = request.getParameter("operation");
			AdminBean admin = (AdminBean) (request.getSession()
					.getAttribute("admin"));
			String rodeLevel = admin.getScreenGrantNo() != null ? admin
					.getScreenGrantNo() : "";

			// get process popedom list
			toolMenuList = menubiz.toolMenuList(menu_code, rodeLevel);
			menuentList = menubiz.thirdmenulist(menu_code, rodeLevel);

			int selectMenu = 0;
			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < toolMenuList.size(); i++) {

				toolmenu = (ToolMenu) toolMenuList.get(i);

				if (toolmenu.getSelect() == 1) {

					selectMenu = 1;
				}
				if (toolmenu.getInsertr() == 1) {

					insertMenu = 1;
				}
				if (toolmenu.getUpdate() == 1) {

					updateMenu = 1;
				}
				if (toolmenu.getDelect() == 1) {

					deleteMenu = 1;
				}
			}
			// save insert,update,delete popedom
			map.setInt("selectMenu", selectMenu);
			map.setInt("insertMenu", insertMenu);
			map.setInt("updateMenu", updateMenu);
			map.setInt("deleteMenu", deleteMenu);
			// save MenuEnt object list
			map.set("menuentList", menuentList);
			// save menu code
			map.setString("menu_code", menu_code);
			map.setString("operation", operation);

			request.setAttribute("toolbarInfo", map);
			request.setAttribute("menu_code", menu_code);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put toolbar information to request Exception.",
					e);
		}
	}

}
