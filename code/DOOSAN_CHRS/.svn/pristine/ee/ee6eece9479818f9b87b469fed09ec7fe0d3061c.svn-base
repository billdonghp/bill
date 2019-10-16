/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportPayBankCodeCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaServices paServices = PaServices.getInstance();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject;
		SimpleMap map = new SimpleMap() ;

		try {
			DecimalFormat df = new DecimalFormat("0000");
			
			// retrieve attendance record list
			List recordList = new ArrayList() ;
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("paMonth", parameterObject.getString("year") + parameterObject.getString("month")) ;
			
			if(parameterObject.getString("tableName").equals("PA_HISTORY"))
			{
				recordList = paServices.PayBankCodeForSearchPa(parameterObject);
			}
			else
			{
				recordList = paServices.PayBankCodeForSearchBonus(parameterObject);
			}
			
			for (int i = 0 ; i < recordList.size() ; ++i)
			{
				map = (SimpleMap)recordList.get(i) ;
				map.set("SEQ", df.format(i + 1)) ;
			}
			
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("序号", "SEQ");
			 columns.setString("部门", "DEPARTMENT");
			 columns.setString("职号", "EMPID");
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("银行帐号", "BANK_CARD_NO");
			 columns.setString("实发工资", "WAGE");
			 
			 
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("SEQ", ReportConstant.CELL_TYPE_TEXT);
			 columnType.setInt("BANK_CARD_NO", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("BANK_CARD_NO.xls");
			paramBean.setSheetname("BANK_CARD_NO");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export BANK_CARD_NO record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

