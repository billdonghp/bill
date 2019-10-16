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

public class ExportReport0223Cmd extends ArAbstractCommand {

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
			
			String month = parameterObject.getString("paMonth").substring(4) ;
			
			// retrieve attendance record list
			List recordList = service.retrieve610Report0223List(parameterObject);
			
			SimpleMap tempMap = new SimpleMap();
			for (int i = 0 ; i < recordList.size() ; ++i)
			{
				tempMap = (SimpleMap)recordList.get(i) ;
				tempMap.set("SEQUENCE", i + 1) ;
			}
			
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
	
			columns.setString("序号", "SEQUENCE");
			
			columns.setString("部门", "DEPARTMENT");
			columns.setString("职号", "EMPID");
			columns.setString("姓名", "CHINESENAME");
			columns.setString("基本工资", "BASE_PAY");
			columns.setString("住宅补助", "RESIDENTIAL_GRANTS");
			columns.setString("职务津贴", "DUTIES_ALLOWANCE");
			columns.setString("业绩工资2", "PERFORMANCE_PAY2");
			columns.setString("业绩工资3", "PERFORMANCE_PAY3");
			columns.setString("夜班补助", "NIGHT_DUTY_SUBSIDY");
			columns.setString("特殊补助", "SPECIAL_GRANTS");
			columns.setString("值班补助", "ZHIBAN");
			columns.setString("支给错误", "STICKS_TO_THE_WRONG");
			columns.setString("其他支给", "TO_THE_OTHER");
			columns.setString("高温补贴", "HIGH_T_SUBSIDY");
			columns.setString("奖金", "BONUS_VALUE");
			columns.setString("职务津贴", "DUTY_ALLOWANCE");
			columns.setString("技能津贴", "SKILL_ALLOWANCE");
			columns.setString("全勤奖", "PERFECT_ATTENDANCE");
			columns.setString("福利金", "WELFARE_ALLOWANCE");
			columns.setString("餐费", "MEAL_FEE");
			columns.setString("迟早扣除", "CHIZAO");
			columns.setString("事病减除", "SHIBING");
			columns.setString("旷工减除", "ABSENTEEISM_MINUS");
			columns.setString("放假减除", "HOLIDAY_MINUS_ALL");
			columns.setString("病休减除", "BINGXIU");
			columns.setString("未勤扣除", "NOT_ATTENDANCE_MINUS");
			columns.setString("试用扣除", "TRIAL_MINUS");
			columns.setString("其他减除", "OTHER_LESS");
			columns.setString("减除错误", "REDUCE_ERRORS");
			columns.setString("支给合计", "THIS_TOTAL_SUPPORT");
			columns.setString("住房公积金", "PERSONAL_WELFARE_TOTAL");
			columns.setString("预支扣除", "TAX_AFTER_MINUS");
			columns.setString("奖金税", "BONUS_TAX");
			columns.setString("当前月个税抵扣合计", "CAL_DQGSDK_TOTAL");
			columns.setString("所得税", "THIS_ACTUAL_TAX");
			columns.setString("减除合计", "TOTAL_DEDUCTIONS");
			columns.setString("实领工资", "THIS_ACTUAL_WAGE");
			columns.setString("之前月税前工资合计", "CAL_SQGZ_TOTAL");
			columns.setString("之前月个税抵扣合计", "CAL_GSDK_TOTAL");
			columns.setString("之前月基本减除费用", "CAL_JBJC_TOTAL");
			columns.setString("之前月个人保险合计", "CAL_GRBX_TOTAL");
			columns.setString("之前月个税合计", "CAL_GS_TOTAL");
			
			
			
			
			
//			columns.setString("姓名", "CHINESENAME");
//			columns.setString("所在地", "DEPARTMENT");
//			columns.setString("入社日期", "DATE_STARTED");
//			
//			columns.setString("年薪工资", "ANNUAL_PAY");
//			columns.setString("实习月薪", "PRACTICE_MONTHLY_PAY");
//			columns.setString("基本工资", "BASE_PAY");
//	
//			columns.setString("奖金", "BONUS_VALUE"); 
//		    columns.setString("职务津贴", "DUTY_ALLOWANCE");  
//			columns.setString("技能津贴", "SKILL_ALLOWANCE");  
//			 
//			
//			columns.setString("特殊补助", "SPECIAL_GRANTS");
//			columns.setString("加班工资", "OT_PAY");
//			columns.setString("其他支给", "TO_THE_OTHER");
//			columns.setString("事病假、旷工扣除", "ATTENDANCE_MINUS");
//			
//			columns.setString("本次支给合计", "THIS_TOTAL_SUPPORT");
//			
//			columns.setString(month + "月公积金个人部分", "PERSONAL_HOUSING_FUND");
//			
//			columns.setString("应扣公积金", "PERSONAL_WELFARE_TOTAL");
//			columns.setString("应纳税所得额", "TAXABLE_INCOME");
//			columns.setString("所得税", "THIS_ACTUAL_TAX");
//			columns.setString("实发工资", "THIS_ACTUAL_WAGE");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("Report0223.xls");
			paramBean.setSheetname("Report0223");
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
}
