package com.ait.ar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.bean.DynamicGroup;
import com.ait.ar.bean.Supervisor;
import com.ait.ar.business.ArServices;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.OperationStatus;
import com.ait.web.Command;

public class ArViewCommand implements Command {
	private ArServices arServices;

	private HrmServices hrmServices;

	private String content = null;

	public ArViewCommand() {
		arServices = new ArServices();
		hrmServices = HrmServices.getInstance();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
	     HttpSession session = request.getSession(true);
	   AdminBean admin = (AdminBean) session.getAttribute("admin"); 
		String returnPage = null;
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("groupCondition")) {
				returnPage = viewGroupConditions(request);
			} else if (content.equals("supervisorInfo")) {
				returnPage = viewSupervisorInfo(request,admin);
			}
		} else {
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	private String viewGroupConditions(HttpServletRequest request) {
		String returnPage = null;
		AdminBean admin = (AdminBean) request.getSession()
		.getAttribute("admin");
		String groupNo = request.getParameter("groupNo");
		if (groupNo == null) {
			groupNo = "-1";
			returnPage = "/error.jsp";
		} else {
			try {
				List groupConditionList = arServices.getConditionList(Integer
						.parseInt(groupNo));
				request.setAttribute("groupConditionList", groupConditionList);
				List dynamicGroupList = arServices.getGroupList(admin.getCpnyId());
				request.setAttribute("dynamicGroupList", dynamicGroupList);
				List conditionList = arServices.getConditionList();
				request.setAttribute("conditionList", conditionList);
				DynamicGroup group = arServices.getGroupByNo(Integer
						.parseInt(groupNo));
				request.setAttribute("selectGroup", group);
				OperationStatus operationStatus = new OperationStatus();
				request.setAttribute("operationStatus", operationStatus);// 传给界面，界面接受后，判断执行情况
				returnPage = "/ar_set_dynamicgroup.jsp?groupNo=" + groupNo;
			} catch (Exception ex) {
				Logger.getLogger(getClass()).error(ex.toString());
				returnPage = "/error.jsp";
			}
		}
		Logger.getLogger(getClass()).debug("return page : " + returnPage);
		return returnPage;
	}

	/**
	 * viewSupervisorInfo
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String viewSupervisorInfo(HttpServletRequest request,AdminBean admin) {
		String supervisorId = request.getParameter("superVisorID");
		String returnPage = null;
		SimpleMap sm= new SimpleMap();
		sm.set("cpnyId", admin.getCpnyId());
		if (supervisorId != null) {
			try {
				request.setAttribute("dept_list", hrmServices.retrieveDeptTree(sm));
				List supervisorInfoList = arServices.getSupervisorInfoList(supervisorId);
				request.setAttribute("supervisorInfoList", supervisorInfoList);
				Supervisor supervisor = arServices.getSupervisorById(supervisorId);
				request.setAttribute("selectSupervisor", supervisor);
				List deptListWithObject = arServices.getDeptListWithObject(supervisorId);
				request.setAttribute("deptListWithObject", deptListWithObject);
				
				request.setAttribute("supervisor_item", arServices.getSupervisorItems(supervisorId));
				request.setAttribute("item_group", hrmServices.retrieveItemGroup(sm));
				request.setAttribute("item_group_cut", hrmServices.retrieveItemGroupCut(sm));
				
				returnPage = "/ar_maintenance_supervisor_v.jsp";
			} catch (Exception ex) {
				Logger.getLogger(getClass()).error(ex.toString());
				returnPage = "/error.jsp";
			}
		} else {
			Logger.getLogger(getClass()).error(
					"get superVisorID fail! return error page");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return page : " + returnPage);
		return returnPage;
	}
}
