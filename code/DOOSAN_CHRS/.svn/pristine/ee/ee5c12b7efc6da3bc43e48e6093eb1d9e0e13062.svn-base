/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs.command;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsMaster;
import com.ait.evs.business.EvsServices;
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
public class ExportJnpjRecordReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArServices services = new ArServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		SimpleMap parameterObject = new SimpleMap();
		EvsMaster evsMaster=new EvsMaster();
		EvsServices evsservices = EvsServices.getInstance();
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			if (parameterObject.getString("TABLE_NAME") == null || parameterObject.getString("TABLE_NAME").length() == 0)
			{
				parameterObject.set("TABLE_NAME", "AR_MAC_RECORDS");
			}
			
			parameterObject.set("startRownum",0);
			parameterObject.set("endRownum",100000);
			
			// retrieve attendance record list
			List recordList = evsservices.retrieveJnpjRecordList(parameterObject);
				//recordList=evsMaster.getAllEvEmpsByMasterPeriod2(evPeriodId,period,GoEmp,evDeptId,craft,line,jobcontent,zyzgdj,gxjndj,sumstoref,sumstoret,deptke,deptzhi,deptzu);//大的 
			
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 
			 columns.setString("年度", "EV_PERIOD_ID");
			 columns.setString("职号", "EMPID");
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("部门", "DEPTNAME");
			 columns.setString("课", "DEPTKE");
			 columns.setString("职", "DEPTZHI");
			 columns.setString("组", "DEPTZU");			 
			 columns.setString("工种", "CRAFT");
			 columns.setString("岗位", "LINE");
			 columns.setString("工序", "JOBCONTENT");
			 columns.setString("工序技能等级", "SKILLLEVEL");
			 columns.setString("职业资格", "ZYZGDJLEVEL");
			 columns.setString("积分", "SUMSCORE");
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 //columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			// columnType.setInt("CARD_NO", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("jnpj_record_data.xls");
			paramBean.setSheetname("RECORD_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			// LSFC考勤刷卡记录表
			paramBean.setInLineHeadContent("评价成绩列表");
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

