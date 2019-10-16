/*
 * @(#)DownloadAttRecordTemplateCmd.java 1.0 2007-10-7 下午03:31:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.paramdate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午03:31:49
 * @version 1.0
 * 
 */
public class DownloadParamDateTemplateCmd extends PaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSourceReport = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		MessageSource messageSourceAr = new MessageSource("ar", admin.getLocale(), "UTF-8"); 
		
		String fileName = "PARAM_DATE.xls";

		String sheetName = "PARAM_DATE";

		SimpleMap columns = new SimpleMap();
		
		columns.setString(messageSourceReport.getMessage("report.global.title.empID"), "") ;
		columns.setString(messageSourceReport.getMessage("report.global.title.value"), "") ;
		columns.setString(messageSourceReport.getMessage("report.global.title.name"), "") ;
		columns.setString(messageSourceReport.getMessage("report.att.setting.dayoff.start_month"), "") ;
		columns.setString(messageSourceReport.getMessage("report.mutual.numericalR"), "") ;//期间数值
		columns.setString(messageSourceReport.getMessage("report.att.setting.dayoff.end_month"), "") ;

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);
		return "/inc/commonReport.jsp";
	}

}

