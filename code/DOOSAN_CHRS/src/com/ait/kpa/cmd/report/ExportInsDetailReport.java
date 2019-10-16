
/*
 * @(#)ExportPaDetailMonthCmd.java 1.0 2007-11-17 下午01:43:00
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
import com.ait.util.StringUtil;

public class ExportInsDetailReport extends PaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.pa.servlet.PaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaServices services = PaServices.getInstance();
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		SimpleMap map_dept ;
		SimpleMap map_section ;
		SimpleMap map_office ;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		List deptList = new ArrayList() ; //部一级的部门
		List deptList_section = new ArrayList() ; //科一级的部门
		List deptList_office = new ArrayList() ;  //办公室一级的部门
		
		// retrieve report pa detail month list
		List reportList = new ArrayList() ;
		List list = new ArrayList() ;
		List list_sum = new ArrayList() ;
		
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// 部一级的部门
			parameterObject.setInt("level", 2) ;
			deptList = services.retrievePaDetail_DeptList(parameterObject) ;
			if (deptList.isEmpty())
			{
				map_dept = new SimpleMap() ;
				map_dept.setString("DEPTID", parameterObject.getString("dept")) ;
				deptList.add(map_dept) ;
			}
		
			for(int i = 0 ; i < deptList.size() ; i ++)
			{
				map_dept = (SimpleMap)deptList.get(i) ;
				
				parameterObject.setInt("level", 3) ;
				parameterObject.setString("dept", map_dept.getString("DEPTID")) ; 
				deptList_section = services.retrievePaDetail_DeptList(parameterObject) ;
			
				//科一级的部门
				for(int j = 0 ; j < deptList_section.size() ; j ++)
				{
					map_section = (SimpleMap)deptList_section.get(j) ;
					parameterObject.setString("dept", map_section.getString("DEPTID")) ;
					deptList_office = services.retrievePaDetail_DeptList_section(parameterObject) ;
					
					//办公室一级的部门
					for(int k = 0 ; k < deptList_office.size() ; k ++)
					{
						map_office = (SimpleMap)deptList_office.get(k) ;
						parameterObject.setString("dept", map_office.getString("DEPTID")) ;	
						parameterObject.setString("dept_cn", map_office.getString("DEPTNAME") + " 小计") ;
						parameterObject.setString("dept_en", map_office.getString("DEPT_EN_NAME") + " TOTAL") ;
						
						if (map_office.getString("DEPT_LEVEL").equals("3"))
							parameterObject.setString("condition", "self") ;
						else
							parameterObject.setString("condition", "all") ;
							
						list = services.retrieveInsDetailMonth(parameterObject) ;				
						reportList.addAll(list) ;
						
						if (!list.isEmpty())
						{						
							list_sum = services.retrieveInsDetailMonth_SUM(parameterObject) ;
							reportList.addAll(list_sum) ;
						}
					}
					
					parameterObject.setString("dept", map_section.getString("DEPTID")) ;
					parameterObject.setString("dept_cn", map_section.getString("DEPTNAME")  + " 合计") ;
					parameterObject.setString("dept_en", map_section.getString("DEPT_EN_NAME") + " TOTAL") ;
					parameterObject.setString("condition", "all") ;
					list_sum = services.retrieveInsDetailMonth_SUM(parameterObject) ;
					
					if(!deptList_section.isEmpty())
					{
						map_section = (SimpleMap)list_sum.get(0) ;
						if(map_section.getString("INSURANCE_BASE") != null && map_section.getString("INSURANCE_BASE").length() > 0)
							reportList.addAll(list_sum) ;
					}
				}
				
				parameterObject.setString("dept", map_dept.getString("DEPTID")) ;
				parameterObject.setString("dept_cn", StringUtil.checkNull(map_dept.getString("DEPTNAME")) + " 合计") ;
				parameterObject.setString("dept_en", StringUtil.checkNull(map_dept.getString("DEPT_EN_NAME")) + " TOTAL") ;
				parameterObject.setString("condition", "all") ;
				list_sum = services.retrieveInsDetailMonth_SUM(parameterObject) ;
				reportList.addAll(list_sum) ;
			}
			
			parameterObject = ObjectBindUtil.getData(request);
			if (parameterObject.getString("dept") != null && parameterObject.getString("dept").equals("Z000000"))
			{
				parameterObject.setString("dept_cn", "总计") ;
				parameterObject.setString("dept_en", "TOTAL") ;
				parameterObject.setString("condition", "all") ;
				list_sum = services.retrieveInsDetailMonth_SUM(parameterObject) ;
				reportList.addAll(list_sum) ;
			}
			
			String fileName = "insdetail_month.xls";

			String sheetName = "insdetail_month";

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
		    /* 部门，职务，工号，姓名，员工类型，
		     * 保险类型，保险基数，
		     * 个人养老保险，个人医疗保险，个人失业保险，个人保险补扣,个人保险汇总，
		     * 公司养老保险，公司医疗保险，公司失业保险，公司工伤保险，公司生育保险，公司保险补扣,公司保险汇总，
		     * 保险合计
			*/ 
			 
			 columns.setString(messageSource.getMessage("report.global.title.deptName"),messageSource.getMessage("report.global.field.deptName")) ;
			 columns.setString(messageSource.getMessage("report.global.title.post"),messageSource.getMessage("report.global.field.post")) ;
			 columns.setString(messageSource.getMessage("report.global.title.empID"),"EMPID") ;
			 columns.setString(messageSource.getMessage("report.global.title.name"),messageSource.getMessage("report.global.field.name")) ;
			 columns.setString(messageSource.getMessage("report.att.view.monthly.title.join.type"),messageSource.getMessage("report.att.view.monthly.field.join.type")) ;
			 
			 columns.setString(messageSource.getMessage("report.global.title.insurance_type"),messageSource.getMessage("report.global.field.insurance_type")) ;
			 columns.setString(messageSource.getMessage("report.mutual.title.insurance_base"),"INSURANCE_BASE") ;

			 columns.setString(messageSource.getMessage("report.mutual.title.per_retire_ins"),"PER_RETIRE_INS") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.per_medicare_ins"),"PER_MEDICARE_INS") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.per_unemploy_ins"),"PER_UNEMPLOY_INS") ;
			 columns.setString("个人保险补扣","PER_INS_APPEND_DEDUCT") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.per_ins_total"),"PER_INS_TOTAL") ;
			 
			 columns.setString(messageSource.getMessage("report.mutual.title.cpy_retire_ins"),"CPY_RETIRE_INS") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.cpy_medicare_ins"),"CPY_MEDICARE_INS") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.cpy_unemploy_ins"),"CPY_UNEMPLOY_INS") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.cpy_work_injury_ins"),"CPY_WORK_INJURY_INS") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.cpy_childbirth_ins"),"CPY_CHILDBIRTH_INS") ;
			 columns.setString("公司保险补扣","CPY_INS_APPEND_DEDUCT") ;
			 columns.setString(messageSource.getMessage("report.mutual.title.cpy_ins_total"),"CPY_INS_TOTAL") ;
			 
			 columns.setString(messageSource.getMessage("report.mutual.title.instotal"),"INSTOTAL") ;
					 
//			 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("insdetail_month.xls");
			paramBean.setSheetname("insdetail_month");
			paramBean.setReportData(reportList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(columns.size() - 3);
			paramBean.setImageRow(reportList.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//社保保险明细单
			MessageSource message = new MessageSource("pay", admin.getLocale(), "UTF-8"); 
			paramBean.setInLineHeadContent(message.getMessage("display.pay.view.report.social_sec"));
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


