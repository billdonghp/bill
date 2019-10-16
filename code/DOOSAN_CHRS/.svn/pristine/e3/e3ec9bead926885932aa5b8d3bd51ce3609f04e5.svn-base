package com.ait.ga.cmd.asset;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-10
 * 
 */
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

public class ExportAssetReportCmd extends GaAbstractCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute(
				"admin"));
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId",admin.getCpnyId());

			// retrieve vacationEmp list
			List recordList = services.getAssetList(parameterObject);
			// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
			SimpleMap columns = new SimpleMap();
			// 资产类型 资产编号 名称 起案编号 品牌 规格 产品号 数量 单价 购买日期 保修期 购买厂家 固定资产编号 使用位置 使用部门
			// 管理担当 点检状态 点检周期 预点检日期 备注
			columns.setString("资产类型", "ASSET_TYPE_NAME");
			columns.setString("资产编号", "ASSET_NO");
			columns.setString("名称", "ASSET_NAME");
			columns.setString("起案编号", "FILE_NO");
			columns.setString("品牌", "BRAND");
			columns.setString("规格", "SPECIFIC");
			columns.setString("产品号", "PRODUCT_NO");
			columns.setString("数量", "QUANTITY");
			columns.setString("单价", "UNIT_PRICE");
			columns.setString("购买日期", "BUY_DATE");
			columns.setString("保修期", "WARRANTY");
			columns.setString("购买厂家", "VENDOR");
			columns.setString("固定资产编号", "CAPITAL_ASSET_NO");
			columns.setString("使用位置", "ASSET_POSITION");
			columns.setString("使用部门", "USE_DEPT_NAME");
			columns.setString("管理担当", "ASSET_KEEPER_NAME");
			columns.setString("预点检日期", "EXAMINE_DATE");
			columns.setString("备注", "REMARK");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("ASSET_NO", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("FILE_NO", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("CAPITAL_ASSET_NO", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("PRODUCT_NO", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("SPECIFIC", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("QUANTITY", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("UNIT_PRICE", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("BUY_DATE", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("EXAMINE_DATE", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("ASSET_REPORT_DATA.xls");
			paramBean.setSheetname("ASSET_DATA");
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
			 paramBean.setInLineHeadContent("资产记录信息表");
			 paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export vacationEmp report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
}
