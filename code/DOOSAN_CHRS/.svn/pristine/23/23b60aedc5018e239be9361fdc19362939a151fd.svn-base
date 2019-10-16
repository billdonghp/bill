package com.ait.ga.cmd.inspection;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class ExportInspectionCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String type = request.getParameter("type");
		String url = null;
		
		if(type == "exportExcel"||"exportExcel".equals(type)){
			
			url = this.exportExcel(request, response);
			
		}else if(type=="downloadImportTemplate"||"downloadImportTemplate".equals(type)){
			
			url = this.downloadImportTemplate(request, response);
			
		}else if(type=="importExcel"||"importExcel".equals(type)){
			url = this.importExcel(request, response);
		}
		return url;
	}
	
	
	
	private String importExcel(HttpServletRequest request, HttpServletResponse response){
		String message;
		
		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {

			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("ASSET_NO", ExcelBatchProcessor.CHAR);
			mapColumn.set("DATA_TYPE", ExcelBatchProcessor.CHAR);
			mapColumn.set("INSPECTION_DATE", ExcelBatchProcessor.DATE);
			mapColumn.set("STATUS", ExcelBatchProcessor.CHAR);
			mapColumn.set("INSPECTION_EMPID", ExcelBatchProcessor.CHAR);
			mapColumn.set("PERIOD_NUM", ExcelBatchProcessor.NUMBER);
			mapColumn.set("PERIOD_UNIT", ExcelBatchProcessor.CHAR);
			
			
			mapColumn.set("MAINTAIN_VENDOR", ExcelBatchProcessor.CHAR);
			mapColumn.set("MAINTAIN_CONTENT", ExcelBatchProcessor.CHAR);
			mapColumn.set("WARRANTY_LENGTH", ExcelBatchProcessor.CHAR);
			mapColumn.set("MAINTAIN_COST", ExcelBatchProcessor.CHAR);
			mapColumn.set("REMARK", ExcelBatchProcessor.CHAR);
			mapColumn.set("UPDATED_BY", ExcelBatchProcessor.CHAR);
			mapColumn.set("UPDATE_DATE", ExcelBatchProcessor.DATE);
			
			

			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("SEQ_NO", "");
			appendColumn.set("CREATE_DATE", "");
			appendColumn.set("CREATED_BY", "");
			appendColumn.set("ACTIVITY", "");

			// 定义插入语句的子查询语句
			String sqlContent = "SELECT #ASSET_NO#,"+
								"(SELECT A.CODE_ID FROM SY_CODE A WHERE A.CODE_NAME = #DATA_TYPE#),"+
								"#INSPECTION_DATE#,"+
								"(SELECT B.CODE_ID FROM SY_CODE B WHERE B.CODE_NAME = #STATUS# AND B.PARENT_CODE = 'CheckStatus'),"+
								"(SELECT E.PERSON_ID FROM HR_EMPLOYEE E WHERE (E.EMPID = #INSPECTION_EMPID# OR E.CHINESENAME = #INSPECTION_EMPID#) AND E.CPNY_ID = "+admin.getCpnyId()+"),"+
								"#PERIOD_NUM#,"+
								"(SELECT C.CODE_ID FROM SY_CODE C WHERE C.CODE_NAME = #PERIOD_UNIT# AND C.PARENT_CODE = 'checkUnit'),"+
								"#MAINTAIN_VENDOR#, #MAINTAIN_CONTENT#, #WARRANTY_LENGTH#, #MAINTAIN_COST#, #REMARK#, "+
								"(SELECT D.PERSON_ID FROM HR_EMPLOYEE D WHERE (D.EMPID = #UPDATED_BY# OR D.CHINESENAME = #UPDATED_BY#) AND D.CPNY_ID = "+admin.getCpnyId()+")," +
								"#UPDATE_DATE#, "+
								"SEQ_GA_ASSET_INSPECTION.NEXTVAL, SYSDATE, "+admin.getAdminID()+", 1 FROM DUAL";

			// 导入表的关键字段
			String pkStr = "SEQ_NO";

			// 添加参数
			parameters.set("tableName", "GA_ASSET_INSPECTION");
			parameters.set("sheetName", "INSPECTION_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("sqlContent", sqlContent);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters,
					ExcelBatchProcessor.INVOLUTE);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Import INSPECTION record data fail. " + e.toString());
			throw new GlRuntimeException("Import smock relation record data fail.",
					e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload",
				"parent.opener.location.reload();parent.close()");
		
		
		return "/inc/alertMessage.jsp";
	
	}
	
	private String downloadImportTemplate(HttpServletRequest request, HttpServletResponse response){
		
		String fileName = "INSPECTION_Template.xls";

		String sheetName = "INSPECTION_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString("资产编号", "");
		columns.setString("类别", "");
		columns.setString("点检/维修日期", "");
		columns.setString("点检状态", "");
		columns.setString("点检人", "");
		columns.setString("点检周期", "");
		columns.setString("单位", "");
		columns.setString("维修厂家", "");
		columns.setString("维修内容", "");
		columns.setString("保质期", "");
		columns.setString("费用(元)", "");
		columns.setString("备注", "");
		columns.setString("确认人", "");
		columns.setString("确认日期", "");
		
		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
		
	}
	
	
	
	
	private String exportExcel(HttpServletRequest request, HttpServletResponse response){
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		GaServices services = new GaServices();
		SimpleMap parameterObject;
		
		try{
			//bind request parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			List recordList = services.getInspectionList(parameterObject);
			
			//create SQL and Excel mapped
			SimpleMap columns = new SimpleMap();
			columns.setString("资产编号", "ASSET_NO");
			columns.setString("名称", "ASSET_NAME");
			columns.setString("资产类型", "ASSET_TYPE");
			columns.setString("类别", "DATA_TYPE_NAME");
			columns.setString("点检/维修日期", "INSPECTION_DATE");
			columns.setString("点检状态", "STATUS_NAME");
			columns.setString("点检人", "INSPECTION_NAME");
			columns.setString("点检周期", "PERIOD");
			columns.setString("维修厂家", "MAINTAIN_VENDOR");
			columns.setString("维修内容", "MAINTAIN_CONTENT");
			columns.setString("保质期", "WARRANTY_LENGTH");
			columns.setString("费用", "MAINTAIN_COST");
			columns.setString("备注", "REMARK");
			columns.setString("确认人", "UPDATED_BY");
			columns.setString("确认日期", "UPDATE_DATE");
			
			//set Excel column type
			SimpleMap columnsType = new SimpleMap();
			columnsType.setInt("ASSET_NO", ReportConstant.CELL_TYPE_TEXT);
			columnsType.setInt("REMARK", ReportConstant.CELL_TYPE_TEXT);
			
			//set report forms parameter
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("EXPORT_INSPECTION_DATA.xls");
			paramBean.setSheetname("EXPORT_INSEPCTION_DATA");
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnsType);
			paramBean.setReportData(recordList);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setInLineHeadContent("点检/维修记录表");
			paramBean.setInLineHeadMergeSize(columns.size());
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
//			 make attendance record report
			ReportUtil.makeReport(request, paramBean);
			
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			new GlRuntimeException("export inspection data Exception.",e);
		}
		return "/inc/commonReport.jsp";
	}

}
