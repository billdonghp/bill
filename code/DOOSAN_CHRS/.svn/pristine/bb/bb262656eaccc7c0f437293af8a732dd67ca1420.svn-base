/*
 * @(#)ViewEvaluateReportCmd.java 1.0 2007-5-17 上午11:30:33
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsEmp;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-5-17 上午11:30:33
 * @version 1.0
 * 
 */
public class ViewEvaluateReportCmd implements Command {

	private static final Logger logger = Logger
			.getLogger(ViewEvaluateReportCmd.class);

	private EvsEmp evsEmp = new EvsEmp();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			List lEvsPeriod = EvsEmp.getEvEmpPeriodList();

			request.setAttribute("evsPeriod", lEvsPeriod);

		} catch (Exception e) {

			logger.error("View evluate report Exception : " + e);
			throw new GlRuntimeException("View evluate report Exception : ", e);
		}

		return "/evs0304.jsp";
	}

}
