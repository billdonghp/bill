/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.vacation_emp;

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
public class ExportVacationEmpCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArServices services = new ArServices();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.setString("cpnyId", admin.getCpnyId());

			// retrieve vacationEmp list
			List recordList = services.retrieveVacationEmpList(parameterObject);
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 //工号 姓名 部门 休假类型 休假ID 开始日期 结束日期 休假天数 已用天数 剩余天数 操作者 修改时间
			 columns.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
			 columns.setString(messageSource.getMessage("report.global.title.name"), messageSource.getMessage("report.global.field.name"));
			 columns.setString(messageSource.getMessage("report.global.title.deptName"), messageSource.getMessage("report.global.field.deptName"));
			 columns.setString("工作时间", "WORK_YEAR");
			 columns.setString(messageSource.getMessage("report.att.annual.vacation.title.dayoff.type"), 
					 messageSource.getMessage("report.att.annual.vacation.field.dayoff.type"));
			 columns.setString(messageSource.getMessage("report.att.annual.vacation.title.dayoff.cycle"), "VAC_ID");
			 columns.setString(messageSource.getMessage("report.global.title.start_date"), "STRT_DATE");  
			 columns.setString(messageSource.getMessage("report.global.title.end_date"), "END_DATE");
			 columns.setString(messageSource.getMessage("report.att.annual.vacation.title.days"), "TOT_VAC_CNT");
			 columns.setString(messageSource.getMessage("report.att.annual.vacation.title.taken"), "USE_VAC_CNT");
			 columns.setString(messageSource.getMessage("report.att.annual.vacation.title.owing"), "REST_VAC_CNT");
			 columns.setString(messageSource.getMessage("report.global.title.operator"), 
					 messageSource.getMessage("report.global.field.operator"));
			 columns.setString(messageSource.getMessage("report.global.title.updateTime"), "UPDATE_DATE");
			 
//			 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("vacation_emp_data.xls");
			paramBean.setSheetname("VACATION_EMP_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			/*
			// 添加报表图片
			paramBean.setImageCol(columns.size() - 4);
			paramBean.setImageRow(recordList.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			 */
			// 设置内嵌表头
			//DICC个人年假记录表
			paramBean.setInLineHeadContent(messageSource.getMessage("report.att.annual.vacation.title.tablename"));
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export vacationEmp report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

