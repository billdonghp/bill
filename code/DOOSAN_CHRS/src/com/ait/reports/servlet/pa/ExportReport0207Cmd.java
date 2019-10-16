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
public class ExportReport0207Cmd extends ArAbstractCommand {

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
			if(parameterObject.getString("distinction") != null && parameterObject.getString("distinction").length() > 0)
			{
				parameterObject.set("distinction", parameterObject.getString("distinction").split(",")) ;
			}
			

			// retrieve attendance record list
			List recordList = new ArrayList() ;

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("工资月", "PA_MONTH");
			 columns.setString("职号", "EMPID");
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("所属", "DEPARTMENT");
			 columns.setString("部门", "DEPTNAME");
			 if (parameterObject.getString("cpnyId").equals("78000000")) {
				 columns.setString("所属全称", "DEPTFULLNAME");
				 columns.setString("当月部门", "OFFICENAME2");
			 }
			 columns.setString("在职类型", "CODE_NAME");
			 columns.setString("职位", "POST_GRADE");
			 columns.setString("职级", "POST");
			 columns.setString("工资类型", "WAGES_TYPE");
			 columns.setString("职群", "JOIN_TYPE");
			 if (parameterObject.getString("cpnyId").equals("63000000") || parameterObject.getString("cpnyId").equals("78000000")) {
				 columns.setString("制管销区分", "PZ_TYPE");
			 }
			 columns.setString("号俸", "POST_COEF");
			 columns.setString("性别", "SEX");
			 columns.setString("婚否", "MARITAL_STATUS");
			 columns.setString("到公司日", "DATE_STARTED");
			 columns.setString("最初入职日", "FIRST_ENTER_DATE");
			 
			 columns.setString("基本工资", "BASE_PAY");
			 
			if (parameterObject.getString("cpnyId").equals("60000000")) {

				columns.setString("其他支给", "STICKS_TO_THE_WRONG");
			}
			else if (parameterObject.getString("cpnyId").equals("63000000")) {
					 
				columns.setString("奖金2", "STICKS_TO_THE_WRONG");
			}else if (parameterObject.getString("cpnyId").equals("78000000")) {
				columns.setString("奖金", "BASE_BONUS");	 	 
				columns.setString("奖金2", "STICKS_TO_THE_WRONG");
				columns.setString("其他减除", "OTHER_LESS");
			}
			 
			 columns.setString("支给合计", "THIS_TOTAL_SUPPORT");
			 columns.setString("所得税", "THIS_ACTUAL_TAX");
			 columns.setString("实领奖金", "THIS_ACTUAL_BONUS");
			 
			 columns.setString("邮件", "EMAIL");
			
			if (parameterObject.getString("cpnyId").equals("78000000")) {
				
				recordList = this.setInfo78000000(request, response, columns);
			} else if (parameterObject.getString("cpnyId").equals("60000000")) {

				recordList = this.setInfo60000000(request, response, columns);
			} else if (parameterObject.getString("cpnyId").equals("63000000")) {

				recordList = this.setInfo63000000(request, response, columns);
			}
			
			// 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_bonus_detail_factory.xls");
			paramBean.setSheetname("PA_BONUS_DETAIL_FACTORY_DATA");
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
		
		return service.retrieve780Report0207List(parameterObject);
	}
	
	private List setInfo60000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve600Report0207List(parameterObject);
	}
	
	private List setInfo63000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve630Report0207List(parameterObject);
	}

}

