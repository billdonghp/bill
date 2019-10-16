/*
 * @(#)DeleteRelationCmd.java 1.0 2006-12-28 下午06:22:36
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

public class DeleteRelationCmd extends HrmAbstractCommand {

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
		MilitaryService militaryService = new MilitaryService();

		// bind family form data to list object
		ObjectBindUtil.setFormBean(request, new Family(), familyList, Integer
				.parseInt(request.getParameter("familyInfoCnt")), "_");

		// filtrate data
		this.filtrateData(familyList, request, "family");

		// bind military service form data to bean object
		if (request.getParameter("military") != null
				&& !request.getParameter("military").equals("")) {

			ObjectBindUtil.setFormBean(request, militaryService);
			map.put("military", militaryService);
		}

		// add parameter list to Map
		map.put("family", familyList);

		// delete relation information
		services.deleteRelationInfo(map);

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.delete_successfully");
		
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
