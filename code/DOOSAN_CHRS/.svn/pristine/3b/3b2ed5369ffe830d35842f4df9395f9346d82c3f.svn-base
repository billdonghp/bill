/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.reports.servlet.pa;

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
public class ExportReport0230Cmd extends ArAbstractCommand {

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
			List recordList = service.retrieveReport0230List(parameterObject) ;

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("月份", "PA_MONTH");
			 columns.setString("工号", "EMPID");
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("所属", "DEPARTMENT");
			 columns.setString("职级", "POST");
			 columns.setString("职位", "POST_GRADE");
			 columns.setString("年龄", "AGE");
			 columns.setString("保险类型", "INSURANCE_TYPE");
			 columns.setString("入职日期", "DATE_STARTED");
			 columns.setString("本月应发", "SHOULD_BE_ISSUED_THIS_MONTH");
			 columns.setString("本月实发", "THIS_MONTH_IS_THE");
			 columns.setString("待补新应发之和", "TO_BE_SHOULD_NEW");
			 columns.setString("待补原应发之和", "TO_BE_SHOULD_OLD");
			 columns.setString("待补应发之差", "DIF_SHOULD");
			 columns.setString("补差汇总应发", "SUM_SHOULD");
			 columns.setString("补差汇总税", "SUM_SHOULD_TAX");
			 columns.setString("实发总额", "TOTAL_AMOUNT");
			 columns.setString("工资补差", "WAGE_SUPPLEMENT");													 
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_supplement.xls");
			paramBean.setSheetname("PA_SUPPLEMENT_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent("斗山（中国）投资有限公司  "+paMonth.substring(0,4)+"年"+paMonth.substring(4,6)+"月工资补差明细表");
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

