/*
 * @(#)InsertBaseInfoCmd.java 1.0 2006-12-19 下午04:49:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.baseinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Education;
import com.ait.hrm.bean.ForeignLanguage;
import com.ait.hrm.bean.ITLevel;
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
public class InsertBaseInfoCmd extends HrmAbstractCommand {

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
		List educationList = new ArrayList();
		List languageList = new ArrayList();
		List ITLevelList = new ArrayList();
		List militaryList = new ArrayList();

		// bind education form data to list object
		ObjectBindUtil.setFormBean(request, new Education(), educationList, 3,
				"_");
		// filtrate data
		this.filtrateData(educationList, request.getParameter("educationSign")
				.split(","));

		// bind language form data to list object
		ObjectBindUtil.setFormBean(request, new ForeignLanguage(),
				languageList, 3, "_");
		// filtrate data
		this.filtrateData(languageList, request.getParameter("languageSign")
				.split(","));

		// bind IT level form data to list object
		ObjectBindUtil
				.setFormBean(request, new ITLevel(), ITLevelList, 3, "_@");
		// filtrate data
		this.filtrateData(ITLevelList, request.getParameter("ITLeveSign")
				.split(","));

		// add parameter list to Map
		map.put("education", educationList);
		map.put("language", languageList);
		map.put("ITLevel", ITLevelList);

		// insert basic information
		services.insertBasicInfo(map);

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.save_successfully");
		
		request.setAttribute("alert", msg);
		request
				.setAttribute("url",
						"/hrmControlServlet?operation=viewBaseInfo&menu_code="
								+ request.getParameter("menu_code") + "&empID="
								+ empID);

		return "/inc/alertMessage.jsp";
	}

	/**
	 * filtrate bean object
	 * 
	 * @param list
	 * @param row
	 */
	private void filtrateData(List list, String[] row) {

		// remove bean object by row number array
		for (int i = row.length; i > 0; i--) {

			if (row[i - 1].equals(""))
				return;

			list.remove(Integer.parseInt(row[i - 1]) - 1);
		}
	}

}
