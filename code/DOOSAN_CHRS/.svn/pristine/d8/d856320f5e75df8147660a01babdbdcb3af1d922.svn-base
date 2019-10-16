

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

	public class ExportPayDetailReport extends PaAbstractCommand {

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
				List reportList = services.retrievePayDetail(parameterObject);
				
				String fileName = "paydetail_month.xls";

				String sheetName = "paydetail_month";

				// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap();
			    /*
			     * 银行卡号,姓名,应发工资,身份证号
			     */ 
				 columns.setString("帐号","CARD_NO") ;
				 columns.setString("姓名","CHINESENAME") ;
				 columns.setString("金额","NET_PAY") ;
				 columns.setString("身份证号","ID_CARD") ;
						 
//				 定义列类型
				 SimpleMap columnType = new SimpleMap();
				 columnType.setInt("ID_CARD", ReportConstant.CELL_TYPE_TEXT);
				 columnType.setInt("CARD_NO", ReportConstant.CELL_TYPE_TEXT);
				
//				 设置报表参数
				ExcelParameterBean paramBean = new ExcelParameterBean();
				paramBean.setFileName("paydetail_month.xls");
				paramBean.setSheetname("paydetail_month");
				paramBean.setReportData(reportList);
				paramBean.setColumns(columns);
				paramBean.setColumnTypes(columnType);
				paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
				// 设置页眉
				//paramBean.setHeadContent("LSFC考勤刷卡记录表");
				// 设置内嵌表
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



