/*
 * @(#)DownloadAttRecordTemplateCmd.java 1.0 2007-10-7 下午03:31:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.vacation_emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
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
public class DownloadVacationEmpTemplateCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		String fileName = "VACATION_EMP_DATA.xls";

		String sheetName = "VACATION_EMP_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString(messageSource.getMessage("report.global.title.empID"), "") ;
		columns.setString("工作时间", "") ;
		columns.setString(messageSource.getMessage("report.att.annual.vacation.title.dayoff.type"), "") ;
		columns.setString(messageSource.getMessage("report.att.annual.vacation.title.dayoff.cycle"), "") ;
		columns.setString(messageSource.getMessage("report.global.title.start_date"), "") ;
		columns.setString(messageSource.getMessage("report.global.title.end_date"), "") ;
		columns.setString(messageSource.getMessage("report.att.annual.vacation.title.days"), "") ;

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}

}

