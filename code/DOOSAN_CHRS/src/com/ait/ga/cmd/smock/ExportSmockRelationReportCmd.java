package com.ait.ga.cmd.smock;

import java.io.IOException;
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
import com.ait.sy.bean.AdminBean;

public class ExportSmockRelationReportCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute(
				"admin")) ;
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getCpnyId());
			parameterObject.setString("cpnyId",admin.getCpnyId());
			

			// retrieve vacationEmp list
			List recordList = services.selectSmockRelationList(parameterObject);
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			
			columns.setString("职号","EMPID");
			columns.setString("姓名", "CHINESENAME");
			columns.setString("性别", "SEX");
			columns.setString("课组","DEPTNAME");
			columns.setString("部门", "FOURTHIDEPTNAME");
			columns.setString("入社时间", "DATESTARTED");
			columns.setString("名称", "SMOCKNAME");
			columns.setString("型号", "MODELNUMBER");
			columns.setString("频率", "PERIOD");
			columns.setString("备注", "REMARK");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("DATESTARTED", ReportConstant.CELL_TYPE_TEXT);
			
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("SMOCKRELATION_REPORT_DATA.xls");
			paramBean.setSheetname("SMOCKRELATION_DATA");
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
//			paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			 paramBean.setInLineHeadContent("员工工作服信息表");
			 paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export smock relation report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}

}
