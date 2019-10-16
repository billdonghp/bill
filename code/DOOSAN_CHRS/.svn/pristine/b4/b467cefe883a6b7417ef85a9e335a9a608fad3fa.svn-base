/*
 * @(#)ExcelBatchProcessor.java 1.0 2007-1-29 下午08:34:59
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-29 下午08:34:59
 * @version 1.0
 * 
 */
public class ExcelBatchProcessor {

	public final static int CHAR = 0;

	public final static int DATE = 1;

	public final static int NUMBER = 2;
	
	public final static int SINGLE = 0;
	
	public final static int INVOLUTE = 1;

	private ExcelBatchDAO dao;

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);

	public ExcelBatchProcessor() {

		dao = new ExcelBatchDAO();
	}

	/**
	 * Excel process batch
	 * 
	 * @param file
	 * @return
	 * @throws GlRuntimeException
	 */
	public ExcelBatchResult process(File file) throws GlRuntimeException {

		SimpleMap parameters = new SimpleMap();
		return this.process(file, parameters, false);
	}

	/**
	 * Excel process batch
	 * 
	 * @param file
	 * @param paras
	 * @param TRANSACTION_MODE
	 * @return ExcelBatchResult
	 */
	public ExcelBatchResult process(File file, SimpleMap paras,
			boolean TRANSACTION_MODE) {

		Workbook w = null;
		try {
			w = Workbook.getWorkbook(file);
		} catch (Exception e) {
			throw new GlRuntimeException(
					" Can't Load Excel file ["
							+ file.getName()
							+ "]. It may broken excel file or Not an excel file format.",
					e);
		}

		// 构造ExcelBatchResult对象
		ExcelBatchResult result = new ExcelBatchResult(file.getName(), w
				.getNumberOfSheets());

		long start = System.currentTimeMillis();

		// 遍例文件的所有sheet
		for (int index = 0; index < w.getNumberOfSheets(); index++) {

			Sheet sheet = w.getSheet(index);

			// 插入sheet数据到数据库
			SheetBatchResult sheetResult = dao.insertSheetTx(sheet, paras,
					TRANSACTION_MODE);
			result.addSheetResult(sheetResult);
		}

		long end = System.currentTimeMillis();
		result.setElapsedTime(end - start);

		w.close();
		return result;

	}

	/**
	 * Excel process batch
	 * 
	 * @param request
	 * @param paras
	 * @param TRANSACTION_MODE
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public String process(HttpServletRequest request, SimpleMap paras,
			boolean TRANSACTION_MODE) {

		String message = "数据导入成功";
		
		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.att.no_found_error");

		String webPath = request.getRealPath("/").replace("\\", "/");

		String sheetName = paras.getString("sheetName");

		try {

			// 上传文件,返回一个File对象的列表
			List list = new FileUploadUtil(webPath).uploadFile(request);

			File file = (File) list.get(0);
			
			// 检测导入的文件
			if (file == null || !file.isFile()
					|| !file.getName().endsWith(".xls")) {
				//导入文件不存在或者文件格式错误。
				message = msg;
			} else {

				Workbook workBook = Workbook.getWorkbook(file);

				// 获得第一个工作表对象
				Sheet sheetExcel = workBook.getSheet(0);

				// 获得工作表的名称
				String currentSheetName = sheetExcel.getName();

				// 验证工作表的名称
				if (sheetName != null && currentSheetName.equals(sheetName)) {

					// 删除导入的年假临时数据
					new ArServices().deleteAnnualImportTempData();

					// 导入数据到临时表
					ExcelBatchResult result = process(file, paras,
							TRANSACTION_MODE);

				} else {

					message = "Excel的sheet名称不正确,名称必须为：" + sheetName;
				}
			}

			// 删除上传文件
			if (file != null)
				file.delete();

		} catch (Exception e) {

			logger.error("Excel process batch fail.");
			e.printStackTrace();
			message = "Excel 文件格式不正确,请下载模板后从新导入";
		}

		return message;
	}

	
	
	/*************************************************基于IBATIS****************************************************/

	
	
	
	/**
	 * Excel process batch by IBATIS
	 * 
	 * @param request
	 * @param paras
	 * @param flag
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public String process(HttpServletRequest request, SimpleMap paras, int flag) {

		String message;
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
		String msg = messageSource.getMessage("alert.att.no_found_error");

		String webPath = request.getRealPath("/").replace("\\", "/");

		try {
			logger.debug("webPath: " + webPath) ;
			// 上传文件,返回一个File对象的列表
			List list = new FileUploadUtil(webPath).uploadFile(request);

			File file = (File) list.get(0);
			logger.debug("File Object: " + file);

			// 检测导入的文件
			if (file == null || !file.isFile() || !file.getName().endsWith(".xls")) {

				//导入文件不存在或者文件格式错误
				message = msg;
			} else {

				// 导入数据到临时表
				message = process(file, paras, flag);
			}

			// 删除上传文件
			if (file != null)
				file.delete();

		} catch (Exception e) {
			
			logger.error("webPath: " + webPath + " " + e) ;
			logger.error("Excel process batch fail.");
			e.printStackTrace();
			message = "Excel 文件格式不正确,请下载模板后重新导入";
		}

		return message;
	}

	/**
	 * Excel process batch by IBATIS
	 * 
	 * @param request
	 * @param paras
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String process(HttpServletRequest request, SimpleMap paras) {
		
		return this.process(request, paras, this.SINGLE);
	}
	
	/**
	 * Excel process batch by IBATIS
	 * 
	 * @param file
	 * @param paras
	 * @return String
	 * @throws BiffException,IOException
	 */
	public String process(File file, SimpleMap paras) throws BiffException,
			IOException {
		
		return this.process(file, paras, this.SINGLE);
	}
	
	/**
	 * Excel process batch by IBATIS
	 * 
	 * @param file
	 * @param paras
	 * @param flag
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public String process(File file, SimpleMap paras, int flag) throws BiffException,
			IOException {

		String message;

		String sheetName = paras.getString("sheetName").toUpperCase();

		Workbook workBook = Workbook.getWorkbook(file);

		// 获得第一个工作表对象
		Sheet sheet = workBook.getSheet(0);

		// 获得工作表的名称
		String currentSheetName = sheet.getName().toUpperCase();

		// 验证工作表的名称
		if (sheetName != null && currentSheetName.equals(sheetName)) {

			paras.setString("tempTable", paras.getString("tableName") + "_I");
			paras.setString("sourceTable", paras.getString("tableName"));

			// 取得列信息
			List columnList = dao.retrieveColumnList(paras);

			Logger.getLogger(ExcelBatchProcessor.class).debug("创建插入到临时表的SQL") ;
			// 创建插入到临时表的SQL
			// String sql = this.createInsertSqlByIBATIS(sheet, paras, columnList);
			// paras.setString("sqlStatement", sql);
			this.createInsertSql(sheet, paras, columnList);
			
			Logger.getLogger(ExcelBatchProcessor.class).debug("插入sheet数据到数据库") ;
			// 插入sheet数据到数据库
			message = dao.insertSheet(sheet, paras, flag);
			
			logger.debug("总行数: " + sheet.getRows()) ;
			
		} else {

			message = "Excel的sheet名称不正确,名称必须为：" + sheetName;
		}

		workBook.close();

		return message;

	}

	/**
	 * create sql of insert database
	 * 
	 * @param sheet
	 * @param paras
	 * @param columnList
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private void createInsertSql(Sheet sheet, SimpleMap paras, List columnList)
			throws ArrayIndexOutOfBoundsException {

		// 取得第一行的单元格数组
		Cell[] firstRow = sheet.getRow(0);

		// 取得Excel列对应的表字段
		//String[] columns = (String[]) paras.get("mappingColumn");
		SimpleMap mappingColumn = (SimpleMap) paras.get("mappingColumn");
		Object[] columns = mappingColumn.keySet().toArray();

		// 取得附加列的集合
		SimpleMap appendColumn = (SimpleMap) paras.get("appendColumn");

		final int size = firstRow.length;

		if (columns != null && columns.length != size) {

			logger.error("Excel File Column Map Excepiton. Excel column: "
					+ size + "  Map table column: " + columns.length);
			throw new GlRuntimeException("Excel File Format Excepiton.");
		}

		// 取得要导入的表名称
		String tableName = "";
		if (paras.getString("tempTable") != null) {

			tableName = paras.getString("tempTable");
		} else {

			logger
					.error("Excel File Import Excepiton. import table name is null. ");
			throw new GlRuntimeException("Excel File Import Excepiton.");
		}

		String sql = tableName + " (";

		// 循环当前sheet的所有列名称,拼接SQL
		for (int i = 0; i < size; i++) {

			// 如果有映射的表字段，取映射表字段拼接SQL
			if (columns != null && columns.length != 0) {

				sql += columns[i] + ",";
			} else {

				sql += firstRow[i].getContents() + ",";
			}
		}

		// 循环附加的列名集合,拼接SQL
		if (appendColumn != null) {

			Iterator names = appendColumn.keySet().iterator();
			while (names.hasNext()) {

				String name = (String) names.next();
				if (name == null) {
					continue;
				}
				sql += name + ",";
			}
		}

		sql = sql.substring(0, sql.length() - 1);
		sql += ") ";

		// 添加 "sql_head" 参数
		paras.setString("sql_head", sql);
		logger.debug(sheet.getName() + " head sql:" + sql);

		
		// 循环附加的列值集合，拼接SQL
		sql = "";
		if (appendColumn != null) {

			Iterator it = appendColumn.keySet().iterator();
			while (it.hasNext()) {
				String itStr = (String) it.next();
				if (itStr == null) {
					continue;
				}
				sql += (String) appendColumn.get(itStr) + ",";
			}

			// 添加 "sql_content_append" 参数
			paras.setString("sql_content_append", sql.substring(0,
					sql.length() - 1));
			logger.debug(sheet.getName() + " append content sql:"
					+ sql.substring(0, sql.length() - 1));
		} else {

			// 添加 "sql_content_append" 参数
			paras.setString("sql_content_append", " ");
			logger.debug(sheet.getName() + " append content sql:" + " ");
		}
	}

	/**
	 * create sql of insert database by IBATIS
	 * 
	 * @param sheet
	 * @param paras
	 * @param columnList
	 * @return String
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private String createInsertSqlByIBATIS(Sheet sheet, SimpleMap paras,
			List columnList) throws ArrayIndexOutOfBoundsException {

		// 取得第一行的单元格数组
		Cell[] firstRow = sheet.getRow(0);

		// 取得Excel列对应的表字段
		SimpleMap mappingColumn = (SimpleMap) paras.get("mappingColumn");
		Object[] columns = mappingColumn.keySet().toArray();

		// 取得附加列的集合
		SimpleMap appendColumn = (SimpleMap) paras.get("appendColumn");

		final int size = firstRow.length;

		if (columns != null && columns.length != size) {

			logger.error("Excel File Column Map Excepiton. Excel column: "
					+ size + "Map table column: " + columns.length);
			throw new GlRuntimeException("Excel File Format Excepiton.");
		}

		// 取得要导入的表名称
		String tableName = "";
		if (paras.getString("tempTable") != null) {

			tableName = paras.getString("tempTable");
		} else {

			logger
					.error("Excel File Import Excepiton. import table name is null. ");
			throw new GlRuntimeException("Excel File Import Excepiton.");
		}

		String sql = " INSERT INTO " + tableName + " (";

		// 循环当前sheet的所有列名称,拼接SQL
		for (int i = 0; i < size - 1; i++) {

			// 如果有映射的表字段，取映射表字段拼接SQL
			if (columns != null && columns.length != 0) {

				sql += columns[i] + ", ";
			} else {

				sql += firstRow[i].getContents() + ", ";
			}
		}

		// 循环附加的列名集合,拼接SQL
		if (appendColumn != null) {
			Iterator names = appendColumn.keySet().iterator();
			while (names.hasNext()) {

				String name = (String) names.next();
				if (name == null) {
					continue;
				}
				sql += name + ",";
			}
		}

		// 取sheet最后一个列名,拼接SQL
		if (columns != null && columns.length != 0) {

			sql += columns[size - 1] + ") ";
		} else {

			sql += firstRow[size - 1].getContents() + ") ";
		}

		// 拼接从Excel文件取得值的SQL
		sql += " VALUES ( ";
		for (int i = 0; i < size - 1; i++) {

			// 如果有映射的表字段，取映射表字段拼接SQL
			if (columns != null && columns.length != 0) {

				sql += this.embellishString(columns[i].toString(), columnList) + ", ";
			} else {

				sql += this.embellishString(firstRow[i].getContents(),
						columnList)
						+ ", ";
			}
		}

		// 循环附加的列值集合，拼接SQL
		if (appendColumn != null) {
			Iterator it = appendColumn.keySet().iterator();
			while (it.hasNext()) {
				String itStr = (String) it.next();
				if (itStr == null) {
					continue;
				}
				sql += (String) appendColumn.get(itStr) + ",";
			}
		}

		// 拼接最后一个Excel文件中的字段.如果有映射的表字段，取映射表字段拼接SQL
		if (columns != null && columns.length != 0) {

			sql += this.embellishString(columns[size - 1].toString(), columnList) + ")";
		} else {

			sql += this.embellishString(firstRow[size - 1].getContents(),
					columnList)
					+ ")";
		}

		logger.debug(sheet.getName() + " sql:" + sql);
		return sql;
	}

	/**
	 * embellish string for IBATIS
	 * 
	 * @param columnName
	 * @param columnList
	 * @return String
	 */
	private String embellishString(String columnName, List columnList) {

		Iterator iter = columnList.iterator();
		String result;
		int dataType = CHAR;

		// 取得表字段的类型
		while (iter.hasNext()) {

			SimpleMap map = (SimpleMap) iter.next();
			if (map.getString("COLUMN_NAME").equals(columnName)) {

				dataType = Integer.parseInt(map.getString("DATA_TYPE_CODE"));
				break;
			}
		}

		switch (dataType) {
		case DATE:

			result = "#" + columnName + ":DATE#";
			break;
		case NUMBER:

			result = "#" + columnName + ":NUMERIC#";
			break;
		default:

			result = "#" + columnName + ":VARCHAR#";
			break;
		}

		return result;
	}

}
