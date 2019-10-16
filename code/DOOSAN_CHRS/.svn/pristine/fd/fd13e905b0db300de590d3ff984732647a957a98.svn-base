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
public class ExportReport0210Cmd extends ArAbstractCommand {

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
			 columns.setString("职号", "EMPID");
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("所属", "DEPARTMENT");
			 columns.setString("职位", "POST_GRADE");
			 columns.setString("职级", "POST");
			 columns.setString("工资类型", "WAGES_TYPE");
			 columns.setString("号俸", "POST_COEF");
			 columns.setString("性别", "SEX");
			 columns.setString("婚否", "MARITAL_STATUS");
			 columns.setString("到公司日", "DATE_STARTED");
			 
			 columns.setString("基本工资", "BASE_PAY");
			 if (parameterObject.getString("cpnyId").equals("60000000")){
			 columns.setString("号俸补差", "THEM_SENDERS");
			 }
			 columns.setString("职务津贴", "DUTIES_ALLOWANCE");
			 columns.setString("职责津贴", "JOB_ALLOWANCE");
			 columns.setString("住宅补助", "RESIDENTIAL_GRANTS");
			 columns.setString("其他补偿", "OTHER_COMPENSATION");
			 columns.setString("地区补助", "REGIONAL_GRANTS");
			 columns.setString("特殊补助", "SPECIAL_GRANTS");
			 if (parameterObject.getString("cpnyId").equals("78000000")){
			     columns.setString("派驻补助", "SENT_GRANTS");
			     columns.setString("生活艰苦补助", "LIFE_GRANTS");
			 }
			 
			 if (parameterObject.getString("cpnyId").equals("60000000")){
			     columns.setString("职位补贴", "POSITION_ALLOWANCE");
			     columns.setString("技术补贴", "TECHNIQUE_ALLOWANCE");
			 }
			 
			 columns.setString("基本合计", "TOTAL_BASIC");
			 if (parameterObject.getString("cpnyId").equals("78000000")){
                 columns.setString("淡季奖", "OFF_SEA_BOUNS");
				 columns.setString("业绩工资3", "PERFORMANCE_PAY3");
			 }	
			 if (parameterObject.getString("cpnyId").equals("60000000")){
				 columns.setString("绩效工资", "PERFORMANCE_PAY");
				 columns.setString("顾客满意津贴", "CUSTOMER_SATISFACTION");
			 }	
			 
			 if (parameterObject.getString("cpnyId").equals("63000000")){
				 columns.setString("高温补助", "PAYMENT_HT_ALLOWANCE"); 	
				 columns.setString("餐费", "MEAL_FEE_SUBSIDY");
			 }else{
					columns.setString("餐费", "MEAL_FEE");
			 }
			 
			 columns.setString("其他支给", "TO_THE_OTHER");
			 if (parameterObject.getString("cpnyId").equals("63000000")){
			     columns.setString("销售奖励", "SALES_BOUNS");
			 }
			 if (parameterObject.getString("cpnyId").equals("63000000")||parameterObject.getString("cpnyId").equals("78000000")||parameterObject.getString("cpnyId").equals("60000000")){
                 columns.setString("手机话费", "MOBILE_PHONE_FEE");
				 columns.setString("燃油费", "FUEL_SURCHARGE_FEE");
			 }
			 columns.setString("支给错误", "STICKS_TO_THE_WRONG");
			 if (parameterObject.getString("cpnyId").equals("60000000")){
			     columns.setString("高温补助", "HIGH_T_SUBSIDY");
			 }
			 columns.setString("迟早减除", "LATE_EARLY_MINUS"); /////
			 columns.setString("未勤减除", "NOT_ATTENDANCE_MINUS");
			 columns.setString("事病减除", "LEAVE_SICK_MINUS"); ///
			 columns.setString("休假减除", "MATERNITY_MINUS");///
			 columns.setString("休职减除", "LEVEL_OFF");///
			 columns.setString("旷工减除", "ABSENTEEISM_MINUS");
			 columns.setString("其他减除", "OTHER_LESS");
			 columns.setString("住宅减除", "RESIDENTIAL_MINUS");
			 columns.setString("减除错误", "REDUCE_ERRORS");
			 columns.setString("放假减除", "HOLIDAY_MINUS_ALL");
			 columns.setString("试用扣除", "TRIAL_MINUS");
			 columns.setString("支给合计", "THIS_TOTAL_SUPPORT");
			 columns.setString("其他计税", "GIFT_COST");
			 columns.setString("个税附加扣除", "TAX_GRFJ");
			 //columns.setString("当前月个税抵扣合计", "CAL_DQGSDK_TOTAL");
			 
			 if (parameterObject.getString("cpnyId").equals("78000000")) {
					columns.setString("餐费计税", "MEAL_DUTY");
			 } 
			 if (parameterObject.getString("cpnyId").equals("63000000")) {
					columns.setString("税后减除", "TAX_AFTER_MINUS");
			 } 
			 columns.setString("所得税", "THIS_ACTUAL_TAX");
			 columns.setString("管理费", "HANDLING_CHARGE");
			 columns.setString("公司负担", "PAYMENT_OF_EMPLOYER");
			 columns.setString("小计", "XIAOJI");
			 if (parameterObject.getString("cpnyId").equals("60000000")){
				 columns.setString("个人养老", "PERSONAL_PENSION");
				 columns.setString("个人医疗", "PERSONAL_MEDICAL");
				 columns.setString("个人待业", "PERSONAL_UNEMPLOYED");
				 columns.setString("个人公积金", "PERSONAL_HOUSING_FUND");
			 }
			 columns.setString("个人负担", "PAYMENT_OF_INDIVIDUAL");
			 columns.setString("国企合计", "GUOQIHEJI");
			 columns.setString("实领工资", "THIS_ACTUAL_WAGE");
			 columns.setString("邮件", "EMAIL");
			 columns.setString("之前月税前工资合计", "CAL_SQGZ_TOTAL");
			 columns.setString("之前月个税抵扣合计", "CAL_GSDK_TOTAL");
			 columns.setString("之前月基本减除费用", "CAL_JBJC_TOTAL");
			 columns.setString("之前月个人保险合计", "CAL_GRBX_TOTAL");
			 columns.setString("之前月个税合计", "CAL_GS_TOTAL");
			
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
			paramBean.setFileName("pa_detail_filiale.xls");
			paramBean.setSheetname("PA_DETAIL_FILIALE_DATA");
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
		
		return service.retrieve780Report0210List(parameterObject);
	}
	
	private List setInfo63000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve630Report0210List(parameterObject);
	}
	
	private List setInfo60000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve600Report0210List(parameterObject);
	}

}

