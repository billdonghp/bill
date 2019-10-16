/*
 * @(#)CreateContractReportCmd.java 1.0 2006-12-30 下午09:55:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.contract;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2006-12-30 下午09:55:21
 * @version 1.0
 * 
 */
public class CreateContractReportCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putDeptTree(request);
		this.putToolbarInfo(request);

		HrmServices services = HrmServices.getInstance();

		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		SimpleMap simpleMap = new SimpleMap();
		simpleMap = ObjectBindUtil.getData(request);
		try {
			List list = services.searchContractForOutExcel(simpleMap);
			request.setAttribute("searchContractForOutExcel", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/hrm/hrm_contractreport_excel.jsp";
	}

}
