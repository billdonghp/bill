/*
 * @(#)SheetBatchResult.java 1.0 2007-1-29 下午09:44:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-29 下午09:44:14
 * @version 1.0
 * 
 */
public class SheetBatchResult {

	private String sheetName;

	private int rows;

	private int columns;

	private int successRows;

	private String sql;

	private long elapsed;

	private List errors;

	public SheetBatchResult() {
	}

	public SheetBatchResult(Sheet sheet) {

		this.sheetName = sheet.getName();
		this.rows = sheet.getRows() - 1;
		this.columns = sheet.getRow(0).length;

		errors = new ArrayList();
	}

	public void addErrorMessage(String error) {
		errors.add(error);
	}

	public String getBatchQuery() {
		return sql;
	}

	public int getColumnCount() {
		return columns;
	}

	public long getElapsedTime() {
		return elapsed;
	}

	public List getErrorMessages() {
		return errors;
	}

	public int getFailureCount() {
		return (rows - successRows);
	}

	public int getRowCount() {
		return rows;
	}

	public String getSheetName() {
		return sheetName;
	}

	public int getSuccessCount() {
		return successRows;
	}

	public void setBatchQuery(String sql) {
		this.sql = sql;
	}

	public void setElapsedTime(long elapsed) {
		this.elapsed = elapsed;
	}

	public void setSuccessCount(int successRows) {
		this.successRows = successRows;
	}

}
