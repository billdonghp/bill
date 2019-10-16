/**
 * @Copyright 
 * @author qinxd
 * @date 2006-9-25
 */
package com.ait.commons.xsl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.ait.core.exception.GlRuntimeException;
import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.RowResult;
import com.ait.jdbc.core.SQLResult;
import com.ait.jdbc.core.SqlParameter;
import com.ait.util.DateUtil;
import com.ait.util.POIUtil;

/**
 * xsl报表生成工具
 * @version 
 */
public abstract class PoiReportor {
	protected Logger log = Logger.getLogger(this.getClass());
	
	private SQLResult rs;
	private HSSFWorkbook wb;
	/**
	 * 报表标题
	 */
	private String title;
	/**
	 * 换页行
	 */
	private Integer breakRowIndex;
	/**
	 * 列名数组
	 */
	private String[] colTitles;
	/**
	 * 列宽数组
	 */
	private short[] colWidths;
	/**
	 * 右上角是否打印决裁框
	 */
	private boolean rightTopDetail;
	/**
	 * 报表标题高度
	 */
	private short titleHeight = (short)((7 * 4) / ((double) 1 / 20));
	/**
	 * 报表标题字体
	 */
	private HSSFFont titleFont;
	/**
	 * 右上角是决裁框占用的列数
	 */
	private int rightTopLength = 7;
	/**
	 * 右上角是决裁框字体
	 */
	private HSSFFont rightTopFont;
	/**
	 * 打印日期的样式
	 */
	private HSSFCellStyle printDateStyle;
	/**
	 * 列名高度
	 */
	private short columnTitleHeight; //= (short)((8 * 4) / ((double) 1 / 20));
	/**
	 * 列名样式
	 */
	private HSSFCellStyle columnTitleStyle;
	/**
	 * 列值高度
	 */
	private short columnValueHeight;
	/**
	 * 列值样式
	 */
	private HSSFCellStyle[] columnValueStyles;
	/**
	 * 打印方向 true 横向； false 纵向
	 */
	private boolean landscape;
		
	public HSSFWorkbook createXls(String sql, SqlParameter[] params){
		try {
		   return createXls(JdbcUtil.executeQuery(sql, params));
		} catch (SQLException ex){
		   ex.printStackTrace();
		   Logger.getLogger(this.getClass()).error(sql + ";\n" + ex.getMessage());
		   throw new GlRuntimeException("报表查询触发数据库异常！");
		}
	}
	
	public HSSFWorkbook createXls(String sql){
		   return createXls(sql, null);
	}
	/**
	 * 创建xls输出流
	 * @param rs
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createXls(SQLResult rs){
		this.rs = rs;
		log.debug("col count: " + getColCount());
		wb = new HSSFWorkbook();//建立新HSSFWorkbook对象
		String sheetName = isExistTitle() ? title : "sheet1";
		HSSFSheet sheet = wb.createSheet(sheetName);
		writeTitle(sheet);
		Iterator it = rs.iterator();
		int firstRowIndex = isExistTitle() ? 4 : 1;
		int rowIndex = firstRowIndex;
		while (it.hasNext()){
			RowResult r = (RowResult)it.next();
			writeValue(sheet, r, rowIndex);
			if (breakRowIndex != null && rowIndex == breakRowIndex.intValue()) //换页
				sheet.setRowBreak(rowIndex);
			rowIndex++;
		}
		writeColWidth(sheet);
		//设置重复显示标题行
		wb.setRepeatingRowsAndColumns(0, 0, getColCount() - 1, 0, firstRowIndex - 1);
		configSheet(sheet);
		return wb;
	}
	/**
	 * 写行值
	 * @param sheet
	 * @param r
	 * @param rowIndex
	 */
	protected void writeValue(HSSFSheet sheet, RowResult r, int rowIndex){
		HSSFRow row = sheet.createRow(rowIndex);
		if (columnValueHeight > 0)
	    	row.setHeight(columnValueHeight);
		int[] sqlTypes = rs.getSqlTypes();
		for (int i = 0; i < getColCount(); i++){
			if (sqlTypes[i] == Types.NUMERIC || sqlTypes[i] == Types.DECIMAL || 
					sqlTypes[i] == Types.DOUBLE || sqlTypes[i] == Types.FLOAT)
				POIUtil.createCell(row, i, r.getDoubleObj(i + 1), getColumnValueStyle(i));
			else if (sqlTypes[i] == Types.INTEGER)
			    POIUtil.createCell(row, i, r.getInteger(i + 1), getColumnValueStyle(i));
			else// error date
				POIUtil.createCell(row, i, r.getString(i + 1), getColumnValueStyle(i));
		}
	}
	
	/**
	 * 写标题和列名
	 * @param wb
	 * @param sheet
	 */
	protected void writeTitle(HSSFSheet sheet){
		int columnTitleRowIndex = 0;
		if (isExistTitle()){//输出标题和打印日期
			HSSFRow row_0 = sheet.createRow(0);	
			POIUtil.createCell(row_0, 0, title, createTitleStyle());		
			HSSFRow row_1 = sheet.createRow(1);	
			row_1.setHeight(titleHeight);	
			if (hasRightTopDetail()){//右上角打印决裁框
				for (int i = 1; i < getColCount() - rightTopLength; i++){
					POIUtil.createCell(row_0, i, "", null);	
				}
				sheet.addMergedRegion(new Region(0,(short)0,1,(short)(getColCount() - rightTopLength - 1)));//合并
				writeTopRightDetail(sheet, 0, getColCount() - rightTopLength + 1, row_0, row_1);
			} else {
				sheet.addMergedRegion(new Region(0,(short)0,1,(short)(getColCount() - 1)));//合并
			}
			HSSFRow row_2 = sheet.createRow(2);
			String str = "打印日期：" + DateUtil.formatDate(new Date(), "MM/dd/yyyy");
			POIUtil.createCell(row_2, 0,str, printDateStyle);
			columnTitleRowIndex = 3;
		}		
		HSSFRow row_3 = sheet.createRow(columnTitleRowIndex);
		if (columnTitleHeight > 0)
		    row_3.setHeight(columnTitleHeight);	
		POIUtil.createCellList(row_3, getColTitles(), 0, columnTitleStyle);
	}

	/**
	 * 
	 * 写右上角决裁信息
	 * @param sheet
	 * @param startRowIndex 标题对应的xls开始行，每页都打印标题和列名 
	 * @param index 列索引
	 * @param row_0
	 * @param row_1
	 * @param style
	 */
	protected void writeTopRightDetail(HSSFSheet sheet, int startRowIndex, int index, HSSFRow row_0, HSSFRow row_1){	
		HSSFCellStyle style = createRightTopStyle();
		POIUtil.createCell(row_0, index, "决裁", style);	
		POIUtil.createCell(row_0, index + 1, "起案", style);	
		POIUtil.createCell(row_0, index + 2, "", style);
		POIUtil.createCell(row_0, index + 3, "审议", style);
		POIUtil.createCell(row_0, index + 4, "", style);	
		POIUtil.createCell(row_0, index + 5, "确定", style);
		POIUtil.createCell(row_0, index + 6, "", style);	
		POIUtil.createCell(row_1, index, "", style);	
		POIUtil.createCell(row_1, index + 1, "", style);	
		POIUtil.createCell(row_1, index + 2, "", style);	
		POIUtil.createCell(row_1, index + 3, "", style);	
		POIUtil.createCell(row_1, index + 4, "", style);	
		POIUtil.createCell(row_1, index + 5, "", style);	
		POIUtil.createCell(row_1, index + 6, "", style);	
		sheet.addMergedRegion(new Region(startRowIndex,(short)index,startRowIndex + 1,(short)index));//合并
		sheet.addMergedRegion(new Region(startRowIndex,(short)(index + 1) ,startRowIndex,(short)(index + 2)));//合并
		sheet.addMergedRegion(new Region(startRowIndex,(short)(index + 3),startRowIndex,(short)(index + 4)));//合并
		sheet.addMergedRegion(new Region(startRowIndex,(short)(index + 5),startRowIndex,(short)(index + 6)));//合并
		sheet.addMergedRegion(new Region(startRowIndex + 1,(short)(index + 1),startRowIndex + 1,(short)(index + 2)));//合并
		sheet.addMergedRegion(new Region(startRowIndex + 1,(short)(index + 3),startRowIndex + 1,(short)(index + 4)));//合并
		sheet.addMergedRegion(new Region(startRowIndex + 1,(short)(index + 5),startRowIndex + 1,(short)(index + 6)));//合并
		
	}
	/**
	 * 新创建报表标题样式
	 * @param wb
	 * @return 
	 */
	protected HSSFCellStyle createTitleStyle(){
		if (titleFont == null){
			titleFont = wb.createFont();
			titleFont.setFontName("宋体");
			titleFont.setFontHeightInPoints((short)16);
			titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		HSSFCellStyle ret = wb.createCellStyle();
		ret.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ret.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		ret.setFont(titleFont);
		return ret;
	}
	/**
	 * 创建右上角决裁样式
	 * @return
	 */
	protected HSSFCellStyle createRightTopStyle(){		
		HSSFCellStyle ret = POIUtil.addStyleLine(POIUtil.createStyle(wb, rightTopFont));
		ret.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ret.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return ret;
	}
	/**
	 * 设置列宽
	 * @param sheet
	 */
	protected void writeColWidth(HSSFSheet sheet){
		if (colWidths == null)
			return;
		for (int i = 0; i < getColCount(); i++){
			sheet.setColumnWidth((short)i, colWidths[i]);
		}
	}
	/**
	 * 列数
	 * @return
	 */
	protected int getColCount(){
		return rs.getColumnCount();
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
		if (isLandscape())
		    ps.setLandscape(true);
	}
	/**
	 * 是否输出标题和打印日期
	 * @return
	 */
	public boolean isExistTitle(){
		return !(title == null || "".equals(title));
	}
	
	public Integer getBreakRowIndex() {
		return breakRowIndex;
	}
	public void setBreakRowIndex(Integer breakRowIndex) {
		this.breakRowIndex = breakRowIndex;
	}
	public String[] getColTitles() {
		if (colTitles != null && colTitles.length > 0)
			return colTitles;
		if (rs != null){
			List list = rs.getColNameList();
			this.colTitles = (String[])list.toArray(new String[list.size()]);
			return colTitles; 
		}
		return new String[0];
	}
	public void setColTitles(String[] colTitles) {
		this.colTitles = colTitles;
	}
	public short getColumnTitleHeight() {
		return columnTitleHeight;
	}
	public void setColumnTitleHeight(short columnTitleHeight) {
		this.columnTitleHeight = columnTitleHeight;
	}
	public HSSFCellStyle getColumnTitleStyle() {
		return columnTitleStyle;
	}
	public void setColumnTitleStyle(HSSFCellStyle columnTitleStyle) {
		this.columnTitleStyle = columnTitleStyle;
	}
	public short getColumnValueHeight() {
		return columnValueHeight;
	}
	public void setColumnValueHeight(short columnValueHeight) {
		this.columnValueHeight = columnValueHeight;
	}
	public HSSFCellStyle[] getColumnValueStyles() {
		return columnValueStyles;
	}
	public void setColumnValueStyles(HSSFCellStyle[] columnValueStyles) {
		this.columnValueStyles = columnValueStyles;
	}
	/**
	 * 
	 * @param index
	 * @return
	 */
	private HSSFCellStyle getColumnValueStyle(int index) {
		if (columnValueStyles != null)
		   return columnValueStyles[index];
		return null;
	}
	
	public short[] getColWidths() {
		return colWidths;
	}
	public void setColWidths(short[] colWidths) {
		this.colWidths = colWidths;
	}
	public boolean hasRightTopDetail() {
		return rightTopDetail;
	}
	public void setRightTopDetail(boolean rightTopDetail) {
		this.rightTopDetail = rightTopDetail;
	}
	public HSSFCellStyle getPrintDateStyle() {
		return printDateStyle;
	}
	public void setPrintDateStyle(HSSFCellStyle printDateStyle) {
		this.printDateStyle = printDateStyle;
	}
	public HSSFFont getRightTopFont() {
		return rightTopFont;
	}
	public void setRightTopFont(HSSFFont rightTopFont) {
		this.rightTopFont = rightTopFont;
	}
	public int getRightTopLength() {
		return rightTopLength;
	}
	public void setRightTopLength(int rightTopLength) {
		this.rightTopLength = rightTopLength;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HSSFFont getTitleFont() {
		return titleFont;
	}
	public void setTitleFont(HSSFFont titleFont) {
		this.titleFont = titleFont;
	}
	public short getTitleHeight() {
		return titleHeight;
	}
	public void setTitleHeight(short titleHeight) {
		this.titleHeight = titleHeight;
	}

	public boolean isLandscape() {
		return landscape;
	}

	public void setLandscape(boolean landscape) {
		this.landscape = landscape;
	}
	
	
}
