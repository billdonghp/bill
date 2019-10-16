/*
 * @(#)ImportEateryRecordReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.eatery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-10-7 下午02:02:54
 * @version 1.0
 * 
 */
public class ImportEateryRecordReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message;

		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {
		  
			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("CARD_NO", ExcelBatchProcessor.CHAR);
			mapColumn.set("R_TIME", ExcelBatchProcessor.DATE);
			mapColumn.set("REPAST_TYPE", ExcelBatchProcessor.CHAR);
			mapColumn.set("REMARK", ExcelBatchProcessor.CHAR);
			
			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("RECORD_NO", "AR_EATERY_RECORDS_SEQ.NEXTVAL");
			appendColumn.set("EATERY_DATA_TYPE", "'Consumption'");
			appendColumn.set("INSERT_BY", "'H'");
			appendColumn.set("OPERATOR_ID", "'" + admin.getAdminID() + "'");
			appendColumn.set("INSERT_TIME", "SYSDATE");
			appendColumn.set("ACTIVE", "1");

			// 导入表的关键字段
			String pkStr = "CARD_NO,R_TIME,REPAST_TYPE";

			// 添加参数
			parameters.set("tableName", "AR_EATERY_RECORDS");
			parameters.set("sheetName", "ATT_EATERY_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.SINGLE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import eatery record data fail. " + e.toString());
			throw new GlRuntimeException(
					"Import eatery record data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload",
				"parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}

