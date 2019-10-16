/**
 * @Copyright 
 * @author qinxd
 * @date 2006-9-25
 */
package com.ait.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * POI 工具类
 * @version 
 */
public class POIUtil {
	/**
	 * 新建cell
	 * @param row  行对象
	 * @param colIndex 列索引
	 * @param value  写入值，String类型
	 * @param style  样式
	 * @return
	 */
	public static HSSFCell createCell(HSSFRow row, int colIndex, String value, HSSFCellStyle style){
		HSSFCell cell = row.createCell((short)colIndex);
		if (value != null)
		   cell.setCellValue(hstr(value));
		if (style != null)
			cell.setCellStyle(style);
		return cell;
	}
	/**
	 * s
	 * @param row
	 * @param values
	 * @param startIndex
	 * @param style
	 * @return
	 */
	public static List createCellList(HSSFRow row, String[] values, int startIndex, HSSFCellStyle style){
		List ret = new ArrayList();
		int colIndex = startIndex;
		for (int i = 0; i < values.length; i++){
			String v = values[i] == null ? "" : values[i];
			HSSFCell cell = createCell(row, colIndex,v, style);
			ret.add(cell);
			colIndex++;
		}
		return ret;
	}
	/**
	 * 新建cell
	 * @param row 行对象
	 * @param colIndex 列索引
	 * @param value 写入值，Double类型
	 * @param style 样式
	 * @return
	 */
	public static HSSFCell createCell(HSSFRow row, int colIndex, Double value, HSSFCellStyle style){
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
	 * @param row 行对象
	 * @param colIndex 列索引
	 * @param value 写入值
	 * @param style 样式
	 * @return
	 */
	public static HSSFCell createCell(HSSFRow row, int colIndex, int value, HSSFCellStyle style){
		HSSFCell cell = row.createCell((short)colIndex);	
		cell.setCellValue(value);
		if (style != null)
			cell.setCellStyle(style);
		return cell;
	}
	/**
	 * 新建cell
	 * @param row 行对象
	 * @param colIndex 列索引
	 * @param value 写入值
	 * @param style 样式
	 * @return
	 */
	public static HSSFCell createCell(HSSFRow row, int colIndex, Integer value, HSSFCellStyle style){
		HSSFCell cell = row.createCell((short)colIndex);	
		if (value != null)
		   cell.setCellValue(value.intValue());
		if (style != null)
			cell.setCellStyle(style);
		return cell;
	}
	/**
	 * 新建一个货币格式[#,##0.00]的HSSFCellStyle 
	 * @param wb
	 * @param font 字体
	 * @return
	 */
	public static HSSFCellStyle createStyleCurrency(HSSFWorkbook wb, HSSFFont font){
		HSSFCellStyle ret = createStyle(wb, font);
		ret.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		return ret;
	}
	/**
	 * 新建一个HSSFCellStyle 
	 * @param wb
	 * @param font 字体
	 * @return
	 */
	public static HSSFCellStyle createStyle(HSSFWorkbook wb, HSSFFont font){
		HSSFCellStyle ret = wb.createCellStyle();
		ret.setFont(font);
		return ret;
	}
	/**
	 * 新建一个中文字体
	 * @param wb
	 * @return
	 */
	public static HSSFFont createFontCN(HSSFWorkbook wb, int fontSize){
	   HSSFFont font = wb.createFont();
	   font.setFontName("宋体");
	   font.setFontHeightInPoints((short)fontSize);
	   return font;
	}
	/**
	 * 新建一个中文字体(默认大小10)
	 * @param wb
	 * @return
	 */
	public static HSSFFont createFontCN(HSSFWorkbook wb){
	   return createFontCN(wb, 10);
	}
	/**
	 * 为HSSFCellStyle 加边框
	 * @param style
	 * @return
	 */
	public static HSSFCellStyle addStyleLine(HSSFCellStyle style){
		if (style != null){
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);			
		}
		return style;
	}
	/**
	 * 字符串转换为HSSFRichTextString
	 * @param s
	 * @return
	 */
	public static HSSFRichTextString hstr(String s){
		return new HSSFRichTextString(s);
	}

}
