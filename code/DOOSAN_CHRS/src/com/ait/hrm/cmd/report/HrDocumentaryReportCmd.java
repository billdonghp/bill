/*
 * @(#)HrDocumentaryReport.java 1.0 2007-1-14 下午06:20:37
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.report;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2007-1-14 下午06:20:37
 * @version 1.0
 * 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;

public class HrDocumentaryReportCmd extends HrmAbstractCommand {

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HrmServices services = HrmServices.getInstance();
		// get login ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();

		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", adminId);
		parameterObject.setString("CONDITION", StringUtil.toCN(request
				.getParameter("condition")));
		
		List empList = services.retrieveEmpList(parameterObject);

		request.setAttribute("empList", empList);
		request.setAttribute("empListCnt", empList.size());
		request.setAttribute("menu_code", request.getParameter("menu_code"));
		request.setAttribute("nextOperation", request
				.getParameter("nextOperation"));		
		
		return "/hrm/inc/SearchEmployeeForAllType.jsp";
	}

}



