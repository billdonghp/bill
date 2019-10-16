package com.ait.ga.cmd.smock;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportSmockReportCmd extends GaAbstractCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = null;
		
		if(request.getParameter("reportType")=="smockRadioStatus"||request.getParameter("reportType").equals("smockRadioStatus")){
			
			url = this.reportSmock(request, response);
			
		}else if(request.getParameter("reportType")=="smockRadioStatus1"||request.getParameter("reportType").equals("smockRadioStatus1")){
			
			url = this.reportSmockInfo(request, response);
		}

		return url;
	}

	private String reportSmock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute(
				"admin");
		SimpleMap parameterObject;
		List recordList = null;

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("deptId", request
					.getParameter("smockDeptId"));
			parameterObject.setString("smockInfo", request
					.getParameter("smockInfo"));
			parameterObject.setString("provideStatus", request
					.getParameter("provideStatus"));

			List<SimpleMap> smockCodeName = services.getSmockCodeName(parameterObject);
			String sql = makeSql(smockCodeName);
			parameterObject.setString("sql", sql);

			if (request.getParameter("provideStatus") == "provideReportStat001"
					|| "provideReportStat001".equals(request
							.getParameter("provideStatus"))) {
				recordList = services.getSmockProvideRecord(parameterObject);
			} else {
				recordList = services.getSmockTotalRecord(parameterObject);
			}
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			columns.setString("部门名称", "DEPTNAME");
			columns.setString("工作服名称", "SMOCKNAME");
			for (SimpleMap map : smockCodeName) {
				columns.setString(map.getString("CODENAME"), map.getString(
						"CODEID").toUpperCase());
			}

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("DEPTID", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("DATESTARTED", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("EXPORT_SMOCK_DATA.xls");
			paramBean.setSheetname("EXPORT_SMOCK_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

			paramBean.setInLineHeadContent("部门别工作服统计表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export smock report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

	private String makeSql(List<SimpleMap> list) {

		StringBuffer str = new StringBuffer();
		for (SimpleMap map : list) {

			str.append("SUM(DECODE(B.MODEL_NUMBER,'");
			str.append(map.getString("CODEID"));
			str.append("',1,0)) ");
			str.append(map.getString("CODEID"));
			str.append(",");
		}

		return (str.toString());
	}
	
	private String reportSmockInfo(HttpServletRequest request, HttpServletResponse response)throws GlRuntimeException{
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List recordList = null;
		
		try{
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("deptId", request.getParameter("deptId"));

			recordList = services.getSmockPeriodReport(parameterObject);

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			columns.setString("职号", "EMPID");
			columns.setString("姓名", "CHINESENAME");
			columns.setString("部门", "FOURDEPTNAME");
			columns.setString("课组", "DEPTNAME");
			columns.setString("工作服名称", "SMOCK_NAME");
			columns.setString("型号", "MODEL_NUMBER");
			columns.setString("频率", "PERIOD");
			
			

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("DEPTID", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("DATESTARTED", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("EXPORT_SMOCK_DATA.xls");
			paramBean.setSheetname("EXPORT_SMOCK_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

			paramBean.setInLineHeadContent("部门别频率统计表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
			
			
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("report smock info data Exception .",e);
		}
		return "/inc/commonReport.jsp";
	}

}
