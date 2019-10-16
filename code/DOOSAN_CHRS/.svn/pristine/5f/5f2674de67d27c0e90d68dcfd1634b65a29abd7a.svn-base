/*
 * @(#)RetrieveDataForDeleteRelationCmd.java 1.0 2006-12-28 下午06:21:36
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.relation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.MilitaryService;
import com.ait.hrm.bean.PersonalInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sy.bean.AdminBean;

public class RetrieveDataForDeleteRelationCmd extends HrmAbstractCommand {

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

		// retrieve family information
		List familyInfoList = services.retrieveFamilyInfo(empID);

		// retrieve personal information
		PersonalInfo personalInfo = (PersonalInfo) services
				.retrievePersonalInfo(empID);

		// retrieve military service information
		MilitaryService militaryServiceInfo = (MilitaryService) services
				.retrieveMilitaryServiceInfo(empID);

		request.setAttribute("familyInfoList", familyInfoList);
		request.setAttribute("familyInfoCnt", familyInfoList.size());
		request.setAttribute("personalInfo", personalInfo);
		request.setAttribute("militaryServiceInfo", militaryServiceInfo);
		request.setAttribute("empID", empID);
		request.setAttribute("adminId", adminId);

		return "/hrm/hrm_delete_relation.jsp";
	}
}
