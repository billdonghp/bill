/*
 * @(#)DeleteHealthCmd.java 1.0 2006-12-29 下午04:19:18
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.health;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Health;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;

public class DeleteHealthCmd extends HrmAbstractCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		List healthList = new ArrayList();

		// bind health form data to list object
		ObjectBindUtil.setFormBean(request, new Health(), healthList, Integer
				.parseInt(request.getParameter("healthInfoCnt")), "_");

		// filtrate data
		this.filtrateData(healthList, request, "health");

		// insert basic information
		services.deleteHealthInfo(healthList, true);

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.delete_successfully");
		
		request.setAttribute("alert", msg);  
		request.setAttribute("url",
				"/hrmControlServlet?operation=viewHealth&menu_code="
						+ request.getParameter("menu_code")+"&empID="+empID);

		return "/inc/alertMessage.jsp";
	}

	/**
	 * filtrate bean object
	 * 
	 * @param list
	 * @param request
	 * @param type
	 */
	private void filtrateData(List list, HttpServletRequest request, String type) {

		for (int i = list.size(); i > 0; i--) {

			if (request.getParameter(type + "_" + i) == null
					|| request.getParameter(type + "_" + i).equals("")) {

				list.remove(i - 1);
			}

		}

	}
}
