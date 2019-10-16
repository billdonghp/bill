/*
 * @(#)ImportVacationEmpReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.overtimeplan;

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

public class ImportOTPlanReportCmd extends ArAbstractCommand {

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
			mapColumn.set("LIMIT_OT", ExcelBatchProcessor.NUMBER);
			mapColumn.set("START_MONTH", ExcelBatchProcessor.CHAR);
			mapColumn.set("END_MONTH", ExcelBatchProcessor.CHAR);
			mapColumn.set("APPLY_LIMTIDTIME", ExcelBatchProcessor.CHAR);
			
			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("OT_PLAN_NO", "");
			appendColumn.set("CREATE_DATE", "");
			appendColumn.set("CREATED_BY", "");
			
			// 定义插入语句的子查询语句
			String sqlContent = " SELECT PERSON_ID, #LIMIT_OT#, #START_MONTH#, #END_MONTH#,#APPLY_LIMTIDTIME#, AR_OT_PLAN_SEQ.NEXTVAL, SYSDATE,'" + admin.getAdminID() + "'"
							    + " FROM HR_EMPLOYEE HR WHERE HR.ACTIVITY = 1 AND HR.PERSON_ID = get_personid(#EMPID#,'" + admin.getCpnyId() + "') AND EXISTS (SELECT AR.SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO AR "
							    + " WHERE AR.AR_SUPERVISOR_ID = '" + admin.getAdminID() + "' AND AR.SUPERVISED_DEPTID = HR.DEPTID) " ;

			// 导入表的关键字段
			String pkStr = "EMPID,PLAN_MONTH";
			
			// 添加参数
			parameters.set("tableName", "AR_OVERTIME_PLAN");
			parameters.set("sheetName", "OTPLAN_EMP_DATE");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import AR_OVERTIME_PLAN data fail. " + e.toString());
			throw new GlRuntimeException("Import AR_OVERTIME_PLAN data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload","parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}

