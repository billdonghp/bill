/*
 * @(#)DownloadEateryRecordTemplateCmd.java 1.0 2007-10-7 下午03:31:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.eatery;

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
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午03:31:49
 * @version 1.0
 * 
 */
public class DownloadEateryRecordTemplateCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		String fileName = "Att_Eatery_Data.xls";

		String sheetName = "ATT_EATERY_DATA";

		SimpleMap columns = new SimpleMap();
		//就餐卡号  时间  就餐类型 备注
		columns.setString(messageSource.getMessage("report.att.record.title.cardNo"), "");
		columns.setString(messageSource.getMessage("report.att.eat.title.eat_time"), "");
		columns.setString(messageSource.getMessage("report.att.eat.title.eat_type"), "");
		columns.setString(messageSource.getMessage("report.global.title.remark"), "");

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}

}

