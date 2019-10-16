/*
 * @(#)ExportVisitCardReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.visitcard;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午02:02:14
 * @version 1.0
 * 
 */
public class ExportVisitCardReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArServices services = new ArServices();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;

		HttpSession session1 = request.getSession(true);
		AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
		MessageSource messageSource = new MessageSource("report",admin1.getLocale(), "UTF-8");
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// retrieve visit card list
			List recordList = services.retrieveVisitCardList(parameterObject);

			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			 SimpleMap columns = new SimpleMap();
			//卡号  访问者名称 负责人 访问人数 开始日期  结束日期  创建时间  修改时间  备注
			 columns.setString(messageSource.getMessage("report.att.record.title.cardNo"), "CARD_NO");
			 columns.setString(messageSource.getMessage("report.att.record.card.title.name"), messageSource.getMessage("report.att.record.card.field.name"));
			 columns.setString(messageSource.getMessage("report.att.record.card.title.principal"), "PRINCIPAL");
			 columns.setString(messageSource.getMessage("report.att.record.card.title.visitor"), "VISITOR_AMONT");
			 columns.setString(messageSource.getMessage("report.global.title.start_date"), "FROM_DATE");  
			 columns.setString(messageSource.getMessage("report.global.title.end_date"), "TO_DATE");
			 columns.setString(messageSource.getMessage("report.global.title.creation_date"), "CREATE_DATE");
			 columns.setString(messageSource.getMessage("report.global.title.updateTime"), "UPDATE_DATE");
			 columns.setString(messageSource.getMessage("report.global.title.remark"), "REMARK");

			 // 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			 
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("att_visit_card_data.xls");
			paramBean.setSheetname("ATT_VISIT_CARD_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			paramBean.setImageCol(columns.size() - 2);
			paramBean.setImageRow(recordList.size()+ 5);
			paramBean.setImageHeight(2);
			paramBean.setImageWidth(4);
			paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//访问者信息表
			paramBean.setInLineHeadContent(messageSource.getMessage("report.att.record.card.title.tablename"));
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("export visit card relation report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

