/*
 * @(#)UpdateTrainingCmd.java 1.0 2006-12-19 下午05:16:18
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
public class UpdateTrainingCmd extends HrmAbstractCommand {

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
				Integer.parseInt(request.getParameter("trainingInsideCnt")),
				"_");

		// bind training outside form data to list object
		ObjectBindUtil.setFormBean(request, new TrainingOutside(), outsideList,
				Integer.parseInt(request.getParameter("trainingOutsideCnt")),
				"_@");

		// bind studying form data to list object
//		ObjectBindUtil.setFormBean(request, new Studying(), studyingList,
//				Integer.parseInt(request.getParameter("studyListCnt"))
//						+ Integer.parseInt(request
//								.getParameter("study2ListCnt")), "_%");

		map.put("inside", insideList);
		map.put("outside", outsideList);
		//map.put("studying", studyingList);

		// update training information
		services.updateTrainingInfo(map);

		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.modify_successfully");
		
		request.setAttribute("alert", msg);  
		request
				.setAttribute("url",
						"/hrmControlServlet?operation=viewTraining&menu_code="
								+ request.getParameter("menu_code") + "&empID="
								+ empID);

		return "/inc/alertMessage.jsp";
	}

}
