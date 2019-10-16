/*
 * @(#)TipMessageReportCmd.java 1.0 2007-9-18 下午12:06:37
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.tipmessagesearch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;   
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;       
                                                   

public class TipMessageReportCmd extends HrmAbstractCommand{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                             
		HrmServices services = HrmServices.getInstance();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		String url="";
		SimpleMap map=new SimpleMap();
		String deptid=request.getParameter("DEPTID")!=null?request.getParameter("DEPTID"):"1";
		String sDate=request.getParameter("sDate");
		String eDate=request.getParameter("eDate");
		String key=StringUtil.toCN(request.getParameter("EMPIDORCHINESENAME"));
		map.put("deptid",deptid);
		map.put("sDate",sDate);  
		map.put("eDate",eDate);
		map.put("key",key);
	  String gowhere=request.getParameter("gowhere");
	  if(gowhere.equals("phsical"))      
	  {
			String fileName = "hrm_hrealthinfo_data.xls";
			String sheetName = "hralthinfo";
		   List listdata=services.getHealthTipMessageByConditon(map);
		   SimpleMap colsname =new SimpleMap();
//		   colsname.setString("工号", "EMPID");
//		   colsname.setString("姓名", "CHINESENAME");
//		   colsname.setString("部门", "DEPTNAME");
//		   colsname.setString("职位", "POSITION");
//		   colsname.setString("岗位职务", "POST");
//		   colsname.setString("体检日期", "PHYSICAL_DATE");  
//		   colsname.setString("截止有效日期", "VALID_PHYSICAL_DATE");
		   
		   colsname.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
		   colsname.setString(messageSource.getMessage("report.global.title.name"), 
					 messageSource.getMessage("report.global.field.name"));
		   colsname.setString(messageSource.getMessage("report.global.title.deptName"), 
					 messageSource.getMessage("report.global.field.deptName"));
		   colsname.setString(messageSource.getMessage("report.global.title.position"), 
					 messageSource.getMessage("report.global.field.position"));
		   colsname.setString(messageSource.getMessage("report.global.title.post"), 
					 messageSource.getMessage("report.global.field.post"));
		   colsname.setString(messageSource.getMessage("report.hrm.health.title.physical_exam_date"), "PHYSICAL_DATE");
		   colsname.setString(messageSource.getMessage("report.hrm.health.title.expiration_date"), "VALID_PHYSICAL_DATE");
		   
		   SimpleMap columnType = new SimpleMap();
		   columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
		   
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("hrm_hrealthinfo_data.xls");
			paramBean.setSheetname("hralthinfo");
			paramBean.setReportData(listdata);
			paramBean.setColumns(colsname);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(colsname.size() - 4);
			paramBean.setImageRow(listdata.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//健康信息表
			paramBean.setInLineHeadContent(messageSource.getMessage("report.hrm.health.title.tablename"));
			paramBean.setInLineHeadMergeSize(colsname.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		   url="/hrm/healthreport.jsp";           
	  }
	  
	  if(gowhere.equals("exporedprobat"))      
	  {
			String fileName = "hrm_exporedprobat_data.xls";
			String sheetName = "exporedprobat";
		   List listdata=services.getExpiredProbationByCondition(map);
		   SimpleMap colsname =new SimpleMap();
//		   colsname.setString("工号", "EMPID");
//		   colsname.setString("姓名", "CHINESENAME");
//		   colsname.setString("部门", "DEPTNAME");
//		   colsname.setString("职位", "POSITION");
//		   colsname.setString("岗位职务", "POST");
//		   colsname.setString("预转正日期", "END_PROBATION_DATE"); 
		   
		   colsname.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
		   colsname.setString(messageSource.getMessage("report.hrm.probation.title.name"), 
					 messageSource.getMessage("report.hrm.probation.field.name"));
		   colsname.setString(messageSource.getMessage("report.global.title.deptName"), 
					 messageSource.getMessage("report.global.field.deptName"));
		   colsname.setString(messageSource.getMessage("report.global.title.position"), 
					 messageSource.getMessage("report.global.field.position"));
		   colsname.setString(messageSource.getMessage("report.global.title.post"), 
					 messageSource.getMessage("report.global.field.post"));
		   colsname.setString(messageSource.getMessage("report.hrm.probation.title.off_probation"), "END_PROBATION_DATE");
		   SimpleMap columnType = new SimpleMap();
		   columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);  
		   
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("hrm_exporedprobat_data.xls");
			paramBean.setSheetname("exporedprobat");
			paramBean.setReportData(listdata);
			paramBean.setColumns(colsname);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(colsname.size() - 4);
			paramBean.setImageRow(listdata.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//预转正信息表		
			MessageSource message = new MessageSource("hrm",admin.getLocale(), "UTF-8"); 
			paramBean.setInLineHeadContent(message.getMessage("display.emp.statistics.off_probation_info"));
			paramBean.setInLineHeadMergeSize(colsname.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		   url="/hrm/viewexpiredprobationreport.jsp";   
	  }
	  
	  if(gowhere.equals("upgrade"))         
	  {
		  String empSort=request.getParameter("joinTypeCode");
			map.put("empSort",empSort);   

		   List listdata=services.getUpgradeInfoFieldByCondition(map);   
		   SimpleMap colsname =new SimpleMap();
//		   colsname.setString("工号", "EMPID");
//		   colsname.setString("姓名", "NAME");
//		   colsname.setString("部门", "DEPT");
//		   colsname.setString("职位", "POSITION");
//		   colsname.setString("岗位职务", "POST");         
//		   colsname.setString("人事号", "TRANSNO");  
//		   colsname.setString("人事令内容", "CONTENT");
			
		   colsname.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
		   colsname.setString(messageSource.getMessage("report.hrm.probation.title.name"), 
					 messageSource.getMessage("report.hrm.probation.field.name"));
		   colsname.setString(messageSource.getMessage("report.hrm.upgradeinfor.title.deptName"), 
					 messageSource.getMessage("report.hrm.upgradeinfor.field.deptName"));
		   colsname.setString(messageSource.getMessage("report.hrm.upgradeinfor.title.position"), 
					 messageSource.getMessage("report.hrm.upgradeinfor.field.position"));
		   colsname.setString(messageSource.getMessage("report.hrm.upgradeinfor.title.post"), 
					 messageSource.getMessage("report.hrm.upgradeinfor.field.post"));
		   colsname.setString(messageSource.getMessage("report.hrm.upgradeinfor.title.regulation_no"), "TRANSNO");
		   colsname.setString(messageSource.getMessage("report.hrm.upgradeinfor.title.contents"), "CONTENT");
		   
		   SimpleMap columnType = new SimpleMap();               
		   columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);  
			
		    String fileName = "hrm_upgrade_data.xls";
			String sheetName = "upgrade";
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("hrm_upgrade_data.xls");
			paramBean.setSheetname("upgrade");
			paramBean.setReportData(listdata);
			paramBean.setColumns(colsname);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(colsname.size() - 1);
			paramBean.setImageRow(listdata.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//发令信息表 
			MessageSource message = new MessageSource(admin.getLocale(), "UTF-8"); 
			paramBean.setInLineHeadContent(message.getMessage("display.mutual.regulation_info"));
			paramBean.setInLineHeadMergeSize(colsname.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 
		   
		   url="/hrm/hrm_upgradeinforeport.jsp";
	  }
	  
		
		return url;  
	} 
	
	
	
}