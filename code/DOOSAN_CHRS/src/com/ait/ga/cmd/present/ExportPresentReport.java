/*
 * @(#)RetrievePresentConfirmListCmd.java 1.0 2009-7-17 下午03:14:04
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

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
public class ExportPresentReport extends GaAbstractCommand {

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
			
			// 礼品数量统计
			if (parameterObject.getString("reportType").equals("presentQuentStat")) {
				
				try {
					
					List result = services.getPresentQuentStatistics(parameterObject);
					
					// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
					SimpleMap columns = new SimpleMap();
					columns.setString("礼品名称", "PRESENT_NAME");
					columns.setString("累计购买量", "IN_QUENTITY");
					columns.setString("累计使用数量", "OUT_QUENTITY");
					columns.setString("目前库存量", "QUENTITY");
					 
					// 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("present_quentity_statistics.xls");
					paramBean.setSheetname("PRESENT_STATISTICS");
					paramBean.setReportData(result);
					paramBean.setColumns(columns);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

					// 设置内嵌表头
					paramBean.setInLineHeadContent("礼品数量统计表");
					paramBean.setInLineHeadMergeSize(columns.size());
					
					// make report
					ReportUtil.makeReport(request, paramBean); 
					
				} catch (Exception e) {
					
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("Export present quentity statistics Exception. ", e);
				}
			}
			
			// 礼品发放统计
			if (parameterObject.getString("reportType").equals("presentSendStat")) {
							
				try {
					
					CommonDAO dao = new CommonDAO();
					parameterObject.setString("parentCode", "PresentName");
					List<SimpleMap> codeList = dao.retrieveCodeList(parameterObject);
					String sql = makeSQL(codeList);
					parameterObject.setString("sql", sql);
					List result = services.getPresentSendStatistics(parameterObject);
					
					// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
					SimpleMap columns = new SimpleMap();
					columns.setString("部门ID", "DEPT_ID");
					columns.setString("部门名称", "DEPT_NAME");
					for (SimpleMap map : codeList) {
						
						columns.setString(map.getString("CODE_NAME"), map.getString("CODE_ID").toUpperCase());
					}
					
					// 定义列类型
					 SimpleMap columnType = new SimpleMap();
					 columnType.setInt("DEPT_ID", ReportConstant.CELL_TYPE_TEXT);
					 
					// 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("present_send_statistics.xls");
					paramBean.setSheetname("SEND_STATISTICS");
					paramBean.setReportData(result);
					paramBean.setColumns(columns);
					paramBean.setColumnTypes(columnType);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

					// 设置内嵌表头
					paramBean.setInLineHeadContent("礼品发放统计表");
					paramBean.setInLineHeadMergeSize(columns.size());
					
					// make report
					ReportUtil.makeReport(request, paramBean); 
					
				} catch (Exception e) {
					
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("Export present send statistics Exception. ", e);
				}
			}
			
			// 礼品发放明细
			if (parameterObject.getString("reportType").equals("presentSendDetail")) {
				
				try {
					
					parameterObject.setString("ADMINID", admin.getAdminID());
					List result = services.getPresentSendDetail(parameterObject);
					
					// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
					SimpleMap columns = new SimpleMap();
					columns.setString("申请人职号", "EMPID");
					columns.setString("申请人姓名", "CHINESENAME");
					columns.setString("部门", "DEPTNAME");
					columns.setString("四级部门", "FOURTH_DEPT_NAME");
					columns.setString("申请日期", "APPLY_DATE");
					columns.setString("礼品名称", "PRESENT_NAME");
					columns.setString("数量", "QUENTITY");
					columns.setString("对口单位/个人", "PRESENT_OBJECT");
					columns.setString("申请原因", "REASON");
					columns.setString("使用日期", "USE_DATE");
					 
					// 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("present_send_detail.xls");
					paramBean.setSheetname("SEND_DETAIL");
					paramBean.setReportData(result);
					paramBean.setColumns(columns);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

					// 设置内嵌表头
					paramBean.setInLineHeadContent("礼品发放明细表");
					paramBean.setInLineHeadMergeSize(columns.size());
					
					// make report
					ReportUtil.makeReport(request, paramBean); 
					
				} catch (Exception e) {
					
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("Export present send detail Exception. ", e);
				}
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Export present report Exception. ", e);
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
			
			str.append("SUM(DECODE(B.PRESENT_ID,'");
			str.append(map.getString("CODE_ID"));
			str.append("',B.QUENTITY,0)) AS ");
			str.append(map.getString("CODE_ID"));
			str.append(",");
		}
		
		return str.toString();
	}

}

