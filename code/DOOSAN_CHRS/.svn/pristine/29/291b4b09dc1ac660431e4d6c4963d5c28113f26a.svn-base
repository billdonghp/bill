/*
 * @(#)MakeExpiredContractExcel.java 1.0 2006-12-30 下午05:18:19
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.contract;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2006-12-30 下午05:18:19
 * @version 1.0
 * 
 */

public class MakeExpiredContractExcelCmd extends HrmAbstractCommand {

	private static final Logger logger = Logger
			.getLogger(ViewContractByInsertCmd.class);

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
			if (request.getParameter("DEPTID") != null) {
				List list = services.searchExpiredContract(simpleMap);
				request.setAttribute("makeExpiredContractExcel", list);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"The information Exception when running the MakeExpiredContractExcel. ",
					e);
		}
		return "/hrm/hrm_expiredcontract_excel.jsp";
	}

}
