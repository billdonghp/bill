package com.ait.ga.cmd.smock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ImportSmockRelationReportCmd extends GaAbstractCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String message;

		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {

			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("EMPID", ExcelBatchProcessor.CHAR);
			mapColumn.set("SMOCK_NO", ExcelBatchProcessor.CHAR);
			mapColumn.set("MODEL_NUMBER", ExcelBatchProcessor.CHAR);
			mapColumn.set("PERIOD_NUM", ExcelBatchProcessor.NUMBER);
			mapColumn.set("PERIOD_UNIT", ExcelBatchProcessor.CHAR);
			mapColumn.set("REMARK", ExcelBatchProcessor.CHAR);

			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("SEQ_NO", "");
			appendColumn.set("CREATE_DATE", "");
			appendColumn.set("CREATED_BY", "");
			appendColumn.set("ACTIVITY", "");
			appendColumn.set("CPNY_ID", "");

			// 定义插入语句的子查询语句
			String sqlContent = "SELECT A.PERSON_ID,B.SEQ_NO,D.CODE_ID,#PERIOD_NUM#,E.CODE_ID,#REMARK#,SEQ_GA_SMOCK_RELATION.NEXTVAL,SYSDATE,"+
					"'"+admin.getAdminID()+"',1,"+
					"'"+admin.getCpnyId()+"'"+
					"FROM HR_EMPLOYEE A,GA_SMOCK B,SY_CODE D,SY_CODE E "+
					"WHERE A.EMPID = #EMPID# AND A.ACTIVITY = 1 "+
					"AND   GET_CODE_NAME(B.SMOCK_NAME) = #SMOCK_NO# "+
					"AND   D.CODE_NAME = #MODEL_NUMBER# "+
					"AND   E.CODE_NAME = #PERIOD_UNIT# "+
					"AND E.PARENT_CODE = 'smockPeriodNum' "+
					"AND D.PARENT_CODE IN ('smockSize1','smockSize2','smockSize3') AND A.CPNY_ID ="+"'"+admin.getCpnyId()+"'";

			// 导入表的关键字段
			String pkStr = "SEQ_NO";

			// 添加参数
			parameters.set("tableName", "GA_SMOCK_RELATION");
			parameters.set("sheetName", "SMOCKRELATION_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters,
					ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Import attendance record data fail. " + e.toString());
			throw new GlRuntimeException("Import smock relation record data fail.",
					e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload",
				"parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

}
