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

public class ExportReport0535Cmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	private EvsReportService service = null;

	private AdminBean admin = null;

	private SimpleMap parameterObject = null;
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = new EvsReportService() ;
		admin = (AdminBean) request.getSession(false).getAttribute("admin");
        String  skilltypenum = request.getParameter("skilltypenum");
        String  processnum = request.getParameter("processnum");
        String  skilltype = request.getParameter("skilltype");
        String  process = request.getParameter("process");
        String  year = request.getParameter("year");
        String  yearEnd = request.getParameter("yearEnd");
        String  deptid = request.getParameter("deptid");
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("skilltypenum", skilltypenum);
			parameterObject.setString("processnum", processnum); 
			parameterObject.setString("skilltype", skilltype);
			parameterObject.setString("process", process); 
			parameterObject.setString("year", year);
			parameterObject.setString("yearEnd", yearEnd);
			parameterObject.setString("deptid", deptid);
			if(parameterObject.getString("distinction") != null && parameterObject.getString("distinction").length() > 0)
			{
				parameterObject.set("distinction", parameterObject.getString("distinction").split(",")) ;
			}
			

			// retrieve attendance record list
			List recordList = new ArrayList();

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 columns.setString("评价年", "EV_YEAR");
			 columns.setString("职号", "EV_EMPID");
			 columns.setString("姓名", "EV_EMPNAME");
			 columns.setString("部门", "EV_DEPT_NAME"); 
			 columns.setString("评价类型", "EV_TYPE_ID");
			 columns.setString("当前状态", "EV_PROCESS_NAME");
			 columns.setString("工种名称", "CRAFT");
			 columns.setString("技能类型", "SKILLTYPE");			 
			 columns.setString("技能Line", "LINE");
//			 columns.setString("机种名称", "AIRCRAFT");
			 columns.setString("工序名称", "PROCESS");
//			 columns.setString("作业内容", "JOBCONTENT");
			 //columns.setString("作业类型", "TYPEOPERATION");
			 columns.setString("职业资格", "QUALIFICATION"); 
			 columns.setString("技能积分", "SKILLSCORE"); 
			 columns.setString("技能等级", "SKILLLEVEL"); 
			 columns.setString("培训目标", "PURPOSE"); 
			if (parameterObject.getString("cpnyId").equals("78000000")) {
				
				recordList = this.setInfo78000000(request, response, columns);
				
			}/* else if (parameterObject.getString("cpnyId").equals("60000000")) {

				recordList = this.setInfo60000000(request, response, columns);
				
			} else if (parameterObject.getString("cpnyId").equals("63000000")) {

				recordList = this.setInfo63000000(request, response, columns);
				
			}*/
			
			// 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("evs_detail_factory.xls");
			paramBean.setSheetname("EVS_DETAIL_FACTORY_DATA");
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
			throw new GlRuntimeException("export pa_detail_all_factory record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
	
	private List setInfo78000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve780Report0535List(parameterObject);
	}
	
	/*private List setInfo60000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve600Report0212List(parameterObject);
	}
	
	private List setInfo63000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve630Report0212List(parameterObject);
	}*/
}

