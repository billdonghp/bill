/*
 * @(#)ViewContractReportCmd.java 1.0 2006-12-19 下午04:54:41
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.contract;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.servlet.HrmAbstractCommand;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午04:54:41
 * @version 1.0
 * 
 */
public class ViewContractReportCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putDeptTree(request);
		this.putToolbarInfo(request);
		
		return "/hrm/hrm_report_cont.jsp";
	}

}

