
/*
 * @(#)ExportPaDetailMonthCmd.java 1.0 2007-11-17 下午01:43:00
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.report;

import java.io.File;
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
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportPersonalTaxMonthlyReport extends PaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.pa.servlet.PaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaServices services = PaServices.getInstance();
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			// retrieve report pa detail month list
			List reportList = services.retrievePersonalTaxMonthly(parameterObject);
			
			String fileName = "PersonalTaxMonthly.xls";

			String sheetName = "PersonalTaxMonthly";

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			/*
			 * 工资月，工号，姓名，部门，职务，国家
			 * 应发工资，税前工资，扣税基数，计税工资
			 * 税率，速算扣除数，所得税，税后工资，实发工资
			 */
			 SimpleMap columns = new SimpleMap();
			 MessageSource meg = new MessageSource(admin.getLocale(), "UTF-8"); 
			 columns.setString(meg.getMessage("display.mutual.pay_month"),"PA_MONTH") ; 
			 columns.setString(messageSource.getMessage("report.global.title.empID"),"EMPID") ;
			 columns.setString(messageSource.getMessage("report.global.title.name"),messageSource.getMessage("report.global.field.name")) ;
			 columns.setString(messageSource.getMessage("report.global.title.deptName"),messageSource.getMessage("report.global.field.deptName")) ;
			 columns.setString(messageSource.getMessage("report.global.title.post"),messageSource.getMessage("report.global.field.post")) ;			 
			 columns.setString(meg.getMessage("display.mutual.country"),messageSource.getMessage("report.mutual.field.nationality")) ;
			 
			 columns.setString("应发工资","SALARY_PAYABLE") ;
			 columns.setString("税前工资","PRE_TAX_PAY") ;
			 columns.setString("扣税基数","TAX_BENCHMARK") ;
			 columns.setString("计税工资","TAXABLE_PAY") ;
			 columns.setString("税率","TAX_RATIO") ;
			 columns.setString("速算扣除数","ALGORITHM_MEDUCT") ;
			 columns.setString("所得税","INCOME_TAX") ;
			 columns.setString("税后工资","AFTER_TAX_PAY") ;
			 columns.setString("实发工资","NET_PAY") ;
			 
			                
//			 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("PersonalTaxMonthly.xls");
			paramBean.setSheetname("PersonalTaxMonthly");
			paramBean.setReportData(reportList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(columns.size() - 4);
			paramBean.setImageRow(reportList.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//个人所得税月报表
			MessageSource message = new MessageSource("pay", admin.getLocale(), "UTF-8"); 
			paramBean.setInLineHeadContent(message.getMessage("display.pay.view.report.income"));
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export pa detail month report data Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
}



