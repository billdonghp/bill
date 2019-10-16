/*
 * @(#)ExcelBatchResult.java 1.0 2007-1-29 下午09:39:23
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-29 下午09:39:23
 * @version 1.0
 * 
 */
public class ExcelBatchResult {

	private String fileName;

	private int sheets;

	private Collection sheetResults;

	private long elapsed;

	public ExcelBatchResult() {
	}

	public ExcelBatchResult(String fileName, int sheets) {

		this.fileName = fileName;
		this.sheets = sheets;
		sheetResults = new ArrayList();
	}

	public void setElapsedTime(long elapsed) {
		
		this.elapsed = elapsed;
	}

	public String getFileName() {
		
		return fileName;
	}

	public int getSheetCount() {
		
		return sheets;
	}

	public Collection getSheetResults() {
		
		return sheetResults;
	}

	public long getElapsedTime() {
		
		return elapsed;
	}

	public void addSheetResult(SheetBatchResult sheetResult) {
		sheetResults.add(sheetResult);
	}
}
