/*
 * @(#)RetrieveDataForDeleteBaseInfoCmd.java 1.0 2006-12-27 下午01:52:40
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.baseinfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.PersonalInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDataForDeleteBaseInfoCmd extends HrmAbstractCommand {

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

		// retrieve contact information
		List contractInfoList = services.retrieveContractInfo(empID);
		// retrieve personal information
		PersonalInfo personalInfo = (PersonalInfo) services
				.retrievePersonalInfo(empID);
		// retrieve language information
		List lanuageInfoList = services.retrieveLanguageInfo(empID);
		// retrieve education information
		List educationInfoList = services.retrieveEducationInfo(empID);
		// retrieve IT level information
		List ITLevelInfoList = services.retrieveITLevelInfo(empID);

		// 研修
		SimpleMap sMap = new SimpleMap();
		sMap.put("ADMINID", adminId);
		sMap.put("EMPID", empID);
		sMap.put("TRANSCODE", "TransTraining01");
		sMap.put("ACTIVITY", 1);

		// 留学
		SimpleMap sMap2 = new SimpleMap();
		sMap2.put("ADMINID", adminId);
		sMap2.put("EMPID", empID);
		sMap2.put("TRANSCODE", "TransTraining02");
		sMap2.put("ACTIVITY", 1);

		List studyList = services.getStudying(sMap);
		List study2List = services.getStudying(sMap2);

		request.setAttribute("contractInfoList", contractInfoList);
		request.setAttribute("contractInfoCnt", contractInfoList.size());
		request.setAttribute("personalInfo", personalInfo);
		request.setAttribute("lanuageInfoList", lanuageInfoList);
		request.setAttribute("lanuageInfoCnt", lanuageInfoList.size());
		request.setAttribute("educationInfoList", educationInfoList);
		request.setAttribute("educationInfoCnt", educationInfoList.size());
		request.setAttribute("ITLevelInfoList", ITLevelInfoList);
		request.setAttribute("ITLevelInfoCnt", ITLevelInfoList.size());
		request.setAttribute("studyList", studyList);
		request.setAttribute("studyListCnt", studyList.size());
		request.setAttribute("study2List", study2List);
		request.setAttribute("study2ListCnt", study2List.size());
		request.setAttribute("empID", empID);

		return "/hrm/hrm_delete.jsp";
	}
}
