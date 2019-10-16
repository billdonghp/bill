/*
 * @(#)ExportVisitCardReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.arHistory;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;

public class ExportArHistoryReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArServices arService = new ArServices();

		//取日期参数
		GregorianCalendar currentDay = new GregorianCalendar();
		int m = currentDay.get(Calendar.MONTH) + 1;
		int y = currentDay.get(Calendar.YEAR);

		String month = ("0" + String.valueOf(m)).substring(("0" + String.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
		String year = String.valueOf(y);

		String arMonth = year + month;

		if (request.getParameter("month") != null && request.getParameter("year") != null) {
			try {
				month = request.getParameter("month");
				year = request.getParameter("year");

				arMonth = year + month;

			} catch (Exception e) {
			}
		}

		String key = StringUtil.checkNull((request.getParameter("key")));
		String deptID = StringUtil.checkNull((request.getParameter("deptID")));
		String empDiffType = StringUtil.checkNull((request.getParameter("empDiffType")));

		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");

		try {

			SimpleMap parameterObject = new SimpleMap();

			parameterObject.setString("deptID", deptID);
			parameterObject.setString("empDiffType", empDiffType);
			parameterObject.setString("arMonth", arMonth);
			parameterObject.setString("condition", key);
			parameterObject.setString("supervisor", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("sqlStatement", arService.getArColumns2(admin.getCpnyId()));
			
			SimpleMap columns = new SimpleMap();
			columns.setString("考勤月", "AR_MONTH");
			columns.setString("员工区分", "ATTENDANCE_PERIOD");
			columns.setString("职号", "EMPID");
			columns.setString("姓名", "CHINESENAME");
			columns.setString("部门", "DEPARTMENT");
			if("78000000".equals(admin.getCpnyId())){
				columns.setString("部门全称", "DEPTFULLNAME");
			}
			List<SimpleMap> columnList = arService.getArColumns(admin.getCpnyId());
			for (SimpleMap map : columnList) 
				columns.setString(map.getString("ITEM_NAME"), map.getString("COLUMN_NAME"));

			List recordList = arService.getArData(parameterObject);

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("ar_history_data.xls");
			paramBean.setSheetname("AR_HISTORY_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export Ar History report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}
