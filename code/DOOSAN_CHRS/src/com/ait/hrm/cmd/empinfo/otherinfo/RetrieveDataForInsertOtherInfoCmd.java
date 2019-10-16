/*
 * @(#)RetrieveDataforInsertOtherInfoCmd.java 1.0 2006-12-19 下午04:58:13
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.otherinfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-19 下午05:16:53
 * @version 1.0
 * 
 */
public class RetrieveDataForInsertOtherInfoCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.putBasicInfo(request);
		this.putToolbarInfo(request);

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		// retrieve other information
		List experienceInfoList = services.retrieveExperienceInfo(empID);
		List qualificationInfoList = services.retrieveQualificationInfo(empID);

		request.setAttribute("experienceInfoList", experienceInfoList);
		request.setAttribute("experienceInfoCnt", experienceInfoList.size());
		request.setAttribute("qualificationInfoList", qualificationInfoList);
		request.setAttribute("qualificationInfoCnt", qualificationInfoList
				.size());
		request.setAttribute("empID", empID);
		request.setAttribute("adminId", adminId);
		
		return "/hrm/hrm_add_otherinfo.jsp";
	}

}

