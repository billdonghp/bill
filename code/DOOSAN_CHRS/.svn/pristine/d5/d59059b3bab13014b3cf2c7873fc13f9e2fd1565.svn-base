/*
 * @(#)DeleteAppendInfoCmd.java 1.0 2007-1-2 下午03:50:40
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.appendinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Additional;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-2 下午03:50:40
 * @version 1.0
 * 
 */
public class DeleteAppendInfoCmd extends HrmAbstractCommand {

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

		List appendList = new ArrayList();

		// bind health form data to list object
		ObjectBindUtil.setFormBean(request, new Additional(), appendList,
				Integer.parseInt(request.getParameter("appendInfoCnt")), "_");

		// filtrate data
		this.filtrateData(appendList, request, "append");

		// delete append information
		services.deleteAdditionalInfo(appendList, true);   

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.delete_successfully");
		
		request.setAttribute("alert", msg);
		request.setAttribute("url",
				"/hrmControlServlet?operation=viewAppendInfo&menu_code="
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
