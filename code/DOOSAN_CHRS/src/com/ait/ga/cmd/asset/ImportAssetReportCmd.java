package com.ait.ga.cmd.asset;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-10
 * 
 */
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

public class ImportAssetReportCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message;

		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {
		  
			// Excel列映射的数据库字段数组
			SimpleMap columns = new SimpleMap();
			columns.set("ASSET_TYPE", ExcelBatchProcessor.CHAR);
			columns.set("ASSET_NO", ExcelBatchProcessor.CHAR);
			columns.set("ASSET_NAME", ExcelBatchProcessor.CHAR);
			columns.set("FILE_NO", ExcelBatchProcessor.CHAR);
			columns.set("BRAND", ExcelBatchProcessor.CHAR);
			columns.set("SPECIFIC", ExcelBatchProcessor.CHAR);
			columns.set("PRODUCT_NO", ExcelBatchProcessor.CHAR);
			columns.set("QUANTITY", ExcelBatchProcessor.NUMBER);
			columns.set("UNIT_PRICE", ExcelBatchProcessor.NUMBER);
			columns.set("BUY_DATE", ExcelBatchProcessor.DATE);
			columns.set("WARRANTY", ExcelBatchProcessor.CHAR);
			columns.set("VENDOR", ExcelBatchProcessor.CHAR);
			columns.set("CAPITAL_ASSET_NO", ExcelBatchProcessor.CHAR);
			columns.set("ASSET_POSITION", ExcelBatchProcessor.CHAR);
			columns.set("USE_DEPT_ID", ExcelBatchProcessor.CHAR);
			columns.set("ASSET_KEEPER_ID", ExcelBatchProcessor.CHAR);
			columns.set("EXAMINE_DATE", ExcelBatchProcessor.DATE);
			columns.set("REMARK", ExcelBatchProcessor.CHAR);
			
			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("SEQ_NO", "");
			appendColumn.set("CREATE_DATE", "");
			appendColumn.set("CREATED_BY", "");
			appendColumn.set("ACTIVITY", "");
			appendColumn.set("CPNY_ID", "");

//			 定义插入语句的子查询语句
			String sqlContent = "SELECT get_code_id(#ASSET_TYPE#,'AssetType'),#ASSET_NO#,#ASSET_NAME#, "
									+" #FILE_NO#,#BRAND#,#SPECIFIC#,#PRODUCT_NO#,#QUANTITY#,#UNIT_PRICE#, "
									+" #BUY_DATE#,#WARRANTY#,#VENDOR#,#CAPITAL_ASSET_NO#, "
									+" #ASSET_POSITION#,#USE_DEPT_ID#,GET_PERSONID(#ASSET_KEEPER_ID#,"+admin.getCpnyId()+"), "
									+" #EXAMINE_DATE#,#REMARK#,SEQ_GA_ASSET.NEXTVAL,SYSDATE, "
									+"'" + admin.getAdminID() + "',1,"+"'" + admin.getCpnyId() + "' FROM DUAL";
			
			// 导入表的关键字段
			String pkStr = "ASSET_NO";
			
			Logger.getLogger(getClass()).debug("sql==================:"+sqlContent);

			// 添加参数
			parameters.set("tableName", "GA_ASSET");
			parameters.set("sheetName", "ASSET_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", columns);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters, ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Import asset record data fail. " + e.toString());
			throw new GlRuntimeException(
					"Import asset record data fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload",
				"parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}
}
