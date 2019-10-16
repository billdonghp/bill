/**
 * @Copyright 
 * @author qinxd
 * @date 2006-9-6
 */
package com.ait.web.poi;

import java.sql.Types;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.ait.jdbc.core.RowResult;
import com.ait.jdbc.core.SQLResult;
import com.ait.util.DateUtil;

/**
 * 
 * @version 
 */
public class DefaultPoiReportCreator extends PoiReportCreator{
	
	private final static int titleRowIndex = 0;
	
	private final static int firstColmnRowIndex = 2;
	
	private final static int firstValueRowIndex = 3;

	public void drawValue(HSSFWorkbook wb, HSSFSheet sheet, SQLResult rs, String title) {
	    List colNameList = rs.getColNameList();	    
	    drawTitle(wb, sheet, (String[])colNameList.toArray(new String[colNameList.size()]), title); 
	    //
		int[] sqlTypes = rs.getSqlTypes();
		int rowIndex = firstValueRowIndex;
		HSSFCellStyle style = addStyleLine(wb.createCellStyle());
		Iterator it = rs.iterator();
		while (it.hasNext()){
			RowResult rt = (RowResult)it.next();
			drawRowValue(wb, sheet, rowIndex, rt, sqlTypes,style);
			rowIndex++;
		}
		//打印时，重复的行、列
		wb.setRepeatingRowsAndColumns(0, 0, colNameList.size() - 1, 0, 2);
	}
	
	protected HSSFRow drawRowValue(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex, RowResult rt, int[] sqlTypes, HSSFCellStyle style){
		HSSFRow row = sheet.createRow(rowIndex);
		for (int i = 0; i < sqlTypes.length; i++){
			int sqlType = sqlTypes[i];			  
			if (sqlType == Types.DECIMAL || sqlType == Types.NUMERIC){
				createCell(row, i, rt.getDoubleObj(i + 1), style);
			} else if (sqlType == Types.DATE){				
				createCell(row, i, DateUtil.formatDate(rt.getDate(i + 1), DateUtil.DATE_PATTERN), style);
			} else {
				createCell(row, i, rt.getString(i + 1), style);
			}
		}
		return row;
	}
	
	protected void drawTitle(HSSFWorkbook wb, HSSFSheet sheet, String[] colNameList, String title) {
		HSSFRow row_0 = sheet.createRow(titleRowIndex);
		createCell(row_0, 0, title, createTitleStyle(wb));				
		row_0.setHeight((short)((5 * 8) / ((double) 1 / 20)));
		for (int i = 1; i < colNameList.length - 1; i++){
			row_0.createCell((short)i);
		}
		sheet.addMergedRegion(new Region(titleRowIndex,(short)0,titleRowIndex,(short)(colNameList.length - 1)));//合并
		HSSFRow row_1 = sheet.createRow(titleRowIndex + 1);
		String str = "打印日期：" + DateUtil.formatDate(new Date(), "MM/dd/yyyy");
		createCell(row_1, 0, str, null);
		//
		HSSFRow row_2 = sheet.createRow(firstColmnRowIndex);
		HSSFCellStyle style = addStyleLine(wb.createCellStyle());
		for (int i = 0; i < colNameList.length; i++){
			createCell(row_2, i, colNameList[i], style);
		}
		log.debug("xsl title finished");
	}

}
