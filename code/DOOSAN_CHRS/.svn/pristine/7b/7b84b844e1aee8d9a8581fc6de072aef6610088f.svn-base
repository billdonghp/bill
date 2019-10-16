/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.reports.servlet.pa;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ait.reports.reportservices.PaReportService;
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
public class ExportReport0209Cmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	private PaReportService service = null;

	private AdminBean admin = null;

	private SimpleMap parameterObject = null;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service = new PaReportService() ;
		admin = (AdminBean) request.getSession(false).getAttribute("admin");
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			// retrieve attendance record list
			List recordList = new ArrayList() ;

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("工资月", "PA_MONTH");
			 columns.setString("区分", "EMPLOYEE_DISTINCTION");
			 columns.setString("部门", "OFFICENAME");
			 columns.setString("人员", "RENSHU");
			 
			 columns.setString("基本工资", "BASE_PAY");
			 
			if (parameterObject.getString("cpnyId").equals("60000000")) {
					
				columns.setString("住宅补助", "RESIDENTIAL_GRANTS");
				columns.setString("其他支给", "STICKS_TO_THE_WRONG");
			}
			else if (parameterObject.getString("cpnyId").equals("63000000")) {
					 
				columns.setString("其他支给", "STICKS_TO_THE_WRONG");
			}
			 
			 columns.setString("支给合计", "THIS_TOTAL_SUPPORT");
			 columns.setString("所得税", "THIS_ACTUAL_TAX");
			 if (parameterObject.getString("cpnyId").equals("78000000")) {
				 	columns.setString("其他减除", "OTHER_LESS");
					columns.setString("公司负担", "PAYMENT_OF_EMPLOYER");
					columns.setString("个人负担", "PAYMENT_OF_INDIVIDUAL");
			 }
			 columns.setString("实领奖金", "THIS_ACTUAL_BONUS");
			
			if (parameterObject.getString("cpnyId").equals("78000000")) {
				
				recordList = this.setInfo78000000(request, response, columns);
			} else if (parameterObject.getString("cpnyId").equals("63000000")) {

				recordList = this.setInfo63000000(request, response, columns);
			}else if (parameterObject.getString("cpnyId").equals("60000000")) {

				recordList = this.setInfo60000000(request, response, columns);
			}
			
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_bonus_detail_filiale.xls");
			paramBean.setSheetname("PA_BONUS_DETAIL_Filiale_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent("");
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
	
	private List setInfo78000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve780Report0209List(parameterObject);
	}
	
	private List setInfo63000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve630Report0209List(parameterObject);
	}
	
	private List setInfo60000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve600Report0209List(parameterObject);
	}
	
}

