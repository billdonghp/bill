/*
 * @(#)RetrievePresentConfirmListCmd.java 1.0 2009-7-17 下午03:14:04
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.washhouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-17 下午03:14:04
 * @version 1.0
 * 
 */
public class ExportWashReport extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.putToolbarInfo(request);
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List recordList = new ArrayList();
		
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			// 洗衣申请个人别统计
			if (parameterObject.getString("reportType").equals("washStatsByPerson")) {
							
				try {
					
					CommonDAO dao = new CommonDAO();
					parameterObject.setString("parentCode", "WashName");
					List<SimpleMap> codeList = dao.retrieveCodeList(parameterObject);
					String sql = makeSQL(codeList);
					parameterObject.setString("sql", sql);
					List result = services.getWashStatisticsByPerson(parameterObject);
					
					// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
					SimpleMap columns = new SimpleMap();
					columns.setString("申请人", "CHINESENAME");
					columns.setString("部门", "DEPT_NAME");
					columns.setString("申请日期", "CREATE_DATE");
					for (SimpleMap map : codeList) {
						
						columns.setString(map.getString("CODE_NAME"), map.getString("CODE_ID").toUpperCase());
					}
					columns.setString("价格", "TOTAL_PRICE");
					
					// 定义列类型
					 SimpleMap columnType = new SimpleMap();
					 columnType.setInt("CHINESENAME", ReportConstant.CELL_TYPE_TEXT);
					 
					// 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("wash_person_statistics.xls");
					paramBean.setSheetname("PERSON_STATISTICS");
					paramBean.setReportData(result);
					paramBean.setColumns(columns);
					paramBean.setColumnTypes(columnType);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

					// 设置内嵌表头
					paramBean.setInLineHeadContent("个人别洗衣统计表");
					paramBean.setInLineHeadMergeSize(columns.size());
					
					// make report
					ReportUtil.makeReport(request, paramBean); 
					
				} catch (Exception e) {
					
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("Export wash statistics by person Exception. ", e);
				}
			}
			
			// 洗衣申请部门别统计
			if (parameterObject.getString("reportType").equals("washStatsByDept")) {
				
				try {
					
					CommonDAO dao = new CommonDAO();
					parameterObject.setString("parentCode", "WashName");
					List<SimpleMap> codeList = dao.retrieveCodeList(parameterObject);
					String sql = makeSQL(codeList);
					parameterObject.setString("sql", sql);
					List result = services.getWashStatisticsByDept(parameterObject);
					
					// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
					SimpleMap columns = new SimpleMap();
					columns.setString("部门ID", "DEPTID");
					columns.setString("部门名称", "DEPT_NAME");
					for (SimpleMap map : codeList) {
						
						columns.setString(map.getString("CODE_NAME"), map.getString("CODE_ID").toUpperCase());
					}
					columns.setString("价格", "TOTAL_PRICE");
					
					// 定义列类型
					 SimpleMap columnType = new SimpleMap();
					 columnType.setInt("DEPTID", ReportConstant.CELL_TYPE_TEXT);
					 
					// 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("wash_dept_statistics.xls");
					paramBean.setSheetname("DEPT_STATISTICS");
					paramBean.setReportData(result);
					paramBean.setColumns(columns);
					paramBean.setColumnTypes(columnType);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

					// 设置内嵌表头
					paramBean.setInLineHeadContent("部门别洗衣统计表");
					paramBean.setInLineHeadMergeSize(columns.size());
					
					// make report
					ReportUtil.makeReport(request, paramBean); 
					
				} catch (Exception e) {
					
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("Export wash statistics by dept Exception. ", e);
				}
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Export wash report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
	
	/**
	 * make SQL
	 * 
	 * @param list
	 * @return
	 */
	private String makeSQL(List<SimpleMap> list) {
		
		StringBuffer str = new StringBuffer();
		
		for(SimpleMap map : list) {
			
			str.append("SUM(DECODE(F.WASH_ID,'");
			str.append(map.getString("CODE_ID"));
			str.append("',F.QUENTITY,0)) AS ");
			str.append(map.getString("CODE_ID"));
			str.append(",");
		}
		
		return str.toString();
	}

}

