package com.ait.util;

import java.util.List;

import com.ait.ar.bean.ArDetail;
import com.ait.commons.dao.ViewOptionDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.ReportItem;
import com.ait.sy.bean.ReportTable;
import com.ait.web.ApplicationContext;

public class ViewOptionUtil {

	private ViewOptionDAO viewDAO = new ViewOptionDAO();

	private StringBuffer dataTable = new StringBuffer();

	public static final int HORIZONTAL = 4;

	public static final int VERTICAL_A = 1;

	public static final int VERTICAL_B = 3;

	public static final int CARLENDAR = 2;

	/**
	 * make data table
	 * 
	 * @param menu_code
	 * @param parameterObject
	 * @return
	 */
	public String makeDataTable(String menu_code, SimpleMap parameterObject) {
		
		parameterObject.setString("cpnyId", ApplicationContext.getTheadLocal().getCpnyId());
		parameterObject.setString("menuCode", menu_code);

		List<ReportTable> list = viewDAO.retrieveReportTableList(parameterObject);
		List dataList = null;
		int i = 0;

		for (ReportTable reportTable : list) {
			
			i ++;
			List<ReportItem> itemList = viewDAO.retrieveReportItemList(reportTable);

			if (reportTable.getView_model() == VERTICAL_A || reportTable.getView_model() == VERTICAL_B || reportTable.getView_model() == HORIZONTAL) {
				String sql = getReportViewSQL(itemList, reportTable.getReport_type());
				parameterObject.setString("sql", sql);
				parameterObject.setInt("reportType", reportTable.getReport_type());
				dataList = viewDAO.retrieveReportDataList(parameterObject);
			}

			if (reportTable.getView_model() == HORIZONTAL) 
				this.makeHorizontalTableBeginHTML(list);
			
			this.makeTableHTML((List) parameterObject.get("detailList"), (List) parameterObject.get("overTimeList"), dataList, itemList, reportTable);
			
			if (reportTable.getView_model() == HORIZONTAL && i == list.size()) 
				this.makeHorizoncalTableEndHTML();
		}

		return dataTable.toString();
	}

	/**
	 * make data table by paging
	 * 
	 * @param menu_code
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public String makeDataTable(String menu_code, SimpleMap parameterObject, int currentPage, int pageSize) {

		parameterObject.setString("cpnyId", ApplicationContext.getTheadLocal().getCpnyId());
		parameterObject.setString("menuCode", menu_code);
		
		List<ReportTable> list = viewDAO.retrieveReportTableList(parameterObject);
		List dataList = null;

		for (ReportTable reportTable : list) {

			List<ReportItem> itemList = viewDAO.retrieveReportItemList(reportTable);

			if (reportTable.getView_model() == VERTICAL_A || reportTable.getView_model() == VERTICAL_B) {
				String sql = getReportViewSQL(itemList, reportTable.getReport_type());
				parameterObject.setString("sql", sql);
				parameterObject.setInt("reportType", reportTable.getReport_type());
				dataList = viewDAO.retrieveReportDataList(parameterObject, currentPage, pageSize);
			}

			makeTableHTML((List) parameterObject.get("detailList"), (List) parameterObject.get("overTimeList"), dataList, itemList, reportTable);
		}
		return dataTable.toString();
	}
	
	
	/**
	 * make data table by paging
	 * 
	 * @param menu_code
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public String makeDataTableForArMonth(String menu_code, SimpleMap parameterObject, int currentPage, int pageSize) {

		parameterObject.setString("cpnyId", ApplicationContext.getTheadLocal().getCpnyId());
		parameterObject.setString("menuCode", menu_code);
		
		List<ReportTable> list = viewDAO.retrieveReportTableList(parameterObject);
		List dataList = null;

		for (ReportTable reportTable : list) {

			List<ReportItem> itemList = viewDAO.retrieveReportItemForArMonthList(reportTable);

			if (reportTable.getView_model() == VERTICAL_A || reportTable.getView_model() == VERTICAL_B) {
				String sql = getReportViewSQL(itemList, reportTable.getReport_type());
				parameterObject.setString("sql", sql);
				parameterObject.setInt("reportType", reportTable.getReport_type());
				dataList = viewDAO.retrieveReportDataList(parameterObject, currentPage, pageSize);
				
			}

			makeTableHTML((List) parameterObject.get("detailList"), (List) parameterObject.get("overTimeList"), dataList, itemList, reportTable);
		}
		return dataTable.toString();
	}

	/**
	 * get report data
	 * 
	 * @param menuCode
	 * @param param
	 * @param reportType
	 * @return
	 */
	public List<SimpleMap> getReportData(String menuCode, SimpleMap param) {

		param.setString("cpnyId", ApplicationContext.getTheadLocal().getCpnyId());
		param.setString("menuCode", menuCode);

		List<SimpleMap> result;

		try {
			List<SimpleMap> items = this.getReportItemsList(param);

			String sql = getReportSQL(items, param.getInt("reportType"));
			param.setString("sql", sql);

			result = viewDAO.retrieveReportDataList(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlRuntimeException("Get report data Exception", e);
		}

		return result;
	}

	/**
	 * get table data count
	 * 
	 * @param menu_code
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public int[] getTableDataCnt(String menu_code, SimpleMap parameterObject, int currentPage, int pageSize) {

		parameterObject.setString("cpnyId", ApplicationContext.getTheadLocal().getCpnyId());
		parameterObject.setString("menuCode", menu_code);
		
		List<ReportTable> list = viewDAO.retrieveReportTableList(parameterObject);
		int[] result = new int[list.size()];
		List dataList = null;
		int i = 0;

		for (ReportTable reportTable : list) {

			if (reportTable.getView_model() == VERTICAL_A || reportTable.getView_model() == VERTICAL_B) {
				String sql = getReportViewCntSQL(reportTable.getReport_type());
				parameterObject.setString("sql", sql);
				parameterObject.setInt("reportType", reportTable.getReport_type());
				dataList = viewDAO.retrieveReportDataList(parameterObject);
			}
			result[i] = ((SimpleMap) dataList.get(0)).getInt("CNT");
			i++;
		}

		return result;
	}

	/**
	 * get report item list
	 * 
	 * @param map
	 * @return
	 */
	public List<SimpleMap> getReportItemsList(SimpleMap map) {
		return viewDAO.getReportItemList(map);
	}

	/**
	 * get view SQL
	 * 
	 * @param itemList
	 * @param report_type
	 * @return
	 */
	private String getReportViewSQL(List<ReportItem> itemList, int report_type) {
		String sql = "";
		for (ReportItem item : itemList) {
			if (sql.equals(""))
				sql = "SELECT (" + item.getRef_item_id() + ") AS \"" + item.getItem_name() + "\"";
			else
				sql += "," + item.getRef_item_id() + " AS \"" + item.getItem_name() + "\"";
		}
		sql += " FROM " + (report_type == 1 ? "AR_HISTORY A" : "PA_HISTORY A");
		return sql;
	}

	/**
	 * get SQL
	 * 
	 * @param itemList
	 * @param report_type
	 * @return
	 */
	private String getReportSQL(List<SimpleMap> itemList, int report_type) {
		String sql = "";
		for (SimpleMap item : itemList) {
			if (sql.equals(""))
				sql = "SELECT " + item.getString("REF_ITEM_ID") + " AS \"" + item.getString("ITEM_NAME") + "\"";
			else
				sql += "," + item.getString("REF_ITEM_ID") + " AS \"" + item.getString("ITEM_NAME") + "\"";
		}
		sql += " FROM " + (report_type == 1 ? "AR_HISTORY A" : "PA_HISTORY A");
		return sql;
	}

	/**
	 * get view count SQL
	 * 
	 * @param report_type
	 * @return
	 */
	private String getReportViewCntSQL(int report_type) {

		String sql = "SELECT COUNT(*) AS CNT FROM " + (report_type == 1 ? "AR_HISTORY A" : "PA_HISTORY A");

		return sql;
	}

	/**
	 * make table HTML
	 * 
	 * @param detailList
	 * @param overTimeList
	 * @param dataList
	 * @param itemList
	 * @param reportTable
	 * @return
	 */
	private String makeTableHTML(List detailList, List<SimpleMap> overTimeList, List<SimpleMap> dataList, List<ReportItem> itemList, ReportTable reportTable) {

		if (reportTable.getView_model() == HORIZONTAL) {
			
			this.makeHorizontalTableContentHTML(dataList, itemList);
		} else {
			
			makeVerticalTableBeginHTML(reportTable);

			if (reportTable.getView_model() == VERTICAL_A) {
				makeVerticalTableTitleHTMLA(itemList);
				makeVerticalTableContentHTML(dataList, itemList);
			} else if (reportTable.getView_model() == VERTICAL_B) {

				makeVerticalTableTitleHTMLB(itemList);
				makeVerticalTableContentHTML(dataList, itemList);
			} else {
				makeTableSpecialTitleHTML(detailList);
				makeTableSpecialContentHTML(itemList, detailList, overTimeList);
			}

			makeVerticalTableEndHTML();
		}
	
		return dataTable.toString();
	}

	/**
	 * make content HTML for vertical table
	 * 
	 * @param dataList
	 * @param itemList
	 * @return
	 */
	private String makeVerticalTableContentHTML(List<SimpleMap> dataList, List<ReportItem> itemList) {
		SimpleMap sMap = null;
		if (dataList.size() > 0)
			for (int i = 0; i < dataList.size(); i++) {
				dataTable.append("<tr>");
				sMap = dataList.get(i);
				for (ReportItem reportItem : itemList) {
					dataTable.append("<td class='info_content_01' height='30' nowrap >");
					dataTable.append(StringUtil.checkNull(sMap.get(reportItem.getItem_name())));
					dataTable.append("</td>");
				}
				dataTable.append("</tr>");
			}
		else {
			dataTable.append("<tr>");
			for (ReportItem reportItem : itemList) {
				dataTable.append("<td class='info_content_01' height='30' nowrap >&nbsp;</td>");
			}
			dataTable.append("</tr>");
		}
		return dataTable.toString();
	}

	/**
	 * make content HTML for horizontal table
	 * 
	 * @param dataList
	 * @param itemList
	 * @return
	 */
	private String makeHorizontalTableContentHTML(List<SimpleMap> dataList, List<ReportItem> itemList) {

		SimpleMap sMap = null;
		dataTable.append("<td valign='top'>");
		dataTable.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d' >");

		for (ReportItem reportItem : itemList) {
			dataTable.append("<tr>");
			dataTable.append("<td class='info_title_01' height='30' width='50%' nowrap >");
			dataTable.append(reportItem.getItem_name());
			dataTable.append("</td>");

			if (dataList.size() > 0) {

				for (SimpleMap map : dataList) {

					dataTable.append("<td class='info_content_01' height='30' width='50%' nowrap>");
					dataTable.append(StringUtil.checkNull(map.getString(reportItem.getItem_name())));
					dataTable.append("</td>");
				}

			} else {

				dataTable.append("<td class='info_content_01' height='30' width='50%' nowrap >&nbsp;</td>");
			}

			dataTable.append("</tr>");
		}

		dataTable.append("</table>");
		dataTable.append("</td><td width='30'>&nbsp;</td>");

		return dataTable.toString();
	}

	/**
	 * make content HTML for date stytle table
	 * 
	 * @param itemList
	 * @param detailList
	 * @param overTimeList
	 * @return
	 */
	private String makeTableSpecialContentHTML(List<ReportItem> itemList, List<ArDetail> detailList, List<SimpleMap> overTimeList) {
		for (ReportItem reportItem : itemList) {
			dataTable.append("<tr>");
			dataTable.append("<td class='info_content_01' height='30' nowrap >");
			dataTable.append(reportItem.getItem_name());
			dataTable.append("</td>");
			if (reportItem.getRef_item_id().toUpperCase().equals("SHIFT")) {
				if (overTimeList != null) {
					for (SimpleMap overTimeMap : overTimeList)
						for (ArDetail arDetail : detailList)
							if ((overTimeMap.get("DATE_DAY") + "").equals(arDetail.getDate_day() + "")) {
								dataTable.append("<td class='info_content_01' nowrap>");
								dataTable.append(StringUtil.checkNull(arDetail.getShiftShortName()));
								dataTable.append("</td>");
							}
				} else {
					for (int i = 1; i <= 31; i++) {
						dataTable.append("<td class='info_content_01' nowrap>&nbsp;</td>");
					}
				}
			} else if (reportItem.getRef_item_id().toUpperCase().equals("ATDETAIL")) {
				if (overTimeList != null) {
					for (SimpleMap overTimeMap : overTimeList)
						for (ArDetail arDetail : detailList)
							if ((overTimeMap.get("DATE_DAY") + "").equals(arDetail.getDate_day() + "")) {
								dataTable.append("<td class='info_content_01' nowrap>");
								dataTable.append(StringUtil.checkNull(arDetail.getItemShortName()));
								dataTable.append("</td>");
							}
				} else {
					for (int i = 1; i <= 31; i++) {
						dataTable.append("<td class='info_content_01' nowrap>&nbsp;</td>");
					}
				}
			} else {
				if (overTimeList != null) {
					for (SimpleMap overTimeMap : overTimeList) {
						dataTable.append("<td class='info_content_01' nowrap>");
						dataTable.append(StringUtil.checkNull(overTimeMap.get("OVERTIME")));
						dataTable.append("</td>");
					}
				} else {
					for (int i = 1; i <= 31; i++) {
						dataTable.append("<td class='info_content_01' nowrap>&nbsp;</td>");
					}
				}
			}
			dataTable.append("</tr>");
		}
		return dataTable.toString();
	}

	/**
	 * make title HTML for vertical table (horizontal title)
	 * @param itemList
	 * @return
	 */
	private String makeVerticalTableTitleHTMLA(List<ReportItem> itemList) {
		dataTable.append("<tr>");
		for (ReportItem reportItem : itemList) {
			dataTable.append("<td class='info_title_01' height='30' nowrap >");
			dataTable.append(reportItem.getItem_name());
			dataTable.append("</td>");
		}
		dataTable.append("</tr>");
		return dataTable.toString();
	}

	/**
	 * make title HTML for vertical table (vertical title)
	 * 
	 * @param itemList
	 * @return
	 */
	private String makeVerticalTableTitleHTMLB(List<ReportItem> itemList) {
		dataTable.append("<tr>");
		for (ReportItem reportItem : itemList) {
			dataTable.append("<td class='info_title_00' nowrap >");
			dataTable.append(reportItem.getItem_name());
			dataTable.append("</td>");
		}
		dataTable.append("</tr>");
		return dataTable.toString();
	}

	/**
	 * make title HTML for date stytle table
	 * 
	 * @param detailList
	 * @return
	 */
	private String makeTableSpecialTitleHTML(List<ArDetail> detailList) {
		dataTable.append("<tr>");
		dataTable.append("<td width='70' height='30' class='info_title_01' nowrap>");
		dataTable.append("</td>");
		if (detailList != null)
			for (ArDetail arDetail : detailList) {
				dataTable.append("<td class='info_title_01' nowrap>");
				dataTable.append(arDetail.getDate_day());
				dataTable.append("</td>");
			}
		else
			for (int i = 1; i <= 31; i++) {
				dataTable.append("<td class='info_title_01' nowrap>");
				dataTable.append(i);
				dataTable.append("</td>");
			}
		dataTable.append("</tr>");
		return dataTable.toString();
	}

	/**
	 * make head HTML for horizontal table
	 * 
	 * @param tableList
	 * @return
	 */
	private String makeHorizontalTableBeginHTML(List<ReportTable> tableList) {

		if (dataTable.toString().equals("")) {

			dataTable.append("<tr height='20'>");

			for (ReportTable table : tableList) {
				
				dataTable.append("<td class='title1'>");
				dataTable.append(table.getTable_name());
				dataTable.append("</td>");
				dataTable.append("<td>&nbsp;</td>");
			}
			dataTable.append("</tr>");
			dataTable.append("<tr>");
		}

		return dataTable.toString();
	}

	/**
	 * make head HTML for vertical table
	 * 
	 * @param reportTable
	 * @return
	 */
	private String makeVerticalTableBeginHTML(ReportTable reportTable) {
//		if (!dataTable.toString().equals("")) {
//			dataTable.append("<tr height='1'>");
//			dataTable.append("<td colspan='2'>&nbsp;</td>");
//			dataTable.append("</tr>");
//		}
		dataTable.append("<tr height='20'>");
		dataTable.append("<td  class='title1'>");
		dataTable.append(reportTable.getTable_name());
		dataTable.append("</td>");
		dataTable.append("</tr>");
		dataTable.append("<tr>");
		dataTable.append("<td>");
		dataTable.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' class='dr_d' >");
		return dataTable.toString();
	}

	/**
	 * make end HTML for vertical table
	 * 
	 * @return
	 */
	private String makeVerticalTableEndHTML() {
		dataTable.append("</table>");
		dataTable.append("</td>");
		dataTable.append("</tr>");
		return dataTable.toString();
	}

	/**
	 * make end HTML for horzoncal table
	 * 
	 * @return
	 */
	private String makeHorizoncalTableEndHTML() {
		dataTable.append("</tr>");
		return dataTable.toString();
	}
}
