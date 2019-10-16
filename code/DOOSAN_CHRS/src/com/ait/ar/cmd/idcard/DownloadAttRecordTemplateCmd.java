/*
 * @(#)DownloadAttRecordTemplateCmd.java 1.0 2007-10-7 下午03:31:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.idcard;

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
public class DownloadAttRecordTemplateCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = "Att_Record_Data.xls";

		String sheetName = "ATT_RECORD_DATA";

		SimpleMap columns = new SimpleMap();

		AdminBean admin =(AdminBean) request.getSession().getAttribute("admin");
					MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
					String message1 = messageSource.getMessage("display.mutual.emp_id");
					String message2 = messageSource.getMessage("display.mutual.time");
					String message3 = messageSource.getMessage("display.mutual.type");
					String message4 = messageSource.getMessage("display.mutual.notes");
		
		columns.setString(message1, "");
		columns.setString(message2, "");
		columns.setString(message3, "");
		columns.setString(message4, "");                 

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}

}

