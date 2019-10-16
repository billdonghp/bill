package com.ait.ar.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.dao.implementation.SupervisorDAOImpl;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DataAccessException;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class ArDeleteCommand implements Command {
	private HrmServices hrmServices;

	private ArServices arServices;

	private String content;

	public ArDeleteCommand() {

		arServices = new ArServices();

		hrmServices = HrmServices.getInstance();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String returnPage = null;

		content = StringUtil.checkNull(request.getParameter("content"));// 从request中得到想要查看的内容
		if (content.equals("groupCondition")) {
			returnPage = deleteGroupCondition(request);
		} else if (content.equals("dynamicGroup")) {
			returnPage = deleteDynamicGroup(request);
		} else if (content.equals("ar_supervisor")) {
			returnPage = deleteSupervisor(request, response);
		} else if (content.equals("empCalendar")) {
			returnPage = deleteEmpCalendar(request);
		} else if (content.equals("detail")) {
			returnPage = this.deleteDetail(request);
		} else {
			returnPage = "/error.jsp";
		}

		Logger.getLogger(getClass()).debug("return page is : " + returnPage);
		return returnPage;
	}

	private String deleteDetail(HttpServletRequest request) {
		
		String sDate = StringUtil.checkNull(request.getParameter("sDate"));
		String eDate = StringUtil.checkNull(request.getParameter("eDate"));
		String key = (request.getParameter("key"));
		String deptid = StringUtil.checkNull(request.getParameter("deptid"));
		String currentPage = StringUtil.checkNull(request.getParameter("currentPage"));
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		String itemNo = StringUtil.checkNull(request.getParameter("itemNo"));
		String sLength = StringUtil.checkNull(request.getParameter("sLength"));
		String eLength = StringUtil.checkNull(request.getParameter("eLength"));
		
		try {
			if (sDate.equals("")) {
				sDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
				eDate = sDate;
			}
			
			List errorList = arServices.delDetail(request,admin);
			request.setAttribute("errorList", errorList);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
		return "arControlServlet?currentPage=" + currentPage
				+ "&operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&sDate="
				+ sDate + "&eDate=" + eDate + "&key=" + key + "&deptid=" + deptid
				+ "&itemNo" + itemNo + "&sLength" + sLength + "&eLength" + eLength ;
	}

	private String deleteEmpCalendar(HttpServletRequest request) {
		try {
			arServices.delEmpCalendar(request);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		}
		return "arControlServlet?operation=ar_pagecontrol&page=ec_v&menu_code=ar0101";
	}

	/**
	 * deleteGroupCondition
	 * 
	 * @return String
	 * @param request
	 *            HttpServletRequest
	 */
	private String deleteGroupCondition(HttpServletRequest request) {
		String groupConditionNo = StringUtil.checkNull(request
				.getParameter("groupConditionNo"), "-1");
		String groupNo = StringUtil.checkNull(request.getParameter("groupNo"),
				"-1");
		AdminBean admin = (AdminBean) request.getSession()
		.getAttribute("admin");
		try {
			arServices.deleteGroupCondition(Integer.parseInt(groupConditionNo));
			List groupConditionList = arServices.getConditionList(Integer
					.parseInt(groupNo));
			request.setAttribute("groupConditionList", groupConditionList);
			List dynamicGroupList = arServices.getGroupList(admin.getCpnyId());
			request.setAttribute("dynamicGroupList", dynamicGroupList);
			List conditionList = arServices.getConditionList();
			request.setAttribute("conditionList", conditionList);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		return "/ar_set_dynamicgroup.jsp";
	}

	/**
	 * deleteSupervisor
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 */
	public String deleteSupervisor(HttpServletRequest request,
			HttpServletResponse response) {
		arServices = new ArServices();
		String str = request.getParameter("str");
		String[] s = str.split("&&");
		String id = null;
		SupervisorDAOImpl dao = new SupervisorDAOImpl();
		for (int i = 0; i < s.length; i++) {
			id = s[i];
			try {
				dao.deleteSupervisor(id);
				arServices.deleteSupervisorScreenGrantNo(id) ; 
			} catch (DataAccessException ex) {
				Logger.getLogger(getClass()).error(ex.toString());
			}
		}

		ArPageControlCommand arpage= new ArPageControlCommand();
		return arpage.supervisor(request);
	}

	/**
	 * deleteGroupCondition
	 * 
	 * @return String
	 * @param request
	 *            HttpServletRequest
	 */
	private String deleteDynamicGroup(HttpServletRequest request) {
		String groupNo = StringUtil.checkNull(request.getParameter("groupNo"),
				"-1");
		Logger.getLogger(getClass())
				.debug("ardeleteCommand:groupNo " + groupNo);
		AdminBean admin = (AdminBean) request.getSession()
		.getAttribute("admin");
		try {
			arServices.deleteGroup(Integer.parseInt(groupNo));
			List dynamicGroupList = arServices.getGroupList(admin.getCpnyId());
			request.setAttribute("dynamicGroupList", dynamicGroupList);
			List conditionList = arServices.getConditionList();
			request.setAttribute("conditionList", conditionList);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		return "/ar_set_dynamicgroup.jsp";
	}
}
