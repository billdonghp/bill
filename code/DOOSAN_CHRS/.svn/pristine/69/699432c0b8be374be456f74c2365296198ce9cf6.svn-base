/*
 * @(#)ImportVacationEmpReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.bonus.bonusparamdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportPaBonusParamDataReportCmd extends PaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message;

		SimpleMap parameterObject;
		PaServices paService = PaServices.getInstance();
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", admin.getCpnyId());
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			columns.setString("职号","EMPID");
			columns.setString("姓名","CHINESENAME");
			columns.setString("部门","DEPTNAME");
			columns.setString("默认值","RETURN_VALUE");
			columns.setString("开始月","START_MONTH");
			columns.setString("期间数值","SD_ED_VALUE");
			columns.setString("结束月","END_MONTH");

			List recordList = paService.retrieveExportPaBonusParamDataList(parameterObject);
			
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_bonus_param_data.xls");
			paramBean.setSheetname("PA_BONUS_PARAM_DATA");
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

