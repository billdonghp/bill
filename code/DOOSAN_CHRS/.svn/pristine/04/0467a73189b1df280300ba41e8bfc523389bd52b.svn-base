/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.reports.servlet.pa;

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
import com.ait.reports.reportservices.PaReportService;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午02:02:14
 * @version 1.0
 * 
 */
public class ExportReport0232Cmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaReportService service = new PaReportService() ;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			String paMonth = StringUtil.checkNull(parameterObject.getString("paMonth"));
			if(paMonth.length() > 0)
			{
				parameterObject.set("paMonth", paMonth) ;
			}
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			// retrieve attendance record list
			List recordList = service.retrieveReport0232List(parameterObject);

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("部门", "DEPARTMENT");
			 columns.setString("人数", "PNUM");
			 columns.setString("基本工资", "BASED_ON_WAGES");
			 columns.setString("外地派遣生活费", "LIVING_ABROAD_TO_SEND");
			 columns.setString("外地派遣住宿费", "SEND_ACCOMMODATION");
			 columns.setString("其他补助", "OTHER_GRANTS");
			 columns.setString("其他扣除", "OTHER_DEDUCTIONS");
			 columns.setString("加班费合计", "TOTAL_OVERTIME");
			 columns.setString("考勤扣除合计", "TOTAL_ATTENDANCE_DEDUCTION");
			 columns.setString("个人保险合计", "TOTAL_PERSONAL_INSURANCE");
			 columns.setString("应发工资", "SHOULD_BE_ISSUED_THIS_MONTH");
			 columns.setString("个人所得税", "INCOME_TAX");
			 columns.setString("实发工资", "THIS_MONTH_IS_THE");
			 columns.setString("工资补差", "WAGE_SUPPLEMENT");
			 columns.setString("实发总额", "TOTAL_AMOUNT");
			 columns.setString("公司保险合计", "TOTAL_INSURANCE_COMPANIES");
			 columns.setString("公司负担总额", "COM");
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("DEPARTMENT", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_summary_dept.xls");
			paramBean.setSheetname("PA_SUMMARY_DEPT_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent("斗山（中国）投资有限公司  "+paMonth.substring(0,4)+"年"+paMonth.substring(4,6)+"月 工资汇总表（部门别）");
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

