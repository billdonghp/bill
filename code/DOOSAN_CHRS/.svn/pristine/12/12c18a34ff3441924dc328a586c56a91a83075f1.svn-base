package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;
import java.util.Iterator;
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

public class ExportFestivalPresentReportCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List recordList = null;

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			if(request.getParameter("reportType") == "schemeStatus" || request.getParameter("reportType").equals("schemeStatus")){
				
				recordList = services.getFestivalPresentSchemeReportList(parameterObject);
				
				List recordDetailList = services.selectFestivalSchemeDetaiList(null);
				
				request.setAttribute("recordList", recordList);
				request.setAttribute("recordDetailList", recordDetailList);
				
				return "/ga_export_present_scheme.jsp";
				
			}else if (request.getParameter("reportType") == "presentProvideStatus" || request.getParameter("reportType").equals("presentProvideStatus")) {
				
				recordList = services.getFestivalPresentProvideReportList(parameterObject);
				
				Iterator iterator = recordList.iterator();
				float totalPrice = 0;
				SimpleMap map = new SimpleMap();
				for (int i = 0;iterator.hasNext(); i++) {
					SimpleMap simpleMap =  (SimpleMap)iterator.next();
					totalPrice += Float.parseFloat(simpleMap.getString("TOTALSUM"));
				}
				//保留两位小数
				totalPrice = (float)(Math.round((double)totalPrice*100)/100.00);
				map.setString("SEQ_NO","合计");
				map.setString("TOTALSUM",Float.toString(totalPrice));
				recordList.add(map);
				
				// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				SimpleMap columns = new SimpleMap();
				columns.setString("序号", "SEQ_NO");
				columns.setString("礼品名称", "PRESENT_NAME");
				columns.setString("品牌", "BRAND");
				columns.setString("规格", "SPECIFIC");
				columns.setString("单位", "UNIT");
				columns.setString("数量", "QUENTITY");
				columns.setString("单价", "UNIT_PRICE");
				columns.setString("总价", "TOTALSUM");
		
				// 定义列类型
				SimpleMap columnType = new SimpleMap();
				columnType.setInt("UNIT_PRICE", ReportConstant.CELL_TYPE_TEXT);
				columnType.setInt("QUANTITY", ReportConstant.CELL_TYPE_TEXT);
				columnType.setInt("TOTALSUM", ReportConstant.CELL_TYPE_TEXT);

				// 设置报表参数
				ExcelParameterBean paramBean = new ExcelParameterBean();
				paramBean.setFileName("EXPORT_PRESENT_PROVIDE_DATA.xls");
				paramBean.setSheetname("EXPORT_PRESENT_PROVIDE_DATA");
				paramBean.setReportData(recordList);
				paramBean.setColumns(columns);
				paramBean.setColumnTypes(columnType);
				paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);

				paramBean.setInLineHeadContent("节日礼品发放明细统计表");
				paramBean.setInLineHeadMergeSize(columns.size());
				// 设置EXCEL表头的显示方向
				paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

				// make attendance record report
				ReportUtil.makeReport(request, paramBean);
				
			}else if (request.getParameter("reportType") == "presentQuantityStatus" || request.getParameter("reportType").equals("presentQuantityStatus")) {
				
				recordList = services.getFestivalPresentQuentityReportList(parameterObject);
			
				List recordDetailList = services.selectFestivalSchemeDetaiList(null);
				
				request.setAttribute("recordList", recordList);
				request.setAttribute("recordDetailList", recordDetailList);
				
				return "/ga_export_present_quentity.jsp";
				
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export festival present report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
}
