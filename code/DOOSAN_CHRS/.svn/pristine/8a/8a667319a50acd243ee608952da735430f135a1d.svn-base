package com.ait.gm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.business.SealManagementServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-28
 * 
 */
public class SealManagementCommand implements Command {

	private SealManagementServices smServices = null;

	public SealManagementCommand() {

		smServices = new SealManagementServices();

	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("") && content.equals("sealConfirm")) {
			this.getAllWaitConfirmList(request, admin);
			returnPage = "/gm_seal_confirm.jsp";
		} else if (!content.equals("") && content.equals("oingConfirm")) {
			returnPage = this.oingConfirm(request, admin);
		} else if (!content.equals("") && content.equals("sealConfirmExce")) {
			returnPage = this.sealConfirmExce(request, admin);
		} else {
			return "error.jsp";
		}
		return returnPage;
	}

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

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"ArAbstractCommand put toolbar information to request Exception.",
					e);
		}
	}

	/* 得到公章所以待确认的信息 */
	public void getAllWaitConfirmList(HttpServletRequest request,
			AdminBean admin) {
		this.putToolbarInfo(request);
		try {

			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String qryType = request.getParameter("qryType") !=null?request.getParameter("qryType"):"0";
			String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);
			
			// 取得数据行数
			int resultCount = smServices
					.getAllWaitConfirmListNumber(parameterObject);
			List applyList = smServices.getAllWaitConfirmList(parameterObject,
					currentPage, pageSize);
			request
					.setAttribute("serchDept", request
							.getParameter("serchDept"));
			request.setAttribute("documentno", request
					.getParameter("documentno"));
			request.setAttribute("EndDate", request.getParameter("EndDate"));
			request
					.setAttribute("StartDate", request
							.getParameter("StartDate"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("cpnyId", cpnyId);
			
			
			
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getAllWaitConfirmList happens Exception. ", e);
		}

	}

	public String oingConfirm(HttpServletRequest request, AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		try {

			String[] modifyFlag = request.getParameterValues("affirmno");
			String dd = request.getParameter("affirmorIdea_GZ09072901");
			String ss = parameterObject.getString("affirmorIdea_GZ09072901");
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			for (int i = 0; i < modifyFlag.length; i++) {
				String affirmorIdea = parameterObject.getString("affirmorIdea_"
						+ modifyFlag[i]);
				parameterObject.set("affirmorIdea", affirmorIdea);
				parameterObject.set("applyno", modifyFlag[i]);
				smServices.oingConfirm(parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		this.getAllWaitConfirmList(request, admin);
		return "/gm_seal_confirm.jsp?menu_code="
				+ parameterObject.getString("menu_code");

	}

	public String sealConfirmExce(HttpServletRequest request, AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		try {
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			List applyList = smServices.getAllWaitConfirmList(parameterObject);
			request.setAttribute("applyList", applyList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		//this.getAllWaitConfirmList(request, admin);
		return "/gm_seal_confirm_Exce.jsp";

	}

}
