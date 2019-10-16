/*
 * @(#)ExportEateryRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.eatery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午02:02:14
 * @version 1.0
 * 
 */
public class ExportEateryRecordReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArServices services = new ArServices();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			String CARD_TYPE = StringUtil.checkNull(parameterObject.getString("CARD_TYPE")) ;
			if (CARD_TYPE.equals("CardType05")){
				parameterObject.setString("CardType05", "CardType05") ;
			}else if(CARD_TYPE.equals("CardType10")) {
				parameterObject.setString("CardType10", "CardType10") ;
			}  
			// retrieve eatery record list
			List recordList = services.retrieveEateryRecordList(parameterObject);

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			 //工号 姓名/访问公司 部门 卡号 时间 类型 来源 操作者 修改时间 备注
			 columns.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
			 columns.setString(messageSource.getMessage("report.att.eat.title.name"), 
					 messageSource.getMessage("report.att.eat.field.name"));
			 columns.setString(messageSource.getMessage("report.global.title.deptName"), 
					 messageSource.getMessage("report.global.field.deptName"));
			 columns.setString(messageSource.getMessage("report.att.record.title.cardNo"), "CARD_NO");
			 columns.setString(messageSource.getMessage("report.att.eat.title.eat_time"), "R_TIME");
			 columns.setString(messageSource.getMessage("report.att.eat.title.eat_type"), 
					 messageSource.getMessage("report.att.eat.field.eat_type"));  
			 columns.setString(messageSource.getMessage("report.att.record.title.source"), 
					 messageSource.getMessage("report.att.record.field.source"));
			 columns.setString(messageSource.getMessage("report.global.title.operator"), 
					 messageSource.getMessage("report.global.field.operator"));
			 columns.setString(messageSource.getMessage("report.global.title.updateTime"), "INSERT_TIME");
			 columns.setString(messageSource.getMessage("report.global.title.remark"), "REMARK");

			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 columnType.setInt("CARD_NO", ReportConstant.CELL_TYPE_TEXT);
			 
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("att_eatery_data.xls");
			paramBean.setSheetname("ATT_EATERY_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(columns.size() - 4);
			paramBean.setImageRow(recordList.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//LSFC就餐刷卡记录表
			paramBean.setInLineHeadContent(messageSource.getMessage("report.att.eat.title.tablename"));
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 
			 

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export eatery record report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

