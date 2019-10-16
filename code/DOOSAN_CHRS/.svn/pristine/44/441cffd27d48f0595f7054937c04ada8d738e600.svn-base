/**
 * @Copyright 
 * @author qinxd
 * @date 2006-9-6
 */
package com.ait.web.poi;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.SQLResult;
import com.ait.jdbc.core.SqlParameter;
import com.ait.web.SqlAndTitle;

/**
 * 
 * @version 
 */
public abstract class PoiReportCreator {
	protected Logger log = Logger.getLogger(this.getClass());
	
	protected abstract void drawValue(HSSFWorkbook wb, HSSFSheet sheet, SQLResult rs, String title);
	
	public PoiReportCreator(){
		
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param title
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public HSSFWorkbook createXls(SqlAndTitle sqlAndTitle) throws IOException, SQLException{
		String sql = sqlAndTitle.getSql();
		SqlParameter[] params = sqlAndTitle.getParams();
		String title = sqlAndTitle.getTitle();
	    HSSFWorkbook wb = new HSSFWorkbook();//建立新HSSFWorkbook对象
	    HSSFSheet sheet = wb.createSheet(title);
		SQLResult rs = JdbcUtil.executeQuery(sql, params);
	    drawValue(wb, sheet, rs, title);
	    configSheet(sheet);		    
		log.debug("*** 报表创建完毕！");
		return wb;		
	}

	/**
	 * 为HSSFCellStyle 加边框
	 * @param style
	 * @return
	 */
	protected HSSFCellStyle addStyleLine(HSSFCellStyle style){
		if (style != null){
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);			
		}
		return style;
	}
	/**
	 * 新建cell
	 * @param row 行对象
	 * @param colIndex 列索引
	 * @param value 写入值，Double类型
	 * @param style 样式
	 * @return
	 */
	protected HSSFCell createCell(HSSFRow row, int colIndex, Double value, HSSFCellStyle style){
		HSSFCell cell = row.createCell((short)colIndex);	
		//cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		if (value != null)
		   cell.setCellValue(value.doubleValue());
		if (style != null)
			cell.setCellStyle(style);
		return cell;
	}
	/**
	 * 新建cell
	 * @param row  行对象
	 * @param colIndex 列索引
	 * @param value  写入值，String类型
	 * @param style  样式
	 * @return
	 */
	protected HSSFCell createCell(HSSFRow row, int colIndex, String value, HSSFCellStyle style){
		HSSFCell cell = row.createCell((short)colIndex);
		if (value != null)
		   cell.setCellValue(hstr(value));
		if (style != null)
			cell.setCellStyle(style);
		
		return cell;
	}
	/**
	 * 字符串转换为HSSFRichTextString
	 * @param s
	 * @return
	 */
	protected HSSFRichTextString hstr(String s){
		return new HSSFRichTextString(s);
	}
	
	/**
	 * 报表标题字体高度(默认16)
	 * @return
	 */
	protected short getTitleFontHeightInPoints(){
		return (short)16;
	}
	/**
	 * 新创建报表标题样式
	 * @param wb
	 * @return
	 */
	protected HSSFCellStyle createTitleStyle(HSSFWorkbook wb){
		HSSFFont f0 = wb.createFont();
		f0.setFontHeightInPoints(getTitleFontHeightInPoints());
		f0.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle ret = wb.createCellStyle();
		ret.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ret.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		ret.setFont(f0);
		return ret;
	}

	/**
	 * 打印配置
	 * @param sheet
	 */
	protected void configSheet(HSSFSheet sheet){
		HSSFFooter footer = sheet.getFooter();        
        footer.setCenter(HSSFFooter.page());
		HSSFPrintSetup ps = sheet.getPrintSetup();
		ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
	}

}
