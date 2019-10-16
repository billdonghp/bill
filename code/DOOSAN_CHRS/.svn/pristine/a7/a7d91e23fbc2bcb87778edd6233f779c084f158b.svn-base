/*
 * @(#)ExportPaDetailMonthCmd.java 1.0 2007-11-17 下午01:43:00
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.util.StringUtil ; 
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class EmpPostReport extends PaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.pa.servlet.PaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaServices services = PaServices.getInstance();
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject ;
		SimpleMap map_dept ;
		SimpleMap map_section ;
		SimpleMap map_office ;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		MessageSource msg = new MessageSource(admin.getLocale(), "UTF-8"); 
		List deptList = new ArrayList() ; //部一级的部门
		List deptList_section = new ArrayList() ; //科一级的部门
		List deptList_office = new ArrayList() ;  //办公室一级的部门
		
		// retrieve report pa detail month list
		List reportList = new ArrayList() ;
		List list = new ArrayList() ;
		List list_sum = new ArrayList() ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			
			String fileName = "pa_detail_month.xls";

			String sheetName = "pa_detail_month";


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export pa detail month report data Exception. ", e);
		}

		return "/reports/pa_report/dicc_emp_post.jsp";
	}
}

