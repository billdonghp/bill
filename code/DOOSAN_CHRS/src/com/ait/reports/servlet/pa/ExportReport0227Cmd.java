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

public class ExportReport0227Cmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	private PaReportService service = null;

	private AdminBean admin = null;

	private SimpleMap parameterObject = null;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service = new PaReportService();
		admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
//zj			if (parameterObject.getString("distinction").length() > 0) {
//zj				parameterObject.set("distinction", parameterObject.getString("distinction").split(","));
//zj			}

			// retrieve attendance record list
			List recordList = new ArrayList() ; 

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			columns.setString("工资月", "PA_MONTH");
			columns.setString("区分", "EMPLOYEE_DISTINCTION");
			columns.setString("部门名称", "OFFICENAME");
			columns.setString("人数", "RENSHU");

			columns.setString("基本工资", "BASE_PAY");
			columns.setString("职务津贴", "DUTIES_ALLOWANCE");
			columns.setString("住宅补助", "RESIDENTIAL_GRANTS");
			columns.setString("特殊补助", "SPECIAL_GRANTS"); 
			
			columns.setString("基本合计", "TOTAL_BASIC");

			columns.setString("业绩工资2", "PERFORMANCE_PAY2");
			columns.setString("业绩工资3", "PERFORMANCE_PAY3");
			
			if (parameterObject.getString("cpnyId").equals("63000000")) {
				
				columns.setString("研发补助", "RESEARCH_SUBSIDIES");
				columns.setString("高温补助", "PAYMENT_HT_ALLOWANCE");
			}
			
			if (parameterObject.getString("cpnyId").equals("60000000")) {
				
				columns.setString("技术补贴", "TECHNIQUE_ALLOWANCE");
				columns.setString("体制补差", "SYSTEM_BALANCE");
			}
			
			columns.setString("其他支给", "TO_THE_OTHER");
			columns.setString("支给错误", "STICKS_TO_THE_WRONG");
			columns.setString("夜班补助", "NIGHT_DUTY_SUBSIDY");
			columns.setString("值班补助", "DUTY_SUBSIDIES");
			columns.setString("迟早减除", "LATE_EARLY_MINUS"); 
			columns.setString("未勤减除", "NOT_ATTENDANCE_MINUS");
			columns.setString("事病减除", "LEAVE_SICK_MINUS"); 
			columns.setString("休假减除", "MATERNITY_MINUS");
			columns.setString("休职减除", "LEVEL_OFF_MINUS");
			columns.setString("旷工减除", "ABSENTEEISM_MINUS");
			columns.setString("其他减除", "OTHER_LESS");
			columns.setString("住宅减除", "RESIDENTIAL_MINUS");
			columns.setString("减除错误", "REDUCE_ERRORS");
			columns.setString("放假减除", "HOLIDAY_MINUS_ALL");
			
			if (parameterObject.getString("cpnyId").equals("60000000")) {
				
				columns.setString("工伤减除", "INDUSTRY_INJURY_MINUS");
			} 
			
			columns.setString("试用扣除", "TRIAL_MINUS");
			columns.setString("独生子女", "ONLY_CHILD");
			columns.setString("支给合计", "THIS_TOTAL_SUPPORT");

			columns.setString("养老保险", "PERSONAL_PENSION");
			columns.setString("医疗保险", "PERSONAL_MEDICAL");
			columns.setString("待业保险", "PERSONAL_UNEMPLOYED");
			columns.setString("公积金", "PERSONAL_HOUSING_FUND");
			columns.setString("当前月个税抵扣合计", "CAL_DQGSDK_TOTAL");
			columns.setString("所得税", "THIS_ACTUAL_TAX");
			columns.setString("减除合计", "TOTAL_DEDUCTIONS");
			columns.setString("实领工资", "THIS_ACTUAL_WAGE");
			columns.setString("之前月税前工资合计", "CAL_SQGZ_TOTAL");
			columns.setString("之前月个税抵扣合计", "CAL_GSDK_TOTAL");
			columns.setString("之前月基本减除费用", "CAL_JBJC_TOTAL");
			columns.setString("之前月个人保险合计", "CAL_GRBX_TOTAL");
			columns.setString("之前月个税合计", "CAL_GS_TOTAL");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);

			if (parameterObject.getString("cpnyId").equals("78000000")) {
				
				recordList = this.setInfo78000000(request, response, columns);
			} else if (parameterObject.getString("cpnyId").equals("60000000")) {

				recordList = this.setInfo60000000(request, response, columns);
			} else if (parameterObject.getString("cpnyId").equals("63000000")) {

				recordList = this.setInfo63000000(request, response, columns);	
			}
			
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_detail_factory.xls");
			paramBean.setSheetname("PA_DETAIL_FACTORY_DATA");
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
			throw new GlRuntimeException("export pa_detail_factory record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

	private List setInfo78000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve780Report0202List(parameterObject);
	}
	
	private List setInfo60000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve600Report0202List(parameterObject);
	}
	
	private List setInfo63000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve630Report0202List(parameterObject);
	}

}
