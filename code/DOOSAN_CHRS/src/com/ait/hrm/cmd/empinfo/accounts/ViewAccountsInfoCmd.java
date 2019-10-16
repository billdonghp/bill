/*
 * @(#)ViewAccountsInfoCmd.java 1.0 2007-1-5 下午03:00:17
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.PaEmpInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-5 下午03:00:17
 * @version 1.0
 * 
 */
public class ViewAccountsInfoCmd extends HrmAbstractCommand {

	@Override
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

		// retrieve accounts information
		PaEmpInfo paEmpInfo = (PaEmpInfo)services.retrieveAccountsInfo(empID);

		int emptyFlag = 0;
		if (paEmpInfo == null)
			emptyFlag = 1;
		
		request.setAttribute("paEmpInfo", paEmpInfo);
		request.setAttribute("empID", empID);
		request.setAttribute("emptyFlag", emptyFlag);
		
		return "/hrm/hrm_view_accounts.jsp";
	}

}

