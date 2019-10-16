/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.arBalance;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午02:02:14
 * @version 1.0
 * 
 */
public class ExportArBalanceReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArBalanceDAO arBalanceDAO = new ArBalanceDAO();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// retrieve attendance record list
			List recordList = arBalanceDAO.getArBlanlance(parameterObject);

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			
			columns.setString("考勤月", "AR_MONTH");
			columns.setString("考勤日期", "PAY_YYMMDD");
			columns.setString("职号", "PAY_EMPNO");
			columns.setString("姓名", "CHINESENAME");
			columns.setString("私出", "PAY_UGOTM");
			columns.setString("平时加班", "PAY_EXTTM");
			columns.setString("周末加班", "PAY_SUNTM");
			columns.setString("假日加班", "PAY_HOLTM");
			columns.setString("白班", "PAY_DAY");
			columns.setString("夜班", "PAY_NIGHT");
			columns.setString("迟到", "PAY_LATE");
			columns.setString("早退", "PAY_EARLY");
			columns.setString("请假区分", "PAY_JIA");
			columns.setString("请假小时", "PAY_JIATM");
			columns.setString("部门名称", "PAY_DNM");
			columns.setString("所属名称", "PAY_DEPNM");

			
			 // 定义列类型
			SimpleMap columnType = new SimpleMap();
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("ar_balance_data.xls");
			paramBean.setSheetname("AR_BALANCE_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export ArBalance record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

