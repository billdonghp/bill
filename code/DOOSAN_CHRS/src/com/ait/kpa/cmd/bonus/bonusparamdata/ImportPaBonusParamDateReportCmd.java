/*
 * @(#)ImportVacationEmpReportCmd.java 1.0 2007-10-7 下午02:02:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.bonus.bonusparamdata;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.kpa.servlet.PaAbstractCommand;
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
public class ImportPaBonusParamDateReportCmd extends PaAbstractCommand {

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
			String param_no = request.getParameter("param_no") ;
			
			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("FIELD1_VALUE", ExcelBatchProcessor.CHAR);
			mapColumn.set("RETURN_VALUE", ExcelBatchProcessor.CHAR);
			mapColumn.set("FIELD2_VALUE", ExcelBatchProcessor.CHAR);
			mapColumn.set("START_MONTH", ExcelBatchProcessor.CHAR);
			mapColumn.set("SD_ED_VALUE", ExcelBatchProcessor.CHAR);
			mapColumn.set("END_MONTH", ExcelBatchProcessor.CHAR);

//			 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			
			appendColumn.set("PARAM_DATA_NO", "");
			appendColumn.set("PARAM_NO", "");
			
			/*// 定义插入语句的子查询语句
			String sqlContent = " SELECT #FIELD1_VALUE#,#RETURN_VALUE#,T.CHINESENAME," 
							  + "#START_MONTH#,#SD_ED_VALUE#,#END_MONTH#,PA_BONUS_PARAM_DATA_SEQ.NEXTVAL," + param_no 
							  + " FROM HR_EMPLOYEE T WHERE T.EMPID = #FIELD1_VALUE# " ;
			*/
			// 定义插入语句的子查询语句
			String sqlContent = " SELECT get_emp_id(#FIELD1_VALUE#) FIELD1_VALUE ,#RETURN_VALUE#,T.CHINESENAME," 
							  + "#START_MONTH#,#SD_ED_VALUE#,#END_MONTH#,KPA_BONUS_PARAM_DATA_SEQ.NEXTVAL," + param_no 
							  + " FROM HR_EMPLOYEE T WHERE T.EMPID = #FIELD1_VALUE# " 
							  + " AND NOT EXISTS "
							  + "  ( SELECT D.PARAM_DATA_NO FROM KPA_BONUS_PARAM_DATA_I D "
							  + "     WHERE D.FIELD1_VALUE = #FIELD1_VALUE# AND D.PARAM_NO = " + param_no + " ) " ;;
			
			String pkStr = "PARAM_NO,FIELD1_VALUE";
			
			// 添加参数
			parameters.set("tableName", "KPA_BONUS_PARAM_DATA");
			parameters.set("sheetName", "PARAM_DATE");
			parameters.set("mappingColumn", mapColumn);
			parameters.set("appendColumn", appendColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);
			
			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import PA_PARAM_DATA data fail. " + e.toString());
			throw new GlRuntimeException("Import PA_PARAM_DATA data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload","parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}

