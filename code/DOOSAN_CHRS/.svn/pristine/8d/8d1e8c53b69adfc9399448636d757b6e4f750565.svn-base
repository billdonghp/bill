/*
 * @(#)ImportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.idcard;

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
public class ImportAttRecordReportCmd extends ArAbstractCommand {

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
			mapColumn.set("EMPID", ExcelBatchProcessor.CHAR);
			mapColumn.set("R_TIME", ExcelBatchProcessor.DATE);
			mapColumn.set("DOOR_TYPE", ExcelBatchProcessor.CHAR);
			mapColumn.set("REMARK", ExcelBatchProcessor.CHAR);
			
			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("RECORD_NO", "");
			appendColumn.set("CARD_NO", "");
			appendColumn.set("INSERT_BY", "");
			appendColumn.set("OPERATOR_ID", "");
			appendColumn.set("INSERT_TIME", "");
			appendColumn.set("ACTIVE", "");
			
			// 定义插入语句的子查询语句
			String sqlContent = " SELECT #EMPID#, #R_TIME#, #DOOR_TYPE#, " +
								"#REMARK#, AR_RECORDS_SEQ.NEXTVAL, B.CARD_NO, 'H', " +
								"'" + admin.getAdminID() + "', " + "SYSDATE, 1 " +
								" FROM AR_EMP_CARD B WHERE B.EMPID  = #EMPID# " + 
								" AND #R_TIME# BETWEEN B.FROM_DATE AND NVL(B.TO_DATE,TO_DATE('9999-12-30','YYYY-MM-DD'))+1 " +
								" AND B.ACTIVE = 1";

			// 导入表的关键字段
			String pkStr = "EMPID,R_TIME,DOOR_TYPE";

			// 添加参数
			// parameters.set("tableName", "I_AR_ANNUAL_HOLIDAY");
			parameters.set("tableName", "AR_MAC_RECORDS");
			parameters.set("sheetName", "ATT_RECORD_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import attendance record data fail. " + e.toString());
			throw new GlRuntimeException(
					"Import attendance record data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload",
				"parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}

