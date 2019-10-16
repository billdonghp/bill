/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.idcard;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
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
public class ExportAttRecordReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArServices services = new ArServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			if (parameterObject.getString("TABLE_NAME") == null || parameterObject.getString("TABLE_NAME").length() == 0)
			{
				parameterObject.set("TABLE_NAME", "AR_MAC_RECORDS");
			}
			
			parameterObject.set("startRownum",0);
			parameterObject.set("endRownum",100000);
			
			// retrieve attendance record list
			List recordList = services.retrieveAttRecordList(parameterObject);

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
//			 columns.setString("工号", "EMPID");
//			 columns.setString("姓名", "CHINESENAME");
//			 columns.setString("部门", "DEPTNAME");
//			 columns.setString("卡号", "CARD_NO");
//			 columns.setString("时间", "R_TIME");
//			 columns.setString("修改时间", "INSERT_TIME");
			 
			columns.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
			columns.setString(messageSource.getMessage("report.global.title.name"), messageSource.getMessage("report.global.field.name"));
			columns.setString(messageSource.getMessage("report.global.title.deptName"), messageSource.getMessage("report.global.field.deptName"));
			columns.setString(messageSource.getMessage("report.att.record.title.cardNo"), "CARD_NO");
			columns.setString("刷卡时间", "R_TIME");
			columns.setString("导入时间", "INSERT_TIME");
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 columnType.setInt("CARD_NO", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("att_record_data.xls");
			paramBean.setSheetname("ATT_RECORD_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent(messageSource.getMessage("report.att.record.title.tablename"));
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export attendance record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

