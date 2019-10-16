/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.common;

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

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangliwei (wangliwei@ait.net.cn)
 * @Date 2006-12-22 下午01:03:53
 * @version 1.0
 * 
 */
public class ViewEmpListCmd extends HrmAbstractCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HrmServices services = HrmServices.getInstance();
		// get login ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();

		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", adminId);
		parameterObject.setString("CONDITION", StringUtil.toCN(request
				.getParameter("condition")));
//		parameterObject.set("DATELEFT", "not null");
		// retrieve employee list
		List empList = services.retrieveEmpList(parameterObject);

		request.setAttribute("empList", empList);
		request.setAttribute("empListCnt", empList.size());
		request.setAttribute("menu_code", request.getParameter("menu_code"));
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("nextOperation", request
				.getParameter("nextOperation"));

		return "/hrm/inc/hrm_SearchEmployee.jsp";
	}
}
