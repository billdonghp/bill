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

public class ExportReport0220Cmd extends ArAbstractCommand {

	private PaReportService service = null;

	private AdminBean admin = null;

	private SimpleMap parameterObject = null;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service = new PaReportService() ;
		admin = (AdminBean) request.getSession(false).getAttribute("admin");

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			// retrieve attendance record list
			List recordList = new ArrayList() ;

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();			
			 columns.setString("职号", "EMPID");
			 columns.setString("姓名", "CHINESENAME");
			 
			 columns.setString("基本工资", "BASE_PAY");
			 columns.setString("职务津贴", "DUTIES_ALLOWANCE");
			 columns.setString("住宅补助", "RESIDENTIAL_GRANTS");
			 columns.setString("其他补偿", "OTHER_COMPENSATION");
			 columns.setString("地区补助", "REGIONAL_GRANTS");
			 columns.setString("特殊补助", "SPECIAL_GRANTS");
			 
			 if (parameterObject.getString("cpnyId").equals("60000000")) {
					
				 	columns.setString("绩效工资", "PERFORMANCE_PAY");
			}
			 
			 columns.setString("基本合计", "TOTAL_BASIC");
			 
			 columns.setString("其他支给", "TO_THE_OTHER");
			 columns.setString("支给错误", "STICKS_TO_THE_WRONG");
			 columns.setString("迟早减除", "LATE_EARLY_MINUS"); /////
			 columns.setString("未勤减除", "NOT_ATTENDANCE_MINUS");
			 columns.setString("事病减除", "LEAVE_SICK_MINUS"); ///
			 columns.setString("休假减除", "MATERNITY_MINUS");///
			 columns.setString("休职减除", "LEVEL_OFF_MINUS");///
			 columns.setString("旷工减除", "ABSENTEEISM_MINUS");
			 columns.setString("其他减除", "OTHER_LESS");
			 columns.setString("住宅减除", "RESIDENTIAL_MINUS");
			 columns.setString("减除错误", "REDUCE_ERRORS");
			 columns.setString("放假减除", "HOLIDAY_MINUS_ALL");
			 
			 if (parameterObject.getString("cpnyId").equals("60000000")) {
					
			 	columns.setString("工伤减除", "INDUSTRY_INJURY_MINUS");
			 }
			 
			 columns.setString("试用扣除", "TRIAL_MINUS");
			 columns.setString("平均工资", "THIS_TOTAL_SUPPORT");
			 
			 columns.setString("所得税", "THIS_ACTUAL_TAX");
			 columns.setString("管理费", "HANDLING_CHARGE");
			 columns.setString("公司负担", "PAYMENT_OF_EMPLOYER");
			 columns.setString("小计", "XIAOJI");
			 columns.setString("个人负担", "PAYMENT_OF_INDIVIDUAL");
			 columns.setString("国企合计", "GUOQIHEJI");
			 columns.setString("实领工资", "THIS_ACTUAL_WAGE");
			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			
			if (parameterObject.getString("cpnyId").equals("78000000")) {
				
				recordList = this.setInfo78000000(request, response, columns);
				
			} else if (parameterObject.getString("cpnyId").equals("63000000")) {

				recordList = this.setInfo63000000(request, response, columns);
				
			} else if (parameterObject.getString("cpnyId").equals("60000000")) {

				recordList = this.setInfo60000000(request, response, columns);
				
			} 
			
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pa_detail_filiale_average.xls");
			paramBean.setSheetname("PA_DETAIL_FILIALE_DATA");
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
			throw new GlRuntimeException("export pa_detail_filiale_average record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
	
	private List setInfo78000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve780Report0220List(parameterObject);
		
		
	}
	
	
	private List setInfo63000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve630Report0220List(parameterObject);
		
		
	}
	
	private List setInfo60000000(HttpServletRequest request, HttpServletResponse response, SimpleMap columns) {
		
		return service.retrieve600Report0220List(parameterObject);
		
		
	}

}
