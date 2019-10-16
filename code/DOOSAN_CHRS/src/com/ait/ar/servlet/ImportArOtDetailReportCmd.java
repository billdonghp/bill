/*
 * @(#)ImportVacationEmpReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ImportArOtDetailReportCmd extends ArAbstractCommand {
	
	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	
	private String flag = null;
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message;

		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();
		
		ArServices arServices;
	    flag = request.getParameter("flag");
	    System.out.print("sssssssssssssssssssssssssssssss"+request.getParameter("flag"));
		try {
			arServices = new ArServices();
			arServices.deleteTArOtDetail();
			
			
			
			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("AR_DATE_STR", ExcelBatchProcessor.CHAR);
			mapColumn.set("EMPID", ExcelBatchProcessor.CHAR);
			mapColumn.set("SHIFT_NO", ExcelBatchProcessor.CHAR);
			mapColumn.set("AR_ITEM_NO", ExcelBatchProcessor.CHAR);
			mapColumn.set("FROM_TIME", ExcelBatchProcessor.CHAR);
			mapColumn.set("TO_TIME", ExcelBatchProcessor.CHAR);
			mapColumn.set("FROM_TIME", ExcelBatchProcessor.CHAR);
			mapColumn.set("QUANTITY", ExcelBatchProcessor.NUMBER);
			mapColumn.set("DATE_TYPE", ExcelBatchProcessor.CHAR);
			mapColumn.set("AR_MONTH_STR", ExcelBatchProcessor.CHAR);
			
			
//			mapColumn.set("LIMIT_OT", ExcelBatchProcessor.NUMBER);
//			mapColumn.set("START_MONTH", ExcelBatchProcessor.CHAR);
//			mapColumn.set("END_MONTH", ExcelBatchProcessor.CHAR);
//			mapColumn.set("APPLY_LIMTIDTIME", ExcelBatchProcessor.CHAR);
			
			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			//appendColumn.set("PK_NO", "");
			appendColumn.set("LOCK_YN", "");
			appendColumn.set("UNIT", "");
			appendColumn.set("MOD_DATE", "");
			
			// 定义插入语句的子查询语句
			String sqlContent = " SELECT PERSON_ID, #LIMIT_OT#, #START_MONTH#, #END_MONTH#,#APPLY_LIMTIDTIME#, AR_OT_PLAN_SEQ.NEXTVAL, SYSDATE,'" + admin.getAdminID() + "'"
							    + " FROM HR_EMPLOYEE HR WHERE HR.ACTIVITY = 1 AND HR.PERSON_ID = get_personid(#EMPID#,'" + admin.getCpnyId() + "') AND EXISTS (SELECT AR.SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO AR "
							    + " WHERE AR.AR_SUPERVISOR_ID = '" + admin.getAdminID() + "' AND AR.SUPERVISED_DEPTID = HR.DEPTID) " ;

			String sqlContent1 = "SELECT to_char(to_date(#AR_DATE_STR#,'dd-mm-yyyy'),'yyyy/mm/dd'),(SELECT E.PERSON_ID FROM HR_EMPLOYEE E WHERE E.EMPID = #EMPID# AND E.CPNY_ID = "+admin.getCpnyId()+"),"
			                    + "(select t.Shift_No from ar_shift010 t WHERE T.SHIFT_NAME = #SHIFT_NO#),"
			                    + "(select T.ITEM_NO from ar_item t WHERE T.ITEM_NAME =  #AR_ITEM_NO#),"
			                    + "to_date(#FROM_TIME#,'mm-dd-yy HH24:MI:SS'), to_date(#TO_TIME#,'mm-dd-yy HH24:MI:SS'),#QUANTITY#,#DATE_TYPE#,#AR_MONTH_STR#,'Y','HOUR',SYSDATE from dual";
			
			// 导入表的关键字段
			//String pkStr = "EMPID,PLAN_MONTH";
			String pkStr = "PK_NO";
			// 添加参数
			parameters.set("tableName", "T_AR_OT_DETIAL");
			parameters.set("sheetName", "AR_OT_DETIAL_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("sqlContent", sqlContent1);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);
			
			if("next".equals(flag)){
				arServices.insertArOtDetail();
			}else{
				arServices.deleteArOtDetail();
				arServices.insertArOtDetail();
			}
			
			
			

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import AR_OT_DETIAL_DATA data fail. " + e.toString());
			throw new GlRuntimeException("Import AR_OT_DETIAL_DATA data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload","parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}

