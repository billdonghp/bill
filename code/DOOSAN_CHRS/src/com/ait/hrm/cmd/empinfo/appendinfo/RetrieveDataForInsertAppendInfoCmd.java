/*
 * @(#)RetrieveDataForInsertAppendInfoCmd.java 1.0 2006-12-19 下午04:45:37
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.appendinfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
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
public class RetrieveDataForInsertAppendInfoCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putBasicInfo(request);
		this.putToolbarInfo(request);

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String lan = admin.getLanguagePreference();
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		
		String adminName = "";
		
		if(lan.equals("zh")){
			adminName = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getChineseName();
		}else if(lan.equals("ko")){
			adminName = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getKoreanname();
		}else{
			adminName = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getPinyin();
		}
		
		
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		SimpleMap paramObject = new SimpleMap();
		paramObject.setString("adminId", adminId);   
		paramObject.setString("EMPID", empID);

		// retrieve append information
		List appendInfoList = services.retrieveAdditionalInfo(empID);
		List resignationList = services.getResignation(paramObject);

		request.setAttribute("appendInfoList", appendInfoList);
		request.setAttribute("appendInfoCnt", appendInfoList.size());
		request.setAttribute("resignationList", resignationList);
		request.setAttribute("resignationCnt", resignationList.size());
		request.setAttribute("empID", empID);
		request.setAttribute("adminId", adminId);
		request.setAttribute("adminName", adminName);

		return "/hrm/hrm_add_append.jsp";
	}

}
