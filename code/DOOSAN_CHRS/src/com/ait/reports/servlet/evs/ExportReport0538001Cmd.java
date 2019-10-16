/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.reports.servlet.evs;

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
import com.ait.reports.reportservices.EvsReportService;
import com.ait.reports.reportservices.PaReportService;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportReport0538001Cmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	private EvsReportService service = null;

	private AdminBean admin = null;

	private SimpleMap parameterObject = null;
	int pageSize = 0;
	int pageGroupSize = 0;
	int currentPage = 0;
	int resultCount = 0;   
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = new EvsReportService() ;
		admin = (AdminBean) request.getSession(false).getAttribute("admin");
        String  deptid = request.getParameter("deptid"); 
        String  year = request.getParameter("year");
        String  yearEnd = request.getParameter("yearEnd");
        String  empId = request.getParameter("empId"); 
        String  craftid = request.getParameter("craftid");
        String  qualificationid = request.getParameter("qualificationid");
        List recordList = new ArrayList();
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("deptid", deptid); 
			parameterObject.setString("year", year);
			parameterObject.setString("yearEnd", yearEnd);
			parameterObject.setString("empId", empId); 
			parameterObject.setString("craftid", craftid); 
			parameterObject.setString("qualificationid", qualificationid); 
			if(parameterObject.getString("distinction") != null && parameterObject.getString("distinction").length() > 0)
			{
				parameterObject.set("distinction", parameterObject.getString("distinction").split(",")) ;
			}
			if (parameterObject.getString("cpnyId").equals("78000000")) {
				
				recordList = this.setInfo78000000();
			} 
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export pa_detail_all_factory record report Exception. ", e);
		}
		resultCount = recordList.size();
		request.setAttribute("recordList", recordList); 
		request.setAttribute("resultCount", resultCount);                    
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);  
		request.setAttribute("update", 1);
		return "/evsea/evs_SearchReport0538.jsp";
	}
	
	private List setInfo78000000() {
		
		return service.retrieve780Report0538List(parameterObject);
	}
}

