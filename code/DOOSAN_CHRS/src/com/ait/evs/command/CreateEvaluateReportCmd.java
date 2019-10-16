/*
 * @(#)CreateEvaluateReportCmd.java 1.0 2007-5-17 下午03:21:18
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
import com.ait.evs.business.EvsServices;
import com.ait.excel.util.ReportUtil;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-5-17 下午03:21:18
 * @version 1.0
 * 
 */
public class CreateEvaluateReportCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		EvsServices services = EvsServices.getInstance();

		List evaluateList;

		SimpleMap parameterObject = ObjectBindUtil.getData(request);

		String reportType = parameterObject.getString("reportType");

		try {

			if (reportType.equals("evGrade")) {

				evaluateList = (List) ((EvsServices) services)
						.retrieveEvaluateResultList(parameterObject);
				
				String fileName = "evs_result_list.xls";

				String sheetName = "EVS_RESULT";

				// make evaluate result report
				ReportUtil.makeReport(request, evaluateList, fileName,
						sheetName, 1, 1);
				
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Create evaluate  report error. " + e.toString());
			throw new GlRuntimeException("Create evaluate  report error. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}
