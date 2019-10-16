/*
 * @(#)RetrievePresentApplyInfoCmd.java 1.0 2009-7-16 上午10:03:25
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

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-16 上午10:03:25
 * @version 1.0
 * 
 */
public class RetrievePresentApplyExcelCmd extends GaAbstractCommand {

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
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("applicant", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("applicant", "");
			parameterObject.set("ADMINID", admin.getAdminID());
			}
		}
			
			// retrieve apply list
			List<SimpleMap> applyList = services.retrievePresentApplyList(parameterObject, currentPage, 10000);
			Object applyCount = services.retrievePresentApplyCnt(parameterObject);
			
			List<SimpleMap> detailList;
			List affirmList;
			for (SimpleMap applyInfo : applyList) {
				
				parameterObject.setString("applyNo", applyInfo.getString("SEQ_NO"));
				// retrieve detail list
				detailList = services.retrievePresentApplyDetail(parameterObject);
				// retrieve affirm list
				affirmList = services.retrievePresentAffirmList(parameterObject);
				
				String detail="";
				for (SimpleMap detaillist : detailList) {
					detail= "对口单位/个人："+detaillist.getString("PRESENT_OBJECT") 
					        + "  名称："+detaillist.getString("PRESENT_NAME")
					        + "  数量："+detaillist.getString("QUENTITY")+detaillist.getString("UNIT_NAME")
					        + "  单价："+detaillist.getString("UNIT_PRICE")+"&nbsp;元"
					        + "  价格："+detaillist.getString("AMOUNT")+"&nbsp;元";
				}
				applyInfo.set("detailList", detail);
				applyInfo.set("affirmList", affirmList);
				recordList.add(applyInfo);
			}

			
			SimpleMap columns = new SimpleMap();
			columns.setString("职号", "EMPID");
			columns.setString("姓名", "CHINESENAME");
			columns.setString("部门", "DEPT_NAME");
			columns.setString("申请内容", "detailList");
			columns.setString("赠送原因", "REASON");
			columns.setString("备注", "REMARK");
			columns.setString("使用日期", "USE_DATE");
			columns.setString("申请日期", "CREATE_DATE");
			//columns.setString("决裁情况", "peopleClassup");
			//columns.setString("确认情况", "bookNum");

			
			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("CREATE_DATE", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("USE_DATE", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("DEPT_NAME", ReportConstant.CELL_TYPE_TEXT);
	
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("APPLY_PRESENT_DATA.xls");
			paramBean.setSheetname("PRESENT_DATA");
			paramBean.setReportData(recordList);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("礼品信息");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
	
			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve present apply information Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}

