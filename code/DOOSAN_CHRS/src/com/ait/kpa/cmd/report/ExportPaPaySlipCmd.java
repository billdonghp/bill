/*
 * @(#)ExportPaPaySlipCmd.java 1.0 2007-11-22 下午08:03:01
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

public class ExportPaPaySlipCmd extends PaAbstractCommand {

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
			
			// retrieve report pa social security list
			List reportDateList = services.retrievePaySlip(parameterObject);
			
			String fileName = "pay_slip.xls";

			String sheetName = "pay_slip";

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap() ;
			 
			 columns.setString("工资月","PA_MONTH") ;
			 columns.setString("部门","DEPARTMENT") ;
			 columns.setString("工号","EMPID") ;
			 columns.setString("姓名","CHINESENAME") ;
			 columns.setString("基本工资","BASE_PAY") ;
			 columns.setString("日工资","DAILY_WAGES") ;
			 columns.setString("浮动工资","VARIABLE_PAY") ;
			 
			 columns.setString("职务津贴","POST_ALLOWANCE") ;
			 columns.setString("其它津贴","OTHER_ALLOWANCE") ;//其它津贴 + 值班津贴 + 出差补助
			 columns.setString("加班小时","OVER_TIME") ;		//平日加班 + 周休加班 + 节假日加班
			 columns.setString("加班工资","OT_PAY_TOTAIL") ;
			 
			 columns.setString("考勤扣款","ABSENT_DEDUCT_TOTAL") ;
			 columns.setString("税前加项","BEFORE_TAXABLE_PLUS") ;
			 columns.setString("税前减项","BEFORE_TAXABLE_DEDUCT") ;
			 columns.setString("应发工资","SALARY_PAYABLE") ;
			 columns.setString("个人保险","PER_INS_TOTAL") ;
			 columns.setString("税前工资","PRE_TAX_PAY") ;
			 
			 columns.setString("计税工资","TAXABLE_PAY") ;
			 columns.setString("个税扣款","INCOME_TAX") ;
			 columns.setString("税后加项","AFTER_TAXABLE_PLUS") ;
			 columns.setString("税后减项","AFTER_TAXABLE_DEDUCT") ;
			 columns.setString("实发工资","NET_PAY") ;
			 
			 SimpleMap reportHead = new SimpleMap() ;
			 SimpleMap reportSpaces = new SimpleMap() ;
			 
			 List reportHeadList = new ArrayList() ;
			 reportHeadList.addAll(columns.keySet()) ;
			 
			 for (int i = 0 ; i < reportHeadList.size() ; i ++)
			 {
				 reportHead.set(columns.get(reportHeadList.get(i)), reportHeadList.get(i)) ;
				 reportSpaces.set(" ", reportHeadList.get(i)) ;
			 }
			 
			 List reportList = new ArrayList() ;
			 
			 for (int i = 0 ; i < reportDateList.size() ;i ++)
			 {
				 reportList.add(reportSpaces) ;
				 reportList.add(reportDateList.get(i)) ;
				 
				 if (i < reportDateList.size() - 1)
				 {
					 reportList.add(reportSpaces) ;
					 reportList.add(reportSpaces) ;
					 reportList.add(reportHead) ;				 
				 }
					 
			 }
			 
			 
//			 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("pay_slip.xls");
			paramBean.setSheetname("pay_slip");
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
			//日工资明细表
			MessageSource message = new MessageSource("pay", admin.getLocale(), "UTF-8"); 
			paramBean.setInLineHeadContent(message.getMessage("display.pay.view.report.slip"));
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export pay slip report data Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
}


