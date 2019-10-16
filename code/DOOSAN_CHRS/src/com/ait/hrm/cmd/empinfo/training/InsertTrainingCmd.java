/*
 * @(#)InsertTrainingCmd.java 1.0 2006-12-19 下午05:15:06
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Studying;
import com.ait.hrm.bean.TrainingInside;
import com.ait.hrm.bean.TrainingOutside;
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
public class InsertTrainingCmd extends HrmAbstractCommand {

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
		List insideList = new ArrayList();
		List outsideList = new ArrayList();
		List studyingList = new ArrayList();

		// bind training inside form data to list object
		ObjectBindUtil.setFormBean(request, new TrainingInside(), insideList,
				3, "_");
		// filtrate data
		this.filtrateData(insideList, request.getParameter("insideSign").split(
				","));

		// bind training outside form data to list object
		ObjectBindUtil.setFormBean(request, new TrainingOutside(), outsideList,
				3, "_@");
		// filtrate data
		this.filtrateData(outsideList, request.getParameter("outsideSign")
				.split(","));
		
		// bind studying form data to list object
		ObjectBindUtil.setFormBean(request, new Studying(), studyingList,
				6, "_%");
		// filtrate data
		this.filtrateData(studyingList, request.getParameter("studyingSign")
				.split(","));

		map.put("inside", insideList);
		map.put("outside", outsideList);
		map.put("studying", null);

		// insert training information
		services.insertTrainingInfo(map);
		
		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.save_successfully");
		
		request.setAttribute("alert", msg);
		request.setAttribute("url",
				"/hrmControlServlet?operation=viewTraining&menu_code="
						+ request.getParameter("menu_code")+"&empID="+empID);

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
