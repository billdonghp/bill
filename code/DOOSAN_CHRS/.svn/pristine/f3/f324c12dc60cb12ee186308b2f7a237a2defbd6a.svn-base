/*
 * @(#)InsertRelationCmd.java 1.0 2006-12-19 下午05:03:56
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.relation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Family;
import com.ait.hrm.bean.MilitaryService;
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
public class InsertRelationCmd extends HrmAbstractCommand {

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
		List familyList = new ArrayList();
		List militaryList = new ArrayList();
		MilitaryService militaryService = new MilitaryService();

		// bind family form data to list object
		ObjectBindUtil.setFormBean(request, new Family(), familyList, 3, "_");
		// filtrate data
		this.filtrateData(familyList, request.getParameter("familySign").split(
				","));

		// bind military service form data to list object
		if (request.getParameter("militarySign").equals("")) {

			ObjectBindUtil.setFormBean(request, militaryService);
			militaryList.add(militaryService);
			this.filtrateData(militaryList, request
					.getParameter("militarySign").split(","));
			map.put("military", militaryList);
		}

		// add parameter list to Map
		map.put("family", familyList);

		// insert relation information
		services.insertRelationInfo(map);

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.save_successfully");
		
		request.setAttribute("alert", msg);
		request
				.setAttribute("url",
						"/hrmControlServlet?operation=viewRelation&menu_code="
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
