/*
 * @(#)ImportVacationEmpReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.vacation_emp;

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
public class ImportVacationEmpReportCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message;

		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {
		  
			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("EMPID", ExcelBatchProcessor.CHAR);
			mapColumn.set("WORK_YEAR", ExcelBatchProcessor.NUMBER);
			mapColumn.set("VAC_TP", ExcelBatchProcessor.CHAR);
			mapColumn.set("VAC_ID", ExcelBatchProcessor.CHAR);
			mapColumn.set("STRT_DATE", ExcelBatchProcessor.DATE);
			mapColumn.set("END_DATE", ExcelBatchProcessor.DATE);
			mapColumn.set("TOT_VAC_CNT", ExcelBatchProcessor.NUMBER);
			
			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("VACATION_NO", "");
			appendColumn.set("CREATE_DATE", "");
			appendColumn.set("CREATED_BY", "");
			appendColumn.set("UPDATE_DATE", "");
			appendColumn.set("UPDATED_BY", "");
			appendColumn.set("ACTIVITY", "");
			
			// 定义插入语句的子查询语句
			String sqlContent = " SELECT HR.PERSON_ID,NVL(#WORK_YEAR# , 0), (SELECT  T.CODE_ID FROM SY_CODE T WHERE T.CODE_NAME = #VAC_TP# AND T.PARENT_CODE = 'VacationType'), #VAC_ID#,#STRT_DATE#,#END_DATE#,#TOT_VAC_CNT#," 
							    + "AR_VACATION_SEQ.NEXTVAL, SYSDATE,'" + admin.getAdminID() + "',SYSDATE,'" + admin.getAdminID() + "',1 "
							    + " FROM HR_EMPLOYEE HR WHERE HR.ACTIVITY = 1 AND HR.PERSON_ID = GET_PERSONID(#EMPID#,'" + admin.getCpnyId() + "') AND EXISTS (SELECT AR.SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO AR "
							    + " WHERE AR.AR_SUPERVISOR_ID = '" + admin.getAdminID() + "' AND AR.SUPERVISED_DEPTID = HR.DEPTID) " ;

			// 导入表的关键字段
			String pkStr = "VAC_ID,VAC_TP,EMPID";

			// 添加参数
			parameters.set("tableName", "AR_VAC_EMP");
			parameters.set("sheetName", "VACATION_EMP_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import vacationEmp data fail. " + e.toString());
			throw new GlRuntimeException("Import vacationEmp data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload","parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}

