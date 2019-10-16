package com.ait.ar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class ArSearchCommand implements Command {

	private HrmServices hrmServices;

	public ArSearchCommand() {

		hrmServices = HrmServices.getInstance();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		HttpSession session = request.getSession(true);
 		AdminBean admin = (AdminBean) session.getAttribute("admin");  
		if (StringUtil.checkNull(request.getParameter("content")).equals(
				"supervisor")) {
			returnPage = getSupervisor(request,admin);
		} else {
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return page : " + returnPage);
		return returnPage;
	}

	/**
	 * getSupervisor
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String getSupervisor(HttpServletRequest request,AdminBean admin) {

		String deptID = request.getParameter("deptid") != null ? request
				.getParameter("deptid") : "";
		String searchcontent = request.getParameter("searchcontent") != null ? request
				.getParameter("searchcontent")
				: "";
		searchcontent = StringUtil.toCN(searchcontent);

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("CONDITION", searchcontent);
		parameterObject.setString("DEPTID", deptID);
		parameterObject.set("cpnyId", admin.getCpnyId());
		SimpleMap sm= new SimpleMap();
		sm.set("cpnyId", admin.getCpnyId());

		try {
			List emp_list = hrmServices.retrieveSupervisorEmpList(parameterObject);
			request.setAttribute("emp_list", emp_list);
			request.setAttribute("dept_list", hrmServices
					.retrieveDeptTree(sm));
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_maintenance_supervisor_a.jsp";
	}

}
