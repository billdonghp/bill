/*
 * @(#)ExcelParameterBean.java 1.0 2007-12-1 下午08:46:51
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-12-1 下午08:46:51
 * @version 1.0
 * 
 */
public class ExcelParameterBean implements Serializable {

	private int fileType;
	
	private int reportType;
	
	private int cellType;
	
	private int orientation;
	
	private Map columns;
	
	private Map columnTypes;
	
	private List reportData;
	
	private String fileName;
	
	private String sheetname;

	private int imageCol;
	
	private int imageRow;
	
	private int imageWidth;
	
	private int imageHeight;
	
	private File imageFile;
	
	private String headContent;
	
	private int headFontSize;
	
	private String inLineHeadContent;
	
	private int inLineHeadMergeSize;
	
	public int getCellType() {
		return cellType;
	}

	
	public void setCellType(int cellType) {
		this.cellType = cellType;
	}

	
	public Map getColumns() {
		return columns;
	}

	
	public void setColumns(Map columns) {
		this.columns = columns;
	}

	
	public Map getColumnTypes() {
		return columnTypes;
	}

	
	public void setColumnTypes(Map columnTypes) {
		this.columnTypes = columnTypes;
	}

	
	public String getFileName() {
		return fileName;
	}

	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	public int getFileType() {
		return fileType;
	}

	
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	
	public int getOrientation() {
		return orientation;
	}

	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	
	public List getReportData() {
		return reportData;
	}

	
	public void setReportData(List reportData) {
		this.reportData = reportData;
	}

	
	public int getReportType() {
		return reportType;
	}

	
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	
	public String getSheetname() {
		return sheetname;
	}

	
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}


	
	public int getImageCol() {
		return imageCol;
	}


	
	public void setImageCol(int imageCol) {
		this.imageCol = imageCol;
	}


	
	public File getImageFile() {
		return imageFile;
	}


	
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}


	
	public int getImageHeight() {
		return imageHeight;
	}


	
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}


	
	public int getImageRow() {
		return imageRow;
	}


	
	public void setImageRow(int imageRow) {
		this.imageRow = imageRow;
	}


	
	public int getImageWidth() {
		return imageWidth;
	}


	
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}


	
	public String getHeadContent() {
		return headContent;
	}


	
	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}

	
	public String getInLineHeadContent() {
		return inLineHeadContent;
	}


	
	public void setInLineHeadContent(String inLineHeadContent) {
		this.inLineHeadContent = inLineHeadContent;
	}


	
	public int getHeadFontSize() {
		return headFontSize;
	}


	
	public void setHeadFontSize(int headFontSize) {
		this.headFontSize = headFontSize;
	}


	
	public int getInLineHeadMergeSize() {
		return inLineHeadMergeSize;
	}


	
	public void setInLineHeadMergeSize(int inLineHeadMergeSize) {
		this.inLineHeadMergeSize = inLineHeadMergeSize;
	}
	
}

