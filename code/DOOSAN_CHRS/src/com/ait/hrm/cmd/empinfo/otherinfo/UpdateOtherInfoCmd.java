/*
 * @(#)UpdateOtherInfoCmd.java 1.0 2006-12-19 下午04:59:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.otherinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Experience;
import com.ait.hrm.bean.Qualification;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-19 下午05:16:53
 * @version 1.0
 * 
 */
public class UpdateOtherInfoCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		// declare bind object
		Map map = new SimpleMap();
		List experienceList = new ArrayList();
		List qualificationList = new ArrayList();

		// bind experience form data to list object
		ObjectBindUtil.setFormBean(request, new Experience(), experienceList,
				Integer.parseInt(request.getParameter("experienceInfoCnt")),
				"_@");

		// bind qualification form data to list object
		ObjectBindUtil.setFormBean(request, new Qualification(),
				qualificationList, Integer.parseInt(request
						.getParameter("qualificationInfoCnt")), "_");

		map.put("experience", experienceList);
		map.put("qualification", qualificationList);

		// insert other information
		services.updateOtherInfo(map);

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.modify_successfully");
		
		request.setAttribute("alert", msg);  
		request.setAttribute("url",
				"/hrmControlServlet?operation=viewOtherInfo&menu_code="
						+ request.getParameter("menu_code")+"&empID="+empID);

		return "/inc/alertMessage.jsp";
	}

}
